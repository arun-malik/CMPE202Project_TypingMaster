import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;

public abstract class Theme extends Actor
{
	public abstract GreenfootImage getBackground();
    public abstract Champion getChampion();
    public abstract Enemy getEnemiesClass();
    public abstract GreenfootSound getBackgroundSound();
}
