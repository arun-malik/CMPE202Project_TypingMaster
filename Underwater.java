import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;

public class Underwater extends Theme
{
	private Champion champion;
	private Enemy enemiesClass;
	private GreenfootImage image;
	private GreenfootSound sound;
	
	public Underwater() {
		// Set the Actor Image
    	setImage(TypingMaster.BUTTON_UNDERWATER);
    	
    	image = new GreenfootImage("images/underwater/background.jpg");
    	sound = new GreenfootSound("sounds/Ocean.mp3");
    	champion = new Diver();
    	enemiesClass = new Oceanmies();
	}
	
	
	public GreenfootImage getBackground() {
		return image;
	}
	
    public Champion getChampion() {
    	return champion;
    }
    
    public Enemy getEnemiesClass() {
    	return enemiesClass;
    }
    
    public GreenfootSound getBackgroundSound() {
    	return sound;
    }
}
