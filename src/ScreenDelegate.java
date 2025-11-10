public interface ScreenDelegate {
    void onMonsterRevealed();
    void onBatteryLow(double remaining);
    void onPlayerLose();
    void onDistractionTriggered(String type);
    void onLevelComplete();
}

// add to the classes by adding "private ScreenDelegate delegate" as a data member