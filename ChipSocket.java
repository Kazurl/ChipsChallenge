public class ChipSocket extends Tile {

    //Constructor
    public ChipSocket() {
        super(true, true);
    }

  //Constructor
  public ChipSocket(boolean convertPath) {
    super(convertPath, true);
  }
  
  public void setWalkable(boolean walk) {
    super.setWalkable(walk && ComputerChip.isTrue());
  }
}