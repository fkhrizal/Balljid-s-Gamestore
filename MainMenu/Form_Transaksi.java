/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainMenu;

import java.util.ArrayList;
import Configuration.Connect;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Entities.Game;
//import com.sun.imageio.plugins.png.RowFilter;
import javax.swing.RowFilter;
import java.awt.Image;
import java.sql.PreparedStatement;
import java.util.Enumeration;
import java.util.List;
import javax.management.Query;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableRowSorter;
import Entities.Game_Item;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.Icon;

public class Form_Transaksi extends javax.swing.JFrame {
Connect conn = new Connect();
Game_Item GI =new Game_Item();

DefaultTableModel dm;
    /**
     * Creates new form Form_Transaksi
     */
    public Form_Transaksi() {
        initComponents();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        Display();
        DisplayTrans();
        autoCartID();
        autoTransNum();
        tanggal();
    }
    
    
    public ArrayList<Game> TransList(){
        ArrayList<Game> transList = new ArrayList ();
        try {
            conn.ConnectionConn();
            String transs= "Select*From Game.Transactions";
            Statement stm = conn.con.createStatement();
            ResultSet rts = stm.executeQuery(transs);
            Game trans;
            while (rts.next()){
                trans = new Game(rts.getString("Transaction_Num"), rts.getString("Payment_Date"),rts.getInt("Total_Item"),rts.getInt("Total_Price"));
                transList.add(trans);
                }
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex);
        }
        return transList;
    }
    
    public ArrayList<Game> GameList(){
        ArrayList<Game> gameList = new ArrayList ();
        try {
            conn.ConnectionConn();
            String games= "Select*From Game.Games";
            Statement stm = conn.con.createStatement();
            ResultSet rts = stm.executeQuery(games);
            Game game;
            while (rts.next()){
                game = new Game(rts.getString("Game_ID"), rts.getString("Game_Name"),rts.getString("Platform"),rts.getString("Category"),rts.getString("Publisher"),rts.getString("Description"),rts.getInt("Stock"),rts.getString("Supplier_ID"),rts.getInt("Price"),rts.getBytes("Game_Cover"));
                gameList.add(game);
                }
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex);
        }
        return gameList;
    }

    public void autoCartID(){
        lb_resi.setText(GI.autoGenerateCart());
    }
    
    public  void autoTransNum(){
        lb_resNum.setText(GI.autoGenerateTransNum());
    }
    
     public  void autoDecrease(){
        lb_Decrease.setText(GI.autoDecrease());
    }
      private void GetDataStock()
    {
        try{
            GI.Game_ID = lb_GameI_ID.getText();
            GI.Game_Name = lb_Game_Name.getText();
            GI.Platform = lb_platform.getText();
            GI.Category = lb_Game_Name.getText();
            GI.Publisher = lb_Publisher1.getText();
            GI.Description = lb_Description.getText();
            GI.Supplier_ID = lb_sup.getText();
            //
                try {
            Icon icons = M.getIcon();
            BufferedImage bi = new BufferedImage(icons.getIconWidth(), icons.getIconHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics g = bi.createGraphics();
            icons.paintIcon(null, g, 0, 0);
            g.dispose();

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", os);
            InputStream fis = new ByteArrayInputStream(os.toByteArray());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
                System.out.println("read " + readNum + " bytes,");
                }
             byte[] bytes = bos.toByteArray();
                byte[] photo = bytes;
        } catch (IOException d) {
            JOptionPane.showMessageDialog(rootPane, d);
        }
                
           // GI.Game_Cover = Byte.parseByte(M.getText());
           GI.Stock = Integer.parseInt(lb_getStock.getText());
        }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }
    }
      public void getTotal(){
          String text = lb_total.getText();
      } 
      
     private void UpdatedataStock()
    {
         this.GetDataStock();
        int valid =0;
        if (valid == 0){
            int i = GI.UpdateStock();
            if (i == 0){
               // JOptionPane.showMessageDialog(null, "Data can't be Updated");
            }
            else {
             //   JOptionPane.showMessageDialog(null, "Data Updated");
            }
        }
    }   
         
     private void InsertTransact()
    {
       
        this.GetDataTrans();
        int valid = 0;
        if(valid == 0)
        {
           int i = GI.InsertionTrans();
            if (i == 0) {
                JOptionPane.showMessageDialog(null, "Data can't be inputted");
            }
            else {
                JOptionPane.showMessageDialog(null, "Data inputted");
            }
            
        }
    }
     
     private void GetDataTrans(){
        
        try{
            GI.Transaction_Num = lb_resNum.getText();
            GI.Payment_Date = lb_date.getText();
            GI.Total_Item = lb_totTem.getText();
            GI.Total_Price = lb_total.getText();
            
        }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }
    }
    private void GetDataResi()
    {
        try{
            GI.Cart_ID = lb_resi.getText();
        }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }
    }
    public void SaveFromTabletoDatabase (){
        
        int rows = tb_Keranjang.getRowCount();

       
       for(int row = 0; row<rows ; row++)
       {

       String Cart_ID = (String) tb_Keranjang.getValueAt(row, 0);
       String Trans_Num = (String) tb_Keranjang.getValueAt(row, 1);
       String Game_ID = (String) tb_Keranjang.getValueAt(row, 2);
       



         String pass = (String) tb_Keranjang.getValueAt(row, 3);
        try{
         conn.ConnectionConn();
         String query = "insert into Game.Cart(Cart_ID,Transaction_Num,Game_ID) values(?,?,?)" ;


        PreparedStatement stmt = conn.con.prepareStatement(query);
        stmt.setString(1, Cart_ID); //Invoice No
        stmt.setString(2, Trans_Num); //Code
        stmt.setString(3, Game_ID); //Description


        stmt.addBatch();
       stmt.executeBatch();
        conn.con.commit();
            }

            catch(Exception ex)
            {
             JOptionPane.showMessageDialog(null, "Cannot save. "+ ex);
               }    
           }

    }
     private void InsertResi()
    {
        this.GetDataResi();
        int valid = 0;
        if(valid == 0)
        {
            int i = GI.insertCart();
            
        }
    }
    
    public void SendToTable (){
    String Game_id = lb_GameI_ID.getText();
    String Game_name = lb_Game_Name.getText();
    String Publisher = total.getText();
    String Price = lb_Price.getText();
    String Resi = lb_resi.getText();
    String ResiNum = lb_resNum.getText();
    
    String Description = lb_Description.getText();
    
    
    if (rbPC.isSelected()) {
        lb_Platform.setText("PC");
    }
    if (rbPSV.isSelected()) {
        lb_Platform.setText("PS Vita");
    }
    if (rbXBO.isSelected()) {
        lb_Platform.setText("XBOX One");
    }
    if (rbSwitch.isSelected()) {
        lb_Platform.setText("Switch");
    }
    if (rbNDS.isSelected()) {
        lb_Platform.setText("NDS");
    }
    if (rbPS4.isSelected()) {
        lb_Platform.setText("PS4");
    }
    if (rb3DS.isSelected()) {
        lb_Platform.setText("3DS");
    }
    
    String Platform = lb_Platform.getText();
    Object[] row = { Resi,ResiNum, Game_id, Game_name, Platform, Publisher,Description,Price };

    DefaultTableModel model = (DefaultTableModel) tb_Keranjang.getModel();

    model.addRow(row);

    }
    public String getDataDecrease(){
        lb_resi.getText();
       return getDataDecrease();
    }
    private void DeleteData() {
        GI.Game_ID = lb_Decrease.getText();
        int i = GI.DeleteResi();
        if (i == 0) {
            JOptionPane.showMessageDialog(null, "Data can't be Deleted");
        }
        else {
           JOptionPane.showMessageDialog(null, "Data Deleted");
        }
    }
    
    public void removeFromTable (){
       DefaultTableModel model = (DefaultTableModel) tb_Keranjang.getModel();   
    model.removeRow(model.getRowCount() - 1);
    }
    
    public void DecreaseStock(){       
        int setok = Integer.parseInt(lb_getStock.getText());
        int satu = Integer.parseInt(lb_satu.getText());
        
        String  hasil = String.valueOf(setok-satu);  
        lb_getStock.setText(hasil);
    }
     public void increaseStock(){       
        int setok = Integer.parseInt(lb_getStock.getText());
        int satu=Integer.parseInt(lb_satu.getText());
        
        String  hasil = String.valueOf(setok+satu);  
        lb_getStock.setText(hasil);
    }
    
   public void tableChanged(TableModelEvent e){
       if (e.getType() == TableModelEvent.INSERT){
           
           int row = tb_Keranjang.getRowCount();
           int total=0;
             
           for (int i = 0; i <= row; i++){
               total += (Integer)tb_Keranjang.getValueAt(i,5);
               
           }
           String totalResult;
            totalResult = Integer.toString(total); // String totalResult declared as instance variable
            System.out.println(totalResult);
           
        }
   }
   
   
    public int stockvalid() {
        int valid = 0;
        int stock = Integer.parseInt(lb_getStock.getText());
        if (stock <= 0) {
            valid = 1;
        }
        return valid;
    }
   
    public void addItem() {
        int totalitem = Integer.parseInt(lb_totTem.getText());
        String totem = String.valueOf(totalitem + 1);
        lb_totTem.setText(totem);
    }
    
    public void decreaseItem() {
        int totalitem = Integer.parseInt(lb_totTem.getText());
        String totem = String.valueOf(totalitem - 1);
        lb_totTem.setText(totem);
    }
   
   public void totalInc(){
       int harga = Integer.parseInt(lb_Price.getText());
       int hasil = Integer.parseInt(lb_total.getText());
       
       String total = String.valueOf(hasil+harga);
       lb_total.setText(total);
   }
   
    public void totalDec(){
       int harga = Integer.parseInt(lb_Price.getText());
       int hasil = Integer.parseInt(lb_total.getText());
       
       String total = String.valueOf(hasil-harga);
       lb_total.setText(total);
   }
    
    private void refund(){
        int total  = Integer.parseInt(lb_total.getText());
        int Bayar = Integer.parseInt(tx_pay.getText());
        String Kembali = String.valueOf(Bayar-total);
        lb_refund.setText(Kembali);
                }
            
   public void DisplayTrans(){
        ArrayList<Game> list = TransList();
        dm = (DefaultTableModel) tb_Trans.getModel();
        Object[] Column = new Object[4];
        for (int i= 0; i<list.size(); i++){
            Column[0] = list.get(i).getTrans_Num();
            Column[1] = list.get(i).getPayment_Date();
            Column[2] = list.get(i).getTotal_Item();
            Column[3] = list.get(i).getTota_Price();
            dm.addRow(Column);
                    }
    }
   
   public void tanggal(){
       Date d = new Date();
       SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
       lb_date.setText(s.format(d));
       
   }
    
    public void Display(){
        ArrayList<Game> list = GameList();
        dm = (DefaultTableModel) Tb_Games.getModel();
        Object[] Column = new Object[10];
        for (int i= 0; i<list.size(); i++){
            Column[0] = list.get(i).getGame_ID();
            Column[1] = list.get(i).getGame_Name();
            Column[2] = list.get(i).getPlatform();
            Column[3] = list.get(i).getCategory();
            Column[4] = list.get(i).getPublisher();
            Column[5] = list.get(i).getDescription();
            Column[6] = list.get(i).getStock();
            Column[7] = list.get(i).getSupplier_ID();
            Column[8] = list.get(i).getStock();
            Column[9] = list.get(i).getPrice();
            dm.addRow(Column);
                    }
    }
    public void searchData(String query){
       TableRowSorter<DefaultTableModel>  tr= new TableRowSorter<DefaultTableModel>(dm);
       Tb_Games.setRowSorter(tr);
       tr.setRowFilter(RowFilter.regexFilter(query));
       
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup2 = new javax.swing.ButtonGroup();
        lb_Platform = new javax.swing.JLabel();
        lb_resi = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbl_Title2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        lb_Game_Name = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        rbNDS = new javax.swing.JRadioButton();
        rbPC = new javax.swing.JRadioButton();
        lb_Description = new javax.swing.JLabel();
        rbPSV = new javax.swing.JRadioButton();
        rbPS4 = new javax.swing.JRadioButton();
        jSeparator3 = new javax.swing.JSeparator();
        lb_refund = new javax.swing.JLabel();
        lb_total = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_Keranjang = new javax.swing.JTable();
        lb_Price = new javax.swing.JLabel();
        rbXBO = new javax.swing.JRadioButton();
        M = new javax.swing.JLabel();
        lb_Publisher1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lb_resNum = new javax.swing.JLabel();
        rbSwitch = new javax.swing.JRadioButton();
        btn_saveCart = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lb_resNum1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Trans = new javax.swing.JTable();
        lb_GameI_ID = new javax.swing.JLabel();
        tx_pay = new javax.swing.JTextField();
        lb_totprice = new javax.swing.JLabel();
        rb3DS = new javax.swing.JRadioButton();
        lb_satu = new javax.swing.JLabel();
        lb_platform = new javax.swing.JLabel();
        lb_date = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tb_Games = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        tx_cari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        lb_Decrease = new javax.swing.JLabel();
        lb_totTem = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        lb_getStock = new javax.swing.JLabel();
        lb_sup = new javax.swing.JLabel();
        lbl_Title1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        lb_Platform.setText("jLabel8");

        lb_resi.setText("jLabel8");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/game-controller.png"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 80, -1, 67));

        lbl_Title2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbl_Title2.setForeground(new java.awt.Color(153, 153, 153));
        lbl_Title2.setText("Baljid Game Store");
        getContentPane().add(lbl_Title2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 150, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/index.jpg"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 250, 120));

        jButton4.setBackground(new java.awt.Color(0, 0, 0));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/left-arrow.png"))); // NOI18N
        jButton4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 40, 30));

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        lb_Game_Name.setBackground(new java.awt.Color(51, 51, 51));
        lb_Game_Name.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lb_Game_Name.setForeground(new java.awt.Color(255, 255, 255));
        lb_Game_Name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_Game_Name.setText("Game Name");
        lb_Game_Name.setOpaque(true);
        jPanel1.add(lb_Game_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 210, 30));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 250, -1, -1));

        rbNDS.setBackground(new java.awt.Color(51, 51, 51));
        rbNDS.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rbNDS.setForeground(new java.awt.Color(255, 255, 255));
        rbNDS.setText("NDS");
        rbNDS.setEnabled(false);
        jPanel1.add(rbNDS, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 90, -1, 20));

        rbPC.setBackground(new java.awt.Color(51, 51, 51));
        buttonGroup2.add(rbPC);
        rbPC.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rbPC.setForeground(new java.awt.Color(255, 255, 255));
        rbPC.setText("PC");
        rbPC.setEnabled(false);
        rbPC.setOpaque(false);
        jPanel1.add(rbPC, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 70, 70, -1));

        lb_Description.setBackground(new java.awt.Color(51, 51, 51));
        lb_Description.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lb_Description.setForeground(new java.awt.Color(255, 255, 255));
        lb_Description.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_Description.setText("Description");
        lb_Description.setOpaque(true);
        jPanel1.add(lb_Description, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 40, 210, 30));

        rbPSV.setBackground(new java.awt.Color(51, 51, 51));
        buttonGroup2.add(rbPSV);
        rbPSV.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rbPSV.setForeground(new java.awt.Color(255, 255, 255));
        rbPSV.setText("PS VITA");
        rbPSV.setEnabled(false);
        rbPSV.setOpaque(false);
        jPanel1.add(rbPSV, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, 70, -1));

        rbPS4.setBackground(new java.awt.Color(51, 51, 51));
        buttonGroup2.add(rbPS4);
        rbPS4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rbPS4.setForeground(new java.awt.Color(255, 255, 255));
        rbPS4.setText("PS4");
        rbPS4.setEnabled(false);
        rbPS4.setOpaque(false);
        jPanel1.add(rbPS4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 70, -1));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 200, 150, 10));

        lb_refund.setBackground(new java.awt.Color(120, 120, 120));
        lb_refund.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lb_refund.setForeground(new java.awt.Color(255, 255, 255));
        lb_refund.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_refund.setText("0");
        lb_refund.setOpaque(true);
        jPanel1.add(lb_refund, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 210, 150, 40));

        lb_total.setBackground(new java.awt.Color(120, 120, 120));
        lb_total.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        lb_total.setForeground(new java.awt.Color(255, 255, 255));
        lb_total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_total.setText("0");
        lb_total.setOpaque(true);
        jPanel1.add(lb_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 90, 150, 70));

        tb_Keranjang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Trans_ID", "Trans_Number", "Game_ID", "Game_Name", "Platoform", "Publisher", "Description", "Price"
            }
        ));
        tb_Keranjang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_KeranjangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tb_Keranjang);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 560, 130));

        lb_Price.setBackground(new java.awt.Color(51, 51, 51));
        lb_Price.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lb_Price.setForeground(new java.awt.Color(255, 255, 255));
        lb_Price.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_Price.setText("Price");
        lb_Price.setOpaque(true);
        jPanel1.add(lb_Price, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 70, 210, 20));

        rbXBO.setBackground(new java.awt.Color(51, 51, 51));
        buttonGroup2.add(rbXBO);
        rbXBO.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rbXBO.setForeground(new java.awt.Color(255, 255, 255));
        rbXBO.setText("XBOX ONE");
        rbXBO.setEnabled(false);
        rbXBO.setOpaque(false);
        jPanel1.add(rbXBO, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 90, 70, -1));

        M.setBackground(new java.awt.Color(175, 175, 175));
        M.setOpaque(true);
        jPanel1.add(M, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 230, 250));

        lb_Publisher1.setBackground(new java.awt.Color(51, 51, 51));
        lb_Publisher1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lb_Publisher1.setForeground(new java.awt.Color(255, 255, 255));
        lb_Publisher1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_Publisher1.setText("Publisher");
        lb_Publisher1.setOpaque(true);
        jPanel1.add(lb_Publisher1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 210, 30));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 50, 60, 30));

        lb_resNum.setBackground(new java.awt.Color(255, 31, 103));
        lb_resNum.setFont(new java.awt.Font("Century Gothic", 3, 18)); // NOI18N
        lb_resNum.setForeground(new java.awt.Color(255, 255, 255));
        lb_resNum.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_resNum.setText("TRANSACTION NUMBER");
        jPanel1.add(lb_resNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 30, 110, -1));

        rbSwitch.setBackground(new java.awt.Color(51, 51, 51));
        buttonGroup2.add(rbSwitch);
        rbSwitch.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rbSwitch.setForeground(new java.awt.Color(255, 255, 255));
        rbSwitch.setText("SWITCH");
        rbSwitch.setEnabled(false);
        rbSwitch.setOpaque(false);
        jPanel1.add(rbSwitch, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 70, 70, -1));

        btn_saveCart.setText("Go to Payment");
        btn_saveCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveCartActionPerformed(evt);
            }
        });
        jPanel1.add(btn_saveCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 90, 130, 30));

        jLabel7.setBackground(new java.awt.Color(61, 61, 61));
        jLabel7.setOpaque(true);
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, 1010, 20));

        lb_resNum1.setBackground(new java.awt.Color(255, 31, 103));
        lb_resNum1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lb_resNum1.setForeground(new java.awt.Color(255, 255, 255));
        lb_resNum1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_resNum1.setText("TRANSACTION NUMBER");
        lb_resNum1.setOpaque(true);
        jPanel1.add(lb_resNum1, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 10, 290, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 240, 10));

        tb_Trans.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Transaction_Num", "Payment_Date", "Total_Item", "Total_Price"
            }
        ));
        jScrollPane2.setViewportView(tb_Trans);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 300, 970, 180));

        lb_GameI_ID.setBackground(new java.awt.Color(51, 51, 51));
        lb_GameI_ID.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lb_GameI_ID.setForeground(new java.awt.Color(255, 255, 255));
        lb_GameI_ID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_GameI_ID.setText("Game ID");
        lb_GameI_ID.setOpaque(true);
        jPanel1.add(lb_GameI_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 210, 30));

        tx_pay.setBackground(new java.awt.Color(120, 120, 120));
        tx_pay.setForeground(new java.awt.Color(255, 255, 255));
        tx_pay.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tx_pay.setText("PAY");
        tx_pay.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tx_pay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tx_payKeyPressed(evt);
            }
        });
        jPanel1.add(tx_pay, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 170, 150, 30));

        lb_totprice.setText("4");
        jPanel1.add(lb_totprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 440, 80, 50));

        rb3DS.setBackground(new java.awt.Color(51, 51, 51));
        buttonGroup2.add(rb3DS);
        rb3DS.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rb3DS.setForeground(new java.awt.Color(255, 255, 255));
        rb3DS.setText("3DS");
        rb3DS.setEnabled(false);
        rb3DS.setOpaque(false);
        jPanel1.add(rb3DS, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, 70, -1));

        lb_satu.setText("1");
        jPanel1.add(lb_satu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 400, -1, -1));

        lb_platform.setBackground(new java.awt.Color(51, 51, 51));
        lb_platform.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lb_platform.setForeground(new java.awt.Color(255, 255, 255));
        lb_platform.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_platform.setOpaque(true);
        jPanel1.add(lb_platform, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, 210, 60));

        lb_date.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lb_date.setForeground(new java.awt.Color(255, 255, 255));
        lb_date.setText("2019-07-20");
        jPanel1.add(lb_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 20, 140, 30));

        Tb_Games.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Game_ID", "Game_Name", "", "", "", "", "", "", "", ""
            }
        ));
        Tb_Games.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tb_GamesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tb_Games);
        if (Tb_Games.getColumnModel().getColumnCount() > 0) {
            Tb_Games.getColumnModel().getColumn(0).setPreferredWidth(10000);
            Tb_Games.getColumnModel().getColumn(1).setResizable(false);
            Tb_Games.getColumnModel().getColumn(1).setPreferredWidth(13000);
            Tb_Games.getColumnModel().getColumn(2).setResizable(false);
            Tb_Games.getColumnModel().getColumn(2).setPreferredWidth(0);
            Tb_Games.getColumnModel().getColumn(3).setResizable(false);
            Tb_Games.getColumnModel().getColumn(3).setPreferredWidth(0);
            Tb_Games.getColumnModel().getColumn(4).setResizable(false);
            Tb_Games.getColumnModel().getColumn(4).setPreferredWidth(0);
            Tb_Games.getColumnModel().getColumn(5).setResizable(false);
            Tb_Games.getColumnModel().getColumn(5).setPreferredWidth(0);
            Tb_Games.getColumnModel().getColumn(6).setResizable(false);
            Tb_Games.getColumnModel().getColumn(6).setPreferredWidth(0);
            Tb_Games.getColumnModel().getColumn(7).setResizable(false);
            Tb_Games.getColumnModel().getColumn(7).setPreferredWidth(0);
            Tb_Games.getColumnModel().getColumn(8).setResizable(false);
            Tb_Games.getColumnModel().getColumn(8).setPreferredWidth(0);
            Tb_Games.getColumnModel().getColumn(9).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 270, 420));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/magnifying-glass-finder.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 50, 50));

        tx_cari.setBackground(new java.awt.Color(120, 120, 120));
        tx_cari.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tx_cari.setForeground(new java.awt.Color(255, 255, 255));
        tx_cari.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tx_cari.setText("Search Games");
        tx_cari.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tx_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tx_cariKeyReleased(evt);
            }
        });
        jPanel1.add(tx_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 230, 40));

        jLabel1.setBackground(new java.awt.Color(120, 120, 120));
        jLabel1.setOpaque(true);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 274, 470));

        total.setBackground(new java.awt.Color(255, 31, 103));
        total.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        total.setForeground(new java.awt.Color(255, 255, 255));
        total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        total.setText("Total");
        total.setOpaque(true);
        jPanel1.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 60, 150, 20));

        lb_Decrease.setText("jLabel8");
        jPanel1.add(lb_Decrease, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 450, -1, -1));

        lb_totTem.setText("0");
        jPanel1.add(lb_totTem, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 170, 20, 30));

        jButton3.setText("-");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 50, 60, 30));

        lb_getStock.setBackground(new java.awt.Color(51, 51, 51));
        lb_getStock.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lb_getStock.setForeground(new java.awt.Color(255, 255, 255));
        lb_getStock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_getStock.setText("0");
        lb_getStock.setOpaque(true);
        jPanel1.add(lb_getStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 90, 210, 30));

        lb_sup.setText("SUP_ID");
        jPanel1.add(lb_sup, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 180, 90, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 1260, 500));

        lbl_Title1.setBackground(new java.awt.Color(153, 153, 153));
        lbl_Title1.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        lbl_Title1.setForeground(new java.awt.Color(153, 153, 153));
        lbl_Title1.setText("TRANSACTION FORM");
        getContentPane().add(lbl_Title1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, -1, -1));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setOpaque(true);
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 1260, 120));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cropped-47891.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setPreferredSize(new java.awt.Dimension(1260, 768));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 730));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Tb_GamesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tb_GamesMouseClicked
        // TODO add your handling code here:
        
         int i = Tb_Games.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)Tb_Games.getModel();
        lb_GameI_ID.setText(model.getValueAt(i, 0).toString());
        lb_Game_Name.setText(model.getValueAt(i, 1).toString());
        String Platform = model.getValueAt(i, 2).toString();
            if (Platform.equals("PC")) {
                rbPC.setSelected(true);
            }
            if (Platform.equals("PS4")) {
                rbPS4.setSelected(true);
            }
            if (Platform.equals("XBOX One")) {
                rbXBO.setSelected(true);
            }
            if (Platform.equals("Switch")) {
                rbSwitch.setSelected(true);
            }
            if (Platform.equals("PS Vita")) {
                rbPSV.setSelected(true);
            }
            if (Platform.equals("3DS")) {
                rb3DS.setSelected(true);
            }
           
        total.setText(model.getValueAt(i, 4).toString());
        lb_Description.setText(model.getValueAt(i, 5).toString());
        lb_getStock.setText(model.getValueAt(i, 6).toString());
        lb_sup.setText(model.getValueAt(i, 7).toString());
        lb_Price.setText(model.getValueAt(i, 9).toString());
        byte[] img = (GameList().get(i).getGame_Cover());
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(M.getWidth(), M.getHeight(), Image.SCALE_SMOOTH));
        M.setIcon(imageIcon);
        
    }//GEN-LAST:event_Tb_GamesMouseClicked
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int valid = this.stockvalid();
        if (valid == 0) {
            SendToTable();
            addItem();
            DecreaseStock();
            UpdatedataStock();
            InsertResi();
            autoCartID();
        totalInc();
        }
        else {
            JOptionPane.showMessageDialog(null, "Can't be Negative");
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      removeFromTable();
      decreaseItem();
      totalDec();
      increaseStock();
      UpdatedataStock();
      //autoDecrease();
     // DeleteData();
      
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tx_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx_cariKeyReleased
       DefaultTableModel table = (DefaultTableModel)Tb_Games.getModel();
        String search = tx_cari.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
        Tb_Games.setRowSorter(tr);
        tr.setRowFilter(javax.swing.RowFilter.regexFilter(search));
        // TODO add your handling code here:
    }//GEN-LAST:event_tx_cariKeyReleased

    private void btn_saveCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveCartActionPerformed
   SaveFromTabletoDatabase();
   InsertTransact();
   dispose();
   Form_Transaksi fr = new Form_Transaksi();
   fr.show();
    }//GEN-LAST:event_btn_saveCartActionPerformed

    private void tb_KeranjangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_KeranjangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_KeranjangMouseClicked

    private void tx_payKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx_payKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            refund();
      // Enter was pressed. Your code goes here.
   }
    }//GEN-LAST:event_tx_payKeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Dashboard db = new Dashboard();
        db.show();
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel M;
    private javax.swing.JTable Tb_Games;
    private javax.swing.JButton btn_saveCart;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lb_Decrease;
    private javax.swing.JLabel lb_Description;
    private javax.swing.JLabel lb_GameI_ID;
    private javax.swing.JLabel lb_Game_Name;
    private javax.swing.JLabel lb_Platform;
    private javax.swing.JLabel lb_Price;
    private javax.swing.JLabel lb_Publisher1;
    private javax.swing.JLabel lb_date;
    private javax.swing.JLabel lb_getStock;
    private javax.swing.JLabel lb_platform;
    private javax.swing.JLabel lb_refund;
    private javax.swing.JLabel lb_resNum;
    private javax.swing.JLabel lb_resNum1;
    private javax.swing.JLabel lb_resi;
    private javax.swing.JLabel lb_satu;
    private javax.swing.JLabel lb_sup;
    private javax.swing.JLabel lb_totTem;
    private javax.swing.JLabel lb_total;
    private javax.swing.JLabel lb_totprice;
    private javax.swing.JLabel lbl_Title1;
    private javax.swing.JLabel lbl_Title2;
    private javax.swing.JRadioButton rb3DS;
    private javax.swing.JRadioButton rbNDS;
    private javax.swing.JRadioButton rbPC;
    private javax.swing.JRadioButton rbPS4;
    private javax.swing.JRadioButton rbPSV;
    private javax.swing.JRadioButton rbSwitch;
    private javax.swing.JRadioButton rbXBO;
    private javax.swing.JTable tb_Keranjang;
    private javax.swing.JTable tb_Trans;
    private javax.swing.JLabel total;
    private javax.swing.JTextField tx_cari;
    private javax.swing.JTextField tx_pay;
    // End of variables declaration//GEN-END:variables
}
