/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import db.Loader;
import entitys.User;
import forms.Dialog;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author ВИКА
 */
public class RatingPanel  extends JPanel implements TableInterface{
    private final Loader loader = new Loader();
    private Table table;
    private Object[][] inData = new Object[][] {{"1","2","3"},{"4","5","6"},{"7","8","9"}};
    private final Object[] columnNames = new Object[] {"Студент","Коэффициент"};

    public RatingPanel(){
        init();
    }

    private void init() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(new TitledBorder("Рейтинг студентов"));
        inData = getDataFromBase();
        table = new Table(inData,columnNames);

        this.add(table);
    }
    
    @Override
    public Object[][] getDataFromBase(){
        return loader.getDataFromBase("select student.fio as fio, SUM(journal.value)/COUNT(journal.value) as val  from student,journal " +
                                        " where journal.student_id = student.student_id group by student.fio order by val desc" , 
                                        new String[]{"fio","val"});
    }


    
    
    
}

