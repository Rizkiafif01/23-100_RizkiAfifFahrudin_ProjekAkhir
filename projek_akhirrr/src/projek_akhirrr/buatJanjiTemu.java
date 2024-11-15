/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projek_akhirrr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;

public class buatJanjiTemu extends javax.swing.JFrame {

    Connection konek;
    DefaultComboBoxModel modelCmbPasien;
    DefaultComboBoxModel modelCmbDokter;
    public buatJanjiTemu() {
        initComponents();
        konek = koneksi.getConnection();
        modelCmbPasien = new DefaultComboBoxModel();
        modelCmbDokter = new DefaultComboBoxModel();
        
        cmb_pasien.setModel(modelCmbPasien);
        cmb_dokter.setModel(modelCmbDokter);
        
        loadCmbPasien();
        loadCmbDokter();
    }
    
    private int getIdPasien(){ 
        try{
            String namaDokter = (String) cmb_pasien.getSelectedItem();
            String query = "SELECT id FROM pasien WHERE nama = ?";
            PreparedStatement ps = konek.prepareStatement(query);
            ps.setString(1, namaDokter);
        
            ResultSet rs = ps.executeQuery();
            if(rs.next()) { 
                return rs.getInt("id");
            }
            return -1;
        
        }catch (SQLException e) {
            System.out.println("Error Wak " + e.getMessage());
            return -1;
            }
        }
    
    private int getIdDokter(){ 
        
        try{
            String namaPasien = (String) cmb_dokter.getSelectedItem();
            String query = "SELECT id FROM dokter WHERE nama = ?";
            PreparedStatement ps = konek.prepareStatement(query);
            ps.setString(1, namaPasien);
        
            ResultSet rs = ps.executeQuery();
            if(rs.next()) { 
                return rs.getInt("id");
            }
            return -1;
        
        }catch (SQLException e) {
            System.out.println("Error Wak " + e.getMessage());
            return -1;
            }
        }

    private void loadCmbPasien(){
        modelCmbPasien.removeAllElements();
        
        try{        
            String query = "SELECT nama FROM pasien";
            PreparedStatement ps = konek.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
        
            while(rs.next()){
                String data = rs.getString("nama");
                modelCmbPasien.addElement(data);
            }
        cmb_pasien.setModel(modelCmbPasien);
        }catch(SQLException e){
            System.out.println("Fail " + e.getMessage());
        }
    }
    
    private void loadCmbDokter(){
        modelCmbDokter.removeAllElements();
        
        try{        
            String query = "SELECT nama FROM dokter";
            PreparedStatement ps = konek.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
        
            while(rs.next()){
                String data = rs.getString("nama");
                modelCmbDokter.addElement(data);
            }
        cmb_dokter.setModel(modelCmbDokter);
        }catch(SQLException e){
            System.out.println("Fail " + e.getMessage());
        }
    }
    
    private void addJanjiTemu(){
        try {
            int id_pasien = getIdPasien();
            int id_dokter = getIdDokter();

            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
            String date = formatDate.format(date_choser.getDate());
            String selectedTime = cmb_jam.getSelectedItem().toString();

            String cekJanjiTemuWaktu = "SELECT * FROM janji_temu WHERE id_dokter = ? AND tanggal = ? AND jam = ?";
            PreparedStatement psCekJanjiTemuWaktu = konek.prepareStatement(cekJanjiTemuWaktu);
            psCekJanjiTemuWaktu.setInt(1, id_dokter);
            psCekJanjiTemuWaktu.setString(2, date);
            psCekJanjiTemuWaktu.setString(3, selectedTime);

            ResultSet rsCekJanjiWaktu = psCekJanjiTemuWaktu.executeQuery();

            if (rsCekJanjiWaktu.next()) {
                JOptionPane.showMessageDialog(this, "Dokter sudah memiliki janji temu pada tanggal dan jam ini.\nSilakan pilih jam atau hari lain.", "Peringatan!!!", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String cekJanjiTemuPasien = "SELECT * FROM janji_temu WHERE id_pasien = ? AND id_dokter = ? AND tanggal = ?";
            PreparedStatement psCekJanjiTemuPasien = konek.prepareStatement(cekJanjiTemuPasien);
            psCekJanjiTemuPasien.setInt(1, id_pasien);
            psCekJanjiTemuPasien.setInt(2, id_dokter);
            psCekJanjiTemuPasien.setString(3, date);

            ResultSet rsCekJanjiPasien = psCekJanjiTemuPasien.executeQuery();

            if (rsCekJanjiPasien.next()) {
                JOptionPane.showMessageDialog(this, "Pasien sudah memiliki janji temu dengan dokter ini pada tanggal tersebut.\nSilakan pilih hari lain.", "Peringatan!!!", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String query = "INSERT INTO janji_temu (id_pasien, id_dokter, status, tanggal, jam) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = konek.prepareStatement(query);

            ps.setInt(1, id_pasien);
            ps.setInt(2, id_dokter);
            ps.setString(3, cmb_status.getSelectedItem().toString());
            ps.setString(4, date);
            ps.setString(5, selectedTime);

            ps.execute();
            JOptionPane.showMessageDialog(this, "Data Berhasil Ditambahkan", "Info", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            System.out.println("Fail " + e.getMessage());
        }  
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmb_pasien = new javax.swing.JComboBox<>();
        cmb_dokter = new javax.swing.JComboBox<>();
        cmb_status = new javax.swing.JComboBox<>();
        btn_simpan = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btn_keluar = new javax.swing.JButton();
        date_choser = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        cmb_jam = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Pasien");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 17;
        gridBagConstraints.ipady = 17;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Dokter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 17;
        gridBagConstraints.ipady = 17;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 17;
        gridBagConstraints.ipady = 17;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 17;
        gridBagConstraints.ipady = 17;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel4, gridBagConstraints);

        cmb_pasien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmb_pasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_pasienActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(cmb_pasien, gridBagConstraints);

        cmb_dokter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmb_dokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_dokterActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(cmb_dokter, gridBagConstraints);

        cmb_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Belum Bertemu", "Sudah Bertemu" }));
        cmb_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_statusActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(cmb_status, gridBagConstraints);

        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        jPanel1.add(btn_simpan, gridBagConstraints);

        jButton2.setText("Reset");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        jPanel1.add(jButton2, gridBagConstraints);

        btn_keluar.setText("Keluar");
        btn_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_keluarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        jPanel1.add(btn_keluar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(date_choser, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Jam");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 17;
        gridBagConstraints.ipady = 17;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel5, gridBagConstraints);

        cmb_jam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "09:00:00", "11:00:00", "13:00:00", "15:00:00" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(cmb_jam, gridBagConstraints);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 310, 230));

        jLabel7.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Buat Janji Temu");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\asus\\Downloads\\Healthcare-Featured-Image (1) (1).png")); // NOI18N
        jLabel6.setText("a");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_pasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_pasienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_pasienActionPerformed

    private void cmb_dokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_dokterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_dokterActionPerformed

    private void cmb_statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_statusActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        if(date_choser.getDate()==null){
            JOptionPane.showMessageDialog(this,"Pilih Tanggal Lebih dulu","Peringatan!!!",JOptionPane.WARNING_MESSAGE);
        }else{
            addJanjiTemu();
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_keluarActionPerformed
        main main = new main();
        main.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_keluarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_keluar;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JComboBox<String> cmb_dokter;
    private javax.swing.JComboBox<String> cmb_jam;
    private javax.swing.JComboBox<String> cmb_pasien;
    private javax.swing.JComboBox<String> cmb_status;
    private com.toedter.calendar.JDateChooser date_choser;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
