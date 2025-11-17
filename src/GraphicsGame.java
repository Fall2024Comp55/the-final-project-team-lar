import acm.program.*;
import acm.graphics.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import acm.util.*;
import javax.swing.*;
import java.awt.Color;

/*
 * The main controller class for the game.
 */
public class GraphicsGame extends GraphicsPane implements ScreenDelegate {

	private MainApplication app;
	 
	private Level currentLevel;
	private GameState gameState;
	SoundManager soundManager = SoundManager.getInstance();
	 
	private GLabel batteryLabel;
	private GLabel messageLabel;
	//private GImage background;
	
	//constructor
	public GraphicsGame(MainApplication app) {
        this.app = app;
        this.gameState = GameState.MENU; // will change once gameplay starts
        this.soundManager = SoundManager.getInstance();
    }
	 
	// Called when MainApplication displays this pane
    @Override
    public void showContent() {
        startNewLevel(1);
    }
	
	
    public void startNewLevel(int levelNum) {
    	app.removeAll();

        currentLevel = new Level(levelNum, "*****");
        currentLevel.generateLevel();

        gameState = GameState.PLAYING;

        //drawRoom();         // draw current room
        //drawHUD();          // battery, messages, UI

        soundManager.play("ambient");
    }
    
	 public void setupUI() {
		/*
		 batteryLabel = new GLabel("100%");
	        
		 batteryLevel = new GRect(150, 25);
	     batteryLevel.setFilled(true);
	     batteryLevel.setFillColor(Color.red);
	        
	     batteryBackground = new GRect(150, 25);
	     batteryBackground.setFilled(true);
	     batteryBackground.setFillColor(Color.gray);
	        
	     add(batteryBackground, 10, 525);
	     add(batteryLevel, 10, 525);
	     add(batteryLabel, 65, 545);
	    */
	 }
	 
	 private void startGame() {
        currentLevel = new Level(1, "abc123");
        currentLevel.generateLevel();
        gameState = GameState.PLAYING;

        soundManager.loop("ambient");
    }

    private void update() {
        if (gameState != GameState.PLAYING) return;

        updateBattery();

        
        // TODO: Future monster movement logic
    }
    
    private void updateBattery() {
        //batteryLabel.setLabel("Battery: " + (int) (percent * 100) + "%");
        
        /*
        if (percent < 0.3) batteryLevel.setFillColor(Color.RED);
        else if (percent < 0.6) batteryLevel.setFillColor(Color.ORANGE);
        else batteryLevel.setFillColor(Color.GREEN);
        */
    }
    
	 
	 //-----Mouse Handlers-----//
	 @Override
	 public void mouseMoved(MouseEvent e) {
		 if (gameState != GameState.PLAYING) return;
	     //flashlight.MouseMoved(e);
	 }
	 
	 @Override
	 public void mousePressed(MouseEvent e) {
	    if (gameState != GameState.PLAYING) return;
	    //flashlight.MouseClicked(e);
	    if (currentLevel.checkMonsterFound(e.getX(), e.getY())) {
	        onMonsterRevealed();
	    }
	 }
	 
	 
	 //-----Delegate callbacks-----//
	 @Override
	 public void onMonsterRevealed() {
		 soundManager.play("monster_revealed");
	     onLevelComplete();
	 }
	 
	 @Override
	 public void onBatteryLow(double remaining) {
		 soundManager.play("battery_low");
	 }
	 
	 @Override
	 public void onPlayerLose() {
		 soundManager.play("lose");
	     gameState = GameState.LOSE;
	     showLoseScreen();
	 }
	 
	 @Override
	 public void onDistractionTriggered(String type) {
		 soundManager.play(type);
	 }
	 
	 @Override
	 public void onLevelComplete() {
		 gameState = GameState.WIN;
	     showWinScreen();
	 }
	 
	 
	 
	//-----Game Screen changes-----// 
	 private void showWinScreen() {
		//removeAll();
		//GLabel win = new GLabel("You found El Cucuy!");
		//win.setFont("SansSerif-30");
		//add(win, getWidth() / 2 - 100, getHeight() / 2);
	 }
	 
	 private void showLoseScreen() {
	      //removeAll();
	      //GLabel lose = new GLabel("You died...");
	      //lose.setFont("SansSerif-30");
	      //add(lose, getWidth() / 2 - 120, getHeight() / 2);
	 }
	 
	 
	 //-----Main Function-----//
	 public static void main(String[] args) {
			//new GraphicsGame().start();
	 }
}