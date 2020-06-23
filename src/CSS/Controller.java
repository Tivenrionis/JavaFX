package CSS;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class Controller {
    @FXML
    private Label label;
    @FXML
    private Button button4;
    @FXML
    private GridPane gridPane;
    @FXML
    private WebView webView;

    public void initialize() {
//        label.setScaleX(6);
//        label.setScaleY(2.5);
        button4.setEffect(new DropShadow());
    }

    @FXML
    public void handleClick() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save Application File");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text", "*.txt"),
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        // DirectoryChooser chooser = new DirectoryChooser();
//        File file = chooser.showSaveDialog(gridPane.getScene().getWindow());
//        if (file != null) {
//            System.out.println(file.getPath());
//        } else System.out.println("Chooser was canceled");
        List<File> files = chooser.showOpenMultipleDialog(gridPane.getScene().getWindow());
        if (files != null) {
            for (File file : files) {
                System.out.println(file.getPath());
            }
        } else System.out.println("Chooser was canceled");
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

    @FXML
    public void handleClicked() {
//        System.out.println("Link was clicked");
//        try {
//            Desktop.getDesktop().browse(new URI("http://www.javafx.com"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (URISyntaxException u) {
//            u.printStackTrace();
//        }

        WebEngine engine = webView.getEngine();
        engine.load("http://www.javafx.com");
    }
}
