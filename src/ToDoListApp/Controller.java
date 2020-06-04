package ToDoListApp;

import ToDoListApp.dataModel.ToDoData;
import ToDoListApp.dataModel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class Controller {
    private List<TodoItem> todoItems;
    @FXML
    private Label deadLineLabel;

    @FXML
    private ListView<TodoItem> todoListView;
    @FXML
    private TextArea textArea;
    @FXML
    private BorderPane mainBorderPane;

    public void initialize() {
//        TodoItem item1 = new TodoItem("Mail birthday card", "Buy a 30th birthday card for John",
//                LocalDate.of(2016, Month.APRIL, 25));
//        TodoItem item2 = new TodoItem("Doctor's Appointment", "See Dr. Smith at 123 Main Street. Bring paperwork",
//                LocalDate.of(2016, Month.MAY, 23));
//        TodoItem item3 = new TodoItem("Finish design proposal for client", "I promised Mike I'd email website rockups by Friday 22nd April",
//                LocalDate.of(2016, Month.APRIL, 22));
//        TodoItem item4 = new TodoItem("Pickup Doug at the train station", "Doug's arriving on March 23 on the 5:00 train",
//                LocalDate.of(2016, Month.MARCH, 23));
//        TodoItem item5 = new TodoItem("Pick up dry cleaning", "The clothes should be ready by Wendsday",
//                LocalDate.of(2016, Month.APRIL, 22));
//        todoItems = new ArrayList<TodoItem>();
//        todoItems.add(item1);
//        todoItems.add(item2);
//        todoItems.add(item3);
//        todoItems.add(item4);
//        todoItems.add(item5);
//
//        ToDoData.getInstance().setItems(todoItems);

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            // old poprzedni wybrany item, new nowo wybrany item ... by default oba null
            @Override
            public void changed(ObservableValue<? extends TodoItem> observable, TodoItem oldValue, TodoItem newValue) {

                if (newValue != null) {
                    TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                    textArea.setText(item.getDetails());
                    //date forrmating -> w dokumentacji wiecej przykladow
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    //end of formatting
                    deadLineLabel.setText(dateTimeFormatter.format(item.getDeadLine()));

                }

            }
        });

        todoListView.getItems().setAll(ToDoData.getInstance().getItems());
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();

    }

    @FXML
    public void showNewItemDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add New ToDo Item");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("todoItemDialog.fxml"));
        try {
            //  Parent root = FXMLLoader.load(getClass().getResource("todoItemDialog.fxml"));
            dialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {
            System.out.println("Couldnt load the dialog");
            e.printStackTrace();
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DialogController controller = loader.getController();
            TodoItem newItem = controller.processResults();
            todoListView.getItems().setAll(ToDoData.getInstance().getItems());
            todoListView.getSelectionModel().select(newItem);
            System.out.println("OK pressed");
        } else {
            System.out.println("Cancel pressed");
        }
        //dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

    }

    //odkad ustawilismy change listener to ta funkcja jest nam niepotrzebna bo change listener wystepuje w roli event handlera
    @FXML
    public void handleClickListView() {
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
        textArea.setText(item.getDetails());
        deadLineLabel.setText(item.getDeadLine().toString());
        //System.out.println(item);
//        StringBuilder sb = new StringBuilder(item.getDetails());
//        sb.append("\n\n\n\n");
//        sb.append("Due: ");
//        sb.append(item.getDeadLine().toString());
        //     textArea.setText(sb.toString());


    }

}
