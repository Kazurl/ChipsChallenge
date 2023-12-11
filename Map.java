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
     * IDK what tis is
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
     *
     * @param timeLeft Time left for Player to win the Level.
     * @param width The width of the Map.
     * @param height The height of the Map.
     * @param actorMap Actor Layer for the Map.
     * @param itemMap Item Layer for the Map.
     * @param tileMap Tile Layer for the Map.
     * @param player The Player.
     * @param frogs List of Frogs on the Map.
     * @param bugs List of Bugs on the Map.
     * @param pinkBalls List of PinkBalls on the Map.
     * @param blocks List of Blocks on the Map.
     * @param buttons List of Buttons on the Map.
     * @param scores List of Scores that completed the Map.
     * @param names List of Player names that completed the Map.
     * @param original //IDK what tis is
     */
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

    /**
     * Get the Player on the Map.
     *
     * @return The player on the Map.
     */
    public Player getPlayer() {
        return playerStored;
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
     * Gets the list of Blocks on the Map.
     *
     * @return List of Blocks on the Map.
     */
    public Block[] getBlocksStored() {
        return blocksStored;
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
     * Gets the Top-10 scorers of the Map.
     *
     * @return List of Top-10 scorers.
     */
    public int[] getTopScores() {
        return topScores;
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
     * IDK what tis is.
     *
     * @return
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
