public class Mobs extends Actors{

    private Tiles currentTile;
    public int mobLocationX, mobLocationY;

    public class Mobs(Tiles tile) {
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

    public void interactPlayer(Player player) {
        // update this method
        // Need to check if same tile as player and trigger event
    }
}
