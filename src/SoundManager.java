import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

// For future reference!
// Only supported file formats for ACM SoundClip are WAV, AU, or AIFF
// Some WAV files have encoding compatibility issues with SoundClip


public class SoundManager {
	
	private static SoundManager instance; // private singleton instance
	private HashMap<String, Clip> sounds; // sound storage

	private float globalVolume;
    private boolean isMuted;
    
    // Default value for volume (range 0.0 - 1.0)
    private static final float DEFAULT_VOLUME = 0.8f;
	
    // Private Constructor
	private SoundManager() {
		sounds = new HashMap<>();
		globalVolume = DEFAULT_VOLUME;
		isMuted = false;
	}
	
	// public access
	public static synchronized SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }
	
	// Loads sounds from Media folder with a String as a key
	public void loadSound(String name, String filename) {
		if (sounds.containsKey(name)) return; // already loaded
		 try {
	            File soundFile = new File(filename);
	            if (!soundFile.exists()) {
	                System.err.println("Sound file not found: " + filename);
	                return;
	            }

	            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
	            Clip clip = AudioSystem.getClip();
	            clip.open(audioIn);
	            sounds.put(name, clip);
	        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	            e.printStackTrace();
	        }
	}
	
	// Can set individual volume of clips as long as it doesn't exceed globalVolume
	public void setVolume(String name, float volume) {
		
	}
	
	// Plays a sound once
	public void play(String name) {
		if (isMuted) return;
        Clip clip = sounds.get(name);
        if (clip != null) {
            clip.stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }
	
	// Loops the sound (useful for music/ambient sound
	public void loop(String name) {
		 
    }
	
	// Halts playing of sound
	public void stop(String name) {
		Clip clip = sounds.get(name);
        if (clip != null) clip.stop();
    }
	
	
	public void stopAll() {
		for (Clip clip : sounds.values()) {
            clip.stop();
        }
    }
	
	public double getGlobalVolume() {
        return globalVolume;
    }
	
	// Sets global volume within range 0.0 - 1.0
	public void setGlobalVolume(float volume) {
		
	}

    // Mutes all sounds.
    public void mute() {
        isMuted = true;
        this.stopAll(); 
    }
    
    // Only sets isMuted to false, so making music/sounds play again will be done in GraphicsGame
    public void unmute() {
        isMuted = false;
    }

    // Toggles mute/unmute state
    public void toggleMute() {
        isMuted = !isMuted;
        if (isMuted) stopAll();
    }
    
    // Returns whether the sound is muted
    public boolean isMuted() {
        return isMuted;
    }

    // Quick test
	public static void main(String[] args) {
		SoundManager test = SoundManager.getInstance();
		
		test.loadSound("main", "Media/test ambients.wav");
		test.loadSound("test", "Media/test.au");
		test.play("main");
		test.play("test");
		
		try { // The bad noise/sound cutting out may be caused by 8-bit encoding or low-power mode in laptops
            Thread.sleep(3000);
            System.out.println("Muting...");
            test.mute();

            Thread.sleep(2000);
            System.out.println("Unmuting...");
            test.unmute();
            test.play("main");
            test.play("test");
            
            Thread.sleep(2000);
            System.out.println("Stopping all sound...");
            test.stopAll();
            
            Thread.sleep(2000);
            System.out.println("Looping sound...");
            //test.loop("main");
            
            Thread.sleep(4000);
            System.out.println("Stopping...");
            test.stop("main");
            
            test.play("test");
            
            Thread.sleep(10000);
            test.stopAll();
             
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		return;
		
	}
	
}

