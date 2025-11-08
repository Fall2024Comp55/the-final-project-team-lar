import java.awt.event.MouseEvent;

public class Monster extends GameObject{
	
	private boolean isRevealed;
	private double scareIntensity;
	private int monsterRoom;
	//level levelDelegate
	//GraphicsGame screenDelegate

	public Monster(double x, double y,double scareIntensity)
	{
		super(0,0,"monster.png","");
		isRevealed = false;
		this.scareIntensity = scareIntensity;
		
	}
	
	public void reveal() {
		isRevealed = true;
	}
	public void onMouseAction(MouseEvent e) {
		
	}
}
