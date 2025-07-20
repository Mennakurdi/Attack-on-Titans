package game.gui;

import java.io.IOException;
import java.util.PriorityQueue;
import game.engine.Battle;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.ColossalTitan;
import game.engine.titans.PureTitan;
import game.engine.titans.Titan;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;




public class Main extends Application {
	
	private Scene scene;
	private Pane pane;
	private Button HowToPlay;
	private Button Easy;
	private Button Hard;
	public VBox [] laneso;
	private AnchorPane anchorPane1;
	// for the easy mode
	private Scene scene3;
	private Battle battle;
	private AnchorPane anchorPane2;
	private Scene scene4;
	//to add titan in the lanes
	//private Titan Score ;
	private Label scoreLabel;
    private Label phaseLabel;
    private Label resourcesLabel;
    private Label TurnsLabel;
    private Label Lanes;
    @Override
    public void start(Stage primaryStage) throws Exception  {
    //	battle  = new Battle(1, 0, 150, 3, 250);
    	ImageView imageView = new ImageView(new Image("download.jpg"));
    	imageView.setFitWidth(700); // Set the width of the image to match the scene width
    	imageView.setFitHeight(700); // Set the height of the image to match the scene height
        // pane.getChildren().add(imageView); // Add the image view to your root pane
    	primaryStage.setTitle("Utopia : Attack On Titan");
    	Easy = new Button ("Easy");
    	double  x = 280;
    	double y = 300;
    	Easy.setLayoutX(x);
    	Easy.setLayoutY(y);
    	Easy.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-font-weight: bold;");
    	Hard = new Button ("Hard");
    	double H = 370;
    	double a = 300;
    	Hard.setLayoutX(H);
    	Hard.setLayoutY(a);
    	Hard.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-font-weight: bold;");
    	Hard.setOnAction(event -> {
    	    try {
    	        battle = new Battle(1, 0, 100, 5, 125);
    	        primaryStage.setScene(creates4(primaryStage));
    	        //updateLabels();
    	    } catch (IOException e) {
    	        this.displayAlert("Battle error", "Cannot initialize battle");
    	        //e.printStackTrace();
    	    }
    	});        
    	HowToPlay = new Button ("How To Play");
       	double p = 300;
       	double l = 350;
       	HowToPlay.setLayoutX(p);
       	HowToPlay.setLayoutY(l);
       	HowToPlay.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-font-weight: bold;");
       	Easy.setOnAction(event -> {
       	    //primaryStage.setScene(creates3(primaryStage));
       	    try {
				battle = new Battle(1, 0, 150, 3, 250);
				 primaryStage.setScene(creates3(primaryStage));
				//primaryStage.setScene(creates3(primaryStage));
			} catch (IOException e) {
				this.displayAlert("Battle error", "Cannot initialize battle");
				//e.printStackTrace();
			}
       	});
       	HowToPlay.setOnMouseClicked(new EventHandler<Event>(){
			@Override
			public void handle(Event event) {
						 primaryStage.setScene(creates2(primaryStage));
			}
       		
       	});
        pane  = new Pane();
        pane.getChildren().addAll(Easy, Hard, HowToPlay,imageView);
        Easy.toFront();
        Hard.toFront();
        HowToPlay.toFront();
        primaryStage.setResizable(false);
        scene = new Scene(pane, 700,700);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    public Scene creates2(Stage primaryStage) {
        // Load the background image
        ImageView backgroundImage = new ImageView(new Image("Old Parchment.jpg"));
        backgroundImage.setFitWidth(700); // Set the width of the image to match the scene width
        backgroundImage.setFitHeight(700);

        // Create the text with bold font and white color
        Text t = new Text();
        t.setText("In the tower defense game, players defend lanes from advancing titans by strategically placing \n weapons.\n Upon starting, players select a mode (Easy or Hard) and receive clear game instructions.\n Throughout the game, essential information like score,\n turn, phase, and resources is displayed and updated.\n Players manage a weapon shop, purchasing items with varying attributes like name,\n type, price, and damage points. \n Lanes feature details such as wall health, \n danger level, available weapons, and active titans.\n  Players can pass turns, select lanes for weapon placement, \n  buy weapons, and distinguish between titans and weapons.\n Invalid actions prompt indicators, ensuring uninterrupted gameplay. \n Upon defeat, players are notified of their loss, \n shown their final score, and returned to the start window.");
        t.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        t.setFill(Color.WHITE);

        // Create the "Go Back" button with transparent background and white text
        Button goBackButton = new Button("Go Back");
        goBackButton.setOnAction(event -> primaryStage.setScene(scene)); // Set the action to switch to the original scene
        goBackButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");

        // Create an AnchorPane to hold the text and button
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(t, goBackButton);
        anchorPane.setTopAnchor(t, 100.0);
        anchorPane.setLeftAnchor(t, 30.0);
        anchorPane.setBottomAnchor(goBackButton, 20.0);

        // Create a StackPane to layer the background image and AnchorPane
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundImage, anchorPane);

        // Create the scene with the stack pane
        Scene scene2 = new Scene(stackPane, 700, 700);


        return scene2;
    }
	
