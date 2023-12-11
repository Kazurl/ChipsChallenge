/**
 *<ol>
 *     <li>File-name: Water.java</li>
 *     <li>Purpose of the program: Creates the Water tile for the game, it extends the Tile class and
 *     contains information about walk ability and events triggered when a player walks on it.</li>
 *
 *</ol>
 *
 * @author [Code - Freddie, Ffi, Azmeera. JavaDoc - Ffi, Enrique]
 */
public class Water extends Tile {

    /**
     * Placeholder for handling events when a player walks on the Water tile.
     */
    public Boolean onPlayerWalk;


    /**
     * Creates the Water tile.
     *
     * @param convertPath True if the tile converts to a path, false otherwise.
     */
    public Water(boolean convertPath) {
        super(convertPath, true);
    }


    /**
     * Sets the walk ability of the Water tile and handles the event when a player walks on it.
     *
     * @param walk True if the tile should be walkable, false otherwise.
     */
    public void setWalkable(boolean walk) {

        super.setWalkable(walk);

        if (walk) {
            onPlayerWalk = true;
        } else {
            onPlayerWalk = false;
        }
    }
}
