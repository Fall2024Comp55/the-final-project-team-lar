import java.awt.event.MouseEvent;

public class Door extends GameObject {
	
	private String roomID;

	public String getID() 
	{
		return roomID;
	}
	//made 2 constructors in care we want to specify the location the door is created
	public Door(String roomID)
	{
		super(0,0,"arrow.png","insertSoundName(maybe door creak or smthn)");
		this.roomID = roomID;
	}
	
	public Door(double x, double y, String roomID)
	{
		super(x,y,"arrow.png","insertSoundName(maybe door creak or smthn)");
		this.roomID = roomID;
	}

	public void onMouseAction(MouseEvent e) {
		this.getID();
	}
}
