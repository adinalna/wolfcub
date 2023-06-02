/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package warewolfculb;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Farisah
 */
public class MenuController {     // implements Initializable

    private Lobby lobby; // Reference to the Lobby class
    private Player player;
    private String sessionId; // Session ID for the current player

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

    public void initialize() {
        lobby = new Lobby(); // Instantiate the Lobby class
        int userId = lobby.generateUserId();
        sessionId = generateSessionId(userId); // Generate session ID for the player
        player = new Player(String.valueOf(userId), "Player" + userId);
        USERNAME.setText("Welcome Player " + player.getPlayerID());
        System.out.println("Welcome! Your User ID: " + userId + ", Session ID: " + sessionId);
        String paragraph = """
                            Steps:
                            1. Once 7 players have entered the game room, the room will be start.
                            2. Count down starts from 3 and then, the game begins.
                            3. Roles are assigned to each of the players randomly. Villagers can only see their own roles, while werewolves can see their roles as well as the other werewolves.
                            4. When the night phase begins, the werewolves will secretly discuss and select a player to be eliminated.
                            5. Other villagers with abilities such as doctor and seer can also use their powers during the night phase. However, the seer can only use her ability on the second night.
                            6. The day phase begins, all players will discuss and try to identify werewolves among them within 2 minutes.
                            7. Players with the most vote will be eliminated. Their role will be revealed to determine whether they were a villager or a werewolf.
                            
                            Roles:
                            i. Villager: Has no special ability.
                            ii. Seer: Can identify the role of one of the players (villagers or werewolves.)
                            iii. Doctor: Can protect one of the players at night from being eliminated.
                            iv. Werewolf: Is able to kill innocent villagers at night.""";
        RULES.setText(paragraph);
    }

    @FXML
    private void joinRoomButtonClicked(ActionEvent event) {
        switchToLobby();
    }


    private void showJoinRoomErrorAlert(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Join Room Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    @FXML
    private void createRoomButtonClicked(ActionEvent event) {
        //can get the user id from the database
        String userId = player.getPlayerID();
        String roomCode = lobby.createRoom(userId);
        if (roomCode != null) {
            System.out.println("Created private room. User ID: " + userId + ", Room Code: " + roomCode);
            showRoomCodeAlert(roomCode);
        } else {
            System.out.println("Failed to create private room. Maximum number of private rooms reached.");
        }
    }

    @FXML
    private void joinWithCodeButtonClicked(ActionEvent event) {
        String roomCode = roomCodeField.getText();
        int userId = lobby.getUserId();
        // TODO: Implement logic to join private room with code
        if (!roomCode.isEmpty()) {
            System.out.println("Joined private room with code: " + roomCode + ". User ID: " + userId);
        } else {
            System.out.println("Please enter a valid room code.");
        }
    }

    private String generateSessionId(int userId) {
        // Generate session ID based on user ID or any other logic you prefer
        return "SESSION_" + userId;
    }

    private void showRoomCodeAlert(String roomCode) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Room Code");
        alert.setHeaderText("Your room code is:");
        alert.setContentText(roomCode);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true); // Ensure the alert dialog is always on top

        alert.showAndWait();
    }
    /**
     * Initializes the controller class.
     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }

    private void switchToLobby() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Lobby.fxml"));
            Parent root = loader.load();
            LobbyController lobbyController = loader.getController();

            Stage stage = (Stage) joinRoomButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Lobby");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showJoinRoomErrorAlert("Failed to load Lobby.fxml");
        }
    }

}
