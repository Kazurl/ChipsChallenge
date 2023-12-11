/**
 *<ol>
 *     <li>File-name: Block.java</li>
 *     <li>Purpose of the program: Creates the Block tile for the game. This includes
 *     the behaviour of the Block when interacting with various elements of the game.</li>
 *
 *</ol>
 * @author [Code - Freddie, Enrique, Azmeera. JavaDoc - Ffi, Enrique]
 */
public class Block extends Actor {

    /**
     * Checks if the Block is on Ice.
     */
    private boolean onIce;

    /**
     * Initialise the collision of the Block.
     */
    private boolean collision = true;

    /**
     * X-Coordinates for the Block.
     */
    private int locationX;

    /**
     * Y-Coordinates for the Block.
     */
    private int locationY;

    /**
     * Manipulates the behaviour of the Block depending on the type of Ice tile.
     *
     * @param tile Takes the tile of the Ice the Block is on.
     */
    public void slideOnIce(Tile tile) {
    }

    /**
     * Handles the behaviour of the Block when
     * interacting with the player.
     *
     * @param player when Player touches the Block,
     *               it activates the behaviour of the Block.
     */
    public void interactPlayer(Player player) {

    }
}
