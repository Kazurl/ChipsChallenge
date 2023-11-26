import java.util.Scanner;

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
    }
}
