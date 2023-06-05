/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wolfcub.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Room implements Runnable{
    private static final int MAX_USERS_PER_ROOM = 7;
    private final List<String> users = new ArrayList<>();
    private final Lock lock = new ReentrantLock();

    public boolean isFull() {
        return users.size() >= MAX_USERS_PER_ROOM;
    }

    public void addUser(String userId) {
        lock.lock();
        try {
            if (users.size() >= MAX_USERS_PER_ROOM) {
                throw new IllegalStateException("Room is already full");
            }
            users.add(userId);
        } finally {
            lock.unlock();
        }
    }
    
    @Override
    public void run() {
            // Code for running the room as a thread
            // ...
        }
}
