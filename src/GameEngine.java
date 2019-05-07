import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public class GameEngine {

    private GamePane gamePane;
    private StartMenuPane startPane;
    private Scene scene;
    private Scene gameScene;

    private AnimationTimer gameLoop;

    Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
    private final double GROUNDLEVEL = primaryScreenBounds.getHeight()*(.84);
    private Rectangle ground = new Rectangle();
    private ArrayList<String> input;

    private Character c = new Character();
    private boolean jumping = false;
    private boolean paused = false;
    double gravity = 0;
    double prevPos = 0;

     public GameEngine() {

        gamePane = new GamePane();
        startPane = new StartMenuPane();
        gameLoop = gameLoop();

        input = new ArrayList<>();

        scene = new Scene(startPane, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());

        btnListener();

     }

     private void initGame() {
         //Add character and ground to game
         gamePane.getChildren().addAll(c, ground);

         //Create game scene
         gameScene = new Scene(gamePane, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());

         //Initialize ground
         createGround();

         //Give character game instance
         c.setGame(this);

         //Set keybindings
         keyListener();

         //Start the game loop
         gameLoop.start();

         //Add game scene to Stage
         Main.getStage().setScene(gameScene);
     }


    public AnimationTimer gameLoop() {
        return gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                c.handleMovement(input);
            }
        };
    }

    private void keyListener() {

        gameScene.setOnKeyPressed((event) -> {
            String code = event.getCode().toString();

            if(!input.contains(code))
                input.add(code);

            if(input.contains("UP")) {
                c.jump();
            }

            if(input.contains("P")) {
                pauseGame();
            }
        });

        gameScene.setOnKeyReleased((event) -> {
            String code = event.getCode().toString();


            input.remove(code);
        });
    }

    private void btnListener() {
         startPane.getPlayButton().setOnMouseClicked((event) -> {
            initGame();
         });

         startPane.getQuitButton().setOnMouseClicked((event -> {
             //quitWindow.initStyle(StageStyle.UNDECORATED);
             Font font = new Font("Times New Roman", 30);
             BackgroundImage imageBG = new BackgroundImage(new Image("file:Pictures/AoJTerror.gif"),
                     BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                     BackgroundSize.DEFAULT);
             //
             FlowPane fPane = new FlowPane();
             fPane.setBackground(new Background(imageBG));
             fPane.setHgap(5);
             fPane.setAlignment(Pos.BOTTOM_CENTER);
             //
             //no button for confirming quit
             Button btnNo = new Button("No");
             btnNo.setStyle("-fx-border-color: darkorange; -fx-background-color: coral");
             btnNo.setFont(font);
             btnNo.setOpacity(.5);
             btnNo.setTextFill(Color.BLACK);

             //yes button for confirming quit
             Button btnYes = new Button("Yes");
             btnYes.setStyle("-fx-border-color: darkorange; -fx-background-color: coral");
             btnYes.setFont(font);
             btnYes.setOpacity(0.5);
             btnYes.setTextFill(Color.BLACK);

             fPane.getChildren().addAll(btnYes, btnNo);
             Scene quitScene = new Scene(fPane, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
             Main.getStage().setScene(quitScene);

             btnNo.setOnMouseClicked((e) -> {
                 Main.getStage().setScene(scene);
             });
             btnYes.setOnMouseClicked((e) -> {
                 Main.getStage().close();
             });
         }));
    }

    public void pauseGame() {
        if(!paused) {
            gameLoop.stop();
            paused = true;
        }
        else {
            gameLoop.start();
            paused = false;
        }
    }

    private void createGround() {

        ground.setFill(Color.BLACK);
        ground.setStroke(Color.ORANGE);
        ground.setOpacity(0);
        ground.setWidth(scene.getWidth());
        ground.setX(0);
        ground.setHeight(1);
        ground.setY(GROUNDLEVEL);

    }

    public Scene getScene() {
        return scene;
    }

    public Rectangle getGround() {
         return ground;
    }
}
