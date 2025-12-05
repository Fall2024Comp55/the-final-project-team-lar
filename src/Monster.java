import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

public class Monster extends GameObject implements ActionListener {
	
	private boolean isRevealed;
	private double scareIntensity;
	private int initialMonsterRoom;
	Timer monsterTimer = new Timer(500,this);
	private double initialX;
	private double initialY;
	private int monsterRoom;
	private GraphicsGame delegate = mainScreen.getGamePane();
	private double screenHeight;
	private double screenWidth;
	Level levelDelegate;

	public Monster(MainApplication mainScreen, double x, double y,double scareIntensity, int monsterRoom)
	{
		super(mainScreen,x,y,"new_monster2.png","insertSoundName");
		this.image.scale(0.1);
		isRevealed = false;
		this.scareIntensity = scareIntensity;
		setPosition(x,y);
		initialMonsterRoom = monsterRoom;
		initialX = x;
		initialY = y;
		this.monsterRoom = monsterRoom;
		this.levelDelegate = mainScreen.getGamePane().getCurrentLevel();
		this.screenHeight = levelDelegate.getScreenHeight();
		this.screenWidth = levelDelegate.getScreenWidth();
	}
	
	public void add() {
		mainScreen.add(image);
		monsterTimer.start();
	}

	public void remove() {
		mainScreen.remove(image);
	}
	
	public void reveal() {
		isRevealed = true;
		//System.out.println("Monster REVEALED!!");
		setImagePath("Media/new_Monster_revealed5.png");//need to add monster pngs
		this.image.setImage("new_Monster_revealed5.png");
		new javax.swing.Timer(1000, ev -> {
		       this.image.setImage("Media/new_monster_revealed5.png");
		    }).start();
		playSound();
		System.out.println("Monster REVEALED!!");
	}
	
	public void playSound() {
		//
	}
	
	public void reset() {
		isRevealed = false;
		setImagePath("Media/new_monster2.png");
		monsterRoom = initialMonsterRoom;
		setPosition(initialX,initialY);
	}
	
	
	public void isCaught() {
		System.out.println("isCaught");
		reveal();
		//delegate.onMonsterRevealed();
	}
	
	@Override
	public void onMouseAction(MouseEvent e) {
		System.out.println("onMouseAction");
		isCaught();
		javax.swing.Timer t = new javax.swing.Timer(1000, evt -> {
		       delegate.onMonsterRevealed();
		    });
			t.setRepeats(false);
			t.start();
		
	}
	
	public void monsterMovement(int numRooms,int playerRoomID,double screenWidth,double screenHeight){
		//randomizes room
		//randomizes x and y
		System.out.println("the monster is shmovin");
		int randRoom = (int)(Math.random()* numRooms + 1);
		double randX = (double)(Math.random()* screenWidth+1);
		double randY = (double)(Math.random()* screenHeight+ 1);
		
		monsterRoom = randRoom;
		levelDelegate.switchRoomto(randRoom);
		setPosition(randX,randY);
		if(monsterRoom == playerRoomID)
		{
			playSound();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("monsterActionPerformed");
		monsterMovement(levelDelegate.getRooms().size(),levelDelegate.getIndex(levelDelegate.getCurrentRoom()),screenWidth,screenHeight);
	}

	
}
