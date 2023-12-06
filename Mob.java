/**
 * The Mob class represents a mobile entity in the game, extending the Actor class.
 * Mobs can move around the game world and interact with other entities.
 */
public class Mob extends Actor{

    private Tile currentTile, nextTile;
    public int mobLocationX, mobLocationY;

    public Mob() {
        super();
    }

    /**
     * Constructor for the Mob class with an initial tile.
     *
     * @param tile The initial tile on which the Mob is placed.
     */
    public Mob(Tile tile) {
        super();
        this.currentTile = tile;
    }

    /**
     * Sets the X-coordinate of the mob's location.
     *
     * @param locationX The X-coordinate to set.
     */
    public void setX(int locationX) {
        this.mobLocationX = locationX;
    }

    /**
     * Sets the Y-coordinate of the mob's location.
     *
     * @param locationY The Y-coordinate to set.
     */
    public void setY(int locationY) {
        this.mobLocationY = locationY;
    }

    /**
     * Gets the X-coordinate of the mob's location.
     *
     * @return The X-coordinate of the mob's location.
     */
    public int getX() {
        return this.mobLocationX;
    }

    /**
     * Gets the Y-coordinate of the mob's location.
     *
     * @return The Y-coordinate of the mob's location.
     */
    public int getY() {
        return this.mobLocationY;
    }

    /**
     * Gets the next tile the mob will move to.
     *
     * @return The next tile.
     */
    public Tile getNextTile() {
        return this.nextTile;
    }

    /**
     * Gets the current tile the mob is on.
     *
     * @return The current tile.
     */
    public Tile getCurrTile() {
        return this.currentTile;
    }

    /**
     * Interacts with the player, triggering events if on the same tile.
     *
     * @param player The player to interact with.
     */
    public void interactPlayer(Player player) {
        // update this method
        // Need to check if same tile as player and trigger event
        if (this.nextTile == player.getCurrTile()) {

        }
    }
}
