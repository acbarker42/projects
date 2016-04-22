/* Autumn Barker (Carter)
 * cs251 - Fall 2014 Section 5
 * Breakout Game Lab
 * GameView.java
 */
import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.nio.file.*;
import java.io.*;
/**
 * A class that draws the Breakout game
 */

//this code was adapted from the clickcounter code from class
public class GameView extends JPanel implements ActionListener{
    private JFrame frame = new JFrame("Breakout");
    private JLabel lifeLabel = new JLabel();
    private JLabel scoreLabel = new JLabel();
    private JLabel levelLabel = new JLabel();
    private JButton button = new JButton("Start");
    private Paddle paddle;
    private Ball ball;
    private Player player;
    private GameArea gameArea;
    private List<Brick> bricks = new ArrayList<>();
    private String fName;
    private boolean firstLevel = true;
    private boolean isPaused = true;
    private int viewWidth;
    private int viewHeight;
    javax.swing.Timer timer;
    InputMap inputMap;
    ActionMap actionMap;
    
    public GameView(GameArea gameArea, Paddle paddle,
                    Ball ball, List<Brick> bricks, Player player, String fName) {
        this.gameArea = gameArea;
        this.viewWidth = gameArea.getWidth();
        this.viewHeight = gameArea.getHeight();
        this.paddle = paddle;
        this.ball = ball;
        this.bricks = bricks;
        this.player = player;
        this.fName = fName;
        
        lifeLabel.setText("Lives = " + player.getLives());
        scoreLabel.setText("Score = " + player.getScore());
        levelLabel.setText("Level = " + player.getLevel());
        
        //full panel holds two smaller panels
        GamePanel leftPanel = new GamePanel();
        JPanel rightPanel = new JPanel();

        frame.add (leftPanel,BorderLayout.CENTER);
        frame.add (rightPanel,BorderLayout.PAGE_END);

        //right panel uses border layout for label/button positioning.
        rightPanel.add(lifeLabel,BorderLayout.PAGE_START);
        rightPanel.add(scoreLabel,BorderLayout.PAGE_END);
        rightPanel.add(levelLabel,BorderLayout.PAGE_END);
        rightPanel.add(button,BorderLayout.CENTER);
        button.addActionListener(this);
        
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        //couldn't get my key listener to work so tried keybindings
        //help from stackoverflow
        inputMap = leftPanel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        actionMap = leftPanel.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RightArrow");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LeftArrow");
        actionMap.put("LeftArrow", new KeyAction("LeftArrow"));
        actionMap.put("RightArrow", new KeyAction("RightArrow"));
        
