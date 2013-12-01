
public class Oceanmies extends Enemy {
	private static final Class[] OCEANMIES = new Class[] {
		BullShark.class, GreatWhite.class, Hammerhead.class, TigerShark.class
	};

	public Class[] getEnemies() {
		return OCEANMIES;
	}
}