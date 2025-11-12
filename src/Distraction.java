import java.awt.event.MouseEvent;
import java.awt.event.*;
import javax.swing.*;

public class Distraction extends GameObject implements ActionListener{
DistractionType type;
boolean triggered = false;
String SoundName;
//AudioClip effectSound;
Timer distractionTimer = new Timer(12,this);
//level levelDelegate
double initialX;
double initialY;


public Distraction(DistractionType type) {
	super(0,0,"media/missingNo.png","");
	initialX = 0;
	initialY = 0;
	String DType = type.toString();
	if(DType == "fly")
	{
		setImagePath("media/fly.png");
		//setSound??
	}
	if(DType == "fake_monster")
	{
		setImagePath("media/fake_monster.png");
	}
	if(DType == "moving_shadow")
	{
		setImagePath("media/moving_shadow.png");
	}
	if(DType == "whisper")
	{
		setSoundName("whisper");
	}
	if(DType == "creak_sound")
	{
		setSoundName("creak_sound");
	}
}


public Distraction(double x, double y, DistractionType type) {
	super(x,y,"media/missingNo.png","");
	initialX = x;
	initialY = y;
	String DType = type.toString();
	if(DType == "fly")
	{
		setImagePath("media/fly.png");
		//setSound??
	}
	if(DType == "fake_monster")
	{
		setImagePath("media/fake_monster.png");
	}
	if(DType == "moving_shadow")
	{
		setImagePath("media/moving_shadow.png");
	}
	if(DType == "whisper")
	{
		setSoundName("whisper");
	}
	if(DType == "creak_sound")
	{
		setSoundName("creak_sound");
	}
}


public void triggerEffect()
{
	triggered = true;
	
}

public void setSoundName(String name) {
	soundName = name;
}
public void reset() {
	this.triggered = false;
	this.setPosition(initialX, initialY);
	
}
public void actionPerformed(ActionEvent e){
	animation();
}
public void onMouseAction(MouseEvent e) {
	triggerEffect();
}

private void animation() {
//different animations
//idk some circular shape
	if(triggered == true) {
		if(type == DistractionType.FLY) {
		//set points
		
		}
		if(type == DistractionType.FAKE_MONSTER){
		
		
		}
		if(type == DistractionType.MOVING_SHADOW){ 
		
		
		}
	}
}

public void moveToPoint(double newX, double newY, int frames){
	
	if(this.getX()!=newX || this.getY()!=newY)
	{
	double subtractX = (newX - this.getX())/frames;
	double subtractY = (newY - this.getY())/frames;
	
	this.setPosition(subtractX,subtractY);
	}
}

}

