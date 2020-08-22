package DatabaseExample;

import DatabaseExample.model.Album;
import DatabaseExample.model.Artist;
import DatabaseExample.model.Datasource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class Controller {

    @FXML
    private TableView artistTable;

    public void listArtists() {
        Task<ObservableList<Artist>> task = new GetAllArtistsTask();
        artistTable.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();

    }

    @FXML
    public void listAlbumsForArtist() {
        final Artist artist = (Artist) artistTable.getSelectionModel().getSelectedItem();
        if (artist == null) {
            System.out.println("NO ARTISTS SELECTED");
        } else {
            Task task = new Task() {
                @Override
                protected ObservableList<Album> call() {
                    return FXCollections.observableArrayList(
                            Datasource.getInstance().queryAlbumsForArtistID(artist.getId())
                    );
                }
            };
            //binding here
            artistTable.itemsProperty().bind(task.valueProperty());
            new Thread(task).start();

        }
    }

}

class GetAllArtistsTask extends Task {

    @Override
    public ObservableList<Artist> call() {
        return FXCollections.observableArrayList(
                Datasource.getInstance().queryArtists(Datasource.ORDER_BY_ASC));
    }
}