import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class Flashlight extends JPanel implements ActionListener{

	private double battery;
	private double drainRate; //amount drained throughout level
	private double shineRate; //amount drained when light is shone
	private int lightDiameter;
	private boolean isOn;
	private double rechargeAmount;
	private Timer t = new Timer(1000, this); 
	private Color shinee = new Color(74, 118, 249, 0.08f); //flashlight shine color 
	private Color defaultBlue = new Color(74, 118, 249, 0.2f); //default shine color
	private Graphics2D cursorLight;
	
	@Override 
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		cursorLight = (Graphics2D) g;
		cursorLight.setColor(defaultBlue); //initialize flashlight color
		cursorLight.fillOval(100, 100, lightDiameter, lightDiameter); //x, y, width, height
		
	}
	
	
	public Flashlight(double b, double d) {
		battery = b;
		drainRate = d;
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
		}
		else {
			return false;
		}
	}
	
	public void recharge() {
		battery = battery + rechargeAmount; //rechargeAmount not defined
	}
	
	
	public void MouseClicked(MouseEvent e) { //this should be handled by Screen delegate
		this.shine();
	}
	
	public void actionPerformed(ActionEvent e) {
		battery = battery - drainRate;
		if(battery == 0.0) {
			t.stop();
		}
	}
}
