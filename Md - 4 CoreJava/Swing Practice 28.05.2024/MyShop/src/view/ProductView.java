/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.DbUtil;

/**
 *
 * @author Magic Box
 */
public class ProductView extends javax.swing.JFrame {

    DbUtil db = new DbUtil();

    /**
     * Creates new form ProductView
     */
    public ProductView() {
        initComponents();
        showProductOnTable();
        
    }

//    private static class PreparedStatement {
//
//        public PreparedStatement() {
//        }
//    }

    public void addProduct(){

        String sql = "insert into product(name,unitPrice,quantity,totalPrice,salesPrice) values(?,?,?,?,?)";

        PreparedStatement ps;
        try {
            ps = (PreparedStatement) db.getCon().prepareStatement(sql);
              
        ps.setString(1,txtName.getText().trim());
        ps.setFloat(2,Float.parseFloat(txtUnitPrice.getText().trim()));
        ps.setFloat(3,Float.parseFloat(txtQuantity.getText().trim()));
        ps.setFloat(4,Float.parseFloat(txtTotalPrice.getText().trim()));
        ps.setFloat(5,Float.parseFloat(txtSalesPrice.getText().trim()));
        
        ps.executeUpdate();
        ps.close();
        db.getCon().close();
            
        JOptionPane.showMessageDialog(this, "Add Product Successfully");
            clear();
            showProductOnTable();
        
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(this, "Add Product Unsuccessfully");
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Add Product Unsuccessfully");            
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }
      

    }
    
    public void getTotalPrice(){
    float unitPrice = Float.parseFloat(txtUnitPrice.getText().trim());
    float quantity = Float.parseFloat(txtQuantity.getText().trim());
    
    float totalPrice=unitPrice*quantity;
    
    txtTotalPrice.setText(totalPrice+"");
    
    }
    
    public void clear(){
    txtID.setText("");
    txtName.setText("");
    txtUnitPrice.setText("");
    txtQuantity.setText("");
    txtTotalPrice.setText("");
    txtSalesPrice.setText("");
    
    }

    String[] productViewTableColumn={"id", "Name","Unit Price", "Quantity","Total Price", "Sales Price"};
    public void showProductOnTable(){
   
    String sql = "select * from product";
    PreparedStatement ps;
   
    ResultSet rs;
    
    DefaultTableModel model=new DefaultTableModel();
    model.setColumnIdentifiers(productViewTableColumn);
   
    tblProductView.setModel(model);
        try {
            ps= db.getCon().prepareStatement(sql);
            rs=ps.executeQuery();
            
            while(rs.next()){
            int id=rs.getInt("id");
            String name=rs.getString("name");
            float unitPrice=rs.getFloat("unitPrice");
            float quantity=rs.getFloat("quantity");
            float salesPrice=rs.getFloat("salesPrice");
            
            model.addRow(new Object[]{id,name,unitPrice,quantity,salesPrice});
            
            }
            rs.close();
            ps.close();
            db.getCon();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteProduct(){
    
    String sql = "delete from product where id =?";
    PreparedStatement ps;
        try {
            ps=(PreparedStatement) db.getCon().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtID.getText()));
            
            ps.executeUpdate();
            ps.close();
            db.getCon();
            
            JOptionPane.showMessageDialog(this, "Detete Product Successfully");
            clear();
            showProductOnTable(); 
            
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Detete Product Unsuccessfully");
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Detete Product Unsuccessfully");
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editProduct(){
    
    String sql = "update product set name=?, unitPrice=?, quantity=?, totalPrice=?, salesPrice=? where id =?";
    PreparedStatement ps;
        try {
        ps=(PreparedStatement) db.getCon().prepareStatement(sql);
       
        ps.setString(1,txtName.getText().trim());
        ps.setFloat(2,Float.parseFloat(txtUnitPrice.getText().trim()));
        ps.setFloat(3,Float.parseFloat(txtQuantity.getText().trim()));
        ps.setFloat(4,Float.parseFloat(txtTotalPrice.getText().trim()));
        ps.setFloat(5,Float.parseFloat(txtSalesPrice.getText().trim()));
        ps.setInt(6,Integer.parseInt(txtID.getText()));
                 
          ps.executeUpdate();
          ps.close();
          db.getCon();
            
          JOptionPane.showMessageDialog(this, "Update Product Successfully");
            clear();
            showProductOnTable(); 
        
            
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Update Product Unsuccessfully");
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Update Product Unsuccessfully");
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnAddProduct = new javax.swing.JButton();
        btnSalesProduct = new javax.swing.JButton();
        btnStocks = new javax.swing.JButton();
        btnReport = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        MainView = new javax.swing.JTabbedPane();
        Add = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtSalesPrice = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        txtID = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtUnitPrice = new javax.swing.JTextField();
        txtQuantity = new javax.swing.JTextField();
        txtTotalPrice = new javax.swing.JTextField();
        txySalesPrice = new javax.swing.JTextField();
        btnProductAdd = new javax.swing.JButton();
        btnProductDetele = new javax.swing.JButton();
        btnProductEdit = new javax.swing.JButton();
        btnProductReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductView = new javax.swing.JTable();
        Sales = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        Stock = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        Report = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAddProduct.setText("Add Product");
        btnAddProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddProductMouseClicked(evt);
            }
        });
        jPanel1.add(btnAddProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, -1));

