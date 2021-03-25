
package Configuration;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;


public class Connect {
    public Connection con;
    
    public void ConnectionConn (){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url ="jdbc:sqlserver://localhost:1433;databaseName=GameStore;integratedSecurity=true";
            String Username ="";
            String Password ="";
            con = DriverManager.getConnection(url,Username,Password);
            System.out.println("succes yee");
            Statement st = con.createStatement();
        }
        catch (Exception ex)
                {
            ex.printStackTrace();
        }
        
        
    }
    
}
