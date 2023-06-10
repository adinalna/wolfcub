package wolfcub.main;

import java.util.ArrayList;
import java.util.List;

public class Seer extends Role{
    private List<Player> revealedRoles;
    public Seer() {
        super("Seer");
        revealedRoles = new ArrayList<>();
    }

    @Override
    public void specialAbility(Player player) {
        revealedRoles.add(player);
    }
}
