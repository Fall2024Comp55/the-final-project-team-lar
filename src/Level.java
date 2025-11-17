import java.util.ArrayList;

public class Level {
	Flashlight flashlight;
	Monster Monster;
	ArrayList<Room> rooms;
	Room currentRoom;
	ArrayList<Distraction> distractions;
	Boolean isCompleted;
	int levelNumber;
	String password;
	MainApplication mainScreen;
	GraphicsGame delegate;
	
	
	public Level(int levelNumber, String password) {
		this.levelNumber = levelNumber;
		this.password=password;
		currentRoom = new Room(mainScreen, "0");
		rooms.add(currentRoom);
		//need to add the currentRoom to an arrayList
		
	
	}
	
	public void generateLevel() {
		this.flashlight = new Flashlight(mainScreen,200,20);
		
		//confused on how generateLevel would work as wouldn't we want individual pre-made levels?
		//how will currentRoom be decided at start of the game?
		//how will 
	}
	
	public Room getRoom(int index)
	{
		return rooms.get(index);
	}
	
	
	public boolean checkMonsterFound(double x, double y)
	{
		if(Monster.getX() == x && Monster.getY()==y) {
			return true;
		}
		return false;
	}
	
	
	public void resetLevel() {
		
	}
	
	public void drawAllRooms(){
		
	}
	
	public void switchRoomto(int a) {
		currentRoom = getRoom(a);
	}
	
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
}
