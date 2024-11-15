/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projek_akhirrr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class tambahDokter extends javax.swing.JFrame {
    Connection konek;
    public tambahDokter() {
        initComponents();
        konek = koneksi.getConnection();
    }
    
    private void addDokter(){
    try{
        String query = "INSERT INTO dokter (nama,umur,jenis_kelamin,spesialis) VALUES (?,?,?,?)";
        PreparedStatement ps = konek.prepareStatement(query);  
        ps.setString(1, tf_nama.getText().trim());
        ps.setInt(2, Integer.parseInt(tf_umur.getText().trim()));
        if(rb_lakilaki.isSelected()){
            ps.setString(3, "Laki-Laki");
        }else if(rb_perempuan.isSelected()){
            ps.setString(3,"Perempuan");
        }
        ps.setString(4,tf_spesialis.getText().trim());

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

        bgGender = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tf_nama = new javax.swing.JTextField();
        tf_umur = new javax.swing.JTextField();
        tf_spesialis = new javax.swing.JTextField();
        rb_perempuan = new javax.swing.JRadioButton();
        rb_lakilaki = new javax.swing.JRadioButton();
        btn_tambah = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("Nama");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 19;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 102));
        jLabel2.setText("Umur");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 19;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 102));
        jLabel3.setText("Jenis Kelamin");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 19;
        gridBagConstraints.ipady = 19;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 102));
        jLabel4.setText("Spesialis");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 19;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(tf_nama, gridBagConstraints);

        tf_umur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_umurKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(tf_umur, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(tf_spesialis, gridBagConstraints);

        bgGender.add(rb_perempuan);
        rb_perempuan.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        rb_perempuan.setForeground(new java.awt.Color(0, 51, 51));
        rb_perempuan.setText("Perempuan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        jPanel1.add(rb_perempuan, gridBagConstraints);

        bgGender.add(rb_lakilaki);
        rb_lakilaki.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        rb_lakilaki.setForeground(new java.awt.Color(0, 51, 51));
        rb_lakilaki.setText("Laki-Laki");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanel1.add(rb_lakilaki, gridBagConstraints);

        btn_tambah.setText("Tambah");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(btn_tambah, gridBagConstraints);

        jButton2.setText("Reset");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(8, 9, 8, 9);
        jPanel1.add(jButton2, gridBagConstraints);

        jButton3.setText("Keluar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel1.add(jButton3, gridBagConstraints);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 400, 250));

        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\asus\\Downloads\\_bgke1 (2) (1).jpg")); // NOI18N
        jLabel6.setText("a");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, 410));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        if(tf_nama.getText().trim().isEmpty()||tf_umur.getText().trim().isEmpty()||bgGender.getSelection()==null||tf_spesialis.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,"Lengkapi semua data!!","WARNING",JOptionPane.WARNING_MESSAGE);
        }else{
            addDokter();
            tf_nama.setText("");
            tf_umur.setText("");
            bgGender.clearSelection();
            tf_spesialis.setText("");
        }
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        main main = new main();
        main.setVisible(true);
        this.dispose();
        tf_nama.setText("");
        tf_umur.setText("");
        bgGender.clearSelection();
    }//GEN-LAST:event_jButton3ActionPerformed

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
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rb_lakilaki;
    private javax.swing.JRadioButton rb_perempuan;
    private javax.swing.JTextField tf_nama;
    private javax.swing.JTextField tf_spesialis;
    private javax.swing.JTextField tf_umur;
    // End of variables declaration//GEN-END:variables
}
