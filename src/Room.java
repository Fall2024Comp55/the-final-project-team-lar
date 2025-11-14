import java.util.*;
import acm.graphics.*;
import java.awt.event.*;

public class Room {

	private String id;
	private ArrayList<Distraction> distractions;
	private boolean isVisited;
	private String imagePath; 
	private Monster monster;
	private Flashlight light;
	private GImage BackgroundImage;
	private ScreenDelegate delegate;
	private GraphicsGame screen;
	
	public void room(String num) {
		id = num; 
		imagePath = "room" + id + ".png"; //change this if diff file format is used 
		BackgroundImage = new GImage(imagePath); 
	}
	
	public void addDistraction(DistractionType t) {
		//Distraction y = new Distraction(t); 
		//distractions.add(y);
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
	
	public void showContent() {
		screen.add(BackgroundImage);
		//screen.add(monster); 
		//screen.add(light);
		for(Distraction m: distractions) {
			//screen.add(m);
		}
		
	}
	
	public void hideContent() {
		screen.remove(BackgroundImage);
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
