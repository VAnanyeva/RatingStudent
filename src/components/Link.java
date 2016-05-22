/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Cursor;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Victoria
 */
public class Link extends JPanel{
    private String caption = "";
    private String img = "";
    public Link(String img, String caption){
        super();
        this.caption = caption;
        this.img = img;
        init();
    }
    
    private void init(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel pnlX = new JPanel();
        pnlX.setLayout(new BoxLayout(pnlX, BoxLayout.X_AXIS));
        JLabel lblImg = new JLabel();
        lblImg.setIcon(new ImageIcon(getClass().getResource("/img/"+img+".png"))); 
        JLabel lblCaption = new JLabel(caption);
        pnlX.add(lblImg);
        pnlX.add(lblCaption);
        pnlX.add(Box.createGlue());

        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(pnlX);

    }
}
