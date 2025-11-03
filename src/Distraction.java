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
	super(0,0,"missingNo.png","");
	String DType = type.toString();
	if(DType == "fly")
	{
		setImagePath("fly.png");
		//setSound??
	}
	if(DType == "fake_monster")
	{
		setImagePath("fake_monster.png");
	}
	if(DType == "moving_shadow")
	{
		setImagePath("moving_shadow.png");
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
	String DType = type.toString();
	if(DType == "fly") {
		
	}
	if(DType == "fake_monster"){
		
		
	}
	if(DType == "moving_shadow") {
		
		
	}
	}
}
