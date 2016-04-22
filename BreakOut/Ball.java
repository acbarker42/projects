/* Autumn Barker (Carter)
 * cs251 - Fall 2014 Section 5
 * Lab 8:  Breakout Part 1
 * Ball.java
 */
import java.awt.Rectangle;

/**
 *  A class to define the Ball
 */
public class Ball extends GameObject{
    
    int xDir;
    int yDir;
    int initX;
    int initY;
    public Ball(GameArea gameArea, int x, int y, int diameter){
        super.gameArea = gameArea;
        super.x = x;
        super.y = y;
        super.width = diameter;
        super.height = diameter;
        initX = x;
        initY = y;
        xDir = 1;
        yDir = 2;
    }
    public void move(){
        if(x + xDir < 0 ||
           x + xDir + width > gameArea.getWidth()) {
            xDir = -xDir;
        }
        
        if(y + yDir < 0){
            yDir = -yDir;
        }
        x += xDir;
        y += yDir;
        
    }
    public void changeDirection(){
        yDir = -yDir;
        y += yDir;
    }
    public void ballReset(){
        x = initX;
        y = initY;
    }
    
}