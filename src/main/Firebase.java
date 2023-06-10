package wolfcub.main;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import wolfcub.main.Room;
import wolfcub.main.Player;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class Firebase {
    private static DatabaseReference databaseRef;

    public static void initializeFirebaseApp() {
        try {
            FileInputStream serviceAccount = new FileInputStream("wolfcubs-2023-firebase-adminsdk-dtu4e-0e7f622e92.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://wolfcubs-2023-default-rtdb.asia-southeast1.firebasedatabase.app/")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            System.out.println("Error initializing Firebase app: " + e.getMessage());
            System.exit(1);
        }
    }


//    ------------------ MAIN METHOD ----------------------------------------------------------------------------------------

    public static void main(String[] args) {
        // Initialize Firebase app
        initializeFirebaseApp();


        // Add a new player
//        Player player = new Player("jenny", true, "1");
//        addPlayer(player);

//        // Update the player's status
//        updatePlayerStatus("1", true);
//
//        // Delete the player
//        deletePlayer("3");

        // Display the player
        getPlayer("1");
    }

//    ------------------ PLAYER ----------------------------------------------------------------------------------------

    public static void addPlayer(Player player) {
        try {
            databaseRef = FirebaseDatabase.getInstance().getReference("Players");
            final CountDownLatch latch = new CountDownLatch(1);

            Map<String, Object> playerData = new HashMap<>();
            playerData.put("name", player.getName());
            //playerData.put("player_status", player.isActive());
            //SplayerData.put("role_id", player.getRole_id());

            Map<String, String> voteData = new HashMap<>();
            voteData.put("nominee_id", ""); //
            playerData.put("vote", voteData);

            databaseRef.push().setValue(playerData, (databaseError, databaseReference) -> {
                if (databaseError != null) {
                    System.out.println("Error adding player: " + databaseError.getMessage());
                } else {
                    System.out.println("Player added successfully.");
                }
                latch.countDown();
            });
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void updatePlayerStatus(String playerId, boolean status) {
        try {
            databaseRef = FirebaseDatabase.getInstance().getReference("Players").child(playerId).child("player_status");
            final CountDownLatch latch = new CountDownLatch(1);

            databaseRef.setValue(status, (databaseError, databaseReference) -> {
                if (databaseError != null) {
                    System.out.println("Error updating player: " + databaseError.getMessage());
                } else {
                    System.out.println("Player updated successfully. Player ID: " + playerId);
                }
                latch.countDown();
            });
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void deletePlayer(String playerId) {
        try {
            databaseRef = FirebaseDatabase.getInstance().getReference("Players").child(playerId);
            final CountDownLatch latch = new CountDownLatch(1);

            databaseRef.removeValue((databaseError, databaseReference) -> {
                if (databaseError != null) {
                    System.out.println("Error deleting player: " + databaseError.getMessage());
                } else {
                    System.out.println("Player deleted successfully. Player ID: " + playerId);
                }
                latch.countDown();
            });
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void getPlayer(String playerId) {
        try {
            databaseRef = FirebaseDatabase.getInstance().getReference("Players").child(playerId);
            final CountDownLatch latch = new CountDownLatch(1);

            databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        System.out.println("Player retrieved successfully:");
                        Player player = dataSnapshot.getValue(Player.class);
                        System.out.println("Name: " + player.getName());
                        //System.out.println("Status: " + player.isActive());
                        //System.out.println("Role ID: " + player.getRole_id());
                    } else {
                        System.out.println("Player does not exist. Player ID: " + playerId);
                    }
                    latch.countDown();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("Error retrieving player: " + databaseError.getMessage());
                }
            });
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


//    ------------------ ROOM ----------------------------------------------------------------------------------------

    public static void addRoom(Room room) {
        try {
            databaseRef = FirebaseDatabase.getInstance().getReference("Rooms");
            final CountDownLatch latch = new CountDownLatch(1);

            String roomId = databaseRef.push().getKey();
//            room.setId(roomId);

            databaseRef.child(roomId).setValue(room, (databaseError, databaseReference) -> {
                if (databaseError != null) {
                    System.out.println("Error adding room: " + databaseError.getMessage());
                } else {
                    System.out.println("Room added successfully. Room ID: " + roomId);
                }
                latch.countDown();
            });
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void updateRoom(Room room) {
        try {
//            String roomId = room.getId();
            String roomId = "1";

            if (roomId != null) {
                databaseRef = FirebaseDatabase.getInstance().getReference("Rooms").child(roomId);
                final CountDownLatch latch = new CountDownLatch(1);

                databaseRef.setValue(room, (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        System.out.println("Error updating room: " + databaseError.getMessage());
                    } else {
                        System.out.println("Room updated successfully. Room ID: " + roomId);
                    }
                    latch.countDown();
                });
                latch.await();
            } else {
                System.out.println("Invalid room ID.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void deleteRoom(String roomId) {
        try {
            databaseRef = FirebaseDatabase.getInstance().getReference("Rooms").child(roomId);
            final CountDownLatch latch = new CountDownLatch(1);

            databaseRef.removeValue((databaseError, databaseReference) -> {
                if (databaseError != null) {
                    System.out.println("Error deleting room: " + databaseError.getMessage());
                } else {
                    System.out.println("Room deleted successfully. Room ID: " + roomId);
                }
                latch.countDown();
            });
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void getRoom(String roomId) {
        try {
            databaseRef = FirebaseDatabase.getInstance().getReference("Rooms").child(roomId);
            final CountDownLatch latch = new CountDownLatch(1);

            databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        System.out.println("Room retrieved successfully:");
//                        Room room = dataSnapshot.getValue(Room.class);
//                        System.out.println("Room ID: " + room.getId());
//                        System.out.println("Start Time: " + room.getStartTime());
//                        System.out.println("End Time: " + room.getEndTime());
//                        System.out.println("Game Status: " + room.getGameStatus());
                        // Print other room attributes as needed
                    } else {
                        System.out.println("Room does not exist. Room ID: " + roomId);
                    }
                    latch.countDown();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("Error retrieving room: " + databaseError.getMessage());
                }
            });
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


//    ------------------ CHAT ----------------------------------------------------------------------------------------


}


