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

public class updatePasien extends javax.swing.JFrame {
    Connection konek;
    DefaultComboBoxModel modelCmbPasien;
    DefaultTableModel modelPasien;
    
    public updatePasien() {
        initComponents();
        konek = koneksi.getConnection();
        modelCmbPasien = new DefaultComboBoxModel();
        modelPasien = new DefaultTableModel();
        
        modelPasien.addColumn("ID");
        modelPasien.addColumn("NAMA");
        modelPasien.addColumn("UMUR");
        modelPasien.addColumn("JENIS KELAMIN");
        tb_pasien.setModel(modelPasien);
        
        loadData();
        loadDataPasien();
    }
    
    private void loadDataPasien(){
    modelPasien.setRowCount(0);
    try{
        
        String query = "SELECT * FROM pasien";
        PreparedStatement ps = konek.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

         while (rs.next()) {

             modelPasien.addRow(new Object[]{
             rs.getInt("id"),
             rs.getString("nama"),
             rs.getInt("umur"),
             rs.getString("jenis_kelamin")
           });
          }
    
    }catch(SQLException e){
        System.out.println("Fail" + e);
        }
    }
    
    private void loadData(){
    modelCmbPasien.removeAllElements();
    try{
        String query = "SELECT id FROM pasien";
        PreparedStatement ps = konek.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
        String data = rs.getString("id");
        modelCmbPasien.addElement(data);
      }
    }catch(SQLException e){
        System.out.println("Fail "+ e.getMessage());
    }  
    cmbPasien.setModel(modelCmbPasien);
    }
 
    private void updateDataPasien(){
        try{
            String query = "UPDATE pasien SET nama = ?,umur = ?,jenis_kelamin = ? WHERE id = ?";
            PreparedStatement ps = konek.prepareStatement(query);
            ps.setString(1, tf_nama.getText().trim());
            ps.setString(2, tf_umur.getText().trim());
            if(btn_female.isSelected()){
                ps.setString(3,"Perempuan");
            }else if(btn_male.isSelected()){
                ps.setString(3,"Laki-Laki");
            }
            ps.setInt(4,Integer.parseInt(cmbPasien.getSelectedItem().toString()));
            ps.execute();
            JOptionPane.showMessageDialog(this,"Data Berhasil diperbarui","Info",JOptionPane.INFORMATION_MESSAGE);
            loadDataPasien();
        }catch(SQLException e){
            System.out.println("Fail " + e.getMessage());
        }
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        bgGender = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbPasien = new javax.swing.JComboBox<>();
        tf_nama = new javax.swing.JTextField();
        tf_umur = new javax.swing.JTextField();
        btn_female = new javax.swing.JRadioButton();
        btn_male = new javax.swing.JRadioButton();
        btn_update = new javax.swing.JButton();
        btn_kembali = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_pasien = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nama");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Umur");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Jenis Kelamin");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel4, gridBagConstraints);

        cmbPasien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(cmbPasien, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(tf_nama, gridBagConstraints);

        tf_umur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_umurKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(tf_umur, gridBagConstraints);

        bgGender.add(btn_female);
        btn_female.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        btn_female.setForeground(new java.awt.Color(255, 255, 255));
        btn_female.setText("Perempuan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        jPanel1.add(btn_female, gridBagConstraints);

        bgGender.add(btn_male);
        btn_male.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        btn_male.setForeground(new java.awt.Color(255, 255, 255));
        btn_male.setText("Laki-Laki");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        jPanel1.add(btn_male, gridBagConstraints);

        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(btn_update, gridBagConstraints);

        btn_kembali.setText("Kembali");
        btn_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel1.add(btn_kembali, gridBagConstraints);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 360, 230));

        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\asus\\Downloads\\1bg.jpg")); // NOI18N
        jLabel5.setText("a");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 260));

        tb_pasien.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tb_pasien);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 710, 270));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        if(tf_nama.getText().trim().isEmpty()||tf_umur.getText().trim().isEmpty()||bgGender.getSelection()==null){
            JOptionPane.showMessageDialog(this,"Lengkapi Semua Data","Peringatan!!!",JOptionPane.WARNING_MESSAGE);
        }else{
            updateDataPasien();
        }    
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliActionPerformed
       main main = new main();
       main.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_btn_kembaliActionPerformed

    private void tf_umurKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_umurKeyTyped
        char c = evt.getKeyChar();
        if(!Character.isDigit(c)){
            evt.consume();
        }
    }//GEN-LAST:event_tf_umurKeyTyped

    public static void main(String args[]) {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgGender;
    private javax.swing.JRadioButton btn_female;
    private javax.swing.JButton btn_kembali;
    private javax.swing.JRadioButton btn_male;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cmbPasien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_pasien;
    private javax.swing.JTextField tf_nama;
    private javax.swing.JTextField tf_umur;
    // End of variables declaration//GEN-END:variables
}
