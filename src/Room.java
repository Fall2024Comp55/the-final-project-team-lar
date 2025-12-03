import java.util.*;
import acm.graphics.*;
import java.awt.event.*;

public class Room extends GraphicsPane{

	private String id;
	private ArrayList<Distraction> distractions;
	private ArrayList<Door> doors;
	private String imagePath; 
	private Monster monster;
	private GImage BackgroundImage;
	
	public Room(MainApplication mainScreen, String num) {
		this.mainScreen = mainScreen;
		id = num; 
		doors = new ArrayList<Door>();
		distractions = new ArrayList<Distraction>();
		monster = null; //new Monster(mainScreen, 1,1,1,1); //initialization 
		imagePath = "room" + id + ".png"; //change this if diff file format is used 
		BackgroundImage = new GImage(imagePath, 0, 0);
		BackgroundImage.setSize(mainScreen.getWidth(), mainScreen.getHeight());
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
	
	public void addDistraction(MainApplication mainScreen, double x, double y,DistractionType t) {
		Distraction theDistraction = new Distraction(mainScreen, x, y, t); 
		theDistraction.add();
		distractions.add(theDistraction);
	}
	
	public void addDoor(MainApplication mainScreen, String n, double x, double y) {
		Door thedoor = new Door(mainScreen, x, y, n); 
		thedoor.add();
		doors.add(thedoor);
	}
	
	public Door getDoor(int i) {
		return doors.get(i);
	}
	
	public void setMonster(Monster m) { 
		this.monster = m; 
	}
	
	public void drawRoom() {
		hideContent();
		showContent();
	}
	
	
	/*public void setLight (Flashlight light) {
		this.light = light;
	}*/
	
	
	@Override
	public void showContent() {
		mainScreen.add(BackgroundImage);
		contents.add(BackgroundImage);
		if (monster != null) {
			monster.add(); 
		}
		for(Distraction m: distractions) {
			m.add(); 
		}
		for(Door d : doors) {
			d.add(); 
		}
		System.out.println("Drawing room " + id);
		BackgroundImage.sendToBack();
		
	}
	
	@Override
	public void hideContent() {
		for (GObject o: contents) {
			mainScreen.remove(o);		
		}
		contents.clear();
		for(Door d : doors) {
			d.remove(); 
		}
		for(Distraction a:distractions) {
			a.remove();
		}
		if (monster != null) {
			monster.remove();
		}

		mainScreen.remove(BackgroundImage);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("room mouse click");
		double x = e.getX();
		double y = e.getY();
		
		// --- DOOR CLICK DETECTION ---
	    for (Door d : doors) {    // you must store doors in a list!
	        if (d.pointsIn(x, y)) {
	            d.onMouseAction(e);
	            System.out.print("door click");
	            return; // stop — don't let other objects handle the click
	        }
	    }
		
		
		for(Distraction m: distractions) {
			if(m.pointsIn(x, y)) {
				m.onMouseAction(e);
			}
		}
		
		//monster detection
		if(monster != null && monster.pointsIn(x, y))
		{
			monster.onMouseAction(e);
		}
		
		System.out.println("roomShine");
		System.out.println(mainScreen.getGamePane().getLightHole());
		mainScreen.getGamePane().getCurrentLevel().getFlashlight().MouseClicked(e);
		mainScreen.getGamePane().setLightHole("Media/shineFlashlight.png");    
		new javax.swing.Timer(2000, ev -> {
			mainScreen.getGamePane().setLightHole("Media/regularlight.png");    
		    }).start();
		
	}
	
	
}
