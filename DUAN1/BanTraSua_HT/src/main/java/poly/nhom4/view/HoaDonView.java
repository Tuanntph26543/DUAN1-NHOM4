/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package poly.nhom4.view;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import poly.nhom4.domainmodel.HoaDonChiTiet;
import poly.nhom4.reponse.HoaDonReponse;
import poly.nhom4.service.HDCTService;
import poly.nhom4.service.QLHDService;
import poly.nhom4.service.UserTTService;
import poly.nhom4.service.impl.HDCTIplm;
import poly.nhom4.service.impl.QLHDServiceIplm;
import poly.nhom4.service.impl.UserTTServiceIplm;

/**
 *
 * @author ACER
 */
public class HoaDonView extends javax.swing.JFrame {

    private final QLHDService qLHDService;
    private final HDCTService hDCTService;
    DefaultTableModel defaultTableModel;
    private final UserTTService userTTService;

    /**
     * Creates new form TrangChu
     */
    public HoaDonView(int ma) {
        initComponents();
        userTTService = new UserTTServiceIplm();
        qLHDService = new QLHDServiceIplm();
        hDCTService = new HDCTIplm();
        String ma1 = String.valueOf(ma);
        lblMaNVLay.setText(ma1);
        lblThongTinDn.setText(userTTService.getUSerByMaNV(ma).getHOTEN());
        lblChucVu.setText(userTTService.getUSerByMaNV(ma).getCHUCVU().getTENCV());
        addrowHD();
        addrowHDCT();
        addrowHD2();
        dongHo();
    }

    public void dongHo() {
        new Thread() {
            public void run() {
                while (true) {
                    Calendar dh = new GregorianCalendar();
                    int hours = dh.get(Calendar.HOUR);
                    int minute = dh.get(Calendar.MINUTE);
                    int giay = dh.get(Calendar.SECOND);
                    int pm_am = dh.get(Calendar.AM_PM);
                    String day_night;
                    if (pm_am == 1) {
                        day_night = "PM";
                    } else {
                        day_night = "AM";
                    }
                    int gio;
                    if (hours == 0) {
                        gio = 12;
                    } else {
                        gio = hours;
                    }
                    lbl_DongHo.setText(gio + ":" + minute + ":" + giay + ":" + day_night);
                }
            }
        }.start();
    }

    public void addrowHD() {
        List<HoaDonReponse> list = qLHDService.getAllHDByTinhTrangCXN();
        defaultTableModel = (DefaultTableModel) tbHoaDon.getModel();
        defaultTableModel.setRowCount(0);
        for (HoaDonReponse x : list) {
            defaultTableModel.addRow(new Object[]{
                x.getMAHD(), x.getNHANVIEN(), x.getNgayTao(), x.getKHACHHANG(), x.layBan(x.getMAHD()), x.layTrangThai(x.getTrangThai())
            });
        }
        tbHoaDon.setRowSelectionInterval(0, 0);
    }

    public void addrowHD2() {
        List<HoaDonReponse> list = qLHDService.getAll1();
        defaultTableModel = (DefaultTableModel) tbHD.getModel();
        defaultTableModel.setRowCount(0);
        for (HoaDonReponse x : list) {
            defaultTableModel.addRow(new Object[]{
                x.getMAHD(), x.getNHANVIEN(), x.getKHACHHANG(), x.getNgayTao(), x.layTrangThai(x.getTrangThai())
            });
        }
        tbHoaDon.setRowSelectionInterval(0, 0);
    }

