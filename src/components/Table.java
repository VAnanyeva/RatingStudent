/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author VIC
 */
public class Table extends JPanel {
    private AbstractTableModel model;
    private JTable table;
    private JPanel buttons;
    private Object[][] inData;
    private Object[] columns;
    
    public Table(){
        super();
        init();
    }
    
    public Table(Object[][] inData, Object[] columns){
        super();
        this.inData = inData;
        this.columns = columns;
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
    
}
