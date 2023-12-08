public class Buttons extends Tile {

    //Attribute:
    private boolean buttonState;

    private Trap connectTrap;

    private int x;
    private int y;

    private int identifier;

    //Constructor:
    public Buttons() {
        super(true, true);
        this.buttonState = false; //Set initial as false
    }
    public Buttons(int x, int y) {
        super(true, true);
        this.x = x;
        this.y = y;
        this.buttonState = false; //Set initial as false
    }
    public Buttons(int x, int y, Trap connectedTrap) {
        super(true, true);
        this.buttonState = false; //Set initial as false
    }

    //Getter:
    public boolean getButtonState() {
        return buttonState;
    }

    //Setter:
    public void setButtonState(boolean buttonState) {
        this.buttonState = buttonState;
    }

    //Interacting Methods:
    public void interactPlayer(Player player) {

        //When interact with player, button is pressed.
    }

    public void interactMob(Mob mob) {

        //When interact with Mob, button is pressed
    }

    public void interactBlock(Block block) {

        //When interact with Block, button is pressed
    }

    //Other method:
    public void press() {
        buttonState = true;
    }

    public void release() {
        buttonState = false;
    }

    public Trap getConnectTrap() {
        return connectTrap;
    }

    public void setConnectTrap(Trap connectTrap) {
        this.connectTrap = connectTrap;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }
}