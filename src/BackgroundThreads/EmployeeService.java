package BackgroundThreads;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class EmployeeService extends Service<ObservableList<String>> {


    @Override
    protected Task<ObservableList<String>> createTask() {
        return new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() throws Exception {

                String[] names = {"Michal Wes",
                        "Julia Wes",
                        "Wes Michal",
                        "Wes Julia", "Wes KID", "Wes ROFT", "Wes LOR", "LOR Julia"};

                ObservableList<String> employees = FXCollections.observableArrayList();

                for (int i = 0; i < 8; i++) {
                    employees.add(names[i]);
                    updateMessage("Added " + names[i] + " to the list");
                    updateProgress(i + 1, 8);
                    Thread.sleep(200);
                }

                return employees;
            }
        };
    }
}
