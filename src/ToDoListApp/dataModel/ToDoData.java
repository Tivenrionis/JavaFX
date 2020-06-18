package ToDoListApp.dataModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ToDoData {
    private static ToDoData instance = new ToDoData();
    private static String fileName = "ToDoListItems.txt";
    // Trzeba zrobic z tego observable aby moc podpiac handlery, by na nowo nie populowac listy przy dodawaniu itemu
    // private List<TodoItem> items;
    private ObservableList<TodoItem> items;
    private DateTimeFormatter formatter;

    private ToDoData() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public static ToDoData getInstance() {
        return instance;
    }

    public ObservableList<TodoItem> getItems() {
        return items;
    }

    public void addToDoItem(TodoItem item) {
        if (item != null) {
            items.add(item);
        }
    }

    public void loadTodoItems() throws IOException {
        items = FXCollections.observableArrayList();
        Path path = Paths.get(fileName);
        BufferedReader br = Files.newBufferedReader(path);
        String input;

        try {
            while ((input = br.readLine()) != null) {
                String[] itemPieces = input.split("\t");
                String shortDescription = itemPieces[0];
                String details = itemPieces[1];
                String dateString = itemPieces[2];
                LocalDate date = LocalDate.parse(dateString, formatter);
                TodoItem todoItem = new TodoItem(shortDescription, details, date);
                items.add(todoItem);

            }
        } finally {
            if (br != null) {
                br.close();
            }
        }

    }

    public void storeTodoItems() throws IOException {
        Path path = Paths.get(fileName);
        BufferedWriter bw = Files.newBufferedWriter(path);

        try {
            for (TodoItem item : items) {
                bw.write(String.format("%s\t%s\t%s",
                        item.getShortDescription(),
                        item.getDetails(),
                        formatter.format(item.getDeadLine())));
                bw.newLine();
            }
        } finally {
            if (bw != null) {
                bw.close();
            }
        }

    }
}
