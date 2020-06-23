package CSS;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class Controller {
    @FXML
    private Label label;
    @FXML
    private Button button4;
    @FXML
    private GridPane gridPane;

    public void initialize() {
//        label.setScaleX(6);
//        label.setScaleY(2.5);
        button4.setEffect(new DropShadow());
    }

    @FXML
    public void handleClick() {
        //  FileChooser chooser = new FileChooser();
        DirectoryChooser chooser = new DirectoryChooser();
        File file = chooser.showDialog(gridPane.getScene().getWindow());
        if (file != null) {
            System.out.println(file.getPath());
        }
        else System.out.println("Chooser was canceled");

    }

    @FXML
    public void handleMouseEnter() {
        label.setScaleX(2);
        label.setScaleY(2);
    }

    @FXML
    public void handleMouseExit() {
        label.setScaleX(1);
        label.setScaleY(1);
    }
}
