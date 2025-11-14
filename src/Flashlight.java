import javax.swing.*;
import acm.graphics.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Color;


public class Flashlight extends GraphicsPane implements ActionListener{ 

	private double battery;
	private double drainRate; //amount drained throughout level
	private double shineRate; //amount drained when light is shone
	private int lightDiameter;
	private boolean isOn;
	private double rechargeAmount;
	private Timer t = new Timer(1000, this); 
	private Color shinee = new Color(74, 118, 249, 0.08f); //flashlight shine color 
	private Color defaultBlue = new Color(74, 118, 249, 0.2f); //default color
	private GOval cursorLight = new GOval(0, 0, lightDiameter, lightDiameter);
	private GRect batteryMeter = new GRect(0,0, 200, 150);
	//screen.add(batteryMeter);
	
	public Flashlight(MainApplication mainScreen, double b, double d) {
		this.mainScreen = mainScreen;
		battery = b;
		drainRate = d;
		
		cursorLight.setFillColor(defaultBlue); 
		cursorLight.setFilled(true);
	}
	
	public void add() {
		mainScreen.add(cursorLight);
	}
	
	public void remove() {
		mainScreen.remove(cursorLight);
	}
	
	public double getBattery() {
		return battery;
	}
	
	public void toggle(boolean on) {
		if(on) {
			isOn = true;
			this.shine(); 
			isOn = false; 
		}
	}
	
	public void shine() { 
		
		cursorLight.setColor(shinee);
		cursorLight.setSize(200,200); 
		isOn = true;
		this.drain();
	}
	
	public void drain() {
		if(isOn) {
			battery = battery - shineRate; //drainRate not defined
		}
	}
	
	public boolean isEmpty() {
		if(battery == 0.0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void recharge() {
		battery = battery + rechargeAmount; //rechargeAmount not defined
	}
	
	 
	public void MouseClicked(MouseEvent e) { 
		this.shine();
	}
	
	public void MouseMoved(MouseEvent e) {
		cursorLight.setLocation(e.getX(), e.getY());
	}
	
	//drains battery health slowly
	public void actionPerformed(ActionEvent e) {
		battery = battery - drainRate;
		if(battery == 0.0) {
			t.stop();
		}
	}
}
