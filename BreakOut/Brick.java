/* Autumn Barker (Carter)
 * cs251 - Fall 2014 Section 5
 * Lab 8:  Breakout Part 1
 * Brick.java
 */

/**
 *  A class to define the Bricks
 */
public class Brick extends GameObject{
    private int hits;
    private int points;
    public Brick(GameArea gameArea, int x, int y, int width, int height){
        super.gameArea = gameArea;
        super.x = x;
        super.y = y;
        super.width = width;
        super.height = height;
        hits = 1;
        points =hits*10;
        
    }
    public void setHits(int hits){
        this.hits = hits;
    }
    public int getHits(){
        return hits;
    }
    public void loseHit(){
        hits--;
    }
    public int getPointValue(){
        return points;
    }
    public void setPointValue(){
        points = hits*10;
    }
}