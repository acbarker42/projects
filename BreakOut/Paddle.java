/* Autumn Barker (Carter)
 * cs251 - Fall 2014 Section 5
 * Lab 8:  Breakout Part 1
 * Paddle.java
 */

/**
 *  A class to define the Paddle
 */
public class Paddle extends GameObject{
    int toMove = 25;
    int initX;
    int initY;
    
    public Paddle(GameArea gameArea, int x, int y, int width, int height){
        super.gameArea = gameArea;
        super.x = x;
        super.y = y;
        super.width = width;
        super.height = height;
        initX=x;
        initY=y;
    }
    public void moveLeft(){
        //check for left wall otherwise move left by 5 pixels
        if((x-toMove)< 0){
             x=0;
        }
        else{
            x-=toMove;
        }
        return;
    }
    public void moveRight(){
        if((x+toMove+width)>gameArea.getWidth()){
            x=gameArea.getWidth()-width;
        }
        else{
            x+=toMove;
        }
        return;
    }
    public void paddleReset(){
        x=initX;
        y=initY;
    }
}
