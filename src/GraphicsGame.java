import acm.program.*;
import acm.graphics.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import acm.util.*;

/*
 * The main game controller class for the game.
 */
public class GraphicsGame extends GraphicsProgram implements ScreenDelegate {
	 public static final int WINDOW_WIDTH = 800;
	 public static final int WINDOW_HEIGHT = 600;
	 
	 private Level currentLevel;
	 private GameState gameState;
	 SoundManager soundManager = SoundManager.getInstance();
	 
	 private GLabel batteryLabel;
	 //private GImage background;
	 
	 private Flashlight flashlight;
	 
	 @Override
	    public void init() {
	        setSize(800, 600);
	        addMouseListeners();
	 }
	 
	 @Override
	 public void run() {
		 
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
	 
}