/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps09055_dinhhoangkhang_asm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author root
 */
public class QLSV1 extends javax.swing.JFrame {

    boolean fill = false;
    Vector data = new Vector();
    Vector col = new Vector();
    Connection con = null;
    PreparedStatement pstDetails = null;
    PreparedStatement pstInsert = null;
    PreparedStatement pstDelete = null;
    PreparedStatement pstUpdate = null;
    String sqlInsert = "Insert into STUDENTS (masv,hoten,email,sdt,gioitinh,diachi,hinh) values(?,?,?,?,?,?,?)";
    String sqlDelete = "Delete from STUDENTS where masv=?";
    String sqlUpdate = "Update Students set masv=?, hoten=?, email=? , sdt=?, gioitinh=?, diachi=? , hinh=? where masv=?";
    ResultSet rts;

    /**
     * Creates new form QLSV1
     */
    public QLSV1() {
        initComponents();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=FPT;userName=sa;password=123");
            pstInsert = con.prepareStatement(this.sqlInsert);
            pstUpdate = con.prepareStatement(this.sqlUpdate);
            pstDelete = con.prepareStatement(this.sqlDelete);
            pstDetails = con.prepareStatement("select * from STUDENTS", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rts = pstDetails.executeQuery();
            this.loadData();
            fill = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: Connect to database is false!");
        }
        up.setEnabled(false);
    }
    private String header[] = {"Mã SV", "Họ Tên", "Email", "Số ĐT", "Giới tính", "Địa chỉ", "Hình"};
    private DefaultTableModel show = new DefaultTableModel(header, 0);

    private void loadData() throws SQLException {
        String sql = "Select [masv] from STUDENTS";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            data.removeAllElements();
            if (!fill) {
                //get header
                ResultSetMetaData rsmd = rs.getMetaData();
                int n = rsmd.getColumnCount();
            }
            //get data
            while (rs.next()) {
                Vector v = new Vector();
                //data.add(v);.

            }
            TableModel tbl = new DefaultTableModel(data, col);
            this.bang.setModel(tbl);
            rs.close();
            st.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    boolean validates() {
        if (ten.getText().matches("^\\w+$") == false) {
            JOptionPane.showMessageDialog(this, "Tên không được để trống", "Chú ý", 1);
            ten.requestFocus();
            return false;
        }
        String pName = this.ten.getText().trim();
        Iterator it = data.iterator();
        while (it.hasNext()) {
            Vector v = (Vector) it.next();
            String name = ((String) v.get(0)).trim();
            if (pName.equalsIgnoreCase(name)) {
                JOptionPane.showMessageDialog(this, "Tên Sinh Viên  này đã tồn tại!");
                this.ten.grabFocus();
                return false;
            }
        }
        if (diachi.getText().matches("^\\w+$") == true) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống ", "Chú ý", 1);
            diachi.requestFocus();
            return false;
        }
        if (mail.getText().matches("^\\w+$") == false) {
            JOptionPane.showMessageDialog(this, "Email không được để trống ", "Chú ý", 1);
            mail.requestFocus();
            return false;
        }
        if (sdt.getText().matches("^\\d{10,11}$") == false) {
            JOptionPane.showMessageDialog(this, "Số ĐT không được để trống và phải là 10-11 số ", "Chú ý", 1);
            sdt.requestFocus();
            return false;
        }
        return true;
    }
    private void clearForm() {
        ten.setText("");
        ma.setText("");
        mail.setText("");
        sdt.setText("");
        diachi.setText("");
        ma.requestFocus();
        }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ma = new javax.swing.JTextField();
        ten = new javax.swing.JTextField();
        mail = new javax.swing.JTextField();
        sdt = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        diachi = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        bang = new javax.swing.JTable();
        moi = new javax.swing.JButton();
        xoa = new javax.swing.JButton();
        luu = new javax.swing.JButton();
        up = new javax.swing.JButton();
        hinh = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản Lý Sinh Viên");

