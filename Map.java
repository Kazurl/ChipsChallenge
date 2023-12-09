import java.util.Arrays;

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
    private Buttons[] buttonsStored;
    private int[] topScores;
    private int[] newScores;
    private String[] topNames;
    private String[] newNames;
    private String original;
    private Actor[][] actorLayerMap;
    private Item[][] itemLayerMap;
    private Tile[][] tileLayerMap;

    public Map(int timeLeft, int width, int height, Actor[][] actorMap, Item[][] itemMap,
               Tile[][] tileMap, Player player, Frog[] frogs, Bug[] bugs, PinkBall[] pinkBalls, Block[] blocks,
               Buttons[] buttons, int[] scores, String[] names, String original) {
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
        this.buttonsStored = buttons;
        this.topScores = scores;
        this.topNames = names;
        this.original = original;
    }

    public Player getPlayer() {
        return playerStored;
    }

    public Actor[][] getActorLayerMap() {
        return actorLayerMap;
    }

    public Tile[][] getTileLayerMap() {
        return tileLayerMap;
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

    public Buttons[] getButtonsStored() {
        return buttonsStored;
    }

    public void setButtonsStored(Buttons[] buttonsStored) {
        this.buttonsStored = buttonsStored;
    }

    public int[] getTopScores() {
        return topScores;
    }
    public void setTopScores(int[] scores) {
        topScores = scores;
    }
    public int[] newScore(int score, String name) {
        newScores = Arrays.copyOf(topScores, 10);
        newNames = Arrays.copyOf(topNames, 10);
        for(int i = 0; i < newScores.length; i++) {
            if(score>newScores[i]) {
                for(int x = 9; x > i; x--) {
                    newScores[x] = newScores[x-1];
                    newNames[x] = newNames[x-1];
                }
                newNames[i] = name;
                newScores[i] = score;
                break;
            }
        }
        return newScores;
    }

    public String getOriginal() {
        return original;
    }

    public String[] getTopNames() {
        return topNames;
    }
    public String[] getNewNames() {
        return newNames;
    }
    public int[] getNewScore() {return newScores;}

}