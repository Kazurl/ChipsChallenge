import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public class FileConverter {
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
                        case "Pb":
                            actorFile[y][x] = new PinkBall(); // needs its tile
                            break;
                        case "Bg":
                            actorFile[y][x] = new Bug(); // needs its tile
                            break;
                        case "Fr":
                            actorFile[y][x] = new Frog(); // needs its tile
                            break;
                        case "Bl":
                            actorFile[y][x] = new Block(); // needs its tile??
                            break;
                        default:
                            break;
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
            return new Map(timeLeft, width,height,actorFile, itemFile, tileFile, givenPlayer);
        }
        catch(Exception invalidFile) {
            System.out.println("Invalid File!");
            invalidFile.printStackTrace();
            return null;
        }
    }
    public static void convertToFile(Map currentMap, Player player){
        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Please enter a valid file path and name for saving:");
            File newFile = new File(in.next());
            if (newFile.createNewFile()) {
                FileWriter WriteToFile = new FileWriter(newFile);
                WriteToFile.write(currentMap.getBoardWidth()
                        + "," + currentMap.getBoardHeight()
                        + "," + currentMap.getTimeLeft() + "\r\n");
                int[] playerInv = player.getInventory();
                for(int i = 0; i<playerInv.length; i++) {
                    WriteToFile.write(Integer.toString(playerInv[i]));
                    if (i+1 == playerInv.length) {
                        WriteToFile.write("\r\n");
                    } else {
                        WriteToFile.write(",");
                    }
                }
                for (int y = 0; y < currentMap.getBoardHeight(); y++) {
                    for (int x = 0; x < currentMap.getBoardWidth(); x++) {
                        Actor currentActor = currentMap.getPosActor(x,y);
                        if(currentActor instanceof Player) {
                            WriteToFile.write("Pl");
                        } /*else if(currentActor instanceof Frog) {
                            WriteToFile.write("Fr");
                        } else if(currentActor instanceof Bug) {
                            WriteToFile.write("Bg");
                        } else if(currentActor instanceof PinkBall) {
                            WriteToFile.write("Pb");
                        } */ else {
                            WriteToFile.write("0");
                        }
                        if (x+1 != currentMap.getBoardWidth()) {
                            WriteToFile.write(",");
                        }
                    }
                    WriteToFile.write("\r\n");
                }
                for (int y = 0; y < currentMap.getBoardHeight(); y++) {
                    for (int x = 0; x < currentMap.getBoardWidth(); x++) {
                        Item currentItem = currentMap.getPosItem(x,y);
                        if(currentItem instanceof ComputerChip) {
                            WriteToFile.write("C");
                        } else if(currentItem instanceof Key) {
                            if(((Key) currentItem).getKeyColour() == Key.Colour.RED) {
                                WriteToFile.write("K1");
                            } else if(((Key) currentItem).getKeyColour() == Key.Colour.GREEN) {
                                WriteToFile.write("K2");
                            } else if(((Key) currentItem).getKeyColour() == Key.Colour.BLUE) {
                                WriteToFile.write("K3");
                            } else if(((Key) currentItem).getKeyColour() == Key.Colour.YELLOW) {
                                WriteToFile.write("K4");
                            }
                        } else {
                                WriteToFile.write("0");
                        }
                        if (x+1 != currentMap.getBoardWidth()) {
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
                        } /* else if (currentTile instanceof Button) {
                            WriteToFile.write("B" + {something} + ",");
                        } else if (currentTile instanceof Trap) {
                            WriteToFile.write("T" + {something} + ",");
                        } */ else {
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
            }  else {
                System.out.println("File already exists.");
            }
    } catch (IOException e) {
        System.out.println("An error occurred.");
    }
    }
}