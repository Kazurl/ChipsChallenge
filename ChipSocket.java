public class ChipSocket extends Tile {

  private int ChipAmountNeeded;
  //Constructor
  public ChipSocket(int chips) {
      super(false, false);
      this.ChipAmountNeeded = chips;
  }

  public int getChipAmountNeeded() {
      return ChipAmountNeeded;
  }

  public void Unlock() {
  }
}