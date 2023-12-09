/**
 *<ol>
 *     <li>File-name: Item.java</li>
 *     <li>Purpose of the program: This is the superclass for the Items
 *     in the game.</li>
 *
 *</ol>
 */
public class Item {

    /**
     * The X-coordinate of the item's location.
     */
    public int itemLocationX;

    /**
     * The Y-coordinate of the item's location.
     */
    public int itemLocationY;

    /**
     * Creates the Item based on the X and Y coordinates.
     *
     * @param itemLocationX The X-coordinate of the item.
     * @param itemLocationY The Y-coordinate of the item.
     */
    public Item(int itemLocationX, int itemLocationY) {
        this.itemLocationX = itemLocationX;
        this.itemLocationY = itemLocationY;
    }

    /**
     * Sets the X-coordinate of the item's location.
     *
     * @param locationX The new X-coordinate of the item.
     */
    public void setX(int locationX) {
        this.itemLocationX = locationX;
    }

    /**
     * Sets the Y-coordinate of the item's location.
     *
     * @param locationY The new Y-coordinate of the item.
     */
    public void setY(int locationY) {
        this.itemLocationY = locationY;
    }

    /**
     * Gets the X-coordinate of the item's location.
     *
     * @return The X-coordinate of the item.
     */
    public int getX() {
        return this.itemLocationX;
    }

    /**
     * Gets the Y-coordinate of the item's location.
     *
     * @return The Y-coordinate of the item.
     */
    public int getY() {
        return this.itemLocationY;
    }
    
}