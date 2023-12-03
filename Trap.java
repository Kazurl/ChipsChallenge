public class Trap extends Tile {

    //Attribute:
    private Buttons connectButton;
    private boolean trapState;

    //Constructor:
    public Trap(int x, int y) {
        super( true, true);
        deactivate(); //Initialise Trap as close
    }
    public Trap(int x, int y, Buttons button) {
        super( true, true);
        this.connectButton = button;
        deactivate(); //Initialise Trap as close
    }

    //Getter:
    public boolean isActivated() {
        return this.trapState;
    }

    public void updateTrapStatus(boolean button) {

        if (button) {
            deactivate();
        } else {
            activated();
        }
    }

    //Other Method:
    public void activated() {
        this.trapState = true; //Trap is open
    }

    public void deactivate() {
        this.trapState = false; //Trap is close
    }

}