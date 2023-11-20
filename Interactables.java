public abstract class Interactables {

    Player player = new Player();
    Mobs mob = new Mobs(Tile tile);


    public void interactMob(Mobs mob){
        this.mob = mob;
    }

    public void interactPlayer(Player player){
    this.player = player;
    }
}
