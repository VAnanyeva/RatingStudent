/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Victoria
 */
public class Link extends JPanel{
 
    public Link(String img, String text){
        JLabel caption = new JLabel(text);
        this.add(caption);
    }
}
