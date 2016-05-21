/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author VIC
 */
public class TableModel extends AbstractTableModel {
    private Object[] inData;
    private Object[] columns;
    
    public TableModel(Object[] inData, Object[] columns){
        this.inData = inData;
        this.columns = columns;
    }

    @Override
    public int getRowCount() {
        return inData.length/columns.length;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return inData[rowIndex*columnIndex];
    }
    
    
    
}
