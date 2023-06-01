/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package warewolfculb;

import java.util.ArrayList;
import java.util.List;

public abstract class Role {

    private String roleName;
    private Player player;

    // Construtor for Role class
    public Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    // Each roles has their own ability that can be perform
    public abstract void performAbility(Player player);


//    public static List<Role> createRoleList() {
//
//        // Create a list of available type of villagers to be assign at random
//        List<Role> roles = new ArrayList<>();
//        roles.add(new Werewolf());
//        roles.add(new Werewolf());
//        roles.add(new Seer());
//        roles.add(new Doctor());
//        roles.add(Villager.createVillagers().get(0));
//        roles.add(Villager.createVillagers().get(1));
//        roles.add(Villager.createVillagers().get(2));
//
//        return roles;
//
//    }


}
