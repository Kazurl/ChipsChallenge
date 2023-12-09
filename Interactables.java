/**
 *<ol>
 *     <li>File-name: Intractables.java</li>
 *     <li>Purpose of the program: It's an abstract class implements interactions
 *     between different game entities in the game.</li>
 *
 *</ol>
 */
public abstract class Interactables {

    /**
     * The Player.
     */
    protected Player player;

    /**
     * The Mob.
     */
    protected Mob mob;

    /**
     * Interacting Method for Mobs to implement.
     * Implementations should specify the behavior when interacting with mobs.
     *
     * @param mob The mob to interact with.
     */
    public abstract void interactMob(Mob mob); // Implement them in the relevant classes

    /**
     * Interacting Method for Player to implement.
     * Implementations should specify the behavior when interacting with player.
     *
     * @param player The mob to interact with.
     */
    public abstract void interactPlayer(Player player); // Implement them in the relevant classes
}
