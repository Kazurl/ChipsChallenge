public class LockedDoor extends Tile {
//From Freddie: Door doesnt need locked status, if the door has been unlocked it it now a path not a door
    private Key.Colour doorColour;

    public LockedDoor(Key.Colour doorColour) {
        super(true, false);
        this.doorColour = doorColour;
    }

    // Getter for doorColour
    public Key.Colour getDoorColour() {
        return doorColour;
    }

    // Method to unlock the door using a key
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