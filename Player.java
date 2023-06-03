package warewolfculb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {

    private String playerID;
    private String name;
    private boolean inGame;
    private Role role;
    private boolean eliminated;
    private boolean protecteD;
    private List<Player> votes;

    // Constructor for player class
    public Player(String playerID, String name) {

        this.playerID = playerID;
        this.name = name;
        this.eliminated = false;
        this.protecteD = false;
        this.inGame = false;
        this.votes = new ArrayList<>();

    }

    protected void setPlayerRole(Role role) {
        this.role = role;
    }

    // Assign role to players randomly
//    public void assignRole(List<Role> roles) {
//
//        Random random = new Random();
//        int index = random.nextInt(roles.size());
//        
//        // Set the role to the player 
//        this.role = roles.get(index);
//        
//        // Need to remove roles from list 
//        roles.remove(index); 
//    }
//    
//    public void assignRole() {
//
//        // Create a list of available type of villagers to be assign at random
//        List<Role> roles = new ArrayList<>();
//        roles.add(new Werewolf());
//        roles.add(new Werewolf());
//        roles.add(new Seer());
//        roles.add(new Doctor());
//        roles.add(Villager.createVillagers().get(0));
//        roles.add(Villager.createVillagers().get(1));
//        roles.add(Villager.createVillagers().get(2));
//        
//        Random random = new Random();
//        int index = random.nextInt(roles.size());
//        
//        // Set the role to the player 
//        this.role = roles.get(index);
//        
//    }

    // Allow players to vote for a specific player to eliminate


    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void vote(Player player) {

        this.votes.add(player);

    }

    // Get the name of player with the most vote
    public Player getMostVotedPlayer() {

        Map<Player, Integer> voteCounts = new HashMap<>();

        // Count the number of votes for each player
        // Interate through vote list to count the number of vote for each player 
        for (Player player : votes) {
            int voteSum = voteCounts.getOrDefault(player, 0);
            voteCounts.put(player, voteSum + 1);
        }

        // Find the player with the most votes
        Player mostVotedPlayer = null;
        int maxVotes = 0;
        for (Map.Entry<Player, Integer> entry : voteCounts.entrySet()) {
            if (entry.getValue() > maxVotes) {
                mostVotedPlayer = entry.getKey();
                maxVotes = entry.getValue();
            }
        }

        mostVotedPlayer.setEliminated(true);

        // Eliminate the player that has the most vote 
        return mostVotedPlayer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // PlayerID getter 
    public String getPlayerID() {
        return "001";
    }

    public void setRole(Role role) {
        this.role = role;
    }


    // Get the role of the players 
    public Role getRole() {
        return role;
    }

    public boolean isEliminated() {
        return eliminated;
    }

    public void setEliminated(boolean eliminated) {
        this.eliminated = eliminated;
    }

    public boolean isProtected() {
        return protecteD;
    }

    public void setProtected(boolean protecteD) {
        this.protecteD = protecteD;
    }

}