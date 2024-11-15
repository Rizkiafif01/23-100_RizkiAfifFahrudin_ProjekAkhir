/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projek_akhirrr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class updateDokter extends javax.swing.JFrame {
    Connection konek;
    DefaultComboBoxModel modelCmbDokter;
    DefaultTableModel modelDokter;
    
    public updateDokter() {
        initComponents();
        konek = koneksi.getConnection();
        modelCmbDokter = new DefaultComboBoxModel();
        modelDokter = new DefaultTableModel();
        modelDokter.addColumn("ID");
        modelDokter.addColumn("NAMA");
        modelDokter.addColumn("UMUR");
        modelDokter.addColumn("JENIS KELAMIN");
        modelDokter.addColumn("SPESIALIS");
        tb_dokter.setModel(modelDokter);
        
        loadData();
        loadDataDOkter();
    }

    private void loadData(){
    modelCmbDokter.removeAllElements();
    try{
        String query = "SELECT id FROM dokter";
        PreparedStatement ps = konek.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
        String data = rs.getString("id");
        modelCmbDokter.addElement(data);
      }
    }catch(SQLException e){
        System.out.println("Fail "+ e.getMessage());
    }  
    cmb_dokter.setModel(modelCmbDokter);
    }
    
    private void updateDataPasien(){
        try{
            String query = "UPDATE dokter SET nama = ?,umur = ?,jenis_kelamin = ?,spesialis = ? WHERE id = ?";
            PreparedStatement ps = konek.prepareStatement(query);
            ps.setString(1, tf_nama.getText().trim());
            ps.setString(2, tf_umur.getText().trim());
            if(rb_female.isSelected()){
                ps.setString(3,"Perempuan");
            }else if(rb_male.isSelected()){
                ps.setString(3,"Laki-Laki");
            }
            ps.setString(4, tf_spesialis.getText().trim());
            ps.setInt(5,Integer.parseInt(cmb_dokter.getSelectedItem().toString()));
            ps.execute();
            JOptionPane.showMessageDialog(this,"Data Berhasil diperbarui","Info",JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException e){
            System.out.println("Fail " + e.getMessage());
        }
    }
    
    private void loadDataDOkter(){
    modelDokter.setRowCount(0);
    try{
        String query = "SELECT * FROM dokter";
        PreparedStatement ps = konek.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
        
            modelDokter.addRow(new Object []{
            rs.getInt("id"),
            rs.getString("nama"),
            rs.getInt("umur"),
            rs.getString("jenis_kelamin"),
            rs.getString("spesialis")
            });
        }
    }catch(SQLException e){
        System.out.println("Fail" + e.getMessage());
    }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jButton2 = new javax.swing.JButton();
        bgGender = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tf_nama = new javax.swing.JTextField();
        tf_umur = new javax.swing.JTextField();
        tf_spesialis = new javax.swing.JTextField();
        rb_female = new javax.swing.JRadioButton();
        rb_male = new javax.swing.JRadioButton();
        btn_update = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        btn_keluar = new javax.swing.JButton();
        cmb_dokter = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_dokter = new javax.swing.JTable();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Nama");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Umur");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setText("Jenis Kelamin");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setText("Spesialis");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(tf_nama, gridBagConstraints);

        tf_umur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_umurActionPerformed(evt);
            }
        });
        tf_umur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_umurKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(tf_umur, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(tf_spesialis, gridBagConstraints);

        bgGender.add(rb_female);
        rb_female.setText("Perempuan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(rb_female, gridBagConstraints);

        bgGender.add(rb_male);
        rb_male.setText("Laki-Laki");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(rb_male, gridBagConstraints);

        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(btn_update, gridBagConstraints);

        btn_reset.setText("Reset");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        jPanel1.add(btn_reset, gridBagConstraints);

        btn_keluar.setText("Keluar");
        btn_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_keluarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(10, 11, 10, 11);
        jPanel1.add(btn_keluar, gridBagConstraints);

        cmb_dokter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(cmb_dokter, gridBagConstraints);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 430, 270));

        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\asus\\Downloads\\_bgke1 (2) (1).jpg")); // NOI18N
        jLabel6.setText("a");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 340));

        tb_dokter.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tb_dokter);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 340, 640, 260));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_keluarActionPerformed
        main main = new main();
        main.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_keluarActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_resetActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        if(tf_nama.getText().trim().isEmpty()||tf_spesialis.getText().trim().isEmpty()||tf_umur.getText().trim().isEmpty()||bgGender.getSelection()==null){
            JOptionPane.showMessageDialog(this,"Lengkapi Semua Data","Peringatan!!!",JOptionPane.WARNING_MESSAGE);
        }else{
            updateDataPasien();
            loadDataDOkter();
        }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void tf_umurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_umurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_umurActionPerformed

    private void tf_umurKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_umurKeyTyped
        char c = evt.getKeyChar();
        if(!Character.isDigit(c)){
            evt.consume();
        }
    }//GEN-LAST:event_tf_umurKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgGender;
    private javax.swing.JButton btn_keluar;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cmb_dokter;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rb_female;
    private javax.swing.JRadioButton rb_male;
    private javax.swing.JTable tb_dokter;
    private javax.swing.JTextField tf_nama;
    private javax.swing.JTextField tf_spesialis;
    private javax.swing.JTextField tf_umur;
    // End of variables declaration//GEN-END:variables
}
