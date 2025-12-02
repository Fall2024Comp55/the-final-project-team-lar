import acm.program.*;
import acm.graphics.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import acm.util.*;
import javax.swing.*;
import java.awt.Color;
import java.io.File;
import java.io.IOException;


/*
 * The main controller class for the game.
 */
public class GraphicsGame extends GraphicsPane implements ScreenDelegate {

	private MainApplication app;
	private Level currentLevel;
	private LevelGate LevelGate;
	private GameState gameState;
	SoundManager soundManager = SoundManager.getInstance();
	private ArrayList<String> passwords = new ArrayList<String>();
	private GLabel batteryLabel;
	private GLabel messageLabel;
	private GImage lightHole;
	//private GRect darkness;
	//private GOval lightHole;
	
	//constructor
	public GraphicsGame(MainApplication app) {
        this.app = app;
        this.gameState = GameState.MENU; // will change once gameplay starts
        this.soundManager = SoundManager.getInstance();
    	this.LevelGate = new LevelGate(app);

        passwords.add("apples");//password for level 1
        passwords.add("bees");//password for level 2 buggy bc lvl2 and on don't generate properly
        LevelGate.setPasswords(passwords);
    }
	 
	// Called when MainApplication displays this pane
    @Override
    public void showContent() {
        //startNewLevel(1);
    }
	
    public Level getCurrentLevel() {
    	return currentLevel;
    }
    
    public ArrayList<String> getPasswords()
    {
    	return passwords;
    }
	
    public void startNewLevel(int levelNum) {
    	System.out.println("startNewLevel called");
    	app.removeAll();
    	gameState = GameState.PLAYING;
    	
        currentLevel = new Level(app, levelNum, "*****");
        currentLevel.setDelegate(this);
        currentLevel.generateLevel();
        currentLevel.getFlashlight().setDelegate(this);
        currentLevel.getFlashlight().startTimer();

        drawRoom();         // draw current room
        
        setUpDarkness();
        drawHUD();          // battery, messages, UI
        currentLevel.getFlashlight().getCursorLight().sendToFront();

        soundManager.play("ambient");
    }
    
    public void startNewLevel(int levelNum, String password) {
    	app.removeAll();
    	gameState = GameState.PLAYING;
    	
    	//currentLevel = null;
        currentLevel = new Level(app, levelNum, password);
        currentLevel.setDelegate(this);
        currentLevel.generateLevel();
        currentLevel.getFlashlight().setDelegate(this);
        currentLevel.getFlashlight().startTimer();

        drawRoom();         // draw current room
        setUpDarkness();
        drawHUD();          // battery, messages, UI
        currentLevel.getFlashlight().getCursorLight().sendToFront();

        soundManager.play("ambient");
    }
    
    // Called when MainApplication hides this pane
    @Override
    public void hideContent() {
        app.removeAll();
    }
	 
//-----Drawing Functions-----//
    
    public void drawLevelGate() {
    	LevelGate.setUpLevelGate();
    	LevelGate.showContent();
    }
    
    private void drawRoom() {
        Room room = currentLevel.getCurrentRoom();
        room.showContent();                    // background, monster, distractions
    }
    
    private void drawHUD() {
        batteryLabel = new GLabel("Battery: 100%", 10, 20);
        batteryLabel.setColor(java.awt.Color.WHITE);
        batteryLabel.setFont("Arial-Bold-16");
        app.add(batteryLabel);

        messageLabel = new GLabel("", 10, 50);
        messageLabel.setColor(java.awt.Color.RED);
        messageLabel.setFont("Arial-Bold-20");
        app.add(messageLabel);
    }
    
    private void updateHUD() {
        double b = currentLevel.getFlashlight().getBattery();
        batteryLabel.setLabel("Battery: " + (int)(b) + "%");
        batteryLabel.sendToFront();
    }

