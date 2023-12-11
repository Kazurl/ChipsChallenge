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

    /**
     * Unlocks the door if the colored key is correct.
     *
     * @param key The key used to unlock the door.
     * @return True if the door is successfully unlocked, false otherwise.
     */
    public boolean unlock(Key key) {
        // Check if the key's colour matches the door's colour
        if (key.getKeyColour() == doorColour) {
            // Unlock the door and replace it with a walkable path
            setWalkable(true);
            return true; // Unlock successful
        } else {
            return false; // Key does not match, unlock failed
        }
    }
}
