// Java program to create a blank text 
// field of definite number of columns.

/*
 Currently my goal is to get rid of the button so you can just click enter
 */
import java.awt.event.*;
import javax.swing.*;

import acm.graphics.GImage;

class LevelGate extends JFrame implements ActionListener {

    static JTextField text;
    static JFrame frame;
    static JButton button;
    static JLabel label;
    private String password1;
	private GImage BackgroundImage;

    LevelGate() {}

    public static void main(String[] args)
    {
    	
        frame = new JFrame("textfield");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label = new JLabel("Enter Level Password");
        button = new JButton("submit");

        LevelGate textBox = new LevelGate();
        button.addActionListener(textBox);

        text = new JTextField(16);

        text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	label.setText(text.getText()); 
            	 if(text.getText().equals("hi"))
                 {
                 	System.out.println("correct password");
                 }
            	text.setText("");
            }
        });
		
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
            label.setText(text.getText());
            if(text.getText().equals("hi"))
            {
            	System.out.println("correct password");
            }
            text.setText("");

        }
    }
}