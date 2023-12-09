/**
 *<ol>
 *     <li>File-name: Bug.java</li>
 *     <li>Purpose of the program: Creates the Bug for the game, it inherits from the Actor Ckass.</li>
 *
 *</ol>
 */
public class Bug extends Mob {

    /**
     * Checks if the Bug will stick to the left.
     */
    private boolean followLeft;

    /**
     * Creates the bug for the game.
     * This was used for testing.
     */
    public Bug() {
    }

    /**
     * Sets the direction for the bug to follow.
     *
     * @param left will be True if the bug should follow Left side,
     *             False otherwise .
     */
    public void setFollow(boolean left) {
        this.followLeft = left;
    }

    /**
     * Gets the direction the bug is set to follow.
     *
     * @return True if the bug should follow left, False if otherwise.
     */
    public boolean getFollow() {
        return this.followLeft;
    }

    /**
     * Constructs a Bug that will spawn on the specified tile.
     *
     * @param tile The starting tile for the Bug.
     */
    public Bug(Tile tile) {
        super(tile);
    }
}