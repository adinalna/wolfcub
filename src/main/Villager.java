/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wolfcub.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Villager extends Role{
    private String villagerType;

    public Villager(String villagerType) {
        super("Villager");
        this.villagerType = villagerType;
    }
    
    public String getVillagerType() {
        return this.villagerType;
    }
    
    @Override
    public void performAbility(Player player) {
        // Villagers do not perform any special actions
    }
    
    public static List<Villager> createVillagers() {
        
        // Create a list of available type of villagers to be assign at random
        List<String> villagerTypes = new ArrayList<>();
        villagerTypes.add("Fisherman");
        villagerTypes.add("Merchant");
        villagerTypes.add("Farmer");
        villagerTypes.add("Blacksmith");
        villagerTypes.add("Hunter");
        villagerTypes.add("Baker");
        villagerTypes.add("Carpenter");
        villagerTypes.add("Miner");
        villagerTypes.add("Shepherd");
        
        List<Villager> villagers = new ArrayList<>();
        Random random = new Random();
        
        // Select 3 villager types from the villagerType list
        // As there will be 3 villagers available per game
        for (int i = 1; i <= 3; i++) {
            int index = random.nextInt(villagerTypes.size());
            // Remove the chosen index from the list
            String type = villagerTypes.remove(index);
            Villager villager = new Villager(type);
            villagers.add(villager);
        }
        
        return villagers;
    }
}
