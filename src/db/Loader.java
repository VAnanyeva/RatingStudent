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
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ВИКА
 */
public class Loader {
    
    public Loader(){
        
    }
        
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
    
    public Object[] getObjects(String SqlText){
        ArrayList<Object> list = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery(SqlText);
            while (rs.next()){
                     list.add(rs.getObject("TEXT"));  
            }
        } catch (SQLException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list.toArray();
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
    
    public void insertTask(int task_id, String name_task, String descr, int dis_id){
        try {
            statement.execute("insert into task (task_id,name_task,descr,dis_id) " +
                                                   " values ("+task_id+",'"+name_task+"','"+descr+"',"+dis_id+")");
        } catch (SQLException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteTask(int task_id){
        try {
            statement.execute("delete from task where task_id = "+task_id);
        } catch (SQLException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public TreeMap<Integer,String> getMapForComboBox (String SqlText){
        TreeMap<Integer,String> result = new TreeMap<>();
        try {
            ResultSet rs = statement.executeQuery(SqlText);
 
            while (rs.next()){
               result.put(rs.getInt("ID"), rs.getString("TEXT"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int getMaxInd(String SqlText) {
        int result = 0;
        try {
            ResultSet rs = statement.executeQuery(SqlText);
            
            while (rs.next()){
               result = rs.getInt("CNT");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    

    
    
}
