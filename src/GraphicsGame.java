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
	
    public Level getCurrentLevel() {
    	return currentLevel;
    }
	
    public void startNewLevel(int levelNum) {
    	app.removeAll();

        currentLevel = new Level(app, levelNum, "*****");
        currentLevel.generateLevel();

        gameState = GameState.PLAYING;

        drawRoom();         // draw current room
        drawHUD();          // battery, messages, UI

        soundManager.play("ambient");
    }
    
    // Called when MainApplication hides this pane
    @Override
    public void hideContent() {
        app.removeAll();
    }
	 
//-----Drawing Functions-----//
    
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
        batteryLabel.setLabel("Battery: " + (int)(b * 100) + "%");
    }

    
//-----Mouse Handlers-----//
	 @Override
	 public void mouseMoved(MouseEvent e) {
		 if (gameState != GameState.PLAYING) return;

	     currentLevel.getFlashlight().MouseMoved(e);
	 }
	 
	 @Override
	 public void mousePressed(MouseEvent e) {
	    if (gameState != GameState.PLAYING) return;
	    
	    currentLevel.getFlashlight().MouseClicked(e);
	    
	    if (currentLevel.checkMonsterFound(e.getX(), e.getY())) {
	        onMonsterRevealed();
	    }
	    
	    updateHUD();
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