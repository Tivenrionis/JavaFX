package BackgroundThreads;

import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

//Thread  in FX UI extends Task or Worker
public class Controller {
    // private Task<ObservableList<String>> task;
    @FXML
    private ListView listView;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label progressLabel;

    private Service<ObservableList<String>> service;


    public void initialize() {
//        task = new Task<ObservableList<String>>() {
//            @Override
//            protected ObservableList<String> call() throws Exception {
//
//                String[] names = {"Michal Wes",
//                        "Julia Wes",
//                        "Wes Michal",
//                        "Wes Julia", "Wes KID", "Wes ROFT", "Wes LOR", "LOR Julia"};
//
//                ObservableList<String> employees = FXCollections.observableArrayList();
//
//                for (int i = 0; i < 8; i++) {
//                    employees.add(names[i]);
//                    progressBar.setVisible(true);
//                    updateMessage("Added " + names[i] + " to the list");
//                    updateProgress(i + 1, 8);
//                    Thread.sleep(200);
//                }
//
//                return employees;
//            }
//        };

        service = new EmployeeService();

//        service.setOnRunning(new EventHandler<WorkerStateEvent>() {
//            @Override
//            public void handle(WorkerStateEvent event) {
//                progressBar.setVisible(true);
//                progressLabel.setVisible(true);
//            }
//        });
//
//        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
//            @Override
//            public void handle(WorkerStateEvent event) {
//                progressLabel.setVisible(false);
//                progressBar.setVisible(false);
//            }
//        });

        progressBar.progressProperty().bind(service.progressProperty());
        progressLabel.textProperty().bind(service.messageProperty());
        listView.itemsProperty().bind(service.valueProperty());

        progressBar.visibleProperty().bind(service.runningProperty());
        progressLabel.visibleProperty().bind(service.runningProperty());
    }

    @FXML
    public void buttonPressed() {
        if (service.getState() == Service.State.SUCCEEDED) {
            service.reset();
            service.start();
        } else if (service.getState() == Service.State.READY) {
            service.start();

        }
    }
}
