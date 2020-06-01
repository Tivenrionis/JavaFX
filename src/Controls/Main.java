package Controls;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//init start stop
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello JavaFX Controls!");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
