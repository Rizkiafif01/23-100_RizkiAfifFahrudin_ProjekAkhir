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
import javax.swing.table.DefaultTableModel;
public class main extends javax.swing.JFrame {
    Connection konek;

    DefaultTableModel modelPasien;
    DefaultTableModel modelDokter;
    DefaultTableModel modelJanjiTemu;
    public main() {
        initComponents();
        konek = koneksi.getConnection();
        modelPasien = new DefaultTableModel();
        modelDokter = new DefaultTableModel();
        modelJanjiTemu = new DefaultTableModel();
        
        modelPasien.addColumn("ID");
        modelPasien.addColumn("NAMA");
        modelPasien.addColumn("UMUR");
        modelPasien.addColumn("JENIS KELAMIN");
        tb_pasien.setModel(modelPasien);
        
        modelDokter.addColumn("ID");
        modelDokter.addColumn("NAMA");
        modelDokter.addColumn("UMUR");
        modelDokter.addColumn("JENIS KELAMIN");
        modelDokter.addColumn("SPESIALIS");
        tb_dokter.setModel(modelDokter);
        
        modelJanjiTemu.addColumn("ID");
        modelJanjiTemu.addColumn("Id Pasien");
        modelJanjiTemu.addColumn("Nama Pasien");
        modelJanjiTemu.addColumn("Id Dokter");
        modelJanjiTemu.addColumn("Nama Dokter");
        modelJanjiTemu.addColumn("Tanggal");
        modelJanjiTemu.addColumn("Jam");
        modelJanjiTemu.addColumn("Status");
        modelJanjiTemu.addColumn("Diagnosa");
        modelJanjiTemu.addColumn("Obat");
        tb_janji_temu.setModel(modelJanjiTemu);
        
        
        loadDataPasien();
        loadDataDOkter();
        loadDataJanjiTemu();
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
    
    private void loadDataJanjiTemu(){
    modelJanjiTemu.setRowCount(0);
    
    try {
        String query = "SELECT jt.id,jt.jam, jt.id_pasien, p.nama AS nama_pasien, jt.id_dokter, d.nama AS nama_dokter, " +
                       "jt.tanggal, jt.status, jt.diagnosa, jt.obat " +
                       "FROM janji_temu jt " +
                       "JOIN pasien p ON jt.id_pasien = p.id " +
                       "JOIN dokter d ON jt.id_dokter = d.id";
                       
        PreparedStatement ps = konek.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()) {
            modelJanjiTemu.addRow(new Object[]{
                rs.getInt("id"),
                rs.getInt("id_pasien"),
                rs.getString("nama_pasien"),
                rs.getInt("id_dokter"),        
                rs.getString("nama_dokter"),
                rs.getString("tanggal"),
                rs.getString("jam"),
                rs.getString("status"),
                rs.getString("diagnosa"),
                rs.getString("obat")
            });
        }
    } catch(SQLException e) {
        System.out.println("Fail " + e.getMessage());
    }
    }
    
