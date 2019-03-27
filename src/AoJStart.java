import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class AoJStart extends Application 
{
	public Stage backUpWindow; //used to access windows outside the main
	public Scene backUpScene; //used to access the scene outside the main
	public Stage quitWindow = new Stage(); //window for quit screen
	public Stage htpWindow = new Stage();//window for how to play( creating a window so that user can refer to controls while playing)
	
	@Override
	public void start(Stage primaryStage)//window stuff
	{
		backUpWindow = primaryStage;//Possibly binds the two stages
		
		Font font = new Font("Times New Roman", 30);
		GridPane pane = new GridPane();//Pane for main menu
		pane.setAlignment(Pos.CENTER);
		pane.setVgap(15); //Sets vertical gap between nodes inside a pane
		//Setting the background image. Keep in mind if you don't have these files in your computer AND in your project folder
		//for this IDE, you will not be able to see this image and will have a blank screen except for the How to Play scene
		//because that is a URL and the others are files that have to be located somewhere in your project package
		BackgroundImage image = new BackgroundImage(new Image("file:Pictures/AttackOnJavaJupiterMenu.jpg"), 
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT); //
		
		//Creating buttons
		//Play Button with its properties
		Button btnPlay = new Button("Play");
		btnPlay.setStyle("-fx-border-color: deeppink; -fx-background-color: pink;");
		btnPlay.setTextFill(Color.DEEPPINK);
		btnPlay.setFont(font);
		
		//How to Play button with its properties
		Button btnHTP = new Button("How to play");
		btnHTP.setStyle("-fx-border-color: deeppink; -fx-background-color: pink;");
		btnHTP.setTextFill(Color.DEEPPINK);
		btnHTP.setFont(font);
		
		//Credits Button with its properties
		Button btnCredits = new Button("Credits");
		btnCredits.setStyle("-fx-border-color: deeppink; -fx-background-color: pink;");
		btnCredits.setTextFill(Color.DEEPPINK);
		btnCredits.setFont(font);
		
		//Quit Button with its properties
		Button btnQuit = new Button("Quit");
		btnQuit.setStyle("-fx-border-color: deeppink; -fx-background-color: pink;");
		btnQuit.setTextFill(Color.DEEPPINK);
		btnQuit.setFont(font);
		
		//Adding nodes to the pane
		pane.setBackground(new Background(image));
		pane.add(btnPlay, 0, 0);
		pane.add(btnHTP, 0, 1);
		pane.add(btnCredits, 0, 2);
		pane.add(btnQuit, 0, 3);
		
		//Adding pane to the scene and adding the scene to the primaryStage
		Scene scene = new Scene(pane, 1280, 800);
		backUpScene = scene;
		primaryStage.setTitle("AoJ Main Menu");
		primaryStage.setScene(scene);
		backUpWindow.show();
		
		//Programming for the buttons when clicked
		btnQuit.setOnMouseClicked(this :: processQuit);
		btnPlay.setOnMouseClicked(this :: processPlay);
		btnHTP.setOnMouseClicked(this :: processHTP);

		//Programming for credits button handled inside main, could have been done either way
		btnCredits.setOnAction(new EventHandler<ActionEvent>() 
		{
            @Override
           public void handle(ActionEvent event) 
           {
            	BackgroundImage image = new BackgroundImage(new Image("file:Pictures/AoJLoadingScreen.gif"), 
						  BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				      BackgroundSize.DEFAULT);
            	
            	VBox creditPane = new VBox();
            	creditPane.setBackground(new Background(image));
            	
            	Scene credits = new Scene(creditPane, scene.getWidth(), 582);
            	creditPane.setAlignment(Pos.BOTTOM_CENTER);
            	Font CreditFont = new Font("Comic Sans MS", 25);
            	
            	//made 2 labels for names due to formatting issues
            	Label names1 = new Label("Shant"+"\n"+"Zak"+"\n"+"Ryoto"+"\n");
            	names1.setOpacity(.5);
            	names1.setTextFill(Color.CYAN);
            	names1.setFont(CreditFont);
            	creditPane.getChildren().add(names1);
            	Label names = new Label("   Alex"+"\n"+"   Ayato"+"\n"+"   Apurav"+"\n"+"   Donovan");
            	names.setOpacity(.5);
            	names.setTextFill(Color.MEDIUMSLATEBLUE);
            	names.setFont(CreditFont);
            	creditPane.getChildren().add(names);
            	
            	primaryStage.setTitle("Credits");
            	primaryStage.setScene(credits);
            	primaryStage.show();
            	
            	//button to for going back to main menu
            	Button btnBack = new Button("Go Back to Main Menu");
            	btnBack.setAlignment(Pos.BOTTOM_CENTER);
            	btnBack.setOpacity(.5);
            	btnBack.setStyle("-fx-border-color: black; -fx-background-color: darkslateblue;");
            	btnBack.setTextFill(Color.MEDIUMSLATEBLUE);
            	btnBack.setFont(CreditFont);
            	creditPane.getChildren().add(btnBack);
           		btnBack.setAlignment(Pos.BOTTOM_RIGHT);
               
           		//button action
           		btnBack.setOnAction(new EventHandler<ActionEvent>()
           		{
           			@Override
           			public void handle(ActionEvent event) 
           			{
           				primaryStage.setScene(scene);
           				primaryStage.show();
           			}
           		});
           }
		});
	}
	
	public void processPlay(MouseEvent e)
	{
		VBox pane = new VBox();
		Font font = new Font("Times New Roman", 25);
		BackgroundImage image = new BackgroundImage(new Image("file:Pictures/AoJPlayUnderDev.jpg"), 
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		pane.setAlignment(Pos.CENTER);
		pane.setBackground(new Background(image));
		
		//Formatting back to main menu button (btnMenu)
		Button btnMenu = new Button("Back to Main Menu");
		btnMenu.setFont(font);
		btnMenu.setStyle("-fx-border-color: lightsteelblue; -fx-background-color: black");
		btnMenu.setTextFill(Color.LIGHTSTEELBLUE);
		btnMenu.setFont(font);
		btnMenu.setOpacity(.75);
		
		//Place holder to show that we haven't developed the back-end processes
		Label lblPlay = new Label("Path under development");
		lblPlay.setFont(font);
		lblPlay.setTextFill(Color.LIGHTSTEELBLUE);
		
		pane.getChildren().addAll(lblPlay, btnMenu);
		
		Scene playScene = new Scene(pane, backUpScene.getWidth(), backUpScene.getHeight());
		backUpWindow.setScene(playScene);
		backUpWindow.centerOnScreen();
		backUpWindow.show();
		
		btnMenu.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				backUpWindow.setScene(backUpScene);
				backUpWindow.show();
			}
		});
		
	}
	
	public void processHTP (MouseEvent e)
	{
        BackgroundImage imageBG = new BackgroundImage(new Image("https://i.ytimg.com/vi/5Ork84zGLIk/maxresdefault.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        VBox htpPane=new VBox();
        Scene htpScene=new Scene(htpPane,1100,582);
        htpWindow.setScene(htpScene);
        Button btnBack = new Button("Go Back to Main Menu");
        btnBack.setStyle("-fx-border-color: deeppink; -fx-background-color: pink;");
        btnBack.setTextFill(Color.DEEPPINK);
        Font Htp = new Font("Comic Sans MS", 30);
        btnBack.setFont(Htp);
        htpPane.getChildren().add(btnBack);
        htpPane.setBackground(new Background(imageBG));
        htpWindow.show();

        //button to close how to play
        btnBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                htpWindow.close();
            }
            
        });
    }
	
	public void processQuit(MouseEvent e)
	{
		//quitWindow.initStyle(StageStyle.UNDECORATED);
		Font font = new Font("Times New Roman", 30);
		BackgroundImage imageBG = new BackgroundImage(new Image("file:Pictures/AoJTerror.gif"), 
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		
		FlowPane fPane = new FlowPane();
		fPane.setBackground(new Background(imageBG));
		fPane.setHgap(5);
		fPane.setAlignment(Pos.BOTTOM_CENTER);
		
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
		Scene quitScene = new Scene(fPane, 498, 317);
		quitWindow.setScene(quitScene);
		quitWindow.setTitle("Quit Window");
		quitWindow.show();
		
		btnNo.setOnMouseClicked(this :: processCancelQuit);
		btnYes.setOnMouseClicked(this :: processQuitCertain);
	}

	//When user clicks yes after clicking quit
	public void processQuitCertain(MouseEvent e)
	{
		System.exit(0);
	}
	
	//When user clicks no after clicking quit
	public void processCancelQuit(MouseEvent e)
	{
		quitWindow.close();
	}
	
	public static void main(String[] args) 
	{
		Application.launch(args);
		System.exit(0);
	}
}