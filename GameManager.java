/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package warewolfculb;

/**
 *
 * @author HP
 */
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameManager extends Application {
    private static final String GAME_MENU_FXML = "game-menu.fxml";
    private static final String LOBBY_FXML = "lobby.fxml";
    private static final String LOBBY_LIST_FXML = "lobby-list.fxml";
    private static final String GAME_ROOM_FXML = "game-room.fxml";
    private static final String GAME_ROOM_TITLE = "Game Room";

    private LobbyListController lobbyListController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader gameMenuLoader = new FXMLLoader(getClass().getResource(GAME_MENU_FXML));
        Parent gameMenuRoot = gameMenuLoader.load();
        GameMenuController gameMenuController = gameMenuLoader.getController();

        Scene gameMenuScene = new Scene(gameMenuRoot);
        primaryStage.setScene(gameMenuScene);
        primaryStage.setTitle("Game Menu");
        primaryStage.show();

        gameMenuController.setGameManager(this);
    }

    public ObservableList<Lobby> getAvailableLobbies() {
        // Retrieve the available lobbies from wherever you store them
        // Return the list of lobbies
        List<Lobby> availableLobbies = new ArrayList<>();

        // Example code to add dummy lobbies
        availableLobbies.add(new Lobby());
        availableLobbies.add(new Lobby());
        availableLobbies.add(new Lobby());

        return (ObservableList<Lobby>) availableLobbies;
    }

    public LobbyListController getLobbyListController() {
        return lobbyListController;
    }

    public void showLobbyList() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(LOBBY_LIST_FXML));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Lobby List");
            stage.show();

            lobbyListController = loader.getController();
            lobbyListController.setGameManager(this);
            lobbyListController.initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}