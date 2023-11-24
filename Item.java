public class Item {

    public int itemLocationX;
    public int itemLocationY;

    public Item(int itemLocationX, int itemLocationY) {
        this.itemLocationX = itemLocationX;
        this.itemLocationY = itemLocationY;
    }

    public void setX(int locationX) {
        this.itemLocationX = locationX;
    }

    public void setY(int locationY) {
        this.itemLocationY = locationY;
    }

    public int getX() {
        return this.itemLocationX;
    }

    public int getY(int locationY) {
        return this.itemLocationY;
    }
    
}