package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PasswordScreenController {

    public AnchorPane root;
    public TextField lblUserName;
    public PasswordField lblPassword;
    
    public void loginOnAc(ActionEvent actionEvent) throws IOException {


        String userName = "admin";
        String password = "1234";

        if (lblUserName.getText().equals(userName)&&(lblPassword.getText().equals(password))){


            Parent parent = FXMLLoader.load(this.getClass().getResource("../view/dashBoard.fxml"));
            Scene scene = new Scene(parent);
            Stage primaryStage =(Stage) this.root.getScene().getWindow();

            primaryStage.setScene(scene);
            primaryStage.setTitle("Welcome");
            primaryStage.centerOnScreen();
            primaryStage.show();



        }else{

            (new Alert(Alert.AlertType.ERROR, "Invalid User Name or Password", new ButtonType[0])).showAndWait();

            lblUserName.clear();
            lblPassword.clear();
            lblUserName.requestFocus();

        }




    }
}
