package controller;

import db.DbConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static controller.OderController.getCusIds;
import static controller.OderController.getItemIds;
import static java.lang.Double.parseDouble;


public class PlaceOderFromController {


    public Label lblTotal;
    public TableColumn<OrderTm,Double> colTotal;
    public TableColumn<OrderTm,Double> colUnitPrice;
    public TableColumn<OrderTm,Integer> colQty;
    public TableColumn<OrderTm,String> colDescription;
    public TableColumn<OrderTm,String> colId;
    public TableView <OrderTm> tblPlaceOrder;
    public TextField txtOderQty;
    public TextField txtItemQty;
    public TextField txtPrice;
    public TextField txtItemDescription;
    public TextField txtCustName;
    public ComboBox<String> cmdId;
    public ComboBox <String> cmdCustId ;
    public AnchorPane context;
    public Label txtOderId;

    ObservableList<OrderTm> obList= FXCollections.observableArrayList();

    public void initialize() throws SQLException, ClassNotFoundException {
        txtOderId.setText(oderIdGen());

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        //tblPlaceOrder.getColumns().setAll(colId,colDescription,colQty,colUnitPrice,colTotal);

        setCustomer();
        setIds();


        cmdCustId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                getCustomerName(newValue);

            }
        });


        cmdId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                getItemDetails(newValue);
            }
        });
    }





    public void addToCartOnAction(ActionEvent actionEvent) {


        if(txtOderQty.getText().isEmpty() || Integer.parseInt(txtOderQty.getText()) > Integer.parseInt(txtItemQty.getText()) ){
             new Alert(Alert.AlertType.WARNING,"Try Again...").show();

             return;

         }

        String itemId =cmdId.getSelectionModel().getSelectedItem();
        String itemDesc = txtItemDescription.getText();
        int oderQty = Integer.parseInt(txtOderQty.getText());
        double itemPrice = parseDouble(txtPrice.getText());
        double itemTotal = parseDouble(txtPrice.getText()) * Integer.parseInt(txtOderQty.getText());


        OrderTm orderTm =new OrderTm(

                itemId,
                itemDesc,
                oderQty,
                itemPrice,
                itemTotal

        );

       int index = findItemTable(itemId);
        if(index == -1){
            obList.add(orderTm);
        }else {
            OrderTm table=obList.get(index);
            obList.remove(index);
            int newQty = table.getQty()+oderQty;
            double newTotal=table.getTotal()+itemTotal;
            table.setQty(newQty);
            table.setTotal(newTotal);
            obList.add(table);
        }
        tblPlaceOrder.setItems(obList);
        calAmount ();
    }



    public int findItemTable(String id){

        for (int i=0;i < obList.size();i++){
            if (obList.get(i).getId().equals(id))
                return i;
        }

        return -1;
    }




    public void calAmount (){

     double total =0;
     for (OrderTm item :obList){

         total+=item.getTotal();


     }
        lblTotal.setText(String.valueOf(total));
    }





    private void setIds() throws SQLException, ClassNotFoundException {


        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT itemCode FROM `item` ORDER BY itemCode DESC LIMIT 1"
                ).executeQuery();


        cmdId.setItems(getItemIds());


    }


    public void getCustomerName(String id){


        try {
            String cusName = OderController.getCusName(id);
            txtCustName.setText(cusName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


    private void setCustomer() throws SQLException, ClassNotFoundException {


        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT Custid FROM `customer` ORDER BY Custid  DESC LIMIT 1"
                ).executeQuery();

        cmdCustId.setItems(getCusIds());


    }


    public void getItemDetails(String id)  {


        try {

           Item itemById = OderController.getItemByIds(id);

                txtItemDescription.setText(itemById.getItemDes());
                txtItemQty.setText(String.valueOf(itemById.getQty()));
                txtPrice.setText(String.valueOf(itemById.getUnitPrice()));


        } catch (SQLException | ClassNotFoundException e) {
           e.printStackTrace();
        }

    }

    public String oderIdGen() throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement("SELECT OrderID FROM  orders Order BY OrderID  DESC LIMIT 1");
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int tempId = Integer.
                    parseInt(resultSet.getString(1).split("-")[1]);
            tempId = tempId + 1;
            if (tempId < 10) {
                return "S-00" + tempId;
            } else if (tempId < 100) {
                return "S-0" + tempId;
            } else {
                return "S-" + tempId;
            }
        } else {
            return "S-001";
        }
    }





    public void placeOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ArrayList<OderItem> detailLst = new ArrayList<>();
        for (OrderTm tm : obList) {
            OderItem oderItem = new OderItem(

                    txtOderId.getText(),
                    tm.getId(),
                    tm.getQty(),
                    tm.getUnitPrice(),
                    cmdCustId.getValue(),
                    tm.getTotal()

            );
        }


        LocalDate date = LocalDate.now();

        Order order = new Order(

                txtOderId.getText(),
                date,
                cmdCustId.getValue(),
                parseDouble(lblTotal.getText()

        ));

        if (new OderController().placeOrder(order, detailLst)) {
            new Alert(Alert.AlertType.INFORMATION, "Success..").show();
            txtOderId.setText(oderIdGen());
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }

    }

}



