public class Frog extends Mobs{
    public Frog(Tile tile) {
        super(tile);
    }

    public int checkShortest(int[][] map, int frog) {
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
        u = ExtractCheapest(Q);
        S[u] = true;
        for ea vertex i adj to u{
            if (S[i] != true && d[i] > d[u]+1){
                remove i from Q;
                d[i] = d[u] + 1;
                pred[i] = u;
                insert i into Q acc to its d[i];
            }
        }
    }
    
}

