package controller;

import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delivery {

    public AnchorPane context;
    public TextField deliveryId;
    public TextField contactNum;
    public ComboBox custId;
    public TableView diliveryTable;
    public TableColumn tblDeliveryId;
    public TableColumn tblCustId;
    public TableColumn tblContactNum;
    public TableColumn tblDiliveryAdress;
    public TextField deliveryAddress;



    public static Boolean saveDelivery(model.Delivery delivery) throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO DiliveryDetails VALUES (?,?,?,?)");
        Customer customer=new Customer();
        stm.setObject(1,delivery.getDeliveryId());
        //  stm.setObject(2,customer.getCusId());
        stm.setObject(3,delivery.getContactNum());
        stm.setObject(4,delivery.getDeliveryAddress());


        System.out.println(stm);

        return stm.executeUpdate()>0;

    }



    public void addDeliveryOnAction(ActionEvent actionEvent) {


    }

    public void updateDiliveryOnAction(ActionEvent actionEvent) {



    }

    public void deleteDiliveryOnAction(ActionEvent actionEvent) {


    }


}
