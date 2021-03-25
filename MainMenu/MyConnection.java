/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainMenu;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ASUS
 */
public class MyConnection {
    public static Connection getConnection(){
    Connection con=null;
        try {
              Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
              con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=GameStore;integratedSecurity=true");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return con;
}
}