        jLabel1.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản Lý Sinh Viên");

        jLabel2.setFont(new java.awt.Font("DialogInput", 1, 12)); // NOI18N
        jLabel2.setText("Mã SV:");

        jLabel3.setFont(new java.awt.Font("DialogInput", 1, 12)); // NOI18N
        jLabel3.setText("Họ tên:");

        jLabel4.setFont(new java.awt.Font("DialogInput", 1, 12)); // NOI18N
        jLabel4.setText("Email:");

        jLabel5.setFont(new java.awt.Font("DialogInput", 1, 12)); // NOI18N
        jLabel5.setText("Số ĐT:");

        jLabel6.setFont(new java.awt.Font("DialogInput", 1, 12)); // NOI18N
        jLabel6.setText("Giới Tính:");

        jLabel7.setFont(new java.awt.Font("DialogInput", 1, 12)); // NOI18N
        jLabel7.setText("Địa Chỉ:");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("DialogInput", 1, 12)); // NOI18N
        jRadioButton1.setText("Nam");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("DialogInput", 1, 12)); // NOI18N
        jRadioButton2.setText("Nữ");

        diachi.setColumns(20);
        diachi.setRows(5);
        jScrollPane1.setViewportView(diachi);

        bang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SV", "Họ tên", "Email", "Số ĐT", "Giới Tính", "Địa chỉ", "Hình"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(bang);

        moi.setFont(new java.awt.Font("DialogInput", 1, 12)); // NOI18N
        moi.setText("New");
        moi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moiActionPerformed(evt);
            }
        });

        xoa.setFont(new java.awt.Font("DialogInput", 1, 12)); // NOI18N
        xoa.setText("Delete");

        luu.setFont(new java.awt.Font("DialogInput", 1, 12)); // NOI18N
        luu.setText("Save");
        luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                luuActionPerformed(evt);
            }
        });

        up.setFont(new java.awt.Font("DialogInput", 1, 12)); // NOI18N
        up.setText("Update");

        hinh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout hinhLayout = new javax.swing.GroupLayout(hinh);
        hinh.setLayout(hinhLayout);
        hinhLayout.setHorizontalGroup(
            hinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        hinhLayout.setVerticalGroup(
            hinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton2))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ma)
                            .addComponent(ten)
                            .addComponent(mail)
                            .addComponent(sdt))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(moi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(xoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(up, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(luu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(hinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(ma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(mail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(sdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2)))
                    .addComponent(hinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(moi)
                            .addComponent(luu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(xoa)
                            .addComponent(up))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void moiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moiActionPerformed
        // new
        ma.setText("");
        ten.setText("");
        mail.setText("");
        sdt.setText("");
        diachi.setText("");
    }//GEN-LAST:event_moiActionPerformed

    private void luuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_luuActionPerformed
        // save
        if (!validates()) {
            return;
        }
        String id = ma.getText();
        String name = ten.getText();
        String addr = diachi.getText();
        String email = mail.getText();
        String phone = sdt.getText();
        try {
            pstInsert.setString(1, id);
            pstInsert.setString(2, name);
            pstInsert.setString(3, email);
            pstInsert.setString(4, phone);
            pstInsert.setString(6, addr);
            int addRows = pstInsert.executeUpdate();
            this.loadData();
            clearForm();
            if (addRows > 0) {
                JOptionPane.showMessageDialog(this, "Students Details Have Been Save", "Successfull", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to save data in database", "Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_luuActionPerformed

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
            java.util.logging.Logger.getLogger(QLSV1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLSV1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLSV1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLSV1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLSV1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bang;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextArea diachi;
    private javax.swing.JPanel hinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton luu;
    private javax.swing.JTextField ma;
    private javax.swing.JTextField mail;
    private javax.swing.JButton moi;
    private javax.swing.JTextField sdt;
    private javax.swing.JTextField ten;
    private javax.swing.JButton up;
    private javax.swing.JButton xoa;
    // End of variables declaration//GEN-END:variables
}
