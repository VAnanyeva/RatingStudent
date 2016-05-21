/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victoria
 */
public class DataBase {
    public static Connection connect;
    public static Statement statement;
    
    public static void conn (){
        try {    
            String host = "jdbc:mysql://localhost:3306/stud?zeroDateTimeBehavior=convertToNull";
            String uName = "root";
            String uPass = "mysql";

            connect = DriverManager.getConnection(host, uName, uPass);
            statement = connect.createStatement();
        }
        catch ( SQLException err ) {
            System.out.println( err.getMessage( ) );
        }

    }
    

}
