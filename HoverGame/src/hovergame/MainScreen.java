/*
 * Aditi Talati - 9th period T/Th - due May 28, 2018
 * Final Project - make a game of battleship that can be played over 
                   multiple devices
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
public class MainScreen extends JFrame {
    private static int size = 50;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    public static final Color BACKGROUND = new Color(232,241,242);
    public static final Font TEXT_FONT= new Font("Courier", Font.BOLD, 12);
    public static final int DIAMETER = 10;
    public static final int TIME = 250;
    public static final int Y = 25;
    public static final int X = 250;
    
    public static final int BOX_X = 150;
    public static final int BOX_Y = 15;
    public static final int BOX_HEIGHT = 20;
    public static final int BOX_WIDTH = 200;
    
    private int points = 0;
    private int x1 = X;
    private int x2 = X;
    private int x3 = X;
    private boolean x1visible = false;
    private boolean x2visible = false;
    private boolean x3visible = false;
    
    public MainScreen(){
        super("Hover Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4,1));
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
        
        JPanel game1 = new JPanel(){
            public void paintComponent(Graphics g){
                if (x1visible)
                    g.drawOval(x1, Y, DIAMETER, DIAMETER);
                g.drawRect(BOX_X,BOX_Y, BOX_WIDTH,BOX_HEIGHT);
            }
        };
        add(game1);
        JPanel game2 = new JPanel(){
            public void paintComponent(Graphics g){
                if(x2visible)
                    g.drawOval(x2, Y, DIAMETER, DIAMETER);
                g.drawRect(BOX_X,BOX_Y, BOX_WIDTH,BOX_HEIGHT);
            }
        };
        add(game2);
        JPanel game3 = new JPanel(){
            public void paintComponent(Graphics g){
                if(x3visible)
                    g.drawOval(x3, Y, DIAMETER, DIAMETER);
                g.drawRect(BOX_X,BOX_Y, BOX_WIDTH,BOX_HEIGHT);
            }
        };
        add(game3);
        
        TimerTask task = new TimerTask()
        {
            public void run()
            {
                Random rand = new Random();
                x1 += rand.nextGaussian();
                x2 += rand.nextGaussian();
                x3 += rand.nextGaussian();
            }    
        };
        Timer repeat = new Timer();
        repeat.schedule(task, TIME);
        
    }
}
