import acm.graphics.GObject;
import acm.graphics.*;
import acm.program.*;
//flashlight needs to follow mouse
//Distraction needs to show up in room
//monster needs to work and show up in only one of the rooms and 
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MainApplication extends GraphicsProgram{
	//Settings
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	
	//List of all the full screen panes
	private WelcomePane welcomePane;
	private DescriptionPane descriptionPane;
	private GraphicsPane currentScreen;
	private Room room;
	private GraphicsGame gamePane;

	public MainApplication() {
		super();
	}
	
	protected void setupInteractions() {
		requestFocus();
		addKeyListeners();
		addMouseListeners();
	}
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	public void run() {
		System.out.println("Lets' Begin!");
		setupInteractions();
		
		//Initialize all Panes
		welcomePane = new WelcomePane(this); 
		//the opening menu on mouse clicked switches to gamePane (GraphicsGame)
		//graphicsGame starts level 1
		descriptionPane = new DescriptionPane(this);
		gamePane = new GraphicsGame(this);
		
		room = new Room(this, "0"); 

		//TheDefaultPane
		switchToScreen(welcomePane);
	}
	
	public static void main(String[] args) {
		new MainApplication().start();

	}
	
	public void switchToRoom(Room room) {
		switchToScreen(room);
	}
	
	public void startGame() {
	    switchToScreen(gamePane);
	}
	
	public void switchToDescriptionScreen() {
		switchToScreen(descriptionPane);
	}
	
	public void switchToWelcomeScreen() {
		switchToScreen(welcomePane);
	}
	
	protected void switchToScreen(GraphicsPane newScreen) {
		if(currentScreen != null) {
			currentScreen.hideContent();
		}
		newScreen.showContent();
		currentScreen = newScreen;
	}
	
	public GObject getElementAtLocation(double x, double y) {
		return getElementAt(x, y);
	}
	
	public GraphicsGame getGamePane() {
		return gamePane;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(currentScreen != null) {
			currentScreen.mousePressed(e);
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(currentScreen != null) {
			currentScreen.mouseReleased(e);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(currentScreen != null) {
			currentScreen.mouseClicked(e);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if(currentScreen != null) {
			currentScreen.mouseDragged(e);
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if (gamePane != null && gamePane.getCurrentLevel() != null && gamePane.getCurrentLevel().getFlashlight() != null) {

		    gamePane.getCurrentLevel().getFlashlight().MouseMoved(e);
		}
		
	    if (currentScreen != null) {
	        currentScreen.mouseMoved(e);
	    }
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(currentScreen != null) {
			currentScreen.keyPressed(e);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(currentScreen != null) {
			currentScreen.keyReleased(e);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		if(currentScreen != null) {
			currentScreen.keyTyped(e);
		}
	}

}
