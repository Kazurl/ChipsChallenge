public class Player {
    enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
    private boolean isMoving;
    private Tile currTile;
    private Tile nextTile;
    private int[] inventory;
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

    public int getY(int locationY) {
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

    public Object[] getInventory() {
        return this.inventory;
    }

    //add keys or chips to inventory if there is space
    public void addToInventory(Object newObject){
        for(int i=0;i<= inventory.length;i++){
            if (inventory[i] == null) {
                inventory[i] = newObject;
            }
        }

    }

    public void interactMob(Mobs mob) {
        if ((playerLocationX == mob.mobLocationX)&&(playerLocationY == mob.mobLocationY)){
            deadOrAlive = false;

        }
    }
}
