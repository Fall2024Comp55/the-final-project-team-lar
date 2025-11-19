import java.util.ArrayList;
import java.util.Random;

public class Level {
	private Flashlight flashlight;
	private Monster Monster;
	private ArrayList<Room> rooms;
	private Room currentRoom;
	private ArrayList<Distraction> distractions;
	private Boolean isCompleted;
	private int levelNumber;
	private String password;
	private MainApplication mainScreen;
	private GraphicsGame delegate;
	private final Random rng = new Random();
	
	
	public Level(MainApplication mainScreen, int levelNumber, String password) {
		this.levelNumber = levelNumber;
		this.password = password;
		this.mainScreen = mainScreen;
		this.isCompleted = false;
		
		/*
		currentRoom = new Room(mainScreen, "0");
		//currentRoom.setLight(flashlight);
		currentRoom.addDoor(mainScreen, "room1", 300, 300);
		
		this.rooms = new ArrayList<>();
        this.distractions = new ArrayList<>();
		
		rooms.add(currentRoom);
		//need to add the currentRoom to an arrayList
		//*/
	
		generateLevel();
		
		if (!rooms.isEmpty()) {
			currentRoom = rooms.get(0);
		}
		
	}
	
	public void generateLevel() {
		rooms.clear();
        distractions.clear();
        Monster = null;
		
		this.flashlight = new Flashlight(mainScreen,200,2);
		//Monster = new Monster(mainScreen, 1, 1, 1, 1);
		 
		//confused on how generateLevel would work as wouldn't we want individual pre-made levels?
		
		// build rooms
        // room "0" is hallway; other rooms are "1","2","3"
        Room hallway = new Room(mainScreen, "0");
        Room room1   = new Room(mainScreen, "1");
        Room room2   = new Room(mainScreen, "2");
        Room room3   = new Room(mainScreen, "3");
        
        // register rooms in order (index == numeric id)
        rooms.add(hallway); // index 0
        rooms.add(room1);   // index 1
        rooms.add(room2);   // index 2
        rooms.add(room3);   // index 3

	}
	
	public Room getRoom(int index){
		return rooms.get(index);
	}
	
	
	public boolean checkMonsterFound(double x, double y){
		if(Monster.getX() == x && Monster.getY()==y) {
			return true;
		}
		return false;
	}
	
	
	public void resetLevel() {
		
	}
	
	public void drawAllRooms(){
		for(Room r:rooms)
		{
			r.drawRoom();
		}
	}
	
	public void switchRoomto(int a) {
		currentRoom = getRoom(a);
		mainScreen.switchToScreen(currentRoom);
	}
	
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	public Flashlight getFlashlight() {
		return flashlight;
	}
	
}
