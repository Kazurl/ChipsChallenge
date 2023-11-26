public class ComputerChip extends Item {

    //From Freddie: the number of chips will be saved in the inventory, not here
    //private int numOfChips;

    public ComputerChip(int itemLocationX, int itemLocationY) {
        super(itemLocationX, itemLocationY);
    }
    //From Freddie: I have edited this
    public void interactPlayer(Player player){
        player.addToInventory("chip");
    }
    /*
    public boolean hasPlayerContact() {
        // Implement logic to detect player contact with computer chip
        // Return true if contact occurred, false otherwise
    }
    */
}