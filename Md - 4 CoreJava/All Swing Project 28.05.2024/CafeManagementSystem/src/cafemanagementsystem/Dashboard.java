package cafemanagementsystem;

import java.awt.Image;
import java.awt.print.PrinterException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Dashboard extends javax.swing.JFrame {

    private double totalPrice = 0.0;
    private int x = 0;
    private double tax = 0;

    public Dashboard() {
        initComponents();
        init();
        setTime();
    }

    public void royalCafe() {
        int purchaseId = 15020 + (int) (Math.random() * 80800);
        receipt.setText("********************Royal Cafe********************\n"
                + "Time: " + txtTime.getText()+ " " + "Date: " + txtDate.getText() + "\n"
                + "Purchase Id: " + purchaseId + "\n"
                + "****************************************************\n"
                + "Item Name: \t\t" + "Price($)\n");
    }

    public void setTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Date date = new Date();
                    SimpleDateFormat tf = new SimpleDateFormat("h:mm:ss aa");
                    SimpleDateFormat df = new SimpleDateFormat("EEEE, dd-MM-yyyy");
                    String time = tf.format(date);
                    txtTime.setText(time.split(" ")[0] + " " + time.split(" ")[1]);
                    txtDate.setText(df.format(date));
                }
            }
        }).start();
    }

    public boolean qtyIsZero(int qty) {
        if (qty == 0) {
            JOptionPane.showMessageDialog(null, "Please increase the item quantity");
            return false;
        }
        return true;
    }

    public void init() {
        setImage();
    }

    public void setImage() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/coldcoffee.jpg"));
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/images/cappuccinocoffee.jpg"));
        ImageIcon icon2 = new ImageIcon(getClass().getResource("/images/chocolatecoffee.jpg"));
        ImageIcon icon3 = new ImageIcon(getClass().getResource("/images/greentea.jpg"));
        ImageIcon icon4 = new ImageIcon(getClass().getResource("/images/coke.jpg"));
        ImageIcon icon5 = new ImageIcon(getClass().getResource("/images/vegetarianpizza.jpg"));
        ImageIcon icon6 = new ImageIcon(getClass().getResource("/images/strawberrycake.jpg"));
        ImageIcon icon7 = new ImageIcon(getClass().getResource("/images/chocolatecake.jpg"));
        ImageIcon icon8 = new ImageIcon(getClass().getResource("/images/rainbow.jpg"));
        ImageIcon icon9 = new ImageIcon(getClass().getResource("/images/orange_juice.jpg"));

        Image img = icon.getImage().getScaledInstance(itemPhoto.getWidth(), itemPhoto.getHeight(), Image.SCALE_SMOOTH);
        Image img1 = icon1.getImage().getScaledInstance(itemPhoto1.getWidth(), itemPhoto1.getHeight(), Image.SCALE_SMOOTH);
        Image img2 = icon2.getImage().getScaledInstance(itemPhoto2.getWidth(), itemPhoto2.getHeight(), Image.SCALE_SMOOTH);
        Image img3 = icon3.getImage().getScaledInstance(itemPhoto3.getWidth(), itemPhoto3.getHeight(), Image.SCALE_SMOOTH);
        Image img4 = icon4.getImage().getScaledInstance(itemPhoto4.getWidth(), itemPhoto4.getHeight(), Image.SCALE_SMOOTH);
        Image img5 = icon5.getImage().getScaledInstance(itemPhoto5.getWidth(), itemPhoto5.getHeight(), Image.SCALE_SMOOTH);
        Image img6 = icon6.getImage().getScaledInstance(itemPhoto6.getWidth(), itemPhoto6.getHeight(), Image.SCALE_SMOOTH);
        Image img7 = icon7.getImage().getScaledInstance(itemPhoto7.getWidth(), itemPhoto7.getHeight(), Image.SCALE_SMOOTH);
        Image img8 = icon8.getImage().getScaledInstance(itemPhoto8.getWidth(), itemPhoto8.getHeight(), Image.SCALE_SMOOTH);
        Image img9 = icon9.getImage().getScaledInstance(itemPhoto9.getWidth(), itemPhoto9.getHeight(), Image.SCALE_SMOOTH);

        itemPhoto.setIcon(new ImageIcon(img));
        itemPhoto1.setIcon(new ImageIcon(img1));
        itemPhoto2.setIcon(new ImageIcon(img2));
        itemPhoto3.setIcon(new ImageIcon(img3));
        itemPhoto4.setIcon(new ImageIcon(img4));
        itemPhoto5.setIcon(new ImageIcon(img5));
        itemPhoto6.setIcon(new ImageIcon(img6));
        itemPhoto7.setIcon(new ImageIcon(img7));
        itemPhoto8.setIcon(new ImageIcon(img8));
        itemPhoto9.setIcon(new ImageIcon(img9));
    }

    public void reset() {
        btnTotal.setEnabled(true);
        totalPrice = 0.0;
        tax = 0.0;
        x = 0;
        quantity.setValue(0);
        quantity1.setValue(0);
        quantity2.setValue(0);
        quantity3.setValue(0);
        quantity4.setValue(0);
        quantity5.setValue(0);
        quantity6.setValue(0);
        quantity7.setValue(0);
        quantity8.setValue(0);
        quantity9.setValue(0);
        txtTax.setText("0.0");
        txtSubTotal.setText("0.0");
        txtTotal.setText("0.0");
        receipt.setText("");
        jCheckBox.setSelected(false);
        jCheckBox1.setSelected(false);
        jCheckBox2.setSelected(false);
        jCheckBox3.setSelected(false);
        jCheckBox4.setSelected(false);
        jCheckBox5.setSelected(false);
        jCheckBox6.setSelected(false);
        jCheckBox7.setSelected(false);
        jCheckBox8.setSelected(false);
        jCheckBox9.setSelected(false);

    }

    public void dudate() {
        txtSubTotal.setText(String.valueOf(String.format("%.2f", totalPrice)));
        txtTax.setText(String.valueOf(String.format("%.2f", tax)));
        txtTotal.setText(String.valueOf(String.format("%.2f", (totalPrice + tax))));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDate = new javax.swing.JLabel();
        txtTime = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        item2 = new javax.swing.JPanel();
        itemPhoto1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        itemName1 = new javax.swing.JLabel();
        quantity1 = new javax.swing.JSpinner();
        jCheckBox1 = new javax.swing.JCheckBox();
        item1 = new javax.swing.JPanel();
        itemPhoto = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        itemName = new javax.swing.JLabel();
        quantity = new javax.swing.JSpinner();
        jCheckBox = new javax.swing.JCheckBox();
        item3 = new javax.swing.JPanel();
        itemPhoto4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        itemName4 = new javax.swing.JLabel();
        quantity4 = new javax.swing.JSpinner();
        jCheckBox4 = new javax.swing.JCheckBox();
        item4 = new javax.swing.JPanel();
        itemPhoto2 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        itemName2 = new javax.swing.JLabel();
        quantity2 = new javax.swing.JSpinner();
        jCheckBox2 = new javax.swing.JCheckBox();
        item5 = new javax.swing.JPanel();
        itemPhoto3 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        itemName3 = new javax.swing.JLabel();
        quantity3 = new javax.swing.JSpinner();
        jCheckBox3 = new javax.swing.JCheckBox();
        item6 = new javax.swing.JPanel();
        itemPhoto5 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        itemName5 = new javax.swing.JLabel();
        quantity5 = new javax.swing.JSpinner();
        jCheckBox5 = new javax.swing.JCheckBox();
        item7 = new javax.swing.JPanel();
        itemPhoto6 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        itemName6 = new javax.swing.JLabel();
        quantity6 = new javax.swing.JSpinner();
        jCheckBox6 = new javax.swing.JCheckBox();
        item8 = new javax.swing.JPanel();
        itemPhoto7 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        itemName7 = new javax.swing.JLabel();
        quantity7 = new javax.swing.JSpinner();
        jCheckBox7 = new javax.swing.JCheckBox();
        item9 = new javax.swing.JPanel();
        itemPhoto8 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        itemName8 = new javax.swing.JLabel();
        quantity8 = new javax.swing.JSpinner();
        jCheckBox8 = new javax.swing.JCheckBox();
        item10 = new javax.swing.JPanel();
        itemPhoto9 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        itemName9 = new javax.swing.JLabel();
        quantity9 = new javax.swing.JSpinner();
        jCheckBox9 = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        btnExit = new javax.swing.JButton();
        btnTotal = new javax.swing.JButton();
        btnReceipt = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        receipt = new javax.swing.JTextArea();
        txtTotal = new javax.swing.JTextField();
        txtSubTotal = new javax.swing.JTextField();
        txtTax = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 51, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Royal Cafe");
        jLabel1.setBorder(null);
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 50));

        txtDate.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtDate.setForeground(new java.awt.Color(153, 0, 51));
        jPanel2.add(txtDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 10, 160, 30));

        txtTime.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txtTime.setForeground(new java.awt.Color(153, 0, 51));
        jPanel2.add(txtTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, 100, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 50));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(51, 0, 0));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Menu Items");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 790, 40));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 790, 20));

        item2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        item2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        item2.add(itemPhoto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 81));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setText("Purchase:");
        item2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, 20));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setText("Price:");
        item2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 60, 20));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel7.setText("Quantity:");
        item2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 127, -1, 20));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel8.setText("$7.50");
        item2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 70, -1));

        itemName1.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        itemName1.setForeground(new java.awt.Color(255, 0, 0));
        itemName1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        itemName1.setText("Cappucchino Coffee");
        item2.add(itemName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 150, -1));

        quantity1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        quantity1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 50, 1));
        item2.add(quantity1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 50, 30));

        jCheckBox1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        item2.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        jPanel3.add(item2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 150, 180));

        item1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        item1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        item1.add(itemPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 81));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel11.setText("Purchase:");
        item1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, 20));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel13.setText("Price:");
        item1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 60, 20));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel14.setText("Quantity:");
        item1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 127, -1, 20));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel15.setText("$5.50");
        item1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 70, -1));

        itemName.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        itemName.setForeground(new java.awt.Color(255, 0, 0));
        itemName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        itemName.setText("Cold Coffee");
        item1.add(itemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 150, -1));

        quantity.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        quantity.setModel(new javax.swing.SpinnerNumberModel(0, 0, 50, 1));
        item1.add(quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 50, 30));

        jCheckBox.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxActionPerformed(evt);
            }
        });
        item1.add(jCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        jPanel3.add(item1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 150, 180));

        item3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        item3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        item3.add(itemPhoto4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 81));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel10.setText("Purchase:");
        item3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, 20));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel18.setText("Price:");
        item3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 60, 20));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel19.setText("Quantity:");
        item3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 127, -1, 20));

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel20.setText("$2.90");
        item3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 70, -1));

        itemName4.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        itemName4.setForeground(new java.awt.Color(255, 0, 0));
        itemName4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        itemName4.setText("Royal Cola");
        item3.add(itemName4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 150, -1));

        quantity4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        quantity4.setModel(new javax.swing.SpinnerNumberModel(0, 0, 50, 1));
        item3.add(quantity4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 50, 30));

        jCheckBox4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });
        item3.add(jCheckBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        jPanel3.add(item3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 40, -1, 180));

        item4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        item4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        item4.add(itemPhoto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 81));

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel22.setText("Purchase:");
        item4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, 20));

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel24.setText("Price:");
        item4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 60, 20));

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel25.setText("Quantity:");
        item4.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 127, -1, 20));

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel26.setText("$4.50");
        item4.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 70, -1));

        itemName2.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        itemName2.setForeground(new java.awt.Color(255, 0, 0));
        itemName2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        itemName2.setText("Chocolate Coffee");
        item4.add(itemName2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 150, -1));

        quantity2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        quantity2.setModel(new javax.swing.SpinnerNumberModel(0, 0, 50, 1));
        item4.add(quantity2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 50, 30));

        jCheckBox2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        item4.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        jPanel3.add(item4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, -1, 180));

        item5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        item5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        item5.add(itemPhoto3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 81));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel28.setText("Purchase:");
        item5.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, 20));

        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel30.setText("Price:");
        item5.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 60, 20));

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel31.setText("Quantity:");
        item5.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 127, -1, 20));

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel32.setText("$1.50");
        item5.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 70, -1));

        itemName3.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        itemName3.setForeground(new java.awt.Color(255, 0, 0));
        itemName3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        itemName3.setText("Green Tea");
        item5.add(itemName3, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 80, 150, -1));

        quantity3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        quantity3.setModel(new javax.swing.SpinnerNumberModel(0, 0, 50, 1));
        item5.add(quantity3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 50, 30));

        jCheckBox3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });
        item5.add(jCheckBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        jPanel3.add(item5, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, -1, 180));

        item6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        item6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        item6.add(itemPhoto5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 81));

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel34.setText("Purchase:");
        item6.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, 20));

        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel36.setText("Price:");
        item6.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 60, 20));

        jLabel37.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel37.setText("Quantity:");
        item6.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 127, -1, 20));

        jLabel38.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel38.setText("$19.50");
        item6.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 70, -1));

        itemName5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        itemName5.setForeground(new java.awt.Color(255, 0, 0));
        itemName5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        itemName5.setText("Royal Pizza");
        item6.add(itemName5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 80, 150, -1));

        quantity5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        quantity5.setModel(new javax.swing.SpinnerNumberModel(0, 0, 50, 1));
        item6.add(quantity5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 50, 30));

        jCheckBox5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });
        item6.add(jCheckBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        jPanel3.add(item6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 150, 180));

        item7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        item7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        item7.add(itemPhoto6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 81));

        jLabel40.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel40.setText("Purchase:");
        item7.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, 20));

        jLabel42.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel42.setText("Price:");
        item7.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 60, 20));

        jLabel43.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel43.setText("Quantity:");
        item7.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 127, -1, 20));

        jLabel44.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel44.setText("$7.50");
        item7.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 70, -1));

        itemName6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        itemName6.setForeground(new java.awt.Color(255, 0, 0));
        itemName6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        itemName6.setText("Strawberry Cake");
        item7.add(itemName6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 150, -1));

        quantity6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        quantity6.setModel(new javax.swing.SpinnerNumberModel(0, 0, 50, 1));
        item7.add(quantity6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 50, 30));

        jCheckBox6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox6ActionPerformed(evt);
            }
        });
        item7.add(jCheckBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        jPanel3.add(item7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 150, 180));

        item8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        item8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        item8.add(itemPhoto7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 81));

        jLabel46.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel46.setText("Purchase:");
        item8.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, 20));

        jLabel48.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel48.setText("Price:");
        item8.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 60, 20));

        jLabel49.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel49.setText("Quantity:");
        item8.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 127, -1, 20));

        jLabel50.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel50.setText("$8.50");
        item8.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 70, -1));

        itemName7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        itemName7.setForeground(new java.awt.Color(255, 0, 0));
        itemName7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        itemName7.setText("Chocolate Cake");
        item8.add(itemName7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 150, -1));

        quantity7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        quantity7.setModel(new javax.swing.SpinnerNumberModel(0, 0, 50, 1));
        item8.add(quantity7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 50, 30));

        jCheckBox7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox7ActionPerformed(evt);
            }
        });
        item8.add(jCheckBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        jPanel3.add(item8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 230, -1, 180));

        item9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        item9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        item9.add(itemPhoto8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 81));

        jLabel52.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel52.setText("Purchase:");
        item9.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, 20));

        jLabel54.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel54.setText("Price:");
        item9.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 60, 20));

        jLabel55.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel55.setText("Quantity:");
        item9.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 127, -1, 20));

        jLabel56.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel56.setText("$10.50");
        item9.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 70, -1));

        itemName8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        itemName8.setForeground(new java.awt.Color(255, 0, 0));
        itemName8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        itemName8.setText("Rainbow Cake");
        item9.add(itemName8, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 80, 150, -1));

        quantity8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        quantity8.setModel(new javax.swing.SpinnerNumberModel(0, 0, 50, 1));
        item9.add(quantity8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 50, 30));

        jCheckBox8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jCheckBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox8ActionPerformed(evt);
            }
        });
        item9.add(jCheckBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        jPanel3.add(item9, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, -1, 180));

        item10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        item10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        item10.add(itemPhoto9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 81));

        jLabel58.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel58.setText("Purchase:");
        item10.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, 20));

        jLabel60.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel60.setText("Price:");
        item10.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 60, 20));

        jLabel61.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel61.setText("Quantity:");
        item10.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 127, -1, 20));

        jLabel62.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel62.setText("$3.50");
        item10.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 70, -1));

        itemName9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        itemName9.setForeground(new java.awt.Color(255, 0, 0));
        itemName9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        itemName9.setText("Royal Juice");
        item10.add(itemName9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 150, -1));

        quantity9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        quantity9.setModel(new javax.swing.SpinnerNumberModel(0, 0, 50, 1));
        item10.add(quantity9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 50, 30));

        jCheckBox9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jCheckBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox9ActionPerformed(evt);
            }
        });
        item10.add(jCheckBox9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        jPanel3.add(item10, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 230, -1, 180));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnExit.setBackground(new java.awt.Color(204, 0, 0));
        btnExit.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jPanel5.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 80, 40));

        btnTotal.setBackground(new java.awt.Color(0, 102, 102));
        btnTotal.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnTotal.setForeground(new java.awt.Color(255, 255, 255));
        btnTotal.setText("Total");
        btnTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTotalActionPerformed(evt);
            }
        });
        jPanel5.add(btnTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 80, 40));

        btnReceipt.setBackground(new java.awt.Color(204, 102, 0));
        btnReceipt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnReceipt.setForeground(new java.awt.Color(255, 255, 255));
        btnReceipt.setText("Receipt");
        btnReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReceiptActionPerformed(evt);
            }
        });
        jPanel5.add(btnReceipt, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, -1, 40));

        btnReset.setBackground(new java.awt.Color(0, 102, 51));
        btnReset.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jPanel5.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 80, 40));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 790, 60));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 810, 490));

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        receipt.setColumns(20);
        receipt.setRows(5);
        jScrollPane1.setViewportView(receipt);

        jPanel6.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 380));

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal.setText("0.0");
        txtTotal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 450, 120, -1));

        txtSubTotal.setEditable(false);
        txtSubTotal.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtSubTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSubTotal.setText("0.0");
        txtSubTotal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.add(txtSubTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, 120, -1));

        txtTax.setEditable(false);
        txtTax.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtTax.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTax.setText("0.0");
        txtTax.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.add(txtTax, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 420, 120, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Total Price ($)");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 90, 30));

        jLabel5.setBackground(new java.awt.Color(0, 102, 102));
        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Sub Total ($)");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 90, 30));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("Tax ($)");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 90, 30));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 50, 280, 490));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void jCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxActionPerformed
        int qty = Integer.parseInt(quantity.getValue().toString());
        if (qtyIsZero(qty) && jCheckBox.isSelected()) {
            x++;
            if (x == 1) {
                royalCafe();
            }
            double price = qty * 5.50;
            totalPrice += price;
            getTax(totalPrice);
            receipt.setText(receipt.getText() + x + ". " + itemName.getText() + "\t\t" + price + "\n");
            dudate();
        } else {
            jCheckBox.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBoxActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        int qty = Integer.parseInt(quantity1.getValue().toString());
        if (qtyIsZero(qty) && jCheckBox1.isSelected()) {
            x++;
            if (x == 1) {
                royalCafe();
            }
            double price = qty * 7.50;
            totalPrice += price;
            getTax(totalPrice);
            receipt.setText(receipt.getText() + x + ". " + itemName1.getText() + "\t" + price + "\n");
            dudate();
        } else {
            jCheckBox1.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        int qty = Integer.parseInt(quantity2.getValue().toString());
        if (qtyIsZero(qty) && jCheckBox2.isSelected()) {
            x++;
            if (x == 1) {
                royalCafe();
            }
            double price = qty * 4.50;
            totalPrice += price;
            getTax(totalPrice);
            receipt.setText(receipt.getText() + x + ". " + itemName2.getText() + "\t" + price + "\n");
            dudate();
        } else {
            jCheckBox2.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        int qty = Integer.parseInt(quantity3.getValue().toString());
        if (qtyIsZero(qty) && jCheckBox3.isSelected()) {
            x++;
            if (x == 1) {
                royalCafe();
            }
            double price = qty * 1.50;
            totalPrice += price;
            getTax(totalPrice);
            receipt.setText(receipt.getText() + x + ". " + itemName3.getText() + "\t\t" + price + "\n");
            dudate();
        } else {
            jCheckBox3.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        int qty = Integer.parseInt(quantity4.getValue().toString());
        if (qtyIsZero(qty) && jCheckBox4.isSelected()) {
            x++;
            if (x == 1) {
                royalCafe();
            }
            double price = qty * 2.90;
            totalPrice += price;
            getTax(totalPrice);
            receipt.setText(receipt.getText() + x + ". " + itemName4.getText() + "\t\t" + String.format("%.2f", price) + "\n");
            dudate();
        } else {
            jCheckBox4.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        int qty = Integer.parseInt(quantity5.getValue().toString());
        if (qtyIsZero(qty) && jCheckBox5.isSelected()) {
            x++;
            if (x == 1) {
                royalCafe();
            }
            double price = qty * 19.50;
            totalPrice += price;
            getTax(totalPrice);
            receipt.setText(receipt.getText() + x + ". " + itemName5.getText() + "\t\t" + price + "\n");
            dudate();
        } else {
            jCheckBox5.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox6ActionPerformed
        int qty = Integer.parseInt(quantity6.getValue().toString());
        if (qtyIsZero(qty) && jCheckBox6.isSelected()) {
            x++;
            if (x == 1) {
                royalCafe();
            }
            double price = qty * 7.50;
            totalPrice += price;
            getTax(totalPrice);
            receipt.setText(receipt.getText() + x + ". " + itemName6.getText() + "\t" + price + "\n");
            dudate();
        } else {
            jCheckBox6.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBox6ActionPerformed

    private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox7ActionPerformed
        int qty = Integer.parseInt(quantity7.getValue().toString());
        if (qtyIsZero(qty) && jCheckBox7.isSelected()) {
            x++;
            if (x == 1) {
                royalCafe();
            }
            double price = qty * 8.50;
            totalPrice += price;
            getTax(totalPrice);
            receipt.setText(receipt.getText() + x + ". " + itemName7.getText() + "\t" + price + "\n");
            dudate();
        } else {
            jCheckBox7.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBox7ActionPerformed

    private void jCheckBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox8ActionPerformed
        int qty = Integer.parseInt(quantity8.getValue().toString());
        if (qtyIsZero(qty) && jCheckBox8.isSelected()) {
            x++;
            if (x == 1) {
                royalCafe();
            }
            double price = qty * 10.50;
            totalPrice += price;
            getTax(totalPrice);
            receipt.setText(receipt.getText() + x + ". " + itemName8.getText() + "\t" + price + "\n");
            dudate();
        } else {
            jCheckBox8.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBox8ActionPerformed

    private void jCheckBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox9ActionPerformed
        int qty = Integer.parseInt(quantity9.getValue().toString());
        if (qtyIsZero(qty) && jCheckBox9.isSelected()) {
            x++;
            if (x == 1) {
                royalCafe();
            }
            double price = qty * 3.50;
            totalPrice += price;
            getTax(totalPrice);
            receipt.setText(receipt.getText() + x + ". " + itemName9.getText() + "\t\t" + price + "\n");
            dudate();
        } else {
            jCheckBox9.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBox9ActionPerformed

    private void btnTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTotalActionPerformed
        if (totalPrice == 0.0) {
            JOptionPane.showMessageDialog(null, "You haven't select any item.");
        } else {
            receipt.setText(receipt.getText()
                    + "\n****************************************************\n"
                    + "Sub Total: \t\t" + totalPrice + "\n"
                    + "Tax: \t\t" + tax + "\n"
                    + "Total: \t\t" + (totalPrice + tax) + "\n"
                    + "*********************Thank You*******************\n"
            );
            btnTotal.setEnabled(false);
        }
    }//GEN-LAST:event_btnTotalActionPerformed
    int xx, xy;
    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_jPanel2MouseDragged

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jPanel2MousePressed

    private void btnReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReceiptActionPerformed
        if (totalPrice != 0) {
            if (!btnTotal.isEnabled()) {
                try {
                    receipt.print();
                } catch (PrinterException ex) {
                    Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please calculate total price");
            }

        } else {
            JOptionPane.showMessageDialog(null, "You haven't purchased any product");
        }
    }//GEN-LAST:event_btnReceiptActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        for (double i=0.0; i<=1.0; i+=0.1){
            String s =i+"";
            float f=Float.valueOf(s);
            this.setOpacity(f);
            try {
                Thread.sleep(40);
            } catch (InterruptedException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_formWindowOpened
    public void getTax(double t) {
        if (t >= 10 && t <= 20) {
            tax = 0.5;
        } else if (t >= 20 && t <= 40) {
            tax = 1.0;
        } else if (t >= 40 && t <= 60) {
            tax = 2.0;
        } else if (t >= 60 && t <= 80) {
            tax = 3.0;
        } else if (t >= 80 && t <= 100) {
            tax = 4.0;
        } else if (t >= 100 && t <= 150) {
            tax = 8.0;
        } else if (t >= 150 && t <= 200) {
            tax = 10.0;
        } else if (t > 200) {
            tax = 15.0;
        }
    }

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Dashboard().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnReceipt;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTotal;
    private javax.swing.JPanel item1;
    private javax.swing.JPanel item10;
    private javax.swing.JPanel item2;
    private javax.swing.JPanel item3;
    private javax.swing.JPanel item4;
    private javax.swing.JPanel item5;
    private javax.swing.JPanel item6;
    private javax.swing.JPanel item7;
    private javax.swing.JPanel item8;
    private javax.swing.JPanel item9;
    private javax.swing.JLabel itemName;
    private javax.swing.JLabel itemName1;
    private javax.swing.JLabel itemName2;
    private javax.swing.JLabel itemName3;
    private javax.swing.JLabel itemName4;
    private javax.swing.JLabel itemName5;
    private javax.swing.JLabel itemName6;
    private javax.swing.JLabel itemName7;
    private javax.swing.JLabel itemName8;
    private javax.swing.JLabel itemName9;
    private javax.swing.JLabel itemPhoto;
    private javax.swing.JLabel itemPhoto1;
    private javax.swing.JLabel itemPhoto2;
    private javax.swing.JLabel itemPhoto3;
    private javax.swing.JLabel itemPhoto4;
    private javax.swing.JLabel itemPhoto5;
    private javax.swing.JLabel itemPhoto6;
    private javax.swing.JLabel itemPhoto7;
    private javax.swing.JLabel itemPhoto8;
    private javax.swing.JLabel itemPhoto9;
    private javax.swing.JCheckBox jCheckBox;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner quantity;
    private javax.swing.JSpinner quantity1;
    private javax.swing.JSpinner quantity2;
    private javax.swing.JSpinner quantity3;
    private javax.swing.JSpinner quantity4;
    private javax.swing.JSpinner quantity5;
    private javax.swing.JSpinner quantity6;
    private javax.swing.JSpinner quantity7;
    private javax.swing.JSpinner quantity8;
    private javax.swing.JSpinner quantity9;
    private javax.swing.JTextArea receipt;
    private javax.swing.JLabel txtDate;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTax;
    private javax.swing.JLabel txtTime;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
