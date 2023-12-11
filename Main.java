import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Font;


/**
 *<ol>
 *     <li>File-name: Main.java</li>
 *     <li>Purpose of the program: This program is the main one used to launch the game.</li>
 *
 *</ol>
 *
 * @author [Code - Freddie, Olwen. JavaDoc - Ffi, Enrique]
 */
public class Main extends Application {

    /**
     * The width of the window.
     */
    private static int windowWidth = 800;

    /**
     * The height of the window.
     */
    private static int windowHeight = 500;

    /**
    * Width of the canvas.
    */
    private static int canvasWidth;

    /**
     * Height of the canvas.
     */
    private static int canvasHeight;

    /** 
    * The width in pixels of each cell that makes up the game.
    */
    private static int GRID_CELL_WIDTH = 50;

    /**
     * The height in pixels of each cell that makes up the game.
     */
    private static int GRID_CELL_HEIGHT = 50;

    /**
    * The width of the grid in number of cells.
    */
    private static int gridWidth;

    /**
     * The height of the grid in number of cells.
     */
    private static int gridHeight;
    
    /**
    * The canvas in the GUI. This needs to be a global variable
    * (in this setup) as we need to access it in different methods.
    * We could use FXML to place code in the controller instead.
    */
    private Canvas canvas;

    /**
     * Player Image.
     */
    private Image playerImage;

    /**
     * Dirt Image.
     */
    private Image dirtImage;

    /**
     * Icon Image.
     */
    private Image iconImage;

    /**
     * Bug Image.
     */
    private Image bugImage;

    /**
     * Button Image.
     */
    private Image buttonImage;

    /**
     * ChipSocket Image.
     */
    private Image chipSocketImage;

    /**
     * ComputerChip Image.
     */
    private Image compChipImage;

    /**
     * Exit Image.
     */
    private Image exitImage;

    /**
     * Frog Image.
     */
    private Image frogImage;

    /**
     * Ice Image.
     */
    private Image iceImage;

    /**
     * Red Key Image.
     */
    private Image redKeyImage;

    /**
     * Green Key Image.
     */
    private Image greenKeyImage;

    /**
     * Blue Key Image.
     */
    private Image blueKeyImage;

    /**
     * Yellow Key Image.
     */
    private Image yellowKeyImage;

    /**
     * Red Locked Door Image.
     */
    private Image redLockedDoorImage;

    /**
     * Green Locked Door Image.
     */
    private Image greenLockedDoorImage;

    /**
     * Blue Locked Door Image.
     */
    private Image blueLockedDoorImage;

    /**
     * Yellow Locked Door Image.
     */
    private Image yellowLockedDoorImage;

    /**
     * PinkBall Image.
     */
    private Image pinkBallImage;

    /**
     * Trap Image.
     */
    private Image trapImage;

    /**
     * Wall Image.
     */
    private Image wallImage;

    /**
     * Water Image.
     */
    private Image waterImage;

    /**
     * Path Image.
     */
    private Image pathImage;

    /**
     * Top-Left Corner Ice Image.
     */
    private Image TLIceImage;

    /**
     * Top-Right Corner Ice Image.
     */
    private Image TRIceImage;

    /**
     * Bottom-Left Corner Ice Image.
     */
    private Image BLIceImage;

    /**
     * Bottom-Right Corner Ice Image.
     */
    private Image BRIceImage;

    /**
     * Block Image.
     */
    private Image blockImage;

    /**
     * TextField for Username.
     */
    private TextField usernameInput;

    /**
     * Text Label for GUI
     */
    private Label label;


    /**
     * In the case where {@code label} is used,
     * this one is used to display another text label for the GUI so
     * that both labels appear on the GUI.
     */
    private Label label2;

    /**
     * PasswordField for Password.
     */
    private PasswordField passwordInput;

    /**
     * Submit button for the GUI.
     */
    private Button submitButton;

    /**
     * Error Alert Message.
     */
    private Alert message = new Alert(Alert.AlertType.NONE);

