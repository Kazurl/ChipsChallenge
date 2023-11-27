public abstract class Tile {
    
    //Position:
    protected int x; //Should it be final? Since position doesn't change?
    protected int y;

    //Condition:
    protected boolean collision; //Essentially blocksMovement

    public Tile(int x, int y, boolean collision) {
        this.x = x;
        this.y = y;
        this.collision = collision;
    }

    //Getter:
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean hasCollision() {
        return this.collision;
    }

    //Setter:
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public String toString() {
        String output = "The X-position is" + x;
        output += "\n The Y-position is" + y;
        output += "\n Initial collision is set to: " + collision;
        return output;
    }

}
