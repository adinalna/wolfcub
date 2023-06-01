package warewolfculb;
import java.util.*;

public class GameRoom {
    private String roomID;
    private boolean isFull;
    private int numDay;
    private Timer time;
    private List<Player> players;
    private List<Role> roles;
    private Map<Player,Integer> votes = new HashMap<>();
    private Timer discussionTimer;
    private int day = 0;


    public GameRoom(String roomID, Timer time, List<Player>players, List<Role>roles) {
        this.roomID = roomID;
        this.isFull = false;
        this.numDay = 0;
        this.time = time;
        this.players = players;
        this.roles = roles;
    }

    public String getRoomID() {
        return roomID;
    }

    public boolean isFull() {
        return isFull;
    }

    public int getNumDay() {
        return numDay;
    }

    public Timer getTime() {
        return time;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public void startGame() {
        System.out.println("Game started!");
        this.time.start();
        numDay++;
        assignPlayerRoles();
    }

    public void assignPlayerRoles() {
        Random rand = new Random();
        for(int i=0; i<players.size(); i++){
            int role_index = rand.nextInt(roles.size());
            players.get(i).setPlayerRole(roles.get(role_index));
            roles.remove(role_index);
        }
    }

    public void endGame() {
        // Logic to end the game
        // This method will be responsible for resetting the game state, clearing player data, etc.
    }

    public void dayPhase() {
        System.out.println("Day " + day);
        System.out.println("Day phase begins...");

        // Start the discussion timer for 30 seconds
        discussionTimer = new Timer(30);
        discussionTimer.start();
        System.out.println("Start Discussion!");
        // Wait for the discussion timer to complete
        while (!discussionTimer.isExpired()) {
            long remainingTime = discussionTimer.getRemainingTime() / 1000; // Convert remaining time to seconds
            long countdownValue = remainingTime + 1;

            System.out.println(countdownValue);

            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Discussion time is over!");
        //voting phase
        while (true) {//
            try {
                Thread.sleep(1000); // Sleep for 1 second before checking the condition again
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void nightPhase() {
        // Logic for the night phase of the game
        // This method will handle the actions and events that occur during the night phase of the game
    }

    public boolean isLocked() {
        if (this.players.size() == 7)
            return true;
        return false;
    }

    public String roomStatus() {
        // Return the status of the game room
        // This method can be used to retrieve information about the current status of the game room
        // You can return a string containing relevant details such as room ID, number of players, game phase, etc.
        // Customize the implementation based on your specific needs
        return "";
    }
}
