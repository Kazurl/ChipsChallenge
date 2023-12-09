import javafx.scene.input.KeyCode;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Arrays;


/**
 *<ol>
 *     <li>File-name: FileConverter.java</li>
 *     <li>Purpose of the program: Reads the text file and creates a Map,
 *     Saves the Player's Progress and converts them into a text file.</li>
 *
 *</ol>
 */
public class FileConverter {

    /**
     * Reads the text file and creates a Map equipped with all information
     * of the game entities.
     *
     * @param filePath Name of the text file.
     * @return Creates the Map from the text file.
     */
    public static Map convertFromFile(String filePath){

        File newFile = new File(filePath);
        try {
            Scanner fileReader = new Scanner(newFile);
            fileReader.useDelimiter("\r\n|\n");
            Scanner lineReader = new Scanner(fileReader.next());
            lineReader.useDelimiter(",");
            int width = lineReader.nextInt();
            int height = lineReader.nextInt();
            int timeLeft = lineReader.nextInt();
            Player givenPlayer = new Player();
            Block[] givenBlocks = {};
            Frog[] givenFrogs = {};
            Bug[] givenBugs = {};
            PinkBall[] givenPinkBalls = {};
            lineReader = new Scanner(fileReader.next());
            lineReader.useDelimiter(",");
            int[] newInventory = {lineReader.nextInt(),lineReader.nextInt(),lineReader.nextInt(),lineReader.nextInt(),lineReader.nextInt()};

            Actor[][] actorFile = new Actor[height][width];
            for (int y = 0; y < height; y++) {
                lineReader = new Scanner(fileReader.next());
                lineReader.useDelimiter(",");
                for (int x = 0; x < width; x++) {
                    String nextActor = lineReader.next();
                    switch(nextActor){
                        case "Pl":
                            givenPlayer.setInventory(newInventory);
                            givenPlayer.setX(x);
                            givenPlayer.setY(y);
                            actorFile[y][x] = givenPlayer;
                            break;
                        case "Fr":
                            Frog frog = new Frog();
                            frog.setX(x);
                            frog.setY(y);
                            actorFile[y][x] = frog; // needs its tile
                            givenFrogs = Arrays.copyOf(givenFrogs, givenFrogs.length + 1);
                            givenFrogs[givenFrogs.length - 1] = frog;
                            break;
                        case "Bl":
                            Block block = new Block();
                            block.setLocation(x,y);
                            actorFile[y][x] = block; // needs its tile??
                            givenBlocks = Arrays.copyOf(givenBlocks, givenBlocks.length + 1);
                            givenBlocks[givenBlocks.length - 1] = block;
                            break;
                        default:
                            char[] mobChar = nextActor.toCharArray();
                            switch(mobChar[0]) {
                                case 'B':
                                    char bugDirection = mobChar[1];
                                    Bug bug = new Bug();
                                    bug.setX(x);
                                    bug.setY(y);
                                    switch(mobChar[1]) {
                                        case '1':
                                            bug.setFollow(true);
                                            break;
                                        case '2':
                                            bug.setFollow(false);
                                            break;
                                    }
                                    actorFile[y][x] = bug; // needs its tile
                                    givenBugs = Arrays.copyOf(givenBugs, givenBugs.length + 1);
                                    givenBugs[givenBugs.length - 1] = bug;
                                    break;
                                case 'P':
                                    PinkBall ball = new PinkBall();
                                    ball.setX(x);
                                    ball.setY(y);
                                    switch(mobChar[1]) {
                                        case '1':
                                            ball.setDirection(KeyCode.UP);
                                            break;
                                        case '2':
                                            ball.setDirection(KeyCode.DOWN);
                                            break;
                                        case '3':
                                            ball.setDirection(KeyCode.LEFT);
                                            break;
                                        default:
                                            ball.setDirection(KeyCode.RIGHT);
                                            break;

                                    }
                                    actorFile[y][x] = ball; // needs its tile
                                    givenPinkBalls = Arrays.copyOf(givenPinkBalls, givenPinkBalls.length + 1);
                                    givenPinkBalls[givenPinkBalls.length - 1] = ball;
                                    break;
                            }
                    }
                }
            }

            Item[][] itemFile = new Item[height][width];
            for (int y = 0; y < height; y++) {
                lineReader = new Scanner(fileReader.next());
                lineReader.useDelimiter(",");
                for (int x = 0; x < width; x++) {
                    String nextItem = lineReader.next();
                    switch(nextItem){
                        case "C":
                            itemFile[y][x] = new ComputerChip(x,y);
                            break;
                        case "K1":
                            itemFile[y][x] = new Key(x,y,Key.Colour.RED);
                            break;
                        case "K2":
                            itemFile[y][x] = new Key(x,y,Key.Colour.GREEN);
                            break;
                        case "K3":
                            itemFile[y][x] = new Key(x,y,Key.Colour.BLUE);
                            break;
                        case "K4":
                            itemFile[y][x] = new Key(x,y,Key.Colour.YELLOW);
                            break;
                        default:
                            break;
                    }
                }
            }

            Tile[][] tileFile = new Tile[height][width];
            for (int y = 0; y < height; y++) {
                lineReader = new Scanner(fileReader.next());
                lineReader.useDelimiter(",");
                for (int x = 0; x < width; x++) {
                    String nextTile = lineReader.next();
                    switch(nextTile){
                        case "Pt":
                            tileFile[y][x] = new Path();
                            break;
                        case "Dt":
                            tileFile[y][x] = new Dirt(false);
                            break;
                        case "Wl":
                            tileFile[y][x] = new Wall();
                            break;
                        case "Wt":
                            tileFile[y][x] = new Water(false);
                            break;
                        case "Ex":
                            tileFile[y][x] = new Exit(x,y);
                            break;
                        default:
                            char[] tileChar = nextTile.toCharArray();
                            switch(tileChar[0]) {
                                case 'B':
                                    tileFile[y][x] = new Buttons(x,y); // // MUST CONNECT THESE < v
                                    break;
                                case 'T':
                                    tileFile[y][x] = new Trap(x,y); // MUST CONNECT THESE < ^
                                    break;
                                case 'S':
                                    int chipAmount = Integer.parseInt(nextTile.substring(1));
                                    tileFile[y][x] = new ChipSocket(chipAmount);
                                    break;
                                case 'L':
                                    if(tileChar[1] == '1') {
                                        tileFile[y][x] = new LockedDoor(Key.Colour.RED);
                                    } else if(tileChar[1] == '2') {
                                        tileFile[y][x] = new LockedDoor(Key.Colour.GREEN);
                                    } else if(tileChar[1] == '3') {
                                        tileFile[y][x] = new LockedDoor(Key.Colour.BLUE);
                                    } else if(tileChar[1] == '4') {
                                        tileFile[y][x] = new LockedDoor(Key.Colour.YELLOW);
                                    }
                                    break;
                                case 'I':
                                    if(tileChar[1] == '1') {
                                        tileFile[y][x] = new Ice(x,y,Ice.CornerType.NONE);
                                    } else if(tileChar[1] == '2') {
                                        tileFile[y][x] = new Ice(x,y,Ice.CornerType.TOP_LEFT);
                                    } else if(tileChar[1] == '3') {
                                        tileFile[y][x] = new Ice(x,y,Ice.CornerType.TOP_RIGHT);
                                    } else if(tileChar[1] == '4') {
                                        tileFile[y][x] = new Ice(x,y,Ice.CornerType.BOTTOM_LEFT);
                                    } else if(tileChar[1] == '5') {
                                        tileFile[y][x] = new Ice(x,y,Ice.CornerType.BOTTOM_RIGHT);
                                    }
                                    break;
                            }
                            break;
                    }
                }
            }
            return new Map(timeLeft, width,height,actorFile, itemFile, tileFile, givenPlayer, givenFrogs, givenBugs, givenPinkBalls,givenBlocks);
        }
        catch(Exception invalidFile) {
            System.out.println("Invalid File!");
            return null;
        }
    }

