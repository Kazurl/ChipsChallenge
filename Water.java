public class Water extends Tile {
    
    //Constructor:
    public Water(int x, int y) {
        super(x, y, false); //Cause if true, then interactPlayer cannot happen
    }

    //Methods:
    public void interactPlayer(Player player) {
        
       player.die(); //Player drowns if enter water  
    }

    public void interactBlock(Block block) {
        block.sink(); //Block will sink into the water
        turnToPath(); //Water becomes path
    }

    //Other Method:

    //Method that turns Water to Path
    private void turnToPath(){
        setCollision(true);
    }

}
