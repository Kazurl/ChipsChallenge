/**
 *<ol>
 *     <li>File-name: Buttons.java</li>
 *     <li>Purpose of the program: Creates the Buttons for the game. The Button is connected to
 *     their corresponding Trap.</li>
 *
 *</ol>
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
     * Creates a default Button.
     * This was used for testing.
     */
    public Buttons() {
        super(true, true);
        this.buttonState = false; //Set initial as false
    }


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
     * Creates a Button at the specified coordinates along with
     * the Trap that it should connect to.
     *
     * @param x The X-Coordinate of the button tile.
     * @param y The Y-Coordinate of the button tile.
     * @param connectedTrap Which Trap the button should connect to.
     */
    public Buttons(int x, int y, Trap connectedTrap) {
        super(true, true);
        this.buttonState = false; //Set initial as false
    }

    /**
     * Gets the current state of the button.
     *
     * @return True if the button is pressed, False if otherwise.
     */
    public boolean getButtonState() {
        return buttonState;
    }

    /**
     * Sets the state of the button.
     *
     * @param buttonState Is True to set the button as pressed,
     *                    False to set it as released.
     */
    public void setButtonState(boolean buttonState) {
        this.buttonState = buttonState;
    }

    /**
     * Method that lets Player interact with the Button. The button should
     * be pressed when the Player interacts with it and would release when
     * the player moves from the Button tile.
     *
     * @param player The Player that is interacting with the button.
     */
    public void interactPlayer(Player player) {

        //When interact with player, button is pressed.
    }

    /**
     * Method that lets Mob interact with the Button. The button should
     * be pressed when the Mob interacts with it and would release when
     * the MOb moves from the Button tile.
     *
     * @param mob The Mob that is interacting with the button.
     */
    public void interactMob(Mob mob) {

        //When interact with Mob, button is pressed
    }

    /**
     * Method that lets Block interact with the Button. The button should
     * be pressed when the Block interacts with it and would release when
     * the Block moves from the Button tile.
     *
     * @param block The Block that is interacting with the button.
     */
    public void interactBlock(Block block) {

        //When interact with Block, button is pressed
    }

    /**
     * Method that changes the button state to True.
     * Meaning the button is pressed.
     */
    public void press() {
        buttonState = true;
    }

    /**
     * Method that changes the button state to False.
     * Meaning the button is released.
     */
    public void release() {
        buttonState = false;
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