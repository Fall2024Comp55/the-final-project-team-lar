import java.awt.event.MouseEvent;

public class Monster extends GameObject {
	
	private boolean isRevealed;
	private double scareIntensity;
	private int initialMonsterRoom;
	private double initialX;
	private double initialY;
	private int monsterRoom;
	private GraphicsGame delegate = mainScreen.getGamePane();
	//level levelDelegate

	public Monster(MainApplication mainScreen, double x, double y,double scareIntensity, int monsterRoom)
	{
		super(mainScreen,x,y,"monster.jpeg","insertSoundName");
		this.image.scale(0.2);
		isRevealed = false;
		this.scareIntensity = scareIntensity;
		setPosition(x,y);
		initialMonsterRoom = monsterRoom;
		initialX = x;
		initialY = y;
		this.monsterRoom = monsterRoom;
	}
	
	public void add() {
		mainScreen.add(image);
	}

	public void remove() {
		mainScreen.remove(image);
	}
	
	public void reveal() {
		isRevealed = true;
		System.out.println("Monster REVEALED!!");
		setImagePath("media/revealedMonster.jpeg");//need to add monster pngs
		image.setImage("media/revealedMonster.jpeg");
		playSound();
	}
	
	public void playSound() {
		//
	}
	
	public void reset() {
		isRevealed = false;
		setImagePath("monster.jpeg");
		monsterRoom = initialMonsterRoom;
		setPosition(initialX,initialY);
	}
	
	/*public void isCaught(double x, double y) {
		if(this.getX()==x && this.getY() == y)
		{
			reveal();
		}
	}*/
	
	public void isCaught() {
		System.out.println("isCaught");
		reveal();
		delegate.onMonsterRevealed();
	}
	
	@Override
	public void onMouseAction(MouseEvent e) {
		//isCaught(e.getX(),e.getY());
		System.out.println("onMouseAction");
		isCaught();
	}
	
	public void monsterMovement(int numRooms,int playerRoomID,double screenWidth,double screenHeight){
		//randomizes room
		//randomizes x and y
		int randRoom = (int)(Math.random()* numRooms + 1);
		double randX = (double)(Math.random()* screenWidth+1);
		double randY = (double)(Math.random()* screenHeight+ 1);
		
		monsterRoom = randRoom;
		setPosition(randX,randY);
		if(monsterRoom == playerRoomID)
		{
			playSound();
		}
		
	}

	
}
