package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import model.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ItemController {



    public AnchorPane context;
    public TextField itemCode;
    public TextField materialId;
    public TextField unitPrice;
    public TextField qty;
    public TextField itemDesc;

    public TableView itemItemTable;
    public TableColumn tblitemcode;
    public TableColumn tblmaterilalId;
    public TableColumn tblunitprice;
    public TableColumn tblqty;
    public TableColumn tblitemDesc;
    public ComboBox matrialid;



    public  void initialize() throws SQLException, ClassNotFoundException {

        setCustomer();


    }


    private void setCustomer() throws SQLException, ClassNotFoundException {

        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT materialID FROM `materialdetail` ORDER BY materialID  DESC LIMIT 1"
                ).executeQuery();


        matrialid.setItems(getItemIds());


    }



    public static ObservableList<String> getItemIds() throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT materialID FROM `materialdetail`");

        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rst = stm.executeQuery();
        while (rst.next()){
            list.add(new String(rst.getString(1)));
        }
        return list;

    }



    public static Boolean saveItem(Item item) throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO  item VALUES (?,?,?,?)");

        stm.setObject(1,item.getItemCode());
        //stm.setObject(2,item.getMaterialId());
        stm.setObject(2,item.getUnitPrice());
        stm.setObject(3,item.getQty());
        stm.setObject(4,item.getItemDes());
        System.out.println(stm);

        return stm.executeUpdate()>0;


    }



    public void addItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Item item = new Item(itemCode.getText(),Integer.parseInt(unitPrice.getText()),Integer.parseInt(qty.getText()),itemDesc.getText());

        if(saveItem(item)) {

            new Alert(Alert.AlertType.CONFIRMATION,"Customer Update Successfully", ButtonType.OK).show();
            clear();
            success();

        }else{

            new Alert(Alert.AlertType.WARNING,"Try Again!", ButtonType.OK).show();
            clear();

        }

    }




    public void clear(){

        itemDesc.clear();
        itemCode.clear();
        unitPrice.clear();
        qty.clear();
        itemDesc.clear();
    }



    public void success(){
        itemCode.requestFocus();
        itemDesc.setStyle("-fx-border-color: #2ecc71");

        unitPrice.setStyle("-fx-border-color: #2ecc71");
        qty.setStyle("-fx-border-color: #2ecc71");
        itemDesc.setStyle("-fx-border-color: #2ecc71");
    }


    public void UpdateItem(ActionEvent actionEvent) {
    }


    public void deleteItemAction(ActionEvent actionEvent) {
    }


}
