public class Ice extends Tile {
    
    public enum CornerType {
        none,
        top_left,
        top_right,
        bottom_left,
        bottom_right
    }

    //Attribute:
    private CornerType cornerType;

    //Constructor:
    public Ice(int x, int y, CornerType cornerType) {
        super(x, y, false);
    }

    //Getter:
    public CornerType getCornerType() {
        return this.cornerType;
    }

    //Setter:
    public void setCornerType(CornerType cornerType) {
        this.cornerType = cornerType;
    }

    //Interact Methods:
    public CornerType interactPlayer(Player player) {

        //When player lands on tile, then check cornerType.

    }


    public void interactBlock(Block block) {

        //Probably similar to player.

    }


    //Other Methods:
    //Needa think more about this.

}
