public class Tiles {

    //Attibutes:
    private boolean walkable;
    private boolean pushable;

    //Constructor:
    public Tiles(boolean walkable, boolean pushable) {
        this.walkable = walkable;
        this.pushable = pushable;
    }

    //Setters
    public void setWalkable(boolean walk) {
        this.walkable = walk;
    }

    public void setPushable(boolean push) {
        this.pushable = push;
    }

    //Getters 
    public boolean getWalkable() {
        return this.walkable;
    }

    public boolean getPushable() {
        return this.pushable;
    }

}
