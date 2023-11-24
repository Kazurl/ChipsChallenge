public class ComputerChip extends Item {

    private int numOfChips;

    public void interactPlayer(Player player){
        player.getInventory();
        numOfChips = numOfChips +1;
        player.addToInventory(numOfChips);
    }
    public boolean hasPlayerContact() {
        // Implement logic to detect player contact with computer chip
        // Return true if contact occurred, false otherwise
    }
}