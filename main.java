/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package warewolfculb;

/**
 *
 * @author HP
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));

        primaryStage.setTitle("Game Lobby");
        primaryStage.setScene(new Scene(root, 200, 150));
        primaryStage.show();
    }
    
//    private Lobby lobby; // Reference to the Lobby class
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Game Lobby");
//
//        lobby = new Lobby(); // Instantiate the Lobby class
//
//        //Join Room Button
//        Button joinRoomButton = new Button("Join Room");
//        joinRoomButton.setOnAction(e -> {
//            int userId = lobby.getUserId();
//            lobby.joinPublicRoom(userId);
//            System.out.println("Joined public room. User ID: " + userId);
//        });
//
//        //Create Room Button
//        Button createRoomButton = new Button("Create New Room");
//        createRoomButton.setOnAction(e -> {
//            int userId = lobby.getUserId();
//            String roomCode = lobby.createPrivateRoom(userId);
//            if (roomCode != null) {
//                System.out.println("Created private room. User ID: " + userId + ", Room Code: " + roomCode);
//            } else {
//                System.out.println("Failed to create private room. Maximum number of private rooms reached.");
//            }
//        });
//
//        //Enter Private Room Code
//        TextField codeTextField = new TextField();
//        codeTextField.setPromptText("Enter Room Code");
//
//        //Join With Code Button
//        Button joinWithCodeButton = new Button("Join with Code");
//        joinWithCodeButton.setOnAction(e -> {
//            String roomCode = codeTextField.getText();
//            /*
//            if(lobby.fullPrivateRoom==0){
//                int userId = lobby.getUserId();
//                System.out.println("Joined private room with code: " + roomCode + ". User ID: " + userId);
//            } else{
//                System.out.println("Cannot enter, this room is full");
//            }
//            */      
//            int userId = lobby.getUserId();
//            
//            // TODO: Implement logic to join private room with code
//            
//            System.out.println("Joined private room with code: " + roomCode + ". User ID: " + userId);
//        });
//
//        VBox vbox = new VBox(10);
//        vbox.setPadding(new Insets(10));
//        vbox.getChildren().addAll(joinRoomButton, createRoomButton, codeTextField, joinWithCodeButton);
//
//        Scene scene = new Scene(vbox, 200, 200);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
}