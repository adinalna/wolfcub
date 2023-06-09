package wolfcub.resources.controllers;

import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import wolfcub.main.FXMLFetcher;
import wolfcub.main.GameManager;
import wolfcub.main.Lobby;
import wolfcub.main.Player;

public class GameMenuController {     // implements Initializable

    private Lobby lobby; // Reference to the Lobby class
    private Player player;
    private String sessionId; // Session ID for the current player
    private GameManager gameManager;

    @FXML
    private TextField roomCodeField;
    @FXML
    private TextArea RULES;

    @FXML
    private Text USERNAME;
    @FXML
    private Button joinRoomButton;
    @FXML
    private StackPane mainStackPane;
    @FXML
    private Button joinGameButton;

    @FXML
    private Button rulesButton;

    public void initialize() {
//        String paragraph = """
//                Steps:
//                1. Once 7 players have entered the game room, the room will be start.
//                2. Count down starts from 3 and then, the game begins.
//                3. Roles are assigned to each of the players randomly. Villagers can only see their own roles, while werewolves can see their roles as well as the other werewolves.
//                4. When the night phase begins, the werewolves will secretly discuss and select a player to be eliminated.
//                5. Other villagers with abilities such as doctor and seer can also use their powers during the night phase. However, the seer can only use her ability on the second night.
//                6. The day phase begins, all players will discuss and try to identify werewolves among them within 2 minutes.
//                7. Players with the most vote will be eliminated. Their role will be revealed to determine whether they were a villager or a werewolf.
//
//                Roles:
//                i. Villager: Has no special ability.
//                ii. Seer: Can identify the role of one of the players (villagers or werewolves.)
//                iii. Doctor: Can protect one of the players at night from being eliminated.
//                iv. Werewolf: Is able to kill innocent villagers at night."""; */
    }

    public void setUSERNAME(Text USERNAME) {
        this.USERNAME = USERNAME;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @FXML
    private void joinGameButtonClicked(ActionEvent event) {
        try {
            FXMLLoader loader = FXMLFetcher.loadLobbyListFxml();
            Parent root = loader.load();

            LobbyListController lobbyListController = loader.getController();
            lobbyListController.setLobbyList((ObservableList<Lobby>) GameManager.getAvailableLobbies());

            Stage stage = (Stage) joinGameButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Lobby List");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void createGameButtonClicked(ActionEvent event) {
        // Handle create game button click
        System.out.println("Create Game button clicked");
        // GameManager.createLobby(name)
    }

    @FXML
    private void rulesButtonClicked(ActionEvent event) {
        // Handle rules button click
        System.out.println("Rules button clicked");
        try {
            // Load the rules.fxml file
            FXMLLoader loader = FXMLFetcher.loadGameRulesFxml();
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) rulesButton.getScene().getWindow();

            // Set the root as the content of the current stage
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void profileButtonClicked(ActionEvent event) {
        // Handle profile button click
        System.out.println("Profile button clicked");
        // Add your code here
    }

    @FXML
    private void exitButtonClicked(ActionEvent event) {
        // Handle exit button click
        System.out.println("Exit button clicked");
        // Add your code here
    }

    private String generateSessionId(int userId) {
        // Generate session ID based on user ID or any other logic you prefer
        return "SESSION_" + userId;
    }
}


