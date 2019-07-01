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
    public static final int BOX_HEIGHT = 20;
    public static final int BOX_WIDTH = 200;
    
    private int points = 0;
    int x1 = X;
    int x2 = X;
    int x3 = X;
    boolean x1visible = true;
    boolean x2visible = true;
    boolean x3visible = true;
    
    private boolean running = true;
    private boolean scheduled = false;
    
    public MainScreen(){
        super("Hover Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5,1));
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
        JButton end = new JButton("end");
        end.addActionListener(this);
        add(end);
        
        TimerTask task = new TimerTask()
        {
            public void run()
            {
                Random rand = new Random();
                x1 += rand.nextGaussian()*STEPSIZE;
                x2 += rand.nextGaussian()*STEPSIZE;
                x3 += rand.nextGaussian()*STEPSIZE;
                scheduled = false;
                repaint();
            }    
        };
        while (running){
            if(!scheduled){
                Timer t = new Timer();
                t.schedule(task, TIME);
                scheduled = true;
            }
        }
        
    }
    public void actionPerformed(ActionEvent e){
        running = false;
    }
    

}
