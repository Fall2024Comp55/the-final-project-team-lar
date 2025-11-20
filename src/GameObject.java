import acm.graphics.*;
import java.awt.event.MouseEvent;

public abstract class GameObject extends GraphicsPane {

	protected MainApplication mainScreen;
	protected double x;
    protected double y;
    protected String imagePath;    // The image file name or resource ID
    protected boolean isVisible;
    protected String soundName;    // The sound key to use with SoundManager
    protected GImage image;
    
    public GameObject(MainApplication mainScreen, double x, double y, String imagePath, String soundName) {
    	this.mainScreen = mainScreen;
        this.x = x;
        this.y = y;
        this.imagePath = imagePath;
        this.soundName = soundName;
        this.isVisible = true;

        // The image will be loaded and drawn by GraphicsGame
        this.image = new GImage(imagePath, x, y);
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public boolean pointsIn(double mX, double mY) {
    	if (x <= mX && mX <= x + image.getWidth() && y <= mY && mY <= y + image.getHeight()) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
        if (image != null) {
            image.setLocation(x, y);
        }
    }

    public boolean isVisible() {
        return isVisible;
    }
    
    public String getImagePath() {
        return imagePath;
    }
    
    public void setImagePath(String imagePath)
    {
    	this.imagePath = imagePath;
    	//this.image = new GImage(imagePath, x, y);
    }

    public String getSoundName() {
        return soundName;
    }
    
    public abstract void onMouseAction(MouseEvent e);
    
    public void update() {
        // Optional — subclasses like Monster or Distraction may override
    }
	
}