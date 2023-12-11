import java.util.Arrays;

/**
 *<ol>
 *     <li>File-name: Map.java</li>
 *     <li>Purpose of the program: Creates a Map for the game.
 *     The map will be created with all the information needed for that level.</li>
 *
 *</ol>
 * @author [Code - Freddie. JavaDoc - Ffi, Enrique]
 */
public class Map {

    /**
     * Map width.
     */
    private int boardWidth;

    /**
     * Map height.
     */
    private int boardHeight;

    /**
     * Level countdown.
     */
    private int timeLeft;

    /**
     * Creates a scheduler for the Map.
     * This keeps track of the timer in the game.
     */
    private Scheduler schedule = new Scheduler();

    /**
     * The Player.
     */
    private Player playerStored;

    /**
     * List of Blocks for the Map.
     */
    private Block[] blocksStored;

    /**
     * List of Frogs for the Map.
     */
    private Frog[] frogsStored;

    /**
     * List of Bugs for the Map.
     */
    private Bug[] bugsStored;

    /**
     * List of Pinkballs for the Map.
     */
    private PinkBall[] pinkBallsStored;

    /**
     * List of Buttons for the Map.
     */
    private Buttons[] buttonsStored;

    /**
     * List of the Top-10 scorers that completed the Map.
     */
    private int[] topScores;

    /**
     * List of Scores for players that completed the Map.
     */
    private int[] newScores;

    /**
     * List of Top-10 players that completed the Map.
     */
    private String[] topNames;

    /**
     * List of Names that completed the Map.
     */
    private String[] newNames;

    /**
     * original (not current save file) file path
     */
    private String original;

    /**
     * Actor Layer for the Map.
     */
    private Actor[][] actorLayerMap;

    /**
     * Item Layer for the Map.
     */
    private Item[][] itemLayerMap;

    /**
     * Tile Layer for the Map.
     */
    private Tile[][] tileLayerMap;

    /**
     * Creates the Map for the Level.
     * @param mapInfo gives the size and time left for the given map
     * [0] = boardWidth
     * [1] = boardHeight
     * [2] = timeLeft
     *
     * @param arraysSaved stores information of where actors are stored for movement
     * [0] = actorLayerMap
     * [1] = itemLayerMap
     * [2] = tileLayerMap
     * [3] = playerStored
     * [4] = frogsStored
     * [5] = bugsStored
     * [6] = pinkBallsStored
     * [7] = blocksStored
     * [8] = buttonsStored
     * @param scores List of Scores that completed the Map.
     * @param names List of Player names that completed the Map.
     * @param original original map in case of save file.
     */
    public Map(int[] mapInfo, Object[] arraysSaved, int[] scores, String[] names, String original) {
        this.boardWidth = mapInfo[0];
        this.boardHeight = mapInfo[1];
        this.timeLeft = mapInfo[2];
        this.actorLayerMap = ((Actor[][]) arraysSaved[0]);
        this.itemLayerMap = ((Item[][]) arraysSaved[1]);
        this.tileLayerMap = ((Tile[][]) arraysSaved[2]);
        this.playerStored = ((Player) arraysSaved[3]);
        this.frogsStored = ((Frog[]) arraysSaved[4]);
        this.bugsStored = ((Bug[]) arraysSaved[5]);
        this.pinkBallsStored = ((PinkBall[]) arraysSaved[6]);
        this.blocksStored = ((Block[]) arraysSaved[7]);
        this.buttonsStored = ((Buttons[]) arraysSaved[8]);
        this.topScores = scores;
        this.topNames = names;
        this.original = original;
    }

    /**
     * Get the Player on the Map.
     *
     * @return The player on the Map.
     */
    public Player getPlayer() {
        return playerStored;
    }

    /**
     * Gets the 2D array of Actor Layer for the Map.
     *
     * @return The Actor Layer.
     */
    public Actor[][] getActorLayerMap() {
        return actorLayerMap;
    }

    /**
     * Gets the 2D array of Tile Layer for the Map.
     *
     * @return The Tile Layer.
     */
    public Tile[][] getTileLayerMap() {
        return tileLayerMap;
    }



    /**
     * Gets the time left for the Player to complete
     * the Map.
     *
     * @return Time left to complete the Map.
     */
    public int getTimeLeft() {
        return timeLeft;
    }

    /**
     * Gets the Map height.
     *
     * @return The Map height.
     */
    public int getBoardHeight() {
        return boardHeight;
    }

    /**
     * Gets the Map width.
     *
     * @return The Map width.
     */
    public int getBoardWidth() {
        return boardWidth;
    }

    /**
     * Gets the Actor at the specified coordinates.
     *
     * @param posX The X-coordinates of the Actor.
     * @param posY The Y-coodinates of the Actor.
     * @return The Actor on the Map.
     */
    public Actor getPosActor(int posX, int posY) {
        return actorLayerMap[posY][posX];
    }

    /**
     * Gets the Item at the specified coordinates.
     *
     * @param posX The X-coordinates of the Item.
     * @param posY The Y-coordinates of the Item.
     * @return The Item on the Map.
     */
    public Item getPosItem(int posX, int posY) {
        return itemLayerMap[posY][posX];
    }

