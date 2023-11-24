public class Exit extends Tile{
    
    private int exitX;
    private int exitY;

    public Exit(int x, int y) {
        super(true, false);
        this.exitX = x;
        this.exitY = y;
    }

    //Getters
    public int getExitX() {
        return exitX;
    }

    public int getExitY() {
        return exitY;
    }

    public void setExitX(int x) {
        this.exitX = x;
    }

    public void setExitY(int y) {
        this.exitY = y;
    }

    public boolean isPlayerOnExit(Player player) {
        return player.getX() == getExitX() && player.getY() == getExitY();
    }
    
    /*
    public boolean isLevelWon(Player player, int time) {
       //IDK this part
    }
    */

}
