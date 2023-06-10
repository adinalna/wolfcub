package wolfcub.resources.controllers;

import javafx.application.Platform;

import java.util.ArrayList;
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
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Game Information");
            dialog.setHeaderText("All players are ready.");
            dialog.setContentText("The game will start now!");

            ButtonType startGameButton = new ButtonType("Start Game", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(startGameButton);

            dialog.showAndWait().ifPresent(buttonType -> {
                if (buttonType == startGameButton) {
                    roleReveal(currentPlayer);
                }
            });
        });
    }

    private void roleReveal(Player player) {
        Role role = player.getRole();
        String description = TextRepository.getPreGameText(role.getRoleName());

        Platform.runLater(() -> {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Role Information");
            dialog.setHeaderText(role.getRoleName());
            dialog.setContentText(description);

            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(okButton);

            dialog.showAndWait().ifPresent(buttonType -> {
                if (buttonType == okButton) {
                    gameRoom.nightPhase();
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

    public void nightPhaseController(List<Player> alivePlayers) {
        Role playerRole = currentPlayer.getRole();
        String action = "Role Action";
        String actionDesc = TextRepository.getNightPhaseText(playerRole.getRoleName());
        List<Player> targetPlayers = new ArrayList<>();

        Platform.runLater(() -> {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle(action);
            dialog.setHeaderText("It's night time!");
            dialog.setContentText(actionDesc);

            if((playerRole.getRoleName().equals("Villager"))){
                ButtonType startGameButton = new ButtonType("Next", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().add(startGameButton);

                Optional<ButtonType> result = dialog.showAndWait();
                if (result.isPresent() && result.get() == startGameButton) {
                    Platform.runLater(() -> {
                    });
                }

            } else if((playerRole.getRoleName().equals("Werewolf"))){
                for (Player p : alivePlayers) {
                    if (!p.equals(currentPlayer) || p.getRole().getRoleName().equals("Werewolf")) {
                        ButtonType playerButton = new ButtonType(p.getName());
                        dialog.getDialogPane().getButtonTypes().add(playerButton);
                        targetPlayers.add(p);
                    }
                }

                dialog.showAndWait().ifPresent(buttonType -> {
                    for (int i = 0; i < targetPlayers.size(); i++) {
                        if (buttonType.getText().equals(targetPlayers.get(i).getName())) {
                            Player selectedPlayer = targetPlayers.get(i);
                            narrator.appendText("You have chosen " + selectedPlayer.getName() + " to be killed!");
                            break;
                        }
                    }
                });
            }else{
                for (Player p : alivePlayers) {
                    if (!p.equals(currentPlayer)) {
                        ButtonType playerButton = new ButtonType(p.getName());
                        dialog.getDialogPane().getButtonTypes().add(playerButton);
                        targetPlayers.add(p);
                    }
                }

                dialog.showAndWait().ifPresent(buttonType -> {
                    for (int i = 0; i < targetPlayers.size(); i++) {
                        if (buttonType.getText().equals(targetPlayers.get(i).getName())) {
                            Player selectedPlayer = targetPlayers.get(i);
                            narrator.appendText("You have chosen " + selectedPlayer.getName() + " to be protected/role reveal");
                            break;
                        }
                    }
                });
            }
        });
    }

    public void dayPhaseController(List<Player> alivePlayers) {

    }
}