    /**
     * Gets the Tile at the specified coordinates.
     *
     * @param posX The X-coordinates of the Tile.
     * @param posY The Y-coordinates of the Tile.
     * @return The Tile on the Map.
     */
    public Tile getPosTile(int posX, int posY) {
        return tileLayerMap[posY][posX];
    }

    /**
     * Sets the time for the Map to be completed.
     *
     * @param time The time to complete the Map.
     */
    public void setTimeLeft(int time) {
        this.timeLeft = time;
    }

    /**
     * Sets the position of the Actor on the Map.
     *
     * @param posX The X-coordinates of the Actor.
     * @param posY The Y-coordinates of the Actor.
     * @param object The Actor.
     */
    public void setPosActor(int posX, int posY, Actor object) {
        actorLayerMap[posY][posX] = object;
    }

    /**
     * Sets the position of the Item on the Map.
     *
     * @param posX The X-coordinates of the Item.
     * @param posY The Y-coordinates of the Item.
     * @param object The Item.
     */
    public void setPosItem(int posX, int posY, Item object) {
        itemLayerMap[posY][posX] = object;
    }

    /**
     * Sets the position of the Tile on the Map.
     *
     * @param posX The X-coordinates of the Tile.
     * @param posY The Y-coordinates of the Tile.
     * @param object The Tile.
     */
    public void setPosTile(int posX, int posY, Tile object) {
        tileLayerMap[posY][posX] = object;
    }

    /**
     * Gets the specific Map in the game.
     *
     * @return The Map.
     */
    public Map getBoard() {
        return this;
    }

    /**
     * Replaces the current Map with a new one.
     *
     * @param newMap New map that will replace the current one.
     */
    public void setBoard(Map newMap) {
        this.boardWidth = newMap.boardWidth;
        this.boardHeight = newMap.boardHeight;
        this.actorLayerMap = newMap.actorLayerMap;
        this.tileLayerMap = newMap.tileLayerMap;
        this.itemLayerMap = newMap.itemLayerMap;
        this.timeLeft = newMap.timeLeft;
    }

    /**
     * Gets the list of Frogs on the Mpa.
     *
     * @return List of Frogs on the Map.
     */
    public Frog[] getFrogsStored() {
        return frogsStored;
    }

    /**
     * Gets the list of PinkBalls on the Map.
     *
     * @return List of PinkBalls on the Map.
     */
    public PinkBall[] getPinkBallsStored() {
        return pinkBallsStored;
    }

    /**
     * Gets the list of Bugs on the Map.
     *
     * @return List of Bugs on the Map.
     */
    public Bug[] getBugsStored() {
        return bugsStored;
    }

    /**
     * Gets the Scheduler of the Map.
     *
     * @return The Scheduler.
     */
    public Scheduler getSchedule() {
        return schedule;
    }

    /**
     * Sets the Scheduler for the Map.
     *
     * @param schedule The Scheduler.
     */
    public void setSchedule(Scheduler schedule) {
        this.schedule = schedule;
    }

    /**
     * Gets the list of Blocks on the Map.
     *
     * @return List of Blocks on the Map.
     */
    public Block[] getBlocksStored() {
        return blocksStored;
    }

    /**
     * Sets the list of Blocks on the Map.
     *
     * @param blocksStored List of Blocks on the Map.
     */
    public void setBlocksStored(Block[] blocksStored) {
        this.blocksStored = blocksStored;
    }

    /**
     * Gets the list of Buttons on the Map.
     *
     * @return List of Buttons on the Map.
     */
    public Buttons[] getButtonsStored() {
        return buttonsStored;
    }

    /**
     * Sets the list of Buttons on the Map.
     *
     * @param buttonsStored List of Buttons on the Map.
     */
    public void setButtonsStored(Buttons[] buttonsStored) {
        this.buttonsStored = buttonsStored;
    }

    /**
     * Gets the Top-10 scorers of the Map.
     *
     * @return List of Top-10 scorers.
     */
    public int[] getTopScores() {
        return topScores;
    }

    /**
     * Sets a List of Top-10 scorers of the Map.
     *
     * @param scores List of Top-10 scorers of the Map.
     */
    public void setTopScores(int[] scores) {
        topScores = scores;
    }

    /**
     * Returns a score list of Players and their score.
     *
     * @param score The Player's score.
     * @param name The Player's name.
     * @return List of Players and their score.
     */
    public int[] newScore(int score, String name) {
        newScores = Arrays.copyOf(topScores, 10);
        newNames = Arrays.copyOf(topNames, 10);
        for (int i = 0; i < newScores.length; i++) {
            if (score>newScores[i]) {
                for (int x = 9; x > i; x--) {
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

    /**
     * Gets the original map (not the crrent loaded save) file path of the current map
     *
     * @return original map file path
     */
    public String getOriginal() {
        return original;
    }

    /**
     * Gets the Top-10 Names of Players that completed the Map.
     *
     * @return List of Player names.
     */
    public String[] getTopNames() {
        return topNames;
    }

    /**
     * Gets the Names of the Players.
     *
     * @return List of Player names.
     */
    public String[] getNewNames() {
        return newNames;
    }

    /**
     * Gets the new Score for the Player.
     *
     * @return List of new Scores for the Map.
     */
    public int[] getNewScore() {return newScores;}

}
