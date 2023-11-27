public class Exit extends Tile {

    //Attribute:
    private boolean levelState; //If true, then level won. Else false.

    //Constructor:
    public Exit(int x, int y) {
        super(x, y, false); 
        this.levelState = false; //Set level hasn't won.
    }

    //Interact Methods:
    public void interactPlayer(Player player) {

        //Condition if time not 0, then change levelState as true. Level won.
        //IDK this part
        //Put Main.exitGame() or something here.

    }

    public void interactMob(Mob mob) {
        mob.block();
    }

    //For scheduler to stop time if level won
    public boolean getState() {
        return this.levelState;
    }

    //Change state, means level won
    public boolean changeState() {
        return this.levelState = true;
    }

}
