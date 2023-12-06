/**
 * The Wall class represents a tile of type Wall, extending the Tile class.
 * It is a non-walkable tile that blocks movement.
 */
public class Wall extends Tile {

    /**
     * Constructor for the Wall class.
     * Creates a non-walkable wall tile.
     */
    public Wall() {
        super(false, false);
    }
}
