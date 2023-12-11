/**
 *<ol>
 *     <li>File-name: ComputerChip.java</li>
 *     <li>Purpose of the program: Creates the Chip item in the game.
 *     The chip is collected by the Player and saved in the Inventory to unlock the ChipSocket.</li>
 *
 *</ol>
 * @author [Code - Freddie, Azmeera, Ffi. JavaDoc - Ffi, Enrique]
 */
public class ComputerChip extends Item {

    /**
     * Creates a ComputerChip at the specified coordinates.
     *
     *@param itemLocationX The X-Coordinate.
     *@param itemLocationY The Y-Coordinate.
     */
    public ComputerChip(int itemLocationX, int itemLocationY) {
        super(itemLocationX, itemLocationY);
    }

    /**
     * Method that allows the player to interact with the computer chip,
     * adding it to the player's inventory.
     *
     * @param player The Player.
     */
    public void interactPlayer(Player player) {
        player.addToInventory("chip");
    }

}
