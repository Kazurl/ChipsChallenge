public class Dirt extends Tile {
    
    //Attribute:
    private boolean isCompacted;

    //Constructor:
    public Dirt(int x, int y) {
        super(x, y, false);
        this.isCompacted = false; //Initial is false
    }

    //Interact Methods:
    public void interactPlayer(Player player) {
        compact(); //If player walk on top then compact.
    }

    public void interactMob(Mob mob) {
      
        //Condition where, if the dirt isn't compact, then Mobs cannot walk
        if (!isCompacted) { 
            mob.block();
       } else { 
            mob.move(); //If compact then mobs are free to walk on it
       }
    }

    //Other Methods:
    public void compact() {
        isCompacted = true; //Dirt becomes Path.
    }
}
