public class Scheduler 
{
    private int ticks;
    private boolean levelWon;
    private boolean paused;

    public Scheduler() {
        this.ticks = 0;
        this.paused = false;
        this.levelWon = false;
    }

    public int getTick() {
        return ticks;
    }

    public boolean getLevelWon() {
        return levelWon;
    }

    public void setLevelWon(boolean won) {
        levelWon = won;
    }

    public boolean checkPause() {
        return paused;
    }

    public void togglePause(boolean pause) {
        paused = pause;
    }
}