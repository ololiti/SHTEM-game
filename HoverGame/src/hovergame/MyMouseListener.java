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
import static hovergame.MainScreen.X;
import java.awt.event.*;
public class MyMouseListener implements MouseListener{
    private int game; //the index of the panel
    private MainScreen m; //the main screen
    
    public MyMouseListener(int i, MainScreen m){
        //i is 1,2,or 3
        game = i;
        this.m = m;
        
    }
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    
    public void mouseClicked(MouseEvent e) //moves the ball to the center
    { 
        switch(game){
            case 1: MainScreen.x1 = X;
                    break;
            case 2: MainScreen.x2 = X;
                    break;
            case 3: MainScreen.x3 = X;
                    break;
        }
        int[] time = m.s.getTime();
        //prints the current time and each ball's distance from the center
        HoverGame.pw.println(time[1]+":"+time[2]+"," + (m.x1 - m.X) +"," 
                + (m.x2-m.X) + "," + (m.x3-m.X) + ", click, " + game + ", " + m.score);
        HoverGame.pw.flush();
        MainScreen.score -= MainScreen.CLICK_COST; //lose points for clicking
        //repaints the ball locations and the score
        m.repaint();
        m.scoreArea.setText("score: " + m.score);
    }

    public void mouseEntered(MouseEvent e){ //if the mouse is hovering, show the ball
        if(game == 1)
            MainScreen.x1visible=true;
        
        if(game == 2)
            MainScreen.x2visible=true;
        
        if(game == 3)
            MainScreen.x3visible=true;
        int[] time = m.s.getTime();
        //print the time, ball locations, action, and score
        HoverGame.pw.println(time[1]+":"+time[2]+"," + (m.x1 - m.X) +"," 
                + (m.x2 - m.X)+ "," + (m.x3 - m.X) + ", hover, " + game + ", " + m.score);
        HoverGame.pw.flush();
        
        //change the score and paint the screen
        MainScreen.score -= MainScreen.HOVER_COST;
        m.repaint();
        m.scoreArea.setText("score: " + m.score);
    }

    public void mouseExited(MouseEvent e) { //ball is shown until mouse exits - 
                                            //can show it for only one time step
                                            //but would be too short to actually see
                                        
        if(game == 1)
        
            m.x1visible=false;
        
        if(game == 2)
        
            m.x2visible=false;
        
        if(game == 3)
        
            m.x3visible=false;
        m.repaint();
        m.scoreArea.setText("score: " + m.score);
    }
}
