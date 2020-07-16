package BackgroundThreads;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

//Thread  in FX UI extends Task or Worker
public class Controller {
    private Task<ObservableList<String>> task;
    @FXML
    private ListView listView;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label progressLabel;


    public void initialize() {
        task = new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() throws Exception {

                String[] names = {"Michal Wes",
                        "Julia Wes",
                        "Wes Michal",
                        "Wes Julia", "Wes KID", "Wes ROFT", "Wes LOR", "LOR Julia"};

                ObservableList<String> employees = FXCollections.observableArrayList();

                for (int i = 0; i < 8; i++) {
                    employees.add(names[i]);
                    progressBar.setVisible(true);
                    updateMessage("Added " + names[i] + " to the list");
                    updateProgress(i + 1, 8);
                    Thread.sleep(200);
                }

                return employees;
            }
        };
        progressBar.progressProperty().bind(task.progressProperty());
        progressLabel.textProperty().bind(task.messageProperty());
        listView.itemsProperty().bind(task.valueProperty());
    }

    @FXML
    public void buttonPressed() {
        new Thread(task).start();

    }
}
