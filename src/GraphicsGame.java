import acm.program.*;
import acm.graphics.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import acm.util.*;

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
	 
	 private Flashlight flashlight;
	 
	 @Override
	    public void init() {
	        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	        addMouseListeners();
	        
	        
	        //addMouseMotionListeners();
	        //setupUI();

	        gameState = GameState.MENU;
	        
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
	 
	 @Override
	 public void run() {
		 //startGame();
	 }
	 
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
	 
	 public static void main(String[] args) {
			new GraphicsGame().start();
	 }
}