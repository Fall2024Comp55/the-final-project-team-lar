import java.util.*;
import acm.graphics.*;
import java.awt.event.*;

public class Room extends GraphicsPane{

	private String id;
	private ArrayList<Distraction> distractions;
	private boolean isVisited;
	private String imagePath; 
	private Monster monster;
	private Flashlight light;
	private GImage BackgroundImage;
	
	public Room(MainApplication mainScreen, String num) {
		this.mainScreen = mainScreen;
		id = num; 
		distractions = new ArrayList<Distraction>();
		monster = new Monster(1,1,1,1);
		//imagePath = "room" + id + ".png"; //change this if diff file format is used 
		imagePath = "back.jpg";
		BackgroundImage = new GImage(imagePath, 200, 200); 
	}
	
	public void addDistraction(DistractionType t) {
		Distraction y = new Distraction(t); 
		distractions.add(y);
	}
	
	public void addDoor(String n, double x, double y) {
		Door thedoor = new Door(x, y, n); 		
	}
	
	public void drawRoom() {
		hideContent();
		showContent();
	}
	
	public void revealObjects() {
		
		
	}
	
	@Override
	public void showContent() {
		mainScreen.add(BackgroundImage);
		//screen.add(monster); 
		//screen.add(light);
		for(Distraction m: distractions) {
			//screen.add(m);
		}
		
	}
	
	@Override
	public void hideContent() {
		mainScreen.remove(BackgroundImage);
		//delegate.remove(monster);
		//delegate.remove(light);
		for(Distraction m: distractions) {
			//delegate.remove(m);
		}
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
