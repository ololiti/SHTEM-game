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
    public static final int BOX_HEIGHT = 30;
    public static final int BOX_WIDTH = 200;
    
    private int points = 0;
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
        game1.addMouseListener(new MyMouseListener(1,this));
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
    public void actionPerformed(ActionEvent e){
        running = false;
    }
    public void move(){
        Random rand = new Random();
        while(running){
            x1 += rand.nextInt(2*STEPSIZE)-STEPSIZE;
            x2 += rand.nextInt(2*STEPSIZE)-STEPSIZE;
            x3 += rand.nextInt(2*STEPSIZE)-STEPSIZE;
            repaint();
            if(x1 >= 350 || x1<= 150){
                score -= 100;
                x1 = X;
                repaint();
            }
            if(x2 >= 350 || x2<= 150){
                score -= 100;
                x2 = X;
                repaint();
            }
            if(x3 >= 350 || x3<= 150){
                score -= 100;
                x3 = X;
                repaint();
            }
            try{
                Thread.sleep(TIME);
            }catch(Exception e){
                System.out.println("there was an error");
                System.exit(0);
            }
        }
    }

}
