package wolfcub.main;

import java.util.ArrayList;

public class Chat {
    private int chatID;
    private int chatType;
    private ArrayList<String> messages;
    private ArrayList<Player> players;

    public Chat(int chatID, int chatType) {
        this.chatID = chatID;
        this.chatType = chatType;
        this.messages = new ArrayList<String>();
//        this.messages.add(chatType+":Hello Players! Welcome to WerewolfCubs\n");
        this.players = new ArrayList<Player>();
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }

    public ArrayList<String> getMessages() {
        return this.messages;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getChatID() {
        return this.chatID;
    }

    public int getChatType() {
        return this.chatType;
    }

}

class ChatType {
    public static final int GENERAL = 0;
    public static final int WEREWOLF = 1;
}
