/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author ВИКА
 */
public class MiniButton extends JButton{
    private String img;
    
    public MiniButton(String img){
        this.img = img;
        init();
        
    }
    
    private void init(){
        setPreferredSize(new Dimension(32,32));
        setOpaque(true);
        setIcon(new ImageIcon(getClass().getResource(img)));
    }
}
