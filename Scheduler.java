/**
 * The {@code Scheduler} class manages game ticks and level status, providing functionality
 * to pause, resume, and update game ticks.
 */


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
     * Flag indicating whether the level has been lost.
     */
    private boolean levelLost;

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
     * Gets the status of whether the level has been won.
     *
     * @return True if the level has been won, False otherwise.
     */
    public boolean getLevelWon() {
        return this.levelWon;
    }

    /**
     * Sets the status of whether the level has been won and toggles the pause state.
     *
     * @param won True if the level has been won, False otherwise.
     */
    public void setLevelWon(boolean won) {
        togglePause();
        this.levelWon = won;
    }

    /**
     * Gets the status of whether the level has been lost.
     *
     * @return True if the level has been lost, False otherwise.
     */
    public boolean getLevelLost() {
        return this.levelLost;
    }

    /**
     * Sets the status of whether the level has been lost and toggles the pause state.
     *
     * @param lost True if the level has been lost, False otherwise.
     */
    public void setLevelLost(boolean lost) {
        togglePause();
        this.levelLost = lost;
    }

    /**
     * Checks if the game is currently paused.
     *
     * @return True if the game is paused, False otherwise.
     */
    public boolean checkPause() {
        return paused;
    }

    /**
     * Toggles the pause state of the game.
     */
    public void togglePause() {
        paused = !paused;
        // Include method body for pausing ticks
        
    }

    /**
     * Pauses the game.
     */
    public void pause() {
        paused = true;
        // Include method body for pausing ticks

    }

    /**
     * Unpauses the game.
     */
    public void unPause() {
        paused = false;
        // Include method body for pausing ticks

    }
    /**
     * Updates the game tick if the game is not paused and the level has not been won.
     *
     * @return The updated game tick if successful, or -1 if the game is paused or the level has been won.
     */
    // Include method for ticks incrementation and send to map
    public int updateTick() {
        if (!paused && !levelWon) {
            this.ticks++;
            return getTick();
        }
        return -1;
    }
}
