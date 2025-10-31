import java.awt.event.MouseEvent;

public class Door extends GameObject {
	
	private String roomID;

public String getID() 
{
	return roomID;
}
public Door(String roomID)
{
	super(0,0,"arrow.png","");
}

public void onMouseAction(MouseEvent e) {
	
}
}
