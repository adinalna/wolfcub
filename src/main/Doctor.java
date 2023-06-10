package wolfcub.main;

import java.util.List;

public class Doctor extends Role{
    private List<Player> playersProtected;
    public Doctor() {
        super("Doctor");
    }

    @Override
    public void specialAbility(Player player) {
        playersProtected.add(player);
    }
}
