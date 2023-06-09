package wolfcub.resources.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import wolfcub.main.GameManager;
import wolfcub.main.Player;

public class InputName {
    @FXML
    private TextField nameField;
    @FXML
    private Button addButton;
    private GameManager gameManager;
    private Stage currentStage;

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
    @FXML
    private void submitName() {
        String name = nameField.getText();

        if (name.isEmpty()) {
            // Handle empty name error
            return;
        }

        // Create a new Player instance and store the name into database
        Player player = new Player("001", name);

        // Load the game menu after the name is submitted
        gameManager.loadGameMenu(player, currentStage);
    }

    @FXML
    private void addButtonClicked() {
        submitName();
    }
}