/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Zzall
 */
public class Game {
 private String Game_ID;
 private String Game_Name;
 private String Platform;
 private String Category;
 private String Publisher;
 private String Description;
 private int Stock;
 private String Supplier_ID;
 private int Price;
 private byte[] Game_Cover; 
 
 private String Trans_Num;
 private String Payment_Date ;
 private int Total_Item;
 private int Tota_Price;

    public Game(String Game_ID, String Game_Name, String Platform, String Category, String Publisher, String Description, int Stock, String Supplier_ID, int Price, byte[] Game_Cover) {
        this.Game_ID = Game_ID;
        this.Game_Name = Game_Name;
        this.Platform = Platform;
        this.Category = Category;
        this.Publisher = Publisher;
        this.Description = Description;
        this.Stock = Stock;
        this.Supplier_ID = Supplier_ID;
        this.Price = Price;
        this.Game_Cover = Game_Cover;
    }

    public Game(String Trans_Num, String Payment_Date, int Total_Item, int Tota_Price) {
        this.Trans_Num = Trans_Num;
        this.Payment_Date = Payment_Date;
        this.Total_Item = Total_Item;
        this.Tota_Price = Tota_Price;
    }

    public String getTrans_Num() {
        return Trans_Num;
    }

    public String getPayment_Date() {
        return Payment_Date;
    }

    public int getTotal_Item() {
        return Total_Item;
    }

    public int getTota_Price() {
        return Tota_Price;
    }

    public String getGame_ID() {
        return Game_ID;
    }

    public String getGame_Name() {
        return Game_Name;
    }

    public String getPlatform() {
        return Platform;
    }

    public String getCategory() {
        return Category;
    }

    public String getPublisher() {
        return Publisher;
    }

    public String getDescription() {
        return Description;
    }

    public int getStock() {
        return Stock;
    }

    public String getSupplier_ID() {
        return Supplier_ID;
    }

    public int getPrice() {
        return Price;
    }

    public byte[] getGame_Cover() {
        return Game_Cover;
    }
         
         
}
