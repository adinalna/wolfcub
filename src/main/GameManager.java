package wolfcub.main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import wolfcub.resources.controllers.GameMenuController;
import wolfcub.resources.controllers.GameRoomController;

import java.util.ArrayList;
import java.util.List;

public class GameManager extends Application {
    private static final String GAME_ROOM_TITLE = "Game Room";
    private static List<GameRoom> gameRooms = new ArrayList<>();
    private static List<Lobby> lobbies = new ArrayList<>();
    private static GameRoomController gameRoomController = new GameRoomController();

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

    public static ObservableList<Lobby> getAvailableLobbies() {
        List<Lobby> availableLobbies = new ArrayList<>();
        //TEMP
        Lobby test1 = new Lobby("TEST1");
//        test1.joinLobby(new Player("001", "player 1"));
//        test1.joinLobby(new Player("002", "player 2"));
//        test1.joinLobby(new Player("003", "player 3"));
//        test1.joinLobby(new Player("004", "player 4"));
//        test1.joinLobby(new Player("005", "player 5"));
//        test1.joinLobby(new Player("006", "player 6"));
        availableLobbies.add(test1);
        availableLobbies.add(new Lobby("TEST2"));
        availableLobbies.add(new Lobby("TEST3"));

        return FXCollections.observableArrayList(availableLobbies);
    }

    public static GameRoomController getGameRoomController() {
        return gameRoomController;
    }

    public static GameRoom createGameRoom(String name, List<Player> players) {
        GameRoom newGameRoom = new GameRoom(name, players, gameRoomController);
        gameRooms.add(newGameRoom);
        return newGameRoom;
    }

    public static void startGameRoom(GameRoom newGameRoom) {
        new Thread(newGameRoom).start();
    }

    public static void createLobby(String name) {
        Lobby newlobby = new Lobby(name);
        lobbies.add(newlobby);
        //Add in database
    }
}