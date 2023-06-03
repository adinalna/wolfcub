package warewolfculb;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

public class LobbyListController {
    private GameManager gameManager;

    @FXML
    private ListView<Lobby> lobbyListView;

    public void initialize() {
        List<Lobby> availableLobbies = gameManager.getAvailableLobbies();
        ObservableList<Lobby> lobbyList = FXCollections.observableList(availableLobbies);
        lobbyListView.setItems(lobbyList);
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
}