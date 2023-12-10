/**
 *<ol>
 *     <li>File-name: Mob.java</li>
 *     <li>Purpose of the program: Creates the Mobs for the game, it extends the Actor class and
 *     the Mobs can move around the Map and interact with other entities.</li>
 *
 *</ol>
 */
public class Mob extends Actor{

    /**
     * The tile the Mob is on.
     */
    private Tile currentTile;

    /**
     * The tile the Mob will move next.
     */
    private Tile nextTile;

    /**
     * The X-coordinate for the Mob.
     */
    public int mobLocationX;

    /**
     * The Y-coordinate for the Mob.
     */
    public int mobLocationY;

    /**
     * Create a default Mob for the game.
     */
    public Mob() {
        super();
    }

    /**
     * Creates the Mob on that specific tile.
     *
     * @param tile The initial tile where the Mob will spawn.
     */
    public Mob(Tile tile) {
        super();
        this.currentTile = tile;
    }

    /**
     * Sets the X-coordinate for the Mob.
     *
     * @param locationX The X-coordinate.
     */
    public void setX(int locationX) {
        this.mobLocationX = locationX;
    }

    /**
     * Sets the Y-coordinate for the Mob.
     *
     * @param locationY The Y-coordinate.
     */
    public void setY(int locationY) {
        this.mobLocationY = locationY;
    }

    /**
     * Gets the X-coordinate of the Mob.
     *
     * @return The X-coordinate.
     */
    public int getX() {
        return this.mobLocationX;
    }

    /**
     * Gets the Y-coordinate of the Mob.
     *
     * @return The Y-coordinate of the Mob.
     */
    public int getY() {
        return this.mobLocationY;
    }

    /**
     * Gets the next tile that the mob will move to.
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
