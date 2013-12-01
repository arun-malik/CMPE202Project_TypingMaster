
/**
 * Write a description of class Spacemies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spacemies extends Enemy
{
    // List of "concrete" classes for this theme
    private static final Class[] SPACEMIES = new Class[] {
        RogueShadow.class, DeathStar.class, GeonosianSolarSailer.class, EbonHawk.class
    };
    
    /**
     * Returns a list of Enemies classes 
     */
	public Class[] getEnemies() {
      return SPACEMIES;
    }
}
