/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projek_akhirrr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class tambahPasien extends javax.swing.JFrame {
    Connection konek;
    public tambahPasien() {
        initComponents();
        konek = koneksi.getConnection();
    }

   private void addPasien(){
   try{
        String query = "INSERT INTO pasien (nama,umur,jenis_kelamin) VALUES (?,?,?)";
        PreparedStatement ps = konek.prepareStatement(query);  
        ps.setString(1, tf_nama.getText().trim());
        ps.setInt(2, Integer.parseInt(tf_umur.getText().trim()));
        if(rb_lakilaki.isSelected()){
        ps.setString(3, "Laki-Laki");
        }else if(rb_perempuan.isSelected()){
        ps.setString(3,"Perempuan");
        }

        ps.execute();
        JOptionPane.showMessageDialog(this,"Data berhasil Ditambah","AWW",JOptionPane.INFORMATION_MESSAGE);
     
   }catch(SQLException e){
        System.out.println("Gagal "+ e);
   }
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        bg_gender = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tf_nama = new javax.swing.JTextField();
        tf_umur = new javax.swing.JTextField();
        rb_perempuan = new javax.swing.JRadioButton();
        rb_lakilaki = new javax.swing.JRadioButton();
        btnSimpan = new javax.swing.JButton();
        btn_keluar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nama");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 42;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Umur");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 42;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Jenis Kelamin");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 42;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(tf_nama, gridBagConstraints);

        tf_umur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_umurKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(tf_umur, gridBagConstraints);

        bg_gender.add(rb_perempuan);
        rb_perempuan.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        rb_perempuan.setForeground(new java.awt.Color(255, 255, 255));
        rb_perempuan.setText("Perempuan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(rb_perempuan, gridBagConstraints);

        bg_gender.add(rb_lakilaki);
        rb_lakilaki.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        rb_lakilaki.setForeground(new java.awt.Color(255, 255, 255));
        rb_lakilaki.setText("Laki-Laki");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(rb_lakilaki, gridBagConstraints);

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        jPanel1.add(btnSimpan, gridBagConstraints);

        btn_keluar.setText("Keluar");
        btn_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_keluarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        jPanel1.add(btn_keluar, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tambah Pasien");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new java.awt.Insets(13, 0, 13, 0);
        jPanel1.add(jLabel5, gridBagConstraints);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 400, 260));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\asus\\Downloads\\1bg.jpg")); // NOI18N
        jLabel1.setText("a");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 260));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        if(tf_nama.getText().isEmpty()||tf_umur.getText().isEmpty()||bg_gender.getSelection()==null){
        JOptionPane.showMessageDialog(this,"Lengkapi semua data!!","WARNING",JOptionPane.WARNING_MESSAGE);
        }else{
        addPasien();
        tf_nama.setText("");
        tf_umur.setText("");
        bg_gender.clearSelection();
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btn_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_keluarActionPerformed
        // TODO add your handling code here:
        main main = new main();
        main.setVisible(true);
        this.dispose();
        tf_nama.setText("");
        tf_umur.setText("");
        bg_gender.clearSelection();
    }//GEN-LAST:event_btn_keluarActionPerformed

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
         
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bg_gender;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btn_keluar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rb_lakilaki;
    private javax.swing.JRadioButton rb_perempuan;
    private javax.swing.JTextField tf_nama;
    private javax.swing.JTextField tf_umur;
    // End of variables declaration//GEN-END:variables
}
