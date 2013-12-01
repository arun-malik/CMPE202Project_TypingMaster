import greenfoot.Actor;
import greenfoot.Greenfoot;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public abstract class Difficulty extends Actor {
	
	public enum Properties {
    	MAX_NUM_ENEMIES, GAME_SPEED, SCORE_VALUE
    }
	
    // Instance variables
	private int index;
	private HashMap<Properties, String> settings;
	private String[] words;
	
	// Default Values
	private static final int MAX_NUM_ENEMIES = 2;
	private static final int GAME_SPEED = 30;
	private static final int SCORE_VALUE = 10;
	
	
	/**
	 * Initializer for the Difficulty 
	 * @param words - the list of words available
	 * @param settings - Settings for the Difficulty
	 */
    public void init(String filename, HashMap<Properties, String> settings) {
    	List<String> wordsList = new LinkedList<String>();
    	
    	try {
			Scanner wordScanner = new Scanner(new File(filename));
			while(wordScanner.hasNextLine()) {
				wordsList.add(wordScanner.nextLine());
			}
			wordScanner.close();
		} catch (Exception e) {
			System.err.println("ERROR READING DIFFICULTY LIST!");
		}
    	
    	index = 0;
    	this.words = wordsList.toArray(new String[wordsList.size()]);
    	this.settings = settings;
    }
    
    
    /**
     * Returns the next word in the list
     * @return the next word in the list 
     */
    public String getWord() {
    	return words[index++ % words.length];
    }
    
    /**
     * Loops until the next valid word is found 
     * @param ignoreList list of words to be ignored
     * @return Returns the next valid word in the list
     */
    public String getWord(List<String> ignoreList) {
        String word = null;
        
        do {
            word = getWord();
        } while (ignoreList.contains(word));
        
        return word;
    }
    
    /**
     * Returns a random word in the words list
     * @return a random word in the words list
     */
    public String getRandomWord() {
    	return words[Greenfoot.getRandomNumber(words.length)];
    }
    
    /**
     * Returns a random word that is not in the ignoreList 
     * @param ignoreList list of words to be ignored
     * @return Returns the random word not in the ignoreList
     */
    public String getRandomWord(List<String> ignoreList) {
        String word = null;
        
        do {
            word = getRandomWord();
        } while (ignoreList.contains(word));
        
        return word;
    }
    
    /**
     * Returns a property for this difficulty
     * @param property the key to look for
     * @return value associated with the requested key
     */
    public String getProperty(Properties property) {
    	return settings.get(property);
    }
    
    
    /**
     * Returns the maximum number of enemies allowed in the word
     * @return the max number of enemies
     */
    public int getMaxNumberOfEnemies() {
    	try {
    		return Integer.parseInt(getProperty(Properties.MAX_NUM_ENEMIES));
    	}
    	catch(Exception e) {
    		return MAX_NUM_ENEMIES;
    	}
    }
    
    /**
     * Returns the game speed
     * @return Greenfoot Game Speed
     */
    public int getGameSpeed() {
    	try {
    		return Integer.parseInt(getProperty(Properties.GAME_SPEED));
    	}
    	catch(Exception e) {
    		return GAME_SPEED;
    	}
    }
    
    /**
     * Returns the Score Value
     * @return the value of an enemy when destroyed
     */
    public int getScoreValue() {
    	try {
    		return Integer.parseInt(getProperty(Properties.SCORE_VALUE));
    	}
    	catch(Exception e) {
    		return SCORE_VALUE;
    	}
    }
}
