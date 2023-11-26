public class Key extends Item {

    enum Colour {
        RED,
        BLUE,
        YELLOW,
        GREEN,
    }
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
        switch(this.keyColour){
            case RED:
                player.addToInventory("red key");
                break;
            case GREEN:
                player.addToInventory("green key");
                break;
            case BLUE:
                player.addToInventory("blue key");
                break;
            case YELLOW:
                player.addToInventory("yellow key");
                break;
        }
    }
}