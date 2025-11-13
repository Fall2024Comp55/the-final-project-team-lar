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
public class GraphicsGame extends GraphicsProgram implements ScreenDelegate {
	 public static final int WINDOW_WIDTH = 800;
	 public static final int WINDOW_HEIGHT = 600;
	 
	 private Level currentLevel;
	 private GameState gameState;
	 SoundManager soundManager = SoundManager.getInstance();
	 
	 private GLabel batteryLabel;
	 private GRect batteryLevel;
	 private GRect batteryBackground;
	 //private GImage background;
	 
	 // Game timer used for updating screen
	 private javax.swing.Timer gameTimer;
	 
	 private Flashlight flashlight;
	 
	 @Override
	    public void init() {
	        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	        addMouseListeners();
	        
	        
	        //addMouseMotionListeners();
	        setupUI();

	        gameState = GameState.MENU;
	        
	 }
	 
	 @Override
	 public void run() {
		 startGame();
	 }
	 
	 public void setupUI() {
		// /*
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
	    // */
	 }
	 
	 private void startGame() {
        currentLevel = new Level(1, "abc123");
        currentLevel.generateLevel();
        gameState = GameState.PLAYING;

        flashlight = currentLevel.flashlight;
        flashlight.toggle(true);

        soundManager.loop("ambient");

        // Periodic updates (battery drain, monster movement)
        gameTimer = new javax.swing.Timer(100, e -> update());
        gameTimer.start();
    }

    private void update() {
        if (gameState != GameState.PLAYING) return;

        updateBattery();

        if (flashlight.isEmpty()) {
            onPlayerLose();
        }

        // TODO: Future monster movement logic
    }
    
    private void updateBattery() {
        double percent = flashlight.getBattery() / 200.0;
        batteryLevel.setSize(150 * percent, 20);
        batteryLabel.setLabel("Battery: " + (int) (percent * 100) + "%");

        if (percent < 0.3) batteryLevel.setFillColor(Color.RED);
        else if (percent < 0.6) batteryLevel.setFillColor(Color.ORANGE);
        else batteryLevel.setFillColor(Color.GREEN);
    }
    
	 
	 //-----Mouse Handlers-----//
	 @Override
	 public void mouseMoved(MouseEvent e) {
		 if (gameState != GameState.PLAYING) return;
	     flashlight.MouseMoved(e);
	 }
	 
	 @Override
	 public void mousePressed(MouseEvent e) {
	    if (gameState != GameState.PLAYING) return;
	    flashlight.MouseClicked(e);
	    if (currentLevel.checkMonsterFound(e.getX(), e.getY())) {
	        onMonsterRevealed();
	    }
	 }
	 
	 
	 //-----Delegate callbacks-----//
	 @Override
	 public void onMonsterRevealed() {
		 
	 }
	 
	 @Override
	 public void onBatteryLow(double remaining) {
		 
	 }
	 
	 @Override
	 public void onPlayerLose() {
		 
	 }
	 
	 @Override
	 public void onDistractionTriggered(String type) {
		 
	 }
	 
	 @Override
	 public void onLevelComplete() {
		 
	 }
	 
	 
	 // Main Function
	 public static void main(String[] args) {
			new GraphicsGame().start();
	 }
}