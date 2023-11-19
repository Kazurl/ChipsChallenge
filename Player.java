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

    public void setDirection(Direction direction){

    }

    public Tile getNextTile() {
        return this.nextTile;
    }

    public boolean isAlive() {
        boolean deadOrAlive;
        return deadOrAlive;
    }

    public int getInventory() {
        return this.inventory;
    }
}
