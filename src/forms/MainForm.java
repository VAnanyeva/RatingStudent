/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import components.Link;
import components.DisciplinePanel;
import components.JournalPanel;
import components.RatingPanel;
import components.TaskPanel;
import entitys.User;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Victoria
 */
public class MainForm extends JFrame{
    private int usr_id = -1;
    private DisciplinePanel pnlDiscipline;
    private TaskPanel pnlTask;
    private JournalPanel pnlJournal;
    private RatingPanel pnlRating;
    
    public MainForm(){
        init();
    }
    
    public MainForm(int usr_id){
        this.usr_id = usr_id;
        init();
    }
    
    private void init(){
        this.setTitle("Главная форма");
        User user = new User(usr_id);
        JPanel panel = new JPanel(new BorderLayout());
        JPanel pnlNorth = new JPanel();
        pnlNorth.setLayout(new BoxLayout(pnlNorth, BoxLayout.X_AXIS));
        JLabel lblUser = new JLabel();
        lblUser.setIcon(new ImageIcon(getClass().getResource("/img/teacher.png"))); 
  
        
        JLabel lblFio = new JLabel();
        lblFio.setText("Пользователь: " + user.getFIO());
        pnlNorth.add(lblUser);
        pnlNorth.add(lblFio);
        panel.add(pnlNorth, BorderLayout.NORTH);
        JPanel pnlEast = new JPanel();
        JPanel pnlWest = new JPanel();
        pnlWest.setLayout(new BoxLayout(pnlWest, BoxLayout.Y_AXIS));
        pnlWest.setPreferredSize(new Dimension (150,100));
        Link linkDisc = new Link("discipline","Дисциплины");
        linkDisc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 pnlEast.removeAll();
                 pnlDiscipline = new DisciplinePanel();
                 pnlEast.add(pnlDiscipline);
                 pnlEast.repaint();
                 pnlEast.revalidate();
            }
        });
        Link linkTask = new Link("task","Задачи");
        linkTask.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 pnlEast.removeAll();
                 pnlTask = new TaskPanel();
                 pnlEast.add(pnlTask);
                 pnlEast.repaint();
                 pnlEast.revalidate();
            }
        });
        Link linkJornal = new Link("journal","Журнал");
        linkJornal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 pnlEast.removeAll();
                 pnlJournal = new JournalPanel();
                 pnlEast.add(pnlJournal);
                 pnlEast.repaint();
                 pnlEast.revalidate();
            }
        });
        Link linkRating = new Link("rating","Рейтинг");
        linkRating.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 pnlEast.removeAll();
                 pnlRating = new RatingPanel();
                 pnlEast.add(pnlRating);
                 pnlEast.repaint();
                 pnlEast.revalidate();
            }
        });
        pnlWest.add(Box.createVerticalStrut(10));
        pnlWest.add(linkDisc);
        pnlWest.add(linkTask);
        pnlWest.add(linkJornal);
        pnlWest.add(linkRating);
        pnlWest.add(Box.createGlue());
        panel.add(pnlWest, BorderLayout.WEST);
        
        
        pnlDiscipline = new DisciplinePanel();
        pnlEast.add(pnlDiscipline);

        
        panel.add(pnlEast, BorderLayout.EAST);
        
        
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.isMaximumSizeSet();
        this.setSize(1000, 600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
      
}
