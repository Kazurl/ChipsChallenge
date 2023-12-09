import javafx.scene.input.KeyCode;

import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

public class FileConverter {

    static String userNameSaved;
    static String passwordSaved;
    static int levelProgressSaved;

    private static final int PRIME_MULTIPLIER = 31;

    // Hashing function using a prime number multiplier
    public static String encrypt(String string) {
        int hash = 0;

        // Calculate the hash code for the combined string
        for (int i = 0; i < string.length(); i++) {
            hash = (hash + PRIME_MULTIPLIER) + string.charAt(i);
        }

        // Convert the hash code to a string for output
        return String.valueOf(hash);
    }

    public static void registerAccount(String username, String password) {
        password = encrypt(password);
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;

        try (FileWriter f = new FileWriter("Credentials.txt", true);
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter p = new PrintWriter(b);) {
            p.println(username + "," + password + ",0");

        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public static int checkLevels(String username) {
        try {
            Scanner fileReader = new Scanner(new File("Credentials.txt"));
            fileReader.useDelimiter(",|\r\n|\n");
            while(fileReader.hasNext()) {
                String currentName = fileReader.next();
                if(currentName.equals(username)) {
                    fileReader.next();

                    levelProgressSaved = fileReader.nextInt();

                    return levelProgressSaved;
                }
            }
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }
    public static int checkUsername(String username) {
        try {
            int usernameIndex = 0;
            Scanner fileReader = new Scanner(new File("Credentials.txt"));
            fileReader.useDelimiter(",|\r\n|\n");
            while(fileReader.hasNext()) {
                String currentName = fileReader.next();
                if(currentName.equals(username)) {
                    userNameSaved = username;
                    return usernameIndex;
                } else {
                    fileReader.next();
                    fileReader.next();
                    usernameIndex++;
                }
            }
        } catch (Exception e) {
            return -1;
        }
        return -1;
    }
    public static boolean checkPassword(String password, int index) {
        password = encrypt(password);
        String truePassword;
        try {
            Scanner fileReader = new Scanner(new File("Credentials.txt"));
            fileReader.useDelimiter("\r\n|\n");
            for (int i = 0; i < index; i++) {
                fileReader.next();
            }
            fileReader.useDelimiter(",|\r\n|\n");
            fileReader.next();
            truePassword = fileReader.next();
            if(truePassword.equals(password)) {
                passwordSaved = password;
                return true;
            } else {
                return false;
            }

        } catch(Exception e) {
            return false;
        }
    }
    public static Map convertFromFile(String filePath, String userName){

        File newFile = new File(filePath);
        try {
            Scanner fileReader = new Scanner(newFile);
            fileReader.useDelimiter("\r\n|\n");
            Scanner lineReader = new Scanner(fileReader.next());
            lineReader.useDelimiter(",");
            int width = lineReader.nextInt();
            int height = lineReader.nextInt();
            int timeLeft = lineReader.nextInt();
            Player givenPlayer = new Player(userName);
            Block[] givenBlocks = {};
            Frog[] givenFrogs = {};
            Bug[] givenBugs = {};
            Buttons[] givenButtons = {};
            Trap[] givenTraps = {};
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
                                    Bug bug = new Bug();
                                    bug.setX(x);
                                    bug.setY(y);
                                    switch(mobChar[1]) {
                                        case '1':
                                            bug.setDirection(KeyCode.UP);
                                            break;
                                        case '2':
                                            bug.setDirection(KeyCode.DOWN);
                                            break;
                                        case '3':
                                            bug.setDirection(KeyCode.LEFT);
                                            break;
                                        default:
                                            bug.setDirection(KeyCode.RIGHT);
                                            break;
                                    }
                                    switch(mobChar[2]) {
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
            int buttonCount = 1;
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
                                    Buttons button = new Buttons(x,y);
                                    button.setIdentifier(buttonCount);
                                    buttonCount++;
                                    tileFile[y][x] = button; // // MUST CONNECT THESE < v
                                    int buttonNum = Integer.parseInt(nextTile.substring(1));
                                    if(buttonNum > givenButtons.length) {
                                        givenButtons = Arrays.copyOf(givenButtons, buttonNum);
                                    }
                                    givenButtons[buttonNum-1] = button;
                                    break;
                                case 'T':
                                    Trap trap = new Trap(x,y);
                                    tileFile[y][x] = trap; // needs its tile
                                    int trapNum = Integer.parseInt(nextTile.substring(1));
                                    if(trapNum > givenTraps.length) {
                                        givenTraps = Arrays.copyOf(givenTraps, trapNum);
                                    }
                                    givenTraps[trapNum - 1] = trap; // MUST CONNECT THESE < ^
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
            String[] givenTopNames = new String[10];
            int[] givenTopScores = new int[10];
            lineReader = new Scanner(fileReader.next());
            lineReader.useDelimiter(",");
            for(int i = 0; i < 10; i++) {
                givenTopNames[i] = lineReader.next();
                givenTopScores[i] = lineReader.nextInt();
            }
            lineReader = new Scanner(fileReader.next());
            String original = lineReader.next();
            if(original.equals("original")) {
                original = filePath;
            }

            for(int i = 0; i <givenTraps.length; i++) {
                try {
                    if(givenButtons[i] != null) {
                        givenTraps[i].setConnectButton(givenButtons[i]);
                        givenTraps[i].setIdentifier(givenButtons[i].getIdentifier());
                        givenButtons[i].setConnectTrap(givenTraps[i]);
                    }
                } catch(Exception e) {
                    System.out.println("Invalid File!");
                    return null;
                }
            }

            for(int i = 0; i <givenFrogs.length; i++) {
                givenFrogs[i].setMap(actorFile, tileFile);
            }
            return new Map(timeLeft, width,height,actorFile, itemFile, tileFile,
                    givenPlayer, givenFrogs, givenBugs, givenPinkBalls,givenBlocks
                    , givenButtons, givenTopScores, givenTopNames, original);
        }
        catch(Exception invalidFile) {
            System.out.println("Invalid File!");
            return null;
        }
    }
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
                        } else if(currentActor instanceof Block) {
                                WriteToFile.write("Bl");
                            } else if(currentActor instanceof Bug) {
                            WriteToFile.write("B");
                                if (currentActor.getDirection() == KeyCode.UP) {
                                    WriteToFile.write("1");
                                } else if (currentActor.getDirection() == KeyCode.DOWN) {
                                    WriteToFile.write("2");
                                } else if (currentActor.getDirection() == KeyCode.LEFT) {
                                    WriteToFile.write("3");
                                } else if (currentActor.getDirection() == KeyCode.RIGHT) {
                                    WriteToFile.write("4");
                                }
                                if (((Bug) currentActor).getFollow()) {
                                    WriteToFile.write("1");
                                } else {
                                    WriteToFile.write("2");
                                }
                        } else if(currentActor instanceof PinkBall) {
                                if (currentActor.getDirection() == KeyCode.UP) {
                                    WriteToFile.write("P1");
                                } else if (currentActor.getDirection() == KeyCode.DOWN) {
                                    WriteToFile.write("P2");
                                } else if (currentActor.getDirection() == KeyCode.LEFT) {
                                    WriteToFile.write("P3");
                                } else if (currentActor.getDirection() == KeyCode.RIGHT) {
                                    WriteToFile.write("P4");
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
                                WriteToFile.write("S" + ((ChipSocket) currentTile).getChipAmountNeeded());
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
                            WriteToFile.write("B" + ((Buttons) currentTile).getIdentifier());
                        } else if (currentTile instanceof Trap) {
                            WriteToFile.write("T" + ((Trap) currentTile).getIdentifier());
                        }  else {
                                WriteToFile.write("0");
                            }
                            if (x + 1 != currentMap.getBoardWidth()) {
                                WriteToFile.write(",");
                            }
                        }
                        WriteToFile.write("\r\n");
                    }
                    String[] names = currentMap.getTopNames();
                    int[] scores = currentMap.getTopScores();
                    for(int i = 0; i < 10; i++) {
                        WriteToFile.write(names[i] + "," + scores[i]);
                        if (i + 1 != 10) {
                            WriteToFile.write(",");
                        }
                    }
                    WriteToFile.write("\r\n");
                    WriteToFile.write(GameLogic.getGameMap().getOriginal());

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
    public static void changeScore() {
        /*
        File originalFile = new File(GameLogic.getGameMap().getOriginal());
        try {
            Scanner fileReader = new Scanner(originalFile);
            fileReader.
            FileWriter fileWriter = new FileWriter(originalFile);
        } catch(Exception e) {

        }
         */
    }
}