package wolfcub.main;

import java.util.ArrayList;
import java.util.List;

public class Lobby {
    private String name;
    private List<Player> players;
    private GameRoom gameRoom;
    private GameManager gameManager;

    public Lobby(String name) {
        this.name = name;
        players = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void joinLobby(Player player) {
        players.add(player);
        System.out.println(player.getName() + " joined the lobby.");

        if (players.size() == 7) {
            GameManager.createGameRoom(name, players);
        }
    }
}