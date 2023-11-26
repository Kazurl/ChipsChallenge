public class Water extends Tile {
  //Constructor
  public Water(boolean convertPath) {
    super(convertPath, true);
  }
  public Boolean onPlayerWalk; {
  }//PLACEHOLDER FOR BLOCK CLASS  
  public void setWalkable(boolean walk) {
    super.setWalkable(walk);
    if (walk == true) {
      onPlayerWalk = true;
    } else {
      onPlayerWalk = false;
    }
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