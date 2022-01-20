package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import model.OderDetails;
import model.OderItem;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OderController {


    public static ObservableList<String> getItemIds() throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT itemCode FROM `item`");
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rst = stm.executeQuery();
        while (rst.next()){
            list.add(new String(rst.getString(1)));
        }
        return list;

    }


    public static String getCusName(String id) throws SQLException, ClassNotFoundException {


        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT CustName from customer WHERE CustId=?");
        preparedStatement.setObject(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();


        if (resultSet.next()){
            return resultSet.getString("CustName");
       }
        return null;
    }


    public static ObservableList<String> getCusIds() throws SQLException, ClassNotFoundException {


        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT CustId FROM `Customer`");
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rst = stm.executeQuery();
        while (rst.next()){
            list.add(new String(rst.getString(1)));
        }

        return list;

    }


    public static Item getItemByIds(String id) throws SQLException, ClassNotFoundException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from item WHERE ItemCode=?");
        preparedStatement.setObject(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();


        if (resultSet.next()){

            return new Item(

                    resultSet.getString("ItemCode"),
                    resultSet.getInt("unitPrice"),
                    resultSet.getInt("Qty"),
                    resultSet.getString("itemDes")

            );
        }
        return null;
    }


    public static String getLastOderId() throws SQLException, ClassNotFoundException {


        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT OrderID  from orderitem ORDER BY OrderID DESC LIMIT 1");
        ResultSet resultSet = preparedStatement.executeQuery();


        if (resultSet.next()){

                    resultSet.getString("OrderID");
        }
        return null;
    }

    public boolean placeOrder(Order order, ArrayList<OderItem> detailLst) throws SQLException, ClassNotFoundException {

        Connection connection= null;
        try {

            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            if(isOrderAdded(order,connection) && isOrderDetailAdded(detailLst,connection)){
                connection.commit();
                return true;
            }else{
                connection.rollback();
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                assert connection != null;
                connection.setAutoCommit(true);
            }catch ( SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean isOrderDetailAdded(ArrayList<OderItem> detailLst, Connection connection) {
        try {
            int executedRows=0;
            for(OderItem oderItem:detailLst){
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orderitem VALUES (?,?,?,?,?,?)");
                preparedStatement.setObject(1,oderItem.getOderId());
                preparedStatement.setObject(2,oderItem.getItemCode());
                preparedStatement.setObject(3,oderItem.getOderQty());
                preparedStatement.setObject(4,oderItem.getUnitPrice());
                preparedStatement.setObject(5,oderItem.getCusId());
                preparedStatement.setObject(6,oderItem.getTotalAmount());
                if(preparedStatement.executeUpdate() > 0){
                    ++executedRows;
                }else{
                    return false;
                }
            }
            return executedRows==detailLst.size();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    private boolean isOrderAdded(Order order, Connection connection) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO orders values(?,?,?,?)");
            preparedStatement.setObject(1,order.getOid());
            preparedStatement.setObject(2,order.getCusId());
            preparedStatement.setObject(3,order.getLocalDate());
            preparedStatement.setObject(4,order.getTotalAmount());
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean updateQty (String id,int qty){


        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE item set qty = Qty-? WHERE itemCode=?");
            preparedStatement.setObject(1,qty);
            preparedStatement.setObject(2,id);
            return preparedStatement.executeUpdate()>0;


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}