	// l kol lane leeha pane 
		public Scene creates3(Stage primaryStage) {	
			ImageView imageView = new ImageView(new Image("War Disaster.jpg"));
	    	imageView.setFitWidth(700); // Set the width of the image to match the scene width
	    	imageView.setFitHeight(700);
		    VBox lane1 = createLane(1); 
		    VBox lane2 = createLane(2); 
		    VBox lane3 = createLane(3); 
		    laneso= new VBox[]{lane1,lane2,lane3};
		    AnchorPane.setLeftAnchor(lane1, 50.0);
		    AnchorPane.setLeftAnchor(lane2, 250.0);
		    AnchorPane.setLeftAnchor(lane3, 450.0);
		    AnchorPane.setTopAnchor(lane1, 150.0);
		    AnchorPane.setTopAnchor(lane2, 150.0);
		    AnchorPane.setTopAnchor(lane3, 150.0);
		    // Create AnchorPane to hold the Panes
		    Button goBackButton = new Button("Go Back");
		    goBackButton.setOnAction(event -> primaryStage.setScene(scene));
		    Button passTurn = new Button("Pass Turn");
		    passTurn.setOnMouseClicked(new EventHandler<Event>() {
		        @Override
		        public void handle(Event event) {
		                battle.passTurn();
		                updateLabels();
		                checkGameStatus(primaryStage);
		            }
		    });
		    HBox hbox = new HBox();    
		    Button PiercingCannon = new Button("Name: Anti-TitanShell"+"\n"+"Type: Piercing Cannon" +"\n" +"Price:"+25+"\n"+ "Damage: "+10);    
		    PiercingCannon.setOnAction(event -> {
		    	 // Create an array of lane options
		        String[] options = new String[battle.getOriginalLanes().size()];
		        for (int i = 0; i < battle.getOriginalLanes().size(); i++) {
		            options[i] = "Lane " + (i + 1);
		        }

		        // Show the popup message with radio buttons for lane selection
		        showPopupMessageWithRadioButtons("Select the lane:", options);
		        try {
					battle.purchaseWeapon(1, battle.getOriginalLanes().get(1));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					this.displayAlert("not avalaible lanes", "lanes not avaliable");
				}
		        updateLabels();
		        
		    });
		        
		 
		       Button SniperCannon = new Button("Name: Long RangeSpear"+"\n"+"Type: Sniper Cannon"+ "\n" +"Price:"+25 +"\n" +"Damage: "+35);
		       SniperCannon.setOnAction(event -> {
			        // Create an array of lane options
			        String[] options = new String[battle.getOriginalLanes().size()];
			        for (int i = 0; i < battle.getOriginalLanes().size(); i++) {
			            options[i] = "Lane " + (i + 1);
			        }

			        // Show the popup message with radio buttons for lane selection
			        showPopupMessageWithRadioButtons("Select the lane:", options);
			        try {
						battle.purchaseWeapon(2, battle.getOriginalLanes().get(2));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						this.displayAlert("not avalaible lanes", "lanes not avaliable");
					}
			        updateLabels();
			    });
		    Button VolleySpreadCannon = new Button("Name: Wall SpreadCannon"+"\n"+"Type: Volley Spread Cannon"+ "\n"+ "Price: "+100+ "\n" +"Damage: "+5);
		    VolleySpreadCannon.setOnAction(event -> {
		        // Create an array of lane options
		        String[] options = new String[battle.getOriginalLanes().size()];
		        for (int i = 0; i < battle.getOriginalLanes().size(); i++) {
		            options[i] = "Lane " + (i + 1);
		        }

		        // Show the popup message with radio buttons for lane selection
		        showPopupMessageWithRadioButtons("Select the lane:", options);
		        try {
					battle.purchaseWeapon(3, battle.getOriginalLanes().get(0));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					this.displayAlert("not avalaible lanes", "lanes not avaliable");
				}
		        updateLabels();
		    });
		    Button WallTrap = new Button("Name: Proximity Trap"+"\n"+"Type: Wall Trap"+ "\n" +"Price: " +75+"\n"+ "Damage: "+100);
		    WallTrap.setOnAction(event -> {
		        // Create an array of lane options
		        String[] options = new String[battle.getOriginalLanes().size()];
		        for (int i = 0; i < battle.getOriginalLanes().size(); i++) {
		            options[i] = "Lane " + (i + 1);
		        }

		        // Show the popup message with radio buttons for lane selection
		        showPopupMessageWithRadioButtons("Select the lane:", options);
		        try {
					battle.purchaseWeapon(4, battle.getOriginalLanes().get(0));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					this.displayAlert("not avalaible lanes", "lanes not avaliable");
				}
		        updateLabels();
		    });
		    hbox.getChildren().addAll(PiercingCannon, SniperCannon, VolleySpreadCannon, WallTrap);
		    
		    // Set position for the hbox
		    AnchorPane.setRightAnchor(hbox, 100.0);
		    anchorPane1 = new AnchorPane();
		    anchorPane1.getChildren().addAll(imageView,lane1, lane2, lane3, goBackButton, passTurn, hbox);
		    anchorPane1.setRightAnchor(goBackButton, 20.0);
		    anchorPane1.setBottomAnchor(passTurn, 40.0);
		    scene3 = new Scene(anchorPane1, 700, 700);
		    scoreLabel = new Label();
		    phaseLabel = new Label();
		    resourcesLabel = new Label();   
		    TurnsLabel = new Label();
		    Lanes = new Label();
		    updateLabels();
		    HBox updates  = new HBox ();
		    updates.getChildren().addAll(scoreLabel, phaseLabel, resourcesLabel,TurnsLabel,Lanes);
		    updates.setSpacing(20);
		    anchorPane1.getChildren().addAll(updates);
		    anchorPane2.setBottomAnchor(updates, 80.0);

		    return scene3;
		}
	
