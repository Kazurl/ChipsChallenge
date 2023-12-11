/**
 *<ol>
 *     <li>File-name: Ice.java</li>
 *     <li>Purpose of the program: Creates the Ice tile for the game.</li>
 *
 *</ol>
 * @author [Code - Freddie, Ffi, Azmeera - Ffi, Enrique]
 */
public class Ice extends Tile {

    /**
     * Enumeration representing the different corner types of the Ice tile.
     * Corner types include NONE, TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, and BOTTOM_RIGHT.
     */
    public enum CornerType {
        NONE,
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT
    }

    /**
     * The corner type of the Ice tile.
     */
    private CornerType cornerType;

    /**
     * The X-Coordinate of the Ice tile's location.
     */
    private int locationX;

    /**
     * The Y-Coordinate of the Ice tile's location.
     */
    private int locationY;

    /**
     * Creates the different Ice tile depending on the cornerType.
     *
     * @param x The X-coordinate of the Ice tile's location.
     * @param y The Y-coordinate of the Ice tile's location.
     * @param cornerType The corner type of the Ice tile.
     */
    public Ice(int x, int y, CornerType cornerType) {
        super(true, true);
        this.locationX = x;
        this.locationY = y;
        this.cornerType = cornerType;
    }

    /**
     * Gets the corner type of the Ice tile.
     *
     * @return The corner type of the Ice tile.
     */
    public CornerType getCornerType() {
        return cornerType;
    }
}
