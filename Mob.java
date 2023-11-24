public class Mob extends Actor{

    private Tile currentTile, nextTile;
    public int mobLocationX, mobLocationY;

    public Mob(Tile tile) {
        super();
        this.currentTile = tile;
    }

    public void setX(int locationX) {
        this.mobLocationX = locationX;
    }

    public void setY(int locationY) {
        this.mobLocationY = locationY;
    }

    public int getX() {
        return this.mobLocationX;
    }

    public int getY(int locationY) {
        return this.mobLocationY;
    }

    public Tile getNextTile() {
        return this.nextTile;
    }

    public Tile getCurrTile() {
        return this.currentTile;
    }

    public void interactPlayer(Player player) {
        // update this method
        // Need to check if same tile as player and trigger event
        if (this.nextTile == player.getCurrTile()) {

        }
    }
}
