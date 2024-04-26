package ATC;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * represents runway, uses semaphores to manage access
 */
public class Runway {
    private final Semaphore semaphore;
    private final String id; // Identifier for the runway
    private static final long timeout = 1000;

    public Runway(String id) {
        this.id = id;
        this.semaphore = new Semaphore(1); // 1 means only one permit is available
    }

    /**
     * Attempt to acquire the runway for a landing or takeoff operation.
     * This method does not block indefinitely and returns a boolean.
     * @return true if the runway was acquired, false otherwise.
     */
    public boolean tryAcquire() {
        try {
            if (semaphore.tryAcquire(timeout, TimeUnit.MILLISECONDS)) {
                return true;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // restore interruption status
        }
        return false;
    }

    /**
     * Release the runway after a landing or takeoff operation is complete.
     */
    public void release() {
        semaphore.release();
    }

    /**
     * Get the runway ID.
     * @return the ID of the runway.
     */
    public String getId() {
        return id;
    }
}
