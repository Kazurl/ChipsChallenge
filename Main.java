import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


import java.io.*;
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
    private Image blockImage;

    private TextField usernameInput;
    private Label label;
    private Label label2;
    private PasswordField passwordInput;

    private Button submitButton;

    private Button loginButton;

    private Button registerButton;

    // X and Y coordinate of player on the grid.
    private int playerX;
    private int playerY;

    // Timeline which will cause tick method to be called periodically.
    private Timeline tickTimeline;

    /**
     * The main entry point for the JavaFX application.
     *
     * @param primaryStage The primary stage for this application.
     */

    public void start(Stage primaryStage) {

        playerImage = new Image("player.png");
        dirtImage = new Image("dirt2.png");
        bugImage = new Image("Bug.png");
        buttonImage = new Image("Button.png");
        chipSocketImage = new Image("Socket.png");
        compChipImage = new Image("computer chip.png");
        exitImage = new Image("Exit.png");
        frogImage = new Image("Frog.png");
        iceImage = new Image("Ice.png");
        redKeyImage = new Image("redKey.png");
        greenKeyImage = new Image("greenKey.png");
        blueKeyImage = new Image("blueKey.png");
        yellowKeyImage = new Image("yellowKey.png");
        redLockedDoorImage = new Image("RedLock.png");
        greenLockedDoorImage = new Image("GreenLock.png");
        blueLockedDoorImage = new Image("BlueLock.png");
        yellowLockedDoorImage = new Image("YellowLock.png");
        pinkBallImage = new Image("PinkBall.png");
        trapImage = new Image("Trap.png");
        wallImage = new Image("Wall.png");
        waterImage = new Image("Water.png");
        pathImage = new Image("path.png");
        TLIceImage = new Image("IceTopLeft.png");
        TRIceImage = new Image("IceTopRight.png");
        BLIceImage = new Image("IceaBottomLeft.png");
        BRIceImage = new Image("IceBottomRight.png");
        blockImage = new Image("Block.png");


        loginGUI(primaryStage);
        /*
        // Build the GUI
        Pane root = buildGUI();

        // Create a scene from the GUI
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Register an event handler for key presses.
        // This causes the processKeyEvent method to be called each time a key is pressed.
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event));

        // Register a tick method to be called periodically.
        // Make a new timeline with one keyframe that triggers the tick method every half a second.
        tickTimeline = new Timeline(new KeyFrame(Duration.millis(100), event -> tick()));
        // Loop the timeline forever
        tickTimeline.setCycleCount(Animation.INDEFINITE);
        // We start the timeline upon a button press.

        // Display the scene on the stage
        drawGame();
        login();
        primaryStage.setScene(scene);
        primaryStage.show();
        */
    }
    public void loginGUI(Stage stage) { //Azmeera
        BorderPane loginPane = new BorderPane();
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        label = new Label("Welcome");
        label.setFont(new Font(50));
        loginButton = new Button("Log In");
        loginButton.setPrefSize(200, 120);
        registerButton = new Button("Sign Up");
        registerButton.setPrefSize(200, 120);

        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Register here");
                registerGUI(stage);
            }
        });
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                usernameGUI(stage);
            }
        });
        hbox.getChildren().addAll(label,registerButton, loginButton);
        loginPane.setCenter(hbox);
        Scene scene = new Scene(loginPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    public void registerGUI(Stage stage) { //Azmeera
        BorderPane registerPane = new BorderPane();
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(15, 12, 15, 12));
        vbox.setSpacing(10);
        vbox.setStyle("-fx-background-color: #336699;");
        label = new Label("Username: ");
        usernameInput = new TextField();
        label2 = new Label("Password: ");
        passwordInput = new PasswordField();
        submitButton = new Button("Enter");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int usernameIndex = FileConverter.checkUsername(usernameInput.getText());
                if (usernameIndex == -1) {
                    System.out.println("valid username");
                    FileConverter.registerAccount(usernameInput.getText(), passwordInput.getText());
                    loginGUI(stage);
                } else {
                    System.out.println("USERNAME TAKEN");
                }
            }
        });
        vbox.getChildren().addAll(label, usernameInput, label2, passwordInput, submitButton);
        registerPane.setCenter(vbox);
        Scene scene = new Scene(registerPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }


    public void usernameGUI(Stage stage) { //Azmeera
        BorderPane loginPane = new BorderPane();
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        label = new Label("Username: ");
        usernameInput = new TextField();
        submitButton = new Button("Enter");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int usernameIndex = FileConverter.checkUsername(usernameInput.getText());
                if (usernameIndex > -1) {
                    System.out.println("valid username");
                    passwordGUI(stage,usernameIndex);
                } else {
                    System.out.println("WRONG USERNAME");
                }
            }
        });
        hbox.getChildren().addAll(label, usernameInput, submitButton);
        loginPane.setCenter(hbox);
        Scene scene = new Scene(loginPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    public void passwordGUI(Stage stage, int index) { //Azmeera
        BorderPane loginPane = new BorderPane();
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        label = new Label("Password: ");
        passwordInput = new PasswordField();
        submitButton = new Button("Enter");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (FileConverter.checkPassword(passwordInput.getText(), index)) {
                    System.out.println("correct Password");
                    saveOrLoad(stage, usernameInput.getText());
                } else {
                    System.out.println("WRONG PASSWORD");
                }
            }
        });
        hbox.getChildren().addAll(label, passwordInput, submitButton);
        loginPane.setCenter(hbox);
        Scene scene = new Scene(loginPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }




    /**
     * Handles the tick event, called periodically. Updates game positions and triggers various actions.
     */
    public void tick() {
        GameLogic.getGameMap().getSchedule().updateTick();
        GameLogic.updatePositions();
        if(GameLogic.checkStatus()) {
            tickTimeline.stop();
            drawEnd();
            return;
        }
        // Move player every 3 ticks
        if(GameLogic.getGameMap().getSchedule().getTick()%3 == 0) {

            GameLogic.movePlayer(GameLogic.getNextMove());
            GameLogic.setNextMove(KeyCode.E);

        }
        // Move PinkBalls every 5 ticks
        if(GameLogic.getGameMap().getSchedule().getTick()%5 == 0) {
            GameLogic.movePinkBalls();
        }
        // Move Frogs every 10 ticks
        if(GameLogic.getGameMap().getSchedule().getTick()%10 == 0) {
            GameLogic.moveFrogs();
        }
        // Move Bugs every 7 ticks
        if(GameLogic.getGameMap().getSchedule().getTick()%7 == 0) {
            GameLogic.moveBugs();
        }
        if(GameLogic.getGameMap().getSchedule().getTick()%10 == 0) {
            GameLogic.getGameMap().setTimeLeft(GameLogic.getGameMap().getTimeLeft()-1);
        }
        drawGame();
    }

    /**
     * Handles key events when a key is pressed. Changes behavior based on the pressed key.
     *
     * @param event The key event.
     */

    public void processKeyEvent(KeyEvent event) {
        // We change the behaviour depending on the actual key that was pressed.
        if(!(GameLogic.getGameMap().getPosTile(GameLogic.getGameMap().getPlayer().getX(),GameLogic.getGameMap().getPlayer().getY()) instanceof Ice)) {
            GameLogic.setNextMove(event.getCode());
        }
        // Consume the event. This means we mark it as dealt with. This stops other GUI nodes (buttons etc) responding to it.
        event.consume();
    }
    /**
     * Builds the graphical user interface (GUI) for the game.
     *
     * @return The root pane of the GUI.
     */
    private Pane buildGUI() {
        // Create top-level panel that will hold all GUI nodes.
        BorderPane root = new BorderPane();

        // Create the canvas that we will draw on.
        // We store this as a gloabl variable so other methods can access it.
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        root.setCenter(canvas);

        // Create a toolbar with some nice padding and spacing
        HBox toolbar = new HBox();
        toolbar.setSpacing(5);
        toolbar.setPadding(new Insets(10, 10, 10, 10));
        root.setTop(toolbar);

        // Create the toolbar content

        // Tick Timeline buttons
        Button startTickTimelineButton = new Button("Start Ticks");
        Button stopTickTimelineButton = new Button("Stop Ticks");
        Button saveButton = new Button("Save & Quit");
        // We add both buttons at the same time to the timeline (we could have done this in two steps).
        toolbar.getChildren().addAll(startTickTimelineButton, stopTickTimelineButton, saveButton);
        // Stop button is disabled by default
        stopTickTimelineButton.setDisable(true);

        // save behaviour
        saveButton.setOnAction(e -> {
            FileConverter.convertToFile(GameLogic.getGameMap());
            Platform.exit();
        });
        // Set up the behaviour of the buttons.
        startTickTimelineButton.setOnAction(e -> {
            // Start the tick timeline and enable/disable buttons as appropriate.
            startTickTimelineButton.setDisable(true);
            GameLogic.getGameMap().getSchedule().unPause();
            tickTimeline.play();
            stopTickTimelineButton.setDisable(false);
        });

        stopTickTimelineButton.setOnAction(e -> {
            // Stop the tick timeline and enable/disable buttons as appropriate.
            stopTickTimelineButton.setDisable(true);
            GameLogic.getGameMap().getSchedule().pause();
            tickTimeline.stop();
            startTickTimelineButton.setDisable(false);
        });
        // This code allows the canvas to receive a dragged object within its bounds.
        // You probably don't need to change this (unless you wish to do more advanced things).


        // This code allows the canvas to react to a dragged object when it is finally dropped.
        // You probably don't need to change this (unless you wish to do more advanced things).

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
                    gc.setFill(Color.RED);
                    gc.fillText(String.valueOf(((ChipSocket) GameLogic.getGameMap().getPosTile(x,y)).getChipAmountNeeded()), (x+0.45) * GRID_CELL_WIDTH, (y+0.59) * GRID_CELL_HEIGHT);
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
                } else if(GameLogic.getGameMap().getPosActor(x,y) instanceof Block) {
                    gc.drawImage(blockImage,x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                }
            }
        }
        if(GameLogic.getGameMap().getPlayer().isAlive()) {
            gc.drawImage(playerImage,
                    GameLogic.getGameMap().getPlayer().getX() * GRID_CELL_WIDTH,
                    GameLogic.getGameMap().getPlayer().getY() * GRID_CELL_HEIGHT);
        }
        // draw timer overlay
        gc.setFill(Color.RED);
        gc.fillText(String.valueOf(GameLogic.getGameMap().getTimeLeft()), CANVAS_WIDTH  - 3, 0);
        //Draw inventory bar
        int[] invToShow = GameLogic.getGameMap().getPlayer().getInventory();
        gc.setFill(Color.RED);
        gc.fillText(" Keys:" + invToShow[0], 0,CANVAS_HEIGHT  - 3, CANVAS_WIDTH/5);
        gc.setFill(Color.GREEN);
        gc.fillText("Keys:" + invToShow[1], CANVAS_WIDTH * 0.2,CANVAS_HEIGHT - 3, CANVAS_WIDTH/5);
        gc.setFill(Color.BLUE);
        gc.fillText("Keys:" + invToShow[2], CANVAS_WIDTH * 0.4,CANVAS_HEIGHT - 3, CANVAS_WIDTH/5);
        gc.setFill(Color.YELLOW);
        gc.fillText("Keys:" + invToShow[3], CANVAS_WIDTH * 0.6,CANVAS_HEIGHT - 3, CANVAS_WIDTH/5);
        gc.setFill(Color.WHITE);
        gc.fillText("Chips:" + invToShow[4], CANVAS_WIDTH * 0.8,CANVAS_HEIGHT - 3, CANVAS_WIDTH/5);
    }
    public void drawEnd() {
        canvas.setWidth(WINDOW_WIDTH);
        canvas.setHeight(WINDOW_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Clear canvas
        gc.clearRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Set the background to green.
        gc.setFill(Color.GREEN);
        gc.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        gc.setFill(Color.BLACK);
        int [] newScores;
        gc.setFont(Font.font(60));
        if(GameLogic.getGameWon()) {
            /*
            FileConverter.changeScore();
            GameLogic.getGameMap().setTopScores(GameLogic.getGameMap().newScore(GameLogic.getGameMap().getTimeLeft()));
            */
            newScores = GameLogic.getGameMap().newScore(GameLogic.getGameMap().getTimeLeft(),GameLogic.getGameMap().getPlayer().getUserName());
            gc.fillText("Game Won!", WINDOW_WIDTH / 7, WINDOW_HEIGHT / 4);
            GameLogic.endGameChanges();
        } else {
            gc.fillText("Game Lost!", WINDOW_WIDTH / 7, WINDOW_HEIGHT / 4);
            newScores = GameLogic.getGameMap().newScore(-1, ".");
        }
        gc.setFont(Font.font(30));
        gc.fillText("High Scores:", WINDOW_WIDTH / 1.5, WINDOW_HEIGHT * 0.2);
        gc.setFont(Font.font(20));
        for(int i = 0; i < newScores.length; i++){
            gc.fillText(GameLogic.getGameMap().getNewNames()[i], WINDOW_WIDTH / 1.6, WINDOW_HEIGHT * (0.25 + (0.05 * i)));
            gc.fillText(String.valueOf(newScores[i]), WINDOW_WIDTH / 1.2, WINDOW_HEIGHT * (0.25 + (0.05 * i)));
        }

    }

    private void saveOrLoad(Stage stage, String userName) {
        BorderPane mapPane = new BorderPane();
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        Button newMapButton = new Button("New Map");
        newMapButton.setPrefSize(200, 120);

        Button loadButton = new Button("Load Save");
        loadButton.setPrefSize(200, 120);

        newMapButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("new map chosen");
                newMap(stage, userName);
            }
        });

        loadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("new map chosen");
                loadMap(stage, userName);
            }
        });
        hbox.getChildren().addAll(newMapButton, loadButton);
        mapPane.setCenter(hbox);
        Scene scene = new Scene(mapPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    private void loadMap(Stage stage, String userName) {
        BorderPane loginPane = new BorderPane();
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        label = new Label("Save File Path: ");
        usernameInput = new TextField();
        submitButton = new Button("Enter");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int usernameIndex = FileConverter.checkUsername(usernameInput.getText());
                Map loadedMap = FileConverter.convertFromFile(usernameInput.getText(), userName);
                if (loadedMap != null) {
                    System.out.println("valid path");
                    GRID_WIDTH = loadedMap.getBoardWidth();
                    GRID_HEIGHT = loadedMap.getBoardHeight();
                    CANVAS_HEIGHT = loadedMap.getBoardHeight() * GRID_CELL_HEIGHT + 20;
                    CANVAS_WIDTH = loadedMap.getBoardWidth() * GRID_CELL_WIDTH;
                    GameLogic.setGameMap(loadedMap);
                    runGame(stage);
                } else {
                    System.out.println("INVALID FILE PATH");
                }
            }
        });
        hbox.getChildren().addAll(label, usernameInput, submitButton);
        loginPane.setCenter(hbox);
        Scene scene = new Scene(loginPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
    private void newMap(Stage stage, String userName) {
        BorderPane mapPane = new BorderPane();
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        Button map1Button = new Button("Map 1");
        map1Button.setPrefSize(200, 120);

        Button map2Button = new Button("Map 2");
        map2Button.setPrefSize(200, 120);

        Button map3Button = new Button("Map 3");
        map3Button.setPrefSize(200, 120);

        Button map4Button = new Button("Map 4");
        map4Button.setPrefSize(200, 120);

        Button map5Button = new Button("Map 5");
        map5Button.setPrefSize(200, 120);

        map1Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileConverter.checkLevels(userName);
                GameLogic.setLevelNum(1);
                Map newMap = FileConverter.convertFromFile("Map1.txt", userName);
                System.out.println("map 1 chosen");
                GRID_WIDTH = newMap.getBoardWidth();
                GRID_HEIGHT = newMap.getBoardHeight();
                CANVAS_HEIGHT = newMap.getBoardHeight() * GRID_CELL_HEIGHT + 20;
                CANVAS_WIDTH = newMap.getBoardWidth() * GRID_CELL_WIDTH;
                GameLogic.setGameMap(newMap);
                runGame(stage);
            }
        });
        map2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(FileConverter.checkLevels(userName) >= 1) {
                    GameLogic.setLevelNum(2);
                    Map newMap = FileConverter.convertFromFile("Map2.txt", userName);
                    System.out.println("map 2 chosen");
                    GRID_WIDTH = newMap.getBoardWidth();
                    GRID_HEIGHT = newMap.getBoardHeight();
                    CANVAS_HEIGHT = newMap.getBoardHeight() * GRID_CELL_HEIGHT + 20;
                    CANVAS_WIDTH = newMap.getBoardWidth() * GRID_CELL_WIDTH;
                    GameLogic.setGameMap(newMap);
                    runGame(stage);
                } else {
                    System.out.println("you have not unlocked this level");
                }
            }
        });
        map3Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(FileConverter.checkLevels(userName) >= 2) {
                    GameLogic.setLevelNum(3);
                    Map newMap = FileConverter.convertFromFile("Map3.txt", userName);
                    System.out.println("map 3 chosen");
                    GRID_WIDTH = newMap.getBoardWidth();
                    GRID_HEIGHT = newMap.getBoardHeight();
                    CANVAS_HEIGHT = newMap.getBoardHeight() * GRID_CELL_HEIGHT + 20;
                    CANVAS_WIDTH = newMap.getBoardWidth() * GRID_CELL_WIDTH;
                    GameLogic.setGameMap(newMap);
                    runGame(stage);
                } else {
                    System.out.println("you have not unlocked this level");
                }
            }
        });
        hbox.getChildren().addAll(map1Button, map2Button, map3Button, map4Button, map5Button);
        mapPane.setCenter(hbox);

        Scene scene = new Scene(mapPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.show();
        /*
        Scanner in = new Scanner(System.in);
        Map newMap;
        do {
            System.out.println("Input File Path for loading:");
            String mapFilePath = in.next();
            newMap = FileConverter.convertFromFile(mapFilePath, userName);
        } while (newMap == null);
        GRID_WIDTH = newMap.getBoardWidth();
        GRID_HEIGHT = newMap.getBoardHeight();
        CANVAS_HEIGHT = newMap.getBoardHeight() * GRID_CELL_HEIGHT + 20;
        CANVAS_WIDTH = newMap.getBoardWidth() * GRID_CELL_WIDTH;
        GameLogic.setGameMap(newMap);
        // Build the GUI
        Pane root = buildGUI();

        // Create a scene from the GUI
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Register an event handler for key presses.
        // This causes the processKeyEvent method to be called each time a key is pressed.
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event));

        // Register a tick method to be called periodically.
        // Make a new timeline with one keyframe that triggers the tick method every half a second.
        tickTimeline = new Timeline(new KeyFrame(Duration.millis(100), event -> tick()));
        // Loop the timeline forever
        tickTimeline.setCycleCount(Animation.INDEFINITE);
        // We start the timeline upon a button press.

        // Display the scene on the stage
        drawGame();
        stage.setScene(scene);
        stage.show();
         */
    }

    public void runGame(Stage stage) {
        Pane root = buildGUI();

        // Create a scene from the GUI
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event));

        // Register a tick method to be called periodically.
        // Make a new timeline with one keyframe that triggers the tick method every half a second.
        tickTimeline = new Timeline(new KeyFrame(Duration.millis(100), event -> tick()));
        // Loop the timeline forever
        tickTimeline.setCycleCount(Animation.INDEFINITE);
        // We start the timeline upon a button press.

        // Display the scene on the stage
        drawGame();
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
