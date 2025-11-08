import java.awt.event.MouseEvent;

public class Monster extends GameObject{
	
	private boolean isRevealed;
	private double scareIntensity;
	private int initialMonsterRoom;
	private int monsterRoom;
	//level levelDelegate
	//GraphicsGame screenDelegate

	public Monster(double x, double y,double scareIntensity, int monsterRoom)
	{
		super(x,y,"monster.png","insertSoundName");
		isRevealed = false;
		this.scareIntensity = scareIntensity;
		initialMonsterRoom = monsterRoom;
		this.monsterRoom = monsterRoom;
	}
	
	public void reveal() {
		isRevealed = true;
		setImagePath("media/revealedMonster.png");//need to add monster pngs
	}
	
	public void playSound() {
		//eeeee soy confused
	}
	
	public void reset() {
		isRevealed = false;
		monsterRoom = initialMonsterRoom;
		
	}
	
	public void isCaught(double x, double y) {
		
	}
	
	public void onMouseAction(MouseEvent e) {
		
	}
}
