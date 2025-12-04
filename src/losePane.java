import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class losePane extends GraphicsPane {
	
	public losePane(MainApplication mainScreen) {
		this.mainScreen = mainScreen;
	}
	
	@Override
	public void showContent() {
		this.addPicture();
		this.addButton("NextLevel.png", 0.07,0.07,300); //change this one only 
		this.addButton("MainMenu.png", 0.07,0.07,400);
	}

	@Override
	public void hideContent() {
		for(GObject item : contents) {
			mainScreen.remove(item);
		}
		contents.clear();
	}
	
	private void addPicture(){
		GImage startImage = new GImage("lose.png", 200, 100);
		startImage.scale(0.25,0.25);
		startImage.setLocation((mainScreen.getWidth() - startImage.getWidth())/ 2, (mainScreen.getHeight() - startImage.getHeight()-25));
		contents.add(startImage);
		mainScreen.add(startImage);
	}
	
	
	private void addButton(String name,double scaleX,double scaleY, double Yposition) {
		GImage button = new GImage(name, 0, 0);
		button.scale(scaleX, scaleY);
		button.setLocation((mainScreen.getWidth() - button.getWidth())/ 2, Yposition);
		contents.add(button);
		mainScreen.add(button);

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (mainScreen.getElementAtLocation(e.getX(), e.getY()) == contents.get(4)) {
			//mainScreen.switchToRoom();
			mainScreen.switchToWelcomeScreen();
		}
		if (mainScreen.getElementAtLocation(e.getX(), e.getY()) == contents.get(3)) {
			//mainScreen.switchToRoom();
			mainScreen.startGame();
			
			if (mainScreen.getGamePane().getCurrentLevel().getLevelNum() == 1) {
				mainScreen.getGamePane().startNewLevel(2);
			}
			else if (mainScreen.getGamePane().getCurrentLevel().getLevelNum() == 2) {
				mainScreen.getGamePane().startNewLevel(3);
			}
			else {
				mainScreen.getGamePane().startNewLevel(1);
			}
		}
	}
}

