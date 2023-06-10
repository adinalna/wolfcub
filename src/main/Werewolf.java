package wolfcub.main;

import java.util.ArrayList;
import java.util.List;

public class Werewolf extends Role{
    private List<Player> playersToKill;

    public Werewolf() {
        super("Werewolf");
        playersToKill = new ArrayList<>();
    }

    public Player getPlayersKilled(int round){
        return playersToKill.get(round);
    }

    @Override
    public void specialAbility(Player player) {
        playersToKill.add(player);
    }
}
