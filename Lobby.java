/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package warewolfculb;

import java.util.*;
import java.util.concurrent.locks.*;

/**
 *
 * @author Farisah
 */
public class Lobby{
    private static int userIdStart = 1;
    private static int roomCounter = 0;
    private static final int MAX_PUBLIC_ROOMS = 10;
    private static final int MAX_PRIVATE_ROOMS = 10;

    
//    private List<Player> players = new ArrayList<>();
    private static final List<Room> publicRooms = new ArrayList<>();
    private static final List<Room> privateRooms = new ArrayList<>();
    private static final Lock publicRoomsLock = new ReentrantLock();
    private static final Lock privateRoomsLock = new ReentrantLock();

    //to generate user ID
    public static int generateUserId() {
        synchronized (Lobby.class) {
            userIdStart = ++userIdStart;
            return userIdStart;
        }
    }
    
    public static int getUserId(){
        return userIdStart;
    }
    
    //If user join public room
    //
    public static boolean joinPublicRoom(String userId) {
        publicRoomsLock.lock();
        try {
            //find available room
            for (Room room : publicRooms) {
                if (!room.isFull()) {
                    room.addUser(userId);
                    return true;
                }
            }
            // If no available public room, create new one
            if (publicRooms.size() < MAX_PUBLIC_ROOMS) {
                Room room = new Room();
                room.addUser(userId);
                publicRooms.add(room);
                new Thread(room).start(); // Start the room as a thread
            } else {
                // If all public rooms are full
                return false;
            }
        } finally {
            publicRoomsLock.unlock();
        }
        return true;
    }
    
    //if user create private room
    //private room would be automatically destroyed if more than 5 min waiting time
    public static String createRoom(String userId) {
        privateRoomsLock.lock();
        try {
            if (privateRooms.size() >= MAX_PRIVATE_ROOMS) {
                // TODO: Handle the case when all private rooms are full
                return null;
            }
            Room room = new Room();
            room.addUser(userId);
            privateRooms.add(room);
            new Thread(room).start();
            return String.format("%04d", ++roomCounter);
        } finally {
            privateRoomsLock.unlock();
        }
    }
    
    //fullPrivateRoom return 1 if full, return 0 if still not full
    public static int fullPrivateRoom(int roomcode){
        //accessing the database, get total user in the room or maybe we can use map first
        return(0);
    }
}
