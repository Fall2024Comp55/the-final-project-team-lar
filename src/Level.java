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
	//delegate GraphicsGame
	
	
	public Level(int levelNumber, String password) {
		this.levelNumber = levelNumber;
		this.password=password;
	}
	
	public void generateLevel() {
		this.flashlight = new Flashlight(200,20);
		
	}
	
	public Room getRoom(int index)
	{
		return rooms.get(index);
	}
	
	public boolean checkMonsterFound(double x, double y)
	{
		return true;
		//will finish later
	}
	
	
	/*public void resetLevel() {
		
	}
	
	public void drawAllRooms(){
		
	}
	*/
	public void switchRoomto(int a) {
		currentRoom = getRoom(a);
	}
	
	
}
