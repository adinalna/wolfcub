/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wolfcub.main;

public class Seer extends Role{
    public Seer() {
        super("Seer");
    }

    // Seer ability is to reveal the role of the player 
    // Once the seer click on a player, the seer can see the player's role 
    @Override
    public void performAbility(Player player) {
        System.out.println(player.getPlayerID() + " is " + player.getRole().getRoleName());
    }
}
