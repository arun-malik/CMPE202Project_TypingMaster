import greenfoot.Actor;


public class ScoreKeeper extends Actor implements IObserveGameStatus
{
    private int score;

    public ScoreKeeper() {
    	score = 0;
    }
    
    public void update(boolean hitMiss)
    {
        if(hitMiss) {
            updateScore();
        }
    }
    
    /**
     * Returns thet scores
     * @return returns the score
     */
    public int getScore() {
		return score;
	}
  
    public void updateScore() {
    	TypingMaster world = (TypingMaster) getWorld();
		this.score += world.getDifficuly().getScoreValue();
	}
}
