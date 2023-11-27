public class ChipSocket extends Tile {
    
    //Attribute:
    private int requiredChips;
    private int currentNumChip;

    //Constructor:
    public ChipSocket(int x, int y, int requiredChips) {
        super(x, y, true);
        this.requiredChips = requiredChips;
        this.currentNumChip = 0; //Set initial chip num to 0
    }

    //Getter: 
    public int getChips() {
        return this.requiredChips;
    }

    public int getCurrChip() {
        return this.currentNumChip;
    }

    //Setter:
    public void setReqChip(int requiredChips) {
        this.requiredChips = requiredChips;
    }

    public void setCurrChip(int currentNum) {
        this.currentNumChip = currentNum;
    }

    //Interact Methods:
    public void interactPlayer(Player player) {

        //Hit chipsocket, check the currentNum, it the num >= requiredChip then call openSocket
         if (currentNumChip >= requiredChips) {
            openSocket();
         }

         //If not enough then don't change state.
    }

    public void interactMob(Mob mob) {
       
        //Check if Path or not
        if (hasCollision() == true) {
            mob.block();
        } else {
            mob.move();
        }
    }

    //Other method:
    public void openSocket() {

        consumeChip();
        setCollision(false); //Change to Path
    }

    public void consumeChip() {
        
        //Update the numChip
        this.currentNumChip =- requiredChips;
    }

}
