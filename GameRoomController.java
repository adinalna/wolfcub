/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package warewolfculb;

import javafx.application.Platform;

import java.util.List;
import java.util.Optional;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private Label timerLabel;

    @FXML
    private TextArea chatArea;

    @FXML
    private TextArea narrator;

    @FXML
    private TextField messageField;

    @FXML
    private Button sendButton;
    private int timeRemaining;

    @FXML
    public void initialize(Stage stage, GameRoom gameRoom) {
        this.gameRoom = gameRoom;
        this.players = gameRoom.getPlayers();
        // Pre-game (Wait for players)
        narrator.appendText("\nGame will Start in 10..");
        startTimer();
    }

    @FXML
    private void sendMessage() {
        String message = messageField.getText();
        chatArea.appendText("You: " + message + "\n");
        messageField.clear();
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public void setStage(Stage stage) {
    }

    private void preGameController() {
        narrator.appendText("\nNarrator: \nGame is ready\n");
        gameRoom.preGame();
        currentPlayer = players.get(0);

        // Delay the display of the alert using Platform.runLater()
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Game Information");
            alert.setHeaderText("All players are ready.");
            alert.setContentText("The game will start now!");
            gameRoom.preGame();
            alert.showAndWait().ifPresent(buttonType -> {
                if (buttonType == ButtonType.OK) {
                    // OK button was pressed, call your method here
                    roleReveal(currentPlayer);
                }
            });
        });

    }

    private void roleReveal(Player player) {
        Role role = player.getRole();
        String description = "You're a Werewolf\nKill all the villagers!";

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

    private void startTimer() {

        timeRemaining = 10;
        timerLabel.setText(formatTime(timeRemaining));

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    timeRemaining--;
                    timerLabel.setText(formatTime(timeRemaining));

                    if (timeRemaining <= 0) {
                        timerLabel.setText("00:00");
                        endRound();
                    }
                })
        );
        timeline.setCycleCount(10);
        timeline.play();
    }

    private void endRound() {
        narrator.setText("");
        preGameController();
    }

    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

    private void nextRound() {

        int dayCount = 1;
        while(villager > wolf){
            roundTimer();
            narrator.setText("");
            //Announce who has been killed

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
                narrator.appendText("You have to choosen Player 1 to be killed!");
            } else if (buttonType == player2Button) {
                // Player 2 was selected
                narrator.appendText("You have to choosen Player 2 to be killed!");
            } else if (buttonType == player3Button) {
                // Player 3 was selected
                narrator.appendText("You have to choosen Player 1 to be killed!");
            }
        });

        roundTimer();
    }
}
