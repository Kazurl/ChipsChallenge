import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Map newMap;
        do {
            System.out.println("Input File Path for loading:");
            String mapFilePath = in.next();
            newMap = FileConverter.convertFromFile(mapFilePath);
        } while (newMap == null);
        FileConverter.convertToFile(newMap, newMap.getPlayer());
        System.out.println("That is a valid file path, game starting...");
        Scheduler timer = new Scheduler();
        Frog[] frogs = newMap.getFrogs();
        PinkBall[] pinkBalls = newMap.getPinkBalls();
        Bug[] bugs = newMap.getBugs();
        update(timer, newMap);
    }

    private static void update(Scheduler timer, Map map, Frog[] frogs, PinkBall[] pinkBalls, Bug[] bugs) {
        if (timer.updateTick(0.016) == -1) {
            // Start end of game process
            System.out.println("End of game.");
        } 
        double currTime = timer.getTick();
        Map currMap = map;
        if (currTime%5 ==0) { //Update frog position
            // Need a method to get Frog (but need to update map within frog for algorithm to work too)
            // frog.setMap(map.getActorMap(), map.getTileMap());
            // int nextRow, nextCol = frog.checkShortest;
            // frog.setLocation(nextRow, nextCol);
        } 
        if (currTime%3 ==0) { //Update pinkball position
            // Need a method to get PinkBall
            // int randomNumber = rand.nextInt(4);
            // switch (randomNumber) {
            // case 0:
            //  //Option 1
            //  if (map.getTileMap()[pb.getLocationX()-1][ pb.getLocationY()].getWalkable()){
            //      pb.setLocation(pb.getLocationX()-1, pb.getLocationY());
            //  }
            //  break;
            // case 1:
            //  // Option 2
            //  if (map.getTileMap()[pb.getLocationX()+1][ pb.getLocationY()].getWalkable()){
            //      pb.setLocation(pb.getLocationX()+1, pb.getLocationY());
            //  }
            //  break;
            // case 2:
            //  Option 3
            //  if (map.getTileMap()[pb.getLocationX()][ pb.getLocationY()+1].getWalkable()){
            //      pb.setLocation(pb.getLocationX(), pb.getLocationY()+1);
            //  }
            //  break;
            // case 3:
            //  Option 4
            //  if (map.getTileMap()[pb.getLocationX()][ pb.getLocationY()-1].getWalkable()){
            //      pb.setLocation(pb.getLocationX(), pb.getLocationY()-1);
            //  }
            //  break;
            // default:
            //  System.out.println("Not moving this turn");
        }
        if (currTime%4 ==0) { //Update bug position
            // Need a method to get Bug
            // int randomNumber = rand.nextInt(4);
            // switch (randomNumber) {
            // case 0:
            //  //Option 1
            //  if (map.getTileMap()[bg.getLocationX()-1][bg.getLocationY()].getWalkable()){
            //      bg.setLocation(bg.getLocationX()-1, bg.getLocationY());
            //  }
            //  break;
            // case 1:
            //  // Option 2
            //  if (map.getTileMap()[bg.getLocationX()+1][bg.getLocationY()].getWalkable()){
            //      bg.setLocation(bg.getLocationX()+1, bg.getLocationY());
            //  }
            //  break;
            // case 2:
            //  Option 3
            //  if (map.getTileMap()[bg.getLocationX()][bg.getLocationY()+1].getWalkable()){
            //      bg.setLocation(bg.getLocationX(), bg.getLocationY()+1);
            //  }
            //  break;
            // case 3:
            //  Option 4
            //  if (map.getTileMap()[bg.getLocationX()][bg.getLocationY()-1].getWalkable()){
            //      bg.setLocation(bg.getLocationX(), bg.getLocationY()-1);
            //  }
            //  break;
            // default:
            //  System.out.println("Not moving this turn");
        }
    }
}
