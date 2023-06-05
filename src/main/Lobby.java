package wolfcub.main;

import java.util.ArrayList;
import java.util.List;

public class Lobby {
    private String name;
    private List<Player> players;
    private GameRoom gameRoom;

    public Lobby(String name) {
        this.name = name;
        players = new ArrayList<>();
        gameRoom = null;
    }

    public String getName() {
        return name;
    }

    public void joinLobby(Player player) {
        players.add(player);
        System.out.println(player.getName() + " joined the lobby.");

        if (players.size() == 7) {
            createGameRoom();
        }
    }

    private void createGameRoom() {
        gameRoom = new GameRoom(name, players, true);
        System.out.println("Game room created with 7 players.");
        gameRoom.startGame();
    }
}