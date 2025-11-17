import java.awt.event.MouseEvent;

public class Door extends GameObject {
	
	private String roomID;
	private MainApplication mainScreen;
	
	public String getID() 
	{
		return roomID;
	}
	//made 2 constructors in care we want to specify the location the door is created
	public Door(MainApplication mainScreen, String roomID)
	{
		super(0,0,"arrow.png","insertSoundName(maybe door creak or smthn)");
		this.mainScreen = mainScreen;
		this.roomID = roomID;
	}
	
	public Door(MainApplication mainScreen, double x, double y, String roomID)
	{
		super(x,y,"arrow.png","insertSoundName(doorCreak)");
		this.mainScreen = mainScreen;
		this.roomID = roomID;
	}
	
	public void add() {
		mainScreen.add(image);
	}
	
	public void onMouseAction(MouseEvent e) {
		this.getID();
	}
}
