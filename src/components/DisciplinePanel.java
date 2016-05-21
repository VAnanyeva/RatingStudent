/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;


import db.Loader;
import forms.Dialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.border.TitledBorder;


/**
 *
 * @author Victoria
 */
public class DisciplinePanel extends JPanel implements TableInterface{
    private final Loader loader = new Loader();
    private Table table;
    private Object[][] inData = new Object[][] {{"1","2","3"},{"4","5","6"},{"7","8","9"}};
    private final Object[] columnNames = new Object[] {"№","Наименование дисциплины","Количество часов"};
    private Dialog addDialog;

    public DisciplinePanel (){
        init();
    }

    private void init() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(new TitledBorder("Дисциплины"));
        inData = getDataFromBase();
        table = new Table(inData,columnNames);

        this.add(table);
        
        MiniButton btnUpdate = new MiniButton("/img/update.png"); 
        btnUpdate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                  table.refresh(getDataFromBase());  
            }
        });
        table.addMiniButton(btnUpdate);
        
        
        
        MiniButton btnAdd = new MiniButton("/img/add.png"); 
        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 initAddDialog();
                 
                
            }
        });
        table.addMiniButton(btnAdd);
        
        MiniButton btnDel = new MiniButton("/img/delete.png"); 
        btnDel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loader.deleteDiscipline(Integer.parseInt(table.getValueAt(table.getSelectedRow(),0).toString()));
                inData = getDataFromBase();
                table.refresh(inData); 
            }
        });
        table.addMiniButton(btnDel);
        
        table.addMiniButton(Box.createHorizontalGlue());
    }
    
    @Override
    public Object[][] getDataFromBase(){
        return loader.getDataFromBase("select dis_id,name_dis,num_hours from discipline", new String[]{"dis_id","name_dis","num_hours"});
    }

    
    private void initAddDialog(){
        JTextField txfName = new JTextField();
        JTextField txfHours = new JTextField();
        txfName.setBorder(new TitledBorder("Наименование"));
        txfHours.setBorder(new TitledBorder("Количество часов"));        
        addDialog = new Dialog("Добавить дисциплину",400,150){
             @Override
             public void Ok(){
                 loader.insertDistipline(inData.length+1,txfName.getText(), Integer.parseInt(txfHours.getText()));
                 inData = getDataFromBase();
                 table.refresh(inData);  
                 addDialog.dispose();
             }
        };
        addDialog.addComponent(txfName);
        addDialog.addComponent(txfHours);
        addDialog.addComponent(Box.createVerticalGlue());
    }
    
    
    
}
