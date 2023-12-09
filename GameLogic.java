import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.*;
import java.util.Scanner;

public class GameLogic {
    private static Map gameMap;
    private static int levelNum;
    private static KeyCode nextMove;

    private static boolean gameWon = false;
    /**
     * Marks the player as dead based on the cause of death.
     *
     * @param cause The cause of death (e.g., "water").
     */
    public static void hasDied(String cause) {
        gameMap.getPlayer().setAlive(false);
        if(cause == "water") {
            //play drown animation
            //end game
        } else {
            //play death animation
            //end game
        }
    }
    public static void endGameChanges() {
        changeScoresInFile();
        changeMapProgress();
    }

    private static void changeScoresInFile() {
        String oldString = gameMap.getTopNames()[0] + "," + gameMap.getTopScores()[0];
        for(int i = 1; i < 10; i++) {
            oldString = oldString + "," + gameMap.getTopNames()[i] + "," + gameMap.getTopScores()[i];
        }
        String newString = gameMap.getNewNames()[0] + "," + gameMap.getNewScore()[0];
        for(int i = 1; i < 10; i++) {
            newString = newString + "," + gameMap.getNewNames()[i] + "," + gameMap.getNewScore()[i];
        }
        File fileToBeModified = new File(gameMap.getOriginal());

        String oldContent = "";

        BufferedReader reader = null;

        FileWriter writer = null;

        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));

            //Reading all the lines of input text file into oldContent

            String line = reader.readLine();

            while (line != null)
            {
                oldContent = oldContent + line + System.lineSeparator();

                line = reader.readLine();
            }

            //Replacing oldString with newString in the oldContent

            String newContent = oldContent.replaceAll(oldString, newString);

            //Rewriting the input text file with newContent

            writer = new FileWriter(fileToBeModified);

            writer.write(newContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                //Closing the resources

                reader.close();

                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void setLevelNum(int num) {
        levelNum = num;
    }
    private static void changeMapProgress() {
        if (levelNum > FileConverter.levelProgressSaved) {
            String oldString = FileConverter.userNameSaved + "," + FileConverter.passwordSaved + "," + FileConverter.levelProgressSaved;
            String newString = FileConverter.userNameSaved + "," + FileConverter.passwordSaved + "," + (FileConverter.levelProgressSaved + 1);

            File fileToBeModified = new File("Credentials.txt");

            String oldContent = "";

            BufferedReader reader = null;

            FileWriter writer = null;

            try {
                reader = new BufferedReader(new FileReader(fileToBeModified));

                //Reading all the lines of input text file into oldContent

                String line = reader.readLine();

                while (line != null) {
                    oldContent = oldContent + line + System.lineSeparator();

                    line = reader.readLine();
                }

                //Replacing oldString with newString in the oldContent

                String newContent = oldContent.replaceAll(oldString, newString);

                //Rewriting the input text file with newContent

                writer = new FileWriter(fileToBeModified);

                writer.write(newContent);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    //Closing the resources

                    reader.close();

                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
        if(gameMap.getTimeLeft() <= 0){
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
        if(gameMap.getPosTile(playerX, playerY) instanceof Trap) {
            if(!(((Trap) gameMap.getPosTile(playerX, playerY)).isActive())) {
                return false;
            }
        }
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

        if(gameMap.getPosTile(blockX, blockY) instanceof Trap) {
            if(!(((Trap) gameMap.getPosTile(blockX, blockY)).isActive())) {
                return false;
            }
        }
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
                    icePlayer(turnLeft(turnLeft(actorDirection)), corner);
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
                   icePlayer(turnLeft(turnLeft(actorDirection)), corner);
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
                    icePlayer(turnLeft(turnLeft(actorDirection)), corner);
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
                    icePlayer(turnLeft(turnLeft(actorDirection)), corner);
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
                    iceBlock(block, turnLeft(turnLeft(actorDirection)), corner);
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
                    iceBlock(block, turnLeft(turnLeft(actorDirection)), corner);
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
                    iceBlock(block, turnLeft(turnLeft(actorDirection)), corner);
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
                    iceBlock(block, turnLeft(turnLeft(actorDirection)), corner);
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
    public static KeyCode turnLeft(KeyCode direction) {
        switch (direction) {
            case LEFT:
                // If the input direction is LEFT, return RIGHT
                return KeyCode.DOWN;
            case RIGHT:
                // If the input direction is RIGHT, return LEFT
                return KeyCode.UP;
            case UP:
                // If the input direction is UP, return DOWN
                return KeyCode.LEFT;
            default:
                // For any other input, return UP (assumed to cover DOWN as well)
                return KeyCode.RIGHT;

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
    /**
     * Gets the current game map.
     *
     * @return The current game map.
     */
    public static Map getGameMap() {
        return gameMap;
    }

    /**
     * Sets the game map to the specified map.
     *
     * This method updates the game map to the provided map instance.
     *
     * @param gameMap The new game map.
     */
    public static void setGameMap(Map gameMap) {
        // Set the game map to the provided map instance
        GameLogic.gameMap = gameMap;
    }

    /**
     * Gets the next move stored in the game logic.
     *
     * @return The KeyCode representing the next move.
     */
    public static KeyCode getNextMove() {
        return nextMove;
    }
    
    /**
     * Sets the next move in the game logic to the specified KeyCode.
     *
     * This method updates the next move stored in the game logic with the provided KeyCode.
     *
     * @param nextMove The KeyCode representing the next move to be set.
     */
    public static void setNextMove(KeyCode nextMove) {
        GameLogic.nextMove = nextMove;
    }

    /**
     * Moves all pink balls stored in the game map.
     *
     * This method iterates over all pink balls stored in the game map and moves each one.
     */
    public static void movePinkBalls() {
        PinkBall[] pinkBalls = gameMap.getPinkBallsStored();
        for(int i = 0; i < pinkBalls.length; i++) {
            movePinkBall(pinkBalls[i],false);
        }
    }

    /**
     * Moves a pink ball on the game map based on its current direction.
     *
     * @param ball  The PinkBall object to be moved.
     * @param stuck Indicates whether the ball is stuck and needs to change direction.
     */

    public static void movePinkBall(PinkBall ball, boolean stuck) {
        // Get the current coordinates of the pink ball
        int ballX = ball.getX();
        int ballY = ball.getY();
        if(gameMap.getPosTile(ballX, ballY) instanceof Trap) {
            if(!(((Trap) gameMap.getPosTile(ballX, ballY)).isActive())) {
                return;
            }
        }
        // Switch statement to handle different directions
        switch(ball.getDirection()){
            case LEFT:
                // Check if the ball is at the left edge
                if(ball.getX() == 0) {
                    // If at the left edge, change direction to RIGHT
                    ball.setDirection(KeyCode.RIGHT);
                    movePinkBall(ball, true);
                } else if(gameMap.getPosActor(ballX-1, ballY) instanceof Player) {
                    hasDied("ball");
                } else if(gameMap.getPosActor(ballX -1,ballY) == null) {
                    // If the position to the left is empty
                    if (gameMap.getPosTile(ballX-1, ballY) instanceof Path
                            || (gameMap.getPosTile(ballX-1, ballY) instanceof Trap)
                            || gameMap.getPosTile(ballX-1, ballY) instanceof Buttons) {
                        // If the tile to the left is Path, Trap, or Buttons, move the ball
                        gameMap.setPosActor(ballX,ballY, null);
                        ball.setX(ballX-1);
                        gameMap.setPosActor(ballX-1,ballY, ball);
                    } else if (!stuck){
                        // If not stuck and the tile is not suitable, change direction to RIGHT
                        ball.setDirection(KeyCode.RIGHT);
                        movePinkBall(ball, true);
                    }
                } else if(!stuck) {
                    // If not stuck and the position to the left is not empty, change direction to RIGHT
                    ball.setDirection(KeyCode.RIGHT);
                    movePinkBall(ball, true);
                }
                break;
            case RIGHT:
                // Similar logic as for LEFT, but checking the right edge
                if(ball.getX() == gameMap.getBoardWidth()-1) {
                    ball.setDirection(KeyCode.LEFT);
                    movePinkBall(ball, true);
                } else if(gameMap.getPosActor(ballX+1, ballY) instanceof Player) {
                    hasDied("ball");
                } else if(gameMap.getPosActor(ballX +1,ballY) == null) {
                    if (gameMap.getPosTile(ballX+1, ballY) instanceof Path
                            || (gameMap.getPosTile(ballX+1, ballY) instanceof Trap)
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
                // Similar logic as for LEFT, but checking the top edge
                if(ball.getY() == 0) {
                    ball.setDirection(KeyCode.DOWN);
                    movePinkBall(ball, true);
                } else if(gameMap.getPosActor(ballX, ballY-1) instanceof Player) {
                    hasDied("ball");
                } else if(gameMap.getPosActor(ballX,ballY-1) == null) {
                    if (gameMap.getPosTile(ballX, ballY - 1) instanceof Path
                            || (gameMap.getPosTile(ballX, ballY - 1) instanceof Trap)
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
                // Similar logic as for LEFT, but checking the bottom edge
                if(ball.getY() == gameMap.getBoardHeight()-1) {
                    ball.setDirection(KeyCode.UP);
                    movePinkBall(ball, true);
                } else if(gameMap.getPosActor(ballX, ballY+1) instanceof Player) {
                    hasDied("ball");
                } else if(gameMap.getPosActor(ballX,ballY+1) == null) {
                    if (gameMap.getPosTile(ballX, ballY + 1) instanceof Path
                            || (gameMap.getPosTile(ballX, ballY + 1) instanceof Trap)
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

    public static void moveBugs() { // spins round if no wall, is this correct?
        Bug[] bugs = gameMap.getBugsStored();
        for(int i = 0; i < bugs.length; i++) {
            moveBug(bugs[i],0);
        }
    }
    public static void moveBug(Bug bug, int turns) {
        int bugX = bug.getX();
        int bugY = bug.getY();
        if(gameMap.getPosTile(bugX, bugY) instanceof Trap) {
            if(!(((Trap) gameMap.getPosTile(bugX, bugY)).isActive())) {
                return;
            }
        }
        if(bug.getFollow()){
            //follow left
            switch(bug.getDirection()) {
                case LEFT:
                    if(bug.getY() == gameMap.getBoardHeight()-1) {
                        if(turns != 3) {
                            bug.setDirection(KeyCode.UP);
                            moveBug(bug, turns + 1);
                        }
                    } else if(gameMap.getPosActor(bugX, bugY+1) instanceof Player) {
                        hasDied("bug");
                    } else if (gameMap.getPosActor(bugX, bugY+1) == null) {
                        if (gameMap.getPosTile(bugX, bugY+1) instanceof Path
                                || (gameMap.getPosTile(bugX, bugY+1) instanceof Trap)
                                || gameMap.getPosTile(bugX, bugY+1) instanceof Buttons) {
                            gameMap.setPosActor(bugX,bugY, null);
                            bug.setY(bugY+1);
                            bug.setDirection(KeyCode.DOWN);
                            gameMap.setPosActor(bugX,bugY+1, bug);
                        } else if (turns != 3){
                            bug.setDirection(KeyCode.UP);
                            moveBug(bug, turns+1);
                        }
                    } else if(turns != 3) {
                        bug.setDirection(KeyCode.UP);
                        moveBug(bug, turns+1);
                    }
                    break;
                case UP:
                    if(bug.getX() == 0) {
                        if(turns != 3) {
                            bug.setDirection(KeyCode.RIGHT);
                            moveBug(bug, turns + 1);
                        }
                    } else if(gameMap.getPosActor(bugX-1, bugY) instanceof Player) {
                        hasDied("bug");
                    } else if (gameMap.getPosActor(bugX-1, bugY) == null) {
                        if (gameMap.getPosTile(bugX-1, bugY) instanceof Path
                                || (gameMap.getPosTile(bugX-1, bugY) instanceof Trap)
                                || gameMap.getPosTile(bugX-1, bugY) instanceof Buttons) {
                            gameMap.setPosActor(bugX,bugY, null);
                            bug.setX(bugX-1);
                            bug.setDirection(KeyCode.LEFT);
                            gameMap.setPosActor(bugX-1,bugY, bug);
                        } else if (turns != 3){
                            bug.setDirection(KeyCode.RIGHT);
                            moveBug(bug, turns+1);
                        }
                    } else if(turns != 3) {
                        bug.setDirection(KeyCode.RIGHT);
                        moveBug(bug, turns+1);
                    }
                    return;
                case RIGHT:
                    if(bug.getY() == 0) {
                        if(turns != 3) {
                            bug.setDirection(KeyCode.DOWN);
                            moveBug(bug, turns + 1);
                        }
                    } else if(gameMap.getPosActor(bugX, bugY-1) instanceof Player) {
                        hasDied("bug");
                    } else if (gameMap.getPosActor(bugX, bugY-1) == null) {
                        if (gameMap.getPosTile(bugX, bugY-1) instanceof Path
                                || (gameMap.getPosTile(bugX, bugY-1) instanceof Trap)
                                || gameMap.getPosTile(bugX, bugY-1) instanceof Buttons) {
                            gameMap.setPosActor(bugX,bugY, null);
                            bug.setY(bugY-1);
                            bug.setDirection(KeyCode.UP);
                            gameMap.setPosActor(bugX,bugY-1, bug);
                        } else if (turns != 3){
                            bug.setDirection(KeyCode.DOWN);
                            moveBug(bug, turns+1);
                        }
                    } else if(turns != 3) {
                        bug.setDirection(KeyCode.DOWN);
                        moveBug(bug, turns+1);
                    }
                    return;
                case DOWN:
                    if(bug.getX() == gameMap.getBoardWidth()-1) {
                        if(turns != 3) {
                            bug.setDirection(KeyCode.LEFT);
                            moveBug(bug, turns + 1);
                        }
                    } else if(gameMap.getPosActor(bugX+1, bugY) instanceof Player) {
                        hasDied("bug");
                    } else if (gameMap.getPosActor(bugX+1, bugY) == null) {
                        if (gameMap.getPosTile(bugX+1, bugY) instanceof Path
                                || (gameMap.getPosTile(bugX+1, bugY) instanceof Trap)
                                || gameMap.getPosTile(bugX+1, bugY) instanceof Buttons) {
                            gameMap.setPosActor(bugX,bugY, null);
                            bug.setX(bugX+1);
                            bug.setDirection(KeyCode.RIGHT);
                            gameMap.setPosActor(bugX+1,bugY, bug);
                        } else if (turns != 3){
                            bug.setDirection(KeyCode.LEFT);
                            moveBug(bug, turns+1);
                        }
                    } else if(turns != 3) {
                        bug.setDirection(KeyCode.LEFT);
                        moveBug(bug, turns+1);
                    }
                    return;
            }
        } else {
            //follow right
        }
    }

    public static void moveFrogs() {
        Frog[] frogs = gameMap.getFrogsStored();
        for(int i = 0; i < frogs.length; i++) {
            frogs[i].setMap(gameMap.getActorLayerMap(),gameMap.getTileLayerMap());
            int[] newCords = frogs[i].checkShortest(frogs[i].getX(),frogs[i].getY(),
                  gameMap.getPlayer().getX(),gameMap.getPlayer().getY());
            gameMap.setPosActor(frogs[i].getX(),frogs[i].getY(), null);
            frogs[i].setLocation(newCords[0], newCords[1]);
            gameMap.setPosActor(frogs[i].getX(),frogs[i].getY(), frogs[i]);
        }
    }
    /**
     * Update the positions of blocks and the player on the game map.
     * Handles special behavior on ice tiles for both blocks and the player.
     */
    public static void updatePositions() {
        // Get the list of blocks on the game map
        Block[] blockList = gameMap.getBlocksStored();
        Buttons[] buttonList = gameMap.getButtonsStored();
        // Iterate through each block in the list

        for(int i = 0; i < blockList.length; i++) {
            // Get the current block
            Block block = blockList[i];
            // Get the current coordinates of the block
            int blockX = block.getLocationX();
            int blockY = block.getLocationY();
            // Check if the tile at the block's position is Ice
            if(gameMap.getPosTile(blockX, blockY) instanceof Ice) {
                // If on Ice, apply iceBlock behavior to the block/
                iceBlock(block, block.getDirection(), ((Ice) gameMap.getPosTile(blockX, blockY)).getCornerType());
            }
        }
        // Get the player from the game map
        Player player = gameMap.getPlayer();
        // Check if the tile at the player's position is Ice
        if (gameMap.getPosTile(player.getX(),player.getY()) instanceof Ice) {
            // If on Ice, apply icePlayer behavior to the player
            icePlayer(player.getDirection(), ((Ice) gameMap.getPosTile(player.getX(), player.getY())).getCornerType());
        }
        for(int i = 0; i < buttonList.length; i++) {
            Buttons button = buttonList[i];
            int blockX = button.getX();
            int blockY = button.getY();
            if(gameMap.getPosActor(blockX, blockY) != null) {
                button.getConnectTrap().activate();
            } else {
                button.getConnectTrap().deactivate();
            }
        }
    }
}
