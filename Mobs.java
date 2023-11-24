public class Mobs extends Actors{
    private Tile currentTile;
    private Tile nextTile;

    public Mobs(Tile tile) {
        super();
        this.currentTile = tile;
    }

    public Tile getCurrTile() {
        return this.currentTile;
    }

    public Tile getNextTile() {
        return this.nextTile;
    }

    public void interactPlayer(Player player) {
        // update this method
        // Need to check if same tile as player and trigger event
        if (this.nextTile == player.getCurrTile()) {
            
        }
    }
}
