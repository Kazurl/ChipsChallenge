import java.io.*;
import java.util.*;

/**
 *<ol>
 *     <li>File-name: Frog.java</li>
 *     <li>Purpose of the program: Creates the Frog in the game, and inherits from the Mob class.
 *     It will find the shortest path from a point on the map and move accordingly.</li>
 *
 *</ol>
 */
public class Frog extends Mob{

    /**
     * Creates a constant of type Actor and initialise it
     * with a new instance of the FrogRoute class.
     */
    //final static Actor TRAVERSED = new FrogRoute();

    /**
     * Creates a constant of type Actor and initialise it
     * with a new instance of the FrogTraversed class.
     */
    //final static Actor PATH = new FrogTraversed();

    /**
     * 2D array for the Actor Layer on Map.
     */
    private Actor[][] actorLayerMap;

    /**
     * 2D array for Tile Layer on Map.
     */
    private Tile[][] tileLayerMap;

    /**
     * Creates the Frog.
     * This was used for testing.
     */
    public Frog() {
        super();
    }

    /**
     * Constructs a Frog that will spawn on the specified tile.
     *
     * @param tile The starting tile for the Frog.
     */
    public Frog(Tile tile) {
        super(tile);
    }

    /**
     * Method that sets the Map when given the Actor 2D array and Tile 2d Array.
     *
     * @param givenActorMap 2D Actor array.
     * @param givenTileMap 2D Tile array.
     */
    public void setMap(Actor[][] givenActorMap, Tile[][]givenTileMap) {
        this.actorLayerMap = givenActorMap;
        this.tileLayerMap = givenTileMap;
    }

    /**
     * Checks if the given row is within the valid height range of the map.
     *
     * @param row The row to be checked.
     * @param height The height of the map.
     * @return True if the row is within the valid height range, false otherwise.
     */
    private boolean validHeight(int row, int height) {
        return row >= 0 && row < height;
    }

    /**
     * Checks if the given column is within the valid width range of the map.
     *
     * @param col The column to be checked.
     * @param width The width of the map.
     * @return True if the column is within the valid width range, false otherwise.
     */
    private boolean validWidth(int col, int width) {
        return col >= 0 && col < width;
    }

    /**
     * Checks for the shortest path from the frog's current position to a specified destination on the map.
     *
     * @param startx The starting X-coordinate on the map.
     * @param starty The starting Y-coordinate on the map.
     * @param playerx The destination X-coordinate on the map.
     * @param playery The destination Y-coordinate on the map.
     * @return Immediate coordinates in the shortest path on the map.
     */
    public int[] isPath(int startx, int starty, int playerx, int playery) {

        boolean[][] visited = new boolean[actorLayerMap.length][actorLayerMap[0].length];

        for (int i = 0; i< actorLayerMap.length; i++) {
            for (int j =0; j< actorLayerMap[0].length; j++) {
                visited[i][j] = false;
            }
        }

        Pair[][] parent = new Pair[actorLayerMap.length][actorLayerMap[0].length];
        for (int i = 0; i< actorLayerMap.length; i++) {
            for (int j = 0; j < actorLayerMap[0].length; j++) {
                parent[i][j] = null;
            }
        }
        Queue<Pair> Q = new LinkedList<>();
        int[][] directions = {{-1,0}, {0,1}, {1,0}, {0, -1}};
        Q.add(new Pair(startx, starty));
        visited[startx][starty] = true;
        parent[startx][starty] = new Pair(startx, starty);
        int destx = Q.peek().Item1 + directions[0][0];
        int desty = Q.peek().Item2 + directions[0][1];

        while (Q.size() > 0) {
            Pair p = (Q.peek());
            Q.remove();

            if (p.Item1 == playerx && p.Item2 == playery) {
                int tempx = p.Item1, tempy = p.Item2;
                // Backtracking to find the path from the player pos
                while (parent[tempx][tempy].Item1 != startx || parent[tempx][tempy].Item2 != starty){
                    destx = parent[tempx][tempy].Item1;
                    desty = parent[tempx][tempy].Item2;
                    tempx = destx;
                    tempy = desty;
                }
                return new int[] {destx, desty};
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = p.Item1 + directions[i][0];
                int nextCol = p.Item2 + directions[i][1];

                if (walkableActor(nextRow, nextCol) && walkableTile(nextRow, nextCol) && visited[nextRow][nextCol] == false) {
                    visited[nextRow][nextCol] = true;
                    Q.add(new Pair(nextRow, nextCol));
                    parent[nextRow][nextCol] = new Pair(p.Item1, p.Item2);
                }
            }
        }

        return new int[] {destx, desty};
    }

    private boolean walkableActor(int xcoord, int ycoord) {
        if (validHeight(xcoord, actorLayerMap.length) && validWidth(ycoord, actorLayerMap[0].length)){
            if (actorLayerMap[xcoord][ycoord] == null || actorLayerMap[xcoord][ycoord] instanceof Player) return true;
        }
        return false;
    }
    private boolean walkableTile(int xcoord, int ycoord) {
        if (!(validHeight(xcoord, actorLayerMap.length) && validWidth(ycoord, actorLayerMap[0].length))) return false;
        return tileLayerMap[xcoord][ycoord].getWalkable();
    }
}
