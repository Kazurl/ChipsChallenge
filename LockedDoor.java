public class LockedDoor extends Tile {

    private boolean isLocked;
    private Key.Colour doorColour;

    public LockedDoor(boolean isLocked, Key.Colour doorColour) {
        super(true, false);
        this.isLocked = isLocked;
        this.doorColour = doorColour;
    }

    // Getter for isLocked
    public boolean isLocked() {
        return isLocked;
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
            isLocked = false;
            setWalkable(true);
            return true; // Unlock successful
        } else {
            return false; // Key does not match, unlock failed
        }
    }
}