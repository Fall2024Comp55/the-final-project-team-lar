// Java program to create a blank text 
// field of definite number of columns.

import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;

class LevelGate extends JFrame implements ActionListener,KeyListener {

    static JTextField text;
    static JFrame frame;
    static JButton button;
    static JLabel label;
	private ArrayList<String> passwords = new ArrayList<String>();
	private ArrayList<GObject> contents = new ArrayList<GObject>();
	private MainApplication mainScreen;
	private GraphicsGame delegate;
	//Juice ITC

    LevelGate(MainApplication mainScreen) {
    	this.mainScreen = mainScreen;
    	delegate = mainScreen.getGamePane();
    }
    
    public void setUpLevelGate() {
    	frame = new JFrame("textfield");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label = new JLabel("Enter Level Password");
        button = new JButton("submit");

        button.addActionListener(this);

        text = new JTextField(16);
        text.addKeyListener(this);
        
        JPanel panel = new JPanel();
        panel.add(text);
        panel.add(button);
        panel.add(label);
        frame.add(panel);

        frame.setSize(300, 300);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("submit")) {
            handleInput();
        }
        
    }
    
    public void handleInput() {
    	int index = 0;
    	for(String password:passwords)
    	{
    		if(text.getText().equals(password))
            {
    			int lvlNum = index + 1;
            	System.out.println("You want lvl" + lvlNum);
                text.setText("");
                mainScreen.getGamePane().startNewLevel(lvlNum);
                hideContent();
            }
    		index++;
    	}
    	
       text.setText("");
    }
    
    public void addPassword(String password) {
    	passwords.add(password);
    }

	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println("You released key character:" + e.getKeyChar());
		if(e.getKeyCode() == 10)
		{
			handleInput();
		}
	}
    
	private void addBackgroundImage(){
		GImage startImage = new GImage("menu.png", 200, 100);
		startImage.scale(0.25,0.25);
		startImage.setLocation((mainScreen.getWidth() - startImage.getWidth())/ 2, (mainScreen.getHeight() - startImage.getHeight()-25));
		
		contents.add(startImage);
		mainScreen.add(startImage);
		
		GRect g = new GRect(250, 250 , 100, 100);
		Color c = new Color(255, 0, 0, 20); 
		g.setFillColor(c);
		g.setFilled(true);
		mainScreen.add(g);
	}
	
	private void addButton(String name,double scaleX,double scaleY,double positionX,double positionY) {
		GImage button = new GImage(name, 0, 0);
		button.scale(scaleX, scaleY);
		button.setLocation(positionX,positionY);
		contents.add(button);
		mainScreen.add(button);

	}
	
	public void mouseClicked(MouseEvent e) {
		if (mainScreen.getElementAtLocation(e.getX(), e.getY()) == contents.get(1)) {
			mainScreen.switchToWelcomeScreen();
		}
		/*if (mainScreen.getElementAtLocation(e.getX(), e.getY()) == contents.get(2)) {
			//mainScreen.switchToRoom();
			mainScreen.startGame();
		}*/
	}
	
	public void showContent() {
		addBackgroundImage();//0
		addButton("back.jpg", 0.3, 0.3, 100.0, 100.0);//1
		//addButton("more.jpeg", 0.3, 0.3, ((mainScreen.getWidth() - button.getWidth())/ 2),300);//2
        frame.setVisible(true);

	}

	public void hideContent() {
		for(GObject item : contents) {
			mainScreen.remove(item);
		}
		frame.setVisible(false);
		contents.clear();
	}
	
	public void setPasswords(ArrayList<String> passes)
	{
		passwords = passes;
	}
}