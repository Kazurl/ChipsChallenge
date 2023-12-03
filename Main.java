import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.util.Scanner;

public class Main extends Application {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 500;

    // The dimensions of the canvas
    private static int CANVAS_WIDTH;
    private static int CANVAS_HEIGHT;

    // The width and height (in pixels) of each cell that makes up the game.
    private static int GRID_CELL_WIDTH = 50;
    private static int GRID_CELL_HEIGHT = 50;

    // The width of the grid in number of cells.
    private static int GRID_WIDTH;

    private static int GRID_HEIGHT;
    // The canvas in the GUI. This needs to be a global variable
    // (in this setup) as we need to access it in different methods.
    // We could use FXML to place code in the controller instead.
    private Canvas canvas;

    // Loaded images
    private Image playerImage;
    private Image dirtImage;
    private Image iconImage;
    private Image bugImage;
    private Image buttonImage;
    private Image chipSocketImage;
    private Image compChipImage;
    private Image exitImage;
    private Image frogImage;
    private Image iceImage;
    private Image redKeyImage;
    private Image greenKeyImage;
    private Image blueKeyImage;
    private Image yellowKeyImage;
    private Image redLockedDoorImage;
    private Image greenLockedDoorImage;
    private Image blueLockedDoorImage;
    private Image yellowLockedDoorImage;
    private Image pinkBallImage;
    private Image trapImage;
    private Image wallImage;
    private Image waterImage;
    private Image pathImage;
    private Image TLIceImage;
    private Image TRIceImage;
    private Image BLIceImage;
    private Image BRIceImage;

    // X and Y coordinate of player on the grid.
    private int playerX;
    private int playerY;

    // Timeline which will cause tick method to be called periodically.
    private Timeline tickTimeline;

