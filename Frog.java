import java.util.ArrayDeque;
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
     * Sets the traversed cell.
     */
    final static int TRAVERSED = 2;

    /**
     * Sets the path cell.
     */
    final static int PATH = 3;

    /**
     * The 2D array for the Map.
     */
    private int[][] map;

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
     * Checks for the shortest path from the frog's current position to a specified destination on the map.
     *
     * @param startx The starting X-coordinate on the map.
     * @param starty The starting Y-coordinate on the map.
     * @param playerx The destination X-coordinate on the map.
     * @param playery The destination Y-coordinate on the map.
     * @return True if a path to the destination exists, false otherwise.
     */
    private boolean checkShortest(int startx, int starty, int playerx, int playery) {
        Queue<int[]> Q = new ArrayDeque<>();
        Q.offer(new int[]{startx, starty});

        while (!Q.isEmpty()) {
            int[] curr = Q.poll();
            int row = curr[0];
            int col = curr[1];

            if (isEnd(row, col, playerx, playery)) {
                map[row][col] = PATH;
                return true;
            } else {
                map[row][col] = TRAVERSED;

                int[][] directions = {{-1,0}, {0,1}, {1,0}, {0, -1}};
                for (int[] dir : directions) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];

                    if (isValid(nextRow, nextCol)) {
                        Q.offer(new int[]{nextRow, nextCol});
                        map[nextRow][nextCol] = TRAVERSED;
                    }
                }
            }
        }

        return false;
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
        return map[row][col] == 1;
    }

    /**
     * Checks if the Frog position has already been traversed.
     *
     * @param row The row to be checked.
     * @param col The column to be checked.
     * @return True if the position has been traversed, false otherwise.
     */
    private boolean isTraversed(int row, int col) {
        return map[row][col] == TRAVERSED;
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
}

