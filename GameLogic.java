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
                return false; //Player encounters Ice with incorrect movement direction
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
        // Obtain the current location of the Block
        int blockX = block.getLocationX();
        int blockY = block.getLocationY();
        block.setDirection(direction); // Set the direction of the Block
        // Handle LEFT movement
        if (direction == KeyCode.LEFT) {
            // Check if the Block is at the leftmost edge
            if (blockX == 0) {
                return false;
            } else if(gameMap.getPosActor(blockX - 1, blockY) instanceof Player) {
                // Player is at the target position, handle accordingly
                hasDied("block");
                gameMap.setPosActor(blockX, blockY, null);
                block.setLocation(blockX-1, blockY);
                gameMap.setPosActor(blockX-1, blockY, block);
                return true;
            } else if (gameMap.getPosActor(blockX - 1, blockY) instanceof Block) {
                // Block is at the target position, recursively move the other block
                if(blockMove((Block) gameMap.getPosActor(blockX - 1, blockY), direction)) {
                    gameMap.setPosActor(blockX, blockY, null);
                    block.setLocation(blockX-1, blockY);
                    gameMap.setPosActor(blockX-1, blockY, block);
                    return true;
                } else {
                    return false;
                }
            } else if (gameMap.getPosActor(blockX - 1, blockY) instanceof Mob) {
                 // Mob is at the target position, block cannot move
                return false;
            } else if (gameMap.getPosTile(blockX - 1, blockY) instanceof Water) {
                 // Water is at the target position, convert to path
                convertWaterToPath(blockX, blockY, blockX-1, blockY);
                return true;
            } else if (gameMap.getPosTile(blockX - 1, blockY) instanceof Ice
                    && (((Ice) gameMap.getPosTile(blockX - 1, blockY)).getCornerType() == Ice.CornerType.TOP_RIGHT
                    || ((Ice) gameMap.getPosTile(blockX - 1, blockY)).getCornerType() == Ice.CornerType.BOTTOM_RIGHT)) {
                // Ice is at the target position with specific corner type, block cannot move
                return false;
            } else {
                // General case, check if the tile is pushable
                if (gameMap.getPosTile(blockX - 1, blockY).getPushable()) {
                    gameMap.setPosActor(blockX, blockY, null);
                    block.setLocation(blockX-1, blockY);
                    gameMap.setPosActor(blockX-1, blockY, block);
                    return true;
                } else {
                    return false;
                }
            }
            // Handle RIGHT movement
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
            // Handle UP movement
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
            // Handle DOWN movement
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
        // Return false by default (no movement occurred)
        return false;
    }
    /**
     * Handles player movement on Ice tiles considering the specified corner type.
     *
     * This method is responsible for handling player movement on Ice tiles based on the
     * specified actor direction and Ice corner type. It takes into account the sliding behavior
     * on Ice tiles and recursively moves the player accordingly.
     *
     * @param actorDirection The direction in which the player is moving (e.g., KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP, KeyCode.DOWN).
     * @param corner         The corner type of the Ice tile (e.g., Ice.CornerType.TOP_LEFT, Ice.CornerType.TOP_RIGHT, etc.).
     */
   public static void icePlayer(KeyCode actorDirection, Ice.CornerType corner) {
       // Handle LEFT movement
        if (actorDirection == KeyCode.LEFT) {
            if(corner == Ice.CornerType.NONE) {
                // No corner, attempt to move player
                if(!movePlayer(actorDirection)){
                    icePlayer(swapDirection(actorDirection), corner);
                }
            } else if(corner == Ice.CornerType.TOP_LEFT) {
                // Top-left corner, slide player DOWN
                icePlayer(KeyCode.DOWN, corner);
            } else if(corner == Ice.CornerType.TOP_RIGHT || corner == Ice.CornerType.BOTTOM_RIGHT) {
                // Top-right or bottom-right corner, move player LEFT
                movePlayer(actorDirection);
            } else if(corner == Ice.CornerType.BOTTOM_LEFT) {
                // Bottom-left corner, slide player UP
                icePlayer(KeyCode.UP, corner);
            }
            // Handle RIGHT movement
        } else if (actorDirection == KeyCode.RIGHT) {
           if(corner == Ice.CornerType.NONE) {
               // No corner, attempt to move player
               if(!movePlayer(actorDirection)){
                   icePlayer(swapDirection(actorDirection), corner);
               }
           } else if(corner == Ice.CornerType.TOP_RIGHT) {
               // Top-right corner, slide player DOWN
               icePlayer(KeyCode.DOWN, corner);
           } else if(corner == Ice.CornerType.TOP_LEFT || corner == Ice.CornerType.BOTTOM_LEFT) {
               //Top-left or bottom-left corner, move player RIGHT
               movePlayer(actorDirection);
           } else if(corner == Ice.CornerType.BOTTOM_RIGHT) {
               // Bottom-right corner, slide player UP
               icePlayer(KeyCode.UP, corner);
           }
            // Handle UP movement
       } else if (actorDirection == KeyCode.UP) {
            if(corner == Ice.CornerType.NONE) {
                // No corner, attempt to move player
                if(!movePlayer(actorDirection)){
                    icePlayer(swapDirection(actorDirection), corner);
                }
            } else if(corner == Ice.CornerType.TOP_RIGHT) {
                // Top-right corner, slide player LEFT
                icePlayer(KeyCode.LEFT, corner);
            } else if(corner == Ice.CornerType.BOTTOM_LEFT || corner == Ice.CornerType.BOTTOM_RIGHT) {
                // Bottom-left corner or bottom-right, move player UP
                movePlayer(actorDirection);
            } else if(corner == Ice.CornerType.TOP_LEFT) {
                // Top-left corner, slide player RIGHT
                icePlayer(KeyCode.RIGHT, corner);
            }
            // Handle DOWN movement
        } else if (actorDirection == KeyCode.DOWN) {
            if(corner == Ice.CornerType.NONE) {
                // No corner, attempt to move player
                if(!movePlayer(actorDirection)){
                    icePlayer(swapDirection(actorDirection), corner);
                }
            } else if(corner == Ice.CornerType.BOTTOM_RIGHT) {
                // Bottom-right corner, slide player LEFT
                icePlayer(KeyCode.LEFT, corner);
            } else if(corner == Ice.CornerType.TOP_LEFT || corner == Ice.CornerType.TOP_RIGHT) {
                // Top-left corner or top-right, move player DOWN
                movePlayer(actorDirection);
            } else if(corner == Ice.CornerType.BOTTOM_LEFT) {
                // Bottom-left corner, slide player RIGHT
                icePlayer(KeyCode.RIGHT, corner);
            }
        }
    }
    /**
     * Handles the movement of a block on Ice tiles considering the specified corner type.
     *
     * This method is responsible for handling block movement on Ice tiles based on the
     * specified actor direction and Ice corner type. It takes into account the sliding behavior
     * on Ice tiles and recursively moves the block accordingly.
     *
     * @param block          The block to be moved on the Ice tile.
     * @param actorDirection The direction in which the block is moving (e.g., KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP, KeyCode.DOWN).
     * @param corner         The corner type of the Ice tile (e.g., Ice.CornerType.TOP_LEFT, Ice.CornerType.TOP_RIGHT, etc.).
     */
    public static void iceBlock(Block block, KeyCode actorDirection, Ice.CornerType corner) {
        // Handle LEFT movement
        if (actorDirection == KeyCode.LEFT) {
            if(corner == Ice.CornerType.NONE) {
                // No corner, attempt to move block
                if(!blockMove(block, actorDirection)){
                    iceBlock(block, swapDirection(actorDirection), corner);
                }
            } else if(corner == Ice.CornerType.TOP_LEFT) {
                // Top-left corner, slide block DOWN
                iceBlock(block, KeyCode.DOWN, corner);
            } else if(corner == Ice.CornerType.TOP_RIGHT || corner == Ice.CornerType.BOTTOM_RIGHT) {
                // Top-right or bottom-right corner, move block LEFT
                blockMove(block, actorDirection);
            } else if(corner == Ice.CornerType.BOTTOM_LEFT) {
                // Bottom-left corner, slide block UP
                iceBlock(block, KeyCode.UP, corner);
            }
            // Handle RIGHT movement
        } else if (actorDirection == KeyCode.RIGHT) {
            if(corner == Ice.CornerType.NONE) {
                // No corner, attempt to move block
                if(!blockMove(block, actorDirection)){
                    iceBlock(block, swapDirection(actorDirection), corner);
                }
            } else if(corner == Ice.CornerType.TOP_RIGHT) {
                // Top-right corner, slide block DOWN
                iceBlock(block, KeyCode.DOWN, corner);
            } else if(corner == Ice.CornerType.TOP_LEFT || corner == Ice.CornerType.BOTTOM_LEFT) {
                // Top-left or bottom-left corner, move block RIGHT
                blockMove(block, actorDirection);
            } else if(corner == Ice.CornerType.BOTTOM_RIGHT) {
                // Bottom-right corner, slide block UP
                iceBlock(block, KeyCode.UP, corner);
            }
            // Handle UP movement
        } else if (actorDirection == KeyCode.UP) {
            if(corner == Ice.CornerType.NONE) {
                // No corner, attempt to move block
                if(!blockMove(block, actorDirection)){
                    iceBlock(block, swapDirection(actorDirection), corner);
                }
            } else if(corner == Ice.CornerType.TOP_RIGHT) {
                // Top-right corner, slide block LEFT
                iceBlock(block, KeyCode.LEFT, corner);
            } else if(corner == Ice.CornerType.BOTTOM_LEFT || corner == Ice.CornerType.BOTTOM_RIGHT) {
                // bottom-left or bottom-right corner, move block UP
                blockMove(block, actorDirection);
            } else if(corner == Ice.CornerType.TOP_LEFT) {
                // Top-left corner, slide block RIGHT
                iceBlock(block, KeyCode.RIGHT, corner);
            }
            // Handle DOWN movement
        } else if (actorDirection == KeyCode.DOWN) {
            if(corner == Ice.CornerType.NONE) {
                // No corner, attempt to move block
                if(!blockMove(block, actorDirection)){
                    iceBlock(block, swapDirection(actorDirection), corner);
                }
            } else if(corner == Ice.CornerType.BOTTOM_RIGHT) {
                // Bottom-right corner, slide block LEFT
                iceBlock(block, KeyCode.LEFT, corner);
            } else if(corner == Ice.CornerType.TOP_LEFT || corner == Ice.CornerType.TOP_RIGHT) {
                 // Top-left or top-right corner, move block DOWN
                blockMove(block, actorDirection);
            } else if(corner == Ice.CornerType.BOTTOM_LEFT) {
                // Bottom-left corner, slide block RIGHT
                iceBlock(block, KeyCode.RIGHT, corner);
            }
        }
    }

    /**
     * Swaps the given direction to its opposite direction.
     *
     * This method takes a direction (e.g., LEFT, RIGHT, UP, DOWN) and returns
     * its opposite direction. It is commonly used for reversing movement when needed.
     *
     * @param direction The direction to be swapped.
     * @return The opposite direction of the input.
     */
    public static KeyCode swapDirection(KeyCode direction) {
        switch (direction) {
            case LEFT:
                // If the input direction is LEFT, return RIGHT
                return KeyCode.RIGHT;
            case RIGHT:
                // If the input direction is RIGHT, return LEFT
                return KeyCode.LEFT;
            case UP:
                // If the input direction is UP, return DOWN
                return KeyCode.DOWN;
            default:
                // For any other input, return UP (assumed to cover DOWN as well)
                return KeyCode.UP;
        }
    }

    /**
     * Converts water at the specified position to a path and updates the game map.
     *
     * This method takes the coordinates of a block and water position and converts
     * the water tile to a path tile at that position. It then updates the game map
     * accordingly.
     *
     * @param blockX The X-coordinate of the block.
     * @param blockY The Y-coordinate of the block.
     * @param waterX The X-coordinate of the water to be converted.
     * @param waterY The Y-coordinate of the water to be converted.
     */

    public static void convertWaterToPath(int blockX, int blockY, int waterX, int waterY) {
        // Remove the block from its current position
        gameMap.setPosActor(blockX,blockY, null);
        // Replace the water tile with a path tile at the specified position
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
