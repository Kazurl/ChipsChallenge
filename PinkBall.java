import javafx.scene.input.KeyCode;

/**
 * The PinkBall class represents a type of mobile entity in the game, extending the Mob class.
 * PinkBall entities can move around the game world based on player input or game logic.
 */
public class PinkBall extends Mob {

    /**
     * Default constructor for the PinkBall class.
     */
    public PinkBall() {
    }

    /**
     * Constructor for the PinkBall class with an initial tile.
     *
     * @param tile The initial tile on which the PinkBall is placed.
     */
    public PinkBall(Tile tile) {
        super(tile);
    }
}
