import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

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
    final static Actor TRAVERSED = new FrogRoute();

    /**
     * Creates a constant of type Actor and initialise it
     * with a new instance of the FrogTraversed class.
     */
    final static Actor PATH = new FrogTraversed();

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
    public Frog() {}

    /**
     * Constructs a Frog that will spawn on the specified tile.
     *
     * @param tile The starting tile for the Frog.
     */
    public Frog(Tile tile) {
        super(tile);
    }

    /**
     * Finds the shortest path from the frog's current position
     * based on a point on the map and moves accordingly.
     *
     *
     *
     */
    /*public int checkShortest(int[][] map, int frog) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int d[] = new int[6];
        int pred[] = new int[6];
        boolean S[] = new boolean[6];
        for (int i = 0; i < 6; i++) {
            d[i] = 1000;
            pred[i] = -1;
            S[i] = false;
        }
        d[frog] = 0;
        // put all vertices in priority queue, Q, in d[i] decreasing order
        // while !Empty(Q){
        int u = pq.poll();
        System.out.println("Cheapest: " + u + "| The rest of priority Queue: " + pq);
        S[u] = true;
        for ea vertex i adj to u{
            if (S[i] != true && d[i] > d[u]+1){
                remove i from Q;
                d[i] = d[u] + 1;
                pred[i] = u;
                insert i into Q acc to its d[i];
            }
        }
    }*/

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
     * Checks for the shortest path from the frog's current position to a specified destination on the map.
     *
     * @param startx The starting X-coordinate on the map.
     * @param starty The starting Y-coordinate on the map.
     * @param playerx The destination X-coordinate on the map.
     * @param playery The destination Y-coordinate on the map.
     * @return True if a path to the destination exists, false otherwise.
     */
    public int[] checkShortest(int startx, int starty, int playerx, int playery) {
        Queue<int[]> Q = new ArrayDeque<>();
        Q.add(new int[]{startx, starty});

        boolean[][] visited = new boolean[actorLayerMap.length][actorLayerMap[0].length];
        int [][] prev = new int[actorLayerMap.length][actorLayerMap[0].length];

        while (!Q.isEmpty()) {
            int[] curr = Q.poll();
            int row = curr[0];
            int col = curr[1];

            if (isEnd(row, col, playerx, playery)) {
                int r = row;
                int c = col;
                while (r != startx || c != starty) {
                    this.actorLayerMap[r][c] = PATH;
                    int tempr = prev[r][c]/actorLayerMap[0].length;
                    c = prev[r][c]%actorLayerMap[0].length;
                    r = tempr;
                }
                return new int[] {r,c};
            } else {
                this.actorLayerMap[row][col] = TRAVERSED;

                int[][] directions = {{-1,0}, {0,1}, {1,0}, {0, -1}};
                for (int[] dir : directions) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];

                    if (isValid(nextRow, nextCol)) {
                        Q.offer(new int[]{nextRow, nextCol});
                        visited[nextRow][nextCol] = true;
                        prev[nextRow][nextCol] = row * actorLayerMap[0].length + col;   //This provides a unique identifier to figure out where each tile is since it is only 1d
                        this.actorLayerMap[nextRow][nextCol] = TRAVERSED;
                    }
                }
            }
        }

        return null;
    }

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

    public int[] isPath(int startx, int starty, int playerx, int playery) {
        boolean[][] visited = new boolean[actorLayerMap.length][actorLayerMap[0].length];

        for (int i = 0; i< actorLayerMap.length; i++) {
            for (int j =0; j< actorLayerMap[0].length; j++) {
                visited[i][j] = false;
            }
        }

        Pair[][] parent = new Pair[actorLayerMap.length][actorLayerMap[0].length];
        Queue<Pair> Q = new LinkedList<>();
        int[][] directions = {{-1,0}, {0,1}, {1,0}, {0, -1}};
        int destx = directions[0][0], desty = directions[0][1];

        Q.add(new Pair(startx, starty));
        visited[startx][starty] = true;
        parent[startx][starty] = new Pair(-1, -1);

        while (Q.size() > 0) {
            Pair p = (Q.peek());
            Q.remove();

            if (p.Item1 == playerx && p.Item2 == playery) {
                int tempx = p.Item1, tempy = p.Item2;
                while (parent[tempx][tempy].Item1 != startx && parent[tempx][tempy].Item2 != starty){
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

                if (walkableActor(nextRow, nextCol) && walkableTile(nextRow, nextCol)
                        && visited[nextRow][nextCol] == false && validHeight(nextRow, actorLayerMap.length)
                        && validWidth(nextCol, actorLayerMap[0].length)) {
                    visited[nextRow][nextCol] = true;
                    Q.add(new Pair(nextRow, nextCol));
                    parent[nextRow][nextCol] = new Pair(p.Item1, p.Item2);
                }
            }
        }
        return new int[] {destx, desty};
    }

    public boolean walkableActor(int xcoord, int ycoord) {
        if(validWidth(ycoord, actorLayerMap[0].length) && validHeight(xcoord, actorLayerMap.length)) {
            return actorLayerMap[xcoord][ycoord] instanceof Player || actorLayerMap[xcoord][ycoord] == null;
        } else {
            return false;
        }
    }
    public boolean walkableTile(int xcoord, int ycoord) {
        if(validWidth(ycoord, actorLayerMap[0].length) && validHeight(xcoord, actorLayerMap.length)) {
            return tileLayerMap[xcoord][ycoord].getWalkable();
        } else {
            return false;
        }
    }
    /**
     * Checks if the Frog is on the same tile as the Player.
     *
     * @param row The row on the map.
     * @param col The column on the map.
     * @param playerx The Player's X-coordinate on the map.
     * @param playery The Player's Y-coordinate on the map.
     * @return True if the Frog's position is the same as the Player, False otherwise.
     */
    private boolean isEnd(int row, int col, int playerx, int playery) {
        return row == playerx && col == playery;
    }

    /**
     * Checks if the given position is valid for traversal
     * and not outside the Map.
     *
     * @param row The row on the map.
     * @param col The column on the map.
     * @return True if the position is valid, false otherwise.
     */
    private boolean isValid(int row, int col) {
        if (validHeight(row, col) && validWidth(col, col) && isAccessible(row, col) && !isTraversed(row, col)) {
            return true;
        }
        return false;
    }


    /**
     * Checks if the given position is accessible on the map.
     *
     * @param row The row to be checked.
     * @param col The column to be checked.
     * @return True if the position is accessible, false otherwise.
     */
    private boolean isAccessible(int row, int col) {
        return this.tileLayerMap[row][col].getWalkable() ;
    }

    /**
     * Checks if the Frog position has already been traversed.
     *
     * @param row The row to be checked.
     * @param col The column to be checked.
     * @return True if the position has been traversed, false otherwise.
     */
    private boolean isTraversed(int row, int col) {
        return this.actorLayerMap[row][col] == TRAVERSED;
    }

    /**
     * Checks if the given row is within the valid height range of the map.
     *
     * @param row The row to be checked.
     * @param height The height of the map.
     * @return True if the row is within the valid height range, false otherwise.
     */

    /**
     * Checks if the given column is within the valid width range of the map.
     *
     * @param col The column to be checked.
     * @param width The width of the map.
     * @return True if the column is within the valid width range, false otherwise.
     */

}
