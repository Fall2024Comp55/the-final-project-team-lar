import acm.util.SoundClip;
import java.util.HashMap;

// For future reference!
// Only supported file formats for ACM SoundClip are WAV, AU, or AIFF
// Some WAV files have encoding compatibility issues with SoundClip


public class SoundManager {
	
	private HashMap<String, SoundClip> sounds;

	private double globalVolume;
    private boolean isMuted;
    
    // Default value for volume (range 0.0 - 1.0)
    private static final double DEFAULT_VOLUME = 0.5;
	
    // Constructor
	public SoundManager() {
		sounds = new HashMap<>();
		globalVolume = DEFAULT_VOLUME;
		isMuted = false;
	}
	
	// Loads sounds from Media folder with a String as a key
	public void loadSound(String name, String filename) {
		if (sounds.containsKey(name)) return; // already loaded
		try {
            SoundClip clip = new SoundClip(filename);
            if (clip != null) {
            	clip.setVolume(globalVolume); //needs a base volume level or sounds won't play
                sounds.put(name, clip);
            }
        } catch (Exception e) {
            System.err.println("Error loading sound: " + filename);
            e.printStackTrace();
        }
	}
	
	// Can set individual volume of clips as long as it doesn't exceed globalVolume
	public void setVolume(String name, double volume) {
	    SoundClip clip = sounds.get(name);
	    if (clip != null && volume <= globalVolume) {
	        clip.setVolume(volume); // range 0.0–1.0
	    }
	}
	
	// Plays a sound once
	public void play(String name) {
        SoundClip clip = sounds.get(name);
        if (clip != null && !isMuted) {
        	clip.stop();  // ensure no overlap
            clip.play();
        } else {
            System.err.println("Sound not found: " + name);
        }
    }
	
	// Loops the sound (useful for music/ambient sound
	public void loop(String name) {
        SoundClip clip = sounds.get(name);
        if (clip != null && !isMuted) {
        	clip.stop();  // ensure clean restart if looping same file again
            clip.loop();
        } else {
            System.err.println("Sound not found: " + name);
        }
    }
	
	// Halts playing of sound
	public void stop(String name) {
        SoundClip clip = sounds.get(name);
        if (clip != null) {
            clip.stop();
        } else {
            System.err.println("Sound not found: " + name);
        }
    }
	
	
	public void stopAll() {
        for (SoundClip clip : sounds.values()) {
            clip.stop();
        }
    }
	
	public double getGlobalVolume() {
        return globalVolume;
    }
	
	// Sets global volume within range 0.0 - 1.0
	public void setGlobalVolume(double volume) {
		globalVolume = Math.max(0.0, Math.min(1.0, volume));
		for (SoundClip clip : sounds.values()) {
            clip.setVolume(globalVolume);
        }
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
		SoundManager test = new SoundManager();
		
		test.loadSound("main", "Media/test ambients.wav");
		test.play("main");
		
		try { // The bad noise/sound cutting out is caused by the 8-bit encoding
            Thread.sleep(3000);
            System.out.println("Muting...");
            test.mute();

            Thread.sleep(2000);
            System.out.println("Unmuting...");
            test.unmute();
            test.play("main");
            
            Thread.sleep(2000);
            System.out.println("Stopping all sound...");
            test.stopAll();
            
            Thread.sleep(2000);
            System.out.println("Looping sound...");
            test.loop("main");
            
            Thread.sleep(4000);
            System.out.println("Stopping...");
            test.stop("main");
             
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		return;
		
	}
	
}

