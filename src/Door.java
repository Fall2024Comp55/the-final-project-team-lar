import java.awt.event.MouseEvent;

import acm.graphics.GImage;

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
		super(mainScreen,0,0,"door.png","");
		this.roomID = roomID;
	}
	
	public Door(MainApplication mainScreen, double x, double y, String roomID)
	{
		super(mainScreen,x,y,"door.png","door-creak.au");
		this.image.scale(0.3);
		this.roomID = roomID;
	}
	
	public void add() {
		mainScreen.add(image);
	}

	public void remove() {
		mainScreen.remove(image);
	}
	
	@Override
	public void onMouseAction(MouseEvent e) {
		if (mainScreen == null) {
	        System.err.println("Door has no reference to MainApplication.");
	        return;
	    }
		System.out.print("Switching room");
	    // Convert string room ID to integer
		
	    int destRoom;
	    try {
	        destRoom = Integer.parseInt(roomID);
	    } catch (NumberFormatException ex) {
	        System.err.println("Invalid room ID in door: " + roomID);
	        return;
	    }

	    // Get the GraphicsGame pane 
	    GraphicsGame gamePane = mainScreen.getGamePane();
	    if (gamePane == null) {
	        System.err.println("MainApplication returned null for gamePane.");
	        return;
	    }

	    // Get the Level object
	    Level level = gamePane.getCurrentLevel();
	    if (level == null) {
	        System.err.println("GraphicsGame does not have an active level.");
	        return;
	    }

	    // Switch the room (updates Level's current room)
	    level.switchRoomto(destRoom);

	    // Switch the GraphicsPane displayed on screen
	    Room nextRoom = level.getCurrentRoom();
	    if (nextRoom != null) {
	    	gamePane.onDoorOpened();
	    	System.out.println("playing sound");
	    	mainScreen.switchToScreen(nextRoom);
	        
	    } else {
	        System.err.println("Room switch failed: no room exists for ID " + destRoom);
	    }
	}
}
