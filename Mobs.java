public class Mobs extends Actors{
    private Tile currentTile;

    public class Mobs(Tile tile) {
        super();
        this.currentTile = tile;
    }

    public void interactPlayer(Player player) {
        // update this method
        // Need to check if same tile as player and trigger event
    }
}
