public class Scheduler 
{
    private double ticks; // Time is now in double to account for smaller time changes e.g. milliseconds
    private boolean levelWon;
    private boolean levelLost;
    private boolean paused;

    public Scheduler() {
        this.ticks = 0;
        this.paused = false;
        this.levelWon = false;
    }

    public double getTick() {
        return this.ticks;
    }

    public boolean getLevelWon() {
        return this.levelWon;
    }

    public void setLevelWon(boolean won) {
        togglePause();
        this.levelWon = won;
    }

    public boolean getLevelLost() {
        return this.levelLost;
    }

    public void setLevelLost(boolean lost) {
        togglePause();
        this.levelLost = lost;
    }

    public boolean checkPause() {
        return paused;
    }

    public void togglePause() {
        paused = !paused;
        // Include method body for pausing ticks
        
    }

    // Include method for ticks incrementation and send to map
    public double updateTick(double timer) {
        if (!paused && !levelWon) {
            this.ticks += timer;
            return getTick();
        }
        return -1;
    }
}