	private VBox createLane(int number) {
	    VBox lane = new VBox(10); // Horizontal gap between items in the lane
	    lane.setPrefSize(200, 100); // Set the size of the lane
	    Label laneLabel = new Label("Lane " + number);
	    laneLabel.setPadding(new Insets(5)); // Add padding around the label
//	    Label resourcesLabel = new Label("Resources: " + battle.getResourcesGathered());
//	    resourcesLabel.setPadding(new Insets(5)); // Add padding around the label
	    Label currentHealth = new Label ("currentHealth: "+battle.getOriginalLanes().get(number-1).getLaneWall().getCurrentHealth());
	    Label dangerLevel = new Label ("Danger Level: "+ battle.getOriginalLanes().get(number-1).getDangerLevel());
	    Rectangle wall = new Rectangle(100, 50); // Set dimensions of the wall
	    if(battle.getOriginalLanes().get(number-1).isLaneLost())
	    	wall.setFill(Color.RED);
	    else
	    	wall.setFill(Color.GRAY); // Set color of the wall
	    
	    Pane p = new Pane();
	    p.setPrefWidth(100);
	    p.setMinWidth(100);
	    p.setPrefHeight(300);
	    p.setMinHeight(300);
	    PriorityQueue<Titan> titans=battle.getOriginalLanes().get(number-1).getTitans();
	    for(Titan t:titans){
	    	Circle c= new Circle();
	    	c.setRadius(10);
	    	if(t instanceof PureTitan){
	    		c.setRadius(15);
	    		c.setFill(Color.BLACK);
	    		
	    	}else if(t instanceof ArmoredTitan){
	    		 c.setRadius(50);
	    		c.setFill(Color.RED);
	    	}else if(t instanceof ColossalTitan){
	    		c.setRadius(80); 
	    		c.setFill(Color.BLUE);
	    	}else{
	    		c.setRadius(100);
	    		c.setFill(Color.GREEN);
	    		
	    	}
	    	/*Label healthText = new Label(String.valueOf(t.getCurrentHealth()));
	        healthText.setTextFill(Color.WHITE); // Set text color
	        healthText.setFont(Font.font(10)); // Set font size
	        healthText.setTextAlignment(TextAlignment.CENTER); // Align text to center
	        
	        // Position health text inside the circle
	        healthText.setLayoutX(c.getCenterX() - (healthText.getLayoutBounds().getWidth() / 2));
	        healthText.setLayoutY(c.getCenterY() + (healthText.getLayoutBounds().getHeight() / 4));*/
	    	p.getChildren().addAll(c);
	    	c.setLayoutX(Math.random()*100);
	    	c.setLayoutY(((t.getDistance()+0.0)/battle.getTitanSpawnDistance())*300);
	    	
	    }
	    lane.getChildren().addAll(p,laneLabel,currentHealth,dangerLevel,wall);
	   
	    return lane;
	}
		
	
	
