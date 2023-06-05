package wolfcub.main;

public class Timer {
    private long startTime;
    private long duration;

    public Timer(long duration) {
        this.duration = duration * 1000; // Convert duration to milliseconds
    }

    public long getDuration() {
        return duration;
    }

    public void start() {
        startTime = System.currentTimeMillis(); // Get the current system time in milliseconds
    }

    public boolean isExpired() {
        long elapsedTime = System.currentTimeMillis() - startTime; // Calculate the elapsed time since start
        return elapsedTime >= duration; // Return true if elapsed time is greater than or equal to the duration
    }

    public long getRemainingTime() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        long remainingTime = duration - elapsedTime; // Calculate remaining time in milliseconds
        return Math.max(remainingTime, 0); // Return remaining time, ensuring it is not negative
    }
}
