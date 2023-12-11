
/**
 *<ol>
 *     <li>File-name: Exit.java</li>
 *     <li>Purpose of the program: Creates the Exit tile for the game,
 *     the Game will end once the Player reaches this tile.</li>
 *
 *</ol>
 * @author [Code - Freddie, Ffi, Azmeera. JavaDoc - Ffi, Enrique]
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
     * Gets the X-Coordinate of the exit tile.
     *
     * @return The X-Coordinate of the exit tile.
     */
    public int getExitX() {
        return exitX;
    }

    /**
     * Gets the Y-Coordinate of the exit tile.
     *
     * @return The Y-Coordinate of the exit tile.
     */
    public int getExitY() {
        return exitY;
    }

    /**
     * Checks if the player is on the exit tile.
     *
     * @param player The Player to check for.
     */
    public boolean isPlayerOnExit(Player player) {
        return player.getX() == getExitX() && player.getY() == getExitY();
    }

}
