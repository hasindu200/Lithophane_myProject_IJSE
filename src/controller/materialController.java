package controller;

import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.Item;
import model.Material;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class materialController {


    public AnchorPane context;

    public TextField materialId;
    public TextField materialPrice;
    public TextField materialWeight;
    public TextField materialDes;

    public TableView tblmaterial;
    public TableColumn colMaterialId;
    public TableColumn colPrice;
    public TableColumn colWeight;
    public TableColumn colMaterialDes;


    public static Boolean saveMaterial(Material material) throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO  materialdetail VALUES (?,?,?,?)");

        stm.setObject(1, material.getMaterialID());
        stm.setObject(2, material.getMaterialDesc());
        stm.setObject(3, material.getPrice());
        stm.setObject(4, material.getWeight());

        System.out.println(stm);

        return stm.executeUpdate() > 0;

    }




    public void addMaterialOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        Material material = new Material(materialId.getText(),materialDes.getText(), Integer.parseInt(materialPrice.getText()), Integer.parseInt(materialWeight.getText()));

            if (saveMaterial(material)) {

                new Alert(Alert.AlertType.CONFIRMATION, "Customer Update Successfully", ButtonType.OK).show();
                clear();
                success();

            } else {

                new Alert(Alert.AlertType.WARNING, "Try Again!", ButtonType.OK).show();
                clear();

            }


    }


    public void clear(){

        materialDes.clear();
        materialWeight.clear();
        materialId.clear();
        materialPrice.clear();

    }

    public void success(){

        materialId.requestFocus();
        materialPrice.setStyle("-fx-border-color: #2ecc71");
        materialId.setStyle("-fx-border-color: #2ecc71");
        materialDes.setStyle("-fx-border-color: #2ecc71");
        materialWeight.setStyle("-fx-border-color: #2ecc71");

    }


    public void updateMaterialOnAction(ActionEvent actionEvent) {
    }

    public void deleteMaterialOnAction(ActionEvent actionEvent) {
    }
}
