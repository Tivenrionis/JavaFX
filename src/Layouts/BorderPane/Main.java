package Layouts.BorderPane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//init start stop
// Commonly use as a top level layout

//   -------------------------------
//
//   -------------------------------
//  |       |               |       |
//  |       |               |       |
//  |       |               |       |
//  |       |               |       |
//  |       |               |       |
//  |       |               |       |
//  |       |               |       |
//  |       |               |       |
//   -------------------------------
//
//   -------------------------------



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello BorderPane!");
        primaryStage.setScene(new Scene(root, 500, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
