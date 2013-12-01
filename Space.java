import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;

public class Space extends Theme
{
	private Champion champion;
	private Enemy enemiesClass;
	private GreenfootImage image;
	private GreenfootSound sound;
	
	public Space() {
		// Set the Actor Image
    	setImage(TypingMaster.BUTTON_SPACE);
    	image = new GreenfootImage("images/space/background.jpg");
    	sound = new GreenfootSound("sounds/StarWars.mp3");
    	champion = new XWing();
    	enemiesClass = new Spacemies();
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
