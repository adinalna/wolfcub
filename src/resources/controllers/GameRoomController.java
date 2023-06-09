package wolfcub.resources.controllers;

import javafx.application.Platform;
import java.util.List;
import java.util.Optional;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import wolfcub.main.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class GameRoomController {
    private Player currentPlayer;
    private GameRoom gameRoom;
    private List<Player> players;
    private int clickedButtonCount = 0; // counter to keep track of clicked buttons
    private boolean gameEnded = false; // flag to track whether the game has ended
    //private static final int GAME_DURATION = 10; // Duration in seconds

    private int wolf = 2; //TEMP
    private int villager = 3; //TEMP

    @FXML
    private VBox chatRoom;

    @FXML
    private Label timerLabel;

    @FXML
    private TextArea chatArea;

    @FXML
    private TextArea narrator;

    @FXML
    private TextField messageField;

    @FXML
    private Button sendButton;

    @FXML
    private TabPane chatTabPane;

    @FXML
    private TextArea chatArea1;

    @FXML
    private TextField messageField1;

    @FXML
    private TextArea chatArea2;

    @FXML
    private TextField messageField2;
    private int timeRemaining;

    @FXML
    public void initialize() {
        this.narrator.setText("\nGame will Start in 10..");
    }

    @FXML
    private void toggleChatSection() {
        chatTabPane.setVisible(!chatTabPane.isVisible());
    }

    @FXML
    private void sendMessage1() {
        String message = messageField1.getText();
        chatArea1.appendText("You: " + message + "\n");
        messageField1.clear();
    }

    @FXML
    private void sendMessage2() {
        String message = messageField2.getText();
        chatArea2.appendText("You: " + message + "\n");
        messageField2.clear();
    }

    @FXML
    private void sendMessage() {
        String message = messageField.getText();
        chatArea.appendText("You: " + message + "\n");
        messageField.clear();
    }

    public void setGameRoom(GameRoom gameRoom) {
        this.gameRoom = gameRoom;
        this.players = gameRoom.getPlayers();
        currentPlayer = this.gameRoom.getCurrentPlayer();
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public void setStage(Stage stage) {
    }

    public void preGameController() {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Game Information");
            alert.setHeaderText("All players are ready.");
            alert.setContentText("The game will start now!");
            alert.showAndWait().ifPresent(buttonType -> {
                if (buttonType == ButtonType.OK) {
                    gameRoom.assignPlayerRoles();
                    roleReveal(currentPlayer);
                }
            });
        });
    }

    private void roleReveal(Player player) {
        Role role = player.getRole();
        String description = TextRepository.getPreGameText(role.getRoleName());

        // Delay the display of the alert using Platform.runLater()
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Role Information");
            alert.setHeaderText(role.getRoleName());
            alert.setContentText(description);
            alert.showAndWait().ifPresent(buttonType -> {
                if (buttonType == ButtonType.OK) {
                    // OK button was pressed, call your method here
                    nightPhaseController();
                }
            });
        });
    }

    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

    private void nextRound() {
        int dayCount = 1;
        while (villager > wolf) {
            roundTimer();
            narrator.setText("");
            // Announce who has been killed
            narrator.appendText("\nNarrator: \nA player has been killed!\n");
            villager--;
            dayCount++;
        }
    }

    private void roundTimer() {
        timeRemaining = 30;
        timerLabel.setText(formatTime(timeRemaining));

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    timeRemaining--;
                    timerLabel.setText(formatTime(timeRemaining));

                    if (timeRemaining <= 0) {
                        timerLabel.setText("00:00");
                        nextRound();
                    }
                })
        );
        timeline.setCycleCount(30);
        timeline.play();
    }

    private void nightPhaseController() {
        String action = "Kill";
        String actionDesc = "Choose who you want to kill!";

        // Create buttons for each player
        ButtonType player1Button = new ButtonType("Player 1");
        ButtonType player2Button = new ButtonType("Player 2");
        ButtonType player3Button = new ButtonType("Player 3");

        // Create the alert with buttons
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(action);
        alert.setHeaderText("It's night time!");
        alert.setContentText(actionDesc);

        // Set the buttons
        alert.getButtonTypes().setAll(player1Button, player2Button, player3Button);

        // Show the alert and wait for button selection
        Optional<ButtonType> result = alert.showAndWait();
        narrator.setText("");
        narrator.appendText("\nSummary of night time\n");
        // Check which button was selected
        result.ifPresent(buttonType -> {
            if (buttonType == player1Button) {
                // Player 1 was selected
                narrator.appendText("You have chosen Player 1 to be killed!");
            } else if (buttonType == player2Button) {
                // Player 2 was selected
                narrator.appendText("You have chosen Player 2 to be killed!");
            } else if (buttonType == player3Button) {
                // Player 3 was selected
                narrator.appendText("You have chosen Player 3 to be killed!");
            }
        });

        roundTimer();
    }
}
