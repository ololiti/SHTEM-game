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
public class HoverGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainScreen main = new MainScreen();
        main.setVisible(true);
        main.move();
    }
    
}
