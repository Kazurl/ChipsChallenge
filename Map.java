public class Map {

    private int boardWidth;
    private int boardHeight;
    private int timeLeft;

    private Player playerStored;
    private Frog[] frogsStored; // Getters for individual mob arrays
    private Actor[][] actorLayerMap;
    private Item[][] itemLayerMap;
    private Tile[][] tileLayerMap;

    public Map (int timeLeft,int width, int height, Actor[][] actorMap, Item[][] itemMap, Tile[][] tileMap, Player player){ // 
        this.boardWidth = width;
        this.boardHeight = height;
        this.timeLeft = timeLeft;
        this.actorLayerMap  = actorMap;
        this.itemLayerMap  = itemMap;
        this.tileLayerMap  = tileMap;
        this.playerStored = player;
    }

    public Player getPlayer() { return playerStored; }

    public int getTimeLeft() { return timeLeft; }

    public int getBoardHeight() { return boardHeight; }

    public int getBoardWidth() { return boardWidth; }

    /*public Actor getPosActor(int posX, int posY) {
        return actorLayerMap[posY][posX];
    }

    public Item getPosItem(int posX, int posY) { return itemLayerMap[posY][posX]; }

    public Tile getPosTile(int posX, int posY) {
        return tileLayerMap[posY][posX];
    }*/

    public void setTimeLeft(int time) { this.timeLeft = time; }
    public void setPosActor(int posX, int posY, Actor object) {
                actorLayerMap[posY][posX] = object;
    }

    public void setPosItem(int posX, int posY, Item object) {
        itemLayerMap[posY][posX] = object;
    }

    public void setPosTile(int posX, int posY, Tile object) {
        tileLayerMap[posY][posX] = object;
    }

    public Map getBoard() {
        return this;
    }

    public Actor[][] getActorMap() {
        return this.actorLayerMap;
    }

    public Tile[][] getTileMap() {
        return this.tileLayerMap;
    }

    public Item[][] getItemMap() {
        return itemLayerMap;
    }

    public void setBoard(Map newMap) {
        this.boardWidth = newMap.boardWidth;
        this.boardHeight = newMap.boardHeight;
        this.actorLayerMap = newMap.actorLayerMap;
        this.tileLayerMap = newMap.tileLayerMap;
        this.itemLayerMap = newMap.itemLayerMap;
        this.timeLeft = newMap.timeLeft;
    }
}