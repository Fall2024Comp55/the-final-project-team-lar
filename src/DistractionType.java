
public enum DistractionType {
	FLY, FAKE_MONSTER, MOVING_SHADOW, WHISPER, CREAK_SOUND;
	
	public String toString() {
		switch(this) {
			case FLY: return "fly";
			case FAKE_MONSTER: return "fake_monster";
			case MOVING_SHADOW: return "moving_shadow";
			case WHISPER: return "whispering";
			case CREAK_SOUND: return "creak_sound";
			
		}
		return "n/a";
	}
}
