/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Victoria
 */
public class Link extends JPanel{
    private String caption = "";
    
    public Link(String img, String caption){
        super();
        this.caption = caption;
        init();
    }
    
    private void init(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setMinimumSize(new Dimension (50,50));
        JLabel lblCaption = new JLabel(caption);
        this.setBackground(Color.BLACK);
        this.setForeground(Color.white);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(lblCaption);
        this.add(Box.createVerticalStrut(10));
    }
}
