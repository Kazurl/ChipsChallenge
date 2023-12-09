/**
 *<ol>
 *     <li>File-name: Dirt.java</li>
 *     <li>Purpose of the program: Creates the Dirt tile in the game.
 *     The dirt should turn into a Path when the Player interacts with the Tile.
 *     The Mobs should not be able to walk on the Dirt.</li>
 *
 *</ol>
 */
public class Dirt extends Tile {

    /**
     * Creates the Dirt.
     *
     * @param convertPath True if the dirt tile can be part of a path, False otherwise.
     */
    public Dirt(boolean convertPath) {
      super(convertPath, false);
    }


}