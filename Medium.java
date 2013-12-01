
import java.util.HashMap;

public class Medium extends Difficulty {
    
    public Medium() {
    	// Set the Actor Image
    	setImage(TypingMaster.BUTTON_MEDIUM);
    	
    	// File that contains words
    	final String filename = "res\\words_medium.txt";
		
    	// Define Game Settings
    	final HashMap<Properties, String> settings = new HashMap<Properties, String>();
		settings.put(Properties.MAX_NUM_ENEMIES, "3");
		settings.put(Properties.GAME_SPEED, "40");
		settings.put(Properties.SCORE_VALUE, "20");
		
		// Process Difficulty
		super.init(filename, settings);
    }
}
