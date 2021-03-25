/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Configuration.Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import MainMenu.Form_Transaksi;

public class Game_Item {
    public String Game_ID;
    public String Game_Name;
    public String Platform;
    public String Category  = "";
    public String Publisher;
    public String Description;
    public int Stock;
    public String Supplier_ID;
    public int Price;
    public byte[] Game_Cover;
    public String FileName = null;
    public String Transaction_ID;
    public String Cart_ID;
    
    public String Transaction_Num;
    public String Payment_Date;
    public String Total_Item;
    public String Total_Price;
    
    Connect Conf_connect = new Connect();
    
    public int Insertion() {
    int i = 0;
    try {
        Conf_connect.ConnectionConn();
        String ins = "insert into Game.Games(Game_ID, Game_Name, Platform, Category, Publisher, Description, Stock, Supplier_ID, Price, Game_Cover) values(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = Conf_connect.con.prepareStatement(ins);
        
        ps.setString(1, Game_ID);
        ps.setString(2, Game_Name);
        ps.setString(3, Platform);
        ps.setString(4, Category);
        ps.setString(5, Publisher);
        ps.setString(6, Description);
        ps.setInt(7, Stock);
        ps.setString(8, Supplier_ID);
        ps.setInt(9, Price);
        ps.setBytes(10, Game_Cover);
        
        i = ps.executeUpdate();
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
    return i;
    }
    
    public int InsertionTrans() {
    int i = 0;
    try {
        Conf_connect.ConnectionConn();
        String ins = "insert into Game.Transactions(Transaction_Num,Payment_Date,Total_Item,Total_Price) values(?,?,?,?)";
        PreparedStatement ps = Conf_connect.con.prepareStatement(ins);
        
        ps.setString(1, Transaction_Num);
        ps.setString(2, Payment_Date);
        ps.setString(3, Total_Item);
        ps.setString(4, Total_Price);
        
        i = ps.executeUpdate();
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
    return i;
    }
    
    public int insertCart(){
        int i = 0;
    try {
        Conf_connect.ConnectionConn();
        String ins = "insert into Game.TransactionsID (Cart_ID) values(?)";
        PreparedStatement ps = Conf_connect.con.prepareStatement(ins);
        
        ps.setString(1, Cart_ID);
        
        i = ps.executeUpdate();
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
    return i;
    }
    
    public int Update()
    {
        int i = 0;
        try {
            Conf_connect.ConnectionConn();
            String upt = "Update Game.Games ";
            upt = upt + "set Game_Name = ?, ";
            upt = upt + "Platform = ?, ";
            upt = upt + "Category = ?, ";
            upt = upt + "Publisher = ?, ";
            upt = upt + "Description = ?, ";
            upt = upt + "Stock = ?, ";
            upt = upt + "Supplier_ID = ?, ";
            upt = upt + "Price = ?, ";
            upt = upt + "Game_Cover = ? ";
            upt = upt + "where Game_ID = ?";
            
            PreparedStatement ps = Conf_connect.con.prepareStatement(upt);
            ps.setString(1, Game_Name);
            ps.setString(2, Platform);
            ps.setString(3, Category);
            ps.setString(4, Publisher);
            ps.setString(5, Description);
            ps.setInt(6, Stock);
            ps.setString(7, Supplier_ID);
            ps.setInt(8, Price);
            ps.setBytes(9, Game_Cover);
            ps.setString(10, Game_ID);
            
            i = ps.executeUpdate();
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return i;
    }
    
     public int UpdateStock()
    {
        int i = 0;
        try {
            Conf_connect.ConnectionConn();
            String upt = "Update Game.Games ";
            upt = upt + "Set Stock = ? ";
            upt = upt + "where Game_ID = ?";
            
            PreparedStatement ps = Conf_connect.con.prepareStatement(upt);
            ps.setInt(1, Stock);
            ps.setString(2, Game_ID);
            
            i = ps.executeUpdate();
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return i;
    }
    
    public String autoGenerateID()
    {
        String code = null;
        ResultSet rs = null;
        try {
            Conf_connect.ConnectionConn();
            Statement stm = Conf_connect.con.createStatement();
            String aut = "select top 1 Game_ID from Game.Games order by Game_ID desc";
            rs = stm.executeQuery(aut);
            if (rs.next()) {
                code = rs.getString(1);
                int value = Integer.parseInt(code.substring(1));
                value++;
                if (value >= 100){
                    code = "G" + value;
                }else if (value >= 10){
                    code = "G0" + value;
                }else{
                    code = "G00" + value;
                }
            }
            else {
                code = "G001";
            }
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return code;
    }
    
     public String autoGenerateCart()
    {
        String code = null;
        ResultSet rs = null;
        try {
            Conf_connect.ConnectionConn();
            Statement stm = Conf_connect.con.createStatement();
            String aut = "select top 1 Cart_ID from Game.TransactionsID order by Cart_ID desc";
            rs = stm.executeQuery(aut);
            if (rs.next()) {
                code = rs.getString(1);
                int value = Integer.parseInt(code.substring(1));
                value++;
                if (value >= 100){
                    code = "C" + value;
                }else if (value >= 10){
                    code = "C0" + value;
                }else{
                    code = "C00" + value;
                }
            }
            else {
                code = "C001";
            }
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return code;
    }
     
       public String autoDecrease()
    {
        Form_Transaksi ft = new Form_Transaksi();
        String code = null;
        ResultSet rs = null;
        try {
            Conf_connect.ConnectionConn();
            Statement stm = Conf_connect.con.createStatement();
            String aut = ft.getDataDecrease();
            rs = stm.executeQuery(aut);
            if (rs.next()) {
                code = rs.getString(1);
                int value = Integer.parseInt(code.substring(1));
                value++;
                if (value <= 100){
                    code = "TN" + value;
                }else if (value <= 10){
                    code = "TN0" + value;
                }else{
                    code = "TN00" + value;
                }
            }
            else {
                code = "T001";
            }
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return code;
    }
     
     public String autoGenerateTransNum()
    {
        String code = null;
        ResultSet rs = null;
        try {
            Conf_connect.ConnectionConn();
            Statement stm = Conf_connect.con.createStatement();
            String aut = "select top 1 Transaction_Num from Game.Transactions order by Transaction_Num desc";
            rs = stm.executeQuery(aut);
            if (rs.next()) {
                code = rs.getString(1);
                int value = Integer.parseInt(code.substring(1));
                value++;
                if (value >= 100){
                    code = "T" + value;
                }else if (value >= 10){
                    code = "T0" + value;
                }else{
                    code = "T00" + value;
                }
            }
            else {
                code = "T001";
            }
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return code;
    }
     
     public int DeleteResi(){
        int i = 0;
        try {
            Conf_connect.ConnectionConn();
            String del = "delete from Game.TransactionsID where Transaction_ID  = ?";
            PreparedStatement ps = Conf_connect.con.prepareStatement(del);
            
            ps.setString(1, Game_ID);
            
            i = ps.executeUpdate();
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return i;
    }
     
    
    public int Delete()
    {
        int i = 0;
        try {
            Conf_connect.ConnectionConn();
            String del = "Delete from Game.Games where Game_ID = ?";
            PreparedStatement ps = Conf_connect.con.prepareStatement(del);
            
            ps.setString(1, Game_ID);
            
            i = ps.executeUpdate();
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return i;
    }
}
