/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.util.Map;
import java.util.TreeMap;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

/**
 *
 * @author ВИКА
 */
public class ComboBox extends JComboBox{
    private TreeMap<Integer,String> data = new TreeMap<>();
    private String caption = "";
    
    public ComboBox(TreeMap<Integer,String> data, String caption){
        super();
        this.data = data;
        init();
    }
    
    private void init(){
        this.setBorder(new TitledBorder(caption));
        for(Map.Entry<Integer,String> item : data.entrySet()) {
            this.addItem(item.getValue());
        }
    }
    
    public int getSelectedId(){
        int id = -1;
        for(Map.Entry<Integer,String> item : data.entrySet()) {
            if (this.getSelectedItem().toString().equals(item.getValue())){
                id = item.getKey();
            }
        }
        return id;
    }
}