    /**
    * Timeline which will cause tick method to be called periodically.
    */
    private Timeline tickTimeline;

    /**
     * The main entry point for the JavaFX application.
     *
     * @param primaryStage The primary stage for this application.
     */
    public void start(Stage primaryStage) {

        playerImage = new Image("resources/player.png");
        dirtImage = new Image("resources/dirt2.png");
        bugImage = new Image("resources/Bug.png");
        buttonImage = new Image("resources/Button.png");
        chipSocketImage = new Image("resources/Socket.png");
        compChipImage = new Image("resources/computer chip.png");
        exitImage = new Image("resources/Exit.png");
        frogImage = new Image("resources/Frog.png");
        iceImage = new Image("resources/Ice.png");
        redKeyImage = new Image("resources/redKey.png");
        greenKeyImage = new Image("resources/greenKey.png");
        blueKeyImage = new Image("resources/blueKey.png");
        yellowKeyImage = new Image("resources/yellowKey.png");
        redLockedDoorImage = new Image("resources/RedLock.png");
        greenLockedDoorImage = new Image("resources/GreenLock.png");
        blueLockedDoorImage = new Image("resources/BlueLock.png");
        yellowLockedDoorImage = new Image("resources/YellowLock.png");
        pinkBallImage = new Image("resources/PinkBall.png");
        trapImage = new Image("resources/Trap.png");
        wallImage = new Image("resources/Wall.png");
        waterImage = new Image("resources/Water.png");
        pathImage = new Image("resources/path.png");
        TLIceImage = new Image("resources/IceTopLeft.png");
        TRIceImage = new Image("resources/IceTopRight.png");
        BLIceImage = new Image("resources/IceaBottomLeft.png");
        BRIceImage = new Image("resources/IceBottomRight.png");
        blockImage = new Image("resources/Block.png");


        loginGUI(primaryStage);
    }

