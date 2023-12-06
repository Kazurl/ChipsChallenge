public class Map {

    private int boardWidth;
    private int boardHeight;
    private int timeLeft;

    private Scheduler schedule = new Scheduler();

    private Player playerStored;

    private Block[] blocksStored;
    private Frog[] frogsStored;
    private Bug[] bugsStored;
    private PinkBall[] pinkBallsStored;
    private Actor[][] actorLayerMap;
    private Item[][] itemLayerMap;
    private Tile[][] tileLayerMap;

    public Map(int timeLeft, int width, int height, Actor[][] actorMap, Item[][] itemMap,
               Tile[][] tileMap, Player player, Frog[] frogs, Bug[] bugs, PinkBall[] pinkBalls, Block[] blocks) {
        this.boardWidth = width;
        this.boardHeight = height;
        this.timeLeft = timeLeft;
        this.actorLayerMap = actorMap;
        this.itemLayerMap = itemMap;
        this.tileLayerMap = tileMap;
        this.playerStored = player;
        this.frogsStored = frogs;
        this.bugsStored = bugs;
        this.pinkBallsStored = pinkBalls;
        this.blocksStored = blocks;
    }

    public Player getPlayer() {
        return playerStored;
    }


    public int getTimeLeft() {
        return timeLeft;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public Actor getPosActor(int posX, int posY) {
        return actorLayerMap[posY][posX];
    }

    public Item getPosItem(int posX, int posY) {
        return itemLayerMap[posY][posX];
    }

    public Tile getPosTile(int posX, int posY) {
        return tileLayerMap[posY][posX];
    }

    public void setTimeLeft(int time) {
        this.timeLeft = time;
    }

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

    public void setBoard(Map newMap) {
        this.boardWidth = newMap.boardWidth;
        this.boardHeight = newMap.boardHeight;
        this.actorLayerMap = newMap.actorLayerMap;
        this.tileLayerMap = newMap.tileLayerMap;
        this.itemLayerMap = newMap.itemLayerMap;
        this.timeLeft = newMap.timeLeft;
    }

    public Frog[] getFrogsStored() {
        return frogsStored;
    }

    public PinkBall[] getPinkBallsStored() {
        return pinkBallsStored;
    }

    public Bug[] getBugsStored() {
        return bugsStored;
    }

    public Scheduler getSchedule() {
        return schedule;
    }

    public void setSchedule(Scheduler schedule) {
        this.schedule = schedule;
    }

    public Block[] getBlocksStored() {
        return blocksStored;
    }

    public void setBlocksStored(Block[] blocksStored) {
        this.blocksStored = blocksStored;
    }
}