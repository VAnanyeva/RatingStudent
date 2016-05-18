/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Victoria
 */
public class DisciplinePanel extends JPanel{
    private JTable table;
    private AbstractTableModel model;
    public DisciplinePanel (){
//         model = new AbstractTableModel()/*new Object[][] {{"1","Математика","256"},
//                {"2","Русский язык","150"}}, 
//                
//                new Object[]{"№","Наименование","Кол-во часов"}) */{
//
//             @Override
//             public int getRowCount() {
//                 return 2;
//             }
//
//             @Override
//             public int getColumnCount() {
//                 return 3;
//             }
//
//             @Override
//             public Object getValueAt(int rowIndex, int columnIndex) {
//                 
//             }
//         };
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane();
        scroll.add(table);
        add(table);
    }
}
