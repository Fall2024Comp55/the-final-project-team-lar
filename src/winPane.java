import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.*;


public class winPane extends GraphicsPane {
	public winPane(MainApplication mainScreen) {
		this.mainScreen = mainScreen;
	}
	
	@Override
	public void showContent() {
		this.addPicture();
		this.addText();
		this.addButton("NextLevel.png", 0.07,0.07,300);
		this.addButton("more.jpeg", 0.3,0.3,400);
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
	}
	
	private void addText() {
		GLabel text = new GLabel("You found El Cucuy!", 100, 70);
		text.setColor(Color.RED);
		text.setFont("DialogInput-PLAIN-30");
		text.setLocation((mainScreen.getWidth() - text.getWidth()) / 2, 260);
		
		contents.add(text);
		mainScreen.add(text);
		
		int lvlNum = mainScreen.getGamePane().getCurrentLevel().getLevelNum();
		GLabel text2 = new GLabel("Password for Level " + lvlNum + ": " + mainScreen.getGamePane().getLevelGate().getPassword(lvlNum - 1), 100, 70);
		text2.setColor(Color.RED);
		text2.setFont("DialogInput-PLAIN-30");
		text2.setLocation((mainScreen.getWidth() - text2.getWidth()) / 2, 290);
		
		contents.add(text2);
		mainScreen.add(text2);
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