    /**
     * Generates the Login GUI for the application.
     *
     * @param stage The stage for the application.
     */
    public void loginGUI(Stage stage) {
        BorderPane loginPane = new BorderPane();
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(windowHeight /5.0, windowWidth /10.0, windowHeight /5.0, windowWidth /10.0));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        label = new Label("Welcome");
        label.setFont(new Font(50));
        Button loginButton = new Button("Log In");
        loginButton.setPrefSize(200, 120);
        loginButton.setFont(new Font(30));
        Button registerButton = new Button("Sign Up");
        registerButton.setPrefSize(200, 120);
        registerButton.setFont(new Font(30));

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
        hbox.getChildren().addAll(label, registerButton, loginButton);
        loginPane.setCenter(hbox);
        Scene scene = new Scene(loginPane, windowWidth, windowHeight);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Generate the Register GUI for the game.
     *
     * @param stage The stage for the application.
     */
    public void registerGUI(Stage stage) {
        BorderPane registerPane = new BorderPane();
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(15, 12, 15, 12));
        vbox.setSpacing(10);
        vbox.setStyle("-fx-background-color: #336699;");
        label = new Label("Username: ");
        usernameInput = new TextField();
        Label label2 = new Label("Password: ");
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
                    message.setAlertType(Alert.AlertType.ERROR);
                    message.setContentText("USERNAME TAKEN");
                    message.show();
                }
            }
        });
        vbox.getChildren().addAll(label, usernameInput, label2, passwordInput, submitButton);
        registerPane.setCenter(vbox);
        Scene scene = new Scene(registerPane, windowWidth, windowHeight);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Generate the username GUI for the application.
     *
     * @param stage The stage for the application.
     */
    public void usernameGUI(Stage stage) {
        BorderPane loginPane = new BorderPane();
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(windowHeight /4.0, windowWidth /4.0, windowHeight /4.0, windowWidth /4.0));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        label = new Label("Username: ");
        label.setFont(new Font(30));
        usernameInput = new TextField();
        submitButton = new Button("Enter");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int usernameIndex = FileConverter.checkUsername(usernameInput.getText());
                if (usernameIndex > -1) {
                    passwordGUI(stage,usernameIndex);
                } else {
                    message.setAlertType(Alert.AlertType.ERROR);
                    message.setContentText("Incorrect Username");
                    message.show();
                }
            }
        });
        hbox.getChildren().addAll(label, usernameInput, submitButton);
        loginPane.setCenter(hbox);
        Scene scene = new Scene(loginPane, windowWidth, windowHeight);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Generate the Password GUI for the application.
     *
     * @param stage The stage for the application.
     * @param index index at which the password will be found based on the username index.
     */
    public void passwordGUI(Stage stage, int index) {
        BorderPane loginPane = new BorderPane();
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(windowHeight /4.0, windowWidth /4.0, windowHeight /4.0, windowWidth /4.0));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        label = new Label("Password: ");
        label.setFont(new Font(30));
        passwordInput = new PasswordField();
        submitButton = new Button("Enter");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (FileConverter.checkPassword(passwordInput.getText(), index)) {
                    saveOrLoad(stage, usernameInput.getText());
                } else {
                    message.setAlertType(Alert.AlertType.ERROR);
                    message.setContentText("Wrong password");
                    message.show();
                }
            }
        });
        hbox.getChildren().addAll(label, passwordInput, submitButton);
        loginPane.setCenter(hbox);
        Scene scene = new Scene(loginPane, windowWidth, windowHeight);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * This is called periodically and updates the game positions
     * and triggers various actions.
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
        // Move PinkBalls every 2 ticks
        if(GameLogic.getGameMap().getSchedule().getTick()%2 == 0) {
            GameLogic.movePinkBalls();
        }

        // Move Bugs every 4 ticks
        if(GameLogic.getGameMap().getSchedule().getTick()%4 == 0) {
            GameLogic.moveBugs();
        }
        // Second passes every 10 ticks
        if(GameLogic.getGameMap().getSchedule().getTick()%10 == 0) {
            GameLogic.getGameMap().setTimeLeft(GameLogic.getGameMap().getTimeLeft()-1);
        }
        // Move Frogs every 8 ticks
        if(GameLogic.getGameMap().getSchedule().getTick()%8 == 0) {
            GameLogic.moveFrogs();
        }
        drawGame();
    }

    /**
     * Handles key events when a key is pressed.
     * Changes behavior based on the pressed key.
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
    private Pane buildGUI(Stage stage) {
        // Create top-level panel that will hold all GUI nodes.
        BorderPane root = new BorderPane();

        // Create the canvas that we will draw on.
        // We store this as a global variable so other methods can access it.
        canvas = new Canvas(canvasWidth, canvasHeight);
        root.setCenter(canvas);

        // Create a toolbar with some nice padding and spacing
        HBox toolbar = new HBox();
        toolbar.setSpacing(5);
        toolbar.setPadding(new Insets(10, 10, 10, 10));
        root.setTop(toolbar);

        // Create the toolbar content

        // Tick Timeline buttons
        Button pauseButton = new Button("Start");

        Button saveButton = new Button("Save & Quit");
        // We add both buttons at the same time to the timeline (we could have done this in two steps).
        toolbar.getChildren().addAll(saveButton, pauseButton);

        // save behaviour
        saveButton.setOnAction(e -> {
            saveToFile(stage);
        });
        pauseButton.setOnAction(e -> {
            // Start the tick timeline and enable/disable buttons as appropriate.
            if (pauseButton.getText().equals("Pause")) {
                pauseButton.setText("Unpause");
                GameLogic.getGameMap().getSchedule().pause();
                tickTimeline.stop();
            } else {
                pauseButton.setText("Pause");
                GameLogic.getGameMap().getSchedule().unPause();
                tickTimeline.play();
            }
        });

        return root;
    }

    /**
     * JavaFX pane for saving to a given file path.
     *
     * @param stage The stage for the application.
     */
    public void saveToFile(Stage stage) {
        BorderPane loginPane = new BorderPane();
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        label = new Label("File path: ");
        usernameInput = new TextField();
        submitButton = new Button("Enter");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (FileConverter.convertToFile(GameLogic.getGameMap(), usernameInput.getText())) {
                    System.out.println("valid file path");
                    Platform.exit();
                } else {
                    message.setAlertType(Alert.AlertType.ERROR);
                    message.setContentText("INVALID FILE PATH");
                    message.show();
                }
            }
        });
        hbox.getChildren().addAll(label, usernameInput, submitButton);
        loginPane.setCenter(hbox);
        Scene scene = new Scene(loginPane, windowWidth, windowHeight);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Generates the whole Map on the Canvas.
     */
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
        for (int y = 0; y < gridHeight; y++) {
            for (int x = 0; x < gridWidth; x++) {
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
        for (int y = 0; y < gridHeight; y++) {
            for (int x = 0; x < gridWidth; x++) {
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
        for (int y = 0; y < gridHeight; y++) {
            for (int x = 0; x < gridWidth; x++) {
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
        gc.setFont(new Font(20));
        gc.fillText(String.valueOf(GameLogic.getGameMap().getTimeLeft()), canvasWidth - 35, 20);
        //Draw inventory bar
        gc.setFont(new Font(12));
        int[] invToShow = GameLogic.getGameMap().getPlayer().getInventory();
        gc.setFill(Color.RED);
        gc.fillText(" Keys:" + invToShow[0], 0, canvasHeight - 3, canvasWidth /5.0);
        gc.setFill(Color.GREEN);
        gc.fillText("Keys:" + invToShow[1], canvasWidth * 0.2, canvasHeight - 3, canvasWidth /5.0);
        gc.setFill(Color.BLUE);
        gc.fillText("Keys:" + invToShow[2], canvasWidth * 0.4, canvasHeight - 3, canvasWidth /5.0);
        gc.setFill(Color.YELLOW);
        gc.fillText("Keys:" + invToShow[3], canvasWidth * 0.6, canvasHeight - 3, canvasWidth /5.0);
        gc.setFill(Color.WHITE);
        gc.fillText("Chips:" + invToShow[4], canvasWidth * 0.8, canvasHeight - 3, canvasWidth /5.0);
    }

    /**
     * Generate the Ending Canvas for when the game ends.
     */
    public void drawEnd() {
        canvas.setWidth(windowWidth);
        canvas.setHeight(windowHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Clear canvas
        gc.clearRect(0, 0, windowWidth, windowHeight);

        // Set the background to green.
        gc.setFill(Color.GREEN);
        gc.fillRect(0, 0, windowWidth, windowHeight);

        gc.setFill(Color.BLACK);
        int [] newScores;
        gc.setFont(Font.font(60));
        if(GameLogic.getGameWon()) {

            newScores = GameLogic.getGameMap().newScore(GameLogic.getGameMap().getTimeLeft(),GameLogic.getGameMap().getPlayer().getUserName());
            gc.fillText("Game Won!", windowWidth / 7.0, windowHeight / 4.0);
            GameLogic.endGameChanges();
        } else {
            gc.fillText("Game Lost!", windowWidth / 7.0, windowHeight / 4.0);
            newScores = GameLogic.getGameMap().newScore(-1, ".");
        }
        gc.setFont(Font.font(30));
        gc.fillText("High Scores:", windowWidth / 1.5, windowHeight * 0.2);
        gc.setFont(Font.font(20));
        for(int i = 0; i < newScores.length; i++){
            gc.fillText(GameLogic.getGameMap().getNewNames()[i], windowWidth / 1.6, windowHeight * (0.25 + (0.05 * i)));
            gc.fillText(String.valueOf(newScores[i]), windowWidth / 1.2, windowHeight * (0.25 + (0.05 * i)));
        }

    }

    /**
     * Generate the Save and Load GUI for the application.
     *
     * @param stage The stage for the application.
     * @param userName The Player.
     */
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
                newMap(stage, userName);
            }
        });

        loadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadMap(stage, userName);
            }
        });
        hbox.getChildren().addAll(newMapButton, loadButton);
        mapPane.setCenter(hbox);
        Scene scene = new Scene(mapPane, windowWidth, windowHeight);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Load the Map for the game.
     *
     * @param stage The stage for the application.
     * @param userName The Player.
     */
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
                    gridWidth = loadedMap.getBoardWidth();
                    gridHeight = loadedMap.getBoardHeight();
                    canvasHeight = loadedMap.getBoardHeight() * GRID_CELL_HEIGHT + 20;
                    canvasWidth = loadedMap.getBoardWidth() * GRID_CELL_WIDTH;
                    GameLogic.setGameMap(loadedMap);
                    runGame(stage);
                } else {
                    message.setAlertType(Alert.AlertType.ERROR);
                    message.setContentText("INVALID FILE PATH");
                    message.show();
                }
            }
        });
        hbox.getChildren().addAll(label, usernameInput, submitButton);
        loginPane.setCenter(hbox);
        Scene scene = new Scene(loginPane, windowWidth, windowHeight);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Generates the List of Maps in the game.
     *
     * @param stage The stage of the application.
     * @param userName The Player.
     */
    private void newMap(Stage stage, String userName) {
        BorderPane mapPane = new BorderPane();
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        Button map1Button = new Button("Map 1");
        map1Button.setPrefSize(120, 120);

        Button map2Button = new Button("Map 2");
        map2Button.setPrefSize(120, 120);

        Button map3Button = new Button("Map 3");
        map3Button.setPrefSize(120, 120);

        Button map4Button = new Button("Map 4");
        map4Button.setPrefSize(120, 120);

        Button map5Button = new Button("Map 5");
        map5Button.setPrefSize(120, 120);

        Button map6Button = new Button("Map 6");
        map6Button.setPrefSize(120, 120);

        Button map7Button = new Button("Map 7");
        map7Button.setPrefSize(120, 120);

        map1Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileConverter.checkLevels(userName);
                GameLogic.setLevelNum(1);
                Map newMap = FileConverter.convertFromFile("resources/Map1.txt", userName); // change
                gridWidth = newMap.getBoardWidth();
                gridHeight = newMap.getBoardHeight();
                canvasHeight = newMap.getBoardHeight() * GRID_CELL_HEIGHT + 20;
                canvasWidth = newMap.getBoardWidth() * GRID_CELL_WIDTH;
                GameLogic.setGameMap(newMap);
                runGame(stage);
            }
        });
        map2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(FileConverter.checkLevels(userName) >= 1) {
                    GameLogic.setLevelNum(2);
                    Map newMap = FileConverter.convertFromFile("resources/Map2.txt", userName);
                    gridWidth = newMap.getBoardWidth();
                    gridHeight = newMap.getBoardHeight();
                    canvasHeight = newMap.getBoardHeight() * GRID_CELL_HEIGHT + 20;
                    canvasWidth = newMap.getBoardWidth() * GRID_CELL_WIDTH;
                    GameLogic.setGameMap(newMap);
                    runGame(stage);
                } else {
                    message.setAlertType(Alert.AlertType.ERROR);
                    message.setContentText("you have not unlocked this level");
                    message.show();
                }
            }
        });
        map3Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(FileConverter.checkLevels(userName) >= 2) {
                    GameLogic.setLevelNum(3);
                    Map newMap = FileConverter.convertFromFile("resources/Map3.txt", userName);
                    gridWidth = newMap.getBoardWidth();
                    gridHeight = newMap.getBoardHeight();
                    canvasHeight = newMap.getBoardHeight() * GRID_CELL_HEIGHT + 20;
                    canvasWidth = newMap.getBoardWidth() * GRID_CELL_WIDTH;
                    GameLogic.setGameMap(newMap);
                    runGame(stage);
                } else {
                    message.setAlertType(Alert.AlertType.ERROR);
                    message.setContentText("you have not unlocked this level");
                    message.show();
                }
            }
        });
        map4Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(FileConverter.checkLevels(userName) >= 3) {
                    GameLogic.setLevelNum(4);
                    Map newMap = FileConverter.convertFromFile("resources/Map4.txt", userName);
                    gridWidth = newMap.getBoardWidth();
                    gridHeight = newMap.getBoardHeight();
                    canvasHeight = newMap.getBoardHeight() * GRID_CELL_HEIGHT + 20;
                    canvasWidth = newMap.getBoardWidth() * GRID_CELL_WIDTH;
                    GameLogic.setGameMap(newMap);
                    runGame(stage);
                } else {
                    message.setAlertType(Alert.AlertType.ERROR);
                    message.setContentText("you have not unlocked this level");
                    message.show();
                }
            }
        });
        map5Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(FileConverter.checkLevels(userName) >= 4) {
                    GameLogic.setLevelNum(5);
                    Map newMap = FileConverter.convertFromFile("resources/Map5.txt", userName);
                    gridWidth = newMap.getBoardWidth();
                    gridHeight = newMap.getBoardHeight();
                    canvasHeight = newMap.getBoardHeight() * GRID_CELL_HEIGHT + 20;
                    canvasWidth = newMap.getBoardWidth() * GRID_CELL_WIDTH;
                    GameLogic.setGameMap(newMap);
                    runGame(stage);
                } else {
                    message.setAlertType(Alert.AlertType.ERROR);
                    message.setContentText("you have not unlocked this level");
                    message.show();
                }
            }
        });
        map6Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(FileConverter.checkLevels(userName) >= 5) {
                    GameLogic.setLevelNum(6);
                    Map newMap = FileConverter.convertFromFile("resources/Map6.txt", userName);
                    gridWidth = newMap.getBoardWidth();
                    gridHeight = newMap.getBoardHeight();
                    canvasHeight = newMap.getBoardHeight() * GRID_CELL_HEIGHT + 20;
                    canvasWidth = newMap.getBoardWidth() * GRID_CELL_WIDTH;
                    GameLogic.setGameMap(newMap);
                    runGame(stage);
                } else {
                    message.setAlertType(Alert.AlertType.ERROR);
                    message.setContentText("you have not unlocked this level");
                    message.show();
                }
            }
        });
        map7Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(FileConverter.checkLevels(userName) >= 6) {
                    GameLogic.setLevelNum(7);
                    Map newMap = FileConverter.convertFromFile("resources/Map7.txt", userName);
                    gridWidth = newMap.getBoardWidth();
                    gridHeight = newMap.getBoardHeight();
                    canvasHeight = newMap.getBoardHeight() * GRID_CELL_HEIGHT + 20;
                    canvasWidth = newMap.getBoardWidth() * GRID_CELL_WIDTH;
                    GameLogic.setGameMap(newMap);
                    runGame(stage);

                } else {
                    message.setAlertType(Alert.AlertType.ERROR);
                    message.setContentText("you have not unlocked this level");
                    message.show();
                }
            }
        });


        hbox.getChildren().addAll(map1Button, map2Button, map3Button, map4Button, map5Button, map6Button, map7Button);
        mapPane.setCenter(hbox);

        Scene scene = new Scene(mapPane, windowWidth, windowHeight);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Runs the stage for the game.
     *
     * @param stage The stage of the application.
     */
    public void runGame(Stage stage) {
        if(canvasWidth >= windowWidth -100) {
            windowWidth = canvasWidth + 100;
        }
        if(canvasHeight >= windowHeight -100) {
            windowHeight = canvasHeight + 100;
        }
        Pane root = buildGUI(stage);

        // Create a scene from the GUI
        Scene scene = new Scene(root, windowWidth, windowHeight);
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

    /**
     * Officially launch the game on User's device.
     *
     * @param args The application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
