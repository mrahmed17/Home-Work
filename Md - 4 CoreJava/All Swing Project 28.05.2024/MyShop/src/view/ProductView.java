/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.DbUtil;

public class ProductView extends javax.swing.JFrame {

    DbUtil db = new DbUtil();

    PreparedStatement ps;
    ResultSet rs;

    public static float stockQuantity = 0;

    LocalDate currentDate = LocalDate.now();
    java.sql.Date sqlCurrentDate = java.sql.Date.valueOf(currentDate);

    /**
     * Creates new form ProductView
     */
    public ProductView() {
        initComponents();
        showProductOnTable();
        showStockOnTable();

        comProductName.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                getProductSalesPrice(e);
            }

        });

    }

    public void resetRepot() {

        DefaultTableModel model = new DefaultTableModel();
        tblReports.setModel(model);
        model.setRowCount(0);

    }

    public void getSalesReport() {

        String[] salesViewTableColumn = {"SL", "Name", "Unit Price", "Qunatity", "Total Price", "Date"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(salesViewTableColumn);
        tblReports.setModel(model);

        Date toDate = dateToReport.getDate();
        Date fromDate = dateFromReport.getDate();

        String sql = "select * from sales where salesDate between ? and ?";

        String sql1 = "select sum(salesTotalPrice) from sales where salesDate between ? and ?";

        try {
            ps = db.getCon().prepareStatement(sql);

            ps.setDate(1, convertUtilDateToSqlDate(fromDate));
            ps.setDate(2, convertUtilDateToSqlDate(toDate));

            rs = ps.executeQuery();

            int sl = 1;

            while (rs.next()) {
                String name = rs.getString("name");
                float unitPrice = rs.getFloat("salesUnitPrice");
                float quantity = rs.getFloat("salesQuantity");
                float totalPrice = rs.getFloat("salesTotalPrice");
                Date saleDate = rs.getDate("salesDate");

                model.addRow(new Object[]{sl, name, unitPrice, quantity, totalPrice, saleDate});

                sl += 1;
            }

            ps.close();
            rs.close();
            db.getCon().close();

            ps = db.getCon().prepareStatement(sql1);
            ps.setDate(1, convertUtilDateToSqlDate(fromDate));
            ps.setDate(2, convertUtilDateToSqlDate(toDate));

            rs = ps.executeQuery();

            while (rs.next()) {
                float totalPrice = rs.getFloat("sum(salesTotalPrice)");
                model.addRow(new Object[]{"", "", "", "Total Amount", totalPrice});
            }

            ps.close();
            rs.close();
            db.getCon().close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getGrossProfit() {

        Date toDate = dateToReport.getDate();
        Date fromDate = dateFromReport.getDate();

        String sql = "select sum(totalPrice) from product where purchaseDate between ? and ?";

        String sql1 = "select sum(salesTotalPrice) from sales where salesDate between ? and ?";

        try {
            ps = db.getCon().prepareStatement(sql);

            ps.setDate(1, convertUtilDateToSqlDate(fromDate));
            ps.setDate(2, convertUtilDateToSqlDate(toDate));

            rs = ps.executeQuery();

            float totalPurchasePrice = 0;

            while (rs.next()) {
                totalPurchasePrice = rs.getFloat("sum(totalPrice)");

            }

            ps.close();
            rs.close();
            db.getCon().close();

            // For Sales 
            ps = db.getCon().prepareStatement(sql1);

            ps.setDate(1, convertUtilDateToSqlDate(fromDate));
            ps.setDate(2, convertUtilDateToSqlDate(toDate));

            rs = ps.executeQuery();
            float totalSalesPrice = 0;

            while (rs.next()) {
                totalSalesPrice = rs.getFloat("sum(salesTotalPrice)");
            }

            ps.close();
            rs.close();
            db.getCon().close();

            float grossProfit = totalSalesPrice - totalPurchasePrice;

            lblProfit.setText("Profit: " + grossProfit);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getPurchaseReport() {

        String[] productViewTableColumn = {"SL", "Name", "Unit Price", "Qunatity", "Total Price", "Sales Price", "Date"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(productViewTableColumn);
        tblReports.setModel(model);

        Date toDate = dateToReport.getDate();
        Date fromDate = dateFromReport.getDate();

        String sql = "select * from product where purchaseDate between ? and ?";
        try {
            ps = db.getCon().prepareStatement(sql);
            ps.setDate(1, convertUtilDateToSqlDate(fromDate));
            ps.setDate(2, convertUtilDateToSqlDate(toDate));

            rs = ps.executeQuery();

            int sl = 1;

            while (rs.next()) {
                String name = rs.getString("name");
                float unitPrice = rs.getFloat("unitPrice");
                float quantity = rs.getFloat("quantity");
                float totalPrice = rs.getFloat("totalPrice");
                float salesPrice = rs.getFloat("salesPrice");
                Date purchaseDate = rs.getDate("purchaseDate");

                model.addRow(new Object[]{sl, name, unitPrice, quantity, totalPrice, salesPrice, purchaseDate});

                sl += 1;
            }

            ps.close();
            rs.close();
            db.getCon().close();

            String sql1 = "select sum(totalPrice) from product where purchaseDate between ? and ?";

            ps = db.getCon().prepareStatement(sql1);

            ps.setDate(1, convertUtilDateToSqlDate(fromDate));
            ps.setDate(2, convertUtilDateToSqlDate(toDate));

            rs = ps.executeQuery();

            while (rs.next()) {
                float totalPrice = rs.getFloat("sum(totalPrice)");

                model.addRow(new Object[]{"", "", "", "Total Amount", totalPrice});
            }

            ps.close();
            rs.close();
            db.getCon().close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean getStockProductList() {
        String sql = "select name from stock";
        boolean status = false;
        String purchaseProductName = txtName.getText().trim();

        try {
            ps = db.getCon().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String productName = rs.getString("name");
                if (productName.equalsIgnoreCase(purchaseProductName)) {
                    status = true;
                    break;
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public void addProdductToStock() {

        boolean status = getStockProductList();

        if (status) {
            String sql = "update stock set quantity=quantity+? where name=?";
            try {
                ps = db.getCon().prepareStatement(sql);

                ps.setFloat(1, Float.parseFloat(txtQuantity.getText().trim()));
                ps.setString(2, txtName.getText().trim());

                ps.executeUpdate();
                ps.close();
                db.getCon().close();

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            String sql = "insert into stock(name,purchasePrice,quantity) values (?,?,?)";

            try {
                ps = db.getCon().prepareStatement(sql);

                ps.setString(1, txtName.getText().trim());
                ps.setFloat(2, Float.parseFloat(txtUnitPrice.getText().trim()));
                ps.setFloat(3, Float.parseFloat(txtQuantity.getText().trim()));

                ps.executeUpdate();
                ps.close();
                db.getCon().close();

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void addProduct() {

        String sql = "insert into product(name,unitPrice,quantity,totalPrice,salesPrice,purchaseDate) values(?,?,?,?,?,?)";
        try {
            ps = db.getCon().prepareStatement(sql);

            ps.setString(1, txtName.getText().trim());
            ps.setFloat(2, Float.parseFloat(txtUnitPrice.getText().trim()));
            ps.setFloat(3, Float.parseFloat(txtQuantity.getText().trim()));
            ps.setFloat(4, Float.parseFloat(txtTotalPrice.getText().trim()));
            ps.setFloat(5, Float.parseFloat(txtSalesPrice.getText().trim()));
            ps.setDate(6, sqlCurrentDate);

            ps.executeUpdate();
            ps.close();
            db.getCon().close();

            JOptionPane.showMessageDialog(this, "Product Add  Successfully");
            addProdductToStock();
            showStockOnTable();
            clear();
            showProductOnTable();

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Product Add  Unsuccessfully");
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getTotalPrice() {
        float unitPrice = Float.parseFloat(txtUnitPrice.getText().trim());
        float quantity = Float.parseFloat(txtQuantity.getText().trim());

        float totalPrice = unitPrice * quantity;

        txtTotalPrice.setText(totalPrice + "");

    }

    public void clear() {
        txtId.setText("");
        txtName.setText("");
        txtUnitPrice.setText("");
        txtQuantity.setText("");
        txtTotalPrice.setText("");
        txtSalesPrice.setText("");
         btnAddProduct.setVisible(true);

    }

    String[] productViewTableColumn = {"id", "Name", "Unit Price", "Quantity", "Total Price", "Sales Price"};
    String[] stockViewTableColumn = {"id", "Name", "Quantity", "Unit Price"};

    public void showProductOnTable() {

        String sql = "select * from product";
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(productViewTableColumn);

        tblProductView.setModel(model);
        try {
            ps = db.getCon().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float unitPrice = rs.getFloat("unitPrice");
                float quantity = rs.getFloat("quantity");
                float totalPrice = rs.getFloat("totalPrice");
                float salesPrice = rs.getFloat("salesPrice");

                model.addRow(new Object[]{id, name, unitPrice, quantity, totalPrice, salesPrice});

            }
            rs.close();
            ps.close();
            db.getCon();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showStockOnTable() {

        String sql = "select * from stock";

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(stockViewTableColumn);

        tblStock.setModel(model);

        try {
            ps = db.getCon().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                float quantity = rs.getFloat("quantity");
                float purchasePrice = rs.getFloat("purchasePrice");

                model.addRow(new Object[]{id, name, quantity, purchasePrice});

            }

            rs.close();
            ps.close();
            db.getCon();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteProduct() {

        String sql = "delete from product where id =?";
        try {
            ps = (PreparedStatement) db.getCon().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtId.getText()));

            ps.executeUpdate();
            ps.close();
            db.getCon();

            JOptionPane.showMessageDialog(this, "Product Detete Successfully");
            clear();
            showProductOnTable();
            btnAddProduct.setVisible(true);

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Product Detete Unsuccessfully");
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editProduct() {

        String sql = "update product set name=?, unitPrice=?, quantity=?, totalPrice=?, salesPrice=? where id =?";
        try {
            ps = (PreparedStatement) db.getCon().prepareStatement(sql);

            ps.setString(1, txtName.getText().trim());
            ps.setFloat(2, Float.parseFloat(txtUnitPrice.getText().trim()));
            ps.setFloat(3, Float.parseFloat(txtQuantity.getText().trim()));
            ps.setFloat(4, Float.parseFloat(txtTotalPrice.getText().trim()));
            ps.setFloat(5, Float.parseFloat(txtSalesPrice.getText().trim()));
            ps.setInt(6, Integer.parseInt(txtId.getText()));

            ps.executeUpdate();
            ps.close();
            db.getCon();

            JOptionPane.showMessageDialog(this, "Product Update Successfully");
            clear();
            showProductOnTable();
            btnAddProduct.setVisible(true);

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Product Update Unsuccessfully");
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void showProductToCombo() {
        String sql = "select name from product";
        PreparedStatement ps;
        ResultSet rs;

        comProductName.removeAllItems();

        try {
            ps = db.getCon().prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                String productName = rs.getString("name");
                comProductName.addItem(productName);
            }

            ps.close();
            rs.close();
            db.getCon();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getProductSalesPrice(ItemEvent e) {

        String selectedProductName = "";

        if (e.getStateChange() == ItemEvent.SELECTED) {
            selectedProductName = (String) e.getItem();

            //TODO your actitons
            extractSalesPrice(selectedProductName);
        }

        String sql = "Select quantity from stock where name=?";

        try {
            ps = db.getCon().prepareStatement(sql);
            ps.setString(1, selectedProductName);

            rs = ps.executeQuery();

            while (rs.next()) {
                stockQuantity = rs.getFloat("quantity");
                lblStock.setText(stockQuantity + "");

            }

            ps.close();
            db.getCon().close();
            rs.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void salesValidationForQuantity() {

        float salesQuantity = Float.parseFloat(txtSalesQuantity.getText().trim());

        if (salesQuantity > stockQuantity) {

            JOptionPane.showMessageDialog(this, "Sales is more than stock");
            txtSalesQuantity.setText("0");
            txtSalesQuantity.requestFocus();
        } else {

        }

    }

    public void extractSalesPrice(String productName) {

        String sql = "select salesPrice from product where name=?";

        try {
            ps = db.getCon().prepareStatement(sql);
            ps.setString(1, productName);

            rs = ps.executeQuery();

            while (rs.next()) {
                String salesPrice = rs.getString("salesPrice");
                txtSalesUnitPrice.setText(salesPrice);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void showSalesTable() {
        String[] ProductViewTableColumn = {"ID", "Name", "Unit Price", "Quantity", "salesTotalPrice", "Date"};
        String sql = "SELECT * FROM sales";

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(ProductViewTableColumn);
        tblStock.setModel(model);

        try {
            ps = db.getCon().prepareStatement(sql);
            rs = ps.executeQuery();

            int id = 1;

            while (rs.next()) {
                String name = rs.getString("name");

                float unitPrice = rs.getFloat("salesUnitPrice");
                float quantity = rs.getFloat("salesQuantity");
                float salesTotalPrice = rs.getFloat("salesTotalPrice");
                Date date = rs.getDate("salesDate");

                model.addRow(new Object[]{id, name, unitPrice, quantity, salesTotalPrice, date});

                id += 1;
            }

            rs.close();
            ps.close();
            db.getCon();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getTotalSalesPrice() {

        float quantity = Float.parseFloat(txtSalesQuantity.getText().toString().trim());
        float unitPrice = Float.parseFloat(txtSalesUnitPrice.getText().toString().trim());
        float salesTotalPrice = quantity * unitPrice;
        txtSalesTotalPrice.setText(salesTotalPrice + "");

    }

    public String formatDateToDDMMYYYY(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }

    public static java.sql.Date convertUtilDateToSqlDate(java.util.Date utilDate) {
        if (utilDate != null) {
            return new java.sql.Date(utilDate.getTime());
        }
        return null;
    }

    public static java.sql.Date convertStringToSqlDate(String dateString) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("MM-dd-yyyy");
        try {
            java.util.Date utilDate = inputFormat.parse(dateString);

            // Convert to "yyyy-MM-dd" format
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = outputFormat.format(utilDate);

            return java.sql.Date.valueOf(formattedDate);
        } catch (ParseException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; // Return null or handle the error as needed
    }

    public void stockUpdateOnSales() {
        String sql = "update stock set quantity=quantity-? where name=?";

        try {
            ps = db.getCon().prepareStatement(sql);

            ps.setFloat(1, Float.parseFloat(txtSalesQuantity.getText().trim()));
            ps.setString(2, comProductName.getSelectedItem().toString());

            ps.executeUpdate();

            ps.close();
            db.getCon().close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductView.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addSales() {

        Date date = convertUtilDateToSqlDate(salesDate.getDate());

        String sql = "insert into sales(name, salesUnitPrice, salesQuantity, salesTotalPrice, salesDate) "
                + "values(?,?,?,?,?)";

        try {
            ps = db.getCon().prepareStatement(sql);
            ps.setString(1, comProductName.getSelectedItem().toString());
            ps.setFloat(2, Float.parseFloat(txtSalesUnitPrice.getText()));
            ps.setFloat(3, Float.parseFloat(txtSalesQuantity.getText()));
            ps.setFloat(4, Float.parseFloat(txtSalesTotalPrice.getText()));
            ps.setDate(5, convertUtilDateToSqlDate(date));

            ps.executeUpdate();
            ps.close();
            db.getCon().close();

            JOptionPane.showMessageDialog(this, "Successfully Sales Add");
            stockUpdateOnSales();
            showStockOnTable();

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Unsuccessfully Sales Does not Add");
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

        Banner = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        Menu = new javax.swing.JPanel();
        btnAddProduct = new javax.swing.JButton();
        btnSalesProduct = new javax.swing.JButton();
        btnStock = new javax.swing.JButton();
        btnReport = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        MainView = new javax.swing.JTabbedPane();
        Add = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtUnitPrice = new javax.swing.JTextField();
        txtQuantity = new javax.swing.JTextField();
        txtTotalPrice = new javax.swing.JTextField();
        txtSalesPrice = new javax.swing.JTextField();
        btnProductAdd = new javax.swing.JButton();
        btnProductEdit = new javax.swing.JButton();
        btnProductReset = new javax.swing.JButton();
        btnProductDetele = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductView = new javax.swing.JTable();
        Sales = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        comProductName = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txtSalesQuantity = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtSalesUnitPrice = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtSalesTotalPrice = new javax.swing.JTextField();
        btnSalesSave = new javax.swing.JButton();
        btnSalesEdit = new javax.swing.JButton();
        btnSalesReset = new javax.swing.JButton();
        btnSalesDelete = new javax.swing.JButton();
        salesDate = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        lblStock = new javax.swing.JLabel();
        Stock = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblStock = new javax.swing.JTable();
        Reports = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dateFromReport = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        dateToReport = new com.toedter.calendar.JDateChooser();
        btnReportPurchase = new javax.swing.JButton();
        btnReportSales = new javax.swing.JButton();
        btnReportGrossProfit = new javax.swing.JButton();
        btnReportReset = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblReports = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblProfit = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Banner.setBackground(new java.awt.Color(0, 153, 153));
        Banner.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Tree Planting.png"))); // NOI18N
        Banner.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 80, 80));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Text Color.png"))); // NOI18N
        Banner.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 0, -1, 80));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Text Color.png"))); // NOI18N
        Banner.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 70, 80));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("hmed        cademy");
        Banner.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 100));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Reflection.png"))); // NOI18N
        Banner.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, 80, 80));

        getContentPane().add(Banner, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, -1));

        Menu.setBackground(new java.awt.Color(0, 153, 153));
        Menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAddProduct.setBackground(new java.awt.Color(0, 102, 153));
        btnAddProduct.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddProduct.setForeground(new java.awt.Color(255, 255, 255));
        btnAddProduct.setText("Add Product");
        btnAddProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddProductMouseClicked(evt);
            }
        });
        Menu.add(btnAddProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 110, -1));

        btnSalesProduct.setBackground(new java.awt.Color(102, 0, 102));
        btnSalesProduct.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalesProduct.setForeground(new java.awt.Color(255, 255, 255));
        btnSalesProduct.setText("Sales Product");
        btnSalesProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalesProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalesProductMouseClicked(evt);
            }
        });
        Menu.add(btnSalesProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 110, -1));

        btnStock.setBackground(new java.awt.Color(0, 102, 0));
        btnStock.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnStock.setForeground(new java.awt.Color(255, 255, 255));
        btnStock.setText("Stock");
        btnStock.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStockMouseClicked(evt);
            }
        });
        Menu.add(btnStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 110, -1));

        btnReport.setBackground(new java.awt.Color(153, 0, 0));
        btnReport.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReport.setForeground(new java.awt.Color(255, 255, 255));
        btnReport.setText("Report");
        btnReport.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReportMouseClicked(evt);
            }
        });
        Menu.add(btnReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 110, -1));

        btnExit.setBackground(new java.awt.Color(204, 0, 0));
        btnExit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Power Off Button.png"))); // NOI18N
        btnExit.setText("Exit");
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        Menu.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, 30));

        getContentPane().add(Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 130, 400));

        Add.setPreferredSize(new java.awt.Dimension(700, 400));
        Add.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Purchase Product");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 60));

        Add.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("ID");
        Add.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, 72, 20));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Name");
        Add.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, 49, 20));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Unit Price");
        Add.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, 63, 20));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Quantity");
        Add.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 180, 63, 20));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Total Price");
        Add.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, 63, 23));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Sales Price");
        Add.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, 63, 22));

        txtId.setEditable(false);
        txtId.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtId.setForeground(new java.awt.Color(0, 0, 0));
        Add.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, 130, -1));

        txtName.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtName.setForeground(new java.awt.Color(0, 0, 0));
        Add.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, 130, -1));

        txtUnitPrice.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtUnitPrice.setForeground(new java.awt.Color(0, 0, 0));
        Add.add(txtUnitPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 150, 130, -1));

        txtQuantity.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtQuantity.setForeground(new java.awt.Color(0, 0, 0));
        txtQuantity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtQuantityFocusLost(evt);
            }
        });
        Add.add(txtQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 180, 130, -1));

        txtTotalPrice.setEditable(false);
        txtTotalPrice.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTotalPrice.setForeground(new java.awt.Color(0, 0, 0));
        Add.add(txtTotalPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 220, 130, 23));

        txtSalesPrice.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtSalesPrice.setForeground(new java.awt.Color(0, 0, 0));
        Add.add(txtSalesPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 260, 130, -1));

        btnProductAdd.setBackground(new java.awt.Color(0, 102, 153));
        btnProductAdd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnProductAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnProductAdd.setText("Add");
        btnProductAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProductAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductAddMouseClicked(evt);
            }
        });
        Add.add(btnProductAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 310, 80, -1));

        btnProductEdit.setBackground(new java.awt.Color(0, 102, 51));
        btnProductEdit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnProductEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnProductEdit.setText("Edit");
        btnProductEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProductEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductEditMouseClicked(evt);
            }
        });
        Add.add(btnProductEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 310, 80, -1));

        btnProductReset.setBackground(new java.awt.Color(153, 51, 0));
        btnProductReset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnProductReset.setForeground(new java.awt.Color(255, 255, 255));
        btnProductReset.setText("Reset");
        btnProductReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProductReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductResetMouseClicked(evt);
            }
        });
        Add.add(btnProductReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 360, 80, -1));

        btnProductDetele.setBackground(new java.awt.Color(153, 0, 0));
        btnProductDetele.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnProductDetele.setForeground(new java.awt.Color(255, 255, 255));
        btnProductDetele.setText("Delete");
        btnProductDetele.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProductDetele.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductDeteleMouseClicked(evt);
            }
        });
        Add.add(btnProductDetele, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 360, 80, -1));

        tblProductView.setForeground(new java.awt.Color(0, 0, 0));
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

        Add.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 480, 340));

        MainView.addTab("Add", Add);

        Sales.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(51, 0, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Product Sales");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 60));

        Sales.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 59));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Name");
        Sales.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 75, 22));

        comProductName.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        comProductName.setForeground(new java.awt.Color(0, 0, 0));
        comProductName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comProductName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comProductNameMouseClicked(evt);
            }
        });
        Sales.add(comProductName, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 150, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Quantity");
        Sales.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 75, 22));

        txtSalesQuantity.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtSalesQuantity.setForeground(new java.awt.Color(0, 0, 0));
        txtSalesQuantity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSalesQuantityFocusLost(evt);
            }
        });
        txtSalesQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalesQuantityActionPerformed(evt);
            }
        });
        Sales.add(txtSalesQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 150, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Date");
        Sales.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 38, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("UnitPrice");
        Sales.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 75, -1));

        txtSalesUnitPrice.setEditable(false);
        txtSalesUnitPrice.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtSalesUnitPrice.setForeground(new java.awt.Color(0, 0, 0));
        Sales.add(txtSalesUnitPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, 150, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Total Price");
        Sales.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 75, 22));

        txtSalesTotalPrice.setEditable(false);
        txtSalesTotalPrice.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtSalesTotalPrice.setForeground(new java.awt.Color(0, 0, 0));
        Sales.add(txtSalesTotalPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 150, -1));

        btnSalesSave.setBackground(new java.awt.Color(0, 102, 153));
        btnSalesSave.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalesSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSalesSave.setText("Sale Save");
        btnSalesSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalesSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalesSaveMouseClicked(evt);
            }
        });
        btnSalesSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalesSaveActionPerformed(evt);
            }
        });
        Sales.add(btnSalesSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(412, 230, 90, -1));

        btnSalesEdit.setBackground(new java.awt.Color(102, 0, 102));
        btnSalesEdit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalesEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnSalesEdit.setText("Sale Edit");
        btnSalesEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalesEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalesEditMouseClicked(evt);
            }
        });
        Sales.add(btnSalesEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 230, 90, -1));

        btnSalesReset.setBackground(new java.awt.Color(0, 102, 51));
        btnSalesReset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalesReset.setForeground(new java.awt.Color(255, 255, 255));
        btnSalesReset.setText("Reset");
        btnSalesReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalesReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalesResetMouseClicked(evt);
            }
        });
        Sales.add(btnSalesReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(412, 300, 100, -1));

        btnSalesDelete.setBackground(new java.awt.Color(153, 0, 0));
        btnSalesDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalesDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnSalesDelete.setText("Delete");
        btnSalesDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Sales.add(btnSalesDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 300, 90, -1));

        salesDate.setForeground(new java.awt.Color(0, 0, 0));
        Sales.add(salesDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 150, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Available Product");
        Sales.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, 130, 20));

        lblStock.setBackground(new java.awt.Color(0, 0, 0));
        lblStock.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblStock.setForeground(new java.awt.Color(0, 0, 0));
        Sales.add(lblStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 150, 81, 20));

        MainView.addTab("Sales", Sales);

        Stock.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(0, 51, 0));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Stock");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 59));

        Stock.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, -1));

        tblStock.setForeground(new java.awt.Color(0, 0, 0));
        tblStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblStock);

        Stock.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 700, 330));

        MainView.addTab("Stock", Stock);

        Reports.setPreferredSize(new java.awt.Dimension(700, 400));
        Reports.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(51, 0, 0));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Reports");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 60));

        Reports.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("From");
        Reports.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        dateFromReport.setForeground(new java.awt.Color(0, 0, 0));
        Reports.add(dateFromReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 150, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("To");
        Reports.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        dateToReport.setForeground(new java.awt.Color(0, 0, 0));
        Reports.add(dateToReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 150, -1));

        btnReportPurchase.setBackground(new java.awt.Color(0, 102, 153));
        btnReportPurchase.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReportPurchase.setForeground(new java.awt.Color(255, 255, 255));
        btnReportPurchase.setText("Purchase");
        btnReportPurchase.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReportPurchase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReportPurchaseMouseClicked(evt);
            }
        });
        Reports.add(btnReportPurchase, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 90, -1));

        btnReportSales.setBackground(new java.awt.Color(51, 0, 51));
        btnReportSales.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReportSales.setForeground(new java.awt.Color(255, 255, 255));
        btnReportSales.setText("Sales");
        btnReportSales.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReportSales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReportSalesMouseClicked(evt);
            }
        });
        Reports.add(btnReportSales, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 90, -1));

        btnReportGrossProfit.setBackground(new java.awt.Color(51, 102, 0));
        btnReportGrossProfit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReportGrossProfit.setForeground(new java.awt.Color(255, 255, 255));
        btnReportGrossProfit.setText("Gross Profit");
        btnReportGrossProfit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReportGrossProfit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReportGrossProfitMouseClicked(evt);
            }
        });
        Reports.add(btnReportGrossProfit, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, 100, -1));

        btnReportReset.setBackground(new java.awt.Color(153, 0, 0));
        btnReportReset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReportReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReportReset.setText("Reset");
        btnReportReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReportReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReportResetMouseClicked(evt);
            }
        });
        Reports.add(btnReportReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 100, -1));

        tblReports.setForeground(new java.awt.Color(0, 0, 0));
        tblReports.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblReports);

        Reports.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 700, 220));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblProfit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblProfit.setForeground(new java.awt.Color(0, 0, 0));
        lblProfit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblProfit.setText("Gross Profit:");
        jPanel1.add(lblProfit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 30));

        Reports.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 150, 30));

        MainView.addTab("Report", Reports);

        getContentPane().add(MainView, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 720, 430));

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
        showProductToCombo();
    }//GEN-LAST:event_btnSalesProductMouseClicked

    private void btnStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStockMouseClicked
        // TODO add your handling code here:

        MainView.setSelectedIndex(2);
        getStockProductList();
        showStockOnTable();
        
    }//GEN-LAST:event_btnStockMouseClicked

    private void btnReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportMouseClicked
        // TODO add your handling code here:

        MainView.setSelectedIndex(3);
       resetRepot();
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
        
        btnProductAdd.setVisible(false);

        int rowIndex = tblProductView.getSelectedRow();

        String id = tblProductView.getModel().getValueAt(rowIndex, 0).toString();
        String name = tblProductView.getModel().getValueAt(rowIndex, 1).toString();
        String unitPrice = tblProductView.getModel().getValueAt(rowIndex, 2).toString();
        String quantity = tblProductView.getModel().getValueAt(rowIndex, 3).toString();
        String totalPrice = tblProductView.getModel().getValueAt(rowIndex, 4).toString();
        String salesPrice = tblProductView.getModel().getValueAt(rowIndex, 5).toString();

        txtId.setText(id);
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

    private void txtSalesQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalesQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSalesQuantityActionPerformed

    private void btnSalesSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalesSaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalesSaveActionPerformed

    private void comProductNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comProductNameMouseClicked
        // TODO add your handling code here: 
    }//GEN-LAST:event_comProductNameMouseClicked

    private void txtSalesQuantityFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSalesQuantityFocusLost
        // TODO add your handling code here:
        getTotalSalesPrice();
        salesValidationForQuantity();
    }//GEN-LAST:event_txtSalesQuantityFocusLost

    private void btnSalesSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalesSaveMouseClicked
        // TODO add your handling code here:
        addSales();
        showSalesTable();
    }//GEN-LAST:event_btnSalesSaveMouseClicked

    private void btnReportPurchaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportPurchaseMouseClicked
        // TODO add your handling code here:

        getPurchaseReport();

    }//GEN-LAST:event_btnReportPurchaseMouseClicked

    private void btnReportGrossProfitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportGrossProfitMouseClicked
        // TODO add your handling code here:
        getGrossProfit();
    }//GEN-LAST:event_btnReportGrossProfitMouseClicked

    private void btnReportSalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportSalesMouseClicked
        // TODO add your handling code here:
        getSalesReport();
    }//GEN-LAST:event_btnReportSalesMouseClicked

    private void btnReportResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportResetMouseClicked
        // TODO add your handling code here:
         resetRepot();
    }//GEN-LAST:event_btnReportResetMouseClicked

    private void btnSalesEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalesEditMouseClicked
        // TODO add your handling code here:
        editProduct();
    }//GEN-LAST:event_btnSalesEditMouseClicked

    private void btnSalesResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalesResetMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnSalesResetMouseClicked

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ProductView().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Add;
    private javax.swing.JPanel Banner;
    private javax.swing.JTabbedPane MainView;
    private javax.swing.JPanel Menu;
    private javax.swing.JPanel Reports;
    private javax.swing.JPanel Sales;
    private javax.swing.JPanel Stock;
    private javax.swing.JButton btnAddProduct;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnProductAdd;
    private javax.swing.JButton btnProductDetele;
    private javax.swing.JButton btnProductEdit;
    private javax.swing.JButton btnProductReset;
    private javax.swing.JButton btnReport;
    private javax.swing.JButton btnReportGrossProfit;
    private javax.swing.JButton btnReportPurchase;
    private javax.swing.JButton btnReportReset;
    private javax.swing.JButton btnReportSales;
    private javax.swing.JButton btnSalesDelete;
    private javax.swing.JButton btnSalesEdit;
    private javax.swing.JButton btnSalesProduct;
    private javax.swing.JButton btnSalesReset;
    private javax.swing.JButton btnSalesSave;
    private javax.swing.JButton btnStock;
    private javax.swing.JComboBox<String> comProductName;
    private com.toedter.calendar.JDateChooser dateFromReport;
    private com.toedter.calendar.JDateChooser dateToReport;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblProfit;
    private javax.swing.JLabel lblStock;
    private com.toedter.calendar.JDateChooser salesDate;
    private javax.swing.JTable tblProductView;
    private javax.swing.JTable tblReports;
    private javax.swing.JTable tblStock;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSalesPrice;
    private javax.swing.JTextField txtSalesQuantity;
    private javax.swing.JTextField txtSalesTotalPrice;
    private javax.swing.JTextField txtSalesUnitPrice;
    private javax.swing.JTextField txtTotalPrice;
    private javax.swing.JTextField txtUnitPrice;
    // End of variables declaration//GEN-END:variables

}
