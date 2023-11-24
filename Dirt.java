public class Dirt extends Tile {

    //Constructor
    public Dirt(boolean convertPath) {
      super(convertPath, true);
    }

    public Player onPlayerWalk; {
    }

    public void setWalkable(boolean walk) {
      super.setWalkable(walk);
    if (walk == true) {
      onPlayerWalk = true;

    }
    else {
      onPlayerWalk = false;
    }
    public Mobs onMobWalk; {
      super.setWalkable(walk);
      if (walk == true) {
        onMobWalk = false;
    }
      else {
        onMobWalk = true;
    }
}