	public Scene creates4(Stage primaryStage){
		ImageView imageView = new ImageView(new Image("War Disaster.jpg"));
    	imageView.setFitWidth(700); // Set the width of the image to match the scene width
    	imageView.setFitHeight(700);
		VBox lane1 = createLane(1); 
	    VBox lane2 = createLane(2); 
	    VBox lane3 = createLane(3); 
		VBox lane4 = createLane(4);
		VBox lane5 = createLane(5);
		laneso=new VBox[]{lane1,lane2,lane3,lane4,lane5};
		AnchorPane.setLeftAnchor(lane1, 10.0);
	    AnchorPane.setLeftAnchor(lane2,  120.0);
	    AnchorPane.setLeftAnchor(lane3, 230.0);
	    AnchorPane.setLeftAnchor(lane4,340.0);
	    AnchorPane.setLeftAnchor(lane5, 450.0);
	    AnchorPane.setTopAnchor(lane1, 150.0);
	    AnchorPane.setTopAnchor(lane2, 150.0);
	    AnchorPane.setTopAnchor(lane3, 150.0);
	    AnchorPane.setTopAnchor(lane4, 150.0);
	    AnchorPane.setTopAnchor(lane4, 150.0);
	    AnchorPane.setTopAnchor(lane5, 150.0);
	    Button goBackButton = new Button("Go Back");
	    goBackButton.setOnAction(event -> primaryStage.setScene(scene));
	    Button passTurn = new Button("Pass Turn");
				passTurn.setOnMouseClicked(event -> {
				    battle.passTurn(); 
				    updateLabels();
				    checkGameStatus(primaryStage);
				});
			
	    	

	    HBox hbox = new HBox();
	    
	    Button PiercingCannon = new Button("Name: Anti-TitanShell"+"\n"+"Type: Piercing Cannon" +"\n" +"Price:"+25+"\n"+ "Damage: "+10);
	    PiercingCannon.setOnAction(event -> {
	    	 // Create an array of lane options
	        String[] options = new String[battle.getOriginalLanes().size()];
	        for (int i = 0; i < battle.getOriginalLanes().size(); i++) {
	            options[i] = "Lane " + (i + 1);
	        }

	        // Show the popup message with radio buttons for lane selection
	        showPopupMessageWithRadioButtons("Select the lane:", options);
	        try {
				battle.purchaseWeapon(1, battle.getOriginalLanes().get(1));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				this.displayAlert("not avalaible lanes", "lanes not avaliable");
			}
	        updateLabels();
	        //Text it = new Text();
	        //wall.
	        
	    });

	    Button SniperCannon = new Button("Name: Long RangeSpear"+"\n"+"Type: Sniper Cannon"+ "\n" +"Price:"+25 +"\n" +"Damage: "+35);
	    SniperCannon.setOnAction(event -> {
	        // Create an array of lane options
	        String[] options = new String[battle.getOriginalLanes().size()];
	        for (int i = 0; i < battle.getOriginalLanes().size(); i++) {
	            options[i] = "Lane " + (i + 1);
	        }

	        // Show the popup message with radio buttons for lane selection
	        showPopupMessageWithRadioButtons("Select the lane:", options);
	        try {
				battle.purchaseWeapon(2, battle.getOriginalLanes().get(2));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				this.displayAlert("not avalaible lanes", "lanes not avaliable");
			}
	        updateLabels();
	    });
	    Button VolleySpreadCannon = new Button("Name: Wall SpreadCannon"+"\n"+"Type: Volley Spread Cannon"+ "\n"+ "Price: "+100+ "\n" +"Damage: "+5);
	    VolleySpreadCannon.setOnAction(event -> {
	        // Create an array of lane options
	        String[] options = new String[battle.getOriginalLanes().size()];
	        for (int i = 0; i < battle.getOriginalLanes().size(); i++) {
	            options[i] = "Lane " + (i + 1);
	        }

	        // Show the popup message with radio buttons for lane selection
	        showPopupMessageWithRadioButtons("Select the lane:", options);
	        try {
				battle.purchaseWeapon(3, battle.getOriginalLanes().get(0));
			} catch (Exception e) {				
				this.displayAlert("not avalaible lanes", "lanes not avaliable");
			}
	        updateLabels();
	    });
	    Button WallTrap = new Button("Name: Proximity Trap"+"\n"+"Type: Wall Trap"+ "\n" +"Price: " +75+"\n"+ "Damage: "+100);
	    WallTrap.setOnAction(event -> {
	        // Create an array of lane options
	        String[] options = new String[battle.getOriginalLanes().size()];
	        for (int i = 0; i < battle.getOriginalLanes().size(); i++) {
	            options[i] = "Lane " + (i + 1);
	        }

	        // Show the popup message with radio buttons for lane selection
	        showPopupMessageWithRadioButtons("Select the lane:", options);
	        try {
				battle.purchaseWeapon(4, battle.getOriginalLanes().get(0));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				this.displayAlert("not avalaible lanes", "lanes not avaliable");
			}
	        updateLabels();
	    });
	    hbox.getChildren().addAll(PiercingCannon, SniperCannon, VolleySpreadCannon, WallTrap);
	    
	    // Set position for the hbox
	    AnchorPane.setRightAnchor(hbox, 100.0);

	    anchorPane2 = new AnchorPane();
	    anchorPane2.getChildren().addAll(imageView,lane1, lane2, lane3,lane4,lane5, goBackButton, passTurn, hbox);
	    anchorPane2.setRightAnchor(goBackButton, 20.0);
	    anchorPane2.setBottomAnchor(passTurn, 40.0);
	    scene4 = new Scene(anchorPane2, 700, 700);
	    scoreLabel = new Label();
	    phaseLabel = new Label();
	    resourcesLabel = new Label();
	    TurnsLabel = new Label();
	    Lanes = new Label();
	    updateLabels();
	    HBox updates  = new HBox ();
	    updates.getChildren().addAll(scoreLabel, phaseLabel, resourcesLabel,TurnsLabel,Lanes);
	    updates.setSpacing(20);
	    // Add labels to the anchorPane2
	    anchorPane2.getChildren().addAll(updates);
	    anchorPane2.setBottomAnchor(updates, 80.0);
	    return scene4;
	}
	private void displayAlert(String title, String message) {
        Stage alertStage = new Stage();
        alertStage.setTitle(title);

        Label label = new Label(message);
        Button closeButton = new Button("Error");
        //closing is predefined
        closeButton.setOnAction(event -> alertStage.close());

        BorderPane pane = new BorderPane();
        pane.setTop(label);
        pane.setCenter(closeButton);

        Scene scene = new Scene(pane, 500, 100);
        alertStage.setScene(scene);
        alertStage.show();
    }
	
