public public class Trap extends Tile {
    
    //Attribute:
    private Button connectButton;
    private boolean trapState;

    //Constructor:
    public Trap(int x, int y, Button button) {
        super( x, y, true);
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

} {
    
}