    /**
     * Reads the Map and creates a text file to save the Player's Progress.
     * If the map is already saved, it would print "File already exists".
     *
     * @param currentMap Name of Map.
     */
    public static void convertToFile(Map currentMap){
        Scanner in = new Scanner(System.in);
        Player player = currentMap.getPlayer();
        boolean complete = false;
        while(!complete) {
            try {
                System.out.println("Please enter a valid file path and name for saving:");
                File newFile = new File(in.next());
                if (newFile.createNewFile()) {
                    FileWriter WriteToFile = new FileWriter(newFile);
                    WriteToFile.write(currentMap.getBoardWidth()
                            + "," + currentMap.getBoardHeight()
                            + "," + currentMap.getTimeLeft() + "\r\n");
                    int[] playerInv = player.getInventory();
                    for (int i = 0; i < playerInv.length; i++) {
                        WriteToFile.write(Integer.toString(playerInv[i]));
                        if (i + 1 == playerInv.length) {
                            WriteToFile.write("\r\n");
                        } else {
                            WriteToFile.write(",");
                        }
                    }
                    for (int y = 0; y < currentMap.getBoardHeight(); y++) {
                        for (int x = 0; x < currentMap.getBoardWidth(); x++) {
                            Actor currentActor = currentMap.getPosActor(x, y);
                            if (currentActor instanceof Player) {
                                WriteToFile.write("Pl");
                            } else if(currentActor instanceof Frog) {
                            WriteToFile.write("Fr");
                        } else if(currentActor instanceof Bug) {
                            WriteToFile.write("Bg");
                        } else if(currentActor instanceof PinkBall) {
                            WriteToFile.write("Pb");
                        } else {
                                WriteToFile.write("0");
                            }
                            if (x + 1 != currentMap.getBoardWidth()) {
                                WriteToFile.write(",");
                            }
                        }
                        WriteToFile.write("\r\n");
                    }
                    for (int y = 0; y < currentMap.getBoardHeight(); y++) {
                        for (int x = 0; x < currentMap.getBoardWidth(); x++) {
                            Item currentItem = currentMap.getPosItem(x, y);
                            if (currentItem instanceof ComputerChip) {
                                WriteToFile.write("C");
                            } else if (currentItem instanceof Key) {
                                if (((Key) currentItem).getKeyColour() == Key.Colour.RED) {
                                    WriteToFile.write("K1");
                                } else if (((Key) currentItem).getKeyColour() == Key.Colour.GREEN) {
                                    WriteToFile.write("K2");
                                } else if (((Key) currentItem).getKeyColour() == Key.Colour.BLUE) {
                                    WriteToFile.write("K3");
                                } else if (((Key) currentItem).getKeyColour() == Key.Colour.YELLOW) {
                                    WriteToFile.write("K4");
                                }
                            } else {
                                WriteToFile.write("0");
                            }
                            if (x + 1 != currentMap.getBoardWidth()) {
                                WriteToFile.write(",");
                            }
                        }
                        WriteToFile.write("\r\n");
                    }
                    for (int y = 0; y < currentMap.getBoardHeight(); y++) {
                        for (int x = 0; x < currentMap.getBoardWidth(); x++) {
                            Tile currentTile = currentMap.getPosTile(x, y);
                            if (currentTile instanceof Ice) {
                                if (((Ice) currentTile).getCornerType() == Ice.CornerType.NONE) {
                                    WriteToFile.write("I1");
                                } else if (((Ice) currentTile).getCornerType() == Ice.CornerType.TOP_LEFT) {
                                    WriteToFile.write("I2");
                                } else if (((Ice) currentTile).getCornerType() == Ice.CornerType.TOP_RIGHT) {
                                    WriteToFile.write("I3");
                                } else if (((Ice) currentTile).getCornerType() == Ice.CornerType.BOTTOM_LEFT) {
                                    WriteToFile.write("I4");
                                } else if (((Ice) currentTile).getCornerType() == Ice.CornerType.BOTTOM_RIGHT) {
                                    WriteToFile.write("I5");
                                }
                            } else if (currentTile instanceof Dirt) {
                                WriteToFile.write("Dt");
                            } else if (currentTile instanceof Wall) {
                                WriteToFile.write("Wl");
                            } else if (currentTile instanceof Exit) {
                                WriteToFile.write("Ex");
                            } else if (currentTile instanceof Path) {
                                WriteToFile.write("Pt");
                            } else if (currentTile instanceof Water) {
                                WriteToFile.write("Wt");
                            } else if (currentTile instanceof ChipSocket) {
                                WriteToFile.write("W" + ((ChipSocket) currentTile).getChipAmountNeeded());
                            } else if (currentTile instanceof LockedDoor) {
                                if (((LockedDoor) currentTile).getDoorColour() == Key.Colour.RED) {
                                    WriteToFile.write("L1");
                                } else if (((LockedDoor) currentTile).getDoorColour() == Key.Colour.GREEN) {
                                    WriteToFile.write("L2");
                                } else if (((LockedDoor) currentTile).getDoorColour() == Key.Colour.BLUE) {
                                    WriteToFile.write("L3");
                                } else if (((LockedDoor) currentTile).getDoorColour() == Key.Colour.YELLOW) {
                                    WriteToFile.write("L4");
                                }
                            }  else if (currentTile instanceof Buttons) {
                            WriteToFile.write("B" /*+ {something}*/ + ",");
                        } else if (currentTile instanceof Trap) {
                            WriteToFile.write("T" /*+ {something}*/ + ",");
                        }  else {
                                WriteToFile.write("0");
                            }
                            if (x + 1 != currentMap.getBoardWidth()) {
                                WriteToFile.write(",");
                            }
                        }
                        if (y + 1 != currentMap.getBoardHeight()) {
                            WriteToFile.write("\r\n");
                        }
                    }
                    WriteToFile.close();
                    System.out.println("File created: " + newFile.getName());
                    complete = true;
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("Invalid File Path.");
            }
        }
    }
}