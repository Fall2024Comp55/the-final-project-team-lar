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
		currentRoom = new Room(mainScreen, "0");
		//currentRoom.setLight(flashlight);
		currentRoom.addDoor(mainScreen, "room1", 300, 300);
		
		this.rooms = new ArrayList<>();
        this.distractions = new ArrayList<>();
		
		rooms.add(currentRoom);
		//need to add the currentRoom to an arrayList
	}
	
	public void generateLevel() {
		this.flashlight = new Flashlight(mainScreen,200,20);
		Monster = new Monster(mainScreen, 1, 1, 1, 1);
		 
		//confused on how generateLevel would work as wouldn't we want individual pre-made levels?
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
