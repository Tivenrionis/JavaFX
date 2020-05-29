package Layouts.FlowPane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//init start stop
// Commonly use as a top level layout with BorderPane
//Jak nie wiesz ile childrenow bedzie to uzywac flowpane... zmienia polozenie wraz ze zmiana okna

//FlowPane , TilePane z paddingiem, StackPane np do zdjec i nakladania na innych elementow na siebie


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello FlowPane!");
        primaryStage.setScene(new Scene(root, 500, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
