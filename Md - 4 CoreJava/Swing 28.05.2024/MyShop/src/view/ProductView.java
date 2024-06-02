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

/**
 *
 * @author Magic Box
 */
public class ProductView extends javax.swing.JFrame {

    DbUtil db = new DbUtil();
    PreparedStatement ps;
    ResultSet rs;

    LocalDate currentDate = LocalDate.now();
    java.sql.Date sqlCurrentDate = java.sql.Date.valueOf(currentDate);

    /**
     * Creates new form ProductView
     */
    public ProductView() {
        initComponents();
        showProductOnTable();
        showProductToCombo();
        showStockOnTable();

        comProductName.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                getProductSalesPrice(e);
            }

        });

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

                //int id = rs.getInt("id");
                String name = rs.getString("name");
                float unitPrice = rs.getFloat("salesUnitPrice");
                float quantity = rs.getFloat("salesQuantity");
                float totalPrice = rs.getFloat("salesTotalPrice");

                Date salesDate = rs.getDate("salesDate");

                model.addRow(new Object[]{sl, name, unitPrice, quantity, totalPrice, salesDate});

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

        String sql1 = "select sum(salesTotalPrice) from sales where salesDate between ? and ?";

        String sql = "select sum(totalPrice) from product where purcahseDate between ? and ?";

        try {
            ps = db.getCon().prepareStatement(sql);

            ps.setDate(1, convertUtilDateToSqlDate(fromDate));
            ps.setDate(2, convertUtilDateToSqlDate(toDate));

            rs = ps.executeQuery();

            float totalPurchasePrice = 0;

            while (rs.next()) {

                //int id = rs.getInt("id");
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

                //int id = rs.getInt("id");
                String name = rs.getString("name");
                float unitPrice = rs.getFloat("unitPrice");
                float quantity = rs.getFloat("quantity");
                float totalPrice = rs.getFloat("totalPrice");
                float salesPrice = rs.getFloat("salesPrice");
                Date purcahseDate = rs.getDate("purchaseDate");

                model.addRow(new Object[]{sl, name, unitPrice, quantity, totalPrice, salesPrice, purcahseDate});

                sl += 1;
            }

            ps.close();
            rs.close();
            db.getCon().close();

            String sql1 = "select sum(totalPrice) from product where purcahseDate between ? and ?";

            ps = db.getCon().prepareStatement(sql1);

            ps.setDate(1, convertUtilDateToSqlDate(fromDate));
            ps.setDate(2, convertUtilDateToSqlDate(toDate));

            rs = ps.executeQuery();

            while (rs.next()) {

                //int id = rs.getInt("id");
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

            ps.close();
            db.getCon().close();
            rs.close();

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

            JOptionPane.showMessageDialog(this, "Add Product Successfully");
            addProdductToStock();
            showStockOnTable();
            clear();
            showProductOnTable();

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Add Product Unsuccessfully");
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

    }

    String[] productViewTableColumn = {"id", "Name", "Unit Price", "Quantity", "Total Price", "Sales Price"};
    String[] stockViewTableColumn = {"id", "Name", "Quantity", "Unit Price"};

    public void showProductOnTable() {

        String sql = "select * from product";
        PreparedStatement ps;

        ResultSet rs;

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
                float unitPrice = rs.getFloat("purchasePrice");

                model.addRow(new Object[]{id, name, unitPrice, quantity});

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
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) db.getCon().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtId.getText()));

            ps.executeUpdate();
            ps.close();
            db.getCon();

            JOptionPane.showMessageDialog(this, "Detete Product Successfully");
            clear();
            showProductOnTable();

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Detete Product Unsuccessfully");
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editProduct() {

        String sql = "update product set name=?, unitPrice=?, quantity=?, totalPrice=?, salesPrice=? where id =?";
        PreparedStatement ps;
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

            JOptionPane.showMessageDialog(this, "Update Product Successfully");
            clear();
            showProductOnTable();

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Update Product Unsuccessfully");
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void showProductToCombo() {
//        String sql = "select name from product";
//
//        comProductName.removeAllItems();
//
//        try {
//            ps = db.getCon().prepareStatement(sql);
//
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                String productName = rs.getString("name");
//                comProductName.addItem(productName);
//            }
//
//            ps.close();
//
//            db.getCon().close();
//            rs.close();
//
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
//        }

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
            db.getCon();
            rs.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
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
                float quantity = rs.getFloat("quantity");
                lblStock.setText(quantity + "");

            }

            ps.close();
            db.getCon().close();
            rs.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
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

            ps.close();
            db.getCon().close();
            rs.close();

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
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Return null or handle the error as needed
        }
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

        String sql = "insert into sales(name, salesUnitPrice,salesQuantity,salesTotalPrice,salesDate) "
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

            JOptionPane.showMessageDialog(this, "Add Sales Successfully");
            stockUpdateOnSales();
            showStockOnTable();

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Add Sales Unsuccessfully");
            Logger
                    .getLogger(ProductView.class
                            .getName()).log(Level.SEVERE, null, ex);
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
        jLabel1 = new javax.swing.JLabel();
        Menu = new javax.swing.JPanel();
        btnAddProduct = new javax.swing.JButton();
        btnSalesProduct = new javax.swing.JButton();
        btnStock = new javax.swing.JButton();
        btnReport = new javax.swing.JButton();
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
        btnProductDetele = new javax.swing.JButton();
        btnProductEdit = new javax.swing.JButton();
        btnProductReset = new javax.swing.JButton();
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

        Banner.setBackground(new java.awt.Color(0, 51, 51));
        Banner.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ahmed Academy");
        Banner.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 100));

        getContentPane().add(Banner, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, -1));

        Menu.setBackground(new java.awt.Color(0, 0, 0));
        Menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAddProduct.setBackground(new java.awt.Color(0, 102, 153));
        btnAddProduct.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddProduct.setForeground(new java.awt.Color(255, 255, 255));
        btnAddProduct.setText("Add Product");
        btnAddProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddProductMouseClicked(evt);
            }
        });
        Menu.add(btnAddProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 120, -1));

        btnSalesProduct.setBackground(new java.awt.Color(102, 0, 102));
        btnSalesProduct.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalesProduct.setForeground(new java.awt.Color(255, 255, 255));
        btnSalesProduct.setText("Sales Product");
        btnSalesProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalesProductMouseClicked(evt);
            }
        });
        Menu.add(btnSalesProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 120, -1));

        btnStock.setBackground(new java.awt.Color(0, 102, 0));
        btnStock.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnStock.setForeground(new java.awt.Color(255, 255, 255));
        btnStock.setText("Stock");
        btnStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStockMouseClicked(evt);
            }
        });
        Menu.add(btnStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 120, -1));

        btnReport.setBackground(new java.awt.Color(153, 0, 0));
        btnReport.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReport.setForeground(new java.awt.Color(255, 255, 255));
        btnReport.setText("Report");
        btnReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReportMouseClicked(evt);
            }
        });
        Menu.add(btnReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 120, -1));

        getContentPane().add(Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 170, 400));

        Add.setPreferredSize(new java.awt.Dimension(700, 400));

        jPanel3.setBackground(new java.awt.Color(102, 0, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Add Product");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 60));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("ID");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Name");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Unit Price");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Quantity");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Total Price");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Sales Price");

        txtId.setEditable(false);

        txtQuantity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtQuantityFocusLost(evt);
            }
        });

        txtTotalPrice.setEditable(false);

        btnProductAdd.setBackground(new java.awt.Color(0, 102, 153));
        btnProductAdd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnProductAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnProductAdd.setText("Add");
        btnProductAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductAddMouseClicked(evt);
            }
        });

        btnProductDetele.setBackground(new java.awt.Color(153, 0, 0));
        btnProductDetele.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnProductDetele.setForeground(new java.awt.Color(255, 255, 255));
        btnProductDetele.setText("Delete");
        btnProductDetele.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductDeteleMouseClicked(evt);
            }
        });

        btnProductEdit.setBackground(new java.awt.Color(0, 102, 51));
        btnProductEdit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnProductEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnProductEdit.setText("Edit");
        btnProductEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductEditMouseClicked(evt);
            }
        });

        btnProductReset.setBackground(new java.awt.Color(153, 51, 0));
        btnProductReset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnProductReset.setForeground(new java.awt.Color(255, 255, 255));
        btnProductReset.setText("Reset");
        btnProductReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductResetMouseClicked(evt);
            }
        });

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

        javax.swing.GroupLayout AddLayout = new javax.swing.GroupLayout(Add);
        Add.setLayout(AddLayout);
        AddLayout.setHorizontalGroup(
            AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddLayout.createSequentialGroup()
                .addGroup(AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AddLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(AddLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(AddLayout.createSequentialGroup()
                                .addGroup(AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(27, 27, 27)
                                .addGroup(AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSalesPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(AddLayout.createSequentialGroup()
                                .addGroup(AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                                .addGap(27, 27, 27)
                                .addGroup(AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtQuantity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(AddLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnProductAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnProductReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnProductDetele)
                            .addComponent(btnProductEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        AddLayout.setVerticalGroup(
            AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddLayout.createSequentialGroup()
                        .addGroup(AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTotalPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(17, 17, 17)
                        .addGroup(AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSalesPrice)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnProductAdd)
                            .addComponent(btnProductEdit))
                        .addGap(18, 18, 18)
                        .addGroup(AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnProductReset)
                            .addComponent(btnProductDetele))
                        .addContainerGap(47, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        MainView.addTab("Add", Add);

        jPanel4.setBackground(new java.awt.Color(51, 0, 0));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Product Sales");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 60));

        jLabel12.setText("Name");

        comProductName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comProductName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comProductNameMouseClicked(evt);
            }
        });

        jLabel13.setText("Quantity");

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

        jLabel14.setText("Date");

        jLabel16.setText("UnitPrice");

        jLabel17.setText("Total Price");

        btnSalesSave.setText("Save");
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

        btnSalesEdit.setText("Edit");

        btnSalesReset.setText("Reset");

        btnSalesDelete.setText("Delete");

        jLabel15.setText("Stock");

        javax.swing.GroupLayout SalesLayout = new javax.swing.GroupLayout(Sales);
        Sales.setLayout(SalesLayout);
        SalesLayout.setHorizontalGroup(
            SalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(SalesLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(SalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SalesLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(comProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(salesDate, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SalesLayout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(txtSalesQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(lblStock, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SalesLayout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(txtSalesTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addComponent(btnSalesSave)
                        .addGap(45, 45, 45)
                        .addComponent(btnSalesEdit))
                    .addGroup(SalesLayout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(txtSalesUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addComponent(btnSalesReset)
                        .addGap(45, 45, 45)
                        .addComponent(btnSalesDelete))))
        );
        SalesLayout.setVerticalGroup(
            SalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SalesLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(SalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(SalesLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel14))
                    .addComponent(salesDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(SalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SalesLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SalesLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(txtSalesQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15)
                    .addGroup(SalesLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblStock, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(SalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSalesTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalesSave)
                    .addComponent(btnSalesEdit))
                .addGap(28, 28, 28)
                .addGroup(SalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SalesLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel16))
                    .addComponent(txtSalesUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalesReset)
                    .addComponent(btnSalesDelete))
                .addContainerGap(118, Short.MAX_VALUE))
        );

        MainView.addTab("Sales", Sales);

        Stock.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(51, 0, 51));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Stock");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 59));

        Stock.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 679, -1));

        tblStock.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblStock);

        Stock.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 680, 320));

        MainView.addTab("Stock", Stock);

        Reports.setPreferredSize(new java.awt.Dimension(700, 400));
        Reports.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(51, 0, 51));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Reports");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 60));

        Reports.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, -1));

        jLabel5.setText("From");
        Reports.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));
        Reports.add(dateFromReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 150, -1));

        jLabel19.setText("To");
        Reports.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, -1, -1));
        Reports.add(dateToReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 170, -1));

        btnReportPurchase.setText("Purchase");
        btnReportPurchase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReportPurchaseMouseClicked(evt);
            }
        });
        Reports.add(btnReportPurchase, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        btnReportSales.setText("Sales");
        btnReportSales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReportSalesMouseClicked(evt);
            }
        });
        Reports.add(btnReportSales, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, -1, -1));

        btnReportGrossProfit.setText("Gross Profit");
        btnReportGrossProfit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReportGrossProfitMouseClicked(evt);
            }
        });
        Reports.add(btnReportGrossProfit, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, 110, -1));

        btnReportReset.setText("Reset");
        Reports.add(btnReportReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, -1, -1));

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

        Reports.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 680, 210));

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        lblProfit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblProfit.setForeground(new java.awt.Color(255, 255, 255));
        lblProfit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfit.setText("Profit:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblProfit, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblProfit, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        Reports.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, 160, 40));

        MainView.addTab("Report", Reports);

        getContentPane().add(MainView, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 680, 430));

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
    }//GEN-LAST:event_btnStockMouseClicked

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
    }//GEN-LAST:event_txtSalesQuantityFocusLost

    private void btnSalesSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalesSaveMouseClicked
        // TODO add your handling code here:
        addSales();

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
            java.util.logging.Logger.getLogger(ProductView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JPanel Banner;
    private javax.swing.JTabbedPane MainView;
    private javax.swing.JPanel Menu;
    private javax.swing.JPanel Reports;
    private javax.swing.JPanel Sales;
    private javax.swing.JPanel Stock;
    private javax.swing.JButton btnAddProduct;
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
