package warewolfculb;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
public class LobbyController {

    private int playerIdCounter = 1;
    private static final int MAX_PLAYERS = 7;

    private Lobby currentLobby;

    @FXML
    private ListView<String> playerListView;

    private List<Player> players;
    private Timer timer;
    private int playerCounter;

    @FXML
    private Button toGameRoomButton;

    public void initialize() {
        players = new ArrayList<>();
        playerCounter = 0;

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                addPlayer();
            }
        }, 0, 2000); // Add a player every 2 seconds
        toGameRoomButton.setDisable(true);
    }

    private void addPlayer() {
        if (players.size() < MAX_PLAYERS) {
            Player player = new Player(generatePlayerId(), generateRandomPlayerName());
            players.add(player);

            List<String> playerNames = new ArrayList<>();
            for (Player p : players) {
                playerNames.add(p.getName());
            }
            playerListView.getItems().setAll(playerNames);

            if (players.size() == MAX_PLAYERS) {
                toGameRoomButton.setDisable(false);
            }
        }
    }

    private String generateRandomPlayerName() {
        // Generate a random player name
        // Replace this with your own logic to generate player names
        return "Player" + (++playerCounter);
    }

    private String generatePlayerId() {
        String playerId = String.format("%03d", playerIdCounter);
        playerIdCounter++;
        return playerId;
    }

    @FXML
    private void setAllPlayersInGame() {//TEMPORARY
        for (Player player : players) {
            player.setInGame(true);
        }
    }
    @FXML
    private void toGameRoomButtonHandler(ActionEvent event) {
        setAllPlayersInGame();;//TEMPORARY
        if(GamePlaymaker.checkAllPlayersInGame(players)){
            switchToGameRoom();
        }
    }

    private void switchToGameRoom() {
        try {
            Stage stage = (Stage) playerListView.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("GameRoom.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Game Room");

            GameRoomController gameRoomController = loader.getController();
            //create game
//            gameRoomController.initialize(stage, GamePlaymaker.createGameRoom("TEST",players, true));

            Player currentPlayer = players.get(0); // Assuming the current player is at index 0
            currentPlayer.setInGame(true);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLobby(Lobby selectedLobby) {
        this.currentLobby = selectedLobby;
    }
}
