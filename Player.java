/**
 * The Player class represents the player character in the game, extending the Actor class.
 * It includes attributes such as location, inventory, and methods for interacting with the game world.
 */
public class Player extends Actor{

    /**
     * Enum representing directions the player can face.
     */
    enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
    private boolean isMoving;
    private Tile currTile;
    private Tile nextTile;
    private int[] inventory = {/*has red key?*/0,/*has green key?*/0,/*has blue key?*/0,/*has yellow key?*/0,/*how many chips?*/0};;
    boolean isAlive;
    public int playerLocationX, playerLocationY;

    /**
     * Default constructor for the Player class.
     * Initializes the player as alive.
     */
    
    public Player() {
        super();
        this.isAlive = true;
    }

    /**
     * Sets the direction the player is facing.
     *
     * @param direction The direction to set.
     */
    
    public void setDirection(Direction direction){

    }
/**
     * Sets the X-coordinate of the player's location.
     *
     * @param locationX The X-coordinate to set.
     */
    public void setX(int locationX) {
        this.playerLocationX = locationX;
    }

    /**
     * Sets the Y-coordinate of the player's location.
     *
     * @param locationY The Y-coordinate to set.
     */
    public void setY(int locationY) {
        this.playerLocationY = locationY;
    }

    /**
     * Gets the X-coordinate of the player's location.
     *
     * @return The X-coordinate of the player's location.
     */
    public int getX() {
        return this.playerLocationX;
    }

    /**
     * Gets the Y-coordinate of the player's location.
     *
     * @return The Y-coordinate of the player's location.
     */
    public int getY() {
        return this.playerLocationY;
    }
/**
     * Gets the current tile the player is on.
     *
     * @return The current tile.
     */
    public Tile getCurrTile() {
        return this.currTile;
    }

    /**
     * Gets the next tile the player will move to.
     *
     * @return The next tile.
     */
    public Tile getNextTile() {
        return this.nextTile;
    }

    /**
     * Checks if the player is alive.
     *
     * @return True if the player is alive, false otherwise.
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Sets the player's alive status.
     *
     * @param status The status to set (true for alive, false for dead).
     */
    public void setAlive(boolean status) {
        this.isAlive = status;
    }
    
    /**
     * Gets the player's inventory.
     *
     * @return The player's inventory array.
     */
    public int[] getInventory() {
        return this.inventory;
    }

    /**
     * Uses the specified number of chips from the player's inventory.
     *
     * @param num The number of chips to use.
     * @return True if there are enough chips and they are used, false otherwise.
     */
    public boolean useChips(int num) {
        if (this.inventory[4] >= num) {
            this.inventory[4] -= num;
            return true;
        } else {
            return false;
        }
    }
    /**
     * Uses a key of the specified color from the player's inventory.
     *
     * @param colour The color of the key to use.
     * @return True if there is a key of the specified color and it is used, false otherwise.
     */
    public boolean useKey(Key.Colour colour) {
        if(colour == Key.Colour.RED) {
            if(this.inventory[0] > 0) {
                this.inventory[0] -= 1;
                return true;
            } else {
                return false;
            }
        } else if(colour == Key.Colour.GREEN) {
            if(this.inventory[1] > 0) {
                this.inventory[1] -= 1;
                return true;
            } else {
                return false;
            }
        } else if(colour == Key.Colour.BLUE) {
            if(this.inventory[2] > 0) {
                this.inventory[2] -= 1;
                return true;
            } else {
                return false;
            }
        } else if(colour == Key.Colour.YELLOW) {
            if(this.inventory[3] > 0) {
                this.inventory[3] -= 1;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Sets the player's inventory to the specified array.
     *
     * @param inv The inventory array to set.
     */
    public void setInventory(int[] inv) { this.inventory = inv; }

    /**
     * Adds a key or chip to the player's inventory if there is space.
     *
     * @param item The item to add ("red key", "green key", "blue key", "yellow key", or "chip").
     */
    public void addToInventory(String item){
        switch(item){
            case "red key":
                this.inventory[0] = 1;
                break;
            case "green key":
                this.inventory[1] = 1;
                break;
            case "blue key":
                this.inventory[2] = 1;
                break;
            case "yellow key":
                this.inventory[3] = 1;
                break;
            case "chip":
                this.inventory[4] += 1;
                break;
        }

    }
    /*
    public void interactMob(Mobs mob) {
        if ((playerLocationX == mob.mobLocationX)&&(playerLocationY == mob.mobLocationY)){
            deadOrAlive = false;

        }
    }
    */
}
