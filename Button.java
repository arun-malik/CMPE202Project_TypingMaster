import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import java.awt.Color;
import java.awt.Font;

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    private boolean isButton;
    private GreenfootImage image;
    private boolean isScaledDown; 
    private int countDelay;
    
    public Button(GreenfootImage image) {
        setImage(image);
        this.image = image;
        
        isButton = false;
        isScaledDown = false;
        countDelay = 0;
    }
    
    public Button(String buttonName) {
        GreenfootImage img = new GreenfootImage(buttonName, 30, Color.red, new Color(0,0,0,0));
        img.setFont(new Font("Comic Sans MS", Font.BOLD, 30 ));
        
        setImage(img);
        this.image = img;
        
        isButton = false;
        isScaledDown = false;
    }
    
    public Button(String buttonName, Color fontColor, Color back, Font font) {
        GreenfootImage img = new GreenfootImage(buttonName, 30, fontColor, back);
        img.setFont(font);
        setImage(img);
        image = img;
        
        isButton = false;
        isScaledDown = false;
    }
    
    public boolean isIntersecting(Actor actor) {
        if(intersects(actor)) {
            return true;
        }
        
        return false;
    }
    
    public void act(){
        if(isButton) {
            if(Greenfoot.mousePressed(this)) {
                image.scale((int)(image.getWidth()*0.5),(int)(image.getHeight()*0.5));
                setImage(image);
                isScaledDown = true;
            } else {
            	if(isScaledDown && countDelay >= 30) {
                	image.scale((int)(image.getWidth()*2),(int)(image.getHeight()*2));
                	isScaledDown = false;
                	countDelay = 0;
                }
                setImage(image);
            }
            
            if(isButton) {
            	countDelay++;
            }
        }
    }
    
    public void setIsButton(boolean flag) {
        this.isButton = flag;
    }
    
    public boolean getIsButton() {
        return this.isButton;
    }
    
}
