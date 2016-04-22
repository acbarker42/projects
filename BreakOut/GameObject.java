/* Autumn Barker (Carter)
 * cs251 - Fall 2014 Section 5
 * Lab 8:  Breakout Part 1
 * GameObject.java
 */

/**
 * A parent class Game Object to help define its child classes: Paddle, Brick, & Ball
 */

import java.awt.Rectangle;

public abstract class GameObject implements Object2D{
    int x;
    int y;
    int width;
    int height;
    GameArea gameArea;
    Rectangle myRectangle;
    
    
    /** @return x coordinate of upper left corner of object. */
    public int getX(){
        return x;
    }
    
    /** @return y coordinate of upper left corner of object. */
    public int getY(){
        return y;
    }
    
    /** @return object width. */
    public int getWidth(){
        return width;
    }
    
    /** @return object height. */
    public int getHeight(){
        return height;
    }
    /**
     * Get the bounding rectangle for the object.
     * @return Bounding rectangle.
     */
    public Rectangle getBoundingRectangle(){
        myRectangle = new Rectangle(x,y,width,height);
        return myRectangle;
    }
    
    /**
     * Checks to see if this object intersects another (Checks if the bounding
     * rectangles intersects)
     * @param other The other object to check.
     * @return True if objects intersect.
     */
    public boolean intersects(Object2D other){
        return this.getBoundingRectangle().intersects(other.getBoundingRectangle());
    }
    
    /**
     * Checks to see if any part of the object outside of the game area
     * @return True if part of object is out of bounds.
     */
    public boolean isOutOfBounds(){

        if (this.y + this.height > gameArea.getHeight()){
            return true;
        }else{
            return false;
        }
    }
    /**
     * Is any part of the object outside of the game area?
     * @return String of Game Object position
     */
    @Override
    public String toString(){
        if (this instanceof Ball){
            return "Ball at ("+ this.x +", "+ this.y+")";
        }else if (this instanceof Paddle){
            return "Paddle at ("+ this.x +", "+ this.y+")";
            
        }else if (this instanceof Brick){
            return "Brick at ("+ this.x +", "+ this.y+")";
        }else{
            return "Not a Game Object";
        }
    }    
}