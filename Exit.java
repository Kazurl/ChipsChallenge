
/**
 *<ol>
 *     <li>File-name: Exit.java</li>
 *     <li>Purpose of the program: Creates the Exit tile for the game,
 *     the Game will end once the Player reaches this tile.</li>
 *
 *</ol>
 */
public class Exit extends Tile {

    /**
     * Exit X-Coordinates.
     */
    private int exitX;

    /**
     * Exit Y-Coordinates.
     */
    private int exitY;

    /**
     * Creates the Exit tile with initial location at (X,Y).
     */
    public Exit(int x, int y) {
        super(true, false);
        this.exitX = x;
        this.exitY = y;
    }

    /**
     * Gets the X-coordinate of the exit tile.
     *
     * @return The X-coordinate of the exit tile.
     */
    public int getExitX() {
        return exitX;
    }

    /**
     * Gets the Y-coordinate of the exit tile.
     *
     * @return The Y-coordinate of the exit tile.
     */
    public int getExitY() {
        return exitY;
    }

    /**
     * Sets the X-coordinate of the exit tile.
     *
     * @param x The new X-coordinate for the exit tile.
     */
    public void setExitX(int x) {
        this.exitX = x;
    }

    /**
     * Sets the Y-coordinate of the exit tile.
     *
     * @param y The new Y-coordinate for the exit tile.
     */
    public void setExitY(int y) {
        this.exitY = y;
    }

    /**
     * Checks if the player is on the exit tile.
     *
     * @param player The Player to check for.
     */
    public boolean isPlayerOnExit(Player player) {
        return player.getX() == getExitX() && player.getY() == getExitY();
    }
    
    /*
    public boolean isLevelWon(Player player, int time) {
       //IDK this part
    }
    */

}
