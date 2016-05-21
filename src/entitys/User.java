/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import static db.DataBase.statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victoria
 */
public class User {
    public static int usr_id;
    private String fio;
    private String login;
    private boolean admin;
    
    public User(int usr_id){
        this.usr_id = usr_id;
        fill();
    }
    
    private void fill(){
        try {
            ResultSet rs = statement.executeQuery("SELECT FIO, ADMIN from STUD.USER WHERE USER_ID = "+usr_id);
            while (rs.next()){
            fio = rs.getString("FIO");
            admin = (rs.getInt("ADMIN")==1);
        }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public String getFIO(){
        return fio;
    }
    
    public Boolean isAdmin(){
        return admin;
    }
}
