package wolfcub.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Player {

    private String playerID;
    private String name;
    private boolean inLobby;
    private String currentLobbyID;
    private boolean inGame;
    private String currentGameID;
    private Role role;
    private boolean isEliminated;
    private boolean isProtected;
    private List<Player> votes;

    public Player(String playerID, String name) {

        this.playerID = playerID;
        this.name = name;
        this.isEliminated = false;
        this.isProtected = false;
        this.inLobby= false;
        this.inGame = false;
        this.votes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInLobby(boolean inLobby) {
        this.inLobby = inLobby;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public String getCurrentLobbyID() {
        return currentLobbyID;
    }

    public boolean setInLobby(String currentLobbyID) {
        if(!inLobby) {
            this.currentLobbyID = currentLobbyID;
            return true;
        }
        return false;
    }

    public boolean notInLobby() {
        if(inLobby) {
            this.currentLobbyID = null;
            this.inLobby = false;
            return true;
        }
        return false;
    }

    public String getCurrentGameID() {
        return currentGameID;
    }

     public boolean setInGame(String currentGameID) {
        if (!inGame) {
            inGame = true;
            this.currentGameID = currentGameID;
            setEliminated(false);
            setProtected(false);
        }
        return false;
    }

    public boolean notInGame() {
        if(inGame) {
            this.currentGameID = null;
            this.inGame = false;
            resetVotes();
            return true;
        }
        return false;
    }

    protected void setPlayerRole(Role role) {
        this.role = role;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public boolean isAlive() {
        return !isEliminated;
    }

    public boolean isEliminated() {
        return isEliminated;
    }

    public void setEliminated(boolean isEliminated) {
        this.isEliminated = isEliminated;
    }

    public boolean isProtected() {
        return isProtected;
    }

    public void setProtected(boolean isProtected) {
        this.isProtected = isProtected;
    }

    public void resetVotes() {
        votes.clear();
    }

    public Player getVote(int round) {
        return votes.get(round);
    }

    public void castVote(int round, Player player) {
        votes.add(round, player);
    }
}