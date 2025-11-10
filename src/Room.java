import java.util.*;
import acm.graphics.*;

public class Room {

	private String id;
	private ArrayList<Distraction> distractions;
	private boolean isVisited;
	private String imagePath; 
	private GImage BackgroundImage;
	//screenDelegate
	
	void room(String num) {
		id = num; 
		imagePath = "room" + id + ".png"; //change this if diff file format is used 
		BackgroundImage = new GImage(imagePath); 
	}
	
	void addDistraction(DistractionType t) {
		Distraction y = new Distraction(t); 
		distractions.add(y);
	}
	
	void addDoor(String n, double x, double y) {
		Door thedoor = new Door(x, y, n); 		
	}
	
	void drawRoom() {
		
	}
	
	void revealObjects() {
		
	}
	
	void showContent() {
		
	}
	
	void hideContent() {
		
	}
	
	void clickedOn(double x, double y) {
		
	}
	
	
}
