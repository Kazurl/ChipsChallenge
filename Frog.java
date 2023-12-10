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
    private boolean isEnd(int row, int col, int playerX, int playerY) {
        return row == playerX && col == playerY;
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


    /**
     * Checks for the shortest path from the frog's current position to a specified destination on the map.
     *
     * @param startx The starting X-coordinate on the map.
     * @param starty The starting Y-coordinate on the map.
     * @param playerx The destination X-coordinate on the map.
     * @param playery The destination Y-coordinate on the map.
     * @return Immediate coordinates in the shortest path on the map.
     */

    public int[] isPath(int startX, int startY, int playerX, int playerY) {
        System.out.println("Player Position: " + "(" + playerX + ", " + playerY + ")" + " | Frog Position: (" + startX + ", " + startY + ")");
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
        for (int i = 0; i< actorLayerMap.length; i++) {
            for (int j = 0; j < actorLayerMap[0].length; j++) {
                parent[i][j] = null;
            }
        }
        Queue<Pair> Q = new LinkedList<>();
        int[][] directions = {{-1,0}, {0,1}, {1,0}, {0, -1}};

        Q.add(new Pair(startX, startY));
        visited[startX][startY] = true;
        parent[startX][startY] = new Pair(-1, -1);
        int destX = Q.peek().Item1 + directions[0][0];
        int destY = Q.peek().Item2 + directions[0][1];

        while (Q.size() > 0) {
            Pair p = (Q.peek());
            Q.remove();
            System.out.println("(destX, destY): (" + destX + ", " + destY + ")");


            if (p.Item1 == playerX && p.Item2 == playerY) {
                int tempX = p.Item1; 
                int tempY = p.Item2;
                // Backtracking to find the path from the player pos
       System.out.println("/nStart Backtracking to find path.");
                while (parent[tempX][tempY].Item1 != startX && parent[tempX][tempY].Item2 != startY) {
                    destX = parent[tempX][tempY].Item1;
                    destY = parent[tempX][tempY].Item2;
                    tempX = destX;
                    tempY = destY;
                    System.out.println("(destX, destY): (" + destX + ", " + destY + ")");

                }
                return new int[] {destX, destY};
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

        return new int[] {destX, destY};
    }

    private boolean walkableActor(int xCoord, int yCoord, Player player) {
        System.out.println("(PlayerX, PlayerY): (" + player.getX() + ", " + player.getY() + ")");
        if (validHeight(xCoord, actorLayerMap.length) && validWidth(yCoord, actorLayerMap[0].length)){
            if ((xCoord == player.getX() && yCoord == player.getY()) || actorLayerMap[xCoord][yCoord] == null) {
                return true;
            }

        }
        return false;
    }
    private boolean walkableTile(int xCoord, int yCoord) {
        if (!(validHeight(xCoord, actorLayerMap.length) && validWidth(yCoord, actorLayerMap[0].length))) return false;
        return tileLayerMap[xCoord][yCoord].getWalkable();
    }
}
