/**
 *<ol>
 *      <li>File-name: Tile.java
 *      <li>Purpose of the program: Creates the tiles for map
 *</ol>
 * @author Azmeera, Ffi
 * @version 1.1 - added comments
 */


public class Tile {

    /**
     * If tile is walkable by Player and Mobs.
     */
    private boolean walkable;

    /**
     * If Block is allowed to be pushed onto the tile.
     */
    private boolean pushable;

    /**
     * Creates the tile.
     * @param walkable If the tile is walkable.
     * @param pushable If the tile is pushable.
     */
    //Constructor
    public Tile(boolean walkable, boolean pushable) {
        this.walkable = walkable;
        this.pushable = pushable;
    }

    //Getter and Setters:

    /**
     * 
     * @return True if tile is walkable, else False.
     */
    public boolean getWalkable() {
        return this.walkable;
    }

    /**
     * 
     * @return True if tile is pushable, else False.
     */
    public boolean getPushable() {
        return this.pushable;
    }

    /**
     * Change the walk boolean.
     */
    public void setWalkable(boolean walk) {
        this.walkable = walk;
    }

    /**
     * 
     * Change the push boolean.
     */
    public void setPushable(boolean push) {
        this.pushable = push;
    }

}
