import java.util.ArrayList;

public class Map<O> {

    private int boardWidth;
    private int boardHeight;
    private int currentTime;
    private int timeLimit;

    private ArrayList<Actors>[][] actorLayerMap
    //private ArrayList<{Name of Interactables class (proably Interactables)}>[][] interactableLayerMap
    private ArrayList<Tiles>[][] tileLayerMap

    public Map (int timeLimit, int width, int height){
        this.actorLayerMap  = new ArrayList[height][width];
        //this.interactablesLayerMap  = new ArrayList[height][width];
        this.tileLayerMap  = new ArrayList[height][width];
        this.boardWidth = width;
        this.boardHeight = height;
        this.currentTime = 0;
        this.timeLimit = timeLimit;
    }

    public Map (int timeLimit,int width, int height, Actors[][] actorMap/*, Interactables[][] interactableMap*/, Tiles[][] tileMap){
        this.boardWidth = width;
        this.boardHeight = height;
        this.currentTime = 0;
        this.timeLimit = timeLimit;
        this.actorLayerMap  = actorMap;
        //this.interactablesLayerMap  = interactableMap;
        this.tileLayerMap  = tileMap;
    }

    public O getPos(int layerNum, int posX, int posY) {
        switch(layerNum) {
            case 1:
                return actorLayerMap[posY][posX];
            /*
            case 2:
                return interactablesLayerMap[posY][posX];
            */
            case 3:
                return tileLayerMap[posY][posX];
            default:
                return null;
        }
    } 

    public void setPos(int layerNum, int posX, int posY, O object) {
        switch(layerNum) {
            case 1:
                actorLayerMap[posY][posX] = object;
            /*
            case 2:
                interactableLayerMap[posY][posX] = object;
            */
            case 3:
            tileLayerMap[posY][posX] = object;
            default:
                break;
        }
    }

    public O[] getBoard() {
        private ArrayList<O[][]>wholeBoard = new ArrayList<>[3];
        wholeBoard.set(0, actorLayerMap);
        //wholeBoard.set(1, interactablesLayerMap);
        wholeBoard.set(2, tileLayerMap);
        return wholeBoard;
    }

    public void setBoard(O[] objects) {
        actorLayerMap = objects[0];
        //interactableLayerMap = objects[1];
        tileLayerMap[posY][posX] = objects[2];
    }
}