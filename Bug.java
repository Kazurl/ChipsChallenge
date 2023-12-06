public class Bug extends Mob {
    private boolean followLeft;
    public Bug() {
    }

    public void setFollow(boolean left) {
        this.followLeft = left;
    }
    public boolean getFollow() {
        return this.followLeft;
    }
    public Bug(Tile tile) {
        super(tile);
    }
}