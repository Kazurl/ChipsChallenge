/**
 * The Water class represents a tile of type Water, extending the Tile class.
 * It contains information about walkability and events triggered when a player walks on it.
 */
public class Water extends Tile {

    /**
     * Constructor for the Water class.
     *
     * @param convertPath True if the tile should convert to a path, false otherwise.
     */
    public Water(boolean convertPath) {
        super(convertPath, true);
    }

    /**
     * Placeholder for handling events when a player walks on the Water tile.
     */
    public Boolean onPlayerWalk; // PLACEHOLDER FOR BLOCK CLASS

    /**
     * Sets the walkability of the Water tile and handles events when a player walks on it.
     *
     * @param walk True if the tile should be walkable, false otherwise.
     */
    public void setWalkable(boolean walk) {
        super.setWalkable(walk);
        if (walk) {
            onPlayerWalk = true;
        } else {
            onPlayerWalk = false;
        }

        /*
         * Placeholder for handling events when a mob walks on the Water tile.
         */
        /*
        public Mobs onMobWalk;
        {
          super.setWalkable(walk);
          if (walk == true) {
            onMobWalk = false;
          } else {
            onMobWalk = true;
          }
        }
        */
    }
}