    public void start(Stage primaryStage) {

        playerImage = new Image("player.png");
        dirtImage = new Image("dirt.png");
        iconImage = new Image("icon.png");
        bugImage = new Image("Bug.png");
        buttonImage = new Image("Button.png");
        chipSocketImage = new Image("ChipSocket.png");
        compChipImage = new Image("CompChip.png");
        exitImage = new Image("Exit.png");
        frogImage = new Image("Frog.png");
        iceImage = new Image("Ice.png");
        redKeyImage = new Image("redKey.png");
        greenKeyImage = new Image("greenKey.png");
        blueKeyImage = new Image("blueKey.png");
        yellowKeyImage = new Image("yellowKey.png");
        redLockedDoorImage = new Image("redLockedDoor.png");
        greenLockedDoorImage = new Image("greenLockedDoor.png");
        blueLockedDoorImage = new Image("blueLockedDoor.png");
        yellowLockedDoorImage = new Image("yellowLockedDoor.png");
        pinkBallImage = new Image("PinkBall.png");
        trapImage = new Image("Trap.png");
        wallImage = new Image("Wall.png");
        waterImage = new Image("Water.png");
        pathImage = new Image("path.png");
        TLIceImage = new Image("IceTL.png");
        TRIceImage = new Image("IceTR.png");
        BLIceImage = new Image("IceBL.png");
        BRIceImage = new Image("IceBR.png");
        // Build the GUI
        Pane root = buildGUI();

        // Create a scene from the GUI
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Register an event handler for key presses.
        // This causes the processKeyEvent method to be called each time a key is pressed.
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event));

        // Register a tick method to be called periodically.
        // Make a new timeline with one keyframe that triggers the tick method every half a second.
        tickTimeline = new Timeline(new KeyFrame(Duration.millis(500), event -> tick()));
        // Loop the timeline forever
        tickTimeline.setCycleCount(Animation.INDEFINITE);
        // We start the timeline upon a button press.

        // Display the scene on the stage
        drawGame();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void tick() {
        //Mobs move
        // We then redraw the whole canvas.
        drawGame();
    }
    public void canvasDragDroppedOccured(DragEvent event) {
        double x = event.getX();
        double y = event.getY();

        // Print a string showing the location.
        String s = String.format("You dropped at (%f, %f) relative to the canvas.", x, y);
        System.out.println(s);

        // Draw an icon at the dropped location.
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // Draw the image so the top-left corner is where we dropped.
        gc.drawImage(iconImage, x, y);
        // Draw the image so the center is where we dropped.
        // gc.drawImage(iconImage, x - iconImage.getWidth() / 2.0, y - iconImage.getHeight() / 2.0);
    }

    public void processKeyEvent(KeyEvent event) {
        // We change the behaviour depending on the actual key that was pressed.
        GameLogic.movePlayer(event);
        if(GameLogic.checkStatus()) {
            drawEnd();
        }
        // Redraw game as the player may have moved.
        drawGame();

        // Consume the event. This means we mark it as dealt with. This stops other GUI nodes (buttons etc) responding to it.
        event.consume();
    }
    private Pane buildGUI() {
        // Create top-level panel that will hold all GUI nodes.
        BorderPane root = new BorderPane();

        // Create the canvas that we will draw on.
        // We store this as a gloabl variable so other methods can access it.
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        root.setCenter(canvas);

        // Create a toolbar with some nice padding and spacing
        HBox toolbar = new HBox();
        toolbar.setSpacing(10);
        toolbar.setPadding(new Insets(10, 10, 10, 10));
        root.setTop(toolbar);

        // Create the toolbar content


        // Tick Timeline buttons
        Button startTickTimelineButton = new Button("Start Ticks");
        Button stopTickTimelineButton = new Button("Stop Ticks");
        // We add both buttons at the same time to the timeline (we could have done this in two steps).
        toolbar.getChildren().addAll(startTickTimelineButton, stopTickTimelineButton);
        // Stop button is disabled by default
        stopTickTimelineButton.setDisable(true);

        // Set up the behaviour of the buttons.
        startTickTimelineButton.setOnAction(e -> {
            // Start the tick timeline and enable/disable buttons as appropriate.
            startTickTimelineButton.setDisable(true);
            tickTimeline.play();
            stopTickTimelineButton.setDisable(false);
        });

        stopTickTimelineButton.setOnAction(e -> {
            // Stop the tick timeline and enable/disable buttons as appropriate.
            stopTickTimelineButton.setDisable(true);
            tickTimeline.stop();
            startTickTimelineButton.setDisable(false);
        });

        // Setup a draggable image.
        ImageView draggableImage = new ImageView();
        draggableImage.setImage(iconImage);
        toolbar.getChildren().add(draggableImage);

        // This code setup what happens when the dragging starts on the image.
        // You probably don't need to change this (unless you wish to do more advanced things).
        draggableImage.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                // Mark the drag as started.
                // We do not use the transfer mode (this can be used to indicate different forms
                // of drags operations, for example, moving files or copying files).
                Dragboard db = draggableImage.startDragAndDrop(TransferMode.ANY);

                // We have to put some content in the clipboard of the drag event.
                // We do not use this, but we could use it to store extra data if we wished.
                ClipboardContent content = new ClipboardContent();
                content.putString("Hello");
                db.setContent(content);

                // Consume the event. This means we mark it as dealt with.
                event.consume();
            }
        });

        // This code allows the canvas to receive a dragged object within its bounds.
        // You probably don't need to change this (unless you wish to do more advanced things).
        canvas.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                // Mark the drag as acceptable if the source was the draggable image.
                // (for example, we don't want to allow the user to drag things or files into our application)
                if (event.getGestureSource() == draggableImage) {
                    // Mark the drag event as acceptable by the canvas.
                    event.acceptTransferModes(TransferMode.ANY);
                    // Consume the event. This means we mark it as dealt with.
                    event.consume();
                }
            }
        });

        // This code allows the canvas to react to a dragged object when it is finally dropped.
        // You probably don't need to change this (unless you wish to do more advanced things).
        canvas.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                // We call this method which is where the bulk of the behaviour takes place.
                canvasDragDroppedOccured(event);
                // Consume the event. This means we mark it as dealt with.
                event.consume();
            }
        });

        // Finally, return the border pane we built up.
        return root;
    }
    public void drawGame() {
        // Get the Graphic Context of the canvas. This is what we draw on.
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Clear canvas
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Set the background to gray.
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        //Draw peripherals
        int[] invToShow = GameLogic.getGameMap().getPlayer().getInventory();
        gc.drawImage(redKeyImage, GRID_CELL_WIDTH* 0.5, GRID_CELL_HEIGHT * 0.1);
        gc.drawImage(greenKeyImage, GRID_CELL_WIDTH* 0.6, GRID_CELL_HEIGHT * 0.1);
        gc.drawImage(blueKeyImage, GRID_CELL_WIDTH* 0.7, GRID_CELL_HEIGHT * 0.1);
        gc.drawImage(yellowKeyImage, GRID_CELL_WIDTH* 0.8, GRID_CELL_HEIGHT * 0.1);
        gc.drawImage(compChipImage, GRID_CELL_WIDTH * 0.9, GRID_CELL_HEIGHT * 0.1);

        // Draw tiles
        // We multiply by the cell width and height to turn a coordinate in our grid into a pixel coordinate.
        for (int y = 0; y < GRID_HEIGHT; y++) {
            for (int x = 0; x < GRID_WIDTH; x++) {
                if(GameLogic.getGameMap().getPosTile(x,y) instanceof Path) {
                    gc.drawImage(pathImage, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                } else if(GameLogic.getGameMap().getPosTile(x,y) instanceof Wall) {
                    gc.drawImage(wallImage, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                } else if(GameLogic.getGameMap().getPosTile(x,y) instanceof Water) {
                    gc.drawImage(waterImage, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                } else if(GameLogic.getGameMap().getPosTile(x,y) instanceof Exit) {
                    gc.drawImage(exitImage, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                } else if(GameLogic.getGameMap().getPosTile(x,y) instanceof Trap) {
                    gc.drawImage(trapImage, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                } else if(GameLogic.getGameMap().getPosTile(x,y) instanceof Buttons) {
                    gc.drawImage(buttonImage, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                } else if(GameLogic.getGameMap().getPosTile(x,y) instanceof Ice) {
                    if(((Ice) GameLogic.getGameMap().getPosTile(x,y)).getCornerType() == Ice.CornerType.NONE) {
                        gc.drawImage(iceImage, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                    } else if(((Ice) GameLogic.getGameMap().getPosTile(x,y)).getCornerType() == Ice.CornerType.TOP_LEFT) {
                        gc.drawImage(TLIceImage, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT); //NEW PLACEHOLDER NEEDED
                    }else if(((Ice) GameLogic.getGameMap().getPosTile(x,y)).getCornerType() == Ice.CornerType.TOP_RIGHT) {
                        gc.drawImage(TRIceImage, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT); //NEW PLACEHOLDER NEEDED
                    }else if(((Ice) GameLogic.getGameMap().getPosTile(x,y)).getCornerType() == Ice.CornerType.BOTTOM_LEFT) {
                        gc.drawImage(BLIceImage, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT); //NEW PLACEHOLDER NEEDED
                    }else if(((Ice) GameLogic.getGameMap().getPosTile(x,y)).getCornerType() == Ice.CornerType.BOTTOM_RIGHT) {
                        gc.drawImage(BRIceImage, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT); //NEW PLACEHOLDER NEEDED
                    }
                } else if(GameLogic.getGameMap().getPosTile(x,y) instanceof Dirt) {
                gc.drawImage(dirtImage, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                } else if(GameLogic.getGameMap().getPosTile(x,y) instanceof LockedDoor) {
                    if( ((LockedDoor) GameLogic.getGameMap().getPosTile(x,y)).getDoorColour() == Key.Colour.RED) {
                        gc.drawImage(redLockedDoorImage, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                    } else if( ((LockedDoor) GameLogic.getGameMap().getPosTile(x,y)).getDoorColour() == Key.Colour.GREEN) {
                        gc.drawImage(greenLockedDoorImage, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                    }else if( ((LockedDoor) GameLogic.getGameMap().getPosTile(x,y)).getDoorColour() == Key.Colour.BLUE) {
                        gc.drawImage(blueLockedDoorImage, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                    }else if( ((LockedDoor) GameLogic.getGameMap().getPosTile(x,y)).getDoorColour() == Key.Colour.YELLOW) {
                        gc.drawImage(yellowLockedDoorImage, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                    }
                } else if(GameLogic.getGameMap().getPosTile(x,y) instanceof ChipSocket) {
                    gc.drawImage(chipSocketImage, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                    //show amount of chips needed
                }
            }
        }
        // Draw items
        for (int y = 0; y < GRID_HEIGHT; y++) {
            for (int x = 0; x < GRID_WIDTH; x++) {
                if(GameLogic.getGameMap().getPosItem(x,y) instanceof ComputerChip) {
                    gc.drawImage(compChipImage, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                } else if(GameLogic.getGameMap().getPosItem(x,y) instanceof Key) {
                    if(((Key) GameLogic.getGameMap().getPosItem(x,y)).getKeyColour() == Key.Colour.RED) {
                        gc.drawImage(redKeyImage, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                    } else if(((Key) GameLogic.getGameMap().getPosItem(x,y)).getKeyColour() == Key.Colour.GREEN) {
                        gc.drawImage(greenKeyImage, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                    }else if(((Key) GameLogic.getGameMap().getPosItem(x,y)).getKeyColour() == Key.Colour.BLUE) {
                        gc.drawImage(blueKeyImage, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                    }else if(((Key) GameLogic.getGameMap().getPosItem(x,y)).getKeyColour() == Key.Colour.YELLOW) {
                        gc.drawImage(yellowKeyImage, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                    }
                }
            }
        }
        // Draw player at current location // COULD CAUSE PROBLEMS IF ACTOR MOVEMENT IS DONE WRONG
                for (int y = 0; y < GRID_HEIGHT; y++) {
                    for (int x = 0; x < GRID_WIDTH; x++) {
                        if(GameLogic.getGameMap().getPosActor(x,y) instanceof Frog) {
                            gc.drawImage(frogImage,x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                        } else if(GameLogic.getGameMap().getPosActor(x,y) instanceof Bug) {
                            gc.drawImage(bugImage,x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                        } else if(GameLogic.getGameMap().getPosActor(x,y) instanceof PinkBall) {
                            gc.drawImage(pinkBallImage,x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                        }
                    }
                }
        gc.drawImage(playerImage,
                GameLogic.getGameMap().getPlayer().getX() * GRID_CELL_WIDTH,
                GameLogic.getGameMap().getPlayer().getY() * GRID_CELL_HEIGHT);
    }
    public void drawEnd() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Clear canvas
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Set the background to gray.
        gc.setFill(Color.GREEN);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Map newMap;
        do {
            System.out.println("Input File Path for loading:");
            String mapFilePath = in.next();
            newMap = FileConverter.convertFromFile(mapFilePath);
        } while (newMap == null);
        GRID_WIDTH = newMap.getBoardWidth();
        GRID_HEIGHT = newMap.getBoardHeight();
        CANVAS_HEIGHT = newMap.getBoardHeight() * GRID_CELL_HEIGHT;
        CANVAS_WIDTH = newMap.getBoardWidth() * GRID_CELL_WIDTH;
        GameLogic.setGameMap(newMap);
        /*
        FileConverter.convertToFile(newMap, newMap.getPlayer());
         */
        System.out.println("That is a valid file path, game starting...");
        launch(args);
    }
}
