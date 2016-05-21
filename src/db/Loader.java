/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import static db.DataBase.statement;
import entitys.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ВИКА
 */
public class Loader {
    
    public Loader(){
        
    }
        
//    public Object[][] getDataFromBase(String SqlText, String[] fields, boolean b){
//        Object[][] result = null;
//        try {
//            ResultSet rs = statement.executeQuery(SqlText);
//            System.out.println(rs.getFetchSize());
//            result = new Object[3][3];
//            while (rs.next()){
//                for (String field: fields){
//                     int row = rs.getRow()-1;
//                     int col = rs.findColumn(field)-1;
//                     
//                     result[row][col] = rs.getObject(field);
//                     
//                }
//        }
//        } catch (SQLException ex) {
//            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return result;
//    }
    
    public Object[][] getDataFromBase(String SqlText, String[] fields){
        ArrayList<Object> list = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery(SqlText);
 
            while (rs.next()){
               for (String field: fields){
                     list.add(rs.getObject(field));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int cntRow = list.size()/fields.length;
        Object[][] result = new Object[cntRow][fields.length];
        int c = 0;
        for (int i=0; i<cntRow; i++){
            for (int j=0; j<fields.length; j++){
                 result[i][j] = list.get(c);
                 c++;
            }
        }
        return result;
    }
    
    public void insertDistipline(int dis_id, String name_dis, int count_hours){
        try {
            statement.execute("insert into discipline (dis_id,NAME_DIS,num_hours,user_id) " +
                                                   " values ("+dis_id+",'"+name_dis+"',"+count_hours+","+User.usr_id+")");
 
            
        } catch (SQLException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteDiscipline(int dis_id){
        try {
            statement.execute("delete from discipline where dis_id = "+dis_id);
 
            
        } catch (SQLException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
