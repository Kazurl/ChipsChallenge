public class Key extends Item{

    private int numOfKeys;

    public void interactPlayer(Player player){
        player.getInventory();
        numOfKeys = numOfKeys +1;
        player.addToInventory(numOfKeys);
    }
}