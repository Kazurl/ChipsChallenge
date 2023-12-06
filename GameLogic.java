import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameLogic {
    private static Map gameMap;
    private static KeyCode nextMove;

    private static boolean gameWon = false;
    /**
     * Marks the player as dead based on the cause of death.
     *
     * @param cause The cause of death (e.g., "water").
     */
    public static void hasDied(String how) {
        gameMap.getPlayer().setAlive(false);
        if(how == "water") {
            //play drown animation
            //end game
        } else {
            //play death animation
            //end game
        }
    }
    /**
     * Checks the current status of the game, including the player's condition, collected items,
     * and whether the player has reached the exit.
     *
     * @return True if the game has ended, false otherwise.
     */
    public static boolean checkStatus() {
        if(!gameMap.getPlayer().isAlive){
            return true;
        }
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

    /**
     * Sets the game's win status.
     *
     * @param gameWon True if the game is won, false otherwise.
     */
    public static void setGameWon(boolean gameWon) {
        GameLogic.gameWon = gameWon;
    }
    /**
     * Gets the current win status of the game.
     *
     * @return True if the game is won, false otherwise.
     */
    public static boolean getGameWon() {
        return gameWon;
    }
    
    /**
     * Moves the player in the specified direction on the game map.
     *
     * @param direction The direction in which the player should move (LEFT, RIGHT, UP, or DOWN).
     * @return True if the player can move in the specified direction, false otherwise.
     */
    public static boolean movePlayer(KeyCode direction) {
        // Check if the provided direction is valid
        if(!(direction == KeyCode.LEFT || direction == KeyCode.RIGHT || direction == KeyCode.DOWN || direction == KeyCode.UP)) {
            return false;
        }
        // Check if the player can move in the specified direction
        if(checkPlayerMove(direction)){
            // Move the player based on the specified direction
            switch(direction) {
                case LEFT:
                    gameMap.setPosActor(gameMap.getPlayer().getX(),gameMap.getPlayer().getY(), null);
                    gameMap.getPlayer().setX(gameMap.getPlayer().getX()-1);
                    gameMap.setPosActor(gameMap.getPlayer().getX(),gameMap.getPlayer().getY(), gameMap.getPlayer());
                    gameMap.getPlayer().setDirection(direction);
                    return true;
                case RIGHT:
                    gameMap.setPosActor(gameMap.getPlayer().getX(),gameMap.getPlayer().getY(), null);
                    gameMap.getPlayer().setX(gameMap.getPlayer().getX()+1);
                    gameMap.setPosActor(gameMap.getPlayer().getX(),gameMap.getPlayer().getY(), gameMap.getPlayer());
                    gameMap.getPlayer().setDirection(direction);
                    return true;
                case UP:
                    gameMap.setPosActor(gameMap.getPlayer().getX(),gameMap.getPlayer().getY(), null);
                    gameMap.getPlayer().setY(gameMap.getPlayer().getY()-1);
                    gameMap.setPosActor(gameMap.getPlayer().getX(),gameMap.getPlayer().getY(), gameMap.getPlayer());
                    gameMap.getPlayer().setDirection(direction);
                    return true;
                default:
                    gameMap.setPosActor(gameMap.getPlayer().getX(),gameMap.getPlayer().getY(), null);
                    gameMap.getPlayer().setY(gameMap.getPlayer().getY()+1);
                    gameMap.setPosActor(gameMap.getPlayer().getX(),gameMap.getPlayer().getY(), gameMap.getPlayer());
                    gameMap.getPlayer().setDirection(direction);
                    return true;
            }
        }
        return false;
    }
    /**
     * Checks if the player can move in the specified direction on the game map.
     *
     * @param direction The direction in which the player wants to move (LEFT, RIGHT, UP, or DOWN).
     * @return True if the player can move in the specified direction, false otherwise.
     */
    public static boolean checkPlayerMove(KeyCode direction) {
        int playerX = gameMap.getPlayer().getX();
        int playerY = gameMap.getPlayer().getY();
        
        // Check if the provided direction is LEFT
        if(direction == KeyCode.LEFT) {
            if(playerX == 0) {
                return false;// Player is at the left edge of the board
            } else if (gameMap.getPosActor(playerX-1,playerY) instanceof Block) {
                return blockMove((Block) gameMap.getPosActor(playerX - 1, playerY), direction);
            } else if (gameMap.getPosActor(playerX-1,playerY) instanceof Mob) {
                hasDied("mob");
                return false;// Player encounters a Mob
            } else if (gameMap.getPosTile(playerX-1,playerY) instanceof Water) {
                hasDied("water");
                return true;// Player encounters Water
            } else if (gameMap.getPosTile(playerX-1,playerY) instanceof Ice
                    && (((Ice) gameMap.getPosTile(playerX-1,playerY)).getCornerType() == Ice.CornerType.TOP_RIGHT
                    || ((Ice) gameMap.getPosTile(playerX-1,playerY)).getCornerType() == Ice.CornerType.BOTTOM_RIGHT)) {
                return false;// Player encounters Ice with incorrect movement direction
            } else if (gameMap.getPosTile(playerX-1,playerY) instanceof LockedDoor
                    && gameMap.getPlayer().useKey(((LockedDoor) gameMap.getPosTile(playerX-1,playerY)).getDoorColour())) {
                gameMap.setPosTile(playerX-1,playerY, new Path());
                return true;// Player unlocks a LockedDoor
            } else if (gameMap.getPosTile(playerX-1,playerY) instanceof ChipSocket
                    && gameMap.getPlayer().useChips(((ChipSocket) gameMap.getPosTile(playerX-1,playerY)).getChipAmountNeeded())) {
                gameMap.setPosTile(playerX-1,playerY, new Path());
                return true;// Player uses Chips on a ChipSocket
            } else if (gameMap.getPosTile(playerX-1,playerY) instanceof Dirt) {
                gameMap.setPosTile(playerX-1,playerY, new Path());
                return true; // Player encounters Dirt
            } else {
                return gameMap.getPosTile(playerX-1,playerY).getWalkable();
            }
            // Check if the provided direction is RIGHT
        } else if(direction == KeyCode.RIGHT) {
            if(playerX == gameMap.getBoardWidth()-1) {
                return false;// Player is at the right edge of the board
            } else if (gameMap.getPosActor(playerX+1,playerY) instanceof Block) {
                return blockMove((Block) gameMap.getPosActor(playerX + 1, playerY), direction);
            } else if (gameMap.getPosActor(playerX+1,playerY) instanceof Mob) {
                hasDied("mob");
                return false;// Player encounters a Mob
            } else if (gameMap.getPosTile(playerX+1,playerY) instanceof Water) {
                hasDied("water");
                return true;// Player encounters Water
            } else if (gameMap.getPosTile(playerX+1,playerY) instanceof Ice
                    && (((Ice) gameMap.getPosTile(playerX+1,playerY)).getCornerType() == Ice.CornerType.TOP_LEFT
                    || ((Ice) gameMap.getPosTile(playerX+1,playerY)).getCornerType() == Ice.CornerType.BOTTOM_LEFT)) {
                return false;// Player encounters Ice with incorrect movement direction
            } else if (gameMap.getPosTile(playerX+1,playerY) instanceof LockedDoor
                    && gameMap.getPlayer().useKey(((LockedDoor) gameMap.getPosTile(playerX+1,playerY)).getDoorColour())) {
                gameMap.setPosTile(playerX+1,playerY, new Path());
                return true;// Player unlocks a LockedDoor
            } else if (gameMap.getPosTile(playerX+1,playerY) instanceof ChipSocket
                    && gameMap.getPlayer().useChips(((ChipSocket) gameMap.getPosTile(playerX+1,playerY)).getChipAmountNeeded())) {
                gameMap.setPosTile(playerX+1,playerY, new Path());
                return true;// Player uses Chips on a ChipSocket
            } else if (gameMap.getPosTile(playerX+1,playerY) instanceof Dirt) {
                gameMap.setPosTile(playerX+1,playerY, new Path());
                return true;// Player encounters Dirt
            } else {
                return gameMap.getPosTile(playerX+1,playerY).getWalkable();
            }
            // Check if the provided direction is UP
        } else if(direction == KeyCode.UP) {
            if(playerY == 0) {
                return false;// Player is at the top edge of the board
            } else if (gameMap.getPosActor(playerX,playerY-1) instanceof Block) {
                return blockMove((Block) gameMap.getPosActor(playerX, playerY -1), direction);
            } else if (gameMap.getPosActor(playerX,playerY-1) instanceof Mob) {
                hasDied("mob");
                return false;// Player encounters a Mob
            } else if (gameMap.getPosTile(playerX,playerY-1) instanceof Water) {
                hasDied("water");
                return true;// Player encounters Water
            } else if (gameMap.getPosTile(playerX,playerY-1) instanceof Ice
                    && (((Ice) gameMap.getPosTile(playerX,playerY-1)).getCornerType() == Ice.CornerType.BOTTOM_RIGHT
                    || ((Ice) gameMap.getPosTile(playerX,playerY-1)).getCornerType() == Ice.CornerType.BOTTOM_LEFT)) {
                return false;// Player encounters Ice with incorrect movement direction
            } else if (gameMap.getPosTile(playerX,playerY-1) instanceof LockedDoor
                    && gameMap.getPlayer().useKey(((LockedDoor) gameMap.getPosTile(playerX,playerY-1)).getDoorColour())) {
                gameMap.setPosTile(playerX,playerY-1, new Path());
                return true;// Player unlocks a LockedDoor
            } else if (gameMap.getPosTile(playerX,playerY-1) instanceof ChipSocket
                    && gameMap.getPlayer().useChips(((ChipSocket) gameMap.getPosTile(playerX,playerY-1)).getChipAmountNeeded())) {
                gameMap.setPosTile(playerX,playerY-1, new Path());
                return true;// Player uses Chips on a ChipSocket
            } else if (gameMap.getPosTile(playerX,playerY-1) instanceof Dirt) {
                gameMap.setPosTile(playerX,playerY-1, new Path());
                return true;// Player encounters Dirt
            } else {
                return gameMap.getPosTile(playerX,playerY-1).getWalkable();
            }
            // Check if the provided direction is DOWN
        } else if(direction == KeyCode.DOWN) {
            if(playerY == gameMap.getBoardHeight()-1) {
                return false;// Player is at the buttom edge of the board
            } else if (gameMap.getPosActor(playerX,playerY+1) instanceof Block) {
                return blockMove((Block) gameMap.getPosActor(playerX, playerY +1), direction);
            } else if (gameMap.getPosActor(playerX,playerY+1) instanceof Mob) {
                hasDied("mob");
                return false;// Player encounters a Mob
            } else if (gameMap.getPosTile(playerX,playerY+1) instanceof Water) {
                hasDied("water");
                return true;// Player encounters Water
            } else if (gameMap.getPosTile(playerX,playerY+1) instanceof Ice
                    && (((Ice) gameMap.getPosTile(playerX,playerY+1)).getCornerType() == Ice.CornerType.TOP_RIGHT
                    || ((Ice) gameMap.getPosTile(playerX,playerY+1)).getCornerType() == Ice.CornerType.TOP_LEFT)) {
                return false;// Player encounters Ice with incorrect movement direction
            } else if (gameMap.getPosTile(playerX,playerY+1) instanceof LockedDoor
                    && gameMap.getPlayer().useKey(((LockedDoor) gameMap.getPosTile(playerX,playerY+1)).getDoorColour())) {
                gameMap.setPosTile(playerX,playerY+1, new Path());
                return true;// Player unlocks a LockedDoor
            } else if (gameMap.getPosTile(playerX,playerY+1) instanceof ChipSocket
                    && gameMap.getPlayer().useChips(((ChipSocket) gameMap.getPosTile(playerX,playerY+1)).getChipAmountNeeded())) {
                gameMap.setPosTile(playerX,playerY+1, new Path());
                return true;// Player uses Chips on a ChipSocket
            } else if (gameMap.getPosTile(playerX,playerY+1) instanceof Dirt) {
                gameMap.setPosTile(playerX,playerY+1, new Path());
                return true;// Player encounters Dirt
            } else {
                return gameMap.getPosTile(playerX,playerY+1).getWalkable();
            }
        }
        return true;
    }

    /**
     * Attempts to move the specified block in the given direction on the game map.
     *
     * @param block The block to be moved.
     * @param direction The direction in which the block should be moved (LEFT, RIGHT, UP, or DOWN).
     * @return True if the block is successfully moved, false otherwise.
     */
    public static boolean blockMove(Block block, KeyCode direction) {
        int blockX = block.getLocationX();
        int blockY = block.getLocationY();
        block.setDirection(direction);
        if (direction == KeyCode.LEFT) {
            if (blockX == 0) {
                return false;
            } else if(gameMap.getPosActor(blockX - 1, blockY) instanceof Player) {
                hasDied("block");
                gameMap.setPosActor(blockX, blockY, null);
                block.setLocation(blockX-1, blockY);
                gameMap.setPosActor(blockX-1, blockY, block);
                return true;
            } else if (gameMap.getPosActor(blockX - 1, blockY) instanceof Block) {
                if(blockMove((Block) gameMap.getPosActor(blockX - 1, blockY), direction)) {
                    gameMap.setPosActor(blockX, blockY, null);
                    block.setLocation(blockX-1, blockY);
                    gameMap.setPosActor(blockX-1, blockY, block);
                    return true;
                } else {
                    return false;
                }
            } else if (gameMap.getPosActor(blockX - 1, blockY) instanceof Mob) {
                return false;
            } else if (gameMap.getPosTile(blockX - 1, blockY) instanceof Water) {
                convertWaterToPath(blockX, blockY, blockX-1, blockY);
                return true;
            } else if (gameMap.getPosTile(blockX - 1, blockY) instanceof Ice
                    && (((Ice) gameMap.getPosTile(blockX - 1, blockY)).getCornerType() == Ice.CornerType.TOP_RIGHT
                    || ((Ice) gameMap.getPosTile(blockX - 1, blockY)).getCornerType() == Ice.CornerType.BOTTOM_RIGHT)) {
                return false;
            } else {
                if (gameMap.getPosTile(blockX - 1, blockY).getPushable()) {
                    gameMap.setPosActor(blockX, blockY, null);
                    block.setLocation(blockX-1, blockY);
                    gameMap.setPosActor(blockX-1, blockY, block);
                    return true;
                } else {
                    return false;
                }
            }
        } else if (direction == KeyCode.RIGHT) {
            if (blockX == gameMap.getBoardWidth()-1) {
                return false;
            } else if(gameMap.getPosActor(blockX + 1, blockY) instanceof Player) {
                hasDied("block");
                gameMap.setPosActor(blockX, blockY, null);
                block.setLocation(blockX+1, blockY);
                gameMap.setPosActor(blockX+1, blockY, block);
                return true;
            } else if (gameMap.getPosActor(blockX + 1, blockY) instanceof Block) {
                if(blockMove((Block) gameMap.getPosActor(blockX + 1, blockY), direction)) {
                    gameMap.setPosActor(blockX, blockY, null);
                    block.setLocation(blockX+1, blockY);
                    gameMap.setPosActor(blockX+1, blockY, block);
                    return true;
                } else {
                    return false;
                }
            } else if (gameMap.getPosActor(blockX + 1, blockY) instanceof Mob) {
                return false;
            } else if (gameMap.getPosTile(blockX + 1, blockY) instanceof Water) {
                convertWaterToPath(blockX, blockY, blockX+1, blockY);
                return true;
            } else if (gameMap.getPosTile(blockX + 1, blockY) instanceof Ice
                    && (((Ice) gameMap.getPosTile(blockX + 1, blockY)).getCornerType() == Ice.CornerType.TOP_LEFT
                    || ((Ice) gameMap.getPosTile(blockX + 1, blockY)).getCornerType() == Ice.CornerType.BOTTOM_LEFT)) {
                return false;
            } else {
                if (gameMap.getPosTile(blockX + 1, blockY).getPushable()) {
                    gameMap.setPosActor(blockX, blockY, null);
                    block.setLocation(blockX+1, blockY);
                    gameMap.setPosActor(blockX+1, blockY, block);
                    return true;
                } else {
                    return false;
                }
            }
        } else if (direction == KeyCode.UP) {
            if (blockY == 0) {
                return false;
            } else if(gameMap.getPosActor(blockX, blockY -1) instanceof Player) {
                hasDied("block");
                gameMap.setPosActor(blockX, blockY, null);
                block.setLocation(blockX+1, blockY);
                gameMap.setPosActor(blockX+1, blockY, block);
                return true;
            } else if (gameMap.getPosActor(blockX, blockY -1) instanceof Block) {
            if(blockMove((Block) gameMap.getPosActor(blockX, blockY -1), direction)) {
                gameMap.setPosActor(blockX, blockY, null);
                block.setLocation(blockX, blockY-1);
                gameMap.setPosActor(blockX, blockY-1, block);
                return true;
            } else {
                return false;
            }
        } else if (gameMap.getPosActor(blockX, blockY - 1) instanceof Mob) {
                return false;
            } else if (gameMap.getPosTile(blockX, blockY - 1) instanceof Water) {
                convertWaterToPath(blockX, blockY, blockX, blockY-1);
                return true;
            } else if (gameMap.getPosTile(blockX, blockY - 1) instanceof Ice
                    && (((Ice) gameMap.getPosTile(blockX, blockY - 1)).getCornerType() == Ice.CornerType.BOTTOM_RIGHT
                    || ((Ice) gameMap.getPosTile(blockX, blockY - 1)).getCornerType() == Ice.CornerType.BOTTOM_LEFT)) {
                return false;
            } else {
                if (gameMap.getPosTile(blockX, blockY-1).getPushable()) {
                    gameMap.setPosActor(blockX, blockY, null);
                    block.setLocation(blockX, blockY-1);
                    gameMap.setPosActor(blockX, blockY-1, block);
                    return true;
                } else {
                    return false;
                }
            }
        } else if (direction == KeyCode.DOWN) {
            if (blockY == gameMap.getBoardHeight() - 1) {
                return false;
            } else if(gameMap.getPosActor(blockX, blockY +1) instanceof Player) {
                hasDied("block");
                gameMap.setPosActor(blockX, blockY, null);
                block.setLocation(blockX+1, blockY);
                gameMap.setPosActor(blockX+1, blockY, block);
                return true;
            } else if (gameMap.getPosActor(blockX, blockY +1) instanceof Block) {
                if (blockMove((Block) gameMap.getPosActor(blockX, blockY + 1), direction)) {
                    gameMap.setPosActor(blockX, blockY, null);
                    block.setLocation(blockX, blockY + 1);
                    gameMap.setPosActor(blockX, blockY+1, block);
                    return true;
                } else {
                    return false;
                }
            }else if (gameMap.getPosActor(blockX, blockY + 1) instanceof Mob) {
                return false;
            } else if (gameMap.getPosTile(blockX, blockY + 1) instanceof Water) {
                convertWaterToPath(blockX, blockY, blockX, blockY+1);
                return true;
            } else if (gameMap.getPosTile(blockX, blockY + 1) instanceof Ice
                    && (((Ice) gameMap.getPosTile(blockX, blockY + 1)).getCornerType() == Ice.CornerType.TOP_RIGHT
                    || ((Ice) gameMap.getPosTile(blockX, blockY + 1)).getCornerType() == Ice.CornerType.TOP_LEFT)) {
                return false;
            } else {
                if (gameMap.getPosTile(blockX, blockY+1).getPushable()) {
                    gameMap.setPosActor(blockX, blockY, null);
                    block.setLocation(blockX, blockY+1);
                    gameMap.setPosActor(blockX, blockY+1, block);
                    return true;
                } else {
                    return false;
                }
            }
        }
       /* if(gameMap.getPosTile(blockX, blockY) instanceof Ice) {
            blockMove(block, swapDirection(direction, ((Ice) gameMap.getPosTile(blockX, blockY)).getCornerType()));
        }*/
        return false;
    }
   public static void icePlayer(KeyCode actorDirection, Ice.CornerType corner) {
        if (actorDirection == KeyCode.LEFT) {
            if(corner == Ice.CornerType.NONE) {
                if(!movePlayer(actorDirection)){
                    icePlayer(swapDirection(actorDirection), corner);
                }
            } else if(corner == Ice.CornerType.TOP_LEFT) {
                icePlayer(KeyCode.DOWN, corner);
            } else if(corner == Ice.CornerType.TOP_RIGHT || corner == Ice.CornerType.BOTTOM_RIGHT) {
                movePlayer(actorDirection);
            } else if(corner == Ice.CornerType.BOTTOM_LEFT) {
                icePlayer(KeyCode.UP, corner);
            }
        } else if (actorDirection == KeyCode.RIGHT) {
           if(corner == Ice.CornerType.NONE) {
               if(!movePlayer(actorDirection)){
                   icePlayer(swapDirection(actorDirection), corner);
               }
           } else if(corner == Ice.CornerType.TOP_RIGHT) {
               icePlayer(KeyCode.DOWN, corner);
           } else if(corner == Ice.CornerType.TOP_LEFT || corner == Ice.CornerType.BOTTOM_LEFT) {
               movePlayer(actorDirection);
           } else if(corner == Ice.CornerType.BOTTOM_RIGHT) {
               icePlayer(KeyCode.UP, corner);
           }
       } else if (actorDirection == KeyCode.UP) {
            if(corner == Ice.CornerType.NONE) {
                if(!movePlayer(actorDirection)){
                    icePlayer(swapDirection(actorDirection), corner);
                }
            } else if(corner == Ice.CornerType.TOP_RIGHT) {
                icePlayer(KeyCode.LEFT, corner);
            } else if(corner == Ice.CornerType.BOTTOM_LEFT || corner == Ice.CornerType.BOTTOM_RIGHT) {
                movePlayer(actorDirection);
            } else if(corner == Ice.CornerType.TOP_LEFT) {
                icePlayer(KeyCode.RIGHT, corner);
            }
        } else if (actorDirection == KeyCode.DOWN) {
            if(corner == Ice.CornerType.NONE) {
                if(!movePlayer(actorDirection)){
                    icePlayer(swapDirection(actorDirection), corner);
                }
            } else if(corner == Ice.CornerType.BOTTOM_RIGHT) {
                icePlayer(KeyCode.LEFT, corner);
            } else if(corner == Ice.CornerType.TOP_LEFT || corner == Ice.CornerType.TOP_RIGHT) {
                movePlayer(actorDirection);
            } else if(corner == Ice.CornerType.BOTTOM_LEFT) {
                icePlayer(KeyCode.RIGHT, corner);
            }
        }
    }
    public static void iceBlock(Block block, KeyCode actorDirection, Ice.CornerType corner) {
        if (actorDirection == KeyCode.LEFT) {
            if(corner == Ice.CornerType.NONE) {
                if(!blockMove(block, actorDirection)){
                    iceBlock(block, swapDirection(actorDirection), corner);
                }
            } else if(corner == Ice.CornerType.TOP_LEFT) {
                iceBlock(block, KeyCode.DOWN, corner);
            } else if(corner == Ice.CornerType.TOP_RIGHT || corner == Ice.CornerType.BOTTOM_RIGHT) {
                blockMove(block, actorDirection);
            } else if(corner == Ice.CornerType.BOTTOM_LEFT) {
                iceBlock(block, KeyCode.UP, corner);
            }
        } else if (actorDirection == KeyCode.RIGHT) {
            if(corner == Ice.CornerType.NONE) {
                if(!blockMove(block, actorDirection)){
                    iceBlock(block, swapDirection(actorDirection), corner);
                }
            } else if(corner == Ice.CornerType.TOP_RIGHT) {
                iceBlock(block, KeyCode.DOWN, corner);
            } else if(corner == Ice.CornerType.TOP_LEFT || corner == Ice.CornerType.BOTTOM_LEFT) {
                blockMove(block, actorDirection);
            } else if(corner == Ice.CornerType.BOTTOM_RIGHT) {
                iceBlock(block, KeyCode.UP, corner);
            }
        } else if (actorDirection == KeyCode.UP) {
            if(corner == Ice.CornerType.NONE) {
                if(!blockMove(block, actorDirection)){
                    iceBlock(block, swapDirection(actorDirection), corner);
                }
            } else if(corner == Ice.CornerType.TOP_RIGHT) {
                iceBlock(block, KeyCode.LEFT, corner);
            } else if(corner == Ice.CornerType.BOTTOM_LEFT || corner == Ice.CornerType.BOTTOM_RIGHT) {
                blockMove(block, actorDirection);
            } else if(corner == Ice.CornerType.TOP_LEFT) {
                iceBlock(block, KeyCode.RIGHT, corner);
            }
        } else if (actorDirection == KeyCode.DOWN) {
            if(corner == Ice.CornerType.NONE) {
                if(!blockMove(block, actorDirection)){
                    iceBlock(block, swapDirection(actorDirection), corner);
                }
            } else if(corner == Ice.CornerType.BOTTOM_RIGHT) {
                iceBlock(block, KeyCode.LEFT, corner);
            } else if(corner == Ice.CornerType.TOP_LEFT || corner == Ice.CornerType.TOP_RIGHT) {
                blockMove(block, actorDirection);
            } else if(corner == Ice.CornerType.BOTTOM_LEFT) {
                iceBlock(block, KeyCode.RIGHT, corner);
            }
        }
    }

    public static KeyCode swapDirection(KeyCode direction) {
        switch (direction) {
            case LEFT:
                return KeyCode.RIGHT;
            case RIGHT:
                return KeyCode.LEFT;
            case UP:
                return KeyCode.DOWN;
            default:
                return KeyCode.UP;
        }
    }

    public static void convertWaterToPath(int blockX, int blockY, int waterX, int waterY) {
        gameMap.setPosActor(blockX,blockY, null);
        gameMap.setPosTile(waterX,waterY, new Path());
    }
    public static Map getGameMap() {
        return gameMap;
    }

    public static void setGameMap(Map gameMap) {
        GameLogic.gameMap = gameMap;
    }

    public static KeyCode getNextMove() {
        return nextMove;
    }

    public static void setNextMove(KeyCode nextMove) {
        GameLogic.nextMove = nextMove;
    }

    public static void movePinkBalls() {
        PinkBall[] pinkBalls = gameMap.getPinkBallsStored();
        for(int i = 0; i < pinkBalls.length; i++) {
            movePinkBall(pinkBalls[i],false);
        }
    }

    public static void movePinkBall(PinkBall ball, boolean stuck) {
        int ballX = ball.getX();
        int ballY = ball.getY();
        switch(ball.getDirection()){
            case LEFT:
                if(ball.getX() == 0) {
                    ball.setDirection(KeyCode.RIGHT);
                    movePinkBall(ball, true);
                } else if(gameMap.getPosActor(ballX -1,ballY) == null) {
                    if (gameMap.getPosTile(ballX-1, ballY) instanceof Path
                            || gameMap.getPosTile(ballX-1, ballY) instanceof Trap
                            || gameMap.getPosTile(ballX-1, ballY) instanceof Buttons) {
                        gameMap.setPosActor(ballX,ballY, null);
                        ball.setX(ballX-1);
                        gameMap.setPosActor(ballX-1,ballY, ball);
                    } else if (!stuck){
                        ball.setDirection(KeyCode.RIGHT);
                        movePinkBall(ball, true);
                    }
                } else if(!stuck) {
                    ball.setDirection(KeyCode.RIGHT);
                    movePinkBall(ball, true);
                }
                break;
            case RIGHT:
                if(ball.getX() == gameMap.getBoardWidth()-1) {
                    ball.setDirection(KeyCode.LEFT);
                    movePinkBall(ball, true);
                } else if(gameMap.getPosActor(ballX +1,ballY) == null) {
                    if (gameMap.getPosTile(ballX+1, ballY) instanceof Path
                            || gameMap.getPosTile(ballX+1, ballY) instanceof Trap
                            || gameMap.getPosTile(ballX+1, ballY) instanceof Buttons) {
                        gameMap.setPosActor(ballX,ballY, null);
                        ball.setX(ballX+1);
                        gameMap.setPosActor(ballX+1,ballY, ball);
                    } else if (!stuck){
                        ball.setDirection(KeyCode.LEFT);
                        movePinkBall(ball, true);
                    }
                } else if(!stuck) {
                    ball.setDirection(KeyCode.LEFT);
                    movePinkBall(ball, true);
                }
                break;
            case UP:
                if(ball.getY() == 0) {
                    ball.setDirection(KeyCode.DOWN);
                    movePinkBall(ball, true);
                } else if(gameMap.getPosActor(ballX,ballY-1) == null) {
                    if (gameMap.getPosTile(ballX, ballY - 1) instanceof Path
                            || gameMap.getPosTile(ballX, ballY - 1) instanceof Trap
                            || gameMap.getPosTile(ballX, ballY - 1) instanceof Buttons) {
                        gameMap.setPosActor(ballX,ballY, null);
                        ball.setY(ballY-1);
                        gameMap.setPosActor(ballX,ballY-1, ball);
                    } else if (!stuck){
                        ball.setDirection(KeyCode.DOWN);
                        movePinkBall(ball, true);
                    }
                } else if(!stuck) {
                    ball.setDirection(KeyCode.DOWN);
                    movePinkBall(ball, true);
                }
                break;
            case DOWN:
                if(ball.getY() == gameMap.getBoardHeight()-1) {
                    ball.setDirection(KeyCode.UP);
                    movePinkBall(ball, true);
                } else
                if(gameMap.getPosActor(ballX,ballY+1) == null) {
                    if (gameMap.getPosTile(ballX, ballY + 1) instanceof Path
                            || gameMap.getPosTile(ballX, ballY + 1) instanceof Trap
                            || gameMap.getPosTile(ballX, ballY + 1) instanceof Buttons) {
                        gameMap.setPosActor(ballX,ballY, null);
                        ball.setY(ballY+1);
                        gameMap.setPosActor(ballX,ballY+1, ball);
                    } else if (!stuck){
                        ball.setDirection(KeyCode.UP);
                        movePinkBall(ball, true);
                    }
                } else if(!stuck) {
                    ball.setDirection(KeyCode.UP);
                    movePinkBall(ball, true);
                }
                break;
        }
    }

    public static void moveBugs() {
    }

    public static void moveFrogs() {
    }

    public static void updatePositions() {
        Block[] blockList = gameMap.getBlocksStored();

        for(int i = 0; i < blockList.length; i++) {
            Block block = blockList[i];
            int blockX = block.getLocationX();
            int blockY = block.getLocationY();
            if(gameMap.getPosTile(blockX, blockY) instanceof Ice) {
                iceBlock(block, block.getDirection(), ((Ice) gameMap.getPosTile(blockX, blockY)).getCornerType());
            }
        }
        Player player = gameMap.getPlayer();
        if (gameMap.getPosTile(player.getX(),player.getY()) instanceof Ice) {
            icePlayer(player.getDirection(), ((Ice) gameMap.getPosTile(player.getX(), player.getY())).getCornerType());
        }
    }
}
