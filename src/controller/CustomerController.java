package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Customer;


import java.sql.*;
import java.util.ArrayList;

public class CustomerController {


    public AnchorPane context;
    public TableView tblCustomres;
    public TableColumn <Customer,String> tblcusId;
    public TableColumn <Customer,String>tblcusName;
    public TableColumn<Customer,String> tblcusNic;
    public TableColumn <Customer,String>tblCusAddress;
    public TableColumn<Customer,String> tblCusPhone;
    public TextField cusAddress;
    public TextField cusNumber;
    public TextField cusNic;
    public TextField custName;
    public Label custId;



    ObservableList<Customer> obList= FXCollections.observableArrayList();

    public void initialize() throws SQLException, ClassNotFoundException {
        custId.setText(getOrderId());

        tblcusId.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        tblcusName.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        tblcusNic.setCellValueFactory(new PropertyValueFactory<>("cusNic"));
        tblCusAddress.setCellValueFactory(new PropertyValueFactory<>("cusAddress"));
        tblCusPhone.setCellValueFactory(new PropertyValueFactory<>("cusNumber"));



    }


    public String getOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT CustId FROM `customer` ORDER BY CustId  DESC LIMIT 1"
                ).executeQuery();
        if (rst.next()){

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<9){
                return "O-00"+tempId;
            }else if(tempId<99){
                return "O-0"+tempId;
            }else{
                return "O-"+tempId;
            }
        }else{
            return "O-001";
        }
    }

                            //                  SAVE CUSTOMER


    public static Boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO Customer VALUES (?,?,?,?,?)");

        stm.setObject(1,customer.getCusId());
        stm.setObject(2,customer.getCusName());
        stm.setObject(3,customer.getCusNic());
        stm.setObject(4,customer.getCusAddress());
        stm.setObject(5,customer.getCusNumber());
        System.out.println(stm);

        return stm.executeUpdate()>0;

    }






    public void addCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Customer customer = new Customer(custId.getText(),custName.getText(),cusNic.getText(),cusAddress.getText(),cusNumber.getText());
        obList.add(customer);
        tblCustomres.setItems(obList);
        getOrderId();

       // ArrayList<Customer> detailLst = new ArrayList<>();

            if(saveCustomer(customer)) {


            new Alert(Alert.AlertType.CONFIRMATION,"Customer Update Successfully", ButtonType.OK).show();
            getOrderId();
            clear();
            success();


        }else{

            new Alert(Alert.AlertType.WARNING,"Try Again!", ButtonType.OK).show();
            clear();

        }


    }

                                        // UPDATE CUSTOMER


    public static Boolean updateCustomer(Customer customerName) throws SQLException, ClassNotFoundException {


        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement
                ("UPDATE customer SET CustId=?,CustName=?,CustAddress=?,CustNic=?,Custphone=? WHERE CustName='"+customerName.getCusName()+"'");
        stm.setObject(1,customerName.getCusId());
        stm.setObject(2,customerName.getCusName());
        stm.setObject(3,customerName.getCusNic());
        stm.setObject(4,customerName.getCusAddress());
        stm.setObject(5,customerName.getCusNumber());


        return stm.executeUpdate()>0;

    }



    public void updateCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Customer customer = new Customer(custId.getText(),custName.getText(),cusNic.getText(),cusAddress.getText(),cusNumber.getText());


        if(updateCustomer(customer)) {

            new Alert(Alert.AlertType.CONFIRMATION,"Customer Update Successfully", ButtonType.OK).show();
            clear();

        }else{

            new Alert(Alert.AlertType.WARNING,"Try Again!", ButtonType.OK).show();

        }

    }


                                        // Delete Customer


    public void DeleteCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        if (new CustomerController().deleteCustomer(custName.getText())){

            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            clear();
            success();

        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
            clear();
        }


    }


    public Boolean deleteCustomer(String customerName) throws SQLException, ClassNotFoundException {

        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Customer WHERE  CustName='"+customerName+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }


    }



    public void tblCustomres(SortEvent<TableView> tableViewSortEvent) {


    }





    public void clear(){

        cusNumber.clear();
        cusAddress.clear();
        cusNic.clear();
        custName.clear();

    }


    public void success(){

        custName.requestFocus();
        custName.setStyle("-fx-border-color: #2ecc71");
        cusAddress.setStyle("-fx-border-color: #2ecc71");
        cusNic.setStyle("-fx-border-color: #2ecc71");
        cusNumber.setStyle("-fx-border-color: #2ecc71");

    }

}
