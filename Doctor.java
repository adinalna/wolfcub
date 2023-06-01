/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package warewolfculb;

/**
 *
 * @author HP
 */
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
