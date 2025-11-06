import java.awt.event.MouseEvent;
import java.awt.event.*;
import javax.swing.*;

public class Distraction extends GameObject implements ActionListener{
DistractionType type;
boolean triggered = false;
//AudioClip effectSound;
Timer distractionTimer = new Timer(12,this);
//level levelDelegate



public Distraction(DistractionType type) {
	super(0,0,"media/missingNo.png","");
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
	/*if(DType == "whisper")
	{
		setImagePath("whisper.png");
	}
	if(DType == "creak_sound")
	{
		setImagePath("creak_sound.png");
	}*/
}

public void triggerEffect()
{
	if(this.triggered = true)
	{
		animation();
	}
	
}
public void reset() {
	this.triggered = false;
}
public void actionPerformed(ActionEvent e){
	
}
public void onMouseAction(MouseEvent e) {
	triggerEffect();
}

private void animation() {
//different animations
//idk some circular shape
	
	if(type == DistractionType.FLY) {
		
		
	}
	if(type == DistractionType.FAKE_MONSTER){
		
		
	}
	if(type == DistractionType.MOVING_SHADOW){ 
		
		
	}
	}

public void moveToPoint(double newX, double newY, int frames){
	
	if(this.image.getX()!=newX || this.image.getY()!=newY)
	{
	double subtractX = (newX - image.getX())/frames;
	double subtractY = (newY - image.getY())/frames;
	
	this.image.move(subtractX,subtractY);
	}
}

}

