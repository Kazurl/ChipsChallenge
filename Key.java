public class Keys extends Items{
    private int numOfKeys;

    public void interactPlayer(Player player){
        player.getInventory();
        numOfKeys = numOfKeys +1;
        player.addToInventory(numOfKeys);
    }
}