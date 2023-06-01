//package warewolfculb;/*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.*;
//import javafx.stage.Stage;
//import javafx.beans.property.StringProperty;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.geometry.Insets;
//import java.util.ArrayList;
//import java.util.Random;
///**
// *
// * @author HP
// */
//public class ChatPlatform extends Application{
//    @Override
//    public void start(Stage primaryStage) {
//        Random random = new Random();
//
//        // Create the general chat and werewolf chat objects
//        Chat generalChat = new Chat(1, 0);
//        Chat werewolfChat = new Chat(2, 1);
//
//        // Example
//        ArrayList<Player> players = new ArrayList<Player>();
//        char name;
//        int i;
//        for (i = 1, name = 'A'; i < 8; i++, name++) {
//            players.add(new Player("p" + i, "" + name) {});
//        }
//
//        GameRoom g1 = new GameRoom();
//        g1.assignRole(players);
//
//        for (int k = 0; k < players.size(); k++) {
//            generalChat.addPlayer(players.get(k));
//            if (players.get(k).getRole().getRoleName() == "Werewolf") {
//                werewolfChat.addPlayer(players.get(k));
//            }
//        }
//
//        for (Player player: werewolfChat.getPlayers()) {
//            System.out.println(player.getName());
//
//        }
//
////        ------------------ TEST PLAYER ---------------------
//
//
//        // Create the UI for the chat platform
//        TabPane tabPane = new TabPane();
//        Tab generalTab = new Tab("General");
//        Tab werewolfTab = new Tab("Werewolf");
//
//        // Disable closing of tabs
//        generalTab.setClosable(false);
//        werewolfTab.setClosable(false);
//
//        VBox generalVBox = new VBox();
//        TextArea generalTextArea = new TextArea();
//        generalTextArea.setEditable(false);
//        generalVBox.getChildren().add(generalTextArea);
//        generalTab.setContent(generalVBox);
//
//        VBox werewolfVBox = new VBox();
//        TextArea werewolfTextArea = new TextArea();
//        werewolfTextArea.setEditable(false);
//        werewolfVBox.getChildren().add(werewolfTextArea);
//        werewolfTab.setContent(werewolfVBox);
//
//        tabPane.getTabs().addAll(generalTab, werewolfTab);
//
//        // Set up the UI event listeners for the chat platform
//        StringProperty generalMessageProperty = new SimpleStringProperty(generalChat.getMessages().toString().replaceAll("\\[", "").replaceAll("]", ""));
//        generalTextArea.textProperty().bindBidirectional(generalMessageProperty);
//
//        StringProperty werewolfMessageProperty = new SimpleStringProperty(werewolfChat.getMessages().toString().replaceAll("\\[", "").replaceAll("]", ""));
//        werewolfTextArea.textProperty().bindBidirectional(werewolfMessageProperty);
//
//        // Create a text field and send button for users to enter and send messages
//        // General Chat
//        HBox generalMessageHBox = new HBox();
//        TextField generalMessageTextField = new TextField();
//        Button generalSendButton = new Button("Send");
//        generalMessageHBox.getChildren().addAll(generalMessageTextField, generalSendButton);
//        generalMessageHBox.setSpacing(10);
//        generalMessageHBox.setPadding(new Insets(10));
//
//        // Set up the event listener for the send button
//        generalSendButton.setOnAction(event -> {
//            String generalMessage = generalMessageTextField.getText();
//            if (tabPane.getSelectionModel().getSelectedItem() == generalTab && generalMessage != "") {
//                generalChat.addMessage(players.get(random.nextInt(players.size())).getName() + ": " + generalMessage + "\n");
//                generalMessageProperty.set(generalChat.getMessages().toString().replaceAll("\\[", "").replaceAll("]", "").replaceAll(", ", ""));
//            }
//            generalMessageTextField.clear();
//        });
//
//        // Add the text field and send button to the general chat UI
//        generalVBox.getChildren().add(generalMessageHBox);
//
//        // Werewolf Chat
//        HBox werewolfMessageHBox = new HBox();
//        TextField werewolfMessageTextField = new TextField();
//        Button werewolfSendButton = new Button("Send");
//        werewolfMessageHBox.getChildren().addAll(werewolfMessageTextField, werewolfSendButton);
//        werewolfMessageHBox.setSpacing(10);
//        werewolfMessageHBox.setPadding(new Insets(10));
//
//        // Set up the event listener for the send button
//        werewolfSendButton.setOnAction(event -> {
//            String werewolfMessage = werewolfMessageTextField.getText();
//            if (tabPane.getSelectionModel().getSelectedItem() == werewolfTab && werewolfMessage != "") {
//                werewolfChat.addMessage(werewolfChat.getPlayers().get(random.nextInt(werewolfChat.getPlayers().size())).getName() + ": " + werewolfMessage + "\n");
//                werewolfMessageProperty.set(werewolfChat.getMessages().toString().replaceAll("\\[", "").replaceAll("]", "").replaceAll(", ", ""));
//            }
//            werewolfMessageTextField.clear();
//        });
//
//        // Add the text field and send button to the werewolf chat UI
//        werewolfVBox.getChildren().add(werewolfMessageHBox);
//
//        // Show the chat platform UI
//        Scene scene = new Scene(tabPane, 400, 300);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Chat Platform");
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
