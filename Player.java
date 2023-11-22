public class Player {
    enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
    private boolean isMoving;
    private Tile nextTile;
    private int[] inventory;
    boolean deadOrAlive;

    public Player() {
        super();
    }

    public void setDirection(Direction direction){

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

    public void interactMob() {
        // Need to check if same tile as mob and trigger event
    }
}
