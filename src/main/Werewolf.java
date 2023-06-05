/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wolfcub.main;

import java.util.List;
import java.util.Random;

public class Werewolf extends Role{
    private List<Player> votes;

    public Werewolf() {
        super("Werewolf");
    }
    
    // Werewolf ability is to kill a player at night
    // Each werewolf get to vote one player per night
    @Override
    public void performAbility(Player player) {
        this.votes.add(player);
    }
    
    public Player getMostVotedKillPlayer() {
        
        Player mostVotedPlayer;
        
        if (votes.get(0) == votes.get(1)) {
            mostVotedPlayer = votes.get(0);
        }
        else {
            Random random = new Random();
            int index = random.nextInt(1);
            
            mostVotedPlayer = votes.get(index);
        }
        
        // check if the player is being protected by a doctor
        if (!mostVotedPlayer.isProtected()) {
            mostVotedPlayer.setEliminated(true);
        }
        
        
        return mostVotedPlayer;
        
    } 
}
