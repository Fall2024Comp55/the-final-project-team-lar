// Java program to create a blank text 
// field of definite number of columns.

/*
 Currently my goal is to get rid of the button so you can just click enter
 */
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import acm.graphics.GImage;

class LevelGate extends JFrame implements ActionListener,KeyListener {

    static JTextField text;
    static JFrame frame;
    static JButton button;
    static JLabel label;
    private String password1;
	private GImage BackgroundImage;
	private ArrayList<String> passwords = new ArrayList<String>();

	//Juice ITC

    LevelGate() {
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
        frame.setVisible(true);
    
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("submit")) {
            handleInput();
        }
        
    }
    
    public void handleInput() {
    	for(String password:passwords)
    	{
    		if(text.getText().equals(password))
            {
            	System.out.println("correct password");
                text.setText("");

            }
    	}
    	
       text.setText("");
    }
    
    public void addPassword(String password) {
    	passwords.add(password);
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("You released key character:" + e.getKeyChar());
		if(e.getKeyCode() == 10)
		{
			handleInput();
		}
		
	}
    
    
}