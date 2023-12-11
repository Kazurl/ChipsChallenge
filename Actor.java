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
     * The Actor direction.
     */
    private KeyCode direction;

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

}
