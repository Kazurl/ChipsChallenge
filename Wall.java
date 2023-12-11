/**
 *<ol>
 *     <li>File-name: Wall.java</li>
 *     <li>Purpose of the program: Creates the Wall for the game, it extends the Tile class and
 *     is a non-walkable tile that blocks movement.</li>
 *
 *</ol>
 *
 * @author [Code - Freddie, Ffi, Azmeera. JavaDoc - Ffi, Enrique]
 */
public class Wall extends Tile {

    /**
     * Creates the Wall.
     * It should be non-walkable and non-pushable for any Actor.
     */
    public Wall() {
        super(false, false);
    }
}
