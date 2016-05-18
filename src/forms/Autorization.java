/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import db.DataBase;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    
    private void check(String login, char[] password){
        this.setVisible(false);
        MainForm mainForm = new MainForm(1);
    }

}
