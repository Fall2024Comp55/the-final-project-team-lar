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
	//private GraphicsGame delegate; 
	
	public void room(String num) {
		id = num; 
		imagePath = "room" + id + ".png"; //change this if diff file format is used 
		BackgroundImage = new GImage(imagePath); 
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
	
	public void showContent() {
		//delegate.add(BackgroundImage);
		//delegate.add(monster);
		//delegate.add(light);
		for(Distraction m: distractions) {
			//delegate.add(m);
		}
		
	}
	
	public void hideContent() {
		//delegate.remove(BackgroundImage);
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