	private void updateLaneo(int number,VBox lane){
		lane.getChildren().clear();
		number++;
		Label laneLabel = new Label("Lane " + number);
		laneLabel.setTextFill(Color.WHITE);
	    laneLabel.setPadding(new Insets(5)); // Add padding around the label
//	    resourcesLabel.setPadding(new Insets(5)); // Add padding around the label
	    Label currentHealth = new Label ("currentHealth: "+battle.getOriginalLanes().get(number-1).getLaneWall().getCurrentHealth());
	   // currentHealth.setPadding(new Insets(5)); 
	    currentHealth.setTextFill(Color.WHITE);
	    Label dangerLevel = new Label ("Danger Level: "+ battle.getOriginalLanes().get(number-1).getDangerLevel());
	    dangerLevel.setTextFill(Color.WHITE);
	   // dangerLevel.setPadding(new Insets(5));
	    Rectangle wall = new Rectangle(100, 50); // Set dimensions of the wall
	    if(battle.getOriginalLanes().get(number-1).isLaneLost()){
	    	wall.setFill(Color.RED);
	    	// checkGameStatus(primaryStage);
	    }
	    else
	    	wall.setFill(Color.GRAY); // Set color of the wall
	    Pane p = new Pane();
	    p.setPrefWidth(100);
	    p.setMinWidth(100);
	    p.setPrefHeight(300);
	    p.setMinHeight(300);
	    PriorityQueue<Titan> titans=battle.getOriginalLanes().get(number-1).getTitans();
	    for(Titan t:titans){
	    	Circle c= new Circle();
	    	c.setRadius(10);
	    	if(t instanceof PureTitan){
	    		c.setFill(Color.BLACK);
	    	}else if(t instanceof ArmoredTitan){
	    		c.setFill(Color.RED);
	    	}else if(t instanceof ColossalTitan){
	    		c.setFill(Color.BLUE);
	    	}else{
	    		c.setFill(Color.GREEN);
	    	}
	    	p.getChildren().add(c);
	    	c.setLayoutX(Math.random()*100);
	    	//calculate the percentage of distance from the wall and then multiply it by the height
	    	c.setLayoutY(300-((t.getDistance()+0.0)/battle.getTitanSpawnDistance())*300);
	    	
	    }
	    lane.getChildren().addAll(p,laneLabel,currentHealth,dangerLevel,wall);
	   
	}
	private void updateLabels() {
		for(int i=0;i<laneso.length;i++){
			updateLaneo(i, laneso[i]);
		}
	    scoreLabel.setText("Score: " + battle.getScore());
	    scoreLabel.setTextFill(Color.WHITE);
	    phaseLabel.setText("Battle Phase: " + battle.getBattlePhase());
	    phaseLabel.setTextFill(Color.WHITE);
	    resourcesLabel.setText("Resources: " + battle.getResourcesGathered());
	    resourcesLabel.setTextFill(Color.WHITE);
	    TurnsLabel.setText("Turn: "+ battle.getNumberOfTurns());
	    TurnsLabel.setTextFill(Color.WHITE);
	    Lanes.setText("Lanes: " + battle.getOriginalLanes().size());
	    Lanes.setTextFill(Color.WHITE);
	
	}
   
