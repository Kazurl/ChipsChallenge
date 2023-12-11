/**
 *<ol>
 *     <li>File-name: Scheduler.java</li>
 *     <li>Purpose of the program: Creates the Scheduler for the Map,
 *     the class manages game ticks and level status, providing functionality
 *     to pause, resume, and update game ticks. .</li>
 *
 *</ol>
 * @author [Code - Freddie, Bernard. JavaDoc - Ffi, Enrique]
 */
public class Scheduler {

    /**
     * Current game ticks.
     */
    private int ticks;

    /**
     * Flag indicating whether the level has been won.
     */
    private boolean levelWon;

    /**
     * Flag indicating whether the game is paused.
     */
    private boolean paused;

    /**
     * Creates the default Scheduler needed for the game.
     */
    public Scheduler() {
        this.ticks = 0;
        this.paused = true;
        this.levelWon = false;
    }

    /**
     * Gets the ticks for the game.
     *
     * @return The current tick for the game.
     */
    public int getTick() {
        return this.ticks;
    }

    /**
     * Pauses the game.
     */
    public void pause() {
        paused = true;
    }

    /**
     * Unpauses the game.
     */
    public void unPause() {
        paused = false;
    }

    /**
     * Updates the game tick if the game is not paused and the level has not been won.
     *
     * @return The updated game tick if successful, or -1 if the game is paused or the level has been won.
     */
    public int updateTick() {
        if (!paused && !levelWon) {
            this.ticks++;
            return getTick();
        }
        return -1;
    }
}
