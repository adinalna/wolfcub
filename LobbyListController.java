package warewolfculb;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LobbyListController {
    @FXML
    private ListView<Lobby> lobbyListView;

    @FXML
    private TextField gameCodeField;

    @FXML
    private Button joinButton;

    private GameManager gameManager;

    public void initialize() {
        lobbyListView.setCellFactory(param -> new LobbyListCell());
        lobbyListView.setOnMouseClicked(this::handleListViewClick);
    }

    public void setLobbyList(ObservableList<Lobby> lobbies) {
        lobbyListView.setItems(lobbies);
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    private void handleListViewClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Lobby selectedLobby = lobbyListView.getSelectionModel().getSelectedItem();
            if (selectedLobby != null) {
                // Handle joining the selected game lobby using the game code
                System.out.println("GAME SELECTED");
                // Add your code here
            }
        }
    }

    @FXML
    private void joinButtonClicked(ActionEvent event) {
        String gameCode = gameCodeField.getText();
        // Handle joining the game lobby using the entered game code
        // Add your code here
    }

    private class LobbyListCell extends ListCell<Lobby> {
        @Override
        protected void updateItem(Lobby lobby, boolean empty) {
            super.updateItem(lobby, empty);
            if (empty || lobby == null) {
                setText(null);
            } else {
                setText(lobby.getName());
            }
        }
    }
}