        btnSalesProduct.setText("Sales");
        btnSalesProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalesProductMouseClicked(evt);
            }
        });
        btnSalesProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalesProductActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalesProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 150, -1));

        btnStocks.setText("Stock");
        btnStocks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStocksMouseClicked(evt);
            }
        });
        jPanel1.add(btnStocks, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 150, -1));

        btnReport.setText("Report");
        btnReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReportMouseClicked(evt);
            }
        });
        jPanel1.add(btnReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 150, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 170, 400));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ahmed Academy");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 100));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, -1));

        Add.setBackground(new java.awt.Color(255, 255, 204));
        Add.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 102, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Add Product");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 70));

        Add.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel6.setText("ID");
        Add.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 78, 20));

        jLabel7.setText("Name");
        Add.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 78, 20));

        jLabel8.setText("Unit Price");
        Add.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 78, 20));

        jLabel9.setText("Quantity");
        Add.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 78, 20));

        jLabel10.setText("Total Price");
        Add.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 78, -1));

        txtSalesPrice.setText("Sales Price");
        Add.add(txtSalesPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 78, -1));

        jTextField11.setText("jTextField11");
        Add.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 311, -1, 0));

        txtID.setEditable(false);
        Add.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 130, -1));
        Add.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 130, -1));
        Add.add(txtUnitPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 130, -1));

        txtQuantity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtQuantityFocusLost(evt);
            }
        });
        Add.add(txtQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 130, -1));

        txtTotalPrice.setEditable(false);
        Add.add(txtTotalPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 130, -1));
        Add.add(txySalesPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 130, -1));

        btnProductAdd.setText("Add");
        btnProductAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductAddMouseClicked(evt);
            }
        });
        Add.add(btnProductAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 60, -1));

        btnProductDetele.setText("Delete");
        btnProductDetele.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductDeteleMouseClicked(evt);
            }
        });
        Add.add(btnProductDetele, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, -1, -1));

        btnProductEdit.setText("Edit");
        btnProductEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductEditMouseClicked(evt);
            }
        });
        Add.add(btnProductEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 300, 60, -1));

        btnProductReset.setText("Reset");
        btnProductReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductResetMouseClicked(evt);
            }
        });
        Add.add(btnProductReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, -1, -1));

        tblProductView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblProductView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductViewMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProductView);

        Add.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 460, 340));

        MainView.addTab("Add", Add);

        Sales.setBackground(new java.awt.Color(255, 255, 204));
        Sales.setMaximumSize(new java.awt.Dimension(800, 500));

        jPanel4.setBackground(new java.awt.Color(255, 204, 0));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Product Sales");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 60));

        jLabel12.setText("Name");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel13.setText("Quantity");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel14.setText("Date");

        jLabel15.setText("29 May 2024");

        jLabel16.setText("UnitPrice");

        jLabel17.setText("Total Price");

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Edit");

        jButton3.setText("Reset");

        jButton4.setText("Delete");

        javax.swing.GroupLayout SalesLayout = new javax.swing.GroupLayout(Sales);
        Sales.setLayout(SalesLayout);
        SalesLayout.setHorizontalGroup(
            SalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
            .addGroup(SalesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(SalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(SalesLayout.createSequentialGroup()
                        .addGroup(SalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(SalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1, 0, 130, Short.MAX_VALUE)
                            .addComponent(jTextField2)))
                    .addGroup(SalesLayout.createSequentialGroup()
                        .addGroup(SalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3)
                            .addComponent(jButton1))
                        .addGap(55, 55, 55)))
                .addGroup(SalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SalesLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(SalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(SalesLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(SalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(SalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(SalesLayout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );
        SalesLayout.setVerticalGroup(
            SalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SalesLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addGap(25, 25, 25)
                .addGroup(SalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addGroup(SalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(34, 34, 34)
                .addGroup(SalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(0, 127, Short.MAX_VALUE))
        );

        MainView.addTab("Sales", Sales);

        jPanel5.setBackground(new java.awt.Color(255, 0, 51));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Stock");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 60));

        javax.swing.GroupLayout StockLayout = new javax.swing.GroupLayout(Stock);
        Stock.setLayout(StockLayout);
        StockLayout.setHorizontalGroup(
            StockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StockLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        StockLayout.setVerticalGroup(
            StockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StockLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        MainView.addTab("Stock", Stock);

        jLabel5.setText("Report");

        javax.swing.GroupLayout ReportLayout = new javax.swing.GroupLayout(Report);
        Report.setLayout(ReportLayout);
        ReportLayout.setHorizontalGroup(
            ReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportLayout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(269, Short.MAX_VALUE))
        );
        ReportLayout.setVerticalGroup(
            ReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportLayout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(146, Short.MAX_VALUE))
        );

        MainView.addTab("Report", Report);

        getContentPane().add(MainView, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 710, 440));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddProductMouseClicked
        // TODO add your handling code here:

        MainView.setSelectedIndex(0);
    }//GEN-LAST:event_btnAddProductMouseClicked

    private void btnSalesProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalesProductMouseClicked
        // TODO add your handling code here:

        MainView.setSelectedIndex(1);
    }//GEN-LAST:event_btnSalesProductMouseClicked

    private void btnStocksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStocksMouseClicked
        // TODO add your handling code here:

        MainView.setSelectedIndex(2);
    }//GEN-LAST:event_btnStocksMouseClicked

    private void btnReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportMouseClicked
        // TODO add your handling code here:

        MainView.setSelectedIndex(3);
    }//GEN-LAST:event_btnReportMouseClicked

    private void btnProductAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductAddMouseClicked
        // TODO add your handling code here:
        addProduct();

    }//GEN-LAST:event_btnProductAddMouseClicked

    private void txtQuantityFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtQuantityFocusLost
        // TODO add your handling code here:
        
        getTotalPrice();
    }//GEN-LAST:event_txtQuantityFocusLost

    private void btnProductResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductResetMouseClicked
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnProductResetMouseClicked

    private void tblProductViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductViewMouseClicked
        // TODO add your handling code here:
        
        int rowIndex=tblProductView.getSelectedRow();
        
        String id =tblProductView.getModel().getValueAt(rowIndex, 0).toString();
        String name =tblProductView.getModel().getValueAt(rowIndex, 1).toString();
        String unitPrice =tblProductView.getModel().getValueAt(rowIndex, 2).toString();
        String quantity =tblProductView.getModel().getValueAt(rowIndex, 3).toString();
        String totalPrice =tblProductView.getModel().getValueAt(rowIndex, 4).toString();
        String salesPrice =tblProductView.getModel().getValueAt(rowIndex, 5).toString();
        
        
        txtID.setText(id);
        txtName.setText(name);
        txtUnitPrice.setText(unitPrice);
        txtQuantity.setText(quantity);
        txtTotalPrice.setText(totalPrice);
        txtSalesPrice.setText(salesPrice);
        
    }//GEN-LAST:event_tblProductViewMouseClicked

    private void btnProductDeteleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductDeteleMouseClicked
        // TODO add your handling code here:
        deleteProduct();
    }//GEN-LAST:event_btnProductDeteleMouseClicked

    private void btnProductEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductEditMouseClicked
        // TODO add your handling code here:
        
        editProduct();
    }//GEN-LAST:event_btnProductEditMouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSalesProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalesProductActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalesProductActionPerformed

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
            java.util.logging.Logger.getLogger(ProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Add;
    private javax.swing.JTabbedPane MainView;
    private javax.swing.JPanel Report;
    private javax.swing.JPanel Sales;
    private javax.swing.JPanel Stock;
    private javax.swing.JButton btnAddProduct;
    private javax.swing.JButton btnProductAdd;
    private javax.swing.JButton btnProductDetele;
    private javax.swing.JButton btnProductEdit;
    private javax.swing.JButton btnProductReset;
    private javax.swing.JButton btnReport;
    private javax.swing.JButton btnSalesProduct;
    private javax.swing.JButton btnStocks;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTable tblProductView;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JLabel txtSalesPrice;
    private javax.swing.JTextField txtTotalPrice;
    private javax.swing.JTextField txtUnitPrice;
    private javax.swing.JTextField txySalesPrice;
    // End of variables declaration//GEN-END:variables

}
