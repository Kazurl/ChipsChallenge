/**
 *<ol>
 *     <li>File-name: Ice.java</li>
 *     <li>Purpose of the program: Creates the Ice tile for the game.</li>
 *
 *</ol>
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
     * The X-coordinate of the Ice tile's location.
     */
    private int locationX;

    /**
     * The Y-coordinate of the Ice tile's location.
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
/*
   // Method to handle player interaction (skating)
   public void onPlayerSkate(Player player) {
    Tile nextTile = player.getNextTile();
    if (nextTile != null && nextTile.isWalkable()) {
        // Move the player to the next tile
        player.move(nextTile);
    } else if (hasCorners) {
        deflectPlayer(player);
    }
}

// Method to handle block interaction
public void onBlockPush(Block block) {
    Tile nextTile = block.getNextTile();
    if (nextTile != null && nextTile.isPushable() && nextTile.isWalkable()) {
        // Move the block to the next tile
        block.move(nextTile);
    } else if (hasCorners) {
        // Handle corner deflection if the tile has corners
        deflectBlock(block);
    }
}

// Method to deflect the player when hitting a corner
private void deflectPlayer(Player player) {
    // Implement corner deflection logic based on cornerType
    // Adjust the logic according to your corner configurations
    switch (cornerType) {
        case TOP_LEFT:
            // Deflect player logic for top-left corner
            break;
        case TOP_RIGHT:
            // Deflect player logic for top-right corner
            break;
        case BOTTOM_LEFT:
            // Deflect player logic for bottom-left corner
            break;
        case BOTTOM_RIGHT:
            // Deflect player logic for bottom-right corner
            break;
    }
}

// Method to deflect the block when hitting a corner
    private void deflectBlock(Block block) {
        // Implement corner deflection logic based on cornerType
        // Adjust the logic according to your corner configurations
        switch (cornerType) {
            case TOP_LEFT:
                // Deflect block logic for top-left corner
                break;
            case TOP_RIGHT:
                // Deflect block logic for top-right corner
                break;
            case BOTTOM_LEFT:
                // Deflect block logic for bottom-left corner
                break;
            case BOTTOM_RIGHT:
                // Deflect block logic for bottom-right corner
                break;
            }
        }
*/
    /**
     * Gets the corner type of the Ice tile.
     *
     * @return The corner type of the Ice tile.
     */
    public CornerType getCornerType() {
        return cornerType;
    }
}
