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
	//private MainApplication mainScreen; //unnecessary as GameObject now has mainScreen
	
	
	
	public Distraction(MainApplication mainScreen, DistractionType type) {
		super(mainScreen,0,0,"media/missingNo.png","");
		initialX = 0;
		initialY = 0;
		currentX = 0;
		currentY = 0;
		this.type = type;
		String DType = type.toString();
		
		if(DType == "fly"){
			setImagePath("media/fly.png");
				//setSound??
			}
		
		if(DType == "fake_monster"){
			setImagePath("media/fake_monster.png");
			}
		
		if(DType == "moving_shadow"){
			setImagePath("media/moving_shadow.png");
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
			
			if(DType == "creak_sound"){
				setSoundName("creak_sound");
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
		//animation();
	}
		
	public void setSoundName(String name) {
		soundName = name;
	}
	
	public void reset() {
		this.triggered = false;
		this.setPosition(initialX, initialY);	
		}
	
	public void actionPerformed(ActionEvent e){
		if(triggered == true)
			animation();
		}
	
	public void onMouseAction(MouseEvent e) {
		triggerEffect();
		//System.out.println("onMouseAction_Triggered");
		}
		
	private void animation() {
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
//instead of doubles this needs to move in screen distance.
//it also needs to move relative to the object.
	/*public void moveToPoint(double newX, double newY, int frames){
	int i = 0;
		if(i <frames)
		{
			if(this.getX()!=newX || this.getY()!=newY) {
				double subtractX = (newX - this.getX())/frames;
				double subtractY = (newY - this.getY())/frames;
					
				this.setPosition(subtractX,subtractY);
			}
			i = i++;
		}
		else {
			
		}
}
*/
	
	public void moveToPoint(double addX, double addY, int frames){
	double moveX = addX/frames;
	double moveY = addY/frames;
	double newX = initialX + addX;
	double newY = initialY + addY;
	System.out.println("Goal Positions: (" + newX +',' + newY + ')');

		//if(this.getX()!=initialX+addX || this.getY()!=initialY+addY)
		if(!isEqualTo(this.getX(),currentX+addX)||!isEqualTo(this.getY(),currentY+addY))
		{
			/*if(this.getX()!=initialX+addX && this.getY()==initialY+addY)
			{
				this.setPosition(this.getX() + moveX,this.getY());
			}
			if(this.getX()==initialX+addX && this.getY()!=initialY+addY)
			{
				this.setPosition(this.getX(),this.getY()+moveY);
			}*/
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


