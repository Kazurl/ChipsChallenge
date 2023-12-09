public class ComputerChip extends Item {
    public ComputerChip(int itemLocationX, int itemLocationY) {
        super(itemLocationX, itemLocationY);
    }
    public void interactPlayer(Player player){
        player.addToInventory("chip");
    }

}