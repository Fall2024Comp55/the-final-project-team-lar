import javax.swing.*;
import acm.graphics.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Color;


public class Flashlight extends GraphicsPane implements ActionListener{ 

	private double battery;
	private double drainRate = 2.0; //amount drained throughout level
	private double shineRate = 10.0; //amount drained when light is shone
	private int lightDiameter = 100;
	private boolean isOn;
	private double rechargeAmount;
	private Timer t = new Timer(10000, this); 
	private Color shinee = new Color(74, 118, 249, 150); //flashlight shine color 
	private Color defaultBlue = new Color(74, 118, 249, 50); //default color
	private Color defaultYellow = new Color(249, 249, 74, 50); //default color
	private GOval cursorLight = new GOval(0, 0, lightDiameter, lightDiameter);
	private GRect batteryMeter = new GRect(0,0, 200, 150);
	ScreenDelegate delegate;
	//screen.add(batteryMeter);
	
	public Flashlight(MainApplication mainScreen, double b, double d) {
		this.mainScreen = mainScreen;
		battery = b;
		drainRate = d;
		
		cursorLight.setSize(lightDiameter,lightDiameter);
		cursorLight.setFillColor(defaultYellow); 
		cursorLight.setFilled(true);
		cursorLight.setLineWidth(0);
		//t.start();
	}
	
	public void add() {
		cursorLight.setLocation(400, 300);
		mainScreen.add(cursorLight);
		System.out.println("adding flashlight");
	}
	
	public void add(double x, double y) {
		cursorLight.setLocation(x, y);
		mainScreen.add(cursorLight);
		System.out.println("adding flashlight");
	}
	
	public void remove() {
		mainScreen.remove(cursorLight);
		System.out.println("removing flashlight");
	}
	
	public double getBattery() {
		return battery;
	}
	
	public void startTimer() {
	    t.start();
	}
	
	public void toggle(boolean on) {
		isOn = true;
		if(on) {
			//this.shine(); 
		}
	}
	
	public void shine() { 
		double x = cursorLight.getX();
		double y = cursorLight.getY();
		cursorLight.setColor(shinee);
		cursorLight.setSize(200,200); 
		isOn = true;
		this.drain();
		this.add(x,y);
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
	
	public GOval getCursorLight() {
		return cursorLight;
	}
	
	public void setDelegate(ScreenDelegate d) {
		this.delegate = d;
	}
	
	public void MouseClicked (MouseEvent e) { 
		this.shine();
	}
	
	public void MouseMoved(MouseEvent e) {
		cursorLight.setLocation(e.getX() - cursorLight.getWidth()/2, e.getY() - cursorLight.getHeight()/2);
	}
	
	//drains battery health slowly
	@Override
	public void actionPerformed(ActionEvent e) {
		battery = battery - drainRate;
		delegate.onBatteryLow(battery);
		System.out.println("battery health is " + battery); 
		if(battery <= 0.0) {
			battery = 0;
			t.stop();
			
			
		}
	}
}
