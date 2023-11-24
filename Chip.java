public class Chip extends Items{

    private int numOfChips;

    public void interactPlayer(Player player){
        player.getInventory();
        numOfChips = numOfChips +1;
        player.addToInventory(numOfChips);
    }
    
}