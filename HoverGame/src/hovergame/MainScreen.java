/*
 * Aditi Talati - 1 July 2019
 * create a game where people have to keep a point in a range, but they lose 
 * points every time they look at the point
 */
package hovergame;
/**
 *
 * @author Aditi
 * @version 7/1/19
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import java.io.*;
public class MainScreen extends JFrame implements ActionListener{
    private static int size = 50;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    public static final Color BACKGROUND = new Color(232,241,242);
    public static final Font TEXT_FONT= new Font("Courier", Font.BOLD, 12);
    public static final int DIAMETER = 10;
    public static final int TIME = 250;
    public static final int Y = 25;
    public static final int X = 250;
    public static final int STEPSIZE = 10;

    public static final int BOX_X = 150;
    public static final int BOX_Y = 15;
    public static final int BOX_HEIGHT = 30;
    public static final int BOX_WIDTH = 200;
    
    public static final int MIN_POINTS = -250;
    public static final int OUT_OF_RANGE_COST = 100;
    public static final int CLICK_COST = 10;
    public static final int HOVER_COST = 1;

    public static final int PANELS = 3;
    
    //to use in an array if i figure out how to make one
    public static int[] xVals = new int[PANELS];
    public static boolean[] visible = new boolean[PANELS];
    
    static int x1 = X;
    static int x2 = X;
    static int x3 = X;
    static boolean x1visible = false;
    static boolean x2visible = false;
    static boolean x3visible = false;
    static int score =0;

    private boolean running = true;
    private boolean scheduled = false;
    JTextArea scoreArea;
    
    Stopwatch s;

    public MainScreen(){
        //creates the JFrame that runs the main screen
        super("Hover Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(PANELS+2,1));
        setBackground(BACKGROUND);

        JPanel instructions = new JPanel();
        instructions.setLayout(new FlowLayout());
        instructions.setBackground(BACKGROUND);
        JTextArea instructionText = new JTextArea();
        instructionText.setFont(TEXT_FONT);
        instructionText.setText("Hover over a panel to see the point (-1). "
            + "\nClick to move the point to the center (-10)."
            + "\nDon't let the point leave the bounds (-100).");
        instructionText.setEditable(false);
        instructions.add(instructionText);
        add(instructions);

        //there are three panels, which each have a box and a circle
        //the circle is only visible when the mouse is hovering
        //I don't like having separate variables instead of an array - maybe I'll
        //change it to an array
        JPanel game1 = new JPanel(){
            public void paintComponent(Graphics g){ //this function repaints the moving ball
                if (x1visible)
                    g.drawOval(x1, Y, DIAMETER, DIAMETER);
                g.drawRect(BOX_X,BOX_Y, BOX_WIDTH,BOX_HEIGHT);
            }
        };
        game1.addMouseListener(new MyMouseListener(1,this)); //adds a mouselistener that stores the
                                                             //index of the panel
        add(game1);
        JPanel game2 = new JPanel(){
                public void paintComponent(Graphics g){
                    if(x2visible)
                        g.drawOval(x2, Y, DIAMETER, DIAMETER);
                    g.drawRect(BOX_X,BOX_Y, BOX_WIDTH,BOX_HEIGHT);
                }
            };
        game2.addMouseListener(new MyMouseListener(2,this));
        add(game2);
        JPanel game3 = new JPanel(){
                public void paintComponent(Graphics g){
                    if(x3visible)
                        g.drawOval(x3, Y, DIAMETER, DIAMETER);
                    g.drawRect(BOX_X,BOX_Y, BOX_WIDTH,BOX_HEIGHT);
                }
            };
        game3.addMouseListener(new MyMouseListener(3,this));
        add(game3);

        //panel with a score display and an "end" button
        //could add a stopwatch here too
        JPanel endPanel = new JPanel();
        endPanel.setBackground(BACKGROUND);
        endPanel.setLayout(new FlowLayout());

        JButton end = new JButton("end");
        end.addActionListener(this);
        endPanel.add(end);

        scoreArea = new JTextArea();
        scoreArea.setText("score: " + score);
        scoreArea.setEditable(false);
        endPanel.add(scoreArea);
        add(endPanel);
    }

    //this is only called when the mouse clicks the end button
    public void actionPerformed(ActionEvent e){
        running = false;
        //boolean that flags the timer and move functions
    }

    public void move(){ //this function loops constantly until running is false
        s = new Stopwatch(); //starts a stopwatch on a new thread
        s.startThread();
        Random rand = new Random();
        while(running){
            //each ball takes a step and then the screen is repainted
            x1 += rand.nextInt(2*STEPSIZE)-STEPSIZE;
            x2 += rand.nextInt(2*STEPSIZE)-STEPSIZE;
            x3 += rand.nextInt(2*STEPSIZE)-STEPSIZE;
            repaint();
            
            //for each ball, if the ball is out of range,
            //the player loses OUT_OF_RANGE_COST points and the ball
            //moves back to the center
            if(x1 >= BOX_X+BOX_WIDTH || x1<= BOX_X){
                score -= OUT_OF_RANGE_COST;
                x1 = X;
                scoreArea.setText("score: " + score);
                repaint();
            }
            if(x2 >= BOX_X+BOX_WIDTH || x2<= BOX_X){
                score -= OUT_OF_RANGE_COST;
                x2 = X;
                scoreArea.setText("score: " + score);
                repaint();
            }
            if(x3 >= BOX_X+BOX_WIDTH || x3<= BOX_X){
                score -= OUT_OF_RANGE_COST;
                x3 = X;
                scoreArea.setText("score: " + score);
                repaint();
            }
            try{
                Thread.sleep(TIME); //wait TIME milliseconds before moving again
            }catch(Exception e){
                System.out.println("there was an error");
                System.exit(0);
            }
            if (score < MIN_POINTS) //if the player lost
            {
                int[] curTime = s.getTime();            
                s.stopThread(); //stop running the timer
                running=false; //stop running the game
                //switch to the end screen
                EndScreen end= new EndScreen(curTime[1] + " minutes and " + curTime[2] + " seconds");
                end.setVisible(true);
                dispose();
            }
        }
    }
}
