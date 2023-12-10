/**
 *<ol>
 *     <li>File-name: ChipSocket.java</li>
 *     <li>Purpose of the program: Creates the ChipSocket tile  for the game.</li>
 *
 *</ol>
 */
public class ChipSocket extends Tile {


   /**
   * The Amount of Chips the ChipSocket needs to unlock.
   */
  private int ChipAmountNeeded;


  /**
   * Creates a ChipSocket.
   *
   * @param chips The number of chips needed to unlock the chip socket.
   */
  public ChipSocket(int chips) {
      super(false, false);
      this.ChipAmountNeeded = chips;
  }

  /**
  * Gets the amount of chips needed to unlock the chip socket.
  *
  * @return The number of chips needed to unlock the chip socket.
  */
  public int getChipAmountNeeded() {
      return ChipAmountNeeded;
  }

  /**
   * Method that unlocks the chip socket,
   * Should indicate the required amount of chips has been inserted.
   */
  public void Unlock() {
  }

}