/**
 *<ol>
 *     <li>File-name: Trap.java</li>
 *     <li>Purpose of the program: Creates the Trap for the game,
 *     it extends the Tile class and can be activated or deactivated based on a connected button.</li>
 *
 *</ol>
 */
public class Trap extends Tile {

    /**
     * The Button that connects to the Trap.
     */
    private Buttons connectButton;

    /**
     * The initial state of the Trap.
     */
    private boolean trapState;

    /**
     * The X-Coordinates of the Trap.
     */
    private int x;

    /**
     * The Y-Coordinates of the Trap.
     */
    private int y;

    /**
     * Identifier number for the Trap.
     */
    private int identifier;

    /**
     * Creates the default Trap.
     * This was used for testing.
     */
    public Trap() {
        super(true, true);
        deactivate(); // Initialize Trap as closed
    }

    /**
     * Constructor for the Trap class.
     * Initializes a Trap at the specified coordinates
     * and sets it as initially closed.
     *
     * @param x The X-Coordinate of the Trap.
     * @param y The Y-Coordinate of the Trap.
     */
    public Trap(int x, int y) {
        super(true, true);
        this.x = x;
        this.y = y;
        deactivate(); // Initialize Trap as closed
    }

    /**
     * Creates the Trap with a connected button.
     * Initializes a Trap at the specified coordinates,
     * connects it to a button, and sets it as initially closed.
     *
     * @param x      The X-Coordinate of the Trap.
     * @param y      The Y-Coordinate of the Trap.
     * @param button The button connected to the Trap.
     */
    public Trap(int x, int y, Buttons button) {
        super(true, true);
        this.connectButton = button;
        deactivate(); // Initialize Trap as closed
    }

    /**
     * Checks if the Trap is activated.
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

    /**
     * Gets the connected Button for the Trap.
     *
     * @return The Button connected to the Trap.
     */
    public Buttons getConnectButton() {
        return connectButton;
    }

    /**
     * Set the connected Button for the Trap.
     *
     * @param connectButton The Button that will connect to the Trap.
     */
    public void setConnectButton(Buttons connectButton) {
        this.connectButton = connectButton;
    }

    /**
     * Gets the X-coordinate of the Trap.
     *
     * @return The X-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the X-coordinate of the Trap.
     *
     * @param x The X-coordinate.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the Y-coordinate of the Trap.
     *
     * @return The Y-coordinate of the Trap.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the Y-coordinate of the Trap.
     *
     * @param y The Y-coordinate.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets the identifier of the Trap.
     * This lets the FileConverter know that this Button has a connection
     * with the same identifier number Button.
     *
     * @return identifier number for the Trap.
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * Sets the identifier for the Trap.
     *
     * @param identifier The identifier number of the Trap.
     */
    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }
}