        timer = new javax.swing.Timer(10, this);
        timer.setInitialDelay(0);
        createLevel(fName);
        
    }
    //creates custom panel for drawing
    class GamePanel extends JPanel {
        
       GamePanel() {
            setPreferredSize(new Dimension(viewWidth,viewHeight));
            setBackground(Color.BLACK);
       }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            //draw bricks
            g.setColor(Color.BLUE);
            for(Object2D b:bricks){
            g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
            }

            //draw the paddle
            g.setColor(Color.WHITE);
            g.fillRect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());
            //draw the ball
            g.setColor(Color.RED);
            g.fillOval(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
        
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        
        if (obj == timer) {
            ball.move();
            checkStatus();
            frame.getContentPane().validate();
            frame.getContentPane().repaint();
            lifeLabel.setText("Lives = " + player.getLives());
            scoreLabel.setText("Score= " + player.getScore());
        }
        else{
            if(isPaused){
                timerStart();
            }else{
                timerStop();
            }
        }
        
    }
    
    public class KeyAction extends AbstractAction {
        
        private String cmd;
        
        public KeyAction(String cmd) {
            this.cmd = cmd;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!isPaused){
                if (cmd.equalsIgnoreCase("LeftArrow")) {
                    paddle.moveLeft();
                    frame.getContentPane().repaint();
                    
                } else if (cmd.equalsIgnoreCase("RightArrow")) {
                    paddle.moveRight();
                    frame.getContentPane().repaint();
                }
            }
        }
    }
    public void checkStatus(){
        List<Brick> bricksToRemove = new ArrayList<>();
        if (ball.intersects(paddle)){
            ball.changeDirection();
        }
        if(ball.isOutOfBounds()){
            player.loseLife();
            timerStop();
            ball.ballReset();
            paddle.paddleReset();
            
        }
        for(Brick b:bricks){
            if(ball.intersects(b)){
                ball.changeDirection();
                b.loseHit();
                if(b.getHits()==0){
                    bricksToRemove.add(b);
                }
            }
        }
        for(Brick b2r:bricksToRemove){
            player.addPoints(b2r.getPointValue());
            bricks.remove(b2r);
        }
        if(bricks.size()==0){
            timerStop();
            player.nextLevel();
            if(player.getLevel()==6){
                JOptionPane.showMessageDialog(frame,
                                              "FOR THE WIN",
                                              "You WON!",
                                              JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            }
            else{
                getNextLevel();
            }
        }
        if(player.getLives()==0){
            lifeLabel.setText("Lives = " + player.getLives());
            timer.stop();
            JOptionPane.showMessageDialog(frame,
                                          "Game Over!",
                                          "You Lose!",
                                          JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
        }
        
    }
    public void timerStart(){
        timer.start();
        button.setText("Pause");
        isPaused = false;
    }
    public void timerStop(){
        timer.stop();
        isPaused= true;
        button.setText("Start");
    }
    public void getNextLevel(){
        String nextLevel = "level" + player.getLevel()+".txt";
        createLevel(nextLevel);
        ball.ballReset();
        paddle.paddleReset();
        levelLabel.setText("Level = " + player.getLevel());
    }
    public void createLevel(String fileName){
        
        int cols=10;
        int rows=5;
        int spacer = 10;
        List<String> strList = new ArrayList<String>();
        BufferedReader in=null;
        String firstLine ="";
        if(firstLevel){
            try{
                if(fileName.equals("")){
                    fileName = "level1.txt";
                    InputStream input = getClass().getResourceAsStream(fileName);
                    in = new BufferedReader(new InputStreamReader(input));
                }
                else{
                    in = new BufferedReader(new FileReader(fileName));
                }
                firstLine = in.readLine();
                String line;
                while ((line = in.readLine()) != null) {
                    strList.add(line);
                }
            }
            catch(IOException ex){
                System.out.println (ex.toString());
                System.out.println("Could not find file " + fileName);
            }
            firstLevel=false;
            
        }
        else{
            try{

                InputStream input = getClass().getResourceAsStream(fileName);
                in = new BufferedReader(new InputStreamReader(input));
                firstLine = in.readLine();
                String line;
                while ((line = in.readLine()) != null) {
                    strList.add(line);
                }
            }
            
            
            catch(IOException ex){
                System.out.println (ex.toString());
                System.out.println("Could not find file " + fileName);
            }
        
        }

        String[] elems = firstLine.replaceAll(" ", "").split(",");
        rows = Integer.parseInt(elems[0]);
        cols = Integer.parseInt(elems[1]);
        int brickWidth = (gameArea.getWidth()-((cols+1)*spacer))/cols;
        int brickHeight = (gameArea.getHeight()/2)/rows;
        int y = spacer;
        for(String s : strList) {
            int x = spacer;
            String[] blocks = s.replaceAll(" ", "").split(",",-1);
            for(String bs: blocks){
                if(!bs.equals("")){
                    Brick brick = new Brick(gameArea, x, y, brickWidth, brickHeight);
                    brick.setHits(Integer.parseInt(bs));
                    brick.setPointValue();
                    bricks.add(brick);
                    x = x + brickWidth +spacer;
                }else{
                    x = x + brickWidth +spacer;
                }
            }
            y = y + brickHeight + spacer;
        }
    }
}