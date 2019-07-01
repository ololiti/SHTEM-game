/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hovergame;

/**
 *
 * @author Aditi
 */
import static hovergame.MainScreen.X;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class MyMouseListener implements MouseListener{
    private int game;
    private MainScreen m;
    
    public MyMouseListener(int i, MainScreen m){
        //i is 1,2,or 3
        game = i;
        this.m = m;
        
    }
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    
    public void mouseClicked(MouseEvent e) 
    { 
        switch(game){
            case 1: MainScreen.x1 = X;
                    break;
            case 2: MainScreen.x2 = X;
                    break;
            case 3: MainScreen.x3 = X;
                    break;
        }
        
        MainScreen.score -= 10;
        m.repaint();
        m.scoreArea.setText("score: " + m.score);
    }

    public void mouseEntered(MouseEvent e){ 
        if(game == 1)
            MainScreen.x1visible=true;
        
        if(game == 2)
            MainScreen.x2visible=true;
        
        if(game == 3)
            MainScreen.x3visible=true;
        MainScreen.score -= 1;
        m.repaint();
        m.scoreArea.setText("score: " + m.score);
    }

    public void mouseExited(MouseEvent e) { 
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
