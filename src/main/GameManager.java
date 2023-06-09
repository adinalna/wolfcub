package wolfcub.main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import wolfcub.resources.controllers.GameMenuController;
import wolfcub.resources.controllers.InputName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameManager extends Application {
    private static final String GAME_ROOM_TITLE = "Game Room";
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        openNameEntryPage();
    }

    public void loadGameMenu(Player player, Stage currentStage) {
        try {
            FXMLLoader loader = FXMLFetcher.loadGameMenuFxml();
            Parent root = loader.load();
            GameMenuController controller = loader.getController();
            controller.setPlayer(player);

            Scene gameMenuScene = new Scene(root);
            Stage gameMenuStage = new Stage();
            gameMenuStage.setScene(gameMenuScene);
            gameMenuStage.setTitle(GAME_ROOM_TITLE);
            gameMenuStage.show();

            // Close the current stage (Name Input stage)
//            Stage currentStage = (Stage) nameField.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openNameEntryPage() {
        try {
            FXMLLoader loader = FXMLFetcher.loadInputNameFXML();
            Parent root = loader.load();
            InputName controller = loader.getController();
            controller.setGameManager(this);
            Stage currentStage = primaryStage; // Store the current stage
            controller.setCurrentStage(currentStage);

            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Name Input");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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