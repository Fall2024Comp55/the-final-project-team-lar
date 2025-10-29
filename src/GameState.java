public enum GameState {
	MENU, PLAYING, WIN, LOSE, PAUSE, OPTIONS;
	
	public String toString() {
		switch(this) {
			case MENU: return "menu";
			case PLAYING: return "playing";
			case WIN: return "win";
			case LOSE: return "lose";
			case PAUSE: return "pause";
			case OPTIONS: return "options";
		}
		return "n/a";
	}
	
}