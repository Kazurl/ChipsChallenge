import java.util.Arrays;

public class Actor {
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
    private int[][] map;

    public Actor() {
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
        // Idea will be that direction will be randomly selected using RNG, then set next tile of actor to be 1 tile in that chosen direction

    }

        // To print out the state of map
    public String toString() {
        String str = "";
        for (int[] row : map) {
            str = Arrays.toString(row) + "\n";
        }
        return str;
    }
}