package wolfcub.main;

import java.util.List;

public class Werewolf extends Role{
    private List<Player> playersToKill;

    public Werewolf() {
        super("Werewolf");
    }

    public Player getPlayerToKill(int round){
        return playersToKill.get(round);
    }

    @Override
    public void specialAbility(Player player) {
        playersToKill.add(player);
    }
}
