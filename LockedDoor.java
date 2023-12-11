/**
 *<ol>
 *     <li>File-name: LockedDoor.java</li>
 *     <li>Purpose of the program: Creates the LockedDoor for the game.
 *     It has different colours and it extends the Tile class.</li>
 *
 *</ol>
 * @author [Code - Freddie, Ffi, Azmeera. JavaDoc - Ffi, Enrique]
 */
public class LockedDoor extends Tile {

    /**
     * The colour of the LockedDoor.
     */
    private Key.Colour doorColour;

    /**
     * Creates a coloured LockedDoor.
     *
     * @param doorColour The colour of the locked door.
     */
    public LockedDoor(Key.Colour doorColour) {
        super(false, false);
        this.doorColour = doorColour;
    }

    /**
     * Gets the colour of the locked door.
     *
     * @return The colour of the locked door.
     */
    public Key.Colour getDoorColour() {
        return doorColour;
    }
}
