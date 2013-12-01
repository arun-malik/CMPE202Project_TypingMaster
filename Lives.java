public class Lives implements IObserveGameStatus {
	private int lives;

	public Lives() {
		lives = 3;
	}

	public void update(boolean hitMiss) {
		if (!hitMiss) {
			reduceLives();
		}
	}

	public boolean getGameOver() {
		return this.lives == 0;
	}
	
	public int getLivesLeft() {
		return this.lives;
	}

	public void reduceLives() {
		if (lives > 0) {
			this.lives--;
		}
	}
}