	private Scene getGameOverScene(Stage primaryStage) {
	    VBox gameOverWindow = new VBox(10);
	    gameOverWindow.setPadding(new Insets(20));
	    gameOverWindow.setAlignment(Pos.CENTER);

	    Label gameOverLabel = new Label("Game Over!");

	    Button restartButton = new Button("Restart Game");
	    restartButton.setOnAction(e -> {
	        // Reset game state
	        
	        // Return to Start Window
	        primaryStage.setScene(scene); // Assuming 'scene' is your first scene
	    });

	    gameOverWindow.getChildren().addAll(gameOverLabel, restartButton);

	    return new Scene(gameOverWindow, 400, 200);
	}

	private void checkGameStatus(Stage primaryStage) {
	    if (battle.isGameOver()) {
	        primaryStage.setScene(getGameOverScene(primaryStage));
	    }
	}
	private void showPopupMessageWithRadioButtons(String message, String[] options) {
	    Stage popupStage = new Stage();
	    //popupStage.initOwner(primaryStage); // Set the owner stage if needed
	    popupStage.setTitle("Select Lane");

	    // Create a VBox to hold the radio buttons
	    VBox radioButtonsContainer = new VBox();

	    // Create a toggle group for radio buttons
	    ToggleGroup group = new ToggleGroup();

	    // Create and add radio buttons for each option
	    for (String option : options) {
	        RadioButton radioButton = new RadioButton(option);
	        radioButton.setToggleGroup(group);
	        radioButtonsContainer.getChildren().add(radioButton);
	    }

	    // Create a button to confirm selection
	    Button confirmButton = new Button("OK");
	    confirmButton.setOnAction(e -> popupStage.close());

	    // Create the layout
	    VBox layout = new VBox();
	    layout.getChildren().addAll(new Label(message), radioButtonsContainer, confirmButton);

	    // Set the scene
	    Scene scene = new Scene(layout, 300, 200);
	    popupStage.setScene(scene);

	    // Show the popup
	    popupStage.showAndWait();
	}
    public static void main(String[] args) {
        launch(args);
   
  
}

}



	


