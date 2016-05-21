/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import components.Link;
import components.DisciplinePanel;
import entitys.User;
import java.awt.BorderLayout;
import java.awt.Dimension;
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
        if (user.isAdmin()){
            lblUser.setIcon(new ImageIcon(getClass().getResource("/img/Admin.png"))); 
        }
        else{
            lblUser.setIcon(new ImageIcon(getClass().getResource("/img/teacher.png"))); 
        }
        
        JLabel lblFio = new JLabel();
        lblFio.setText("Пользователь: " + user.getFIO());
        pnlNorth.add(lblUser);
        pnlNorth.add(lblFio);
        panel.add(pnlNorth, BorderLayout.NORTH);
        
        JPanel pnlWest = new JPanel();
        pnlWest.setLayout(new BoxLayout(pnlWest, BoxLayout.Y_AXIS));
        pnlWest.setPreferredSize(new Dimension (150,100));
        Link linkDisc = new Link("/img/Admin.png","Дисциплины");
        Link linkTask = new Link("/img/Admin.png","Задачи");
        Link linkJornal = new Link("/img/Admin.png","Журнал");
        Link linkRating = new Link("/img/Admin.png","Рейтинг");
        pnlWest.add(linkDisc);
        pnlWest.add(linkTask);
        pnlWest.add(linkJornal);
        pnlWest.add(linkRating);
        pnlWest.add(Box.createGlue());
        panel.add(pnlWest, BorderLayout.WEST);
        
        JPanel pnlEast = new JPanel();
        DisciplinePanel pnlDiscipline = new DisciplinePanel();
        pnlEast.add(pnlDiscipline);
        //pnlEast.setPreferredSize(new Dimension(1000,700));
        panel.add(pnlEast, BorderLayout.EAST);
        
        
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.isMaximumSizeSet();
        this.setSize(1000, 600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void generateLinks() {
        //Object[] captionLinks = new Object[]{""};
        
    }
    
    
    
    
}