    private void cariPasien(){
    modelPasien.setRowCount(0);
    try {
        String query;
        PreparedStatement ps;

        if (tf_cari_pasien.getText().trim().isEmpty()) {
            loadDataPasien();
            return;
        } else {
            query = "SELECT * FROM pasien WHERE nama LIKE ? OR umur LIKE ?";
            ps = konek.prepareStatement(query);
            ps.setString(1, "%" + tf_cari_pasien.getText().trim() + "%");
            ps.setString(2, "%" + tf_cari_pasien.getText().trim() + "%");
        }

        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            modelPasien.addRow(new Object[]{
                rs.getInt("id"),
                rs.getString("nama"),
                rs.getInt("umur"),
                rs.getString("jenis_kelamin")
            });
        }
    } catch (SQLException e) {
        System.out.println("Fail " + e.getMessage());
    }
        
    }
    
    private void carijanjiTemu(){
    modelJanjiTemu.setRowCount(0);
    try {
        String query;
        PreparedStatement ps;

        if (tf_cari_janji.getText().trim().isEmpty()) {
            loadDataJanjiTemu();
            return;
        } else {
            query = "SELECT jt.id, p.id AS id_pasien, p.nama AS nama_pasien, "
                  + "d.id AS id_dokter, d.nama AS nama_dokter, jt.tanggal, jt.jam, "
                  + "jt.status, jt.diagnosa, jt.obat "
                  + "FROM janji_temu jt "
                  + "JOIN pasien p ON jt.id_pasien = p.id "
                  + "JOIN dokter d ON jt.id_dokter = d.id "
                  + "WHERE p.nama LIKE ? OR d.nama LIKE ? OR jt.tanggal LIKE ?";
            ps = konek.prepareStatement(query);
            String pencarian = "%" + tf_cari_janji.getText().trim() + "%";
            ps.setString(1, pencarian);
            ps.setString(2, pencarian);
            ps.setString(3, pencarian);
        }

        ResultSet rs = ps.executeQuery();
        
        while(rs.next()) {
            modelJanjiTemu.addRow(new Object[]{
                rs.getInt("id"),
                rs.getInt("id_pasien"),
                rs.getString("nama_pasien"),
                rs.getInt("id_dokter"),        
                rs.getString("nama_dokter"),
                rs.getString("tanggal"),
                rs.getString("jam"),
                rs.getString("status"),
                rs.getString("diagnosa"),
                rs.getString("obat")
            });
        }
    } catch (SQLException e) {
        System.out.println("Fail " + e.getMessage());
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_pasien = new javax.swing.JTable();
        btn_tambah_pasien = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        tf_cari_pasien = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        btn_update_dokter = new javax.swing.JButton();
        btn_tambah_dokter = new javax.swing.JButton();
        btn_delete_dokter = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_dokter = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_janji_temu = new javax.swing.JTable();
        btn_janji_temu = new javax.swing.JButton();
        btn_update_janji = new javax.swing.JButton();
        btn_delete_janjiTemu = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        tf_cari_janji = new javax.swing.JTextField();
        btn_cari_janji = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(36, 54, 66));

        jLabel1.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PASIEN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(458, 458, 458)
                .addComponent(jLabel1)
                .addContainerGap(464, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(36, 54, 66));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 519, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel2, java.awt.BorderLayout.LINE_END);

        jPanel3.setBackground(new java.awt.Color(36, 54, 66));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 519, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel3, java.awt.BorderLayout.LINE_START);

        jPanel4.setBackground(new java.awt.Color(56, 116, 120));

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

        btn_tambah_pasien.setText("Tambah");
        btn_tambah_pasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambah_pasienActionPerformed(evt);
            }
        });

        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_cari.setText("Cari");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("SimSun", 1, 12)); // NOI18N
        jLabel4.setText("Cari Pasien");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(74, 75, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tf_cari_pasien, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(27, 27, 27)
                            .addComponent(btn_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(btn_tambah_pasien)
                            .addGap(43, 43, 43)
                            .addComponent(btn_update)
                            .addGap(38, 38, 38)
                            .addComponent(btn_delete)
                            .addGap(362, 362, 362))))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_cari_pasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cari)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tambah_pasien)
                    .addComponent(btn_update)
                    .addComponent(btn_delete))
                .addContainerGap(121, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel4, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Pasien", jPanel5);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel8.setBackground(new java.awt.Color(59, 30, 84));

        jLabel2.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DOKTER");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(436, 436, 436)
                .addComponent(jLabel2)
                .addContainerGap(470, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(38, 38, 38))
        );

        jPanel6.add(jPanel8, java.awt.BorderLayout.PAGE_START);

        jPanel9.setBackground(new java.awt.Color(59, 30, 84));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 519, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel9, java.awt.BorderLayout.LINE_END);

        jPanel10.setBackground(new java.awt.Color(59, 30, 84));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 519, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel10, java.awt.BorderLayout.LINE_START);

        jPanel11.setBackground(new java.awt.Color(155, 126, 189));

        btn_update_dokter.setText("Update");
        btn_update_dokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_update_dokterActionPerformed(evt);
            }
        });

        btn_tambah_dokter.setText("Tambah");
        btn_tambah_dokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambah_dokterActionPerformed(evt);
            }
        });

        btn_delete_dokter.setText("Delete");
        btn_delete_dokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_dokterActionPerformed(evt);
            }
        });

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
        jScrollPane3.setViewportView(tb_dokter);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(btn_tambah_dokter)
                        .addGap(56, 56, 56)
                        .addComponent(btn_update_dokter)
                        .addGap(54, 54, 54)
                        .addComponent(btn_delete_dokter, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_update_dokter)
                    .addComponent(btn_delete_dokter)
                    .addComponent(btn_tambah_dokter))
                .addGap(109, 109, 109))
        );

        jPanel6.add(jPanel11, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Dokter", jPanel6);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel12.setBackground(new java.awt.Color(16, 55, 92));

        jLabel3.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("JANJI TEMU");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(448, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(416, 416, 416))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel3)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel12, java.awt.BorderLayout.PAGE_START);

        jPanel13.setBackground(new java.awt.Color(16, 55, 92));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 519, Short.MAX_VALUE)
        );

        jPanel7.add(jPanel13, java.awt.BorderLayout.LINE_END);

        jPanel14.setBackground(new java.awt.Color(16, 55, 92));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 519, Short.MAX_VALUE)
        );

        jPanel7.add(jPanel14, java.awt.BorderLayout.LINE_START);

        jPanel15.setBackground(new java.awt.Color(106, 154, 176));

        tb_janji_temu.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tb_janji_temu);

        btn_janji_temu.setText("Buat Janji Temu");
        btn_janji_temu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_janji_temuActionPerformed(evt);
            }
        });

        btn_update_janji.setText("Update");
        btn_update_janji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_update_janjiActionPerformed(evt);
            }
        });

        btn_delete_janjiTemu.setText("Delete");
        btn_delete_janjiTemu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_janjiTemuActionPerformed(evt);
            }
        });

        jLabel5.setText("Cari Janji Temu");

        btn_cari_janji.setText("Cari");
        btn_cari_janji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cari_janjiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 756, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(btn_janji_temu)
                        .addGap(56, 56, 56)
                        .addComponent(btn_update_janji)
                        .addGap(60, 60, 60)
                        .addComponent(btn_delete_janjiTemu)
                        .addGap(83, 83, 83)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(tf_cari_janji)
                        .addGap(18, 18, 18)
                        .addComponent(btn_cari_janji)))
                .addGap(25, 25, 25))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_janji_temu)
                    .addComponent(btn_update_janji)
                    .addComponent(btn_delete_janjiTemu)
                    .addComponent(jLabel5)
                    .addComponent(tf_cari_janji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cari_janji))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel15, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Janji Temu", jPanel7);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 670));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tambah_pasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambah_pasienActionPerformed
        tambahPasien addPasien = new tambahPasien();
        addPasien.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_tambah_pasienActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        updatePasien update = new updatePasien();
        update.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        while (true) {
        try {
            String strid = JOptionPane.showInputDialog(this, "Masukkan ID Pasien yang akan dihapus", "Peringatan", JOptionPane.INFORMATION_MESSAGE);

            if (strid == null) {
                JOptionPane.showMessageDialog(this, "Operasi dibatalkan", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if (strid.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID tidak boleh kosong.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int intid;
            try {
                intid = Integer.parseInt(strid);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID harus berupa angka.", "Kesalahan Input", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            String cekId = "SELECT id FROM pasien WHERE id = ?";
            PreparedStatement psCekId = konek.prepareStatement(cekId);
            psCekId.setInt(1, intid);
            ResultSet rs = psCekId.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "ID pasien tidak ditemukan", "WARNING!!!", JOptionPane.INFORMATION_MESSAGE);
                continue;
            } else {
                String cekJanjiTemu = "SELECT * FROM janji_temu WHERE id_pasien = ?";
                PreparedStatement psCekJanjiTemu = konek.prepareStatement(cekJanjiTemu);
                psCekJanjiTemu.setInt(1, intid);
                ResultSet rsJanjiTemu = psCekJanjiTemu.executeQuery();

                if (rsJanjiTemu.next()) {
                    JOptionPane.showMessageDialog(this, "Pasien ini memiliki janji temu dan tidak dapat dihapus.", "Peringatan!!!", JOptionPane.WARNING_MESSAGE);
                    break;
                } else {
                    int input = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus?", "YAKIN LEK???", JOptionPane.WARNING_MESSAGE);
                    if (input == JOptionPane.YES_OPTION) {
                        String query = "DELETE FROM pasien WHERE id = ?";
                        PreparedStatement ps = konek.prepareStatement(query);
                        ps.setInt(1, intid);
                        ps.execute();
                        loadDataPasien();
                        JOptionPane.showMessageDialog(this, "Data pasien berhasil dihapus.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println("Fail: " + e.getMessage());
            break;
        }
    }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_tambah_dokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambah_dokterActionPerformed
    tambahDokter addDokter = new tambahDokter();
    addDokter.setVisible(true);
    }//GEN-LAST:event_btn_tambah_dokterActionPerformed

    private void btn_update_dokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_update_dokterActionPerformed
    updateDokter update = new updateDokter();
    update.setVisible(true);
    this.dispose();;
    }//GEN-LAST:event_btn_update_dokterActionPerformed

    private void btn_delete_dokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete_dokterActionPerformed
        while (true) {
        try {
            String strid = JOptionPane.showInputDialog(this, "Masukkan ID yang akan dihapus", "Peringatan", JOptionPane.INFORMATION_MESSAGE);

            if (strid == null || strid.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID tidak boleh kosong.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int intid;
            try {
                intid = Integer.parseInt(strid);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID harus berupa angka.", "Kesalahan Input", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            String cekId = "SELECT id FROM dokter WHERE id = ?";
            PreparedStatement psCekId = konek.prepareStatement(cekId);
            psCekId.setInt(1, intid);
            ResultSet rs = psCekId.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "ID tidak ditemukan", "WARNING!!!", JOptionPane.INFORMATION_MESSAGE);
                continue;
            } else {
                String cekJanjiTemu = "SELECT * FROM janji_temu WHERE id_dokter = ?";
                PreparedStatement psCekJanjiTemu = konek.prepareStatement(cekJanjiTemu);
                psCekJanjiTemu.setInt(1, intid);
                ResultSet rsJanjiTemu = psCekJanjiTemu.executeQuery();

                if (rsJanjiTemu.next()) {
                    JOptionPane.showMessageDialog(this, "Dokter ini memiliki janji temu dan tidak dapat dihapus.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    break;
                } else {
                    int input = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus?", "Konfirmasi Penghapusan", JOptionPane.WARNING_MESSAGE);
                    if (input == JOptionPane.YES_OPTION) {
                        String query = "DELETE FROM dokter WHERE id = ?";
                        PreparedStatement ps = konek.prepareStatement(query);
                        ps.setInt(1, intid);
                        ps.execute();
                        loadDataDOkter();
                        JOptionPane.showMessageDialog(this, "Data dokter berhasil dihapus.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println("Fail: " + e.getMessage());
            break;
            }
        }
    }//GEN-LAST:event_btn_delete_dokterActionPerformed

    private void btn_janji_temuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_janji_temuActionPerformed
        buatJanjiTemu addJanji = new buatJanjiTemu();
        addJanji.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_janji_temuActionPerformed

    private void btn_update_janjiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_update_janjiActionPerformed
        updateJanjiTemu updateJT = new updateJanjiTemu();
        updateJT.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_update_janjiActionPerformed

    private void btn_delete_janjiTemuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete_janjiTemuActionPerformed
        while (true) {
        try {
            String strid = JOptionPane.showInputDialog(this, "Masukkan ID yang akan dihapus", "Peringatan", JOptionPane.INFORMATION_MESSAGE);

            if(strid == null){
            JOptionPane.showMessageDialog(this,"Operasi dibatalkan","Inpo",JOptionPane.INFORMATION_MESSAGE);
            return;
            }
            
            if (strid.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID tidak boleh kosong.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int intid;
            try {
                intid = Integer.parseInt(strid);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID harus berupa angka.", "Kesalahan Input", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            String cekId = "SELECT id FROM janji_temu WHERE id = ?";
            PreparedStatement psCekId = konek.prepareStatement(cekId);
            psCekId.setInt(1, intid);
            ResultSet rs = psCekId.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "ID tidak ditemukan", "WARNING!!!", JOptionPane.INFORMATION_MESSAGE);
                continue;
            } else {
                int input = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus?", "YAKIN LEK??", JOptionPane.WARNING_MESSAGE);
                if (input == JOptionPane.YES_OPTION) {
                    String query = "DELETE FROM janji_temu WHERE id = ?";
                    PreparedStatement ps = konek.prepareStatement(query);
                    ps.setInt(1, intid);
                    ps.execute();
                    loadDataPasien();
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus.", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
                loadDataJanjiTemu();
                break;
            }
        } catch (SQLException e) {
            System.out.println("Fail: " + e.getMessage());
            break;
            }
        }
    }//GEN-LAST:event_btn_delete_janjiTemuActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        cariPasien();
    }//GEN-LAST:event_btn_cariActionPerformed

    private void btn_cari_janjiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cari_janjiActionPerformed
        // TODO add your handling code here:
        carijanjiTemu();
    }//GEN-LAST:event_btn_cari_janjiActionPerformed

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
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_cari_janji;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_delete_dokter;
    private javax.swing.JButton btn_delete_janjiTemu;
    private javax.swing.JButton btn_janji_temu;
    private javax.swing.JButton btn_tambah_dokter;
    private javax.swing.JButton btn_tambah_pasien;
    private javax.swing.JButton btn_update;
    private javax.swing.JButton btn_update_dokter;
    private javax.swing.JButton btn_update_janji;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tb_dokter;
    private javax.swing.JTable tb_janji_temu;
    private javax.swing.JTable tb_pasien;
    private javax.swing.JTextField tf_cari_janji;
    private javax.swing.JTextField tf_cari_pasien;
    // End of variables declaration//GEN-END:variables
}
