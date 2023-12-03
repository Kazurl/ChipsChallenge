public class Player extends Actor{
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
    boolean deadOrAlive;
    public int playerLocationX, playerLocationY;

    public Player() {
        super();
    }

    public void setDirection(Direction direction){

    }

    public void setX(int locationX) {
        this.playerLocationX = locationX;
    }

    public void setY(int locationY) {
        this.playerLocationY = locationY;
    }

    public int getX() {
        return this.playerLocationX;
    }

    public int getY() {
        return this.playerLocationY;
    }

    public Tile getCurrTile() {
        return this.currTile;
    }

    public Tile getNextTile() {
        return this.nextTile;
    }

    public boolean isAlive() {
        return deadOrAlive;
    }

    public void setAlive(boolean status) {

    }

    public int[] getInventory() {
        return this.inventory;
    }

    public boolean useChips(int num) {
        if(this.inventory[4] >= num) {
            this.inventory[4] = this.inventory[4]-num;
            return true;
        } else {
            return false;
        }
    }
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

    public void setInventory(int[] inv) { this.inventory = inv; }

    //add keys or chips to inventory if there is space
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
