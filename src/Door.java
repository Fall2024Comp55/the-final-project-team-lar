import java.awt.event.MouseEvent;

public class Door extends GameObject {
	
	private String roomID;
	//private MainApplication mainScreen; //unnecessary as GameObject now has mainScreen
	
	public String getID() 
	{
		return roomID;
	}
	//made 2 constructors in care we want to specify the location the door is created
	public Door(MainApplication mainScreen, String roomID)
	{
		super(mainScreen,0,0,"arrow.png","insertSoundName(maybe door creak or smthn)");
		this.roomID = roomID;
	}
	
	public Door(MainApplication mainScreen, double x, double y, String roomID)
	{
		super(mainScreen,x,y,"arrow.png","insertSoundName(doorCreak)");
		this.roomID = roomID;
	}
	
	public void add() {
		mainScreen.add(image);
	}
	
	@Override
	public void onMouseAction(MouseEvent e) {
		int dest = Integer.parseInt(roomID);
		//System.out.print(dest);
		mainScreen.getGamePane().getCurrentLevel().switchRoomto(dest);
	}
}
