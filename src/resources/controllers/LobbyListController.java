package wolfcub.resources.controllers;

import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.control.ListCell;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import wolfcub.main.Lobby;


public class LobbyListController {

    private static final String LOBBY_FXML = "../views/lobby.fxml";

    @FXML
    private ListView<Lobby> lobbyListView;

    @FXML
    private TextField gameCodeField;

    @FXML
    private Button joinButton;

    private GameManager gameManager;

    @FXML
    private Button backMenuButton;

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
                Player currentPlayer = new Player("007","John"); // Replace with the actual player object
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


    @FXML
    private void backMenuClicked(ActionEvent event) {
        try {
            // Load the rules.fxml file
            FXMLLoader loader = FXMLFetcher.loadGameMenuFxml();
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) lobbyListView.getScene().getWindow();

            // Set the root as the content of the current stage
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public class LobbyListCell extends ListCell<Lobby> {
        private Text lobbyText;

        public LobbyListCell() {
            lobbyText = new Text();
            lobbyText.setTextAlignment(TextAlignment.CENTER);

            StackPane container = new StackPane(lobbyText);
            container.setPrefHeight(50);  // Set the preferred height for the cell
            container.setMinHeight(40);   // Set the minimum height for the cell

            Rectangle outline = new Rectangle();
            outline.setStroke(Color.BLACK);  // Set the outline color
            outline.setFill(Color.TRANSPARENT);  // Set the fill color to transparent
            outline.setWidth(container.getPrefWidth());  // Match the outline width with the container width
            outline.heightProperty().bind(container.heightProperty());  // Match the outline height with the container height

            container.getChildren().add(outline);

            setGraphic(container);
        }

        @Override
        protected void updateItem(Lobby lobby, boolean empty) {
            super.updateItem(lobby, empty);
            if (empty || lobby == null) {
                setText(null);
                lobbyText.setText(null);
            } else {
                lobbyText.setText(lobby.getName() + "\n Players: 5/7");
                //lobbyText.setText(lobby.getName() + "\n Players: " + lobby.getPlayers());
            }
        }
    }






}