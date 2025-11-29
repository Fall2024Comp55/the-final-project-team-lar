import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.*;

public class DescriptionPane extends GraphicsPane{
	public DescriptionPane(MainApplication mainScreen) {
		this.mainScreen = mainScreen;
	}
	
	@Override
	public void showContent() {
		addPicture();
		//addText();
		addBackButton();
	}

	@Override
	public void hideContent() {
		for(GObject item : contents) {
			mainScreen.remove(item);
		}
		contents.clear();
	}
	
	private void addPicture(){
		GImage startImage = new GImage("menu.png", 200, 100);
		startImage.scale(0.25,0.25);
		startImage.setLocation((mainScreen.getWidth() - startImage.getWidth())/ 2, (mainScreen.getHeight() - startImage.getHeight()-25));
		//*/
		contents.add(startImage);
		mainScreen.add(startImage);
		
		GRect g = new GRect(250, 250 , 100, 100);
		Color c = new Color(255, 0, 0, 20); 
		g.setFillColor(c);
		g.setFilled(true);
		mainScreen.add(g);
	}
	
	private void addText() {
		GLabel text = new GLabel("This is an example of a new screen with some description!", 100, 70);
		text.setColor(Color.BLUE);
		text.setFont("DialogInput-PLAIN-24");
		text.setLocation((mainScreen.getWidth() - text.getWidth()) / 2, 70);
		
		contents.add(text);
		mainScreen.add(text);
	}
	
	private void addBackButton() {
		GImage backButton = new GImage("back.jpg", 200, 400);
		backButton.scale(0.3, 0.3);
		backButton.setLocation((mainScreen.getWidth() - backButton.getWidth())/ 2, 400);
		
		contents.add(backButton);
		mainScreen.add(backButton);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (mainScreen.getElementAtLocation(e.getX(), e.getY()) == contents.get(1)) {
			mainScreen.switchToWelcomeScreen();
		}
	}

}
