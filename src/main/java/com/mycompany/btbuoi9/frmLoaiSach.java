/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.btbuoi9;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;
import java.util.function.Consumer;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author datcy
 */
public class frmLoaiSach extends javax.swing.JInternalFrame {

    MyLib.SQLUtil sql = new MyLib.SQLUtil();

    /**
     * Creates new form frmLoaiSach
     */
    public frmLoaiSach() {
        initComponents();
        loadData();
        addControl();
    }

    public void addControl() {
        btnThem.addActionListener((e) -> {
            double lastId = sql.execScalar("select Max(LS_ID) from LoaiSach");
            int rs = sql.executeUpdate("insert into LoaiSach(LS_ID, Ten, MoTa) values(?, ?, ?)", ((int) lastId) + 1, txtTenSach.getText(), txtMoTa.getText());
            if (rs != 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại!");
            }
            loadData();
        });
        btnSua.addActionListener((e) -> {
            int selectedRow = tblLoaiSach.getSelectedRow();
            int rs = sql.executeUpdate("update LoaiSach"
                    + " SET Ten = ?, MoTa = ? where LS_ID = ?",
                    txtTenSach.getText(),
                    txtMoTa.getText(),
                    tblLoaiSach.getValueAt(selectedRow, 0)
            );
            if (rs != 0) {
                JOptionPane.showMessageDialog(this, "Sửa thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại!");
            }
            loadData();
        });
        btnXoa.addActionListener((e) -> {
            int selectedRow = tblLoaiSach.getSelectedRow();
            int rs = sql.executeUpdate("delete from LoaiSach"
                    + " where LS_ID = ?",
                    tblLoaiSach.getValueAt(selectedRow, 0)
            );
            if (rs != 0) {
                JOptionPane.showMessageDialog(this, "Xóa thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!");
            }
            loadData();
        });
        tblLoaiSach.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//k cho chọn nhiều dòng
        // Add ListSelectionListener to jTable
        tblLoaiSach.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Check if row selection is done
                int selectedRow = tblLoaiSach.getSelectedRow();
                System.err.println("clicked: " + selectedRow);
                if (selectedRow != -1) {
                    txtTenSach.setText(tblLoaiSach.getValueAt(selectedRow, 1).toString());
                    txtMoTa.setText(tblLoaiSach.getValueAt(selectedRow, 2).toString());
                    System.err.println("clicked");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Không tìm thấy dòng!");
                    System.err.println("click but not found");

                }

            }
        });
//        tblLoaiSach.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                if (!e.getValueIsAdjusting()) {
//                    int selectedRow = tblLoaiSach.getSelectedRow();
//                    System.err.println("clicked: " + selectedRow);
//                    if (selectedRow != -1) {
//                        txtTenSach.setText(tblLoaiSach.getValueAt(selectedRow, 1).toString());
//                        txtMoTa.setText(tblLoaiSach.getValueAt(selectedRow, 2).toString());
//                        System.err.println("clicked");
//                    } else {
//                        JOptionPane.showMessageDialog(rootPane, "Không tìm thấy dòng!");
//                        System.err.println("click but not found");
//
//                    }
//                }
//            }
//        });
    }

    public void loadData() {
        try {
            tblLoaiSach.clearSelection();
            DefaultTableModel dtm = new DefaultTableModel();
            dtm.addColumn("Mã sách");
            dtm.addColumn("Tên sách");
            dtm.addColumn("Mô tả");

            sql.execReader("SELECT * FROM LoaiSach").forEach((Object t) -> {
                Vector vt = new Vector();
                List<Object> tmpList = (List<Object>) t;
                tmpList.forEach((col) -> {
                    vt.add(col.toString());
                });
                dtm.addRow(vt);
            });
            tblLoaiSach.setModel(dtm);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblLoaiSach = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTenSach = new javax.swing.JTextField();
        txtMoTa = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnDong = new javax.swing.JButton();

        tblLoaiSach.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblLoaiSach);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin loại sách"));

        jLabel1.setText("Tên sách:");

        jLabel2.setText("Mô tả:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTenSach, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                    .addComponent(txtMoTa))
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnThem.setText("Thêm");

        btnSua.setText("Sửa");

        btnXoa.setText("Xóa");

        btnDong.setText("Đóng");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa)
                        .addGap(18, 18, 18)
                        .addComponent(btnDong))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnDong))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDong;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblLoaiSach;
    private javax.swing.JTextField txtMoTa;
    private javax.swing.JTextField txtTenSach;
    // End of variables declaration//GEN-END:variables
}
