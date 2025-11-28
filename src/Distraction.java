import java.awt.event.MouseEvent;
import java.awt.event.*;
import javax.swing.*;

	public class Distraction extends GameObject implements ActionListener{
	DistractionType type;
	boolean triggered = false;
	String SoundName;
	//AudioClip effectSound;
	Timer distractionTimer = new Timer(500,this);
	//level levelDelegate
	double initialX;
	double initialY;
	double currentX;
	double currentY;
	int animationCounter = 0;
	private GraphicsGame delegate = mainScreen.getGamePane();

	//private MainApplication mainScreen; //unnecessary as GameObject now has mainScreen
	
	
	
	public Distraction(MainApplication mainScreen, DistractionType type) {
		super(mainScreen,0,0,"media/missingNo.png","");
		initialX = x;
		initialY = y;
		currentX = x;
		currentY = y;
		this.type = type;
		String DType = type.toString();
			if(DType == "fly") {
				setImagePath("media/fly.png");
				image.setImage("media/fly.png");
				image.setSize(80,80);
				//setSound??
			}
			
			if(DType == "fake_monster"){
				setImagePath("media/fake_monster.png");
				image.setImage("media/fake_monster.png");
			}
			
			if(DType == "moving_shadow"){
				setImagePath("media/moving_shadow.png");
				image.setImage("media/moving_shadow.png");
			}
			
			if(DType == "whisper"){
				setSoundName("whisper");
			}
			
			if(DType == "creak_sound"){
				setSoundName("creak_sound");
			}
		}
		
		
	public Distraction(MainApplication mainScreen, double x, double y, DistractionType type) {
		super(mainScreen,x,y,"media/missingNo.png","");
		initialX = x;
		initialY = y;
		currentX = x;
		currentY = y;
		this.type = type;
		String DType = type.toString();
			if(DType == "fly") {
				setImagePath("media/fly.png");
				image.setImage("media/fly.png");
				image.setSize(80,80);
				//setSound??
			}
			
			if(DType == "fake_monster"){
				setImagePath("media/fake_monster.png");
				image.setImage("media/fake_monster.png");
			}
			
			if(DType == "moving_shadow"){
				setImagePath("media/moving_shadow.png");
				image.setImage("media/moving_shadow.png");
			}
			
			if(DType == "whisper"){
				setSoundName("whisper");
			}
			
		}
		
	public void add() {
		mainScreen.add(image);
	}
		
	public void remove() {
		mainScreen.remove(image);
	}
	
	public void triggerEffect(){
		triggered = true;
		distractionTimer.start();
		delegate.onDistractionTriggered(type.toString());
		//animation();
	}
		
	public void setSoundName(String name) {
		soundName = name;
	}
	
	public void reset() {
		this.triggered = false;
		this.setPosition(initialX, initialY);	
		this.animationCounter = 0;
		}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("action_performed");
		if(triggered == true)
			animation();
		}
	
	@Override
	public void onMouseAction(MouseEvent e) {
		triggerEffect();
		System.out.println("onMouseAction_Triggered");
		}
		
	private void animation() {
		System.out.println("animating");
		//different animations
		//idk some circular shape
		if(triggered == true) {
			type.toString();
				if(this.type == DistractionType.FLY) {
				//set points
				if(animationCounter == 0)
				{
					moveToPoint(20, 20,3);

				}
				if(animationCounter ==1 )
				{
					moveToPoint(10,-14,3);
				}
				if(animationCounter == 2)
				{
					triggered = false;
				}
				
					
				}
				
				if(type == DistractionType.FAKE_MONSTER){
				
				
				}
				
				if(type == DistractionType.MOVING_SHADOW){ 
				
				
				}
			}
		}

	public void moveToPoint(double addX, double addY, int frames){
	double moveX = addX/frames;
	double moveY = addY/frames;
	double newX = initialX + addX;
	double newY = initialY + addY;
	System.out.println("Goal Positions: (" + newX +',' + newY + ')');

		//if(this.getX()!=initialX+addX || this.getY()!=initialY+addY)
		if(!isEqualTo(this.getX(),currentX+addX)||!isEqualTo(this.getY(),currentY+addY))
		{
			this.setPosition(this.getX()+moveX,this.getY()+moveY);
		}
		else {
			currentX = currentX + addX;
			currentY = currentY + addY;
			animationCounter++;
			System.out.println("Animation Counter: " + animationCounter);
		}
	System.out.println("Current Position: (" + this.getX() + ',' + this.getY() + ')');
	}
	
	private boolean isEqualTo (double a, double b) {
	    if(Math.abs(a - b) < 0.001)
	    {
	    	return true;
	    }
	    return false;
	}
	//System.out.println("moving to point");
	
	
}


