/* Autumn Barker (Carter)
 * cs251 - Fall 2014 Section 5
 * Breakout Game Lab
 * GameController.java
 */
import java.util.*;
import javax.swing.*;
import java.nio.file.*;
import java.io.*;
/**
 * Driver for testing out breakout objects.
 */
public class Breakout {
    
    private static List<Brick> myBricks = new ArrayList<>();
    private static String fileName;
    public static void main(String[] args)throws IOException {

        // Make a dummy game area
        // In an actual game, this could be a specialized JPanel
        GameArea gameArea = new GameArea() {
            public int getWidth() { return 800; }
            public int getHeight() { return 650; }
        };
        
        
        // Make a ball
        Ball ball = new Ball(gameArea, gameArea.getWidth()/2-13, gameArea.getHeight()-100, 25);
        
        // Make a paddle
        Paddle paddle = new Paddle(gameArea, gameArea.getWidth()/2-50, gameArea.getHeight()-50, 100, 10);
        
        //make blocks
        //check for file
        fileName = "";
        if (args.length > 0){
            fileName = args[0];
        }
        
        //Make a player
        Player player = new Player(5,0);
        
        //Make a gameview
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            GameView gameView = new GameView(gameArea, paddle,ball,myBricks,player,fileName);

            }
        });
    }
}