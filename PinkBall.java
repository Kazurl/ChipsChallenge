import javafx.scene.input.KeyCode;

/**
 *<ol>
 *     <li>File-name: PinkBall.java</li>
 *     <li>Purpose of the program: Creates a PinkBall for the game,
 *     it extends the Mob class and move around the game world based on player input or game logic.</li>
 *
 *</ol>
 */
public class PinkBall extends Mob {

    /**
     * Creates a default pink ball.
     */
    public PinkBall() {
    }

    /**
     * Creates a PinkBall that will spawn on that specific tile.
     *
     * @param tile The initial tile where PinkBall will spawn.
     */
    public PinkBall(Tile tile) {
        super(tile);
    }
}
