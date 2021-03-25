/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.PreparedStatement;
import Configuration.Connect;

/**
 *
 * @author ASUS
 */
public class Suppliers {
    public String Suppliers_ID;
    public String Suppliers_Name;
    public String Suppliers_Email;
    
    Connect obj_Connect = new Connect();
    
    public int doDelete() {
        int i = 0;
        try{
            obj_Connect.ConnectionConn();
            String str = "delete from Game.Supplier where Supplier_ID = ?";
            PreparedStatement pr = obj_Connect.con.prepareStatement(str);
            
            pr.setString(1,Suppliers_ID);
            
            i= pr.executeUpdate();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
             }
        return i;
    }
    
}
