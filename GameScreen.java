import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

public class GameScreen extends Scene implements ISubjectGameStatus {
    private enum GAME_MODE {
        PREGAME, INGAME, POSTGAME;
    }

    private TextBanner banner;
    private GAME_MODE gameMode;

    private ScoreKeeper score;
    private Lives lives;
    private EnemyWatcher enemyWatcher;
    private List<IObserveGameStatus> registeredObservers;
    
    StringBuffer word = new StringBuffer();
    Enemy lockedEnemy = null;
    private Champion champion;
    
    TypingMaster world;

    public GameScreen() {
    }

    /**
     * Act - do whatever the GameScreen wants to do. This method is called
     * whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        // Get World for faster use

        // Changes the behavior based on the current state the game is in
        switch (gameMode) {
            // GAME HAS YET TO BEGIN
            case PREGAME:
                updateText();
                // Start Game on Click
                if (Greenfoot.mouseClicked(null)) {
                    startGame();
                }
                break;
    
            // GAME IS IN PROGRESS
            case INGAME:
                // Get Enemies In Game
                List<Enemy> enemies = world.getObjects(Enemy.class);
    
                // For each enemy compare it's location respective to the champion 
                for (Enemy enemy : enemies) {
                    // If the enemy's left edge matches or passes the right edge of the champion
                    // Then the Game is Over and call the StopGame function
                    if (enemy.getX() <= champion.getX()) {
                   
                        notifyObservers(false);
                        if(lockedEnemy == enemy) {
                            lockedEnemy = null;
                            world.getChampion().turnTowards(world.getChampion().getX(),world.getChampion().getY());
                            word.setLength(0);
                        }
                        world.removeObject(enemy);
                        if(lives.getGameOver()) {
                            stopGame();
                        }
                        break; // No reason to continue
                    }
                }
                
                String key = Greenfoot.getKey();
                if(key != null) {
                        //System.out.println("Key pressed: "+key);
                        if(key.equalsIgnoreCase("backspace")){
                            if(word.length() != 0) {
                                word.setLength(word.length()-1);
                                if(word.length() == 0) {
                                    lockedEnemy = null;
                                    world.getChampion().turnTowards(world.getChampion().getX(),world.getChampion().getY());
                                    word.setLength(0);
                                }
                            }
                        }
                        else if(key.equalsIgnoreCase("space")){
                            lockedEnemy = null;
                            world.getChampion().turnTowards(world.getChampion().getX(),world.getChampion().getY());
                            word.setLength(0);
                        }
                        else {
                            if(key.length() == 1) {
                                word.append(key);
                            }
                        }
                         //System.out.println("Key pressed: "+word.toString());
                        if(lockedEnemy == null && word.length() != 0) {
                            for (Enemy enemy : enemies)     {
                                if (enemy.getWord().toString().startsWith(word.toString())) {
                                    lockedEnemy = enemy;
                                    try {
                                        world.getChampion().turnTowards(lockedEnemy.getX(),lockedEnemy.getY());
                                    } catch(Exception ex) {
                                        world.getChampion().turnTowards(world.getChampion().getX(),world.getChampion().getY());
                                    }
                                    break; // No reason to continue
                                }
                            }
                        }
                        
                        if(lockedEnemy !=null && word.length() != 0) {
                            if(word.toString().contains(lockedEnemy.getWord().toString())) {
                                notifyObservers(true);
                                Button laser = new Button("                                       "+
                                                          "                                       "+
                                                          "                                       "+
                                                          "             "+
                                                          "---------------------------------------"+
                                                          "---------------------------------------",Color.blue,new Color(0,0,0,0), new Font("Comic Sans MS", Font.BOLD, 20 ) );
                                world.addObject(laser,world.getChampion().getX(),world.getChampion().getY());
                                laser.turnTowards(lockedEnemy.getX(),lockedEnemy.getY());
                                Greenfoot.delay(10);
                                world.removeObject(laser);                  
                                world.removeObject(lockedEnemy);
                                lockedEnemy = null;
                                world.getChampion().turnTowards(world.getChampion().getX(),world.getChampion().getY());
                                word.setLength(0);
                            }
                        }
                    }
    
                // Update Game Text
                updateText();
                break;
    
            // GAME IS FINISHED
            case POSTGAME:
            
                updateText();
                // Change Scene To Main Menu when clicked on
                if (Greenfoot.mouseClicked(null)) {
                    world.removeObjects(world.getObjects(TextBanner.class));
                    world.setScene(world.getMainMenu());
                }
                break;
        }
    }

    /**
     * Updates the Score Info On Screen
     */
    private void updateText() {
        banner.setImage(new GreenfootImage(generateInfoButtonString(), 25, Color.RED, new Color(0,0,0,0)));
        //banner.getImage().scale(2 * TypingGame.GRID_SIZE, 2 * TypingGame.GRID_SIZE);
    }

