/*
 * Aditi Talati - 1 July 2019
 * create a game where people have to keep a point in a range, but they lose 
 * points every time they look at the point
 */
package hovergame;

/**
 *
 * @author Aditi
 */
import java.io.*;
public class HoverGame {

    /**
     * @param args the command line arguments
     */
    static PrintWriter pw;
    public static void main(String[] args) {
        try{
            pw = new PrintWriter(new FileOutputStream("player.csv"));
            //each line = time,x1-X,x2-X,x3-X,move,[1,2,3],points before move
            //if |x_i - X|>100 then out of bounds
        } catch(FileNotFoundException e){
            System.out.println("file not found (oops)");
            System.exit(0);
        }
        MainScreen main = new MainScreen();
        main.setVisible(true);
        SimpleAudioPlayer s = null;
        try{
            s = new SimpleAudioPlayer("kahoot.wav");
            s.play();
        } catch (Exception e){
            System.out.println("there was an exception: " + e );
            System.exit(0);
        }
        main.move();
        s.pause();
        pw.close();
    }
    
}
