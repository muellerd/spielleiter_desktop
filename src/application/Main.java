package application;

import control.SessionData;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static SessionData sessionData;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main_window.fxml"));
        primaryStage.setTitle("Spielleiter Desktop");
        primaryStage.setOnCloseRequest(e -> Platform.exit());
        Scene sc = new Scene(root, 1052, 665);
        sc.getStylesheets().add("styles/main.css");
        primaryStage.setScene(sc);
        primaryStage.show();
    }


    public static void main(String[] args) {
        sessionData = new SessionData();
        launch(args);
    }
}
