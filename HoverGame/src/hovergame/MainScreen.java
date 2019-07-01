/*
 * Aditi Talati - 9th period T/Th - due May 28, 2018
 * Final Project - make a game of battleship that can be played over 
                   multiple devices
 */
package hovergame;

/**
 *
 * @author Aditi
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class MainScreen extends JFrame{
    private static int size = 50;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    public static final Color BACKGROUND = new Color(232,241,242);
    public static final Font TEXT_FONT= new Font("Courier", Font.BOLD, 12);
    
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
        
    }
}
