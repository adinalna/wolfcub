package wolfcub.main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import wolfcub.resources.controllers.GameMenuController;
import java.util.ArrayList;
import java.util.List;

public class GameManager extends Application {
    private static final String GAME_ROOM_TITLE = "Game Room";
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader gameMenuLoader = FXMLFetcher.loadGameMenuFxml();
        Parent gameMenuRoot = gameMenuLoader.load();
        GameMenuController gameMenuController = gameMenuLoader.getController();

        Scene gameMenuScene = new Scene(gameMenuRoot);
        primaryStage.setScene(gameMenuScene);
        primaryStage.setTitle(GAME_ROOM_TITLE);
        primaryStage.show();

        gameMenuController.setGameManager(this);
    }

    public ObservableList<Lobby> getAvailableLobbies() {
        // Retrieve the available lobbies from wherever you store them
        // Return the list of lobbies
        List<Lobby> availableLobbies = new ArrayList<>();

        // Example code to add dummy lobbies
        availableLobbies.add(new Lobby("TEST1"));
        availableLobbies.add(new Lobby("TEST2"));
        availableLobbies.add(new Lobby("TEST3"));

        return FXCollections.observableArrayList(availableLobbies);
    }
}