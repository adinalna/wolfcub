package wolfcub.main;

import java.util.*;

public class GamePlaymaker {

    private int currentRound;
    private List<Role> roles;
    private List<Player> players;
    private List<Player> eliminatedPlayers = new ArrayList<>();
    private List<Player> alivePlayers;
    private Map<Werewolf, List<Player>> werewolfVotes = new HashMap<>();

    public GamePlaymaker(List<Player> players) {
        this.players = players;
        alivePlayers = players;
        currentRound = 0;
        setRoleSettings();
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void nextRound() {
        this.currentRound++;
    }

    private List<Role> setRoleSettings() {
        roles = new ArrayList<>();
        roles.add(new Werewolf());
        roles.add(new Werewolf());
        roles.add(new Seer());
        roles.add(new Doctor());
        roles.add(Villager.createVillagers().get(0));
        roles.add(Villager.createVillagers().get(1));
        roles.add(Villager.createVillagers().get(2));
        for (Role role : roles) {
            System.out.println(role.getRoleName());
        }
        return roles;
    }


    public List<Player> getAlivePlayers() {
        return alivePlayers;
    }

    public boolean nonWerewolvesWin() {
        for (Player player : alivePlayers) {
            if (player.getRole() instanceof Werewolf && player.isAlive()) {
                return false;
            }
        }
        return true;
    }

    public boolean werewolfWins() {
        int werewolfCount = 0;
        int nonWerewolfCount = 0;

        for (Player player : alivePlayers) {
            if (player.isAlive()) {
                if (player.getRole() instanceof Werewolf) {
                    werewolfCount++;
                } else {
                    nonWerewolfCount++;
                }
            }
        }

        if (werewolfCount == 2 && werewolfCount > nonWerewolfCount) {
            return true;
        }

        if (werewolfCount == 1 && werewolfCount == nonWerewolfCount) {
            return true;
        }

        return false;
    }

    public void eliminatePlayer(Player player) {
        eliminatedPlayers.add(player);
        alivePlayers.remove(player);
    }

    public int getNumPlayersEliminated() {
        return eliminatedPlayers.size();
    }

    public void castVote(int round, Player voter, Player votedPlayer) {
        voter.castVote(round, votedPlayer);
    }

    public Map<Werewolf, List<Player>> getWerewolfVotes() {
        return werewolfVotes;
    }

    public void assignPlayerRoles() {
        Random rand = new Random();
        int numRoles = roles.size();

        for (int i = 0; i < players.size(); i++) {
            int roleIndex = rand.nextInt(numRoles);
            players.get(i).setPlayerRole(roles.get(roleIndex));
            roles.remove(roleIndex);
            numRoles--;
        }
    }

    public List<Player> getPlayersByRole(Role role) {
        List<Player> playersByRole = new ArrayList<>();

        for (Player player : players) {
            if (player.getRole() == role) {
                playersByRole.add(player);
            }
        }

        return playersByRole;
    }

    public Player getMostVotedPlayer(int round, List<Player> alivePlayers) {
        Map<Player, Integer> voteCounts = new HashMap<>();

        // Iterate through the alivePlayers list and count the votes for each player in the given round
        for (Player voter : alivePlayers) {
            Player votedPlayer = voter.getVote(round);
            voteCounts.put(votedPlayer, voteCounts.getOrDefault(votedPlayer, 0) + 1);
        }

        // Find the player with the most votes
        Player mostVotedPlayer = null;
        int maxVotes = 0;

        for (Map.Entry<Player, Integer> entry : voteCounts.entrySet()) {
            Player player = entry.getKey();
            int votes = entry.getValue();

            if (votes > maxVotes) {
                mostVotedPlayer = player;
                maxVotes = votes;
            }
        }

        return mostVotedPlayer;
    }

    public boolean allPlayersVoted(int round) {
        return true;
    }
}
