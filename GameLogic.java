import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameLogic {
    private static Map gameMap;

    private static boolean gameWon = false;
    public static void hasDied(KeyCode direction, String how) {
        if(how == "water") {
            //play drown animation
            //end game
        } else {
            //play death animation
            //end game
        }
    }
    public static boolean checkStatus() {
        Item currentItem = gameMap.getPosItem(gameMap.getPlayer().getX(), gameMap.getPlayer().getY());
        if (currentItem instanceof ComputerChip) {
            gameMap.getPlayer().addToInventory("chip");
            gameMap.setPosItem(currentItem.getX(), currentItem.getY(), null);
        } else if (currentItem instanceof Key) {
            if (((Key) currentItem).getKeyColour() == Key.Colour.RED) {
                gameMap.getPlayer().addToInventory("red key");
                gameMap.setPosItem(currentItem.getX(), currentItem.getY(), null);
            } else if (((Key) currentItem).getKeyColour() == Key.Colour.GREEN) {
                gameMap.getPlayer().addToInventory("green key");
                gameMap.setPosItem(currentItem.getX(), currentItem.getY(), null);
            } else if (((Key) currentItem).getKeyColour() == Key.Colour.BLUE) {
                gameMap.getPlayer().addToInventory("blue key");
                gameMap.setPosItem(currentItem.getX(), currentItem.getY(), null);
            } else if (((Key) currentItem).getKeyColour() == Key.Colour.YELLOW) {
                gameMap.getPlayer().addToInventory("yellow key");
                gameMap.setPosItem(currentItem.getX(), currentItem.getY(), null);
            }
        }
        if(gameMap.getPosTile(gameMap.getPlayer().getX(), gameMap.getPlayer().getY()) instanceof Exit) {
            setGameWon(true);
            return true;
        } else return gameMap.getPosTile(gameMap.getPlayer().getX(), gameMap.getPlayer().getY()) instanceof Water
                || gameMap.getPosActor(gameMap.getPlayer().getX(), gameMap.getPlayer().getY()) instanceof Mob;
    }

    public static void setGameWon(boolean gameWon) {
        GameLogic.gameWon = gameWon;
    }
    public static boolean getGameWon() {
        return gameWon;
    }

    public static void movePlayer(KeyEvent event) {
        if(!(event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.UP)) {
            return;
        }
        if(checkPlayerMove(event.getCode())){
            switch(event.getCode()) {
                case LEFT:
                    gameMap.getPlayer().setX(gameMap.getPlayer().getX()-1);
                    gameMap.setPosActor(gameMap.getPlayer().getX(),gameMap.getPlayer().getY(), null);
                    gameMap.setPosActor(gameMap.getPlayer().getX(),gameMap.getPlayer().getY(), gameMap.getPlayer());
                    break;
                case RIGHT:
                    gameMap.getPlayer().setX(gameMap.getPlayer().getX()+1);
                    gameMap.setPosActor(gameMap.getPlayer().getX(),gameMap.getPlayer().getY(), null);
                    gameMap.setPosActor(gameMap.getPlayer().getX(),gameMap.getPlayer().getY(), gameMap.getPlayer());
                    break;
                case UP:
                    gameMap.getPlayer().setY(gameMap.getPlayer().getY()-1);
                    gameMap.setPosActor(gameMap.getPlayer().getX(),gameMap.getPlayer().getY(), null);
                    gameMap.setPosActor(gameMap.getPlayer().getX(),gameMap.getPlayer().getY(), gameMap.getPlayer());
                    break;
                case DOWN:
                    gameMap.getPlayer().setY(gameMap.getPlayer().getY()+1);
                    gameMap.setPosActor(gameMap.getPlayer().getX(),gameMap.getPlayer().getY(), null);
                    gameMap.setPosActor(gameMap.getPlayer().getX(),gameMap.getPlayer().getY(), gameMap.getPlayer());
                    break;
            }
        }
    }

    public static boolean checkPlayerMove(KeyCode direction) {
        int playerX = gameMap.getPlayer().getX();
        int playerY = gameMap.getPlayer().getY();
        if(direction == KeyCode.LEFT) {
            if(playerX == 0) {
                return false;
            } else if (gameMap.getPosActor(playerX-1,playerY) instanceof Mob) {
                hasDied(direction, "mob");
                return false;
            } else if (gameMap.getPosTile(playerX-1,playerY) instanceof Water) {
                hasDied(direction, "water");
                return false;
            } else if (gameMap.getPosTile(playerX-1,playerY) instanceof Ice
                    && (((Ice) gameMap.getPosTile(playerX-1,playerY)).getCornerType() == Ice.CornerType.TOP_RIGHT
                    || ((Ice) gameMap.getPosTile(playerX-1,playerY)).getCornerType() == Ice.CornerType.BOTTOM_RIGHT)) {
                return false;
            } else if (gameMap.getPosTile(playerX-1,playerY) instanceof LockedDoor
                    && gameMap.getPlayer().useKey(((LockedDoor) gameMap.getPosTile(playerX-1,playerY)).getDoorColour())) {
                gameMap.setPosTile(playerX-1,playerY, new Path());
                return true;
            } else if (gameMap.getPosTile(playerX-1,playerY) instanceof ChipSocket
                    && gameMap.getPlayer().useChips(((ChipSocket) gameMap.getPosTile(playerX-1,playerY)).getChipAmountNeeded())) {
                gameMap.setPosTile(playerX-1,playerY, new Path());
                return true;
            } else if (gameMap.getPosTile(playerX-1,playerY) instanceof Dirt) {
                gameMap.setPosTile(playerX-1,playerY, new Path());
                return true;
            } else {
                return gameMap.getPosTile(playerX-1,playerY).getWalkable();
            }
        } else if(direction == KeyCode.RIGHT) {
            if(playerX == gameMap.getBoardWidth()-1) {
                return false;
            } else if (gameMap.getPosActor(playerX+1,playerY) instanceof Mob) {
                hasDied(direction, "mob");
                return false;
            } else if (gameMap.getPosTile(playerX+1,playerY) instanceof Water) {
                hasDied(direction, "water");
                return false;
            } else if (gameMap.getPosTile(playerX+1,playerY) instanceof Ice
                    && (((Ice) gameMap.getPosTile(playerX+1,playerY)).getCornerType() == Ice.CornerType.TOP_LEFT
                    || ((Ice) gameMap.getPosTile(playerX+1,playerY)).getCornerType() == Ice.CornerType.BOTTOM_LEFT)) {
                return false;
            } else if (gameMap.getPosTile(playerX+1,playerY) instanceof LockedDoor
                    && gameMap.getPlayer().useKey(((LockedDoor) gameMap.getPosTile(playerX+1,playerY)).getDoorColour())) {
                gameMap.setPosTile(playerX+1,playerY, new Path());
                return true;
            } else if (gameMap.getPosTile(playerX+1,playerY) instanceof ChipSocket
                    && gameMap.getPlayer().useChips(((ChipSocket) gameMap.getPosTile(playerX+1,playerY)).getChipAmountNeeded())) {
                gameMap.setPosTile(playerX+1,playerY, new Path());
                return true;
            } else if (gameMap.getPosTile(playerX+1,playerY) instanceof Dirt) {
                gameMap.setPosTile(playerX+1,playerY, new Path());
                return true;
            } else {
                return gameMap.getPosTile(playerX+1,playerY).getWalkable();
            }
        } else if(direction == KeyCode.UP) {
            if(playerY == 0) {
                return false;
            } else if (gameMap.getPosActor(playerX,playerY-1) instanceof Mob) {
                hasDied(direction, "mob");
                return false;
            } else if (gameMap.getPosTile(playerX,playerY-1) instanceof Water) {
                hasDied(direction, "water");
                return false;
            } else if (gameMap.getPosTile(playerX,playerY-1) instanceof Ice
                    && (((Ice) gameMap.getPosTile(playerX,playerY-1)).getCornerType() == Ice.CornerType.BOTTOM_RIGHT
                    || ((Ice) gameMap.getPosTile(playerX,playerY-1)).getCornerType() == Ice.CornerType.BOTTOM_LEFT)) {
                return false;
            } else if (gameMap.getPosTile(playerX,playerY-1) instanceof LockedDoor
                    && gameMap.getPlayer().useKey(((LockedDoor) gameMap.getPosTile(playerX,playerY-1)).getDoorColour())) {
                gameMap.setPosTile(playerX,playerY-1, new Path());
                return true;
            } else if (gameMap.getPosTile(playerX,playerY-1) instanceof ChipSocket
                    && gameMap.getPlayer().useChips(((ChipSocket) gameMap.getPosTile(playerX,playerY-1)).getChipAmountNeeded())) {
                gameMap.setPosTile(playerX,playerY-1, new Path());
                return true;
            } else if (gameMap.getPosTile(playerX,playerY-1) instanceof Dirt) {
                gameMap.setPosTile(playerX,playerY-1, new Path());
                return true;
            } else {
                return gameMap.getPosTile(playerX,playerY-1).getWalkable();
            }
        } else if(direction == KeyCode.DOWN) {
            if(playerY == gameMap.getBoardHeight()-1) {
                return false;
            } else if (gameMap.getPosActor(playerX,playerY+1) instanceof Mob) {
                hasDied(direction, "mob");
                return false;
            } else if (gameMap.getPosTile(playerX,playerY+1) instanceof Water) {
                hasDied(direction, "water");
                return false;
            } else if (gameMap.getPosTile(playerX,playerY+1) instanceof Ice
                    && (((Ice) gameMap.getPosTile(playerX,playerY+1)).getCornerType() == Ice.CornerType.TOP_RIGHT
                    || ((Ice) gameMap.getPosTile(playerX,playerY+1)).getCornerType() == Ice.CornerType.TOP_LEFT)) {
                return false;
            } else if (gameMap.getPosTile(playerX,playerY+1) instanceof LockedDoor
                    && gameMap.getPlayer().useKey(((LockedDoor) gameMap.getPosTile(playerX,playerY+1)).getDoorColour())) {
                gameMap.setPosTile(playerX,playerY+1, new Path());
                return true;
            } else if (gameMap.getPosTile(playerX,playerY+1) instanceof ChipSocket
                    && gameMap.getPlayer().useChips(((ChipSocket) gameMap.getPosTile(playerX,playerY+1)).getChipAmountNeeded())) {
                gameMap.setPosTile(playerX,playerY+1, new Path());
                return true;
            } else if (gameMap.getPosTile(playerX,playerY+1) instanceof Dirt) {
                gameMap.setPosTile(playerX,playerY+1, new Path());
                return true;
            } else {
                return gameMap.getPosTile(playerX,playerY+1).getWalkable();
            }
        }
        return true;
    }
    public static Map getGameMap() {
        return gameMap;
    }

    public static void setGameMap(Map gameMap) {
        GameLogic.gameMap = gameMap;
    }
}
