/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import static db.DataBase.statement;
import db.Loader;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ВИКА
 */
public class EditTable  extends JPanel {
    private AbstractTableModel model;
    private JTable table;
    private JPanel buttons;
    private Object[][] inData;
    private Object[] columns;
    private Object[] studIds;
    private Object[] taskIds;
    private int dis_id;
    
    public EditTable(){
        super();
        init();
    }
    
    public EditTable(Object[][] inData, Object[] columns){
        super();
        this.inData = inData;
        this.columns = columns;
        
        
        init();
    }
    
    public EditTable(Object[][] inData, Object[] columns, Object[] studIds, Object[] taskIds, int dis_id){
        super();
        this.inData = inData;
        this.columns = columns;
        this.studIds = studIds;
        this.taskIds = taskIds;
        this.dis_id = dis_id;
        init();
    }

    private void init() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));

        model = new AbstractTableModel(){
            @Override
            public int getRowCount() {
                return inData.length;
            }

            @Override
            public int getColumnCount() {
                return columns.length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                 return inData[rowIndex][columnIndex];
            }  
            
            @Override
            public String getColumnName(int column) {
                 return columns[column].toString();
            }
            
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex != 0;
            }

            @Override
            public void setValueAt(Object value, int row, int col) {
                inData[row][col] = value;
                int task_id = Integer.parseInt(taskIds[col-1].toString());
                int stud_id = Integer.parseInt(studIds[row].toString());
                
                update(task_id, stud_id, dis_id, Integer.parseInt(value.toString()));

                fireTableCellUpdated(row, col);
            }
        };
        
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(800,450));
        
        add(buttons);
        
        add(scroll);
    }
    
    public void addMiniButton(Component button){
        buttons.add(button);
    }
    
    public void refresh(Object[][] inData){
        this.inData = inData;
        for (int i=0; i<inData.length; i++){
            for (int j=0; j<columns.length; j++){
                table.setValueAt(inData[i][j], i, j);
            }
        }
        table.revalidate();
        table.repaint();
        ((AbstractTableModel)table.getModel()).fireTableDataChanged();
    }
    
    public int getSelectedRow(){
        return table.getSelectedRow();
    }
    
    public int getSelectedColumn(){
        return table.getSelectedColumn();
    }
    
    public Object getValueAt(int row, int column){
        return table.getValueAt(row, column);
    }
    
    public void setEditingColumn(int column){
        table.setEditingColumn(column);
    }
    
        
    private void update(int task_id, int stud_id, int dis_id, int value){
        try {
            boolean exists = false;
            ResultSet rs = statement.executeQuery("SELECT task_id, dis_id, student_id FROM journal where task_id = "+task_id+" AND dis_id = "+dis_id+" AND student_id = "+stud_id);

                if (rs.next()){
   
                        exists = true;
                    
                }
          
            
            if (!exists){
              statement.execute("INSERT INTO journal (task_id, dis_id, student_id, value) "+
                                "VALUES ("+task_id+","+dis_id+","+stud_id+","+value+")");
            }
            else{
                statement.execute("UPDATE journal SET VALUE = "+value+
                                   " WHERE  task_id = "+task_id+" AND dis_id = "+dis_id+" AND student_id = "+stud_id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
