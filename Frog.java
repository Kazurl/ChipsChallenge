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
    public Frog() {}

    /**
     * Constructs a Frog that will spawn on the specified tile.
     *
     * @param tile The starting tile for the Frog.
     */
    public Frog(Tile tile) {
        super(tile);
    }

    public static void main(String[] args) {
        // Create a Frog instance
        Frog frog = new Frog();

        // Set up test maps (this can be adjusted as per your needs)
        //frog.createActorMap();
        //frog.createTileMap();

        // Sample test coordinates (modify these as needed)
        int startX = 2;
        int startY = 4;
        int playerX = 1;
        int playerY = 1;

        // Check if a path exists from (startX, startY) to (playerX, playerY)
        int[] result = frog.isPath(startX, startY, playerX, playerY);

        if (result != null) {
            System.out.println("Path found. Destination coordinates: (" + result[0] + ", " + result[1] + ")");
        } else {
            System.out.println("No path found.");
        }
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
    /*public int[] checkShortest(int startx, int starty, int playerx, int playery) {
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
                for (int i = 0; i< 4; i++) {
                    int nextRow = row + directions[i][0];
                    int nextCol = col + directions[i][1];

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
    }*/

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
    /*private boolean isValid(int row, int col) {
        if (validHeight(row, col) && validWidth(col, col) && isAccessible(row, col) && !isTraversed(row, col)) {
            return true;
        }
        return false;
    }*/


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
    //private boolean isTraversed(int row, int col) {
    //return this.actorLayerMap[row][col] == TRAVERSED;
    //}

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

    public int[] isPath(int startx, int starty, int playerx, int playery) {
        System.out.println("Player Position: " + "(" + playerx + ", " + playery + ")" + " | Frog Position: (" + startx + ", " + starty + ")");
        Player player = new Player("one");
        player.setX(1);
        player.setY(1);
        createActorMap(player);
        createTileMap();
        // Displaying the entire actor map
        System.out.println("Actor Map:\n" + toStringActorMap());
        System.out.println("\nTile Map:\n" + toStringTileMap());

        boolean[][] visited = new boolean[actorLayerMap.length][actorLayerMap[0].length];

        for (int i = 0; i< actorLayerMap.length; i++) {
            for (int j =0; j< actorLayerMap[0].length; j++) {
                visited[i][j] = false;
            }
        }

        Pair[][] parent = new Pair[actorLayerMap.length][actorLayerMap[0].length];
        Queue<Pair> Q = new LinkedList<>();
        int[][] directions = {{-1,0}, {0,1}, {1,0}, {0, -1}};

        Q.add(new Pair(startx, starty));
        visited[startx][starty] = true;
        parent[startx][starty] = new Pair(-1, -1);
        int destx = Q.peek().Item1 + directions[0][0];
        int desty = Q.peek().Item2 + directions[0][1];

        while (Q.size() > 0) {
            Pair p = (Q.peek());
            System.out.println("Q.peek(): "+ Q.peek() +" | p: (" + p.Item1 + ", " + p.Item2 + ")");
            Q.remove();
            System.out.println("(destx, desty): (" + destx + ", " + desty + ")");

            if (p.Item1 == playerx && p.Item2 == playery) {
                int tempx = p.Item1, tempy = p.Item2;
                // Backtracking to find the path from the player pos
                System.out.println("/nStart Backtracking to find path.");
                while (parent[tempx][tempy].Item1 != startx && parent[tempx][tempy].Item2 != starty){
                    destx = parent[tempx][tempy].Item1;
                    desty = parent[tempx][tempy].Item2;
                    tempx = destx;
                    tempy = desty;
                    System.out.println("(destx, desty): (" + destx + ", " + desty + ")");
                }
                return new int[] {destx, desty};
            }

            System.out.println("Next Rows & Cols: ");
            for (int i = 0; i < 4; i++) {
                int nextRow = p.Item1 + directions[i][0];
                int nextCol = p.Item2 + directions[i][1];
                System.out.println("(nextRow, nextCol): (" + nextRow + ", " + nextCol + ")");

                if (walkableActor(nextRow, nextCol, player) && walkableTile(nextRow, nextCol) && visited[nextRow][nextCol] == false) {
                    visited[nextRow][nextCol] = true;
                    Q.add(new Pair(nextRow, nextCol));
                    parent[nextRow][nextCol] = new Pair(p.Item1, p.Item2);
                }
            }
        }

        return new int[] {destx, desty};
    }

    private boolean walkableActor(int xcoord, int ycoord, Player player) {
        System.out.println("(Playerx, Playery): (" + player.getX() + ", " + player.getY() + ")");
        if (validHeight(xcoord, actorLayerMap.length) && validWidth(ycoord, actorLayerMap[0].length)){
            if ( (xcoord == player.getX() && ycoord == player.getY()) || actorLayerMap[xcoord][ycoord] == null) {
                return true;
            }
        }
        return false;
    }
    private boolean walkableTile(int xcoord, int ycoord) {
        if (!(validHeight(xcoord, actorLayerMap.length) && validWidth(ycoord, actorLayerMap[0].length))) return false;
        return tileLayerMap[xcoord][ycoord].getWalkable();
    }

    private void createActorMap(Player player) {
        Bug bug = new Bug();

        this.actorLayerMap = new Actor[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j< 5; j++){
                if (i == 1 && j == 1) this.actorLayerMap[i][j] = player;
                else if (i == 2 && j == 4) this.actorLayerMap[i][j] = bug;
                else this.actorLayerMap[i][j] = null;
            }
        }
    }

    private void createTileMap() {
        Path path = new Path();

        this.tileLayerMap = new Tile[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j< 5; j++){
                /*if (i == 1 && j == 1) this.actorLayerMap[i][j] = player;
                if (i == 2 && j == 4) this.actorLayerMap[i][j] = bug;*/
                this.tileLayerMap[i][j] = path;
            }
        }
    }

    public String toStringActorMap() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < actorLayerMap.length; i++) {
            str.append(Arrays.toString(actorLayerMap[i])).append("\n");
        }
        return str.toString();
    }

    public String toStringTileMap() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tileLayerMap.length; i++) {
            str.append(Arrays.toString(tileLayerMap[i])).append("\n");
        }
        return str.toString();
    }
}

