import java.awt.event.MouseEvent;
import java.awt.Color;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;

public class WelcomePane extends GraphicsPane{
	public WelcomePane(MainApplication mainScreen) {
		this.mainScreen = mainScreen;
	}
	
	@Override
	public void showContent() {
		addPicture();
		//addButton("levels.png", 0.07, 0.07);
		addButton("levels2.png", 0.07, 0.07);
		addDescriptionButton();
		
		
	}

	@Override
	public void hideContent() {
		for(GObject item : contents) {
			mainScreen.remove(item);
		}
		contents.clear();
	}
	
	private void addPicture(){
		/*
		GImage startImage = new GImage("start.png", 200, 100);
		startImage.scale(0.5, 0.5);
		startImage.setLocation((mainScreen.getWidth() - startImage.getWidth())/ 2, 70);
		*/
		
		///*
		GImage startImage = new GImage("menu.png", 200, 100);
		startImage.scale(0.25,0.25);
		startImage.setLocation((mainScreen.getWidth() - startImage.getWidth())/ 2, (mainScreen.getHeight() - startImage.getHeight()-25));
		//*/
		contents.add(startImage);
		mainScreen.add(startImage);
		
	}
	
	private void addDescriptionButton() {
		GImage moreButton = new GImage("play.png", 200, 400);
		moreButton.scale(0.07, 0.07);
		moreButton.setLocation((mainScreen.getWidth() - moreButton.getWidth())/ 2, 400);
		
		contents.add(moreButton);
		mainScreen.add(moreButton);

	}
	
	private void addButton(String name,double scaleX,double scaleY) {
		GImage button = new GImage(name, 0, 0);
		button.scale(scaleX, scaleY);
		button.setLocation((mainScreen.getWidth() - button.getWidth())/ 2, 300);
		contents.add(button);
		mainScreen.add(button);

	}
	
	/*private void addLevelButton() {
		GImage levelButton = new GImage("more.jpeg", 200, 700);
		levelButton.scale(0.3, 0.3);
		levelButton.setLocation((mainScreen.getWidth() - levelButton.getWidth())/ 2, 400);
		
		contents.add(levelButton);
		mainScreen.add(levelButton);

	}*/
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (mainScreen.getElementAtLocation(e.getX(), e.getY()) == contents.get(2)) {
			//mainScreen.switchToRoom();
			mainScreen.startGame();
			System.out.println("Starting Game");
			mainScreen.getGamePane().startNewLevel(1);
			System.out.println("Starting Level 1");
			
		}
		if (mainScreen.getElementAtLocation(e.getX(), e.getY()) == contents.get(1)) {
			//mainScreen.switchToRoom();
			mainScreen.getGamePane().drawLevelGate();
		}
	}

}
