package wolfcub.main;

public class Doctor extends Role{
    public Doctor() {
        super("Doctor");
    }

    // Doctor ability is to protect 
    // Once the doctor click on a player, the player cannot be killed
    @Override
    public void performAbility(Player player) {
        player.setProtected(true);
    }
}
