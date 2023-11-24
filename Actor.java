public class Actors {
    enum Direction{
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    private int actorLocationX;
    private int actorLocationY;
    private int ticksPerMove;
    private int lastMoveTick;
    private Direction direction;

    public Actors() {
        this.actorLocationX = 0;
        this.actorLocationY = 0;
    }

    public int getLocationX() {
        return actorLocationX;
    }

    public int getLocationY() {
        return actorLocationY;
    }

    public void setLocation(int x, int y) {
        actorLocationX = x;
        actorLocationY = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction dir) {
        direction = dir;
    }

    public void update() {
        // Need to update this method
    }
}