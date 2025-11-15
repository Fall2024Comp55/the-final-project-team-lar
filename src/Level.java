import java.util.ArrayList;

public class Level {
	Flashlight flashlight;
	ArrayList<Room> rooms;
	Room currentRoom;
	Monster Monster;
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
	
	}
	
	public void generateLevel() {
		this.flashlight = new Flashlight(mainScreen,200,20);
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
	
	
}
