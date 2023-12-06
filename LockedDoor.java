/**
 * The LockedDoor class represents a tile of type LockedDoor, extending the Tile class.
 * It is initially not walkable and can be unlocked using a key of a specific colour.
 */

public class LockedDoor extends Tile {
//From Freddie: Door doesnt need locked status, if the door has been unlocked it it now a path not a door
    private Key.Colour doorColour;

    /**
     * Constructor for the LockedDoor class.
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
     * Attempts to unlock the door using a key.
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
