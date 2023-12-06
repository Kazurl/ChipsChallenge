/**
 * The {@code Scheduler} class manages game ticks and level status, providing functionality
 * to pause, resume, and update game ticks.
 */

public class Scheduler 
{
    private int ticks;           // Current game ticks
    private boolean levelWon;    // Flag indicating whether the level has been won
    private boolean levelLost;   // Flag indicating whether the level has been lost
    private boolean paused;      // Flag indicating whether the game is paused

    public Scheduler() {
        this.ticks = 0;
        this.paused = false;
        this.levelWon = false;
    }

    /**
     * Constructs a new {@code Scheduler} with initial settings.
     */
    public int getTick() {
        return this.ticks;
    }

    /**
     * Gets the status of whether the level has been won.
     *
     * @return {@code true} if the level has been won, {@code false} otherwise.
     */
    public boolean getLevelWon() {
        return this.levelWon;
    }

    /**
     * Sets the status of whether the level has been won and toggles the pause state.
     *
     * @param won {@code true} if the level has been won, {@code false} otherwise.
     */
    public void setLevelWon(boolean won) {
        togglePause();
        this.levelWon = won;
    }

    /**
     * Gets the status of whether the level has been lost.
     *
     * @return {@code true} if the level has been lost, {@code false} otherwise.
     */
    public boolean getLevelLost() {
        return this.levelLost;
    }

    /**
     * Sets the status of whether the level has been lost and toggles the pause state.
     *
     * @param lost {@code true} if the level has been lost, {@code false} otherwise.
     */
    public void setLevelLost(boolean lost) {
        togglePause();
        this.levelLost = lost;
    }

    /**
     * Checks if the game is currently paused.
     *
     * @return {@code true} if the game is paused, {@code false} otherwise.
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
