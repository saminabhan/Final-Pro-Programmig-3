/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modul;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class DB {
    
    public static DB dbc = null;

    DB() {

    }

    public static DB getInstance() {
        if (dbc == null) {
            dbc = new DB();
            return dbc;
        } else {
            return dbc;

        }
    }
    
    
    public Connection getConnection() throws SQLException, ClassNotFoundException{
         
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_appointments" ,"root" ,"");
    return conn;
    }
    
}
