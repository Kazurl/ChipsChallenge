
/**
 *<ol>
 *     <li>File-name: Key.java</li>
 *     <li>Purpose of the program: Creates the Key for the game.
 *     The key is used to open the locked doors and allows player to pass.</li>
 *
 *</ol>
 */
public class Key extends Item {

    /**
     * Enumeration representing the different colours for Key.
     */
    enum Colour {
        RED,
        BLUE,
        YELLOW,
        GREEN,
    }

    /**
     * The colour of the Key.
     */
    private Colour keyColour; // Assuming Colour is a class or enum representing colors

    /**
     * Creates a coloured Key at the specified coordinates
     *
     * @param itemLocationX The X-Coordinate.
     * @param itemLocationY The Y-Coordinate.
     * @param keyColour The Key Colour.
     */
    public Key(int itemLocationX, int itemLocationY, Colour keyColour) {
        super(itemLocationX, itemLocationY);
        this.keyColour = keyColour;
    }

    /**
     * Get the colour of the key.
     *
     * @return The colour of the key.
     */
    public Colour getKeyColour() {
        return keyColour;
    }

    /**
     * Will add the colour Key into the Player's inventory.
     *
     * @param player The player.
     */
    public void interactPlayer(Player player) {
        switch (this.keyColour) {
            case RED:
                player.addToInventory("red key");
                break;
            case GREEN:
                player.addToInventory("green key");
                break;
            case BLUE:
                player.addToInventory("blue key");
                break;
            case YELLOW:
                player.addToInventory("yellow key");
                break;
        }
    }
}
