import java.util.HashMap;


public class Easy extends Difficulty
{
	public Easy() {
		// Set the Actor Image
    	setImage(TypingMaster.BUTTON_EASY);
		
		// File that contains words 
		final String filename = "res\\words_easy.txt";
		
		// Define Game Settings
		final HashMap<Properties, String> settings = new HashMap<Properties, String>();
		settings.put(Properties.MAX_NUM_ENEMIES, "3");
		settings.put(Properties.GAME_SPEED, "45");
		settings.put(Properties.SCORE_VALUE, "10");
		
		// Process Difficulty
		super.init(filename, settings);
	}
}
