/* Autumn Barker (Carter)
 * cs251 - Fall 2014 Section 5
 * Breakout Game Lab
 * Player.java
 */

/**
 *  A class to define the Player
 */
public class Player{
    private int lives;
    private int score;
    private int level;
    
    public Player(int lives, int score){
        this.lives = lives;
        this.score = score;
        level = 1;
    }
    //method decrements life when called and returns whether game is over
    //or lives are = or < 0.
    public boolean loseLife()
    {
        lives--;
        if(lives <= 0){
            return true;
        }else{
            return false;
        }
    }
    public void addPoints (int points){
        score += points;
    }
    public int getScore(){
        return score;
    }
    public int getLives(){
        return lives;
    }
    public int getLevel(){
        return level;
    }
    public void nextLevel(){
        level++;
    }
}