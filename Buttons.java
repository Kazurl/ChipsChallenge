/**
 *<ol>
 *     <li>File-name: Buttons.java</li>
 *     <li>Purpose of the program: Creates the buttons for the game.</li>
 *
 *</ol>
 */
public class Buttons extends Tile {

    /**
     * Checks the state of the button.
     */
    private boolean buttonState;

    /**
     * Creates a Button tile at the specified coordinates.
     * This constructor was used for testing.
     *
     * @param x The X-coordinate of the button tile.
     * @param y The Y-coordinate of the button tile.
     */
    public Buttons(int x, int y) {
        super(true, true);
        this.buttonState = false; //Set initial as false
    }

    /**
     * Constructs a Button tile at the specified coordinates along with
     * the Trap that it should connect to.
     *
     * @param x The X-coordinate of the button tile.
     * @param y The Y-coordinate of the button tile.
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
     * @param buttonState IS True to set the button as pressed,
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
}