import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws IOException {


        Parent parent = FXMLLoader.load(this.getClass().getResource("view/passwordScreen.fxml"));
        Scene scene = new Scene(parent);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setTitle("Welcome");
        primaryStage.centerOnScreen();
        primaryStage.show();


    }
    @FXML
    public void exitApplication(ActionEvent event) {
        Platform.exit();
    }
}
