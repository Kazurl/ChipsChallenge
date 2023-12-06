/**
 * The Path class represents a tile of type Path, extending the Tile class.
 * It is a walkable tile that allows movement.
 */
public class Path extends Tile {

    /**
     * Constructor for the Path class.
     * Creates a walkable path tile.
     */
    public Path() {
        super(true, true);
    }
}
