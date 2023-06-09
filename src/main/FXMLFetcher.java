package wolfcub.main;

import javafx.fxml.FXMLLoader;
import java.io.IOException;
public class FXMLFetcher {
    private static final String GAME_MENU_FXML = "../resources/views/game-menu.fxml";
    private static final String LOBBY_LIST_FXML = "../resources/views/lobby-list.fxml";
    private static final String LOBBY_FXML = "../resources/views/lobby.fxml";
    private static final String GAME_RULES_FXML = "../resources/views/game-rules.fxml";
    private static final String GAME_ROOM_FXML = "../resources/views/game-room.fxml";
    private static final String INPUT_NAME_FXML = "../resources/views/input-name.fxml";

    public static FXMLLoader loadGameMenuFxml() throws IOException {
        return loadFXML(GAME_MENU_FXML);
    }

    public static FXMLLoader loadLobbyListFxml() throws IOException {
        return loadFXML(LOBBY_LIST_FXML);
    }

    public static FXMLLoader loadLobbyFxml() throws IOException {
        return loadFXML(LOBBY_FXML);
    }

    public static FXMLLoader loadGameRulesFxml() throws IOException {
        return loadFXML(GAME_RULES_FXML);
    }

    public static FXMLLoader loadGameRoomFxml() throws IOException {
        return loadFXML(GAME_ROOM_FXML);
    }

    private static FXMLLoader loadFXML(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(FXMLFetcher.class.getResource(fxmlPath));
        return loader;
    }

    public static FXMLLoader loadInputNameFXML() throws IOException {
        return loadFXML(INPUT_NAME_FXML);
    }
}
