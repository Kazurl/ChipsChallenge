public class LockedDoor extends Tile {
    
    //Enum:
    public enum Colour {
        red,
        blue,
        green,
        yellow;
    }

    //Attribute:
    private boolean doorState;
    private Colour dColour;


    //Constructor:
    public LockedDoor(int x, int y,Colour dColour) {
        super(x , y, true); //Set as collision initially
        this.dColour = dColour;
        this.doorState = true; //Door is initially locked
    }

    //Getter:
    public boolean getDoorState() {
        return this.doorState;
    }

    public Colour getDColour() {
        return this.dColour;
    }

    //Setter:
    public void setDoorState(boolean state) {
        this.doorState = state;
    }

    public void setDoorColour(Colour colour) {
        this.dColour = colour;
    }

    //Interact Methods
    public void interactPlayer(Player player) {

        //Search in player inventory, if there's keys.
        //Probs need loop here.
        //Use unlock() to check if key same and unlock the door.

    }

    public void interactMob(Mob mob) {
        mob.block();
    }


    //Other method:
    private boolean unlock(Key key) {

        //Check if key colour is the same as the door
        if (key.getKeyColour() == dColour) {
            doorState = false; //Door is opened
            setCollision(false); //Player can walk over it
            return true;
        } else {
            return false;
        }

    }


}
