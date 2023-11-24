public class Key extends Item {

    enum Colour {
        RED,
        BLUE,
        YELLOW,
        GREEN,
    }
    private int numOfKeys;
    private Colour keyColour; // Assuming Colour is a class or enum representing colors

    public Key(int itemLocationX, int itemLocationY, Colour keyColour) {
        super(itemLocationX, itemLocationY);
        this.keyColour = keyColour;
    }

    // Getter for keyColour
    public Colour getKeyColour() {
        return keyColour;
    }

    // Method to interact with the player
    public void interactPlayer(Player player) {
        player.getInventory();
        numOfKeys = numOfKeys + 1;
        player.addToInventory(numOfKeys);
    }
}