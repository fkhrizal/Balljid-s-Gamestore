package MainMenu;

import Configuration.Connect;
import Entities.Game;
import javax.swing.JOptionPane;
import Entities.Game_Item;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class CRUD_Form extends javax.swing.JFrame {
    Game_Item GI = new Game_Item();
    Connect Conf_connect = new Connect();
    DefaultTableModel Model;
    
    public CRUD_Form() {
        initComponents();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        getautoCode();
        Display();
        show_Combo();
        GI.Game_Cover = null;
    }
    
    public ArrayList<Game> GameList() {
        ArrayList<Game> gameList = new ArrayList();
        try{
            Conf_connect.ConnectionConn();
            String games = "Select*From Game.Games";
            Statement stm = Conf_connect.con.createStatement();
            ResultSet rts = stm.executeQuery(games);
            Game game;
            while (rts.next()) {
            game = new Game(rts.getString("Game_ID"), rts.getString("Game_Name"), rts.getString("Platform"), rts.getString("Category"), rts.getString("Publisher"), rts.getString("Description"), rts.getInt("Stock"), rts.getString("Supplier_ID"), rts.getInt("Price"), rts.getBytes("Game_Cover"));
            gameList.add(game);
            }
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return gameList;
    }

    public void show_Combo() {
        try {
            Conf_connect.ConnectionConn();
            Statement stm = Conf_connect.con.createStatement();
            String cmb = "select Supplier_ID from Game.Supplier order by Supplier_ID asc";
            ResultSet rs = stm.executeQuery(cmb);
            while (rs.next()) {
                Object[] obj = new Object[3];
                obj[0] = rs.getString(1);
                txtSupplier.addItem((String) obj[0]);
            }
            rs.close();
            stm.close();
        }
        catch (Exception ex) {
            
        }
    }
    
    public void Search() {
        try {
            Conf_connect.ConnectionConn();
            String sea = "Select * From Game.Games where Game_ID = ?";
            PreparedStatement pst = Conf_connect.con.prepareStatement(sea);
            pst.setString(1, txtSearch.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String setID = rs.getString("Game_ID");
                txtGame_ID.setText(setID);
                String setName = rs.getString("Game_Name");
                txtGame_Name.setText(setName);
                String setPlatform = rs.getString("Platform");
                    if (setPlatform.equals("PC")) {
                        rbPC.setSelected(true);
                    }
                    if (setPlatform.equals("PS4")) {
                        rbPS4.setSelected(true);
                    }
                    if (setPlatform.equals("XBOX One")) {
                        rbXBO.setSelected(true);
                    }
                    if (setPlatform.equals("Switch")) {
                        rbSwitch.setSelected(true);
                    }
                    if (setPlatform.equals("PS Vita")) {
                        rbPSV.setSelected(true);
                    }
                    if (setPlatform.equals("3DS")) {
                        rb3DS.setSelected(true);
                    }
                    if (setPlatform.equals("NDS")) {
                        rbNDS.setSelected(true);
                    }
                String setCategory = rs.getString("Category");
                switch (setCategory) {
                        case "Action " :
                            cbAct.setSelected(true);
                            break;
                        case "Adventure " :
                            cbAdv.setSelected(true);
                            break;
                        case "Arcade " :
                            cbArc.setSelected(true);
                            break;
                        case "Card " :
                            cbCard.setSelected(true);
                            break;
                        case "Puzzle " :
                            cbPuzz.setSelected(true);
                            break;
                        case "Racing " :
                            cbRac.setSelected(true);
                            break;
                        case "RPG " :
                            cbRPG.setSelected(true);
                            break;
                        case "Sport " :
                            cbSpo.setSelected(true);
                            break;
                        case "Strategy " :
                            cbStr.setSelected(true);
                            break;
                        default :
                            cbAct.setSelected(true);
                            cbAdv.setSelected(true);
                            cbArc.setSelected(true);
                            cbCard.setSelected(true);
                            cbPuzz.setSelected(true);
                            cbRac.setSelected(true);
                            cbRPG.setSelected(true);
                            cbSpo.setSelected(true);
                            cbStr.setSelected(true);
                        }
                String setPublsiher = rs.getString("Publisher");
                txtPublisher.setText(setPublsiher);
                String setDescription = rs.getString("Description");
                txtDescription.setText(setDescription);
                String setStock = rs.getString("Stock");
                txtStock.setText(setStock);
                String setPrice = rs.getString("Price");
                txtPrice.setText(setPrice);
            }
            
        } catch(Exception ex) {
            
        }
    }
    
    public void Display() {
        ArrayList<Game> list = GameList();
        Model = (DefaultTableModel)Tab_Games.getModel();
        Object[] Column = new Object[9];
        for(int i = 0; i < list.size(); i++) {
            Column[0] = list.get(i).getGame_ID();
            Column[1] = list.get(i).getGame_Name();
            Column[2] = list.get(i).getPlatform();
            Column[3] = list.get(i).getCategory();
            Column[4] = list.get(i).getPublisher();
            Column[5] = list.get(i).getDescription();
            Column[6] = list.get(i).getStock();
            Column[7] = list.get(i).getSupplier_ID();
            Column[8] = list.get(i).getPrice();
            Model.addRow(Column);
        }
    }
    
    private void DeleteData() {
        GI.Game_ID = txtGame_ID.getText();
        int i = GI.Delete();
        if (i == 0) {
            JOptionPane.showMessageDialog(null, "Data can't be Deleted");
        }
        else {
            JOptionPane.showMessageDialog(null, "Data Deleted");
        }
    }
    
    private void UpdateData() {
        this.GetData();
        int valid = this.validation();
        if (valid == 0){
            int i = GI.Update();
            if (i == 0){
                JOptionPane.showMessageDialog(null, "Data can't be Updated");
            }
            else {
                JOptionPane.showMessageDialog(null, "Data Updated");
            }
        }
    }
    
    private void getautoCode() {
        txtGame_ID.setText(GI.autoGenerateID());
    }
    
    private void GetData() {
        try{
            GI.Game_ID = txtGame_ID.getText();
            GI.Game_Name = txtGame_Name.getText();
            if (rbPC.isSelected()) {
                GI.Platform="PC";
            }
            if (rbPS4.isSelected()) {
                GI.Platform="PS4";
            }
            if (rbXBO.isSelected()) {
                GI.Platform="XBOX One";
            }
            if (rbSwitch.isSelected()) {
                GI.Platform="Switch";
            }
            if (rbPSV.isSelected()) {
                GI.Platform="PS Vita";
            }
            if (rb3DS.isSelected()) {
                GI.Platform="3DS";
            }
            if (rbNDS.isSelected()) {
                GI.Platform="NDS";
            }
            if (cbAct.isSelected()) {
                GI.Category += cbAct.getText() + " ";
            }
            if (cbAdv.isSelected()) {
                GI.Category += cbAdv.getText() + " ";
            }
            if (cbArc.isSelected()) {
                GI.Category += cbArc.getText() + " ";
            }
            if (cbCard.isSelected()) {
                GI.Category += cbCard.getText() + " ";
            }
            if (cbPuzz.isSelected()) {
                GI.Category += cbPuzz.getText() + " ";
            }
            if (cbRac.isSelected()) {
                GI.Category += cbRac.getText() + " ";
            }
            if (cbRPG.isSelected()) {
                GI.Category += cbRPG.getText() + " ";
            }
            if (cbSpo.isSelected()) {
                GI.Category += cbSpo.getText() + " ";
            }
            if (cbStr.isSelected()) {
                GI.Category += cbStr.getText() + " ";
            }
            
            GI.Publisher = txtPublisher.getText();
            GI.Description = txtDescription.getText();
            GI.Stock = Integer.parseInt(txtStock.getText());
            GI.Supplier_ID = txtSupplier.getSelectedItem().toString();
            GI.Price = Integer.parseInt(txtPrice.getText());
            
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();
            GI.FileName = file.getAbsolutePath();
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(GI.FileName).getImage().getScaledInstance(lbl_Image.getWidth(), lbl_Image.getHeight(), Image.SCALE_SMOOTH));
            lbl_Image.setIcon(imageIcon);
            try {
                File image = new File(GI.FileName);
                FileInputStream FIS = new FileInputStream(image);
                ByteArrayOutputStream BOS = new ByteArrayOutputStream();
                byte[] bit = new byte[1024];
                for (int readnum; (readnum=FIS.read(bit)) != -1;){
                    BOS.write(bit, 0, readnum);
                }
                GI.Game_Cover = BOS.toByteArray();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    private void InsertData() {
        this.GetData();
        int valid = this.validation();
        if(valid == 0)
        {
            int i = GI.Insertion();
            if (i == 0) {
                JOptionPane.showMessageDialog(null, "Data can't be inputted");
            }
            else {
                JOptionPane.showMessageDialog(null, "Data inputted");
            }
        }
    }
    
    private int validation() {
        int valid = 0;
        if (txtGame_ID.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "ID Can't be Empty");
            valid = 1;
        }
        if (txtGame_Name.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Game's Name can't be empty");
            valid = 1;
        }
        if (GI.Platform.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Platform can't be empty");
            valid = 1;
        }
        if (GI.Category.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Category can't be empty");
            valid = 1;
        }
        if (txtPublisher.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Publisher can't be empty");
            valid = 1;
        }
        if (txtDescription.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Description can't be empty");
            valid = 1;
        }
//        if (txtStock.getText().equals("")) {
//            JOptionPane.showMessageDialog(null, "Stock can't be empty");
//            valid = 1;
//        }
        if (txtStock.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Supplier ID can't be empty");
            valid = 1;
        }
        if (txtPrice.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Price can't be empty");
            valid = 1;
        }
        if (lbl_Image.getIcon().equals("")) {
            JOptionPane.showMessageDialog(null, "Price can't be empty");
            valid = 1;
        }
        return valid;
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lbl_Title = new javax.swing.JLabel();
        lbl_GameID = new javax.swing.JLabel();
        lbl_GameName = new javax.swing.JLabel();
        lbl_Category = new javax.swing.JLabel();
        lbl_Platform = new javax.swing.JLabel();
        lbl_Publisher = new javax.swing.JLabel();
        lbl_Description = new javax.swing.JLabel();
        txtGame_ID = new javax.swing.JTextField();
        txtGame_Name = new javax.swing.JTextField();
        txtPublisher = new javax.swing.JTextField();
        lbl_Stock = new javax.swing.JLabel();
        lbl_SupplierID = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        lbl_Price = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        btn_DisplayData = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        cbSpo = new javax.swing.JCheckBox();
        cbStr = new javax.swing.JCheckBox();
        cbRac = new javax.swing.JCheckBox();
        cbPuzz = new javax.swing.JCheckBox();
        cbCard = new javax.swing.JCheckBox();
        cbAct = new javax.swing.JCheckBox();
        cbAdv = new javax.swing.JCheckBox();
        cbArc = new javax.swing.JCheckBox();
        cbRPG = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        rbPC = new javax.swing.JRadioButton();
        rbPS4 = new javax.swing.JRadioButton();
        rbXBO = new javax.swing.JRadioButton();
        rbSwitch = new javax.swing.JRadioButton();
        rbPSV = new javax.swing.JRadioButton();
        rb3DS = new javax.swing.JRadioButton();
        rbNDS = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tab_Games = new javax.swing.JTable();
        lbl_GameID1 = new javax.swing.JLabel();
        txtSupplier = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lbl_Image = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(840, 750));
        setPreferredSize(new java.awt.Dimension(1360, 740));
        setSize(new java.awt.Dimension(1270, 720));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_Title.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        lbl_Title.setForeground(new java.awt.Color(153, 153, 153));
        lbl_Title.setText("Administrator Form");
        getContentPane().add(lbl_Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, -1, -1));

        lbl_GameID.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbl_GameID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/find.png"))); // NOI18N
        getContentPane().add(lbl_GameID, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 150, -1, -1));

        lbl_GameName.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbl_GameName.setForeground(new java.awt.Color(204, 204, 204));
        lbl_GameName.setText("Game Name");
        getContentPane().add(lbl_GameName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, -1, -1));

        lbl_Category.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbl_Category.setForeground(new java.awt.Color(204, 204, 204));
        lbl_Category.setText("Category");
        getContentPane().add(lbl_Category, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, -1, -1));

        lbl_Platform.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbl_Platform.setForeground(new java.awt.Color(204, 204, 204));
        lbl_Platform.setText("Platform");
        getContentPane().add(lbl_Platform, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 350, -1, 20));

        lbl_Publisher.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbl_Publisher.setForeground(new java.awt.Color(204, 204, 204));
        lbl_Publisher.setText("Publisher");
        getContentPane().add(lbl_Publisher, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 230, -1, -1));

        lbl_Description.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbl_Description.setForeground(new java.awt.Color(204, 204, 204));
        lbl_Description.setText("Description");
        getContentPane().add(lbl_Description, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 230, -1, -1));

        txtGame_ID.setBackground(new java.awt.Color(82, 82, 82));
        txtGame_ID.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtGame_ID.setForeground(new java.awt.Color(255, 255, 255));
        txtGame_ID.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        getContentPane().add(txtGame_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 240, -1));

        txtGame_Name.setBackground(new java.awt.Color(82, 82, 82));
        txtGame_Name.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtGame_Name.setForeground(new java.awt.Color(255, 255, 255));
        txtGame_Name.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        getContentPane().add(txtGame_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 250, -1));

        txtPublisher.setBackground(new java.awt.Color(82, 82, 82));
        txtPublisher.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtPublisher.setForeground(new java.awt.Color(255, 255, 255));
        txtPublisher.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        getContentPane().add(txtPublisher, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 250, 250, -1));

        lbl_Stock.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbl_Stock.setForeground(new java.awt.Color(204, 204, 204));
        lbl_Stock.setText("Stock");
        getContentPane().add(lbl_Stock, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 290, -1, 20));

        lbl_SupplierID.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbl_SupplierID.setForeground(new java.awt.Color(204, 204, 204));
        lbl_SupplierID.setText("Supplier_ID");
        getContentPane().add(lbl_SupplierID, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 410, -1, -1));

        txtStock.setBackground(new java.awt.Color(82, 82, 82));
        txtStock.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtStock.setForeground(new java.awt.Color(255, 255, 255));
        txtStock.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        getContentPane().add(txtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 310, 250, -1));

        jButton1.setBackground(new java.awt.Color(47, 209, 125));
        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 10)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus.png"))); // NOI18N
        jButton1.setToolTipText("Add Item");
        jButton1.setOpaque(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 310, 40, -1));

        jButton2.setBackground(new java.awt.Color(227, 126, 217));
        jButton2.setFont(new java.awt.Font("Century Gothic", 1, 10)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pencil.png"))); // NOI18N
        jButton2.setToolTipText("Refresh");
        jButton2.setOpaque(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 350, 40, -1));

        jButton3.setBackground(new java.awt.Color(246, 29, 56));
        jButton3.setFont(new java.awt.Font("Century Gothic", 1, 10)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancel-button.png"))); // NOI18N
        jButton3.setToolTipText("Delete Item");
        jButton3.setOpaque(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 350, 40, -1));

        lbl_Price.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbl_Price.setForeground(new java.awt.Color(204, 204, 204));
        lbl_Price.setText("Price");
        getContentPane().add(lbl_Price, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 350, 40, -1));

        txtPrice.setBackground(new java.awt.Color(82, 82, 82));
        txtPrice.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtPrice.setForeground(new java.awt.Color(255, 255, 255));
        txtPrice.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        getContentPane().add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 370, 250, 20));

        btn_DisplayData.setBackground(new java.awt.Color(113, 136, 255));
        btn_DisplayData.setFont(new java.awt.Font("Century Gothic", 1, 10)); // NOI18N
        btn_DisplayData.setForeground(new java.awt.Color(255, 255, 255));
        btn_DisplayData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/refresh-arrows.png"))); // NOI18N
        btn_DisplayData.setToolTipText("Update Item");
        btn_DisplayData.setOpaque(false);
        btn_DisplayData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DisplayDataActionPerformed(evt);
            }
        });
        getContentPane().add(btn_DisplayData, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 390, 40, -1));

        jPanel1.setBackground(new java.awt.Color(82, 82, 82));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        cbSpo.setBackground(new java.awt.Color(82, 82, 82));
        cbSpo.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        cbSpo.setForeground(new java.awt.Color(255, 255, 255));
        cbSpo.setText("Sport");
        jPanel1.add(cbSpo);

        cbStr.setBackground(new java.awt.Color(82, 82, 82));
        cbStr.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        cbStr.setForeground(new java.awt.Color(255, 255, 255));
        cbStr.setText("Strategy");
        jPanel1.add(cbStr);

        cbRac.setBackground(new java.awt.Color(82, 82, 82));
        cbRac.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        cbRac.setForeground(new java.awt.Color(255, 255, 255));
        cbRac.setText("Racing");
        jPanel1.add(cbRac);

        cbPuzz.setBackground(new java.awt.Color(82, 82, 82));
        cbPuzz.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        cbPuzz.setForeground(new java.awt.Color(255, 255, 255));
        cbPuzz.setText("Puzzle");
        jPanel1.add(cbPuzz);

        cbCard.setBackground(new java.awt.Color(82, 82, 82));
        cbCard.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        cbCard.setForeground(new java.awt.Color(255, 255, 255));
        cbCard.setText("Card");
        jPanel1.add(cbCard);

        cbAct.setBackground(new java.awt.Color(82, 82, 82));
        cbAct.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        cbAct.setForeground(new java.awt.Color(255, 255, 255));
        cbAct.setText("Action");
        jPanel1.add(cbAct);

        cbAdv.setBackground(new java.awt.Color(82, 82, 82));
        cbAdv.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        cbAdv.setForeground(new java.awt.Color(255, 255, 255));
        cbAdv.setText("Adventure");
        jPanel1.add(cbAdv);

        cbArc.setBackground(new java.awt.Color(82, 82, 82));
        cbArc.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        cbArc.setForeground(new java.awt.Color(255, 255, 255));
        cbArc.setText("Arcade");
        jPanel1.add(cbArc);

        cbRPG.setBackground(new java.awt.Color(82, 82, 82));
        cbRPG.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        cbRPG.setForeground(new java.awt.Color(255, 255, 255));
        cbRPG.setText("RPG");
        jPanel1.add(cbRPG);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 250, 90));

        jPanel2.setBackground(new java.awt.Color(82, 82, 82));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        rbPC.setBackground(new java.awt.Color(82, 82, 82));
        buttonGroup1.add(rbPC);
        rbPC.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        rbPC.setForeground(new java.awt.Color(255, 255, 255));
        rbPC.setText("PC");
        rbPC.setOpaque(false);
        rbPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPCActionPerformed(evt);
            }
        });
        jPanel2.add(rbPC);

        rbPS4.setBackground(new java.awt.Color(82, 82, 82));
        buttonGroup1.add(rbPS4);
        rbPS4.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        rbPS4.setForeground(new java.awt.Color(255, 255, 255));
        rbPS4.setText("PS4");
        rbPS4.setOpaque(false);
        jPanel2.add(rbPS4);

        rbXBO.setBackground(new java.awt.Color(82, 82, 82));
        buttonGroup1.add(rbXBO);
        rbXBO.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        rbXBO.setForeground(new java.awt.Color(255, 255, 255));
        rbXBO.setText("XBOX One");
        rbXBO.setOpaque(false);
        jPanel2.add(rbXBO);

        rbSwitch.setBackground(new java.awt.Color(82, 82, 82));
        buttonGroup1.add(rbSwitch);
        rbSwitch.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        rbSwitch.setForeground(new java.awt.Color(255, 255, 255));
        rbSwitch.setText("Switch");
        rbSwitch.setOpaque(false);
        jPanel2.add(rbSwitch);

        rbPSV.setBackground(new java.awt.Color(82, 82, 82));
        buttonGroup1.add(rbPSV);
        rbPSV.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        rbPSV.setForeground(new java.awt.Color(255, 255, 255));
        rbPSV.setText("PS Vita");
        rbPSV.setOpaque(false);
        jPanel2.add(rbPSV);

        rb3DS.setBackground(new java.awt.Color(82, 82, 82));
        buttonGroup1.add(rb3DS);
        rb3DS.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        rb3DS.setForeground(new java.awt.Color(255, 255, 255));
        rb3DS.setText("3DS");
        rb3DS.setOpaque(false);
        jPanel2.add(rb3DS);

        rbNDS.setBackground(new java.awt.Color(82, 82, 82));
        buttonGroup1.add(rbNDS);
        rbNDS.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        rbNDS.setForeground(new java.awt.Color(255, 255, 255));
        rbNDS.setText("NDS");
        rbNDS.setOpaque(false);
        jPanel2.add(rbNDS);

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 380, 290, 90));

        txtDescription.setBackground(new java.awt.Color(82, 82, 82));
        txtDescription.setColumns(20);
        txtDescription.setForeground(new java.awt.Color(255, 255, 255));
        txtDescription.setRows(5);
        txtDescription.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jScrollPane2.setViewportView(txtDescription);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 260, 290, 80));

        Tab_Games.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Game_ID", "Game_Name", "Platform", "Category", "Publisher", "Description", "Stock", "Supplier_ID", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tab_Games.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tab_GamesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(Tab_Games);
        if (Tab_Games.getColumnModel().getColumnCount() > 0) {
            Tab_Games.getColumnModel().getColumn(0).setResizable(false);
            Tab_Games.getColumnModel().getColumn(1).setResizable(false);
            Tab_Games.getColumnModel().getColumn(2).setResizable(false);
            Tab_Games.getColumnModel().getColumn(3).setResizable(false);
            Tab_Games.getColumnModel().getColumn(4).setResizable(false);
            Tab_Games.getColumnModel().getColumn(5).setResizable(false);
            Tab_Games.getColumnModel().getColumn(6).setResizable(false);
            Tab_Games.getColumnModel().getColumn(7).setResizable(false);
            Tab_Games.getColumnModel().getColumn(8).setResizable(false);
        }

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 500, 1170, 140));

        lbl_GameID1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbl_GameID1.setForeground(new java.awt.Color(204, 204, 204));
        lbl_GameID1.setText("Game ID");
        getContentPane().add(lbl_GameID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, -1, -1));

        txtSupplier.setBackground(new java.awt.Color(64, 64, 64));
        txtSupplier.setBorder(null);
        txtSupplier.setOpaque(false);
        txtSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupplierActionPerformed(evt);
            }
        });
        getContentPane().add(txtSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 440, 260, -1));

        txtSearch.setBackground(new java.awt.Color(204, 204, 204));
        txtSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
        getContentPane().add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 150, 210, 30));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 390, 240, 20));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 270, 240, 20));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 240, 20));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, 240, 20));
        getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 330, 240, 20));

        jButton4.setBackground(new java.awt.Color(0, 0, 0));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/left-arrow.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 40, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coding-isometric-07-150x150.png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 240, 250));

        lbl_Image.setBackground(new java.awt.Color(0, 102, 102));
        lbl_Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cropped-47891 - Copy.jpg"))); // NOI18N
        lbl_Image.setToolTipText("");
        getContentPane().add(lbl_Image, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 60, 210, 220));

        jLabel4.setBackground(new java.awt.Color(82, 82, 82));
        jLabel4.setOpaque(true);
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 940, 270));

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setOpaque(true);
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 480, 1200, 170));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setOpaque(true);
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 930, 160));

        jLabel1.setBackground(new java.awt.Color(67, 67, 67));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 40, 260, 440));

        jLabel6.setBackground(new java.awt.Color(67, 67, 67));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setOpaque(true);
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, 1200, 430));

        jLabel5.setBackground(new java.awt.Color(67, 67, 67));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cropped-47891 - Copy.jpg"))); // NOI18N
        jLabel5.setOpaque(true);
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1430, 730));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        InsertData();
        this.dispose();
        CRUD_Form CF = new CRUD_Form();
        CF.show();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_DisplayDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DisplayDataActionPerformed
        this.dispose();
        CRUD_Form CF = new CRUD_Form();
        CF.show();
    // TODO add your handling code here:
    }//GEN-LAST:event_btn_DisplayDataActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        UpdateData();
        this.dispose();
        CRUD_Form CF = new CRUD_Form();
        CF.show();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        DeleteData();
        this.dispose();
        CRUD_Form CF = new CRUD_Form();
        CF.show();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void rbPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbPCActionPerformed

    private void Tab_GamesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tab_GamesMouseClicked
        int i = Tab_Games.getSelectedRow();
        Model = (DefaultTableModel)Tab_Games.getModel();
        txtGame_ID.setText(Model.getValueAt(i, 0).toString());
        txtGame_Name.setText(Model.getValueAt(i, 1).toString());
        String Platform = Model.getValueAt(i, 2).toString();
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
            if (Platform.equals("NDS")) {
                rbNDS.setSelected(true);
            }
        String Category = Model.getValueAt(i, 3).toString();
            switch (Category) {
                case "Action " :
                    cbAct.setSelected(true);
                    break;
                case "Adventure " :
                    cbAdv.setSelected(true);
                    break;
                case "Arcade " :
                    cbArc.setSelected(true);
                    break;
                case "Card " :
                    cbCard.setSelected(true);
                    break;
                case "Puzzle " :
                    cbPuzz.setSelected(true);
                    break;
                case "Racing " :
                    cbRac.setSelected(true);
                    break;
                case "RPG " :
                    cbRPG.setSelected(true);
                    break;
                case "Sport " :
                    cbSpo.setSelected(true);
                    break;
                case "Strategy " :
                    cbStr.setSelected(true);
                    break;
                default :
                    cbAct.setSelected(true);
                    cbAdv.setSelected(true);
                    cbArc.setSelected(true);
                    cbCard.setSelected(true);
                    cbPuzz.setSelected(true);
                    cbRac.setSelected(true);
                    cbRPG.setSelected(true);
                    cbSpo.setSelected(true);
                    cbStr.setSelected(true);
            }
        txtPublisher.setText(Model.getValueAt(i, 4).toString());
        txtDescription.setText(Model.getValueAt(i, 5).toString());
        txtStock.setText(Model.getValueAt(i, 6).toString());
        txtPrice.setText(Model.getValueAt(i, 8).toString());
        byte[] img = (GameList().get(i).getGame_Cover());
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(lbl_Image.getWidth(), lbl_Image.getHeight(), Image.SCALE_SMOOTH));
        lbl_Image.setIcon(imageIcon);
        GI.Game_Cover = img;
    }//GEN-LAST:event_Tab_GamesMouseClicked

    private void txtSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupplierActionPerformed
              // TODO add your handling code here:
    }//GEN-LAST:event_txtSupplierActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        Search();
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dispose();
        Dashboard db = new Dashboard();
        db.show();
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
            java.util.logging.Logger.getLogger(CRUD_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CRUD_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CRUD_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CRUD_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CRUD_Form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tab_Games;
    private javax.swing.JButton btn_DisplayData;
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JCheckBox cbAct;
    public javax.swing.JCheckBox cbAdv;
    public javax.swing.JCheckBox cbArc;
    public javax.swing.JCheckBox cbCard;
    public javax.swing.JCheckBox cbPuzz;
    public javax.swing.JCheckBox cbRPG;
    public javax.swing.JCheckBox cbRac;
    public javax.swing.JCheckBox cbSpo;
    public javax.swing.JCheckBox cbStr;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lbl_Category;
    private javax.swing.JLabel lbl_Description;
    private javax.swing.JLabel lbl_GameID;
    private javax.swing.JLabel lbl_GameID1;
    private javax.swing.JLabel lbl_GameName;
    private javax.swing.JLabel lbl_Image;
    private javax.swing.JLabel lbl_Platform;
    private javax.swing.JLabel lbl_Price;
    private javax.swing.JLabel lbl_Publisher;
    private javax.swing.JLabel lbl_Stock;
    private javax.swing.JLabel lbl_SupplierID;
    private javax.swing.JLabel lbl_Title;
    private javax.swing.JRadioButton rb3DS;
    private javax.swing.JRadioButton rbNDS;
    private javax.swing.JRadioButton rbPC;
    private javax.swing.JRadioButton rbPS4;
    private javax.swing.JRadioButton rbPSV;
    private javax.swing.JRadioButton rbSwitch;
    private javax.swing.JRadioButton rbXBO;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtGame_ID;
    private javax.swing.JTextField txtGame_Name;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtPublisher;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtStock;
    private javax.swing.JComboBox<String> txtSupplier;
    // End of variables declaration//GEN-END:variables
}

