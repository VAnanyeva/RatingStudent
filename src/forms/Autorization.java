/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import db.DataBase;
import static db.DataBase.statement;
import db.Loader;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Victoria
 */
public class Autorization extends JFrame{
    private JTextField txfLogin = new JTextField();
    private JPasswordField txfPassword = new JPasswordField();

    
    public Autorization(){
        super();
        init();
        
    }
    
    private void check(String login, char[] password){
        int usr_id = -1;
        char[] pass = null;
        try {
            ResultSet rs = statement.executeQuery("SELECT USER_ID,PASSWORD FROM USER WHERE LOGIN = '" + login+"'");
 
            while (rs.next()){
               usr_id = rs.getInt("USER_ID");
               Object c = rs.getObject("PASSWORD");
               pass = c.toString().toCharArray();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        if ((usr_id != -1)&&(Arrays.toString(pass).equals(Arrays.toString(password)))){
            this.setVisible(false);
            MainForm mainForm = new MainForm(usr_id);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Неверный пароль!");
        }
    }

    private void init() {
        this.setTitle("Авторизация");
        DataBase.conn();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel lblKey = new JLabel();
        lblKey.setIcon(new ImageIcon(getClass().getResource("/img/key.png"))); 
             
        JLabel lblLogin = new JLabel();
        lblLogin.setText("Пользователь");
        JLabel lblPassword = new JLabel();
        lblPassword.setText("Пароль");
        
        JButton btnCheck = new JButton("Войти");
        btnCheck.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                check(txfLogin.getText(),txfPassword.getPassword());      
            }
        });

        panel.add(lblKey);
        panel.add(lblLogin);
        panel.add(txfLogin);
        panel.add(lblPassword);
        panel.add(txfPassword);
        panel.add(btnCheck);
        panel.add(Box.createVerticalStrut(500));
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.isMaximumSizeSet();
        this.setSize(400, 250);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.add(panel);
    }
    



}
