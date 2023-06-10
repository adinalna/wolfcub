package wolfcub.resources.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import wolfcub.main.FXMLFetcher;
import wolfcub.main.Lobby;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameRulesController implements Initializable {
    @FXML
    private VBox rulesContainer;

    @FXML
    private ListView<Lobby> lobbyListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Add rules to the rulesContainer
        Label rule1 = new Label("1. Once 7 players have entered the game room, the room will start.\n");
        Label rule2 = new Label("2. Count down starts from 3 and then, the game begins.\n");
        Label rule3 = new Label("3. Roles are assigned to each of the players randomly. Villagers can only see their own roles, while werewolves can see their roles as well as the other werewolves.\n");
        Label rule4 = new Label("4. When the night phase begins, the werewolves will secretly discuss and select a player to be eliminated.\n");
        Label rule5 = new Label("5. Other villagers with abilities such as doctor and seer can also use their powers during the night phase. However, the seer can only use her ability on the second night.\n");
        Label rule6 = new Label("6. The day phase begins, all players will discuss and try to identify werewolves among them within 2 minutes.\n");
        Label rule7 = new Label("7. Players with the most votes will be eliminated.\n");
        Label rule8 = new Label("    Their role will be revealed to determine whether they were a villager or a werewolf\n");
        Label rule9 = new Label("    \n");
        Label rule10 = new Label("Roles:\n");
        Label rule11 = new Label("i. Villager: Has no special ability.\n");
        Label rule12 = new Label("ii. Seer: Can identify the role of one of the players (villagers or werewolves.)\n");
        Label rule13 = new Label("iii. Doctor: Can protect one of the players at night from being eliminated.\n");
        Label rule14 = new Label("iv. Werewolf: Is able to kill innocent villagers at night.\n");

        rulesContainer.getChildren().addAll(rule1,rule2,rule3,rule4,rule5,rule6,rule7,rule8,rule9,rule10,rule11,rule12,rule13,rule14);
    }

    @FXML
    private void backMenuClicked(ActionEvent event) {
        try {
            // Load the gameMenu.fxml file
            FXMLLoader loader = FXMLFetcher.loadGameMenuFxml();
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) rulesContainer.getScene().getWindow();

            // Set the root as the content of the current stage
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