    public void setUpDarkness() {
    	 // Only create the mask ONCE per level
        if (lightHole == null) {
            lightHole = new GImage("Media/regularLight.png", -400, -300);
            app.add(lightHole);
            lightHole.sendToFront();
        } else {
            // If mask already exists but was hidden or moved behind, bring back
            app.add(lightHole);
            lightHole.sendToFront();
            app.add(batteryLabel);
            batteryLabel.sendToFront();
        }
    }
    
//-----Mouse Handlers-----//
	 @Override
	 public void mouseMoved(MouseEvent e) {
		 if (gameState != GameState.PLAYING) return;

	     currentLevel.getFlashlight().MouseMoved(e);
	     
	     //if (lightHole != null) {
		     lightHole.setLocation(e.getX() - lightHole.getWidth()/2, e.getY() - lightHole.getHeight()/2);
		     lightHole.sendToFront();
		     batteryLabel.sendToFront();
		 //}
	 }
	 
	 @Override
	 public void mousePressed(MouseEvent e) {
	    if (gameState != GameState.PLAYING) return;
	    
	    currentLevel.getFlashlight().MouseClicked(e);
	    
	    lightHole.setImage("Media/shineFlashlight.png");
	    new javax.swing.Timer(1000, ev -> {
	       lightHole.setImage("Media/regularLight.png");
	    }).start();
	    
	    
	    if (currentLevel.checkMonsterFound(e.getX(), e.getY())) {
	    	javax.swing.Timer t = new javax.swing.Timer(4000, evt -> {
	            ((javax.swing.Timer)evt.getSource()).stop();
	            onMonsterRevealed();
	        });
	        t.setRepeats(false);
	        t.start();
	        return;
	    }
	    
	    updateHUD();
	    
	    currentLevel.getCurrentRoom().mouseClicked(e);
	    
	 }
	 
	 
	 //-----Delegate callbacks-----//
	 /*test.loadSound("main", "Media/test ambients.wav");
		test.loadSound("test", "Media/test.au");
		test.play("main");
		test.play("test");
	*/
	 
	 @Override 
	 public void onDoorOpened(){
		 soundManager.loadSound("door-creak", "Media/door-creak.au");
		 soundManager.play("door-creak");
	 }
	 
	 @Override 
	 public void onFlashlightTurnedOn(){
		 soundManager.loadSound("flashlight", "Media/flashlight-clicking-on.au");
		 soundManager.play("flashlight");
	 }
	 
	 @Override
	 public void onMonsterRevealed() {
		 //soundManager.loadSound("monster_revealed", "Media/monster_revealed.wav");
		 soundManager.play("monster_revealed");
		 currentLevel.getFlashlight().stopTimer();
	     onLevelComplete();
	 }
	 
	 @Override
	 public void onBatteryLow(double remaining) {
		 //soundManager.play("battery_low");
		 updateHUD();
	 }
	 
	 @Override
	 public void onPlayerLose() {
		 soundManager.play("lose");
	     gameState = GameState.LOSE;
	     currentLevel.getFlashlight().stopTimer();
	     showLoseScreen();
	 }
	 
	 @Override
	 public void onDistractionTriggered(String type) {
		 soundManager.loadSound(type, "Media/" + type + ".au");
		 soundManager.play(type);
	 }
	 
	 @Override
	 public void onLevelComplete() {
		 
		 gameState = GameState.WIN;
	     showWinScreen();
	 }
	 
	 
	 
//-----Game Screen changes-----// 
	 private void showWinScreen() {
		 app.switchToWin(); //lei lei added 
		messageLabel.setLabel("You found El Cucuy!");
		//messageLabel.setFont("SansSerif-30");
		//add(win, getWidth() / 2 - 100, getHeight() / 2);
	 }
	 
	 private void showLoseScreen() {
	      messageLabel.setLabel("You died...");
	      //lose.setFont("SansSerif-30");
	      //add(lose, getWidth() / 2 - 120, getHeight() / 2);
	 }

}