import javafx.scene.input.KeyCode;

/**
 *<ol>
 *     <li>File-name: Actor.java</li>
 *     <li>Purpose of the program: Used as a base class for game entities,
 *     it's a superclass for Player, Mobs and Blocks.</li>
 *
 *</ol>
 * @author [Code - Freddie, Bernard, Olwen. JavaDoc - Ffi, Enrique]
 */
public class Actor {

    /**
     * Actor X-Coordinates.
     */
    private int actorLocationX;

    /**
     * Actor Y-Coordinates.
     */
    private int actorLocationY;

    /**
     * Record the ticks per move for each actor.
     */
    private int ticksPerMove;

    /**
     * Record the last move ticks for each actor.
     */
    private int lastMoveTick;

    /**
     * The Actor direction.
     */
    private KeyCode direction;

    /**
     * The 2D array of the Map that the Actor is on.
     */
    private int[][] map;

    /**
     * Creates an Actor with initial location at (0,0).
     */
    public Actor() {
        this.actorLocationX = 0;
        this.actorLocationY = 0;
    }

    /**
     * Gets the X-Coordinate of the actor's current location.
     *
     * @return The X-Coordinate of the actor's location.
     */
    public int getLocationX() {
        return actorLocationX;
    }

    /**
     * Gets the Y-Coordinate of the actor's current location.
     *
     * @return The Y-Coordinate of the actor's location.
     */
    public int getLocationY() {
        return actorLocationY;
    }

    /**
     * Sets the X and Y Coordinates of the actor.
     *
     * @param x The X-coordinate.
     * @param y The Y-coordinate.
     */
    public void setLocation(int x, int y) {
        actorLocationX = x;
        actorLocationY = y;
    }

    /**
     * Gets the current direction of the actor.
     *
     * @return The current direction of the actor.
     */
    public KeyCode getDirection() {
        return direction;
    }

    /**
     * Sets the direction of the actor's movement.
     *
     * @param dir Sets the new direction.
     */
    public void setDirection(KeyCode dir) {
        direction = dir;
    }

    /**
     * Updates the actor's state based on its current properties.
     * The method is intended to be overridden in subclasses to provide specific update logic.
     */
    public void update() {
        // Need to update this method
        // Idea will be that direction will be randomly selected using RNG, then set next tile of actor to be 1 tile in that chosen direction

    }
/* From Freddie: Should actor be trying to print the map?
        // To print out the state of map
    public String toString() {
        String str = "";
        for (int[] row : map) {
            str = Arrays.toString(row) + "\n";
        }
        return str;
    }
*/
}
