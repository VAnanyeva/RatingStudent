/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import db.Loader;
import entitys.User;
import forms.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.TreeMap;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author ВИКА
 */
public class TaskPanel extends JPanel implements TableInterface {
    private final Loader loader = new Loader();
    private Table table;
    private Object[][] inData = new Object[][] {{"1","2","3"},{"4","5","6"},{"7","8","9"}};
    private final Object[] columnNames = new Object[] {"№","Наименование задачи","Описание"};
    private Dialog addDialog;
    private ComboBox cbxDis;
    private TreeMap<Integer,String> disciplines = new TreeMap<>();

    public TaskPanel (){
        init();
    }

    private void init() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new TitledBorder("Задачи"));
        
        disciplines = loader.getMapForComboBox("select dis_id as id,name_dis as text from discipline where user_id = "+User.usr_id);
        cbxDis = new ComboBox(disciplines,"Дисциплины");
        cbxDis.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                inData = getDataFromBase();
                table.refresh(inData);
            }
        });
        inData = getDataFromBase();
        table = new Table(inData,columnNames);

        this.add(cbxDis);
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
                loader.deleteTask(Integer.parseInt(table.getValueAt(table.getSelectedRow(),0).toString()));
                inData = getDataFromBase();
                table.refresh(inData); 
            }
        });
        table.addMiniButton(btnDel);
        
        table.addMiniButton(Box.createHorizontalGlue());
    }
    
    @Override
    public Object[][] getDataFromBase(){
        return loader.getDataFromBase("select task_id,name_task,descr from task where dis_id = " + cbxDis.getSelectedId(), 
                                      new String[]{"task_id","name_task","descr"});
    }
    
    private void initAddDialog(){
        JTextField txfName = new JTextField();
        JTextField txfDescr = new JTextField();
        txfName.setBorder(new TitledBorder("Наименование"));
        txfDescr.setBorder(new TitledBorder("Описание задачи"));        
        addDialog = new Dialog("Добавить задачу",400,150){
             @Override
             public void Ok(){
                 int maxInd = loader.getMaxInd("select count(*) as CNT from task");
                 loader.insertTask(maxInd+1,txfName.getText(), txfDescr.getText(),cbxDis.getSelectedId());
                 inData = getDataFromBase();
                 table.refresh(inData);  
                 addDialog.dispose();
             }
        };
        addDialog.addComponent(txfName);
        addDialog.addComponent(txfDescr);
        addDialog.addComponent(Box.createVerticalGlue());
    }
    
    
    
}
