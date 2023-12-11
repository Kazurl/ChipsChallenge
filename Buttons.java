/**
 *<ol>
 *     <li>File-name: Buttons.java</li>
 *     <li>Purpose of the program: Creates the Buttons for the game. The Button is connected to
 *     their corresponding Trap.</li>
 *
 *</ol>
 *
 * @author [Code - Freddie, Azmeera, Ffi. JavaDoc - Ffi, Enrique]
 */
public class Buttons extends Tile {

    /**
     * Checks the state of the button.
     */
    private boolean buttonState;

    /**
     * Trap that the button connects to.
     */
    private Trap connectTrap;

    /**
     * Button X-Coordinate.
     */
    private int x;

    /**
     * Button Y-Coordinate.
     */
    private int y;

    /**
     * Identifier number for the Button.
     */
    private int identifier;

    /**
     * Creates a Button at the specified coordinates.
     *
     * @param x The X-Coordinates.
     * @param y The Y-Coordinates.
     */
    public Buttons(int x, int y) {
        super(true, true);
        this.x = x;
        this.y = y;
        this.buttonState = false; //Set initial as false
    }

    /**
     * Gets the connected trap for the Button.
     *
     * @return Connected Trap.
     */
    public Trap getConnectTrap() {
        return connectTrap;
    }

    /**
     * Sets the connected trap for the Button.
     *
     * @param connectTrap Connects the Trap to the Button.
     */
    public void setConnectTrap(Trap connectTrap) {
        this.connectTrap = connectTrap;
    }

    /**
     * Gets the X-Coordinates of the Button.
     *
     * @return X-Coordinates.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the X-Coordinates for the Button.
     *
     * @param x X-Coordinates.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the Y-Coordinates for the Button.
     *
     * @return Y-Coordinates.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the Y-Coordinates of the Button.
     *
     * @param y Y-Coordinates.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets the identifier of the Button.
     * This lets the FileConverter know that this Button has a connection
     * with the same identifier number Trap.
     *
     * @return identifier number for the Button.
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * Sets the identifier for the Button.
     *
     * @param identifier The identifier number of the Button.
     */
    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

}