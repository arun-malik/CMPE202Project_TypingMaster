import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import java.awt.Color;

import java.util.List;

public class EnemyWatcher extends Actor {
    
    private int count;
    
    public EnemyWatcher() {
        count = 0;
    }
    
    public void act(){
        TypingMaster world = (TypingMaster) getWorld();
        
        if(count++ % world.getDifficuly().getGameSpeed() == 0) {
            // Figure out number of enemies to be added
            if(world.getDifficuly().getMaxNumberOfEnemies() > world.getObjects(Enemy.class).size()) {
                Enemy enemy = world.getTheme().getEnemiesClass().generateEnemy(world);
                int x = TypingMaster.GRID_WIDTH;
                int y = 0;
                
                boolean haveSlot = false;
                do {
                    // Only add to odd places
                    do {
                        //System.out.println("y: "+ y+" Grid_height: "+TypingGame.GRID_HEIGHT);
                        y = Greenfoot.getRandomNumber(TypingMaster.GRID_HEIGHT - 1);
                    } while(y % 2 != 0);
                    
                    // Verify no overlap
                    List<Enemy> enemies = world.getObjects(Enemy.class);
                    if(enemies == null || enemies.isEmpty()){
                        haveSlot = true;
                    } else {
                        for(Enemy env :  enemies) {
                            if(env.getY() != y || env.getY() != y +1) {
                                haveSlot = true;
                                break;
                            }
                        }
                    }
                } while(!haveSlot);
                
                enemy.setImage(new GreenfootImage(enemy.getWord(), 20, Color.RED, Color.BLACK));
                // Add to World
                world.addObject(enemy, x, y);
            }
        }
    }
}
