/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magnumopusms;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author A100519
 */
public class dbConnection {
    public static Connection getConnection(){
     
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/magnumopus_db", "root", "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        return con;
    }
}
