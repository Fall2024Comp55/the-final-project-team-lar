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
		
		this.rooms = new ArrayList<>();
        this.distractions = new ArrayList<>();

		
		/*
		currentRoom = new Room(mainScreen, "0");
		//currentRoom.setLight(flashlight);
		currentRoom.addDoor(mainScreen, "room1", 300, 300);
		
		rooms.add(currentRoom);
		//need to add the currentRoom to an arrayList
		//*/
	
		generateLevel();
		
		if (!rooms.isEmpty()) {
			currentRoom = rooms.get(0);
		}
		
	}
	
	public void generateLevel() {
		// clear any prior state
        rooms.clear();
        distractions.clear();
        Monster = null;
		
		this.flashlight = new Flashlight(mainScreen, 100, 2);
		flashlight.toggle(true);
		flashlight.add();
		flashlight.getCursorLight().sendToFront();
		//Monster = new Monster(mainScreen, 1, 1, 1, 1);
		 
		
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

        // share flashlight across rooms
        /*
        for (Room r : rooms) {
            r.setLight(this.flashlight);
        }
        */
        
        // add doors from hallway to other rooms
        hallway.addDoor(mainScreen, "1", 400, 120); // hallway -> room1
        hallway.addDoor(mainScreen, "2", 400, 260); // hallway -> room2
        hallway.addDoor(mainScreen, "3", 400, 400); // hallway -> room3
        
        hallway.addDistraction(mainScreen, 100, 100, DistractionType.FLY);

        // each room has a door back to hallway
        room1.addDoor(mainScreen, "0", 40, 60);   // room1 -> hallway
        room2.addDoor(mainScreen, "0", 40, 120);  // room2 -> hallway
        room3.addDoor(mainScreen, "0", 40, 180);  // room3 -> hallway
        
        //potential connection from one room to another not via the hallway
        //room2.addDoor(mainScreen, "3", 250, 200); // room2 -> room3
        
        // place monster in a random non-hallway room (1..rooms.size()-1)
        int spawnRoom = 1 + rng.nextInt(Math.max(1, rooms.size() - 1));
        Room monsterRoom = rooms.get(spawnRoom);

        // pick a reasonable spawn position inside the room (adjust to your room images coordinates)
        double spawnX = 150 + rng.nextDouble() * 200; // example x
        double spawnY = 120 + rng.nextDouble() * 200; // example y

        this.Monster = new Monster(mainScreen, spawnX, spawnY, 1.0, spawnRoom);
        // place Monster object into the monster room's state so the room can draw it
        monsterRoom.setMonster(Monster); 

        // set currentRoom to hallway by default
        this.currentRoom = hallway;
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
		
	    // switch GraphicsPane
	    mainScreen.switchToScreen(currentRoom);
	    
	    
	    // ensure flashlight remains visible
	    flashlight.add();
	    flashlight.getCursorLight().sendToFront();
	    
	    delegate.setUpDarkness();
	   
	}
	
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	public Flashlight getFlashlight() {
		return flashlight;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setDelegate(GraphicsGame d) {
		this.delegate = d;
	}
	
}
