package wolfcub.resources.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import wolfcub.main.FXMLFetcher;
import wolfcub.main.Lobby;
import wolfcub.main.Player;
import wolfcub.main.GameManager ;
import java.io.IOException;

public class LobbyListController {

    private static final String LOBBY_FXML = "../views/lobby.fxml";

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
                Player currentPlayer = new Player("001","John"); // Replace with the actual player object
                selectedLobby.joinLobby(currentPlayer);

                // Load the lobby.fxml view and set up the LobbyController
                try {
                    FXMLLoader loader = FXMLFetcher.loadLobbyFxml();
                    Parent root = loader.load();
                    LobbyController lobbyController = loader.getController();
                    lobbyController.setLobby(selectedLobby);

                    // Replace the current stage with the lobby view
                    Stage currentStage = (Stage) lobbyListView.getScene().getWindow();
                    currentStage.setScene(new Scene(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
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