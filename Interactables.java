public abstract class Interactables {

    protected Player player;
    protected Mob mob;

    public abstract void interactMob(Mobs mob); // Implement them in the relevant classes

    public abstract void interactPlayer(Player player); // Implement them in the relevant classes
}
