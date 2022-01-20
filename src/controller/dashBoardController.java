package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;


public class dashBoardController {


    public Pane root;
    public Pane context;
    public ImageView clasoOnAction;
    public ImageView btnClose;
    public ImageView calculationOnAction;
    private Object ActionEvent;

    public void logOutOnAction(MouseEvent mouseEvent) throws IOException {

        Parent parent = FXMLLoader.load(this.getClass().getResource("/view/passwordScreen.fxml"));
        Scene scene = new Scene(parent);
        Stage primaryStage =(Stage) this.root.getScene().getWindow();

        primaryStage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setTitle("Welcome");

    }

    public void addItemOnAction(MouseEvent mouseEvent) throws IOException {

        loadUi("item");

    }

    public void customerFrom(MouseEvent mouseEvent) throws IOException {

       loadUi("customer");

    }

    public void clasoOnAction(MouseEvent mouseEvent) {

    closeWindow();

    }

    private void closeWindow() {
        final Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void item(MouseEvent mouseEvent) throws IOException {

    loadUi("item");

    }

    public void MaterialOnAction(MouseEvent mouseEvent) throws IOException {

        loadUi("materialdetail");

    }

    public void deliveryOnAction(MouseEvent mouseEvent) throws IOException {

        loadUi("delivery");
    }

    public void HomeOnAction(MouseEvent mouseEvent) {


    }

    public void customerOnAction(MouseEvent mouseEvent) throws IOException {
        loadUi("PlacOrderFrom");

    }

    public void calculationOnAction(MouseEvent mouseEvent) throws IOException {

    loadUi("currentCalculator");


    }



    public void loadUi(String fileName) throws IOException {

        URL resource = getClass().getResource("/view/" + fileName + ".fxml");
        Parent parent= FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(parent);

    }



}
