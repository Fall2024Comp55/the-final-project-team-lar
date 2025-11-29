import java.awt.Color;
import acm.graphics.*;


public class winPane extends GraphicsPane {
	public winPane(MainApplication mainScreen) {
		this.mainScreen = mainScreen;
	}
	
	@Override
	public void showContent() {
		this.addPicture();
		this.addText();
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
		text.setColor(Color.GREEN);
		text.setFont("DialogInput-PLAIN-24");
		text.setLocation((mainScreen.getWidth() - text.getWidth()) / 2, 70);
		
		contents.add(text);
		mainScreen.add(text);
	}
}
