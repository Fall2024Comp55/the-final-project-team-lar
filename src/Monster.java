import java.awt.event.MouseEvent;

public class Monster extends GameObject{
	
	private boolean isRevealed;
	private double scareIntensity;
	private int initialMonsterRoom;
	private double initialX;
	private double initialY;
	private int monsterRoom;
	//level levelDelegate
	//GraphicsGame screenDelegate

	public Monster(double x, double y,double scareIntensity, int monsterRoom)
	{
		super(x,y,"monster.jpeg","insertSoundName");
		isRevealed = false;
		this.scareIntensity = scareIntensity;
		setPosition(x,y);
		initialMonsterRoom = monsterRoom;
		initialX = x;
		initialY = y;
		this.monsterRoom = monsterRoom;
	}
	
	public void reveal() {
		isRevealed = true;
		setImagePath("media/revealedMonster.jpeg");//need to add monster pngs
		playSound();
	}
	
	public void playSound() {
		//eeeee soy confused
	}
	
	public void reset() {
		isRevealed = false;
		setImagePath("monster.jpeg");
		monsterRoom = initialMonsterRoom;
		setPosition(initialX,initialY);
	}
	
	public void isCaught(double x, double y) {
		if(this.getX()==x && this.getY() == y)
		{
			reveal();
		}
	}
	
	public void onMouseAction(MouseEvent e) {
		isCaught(e.getX(),e.getY());
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
