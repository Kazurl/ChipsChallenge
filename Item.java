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
     * The X-Coordinate of the item's location.
     */
    public int itemLocationX;

    /**
     * The Y-Coordinate of the item's location.
     */
    public int itemLocationY;

    /**
     * Creates the Item based on the X and Y coordinates.
     *
     * @param itemLocationX The X-Coordinate of the item.
     * @param itemLocationY The Y-Coordinate of the item.
     */
    public Item(int itemLocationX, int itemLocationY) {
        this.itemLocationX = itemLocationX;
        this.itemLocationY = itemLocationY;
    }

    /**
     * Sets the X-Coordinate of the item's location.
     *
     * @param locationX The new X-Coordinate of the item.
     */
    public void setX(int locationX) {
        this.itemLocationX = locationX;
    }

    /**
     * Sets the Y-Coordinate of the item's location.
     *
     * @param locationY The new Y-Coordinate of the item.
     */
    public void setY(int locationY) {
        this.itemLocationY = locationY;
    }

    /**
     * Gets the X-Coordinate of the item's location.
     *
     * @return The X-Coordinate of the item.
     */
    public int getX() {
        return this.itemLocationX;
    }

    /**
     * Gets the Y-Coordinate of the item's location.
     *
     * @return The Y-Coordinate of the item.
     */
    public int getY() {
        return this.itemLocationY;
    }
    
}