    /**
     * Generates the text indicating the Score of the game
     * Returns a String in the following manner:
     *  Time: <time elapsed in seconds> sec
     *  Score: <game score>
     *  Lives: <number of the worlds
     * 
     * @return string with time elapsed and game score
     */
    private String generateInfoButtonString() {
        String data = "";

        switch (gameMode) {
        case PREGAME:
            data = "Click Screen to start game.";
            break;

        case INGAME:
            if (world != null) {
                // Show game time
                /*Date endTime = new Date();
                long seconds = (long) (endTime.getTime() - startTime.getTime()) / 1000;

                // Show score
                int score = world.getScoreKeeper().getScore();

                data = String.format("Time: %d sec\nScore: %d", seconds, score);*/
                data = String.format("Score: "+world.getScoreKeeper().getScore() +"\nLives: "+lives.getLivesLeft()+"\nKey Pressed: "+ word.toString());
            }
            break;

        case POSTGAME:
            if (world != null) {
                // Show game time
                //Date endTime = new Date();
                //long seconds = (long) (endTime.getTime() - startTime.getTime()) / 1000;

                // Show score
                //int score = world.getScoreKeeper().getScore();

                //data = String.format("Time: %d sec\nScore: %d\nClick Screen to continue.",seconds, score);
                data = String.format("Score: "+world.getScoreKeeper().getScore() +"\nLives: "+lives.getLivesLeft()+"\nGame Over");
            }
            break;
        }

        return data;
    }

    private void startGame() {
        
    }

    /**
     * Stops the Game when the game is over
     */
    private void stopGame() {
        // Remove Objects
        removeObjects();

        // Change the game state to postgame
        gameMode = GAME_MODE.POSTGAME;
    }

    private void removeObjects() {
        TypingMaster world = (TypingMaster) getWorld();

        // Remove the actors from the world
        world.removeObjects(world.getObjects(Enemy.class));
        world.removeObjects(world.getObjects(Champion.class));
        //world.removeObjects(world.getObjects(TextBanner.class));

        // Remove Enemy Watcher if it hasn't already been removed
        if (world.getEnemyWatcher() != null) {
            world.removeObject(world.getEnemyWatcher());
            world.setEnemyWatcher(null);
        }
    }

    public void enterScene() {
        world = (TypingMaster) getWorld();
        gameMode = GAME_MODE.INGAME;
        champion = world.getChampion();
        
        world.setBackground(world.getTheme().getBackground());
        
        registeredObservers = new ArrayList<IObserveGameStatus>();
        score = new ScoreKeeper();
        lives = new Lives();
        register(score);
        register(lives);
        
        world.setScoreKeeper(score);
        enemyWatcher = new EnemyWatcher();
        world.setEnemyWatcher(enemyWatcher);
        banner = new TextBanner(new GreenfootImage(generateInfoButtonString(), 25, Color.BLACK, new Color(0,0,0,0)));
        world.addObject(banner,1,6);
        world.addObject(champion,2,2);
        world.addObject(score,0,0);
        world.addObject(enemyWatcher,0,0);
        
        // Play Sound
        world.getTheme().getBackgroundSound().setVolume(100);
        world.getTheme().getBackgroundSound().playLoop();
    }

    public void exitScene() {
        world.getTheme().getBackgroundSound().stop();

        // Remove Variables
        removeObjects();
    }

    public List<IObserveGameStatus> getRegisteredObservers() {
        return registeredObservers;
    }
    
    public void register(IObserveGameStatus observer) {
        registeredObservers.add(observer);
    }

    public void unRegister(IObserveGameStatus observer) {
        registeredObservers.remove(observer);
    }

    public void notifyObservers(Boolean hitMiss) {
        for (IObserveGameStatus observer : registeredObservers) {
            observer.update(hitMiss);
        }
    }
}
