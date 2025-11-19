import java.util.*;
import acm.graphics.*;
import java.awt.event.*;

public class Room extends GraphicsPane{

	private String id;
	private ArrayList<Distraction> distractions;
	private String imagePath; 
	private Monster monster;
	private Flashlight light;
	private GImage BackgroundImage;
	
	public Room(MainApplication mainScreen, String num) {
		this.mainScreen = mainScreen;
		id = num; 
		distractions = new ArrayList<Distraction>();
		monster = new Monster(mainScreen, 1,1,1,1); //initialization 
		imagePath = "room" + id + ".png"; //change this if diff file format is used 
		BackgroundImage = new GImage(imagePath, 200, 200); 
		light = new Flashlight(mainScreen, 100, 2);
	}
	
	//add more rooms as needed
	void setupRoom() {
		switch (id) {
		case "1": 
			Room one = new Room(mainScreen, "1");
			mainScreen.switchToRoom(one);
		case "2":
			Room two = new Room(mainScreen, "2"); 
			mainScreen.switchToRoom(two);
		case "3":
			Room three = new Room(mainScreen, "3"); 
			mainScreen.switchToRoom(three);
		case "4":
			Room four = new Room(mainScreen, "4"); 
			mainScreen.switchToRoom(four);
		}
	}
	
	public void addDistraction(DistractionType t) {
		Distraction y = new Distraction(mainScreen, t); 
		y.add();
	}
	public void addDoor(MainApplication mainScreen, String n, double x, double y) {
		Door thedoor = new Door(mainScreen, x, y, n); 
		thedoor.add();
	}
	
	public void drawRoom() {
		hideContent();
		showContent();
	}
	
	public void setLight (Flashlight light) {
		this.light = light;
	}
	
	
	@Override
	public void showContent() {
		mainScreen.add(BackgroundImage);
		contents.add(BackgroundImage);
		monster.add(); 
		light.add();
		for(Distraction m: distractions) {
			m.add(); 
		}
		
	}
	
	@Override
	public void hideContent() {
		for (GObject o: contents) {
			mainScreen.remove(o);		
		}
		contents.clear();
		
		mainScreen.remove(BackgroundImage);
		monster.remove();
		light.remove();
	}
	
	public void mouseClicked(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		
		for(Distraction m: distractions) {
			if(m.getX() == x && m.getY() == y) {
				m.onMouseAction(e);
			}
		}
		monster.onMouseAction(e);
	}
	
	
}
