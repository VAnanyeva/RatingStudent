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
public class JournalPanel  extends JPanel implements TableInterface {
    private final Loader loader = new Loader();
    private EditTable table;
    private Object[][] inData = new Object[][] {{"1","2","3"},{"4","5","6"},{"7","8","9"}};
    private Object[] columnNames = new Object[] {};
    private ComboBox cbxDis;
    private TreeMap<Integer,String> disciplines = new TreeMap<>();
    private JPanel pnlTable = new JPanel();
    Object[] fioIds = new Object[]{};
    Object[] fioNames = new Object[]{};
    Object[] taskIds = new Object[]{};

    public JournalPanel (){
        super();
        init();
    }

    private void init() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new TitledBorder("Журнал"));
        
        disciplines = loader.getMapForComboBox("select dis_id as id,name_dis as text from discipline where user_id = "+User.usr_id);
        cbxDis = new ComboBox(disciplines,"Дисциплины");
        cbxDis.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                updateTable();
            }
        });
        
        pnlTable = new JPanel();
        columnNames = getColumnNames();
        inData = getDataFromBase();
        
        table = new EditTable(inData,columnNames,fioIds,taskIds,cbxDis.getSelectedId());

        this.add(cbxDis);
        pnlTable.add(table);
        this.add(pnlTable);
        
    }
    
    @Override
    public Object[][] getDataFromBase(){
        fioIds = loader.getObjects("select student_id as text from student");
        fioNames = loader.getObjects("select fio as text from student");
        taskIds = loader.getObjects("select task_id as text from task where dis_id = " + cbxDis.getSelectedId());
        Object[][] temp =  loader.getDataFromBase("select student.fio as fio,journal.student_id as stud_id,journal.task_id as task_id,journal.dis_id as dis_id, journal.value as value from journal,student where journal.student_id = student.student_id and journal.dis_id = " + cbxDis.getSelectedId(), 
                                      new String[]{"fio","stud_id","task_id","dis_id","value"});
        Object[][] result = new Object[fioNames.length][columnNames.length];
        for (int i=0; i<fioNames.length; i++){
             result[i][0] = fioNames[i];
             for (int j=1; j<columnNames.length; j++){
                  result[i][j] = "";
             }
        }
        
        for (int i=0; i<temp.length; i++){
                  int row = 0,col = 0;
                  int stud_id = Integer.parseInt(temp[i][1].toString());
                  int task_id = Integer.parseInt(temp[i][2].toString());
                  for (int j=0; j<fioIds.length; j++){
                       if (stud_id == Integer.parseInt(fioIds[j].toString())){
                           row = j;
                       }
                  }
                  for (int j=0; j<taskIds.length; j++){
                       if (task_id == Integer.parseInt(taskIds[j].toString())){
                           col = j + 1;
                       }
                  }
                  result[row][col] = temp[i][4];
       
        }
        
        return result;
    }
    
    public Object[] getColumnNames(){
        return loader.getObjects("SELECT 'Студент' AS TEXT FROM DUAL union SELECT NAME_TASK AS TEXT FROM TASK WHERE DIS_ID = " + cbxDis.getSelectedId());
    }
    
    private void updateTable(){
        pnlTable.removeAll();
        columnNames = getColumnNames();
        inData = getDataFromBase();
        table = new EditTable(inData,columnNames,fioIds,taskIds,cbxDis.getSelectedId());
        
        pnlTable.add(table);
        pnlTable.repaint();
        pnlTable.revalidate();
    }

}
