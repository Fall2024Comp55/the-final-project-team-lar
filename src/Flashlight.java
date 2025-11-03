import acm.graphics.*;
import acm.program.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color.*; 

//MORE EDITS NEEDED

public class Flashlight implements ActionListener {

	private double battery;
	private double drainRate;
	private double shineRate;
	private double lightDiameter;
	private boolean isOn;
	private double rechargeAmount;
	private Timer t = new Timer(1000, this);  
	
	//add both to the screen later
	GOval cursorLight = new GOval(0, 0, lightDiameter, lightDiameter); //0,0 is just an initialization for x and y
	
	private GRect batteryMeter = new GRect(100, 500, 250, 150); //make changes here once we can print to screen
	
	
	public Flashlight(double b, double d) {
		battery = b;
		drainRate = d;
	}
	
	public double getBattery() {
		return battery;
	}
	
	public void drain() {
		if(isOn) {
			battery = battery - (drainRate * 2); //drainRate not defined
		}
	}
	
	public void shine() { //how will we implement shine?
		//Color shinee = new Color(74, 118, 249, 100);
		//cursorLight.setFillColor(shinee); 
		cursorLight.setFilled(true); 
		//what the frick	
	}
	
	public boolean isEmpty() {
		if(battery == 0.0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void recharge() {
		battery = battery + rechargeAmount; //rechargeAmount not defined
	}
	
	public void toggle(boolean on) {
		if(on) {
			this.shine(); 
			isOn = true;
			this.drain();
			}
	}
	
	public void MouseClicked(MouseEvent e) {
		this.shine();
	}
	
	public void actionPerformed(ActionEvent e) {
		battery = battery - drainRate;
		if(battery == 0) {
			t.stop();
		}
	}
}
