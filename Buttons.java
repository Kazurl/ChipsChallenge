public class Buttons extends Tile {

    //Attribute:
    private boolean buttonState;

    //Constructor:
    public Buttons(int x, int y) {
        super(true, true);
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
}