    public void addrowHDCT() {

        int row = tbHoaDon.getSelectedRow();
        Integer maHD = (Integer) tbHoaDon.getValueAt(row, 0);
        defaultTableModel = (DefaultTableModel) tbHDCT.getModel();
        defaultTableModel.setRowCount(0);
        List<HoaDonChiTiet> list = hDCTService.getHDCTByMaHD(maHD);
        for (HoaDonChiTiet x : list) {
            defaultTableModel.addRow(new Object[]{
                x.getSanPham().getMASP(), x.getSanPham().getTENSP(), x.getDONGIA(), x.getSanPham().getKHUYENMAI().getSOTIENKM(), x.getSOLUONG()
            });
        }
        int tongThanhTien = 0;
        for (int i = 0; i < list.size(); i++) {
            double donGia = list.get(i).getDONGIA().doubleValue();
            double giamGia = list.get(i).getSanPham().getKHUYENMAI().getSOTIENKM().doubleValue();

            tongThanhTien = (int) (tongThanhTien + (donGia - giamGia) * list.get(i).getSOLUONG());
        }
        String tongTien = String.valueOf(tongThanhTien);
        txtTongTien.setText(tongTien);
    }

    public void addrowHDCT2() {

        int row = tbHD.getSelectedRow();
        Integer maHD = (Integer) tbHD.getValueAt(row, 0);
        defaultTableModel = (DefaultTableModel) tbCTHD.getModel();
        defaultTableModel.setRowCount(0);
        List<HoaDonChiTiet> list = hDCTService.getHDCTByMaHD(maHD);
        for (HoaDonChiTiet x : list) {
            defaultTableModel.addRow(new Object[]{
                x.getSanPham().getMASP(), x.getSanPham().getTENSP(), x.getDONGIA(), x.getSanPham().getKHUYENMAI().getSOTIENKM(), x.getSOLUONG()
            });
        }
//        int tongThanhTien = 0;
//        for (int i = 0; i < list.size(); i++) {
//            double donGia = list.get(i).getDONGIA().doubleValue();
//            double giamGia = list.get(i).getSanPham().getKHUYENMAI().getSOTIENKM().doubleValue();
//
//            tongThanhTien = (int) (tongThanhTien + (donGia - giamGia) * list.get(i).getSOLUONG());
//        }
//        String tongTien = String.valueOf(tongThanhTien);
//        txtTongTien.setText(tongTien);
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
        jPanel11 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHoaDon = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbHDCT = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtMaHD = new javax.swing.JTextField();
        txtKhachHang = new javax.swing.JTextField();
        txtNgayTao = new javax.swing.JTextField();
        txtTrangThai = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbHD = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txtMaHDSearch = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        cbTrangThai = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbCTHD = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        lblThongTinDn = new javax.swing.JLabel();
        lblChucVu = new javax.swing.JLabel();
        lbl_DongHo = new javax.swing.JLabel();
        lblMaNVLay = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(153, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("Hóa Đơn");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 110, 60));

        tbHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã HD", "Người Tạo", "Ngày Tạo", "Khách Hàng", "Bàn", "Trạng Thái"
            }
        ));
        tbHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbHoaDon);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 560, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setText("Hóa Đơn Chi Tiết");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 70, -1, -1));

        tbHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Sp", "Tên Sp", "Đơn giá", "Giảm giá", "Số Lượng"
            }
        ));
        jScrollPane2.setViewportView(tbHDCT);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 120, 500, 120));

        jLabel11.setText("Mã HD");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 270, -1, -1));

        jLabel12.setText("Khách Hàng");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 320, -1, -1));

        jLabel14.setText("Ngày Tạo");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 370, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Trạng Thái");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 270, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("Tổng Tiền");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 360, -1, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Xác Nhận Hóa Đơn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 490, 170, 60));
        jPanel2.add(txtMaHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 270, 90, -1));
        jPanel2.add(txtKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 320, 90, -1));
        jPanel2.add(txtNgayTao, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 370, 90, -1));

        txtTrangThai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel2.add(txtTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 300, 160, 40));
        jPanel2.add(txtTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 360, 80, -1));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Hóa Đơn Pha Chế", jPanel11);

        jPanel12.setBackground(new java.awt.Color(153, 255, 204));
        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tbHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã HD", "Người Tạo", "Khách Hàng", "Ngày Tạo", "Trạng Thái"
            }
        ));
        tbHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHDMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbHD);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Chi Tiết Hóa Đơn :");

        jButton2.setText("Tìm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Trạng Thái:");

        cbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chờ Order", "Chờ Xác Nhận", "Hoàn Thành" }));

        jButton3.setText("Lọc");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("Mã Hoá Đơn:");

        tbCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Đơn Gía", "Giam Gía", "Số Lượng"
            }
        ));
        jScrollPane4.setViewportView(tbCTHD);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(txtMaHDSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(122, 122, 122)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton3))
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4))
                .addContainerGap())
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(988, Short.MAX_VALUE)))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaHDSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(cbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(196, Short.MAX_VALUE))
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addComponent(jLabel18)
                    .addContainerGap(760, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Lịch Sử Hóa Đơn", jPanel12);

        jPanel13.setBackground(new java.awt.Color(153, 255, 204));
        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblThongTinDn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblThongTinDn.setText("TÊN NHÂN VIÊN");

        lblChucVu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblChucVu.setText("Chức Vụ");

        lbl_DongHo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_DongHo.setText("ĐỒNG HỒ");

        lblMaNVLay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMaNVLay.setText("Mã NV");

        jButton7.setText("Đăng Xuất");

        jButton8.setText("Đổi Mật Khẩu");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8)
                .addGap(18, 18, 18)
                .addComponent(lblMaNVLay, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblThongTinDn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 543, Short.MAX_VALUE)
                .addComponent(lbl_DongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_DongHo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblMaNVLay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addContainerGap())
            .addComponent(lblThongTinDn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(0, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DARLINGG");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 204, 204)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 190));

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Desktop\\bts2\\src\\main\\java\\com\\raven\\icon\\2.png")); // NOI18N
        jLabel3.setText("Bán Hàng");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 190, 50));

        jPanel6.setBackground(new java.awt.Color(0, 153, 153));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Desktop\\bts2\\src\\main\\java\\com\\raven\\icon\\5.png")); // NOI18N
        jLabel4.setText("Hóa Đơn");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 190, 50));

        jPanel7.setBackground(new java.awt.Color(0, 153, 153));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Desktop\\bts2\\src\\main\\java\\com\\raven\\icon\\6.png")); // NOI18N
        jLabel5.setText("Sản Phẩm");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 190, 50));

        jPanel8.setBackground(new java.awt.Color(0, 153, 153));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Desktop\\bts2\\src\\main\\java\\com\\raven\\icon\\10.png")); // NOI18N
        jLabel6.setText("Khuyến Mại");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 190, 50));

        jPanel9.setBackground(new java.awt.Color(0, 153, 153));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Desktop\\bts2\\src\\main\\java\\com\\raven\\icon\\8.png")); // NOI18N
        jLabel7.setText("Thống Kê");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 190, 50));

        jPanel10.setBackground(new java.awt.Color(0, 153, 153));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Desktop\\bts2\\src\\main\\java\\com\\raven\\icon\\7.png")); // NOI18N
        jLabel8.setText("Nhân Viên");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 190, 50));

        jPanel14.setBackground(new java.awt.Color(0, 153, 153));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("NLiệu, NCC");
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 610, 190, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int row = tbHoaDon.getSelectedRow();
        Integer maHD = (Integer) tbHoaDon.getValueAt(row, 0);
        qLHDService.updateHDThanhCong(maHD);
        addrowHD();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonMouseClicked
        // TODO add your handling code here:
        int row = tbHoaDon.getSelectedRow();
        Integer maHD = (Integer) tbHoaDon.getValueAt(row, 0);
        String maHD1 = String.valueOf(maHD);
        Date date = (Date) tbHoaDon.getValueAt(row, 2);
        String ngayTaoHD = String.valueOf(date);
        String khachHang = (String) tbHoaDon.getValueAt(row, 3);
        String cxn = (String) tbHoaDon.getValueAt(row, 5);
        txtMaHD.setText(maHD1);
        txtNgayTao.setText(ngayTaoHD);
        txtKhachHang.setText(khachHang);
        txtTrangThai.setText(cxn);
        addrowHDCT();
    }//GEN-LAST:event_tbHoaDonMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Integer maHD = Integer.parseInt(txtMaHDSearch.getText());
        HoaDonReponse x = qLHDService.getHDByMa(maHD);
        defaultTableModel = (DefaultTableModel) tbHD.getModel();
        defaultTableModel.setRowCount(0);
        defaultTableModel.addRow(new Object[]{
            x.getMAHD(), x.getNHANVIEN(), x.getKHACHHANG(), x.getNgayTao(), x.layTrangThai(x.getTrangThai())
        });

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (cbTrangThai.getSelectedItem().equals("Chờ Order")) {
            List<HoaDonReponse> list = qLHDService.getAllHDByTinhTrang(0);
            defaultTableModel = (DefaultTableModel) tbHD.getModel();
            defaultTableModel.setRowCount(0);
            for (HoaDonReponse x : list) {
                defaultTableModel.addRow(new Object[]{
                    x.getMAHD(), x.getNHANVIEN(), x.getKHACHHANG(), x.getNgayTao(), x.layTrangThai(x.getTrangThai())
                });
            }
        } else if (cbTrangThai.getSelectedItem().equals("Chờ Xác Nhận")) {
            List<HoaDonReponse> list = qLHDService.getAllHDByTinhTrang(1);
            defaultTableModel = (DefaultTableModel) tbHD.getModel();
            defaultTableModel.setRowCount(0);
            for (HoaDonReponse x : list) {
                defaultTableModel.addRow(new Object[]{
                    x.getMAHD(), x.getNHANVIEN(), x.getKHACHHANG(), x.getNgayTao(), x.layTrangThai(x.getTrangThai())
                });
            }
        } else {
            List<HoaDonReponse> list = qLHDService.getAllHDByTinhTrang(2);
            defaultTableModel = (DefaultTableModel) tbHD.getModel();
            defaultTableModel.setRowCount(0);
            for (HoaDonReponse x : list) {
                defaultTableModel.addRow(new Object[]{
                    x.getMAHD(), x.getNHANVIEN(), x.getKHACHHANG(), x.getNgayTao(), x.layTrangThai(x.getTrangThai())
                });
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        addrowHD2();
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        DoiMatKhauForm dmk = new DoiMatKhauForm();
        dmk.setVisible(true);
        dmk.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void tbHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHDMouseClicked
        // TODO add your handling code here:
        addrowHDCT2();
    }//GEN-LAST:event_tbHDMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        Integer int1 = Integer.valueOf(lblMaNVLay.getText());
        BanHang bh = new BanHang(int1);
        bh.show();
        this.dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        Integer int1 = Integer.valueOf(lblMaNVLay.getText());
        HoaDonView bh = new HoaDonView(int1);
        bh.show();
        this.dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        Integer int1 = Integer.valueOf(lblMaNVLay.getText());

        KhuyenMaiView bh = new KhuyenMaiView(int1);
        bh.show();
        this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        Integer int1 = Integer.valueOf(lblMaNVLay.getText());

        ThongKeView bh = new ThongKeView(int1);
        bh.show();
        this.dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        // TODO add your handling code here:
        Integer int1 = Integer.valueOf(lblMaNVLay.getText());

        NguyenLieuNCCView bh = new NguyenLieuNCCView(int1);
        bh.show();
        this.dispose();
    }//GEN-LAST:event_jLabel20MouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbTrangThai;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblMaNVLay;
    private javax.swing.JLabel lblThongTinDn;
    private javax.swing.JLabel lbl_DongHo;
    private javax.swing.JTable tbCTHD;
    private javax.swing.JTable tbHD;
    private javax.swing.JTable tbHDCT;
    private javax.swing.JTable tbHoaDon;
    private javax.swing.JTextField txtKhachHang;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaHDSearch;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtTrangThai;
    // End of variables declaration//GEN-END:variables
}
