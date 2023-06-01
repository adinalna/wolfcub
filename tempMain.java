package warewolfculb;

import java.util.List;
import java.util.*;
public class tempMain {
    public static void main(String[] args) {

        Player player1 = new Player("001","one");
        Player player2 = new Player("002","two");
        Player player3 = new Player("003","three");
        Player player4 = new Player("004","four");
        Player player5 = new Player("005","five");
        Player player6 = new Player("006","six");
        Player player7 = new Player("007","seven");

        List<Player> players = new ArrayList<Player>(
                Arrays.asList(player1, player2, player3, player4, player5, player6, player7));;

        Timer timer = new Timer(30);

        List<Role> roles = new ArrayList<>();
        roles.add(new Werewolf());
        roles.add(new Werewolf());
        roles.add(new Seer());
        roles.add(new Doctor());
        roles.add(Villager.createVillagers().get(0));
        roles.add(Villager.createVillagers().get(1));
        roles.add(Villager.createVillagers().get(2));

        GameRoom gameRoom = new GameRoom("1", timer, players, roles);

        gameRoom.assignPlayerRoles();
        for (int i = 0; i < players.size(); i++) {
            Player currentPlayer = players.get(i);
            System.out.println(currentPlayer.getName()+ ": " + currentPlayer.getRole().getRoleName());
        }
        gameRoom.dayPhase();
    }
}
