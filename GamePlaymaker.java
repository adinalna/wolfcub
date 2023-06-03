package warewolfculb;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class GamePlaymaker {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int GAME_ID_LENGTH = 5;
    private static List<GameRoom> gameRoomList = new ArrayList<>();

    public static String generateRandomRoomCode() {
        Random random = new Random();
        StringBuilder gameIdBuilder = new StringBuilder(GAME_ID_LENGTH);
        for (int i = 0; i < GAME_ID_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            gameIdBuilder.append(CHARACTERS.charAt(randomIndex));
        }
        return gameIdBuilder.toString();
    }

    public static void removeGame(GameRoom gameRoom) {
        //DATABASE: delete room
        gameRoomList.remove(gameRoom); //TEMP
    }

    public static boolean checkAllPlayersInGame(List<Player> players) {
        boolean allPlayersInGame = true;

        for (Player player : players) {
            if (!player.isInGame()) {
                allPlayersInGame = false;
                break;
            }
        }

        if (allPlayersInGame) {
            return true;
        }
        return false;
    }


}