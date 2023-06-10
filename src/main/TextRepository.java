package wolfcub.main;

import java.util.HashMap;
import java.util.Map;

public class TextRepository {

    private static final String ROLE_WEREWOLF = "Werewolf";
    private static final String ROLE_VILLAGER = "Villager";
    private static final String ROLE_SEER = "Seer";
    private static final String ROLE_DOCTOR = "Doctor";
    private static final Map<String, String> preGameTexts = new HashMap<>();
    private static final Map<String, String> dayPhaseTexts = new HashMap<>();
    private static final Map<String, String> nightPhaseTexts = new HashMap<>();
    private static final Map<String, String> winGameTexts = new HashMap<>();

    static {
        // Set the pre-game text for each role
        preGameTexts.put(ROLE_WEREWOLF, "You are a fierce Werewolf. Your goal is to eliminate all the villagers without getting caught. Work with your fellow Werewolf to decide who to kill each night.");
        preGameTexts.put(ROLE_VILLAGER, "You are a humble Villager. Your goal is to identify and eliminate the Werewolves among you to ensure the safety of the village.");
        preGameTexts.put(ROLE_SEER, "You are a wise Seer. Each night, you can use your special ability to discover the true identity of a player and determine if they are a Werewolf or not.");
        preGameTexts.put(ROLE_DOCTOR, "You are a skilled Doctor. Each night, you can choose a player to protect. If that player is targeted by the Werewolves, they will be saved from the attack.");

        // Set the day phase text for each role
        dayPhaseTexts.put(ROLE_WEREWOLF, "It is currently day time. Keep your true identity hidden and try to blend in with the villagers.");
        dayPhaseTexts.put(ROLE_VILLAGER, "It is currently day time. Discuss with other villagers and analyze the actions of players to identify the Werewolves.");
        dayPhaseTexts.put(ROLE_SEER, "It is currently day time. Share the information you gathered from your visions with the villagers to help identify the Werewolves.");
        dayPhaseTexts.put(ROLE_DOCTOR, "It is currently day time. Use your medical knowledge to protect and heal the villagers.");

        // Set the night phase text for each role
        nightPhaseTexts.put(ROLE_WEREWOLF, "It is now night time. Consult with your fellow Werewolf and select a villager to eliminate.");
        nightPhaseTexts.put(ROLE_VILLAGER, "It is now night time. Be cautious and vigilant as the Werewolves make their move.");
        nightPhaseTexts.put(ROLE_SEER, "It is now night time. Use your powers to uncover the true nature of a player.");
        nightPhaseTexts.put(ROLE_DOCTOR, "It is now night time. Choose a player to protect from the Werewolves.");

        // Set the win game text for each role
        winGameTexts.put(ROLE_WEREWOLF, "The Werewolves have triumphed! The village is now under their control.");
        winGameTexts.put(ROLE_VILLAGER, "The Villagers have emerged victorious! The Werewolves have been eliminated, and the village is safe.");
        winGameTexts.put(ROLE_SEER, "The Seer has succeeded! The true identity of the Werewolves has been revealed, and the village is safe.");
        winGameTexts.put(ROLE_DOCTOR, "The Doctor has saved the day! With your healing powers, the villagers have survived the Werewolf attacks.");
    }

    public static String getPreGameText(String roleType) {
        return preGameTexts.get(roleType);
    }

    public static String getDayPhaseText(String roleType) {
        return dayPhaseTexts.get(roleType);
    }

    public static String getNightPhaseText(String roleType) {
        return nightPhaseTexts.get(roleType);
    }

    public static String getWinGameText(String roleType) {
        return winGameTexts.get(roleType);
    }

    public static String getDayPhaseVoteText() {
        return "Select a player you think is the Werewolf! Cast Your Vote Now!";
    }
}
