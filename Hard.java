
import java.util.HashMap;

public class Hard extends Difficulty
{
    public Hard() {
    	// Set the Actor Image
    	setImage(TypingMaster.BUTTON_HARD);
    	
		// File that contains words 
    	final String filename = "res\\words_hard.txt";
		
		// Define Game Settings
		final HashMap<Properties, String> settings = new HashMap<Properties, String>();
		settings.put(Properties.MAX_NUM_ENEMIES, "4");
		settings.put(Properties.GAME_SPEED, "38");
		settings.put(Properties.SCORE_VALUE, "30");
		
		// Process Difficulty
		super.init(filename, settings);
    }
}
