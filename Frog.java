import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Arrays;

public class Frog extends Mobs{
    final static int TRAVERSED = 2;
    final static int PATH = 3;
    private int[][] map;

    public Frog(Tile tile) {
        super(tile);
    }

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

    private boolean isEnd(int row, int col, int playerx, int playery) {
        return row == playerx && col == playery;
    }

    private boolean isValid(int row, int col) {
        if (validHeight(row, col) && validWidth(col, col) && isAccessible(row, col) && !isTraversed(row, col)) {
            return true;
        }
        return false;
    }

    private boolean isAccessible(int row, int col) {
        return map[row][col] == 1;
    }

    private boolean isTraversed(int row, int col) {
        return map[row][col] == TRAVERSED;
    }

    private boolean validHeight(int row, int height) {
        return row >= 0 && row < height;
    }

    private boolean validWidth(int col, int width) {
        return col >= 0 && col < width;
    }
}

