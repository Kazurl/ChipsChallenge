/**
 * The Trap class represents a tile of type Trap, extending the Tile class.
 * It can be activated or deactivated based on a connected button.
 */
public class Trap extends Tile {

    // Attributes:
    private Buttons connectButton;
    private boolean trapState;

    private int x;
    private int y;

    private int identifier;

    public Trap() {
        super(true, true);
        deactivate(); // Initialize Trap as closed
    }

    /**
     * Constructor for the Trap class.
     * Initializes a Trap at the specified coordinates and sets it as initially closed.
     *
     * @param x The x-coordinate of the Trap.
     * @param y The y-coordinate of the Trap.
     */
    public Trap(int x, int y) {
        super(true, true);
        this.x = x;
        this.y = y;
        deactivate(); // Initialize Trap as closed
    }

    /**
     * Constructor for the Trap class with a connected button.
     * Initializes a Trap at the specified coordinates, connects it to a button, and sets it as initially closed.
     *
     * @param x      The x-coordinate of the Trap.
     * @param y      The y-coordinate of the Trap.
     * @param button The button connected to the Trap.
     */
    public Trap(int x, int y, Buttons button) {
        super(true, true);
        this.connectButton = button;
        deactivate(); // Initialize Trap as closed
    }

    /**
     * Getter method to check if the Trap is activated.
     *
     * @return True if the Trap is activated, false otherwise.
     */
    public boolean isActive() {
        return this.trapState;
    }

    /**
     * Updates the Trap status based on the state of the connected button.
     *
     * @param button The state of the connected button.
     */
    public void updateTrapStatus(boolean button) {
        if (button) {
            deactivate();
        } else {
            activate();
        }
    }

    /**
     * Activates the Trap, opening it.
     */
    public void activate() {
        this.trapState = true;
        // Trap is open
    }

    /**
     * Deactivates the Trap, closing it.
     */
    public void deactivate() {
        this.trapState = false; // Trap is closed
    }

    public Buttons getConnectButton() {
        return connectButton;
    }

    public void setConnectButton(Buttons connectButton) {
        this.connectButton = connectButton;
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
