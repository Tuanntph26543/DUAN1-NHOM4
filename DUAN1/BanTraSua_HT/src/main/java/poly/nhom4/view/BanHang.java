/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package poly.nhom4.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import poly.nhom4.domainmodel.Ban;
import poly.nhom4.domainmodel.HoaDon;
import poly.nhom4.domainmodel.HoaDonChiTiet;
import poly.nhom4.reponse.BanReponse;
import poly.nhom4.reponse.HDCTReponse;
import poly.nhom4.reponse.HoaDonReponse;
import poly.nhom4.reponse.SanPhamReponse;
import poly.nhom4.repository.HoaDonRepository;
import poly.nhom4.repository.KhachHangRepository;
import poly.nhom4.repository.USERTTRepository;
import poly.nhom4.service.BanService;
import poly.nhom4.service.HDCTService;
import poly.nhom4.service.KhachHangService;
import poly.nhom4.service.LoaiSPService;
import poly.nhom4.service.QLHDService;
import poly.nhom4.service.QuanLySPService;
import poly.nhom4.service.UserTTService;
import poly.nhom4.service.impl.BanServiceIplm;
import poly.nhom4.service.impl.HDCTIplm;
import poly.nhom4.service.impl.KhachHangServiceIplm;
import poly.nhom4.service.impl.LoaiSPServiceIplm;
import poly.nhom4.service.impl.QLHDServiceIplm;
import poly.nhom4.service.impl.QuanLySPServiceIplm;
import poly.nhom4.service.impl.UserTTServiceIplm;

/**
 *
 * @author ACER
 */
public class BanHang extends javax.swing.JFrame {

    private final QuanLySPService quanLySPService;
    private final LoaiSPService loaiSPService;
    private final KhachHangService khachHangService;
    private final QLHDService qLHDService;
    private final UserTTService userTTService;
    private final HDCTService hDCTService;
    private final BanService banService;

    /**
     * Creates new form TrangChu
     */
    DefaultTableModel defaultTableModel;
    DefaultComboBoxModel defaultComboBoxModel;
    String tGBD = "";

    public BanHang(int ma) {
        initComponents();
        tGBD = java.time.LocalTime.now().getHour() + ":" + java.time.LocalTime.now().getMinute() + "  " + java.time.LocalDate.now();
        banService = new BanServiceIplm();
        quanLySPService = new QuanLySPServiceIplm();
        loaiSPService = new LoaiSPServiceIplm();
        khachHangService = new KhachHangServiceIplm();
        userTTService = new UserTTServiceIplm();
        qLHDService = new QLHDServiceIplm();
        hDCTService = new HDCTIplm();
        String ma1 = String.valueOf(ma);
        lblMaNVLay.setText(ma1);
        lblThongTinDn.setText(userTTService.getUSerByMaNV(ma).getHOTEN());
        lblChucVu.setText(userTTService.getUSerByMaNV(ma).getCHUCVU().getTENCV());
        dongHo();
        addrow();
        addrowHD();
        addrowHDCT();
        addBan();

    }
    double tongTienBanDuoc = 0;

    public void addrow() {
        List<SanPhamReponse> list = quanLySPService.getAll();
        defaultTableModel = (DefaultTableModel) tbSanPham.getModel();
        defaultTableModel.setRowCount(0);
        for (SanPhamReponse x : list) {
            double gia = x.getDonGia().doubleValue();
            double giamGia = x.getSoTienKM().doubleValue();
            double gia2 = Math.round(gia * 1000) / 1000;
            BigDecimal b = new BigDecimal(gia2);
            BigDecimal c = new BigDecimal(giamGia);
            ImageIcon icon = new ImageIcon(x.getAnhSP());
            defaultTableModel.addRow(new Object[]{
                x.getMaSp(), x.getTenSp(), b, c, icon, x.layTrangThai(x.getTrangThai())
            });
        }
    }

    public void addBan() {
        List<BanReponse> list = banService.getBanConTrong();
        defaultTableModel = (DefaultTableModel) tbBan.getModel();
        defaultTableModel.setRowCount(0);
        for (BanReponse x : list) {
            defaultTableModel.addRow(new Object[]{
                x.getMaBan(), x.getTenBan(), x.getSoLuong(), x.layTrangThai(x.getTrangThai())
            });
        }
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
        List<HoaDonReponse> list = qLHDService.getAll();
        defaultTableModel = (DefaultTableModel) tbHoaDon.getModel();
        defaultTableModel.setRowCount(0);
        for (HoaDonReponse x : list) {
            defaultTableModel.addRow(new Object[]{
                x.getMAHD(), x.getNHANVIEN(), x.getKHACHHANG(), x.getNgayTao(), x.layBan(x.getMAHD()), x.layTrangThai(x.getTrangThai())
            });
        }
        tbHoaDon.setRowSelectionInterval(0, 0);

    }

    public void addrowHDCT1() {
        int row = tbHoaDon.getSelectedRow();
        Integer maHD = (Integer) tbHoaDon.getValueAt(row, 0);
        int[] row1 = tbBan.getSelectedRows();
        for (int i : row1) {
            Integer maBan = (Integer) tbBan.getValueAt(i, 0);
            HDCTReponse hDCTReponse = new HDCTReponse();
            hDCTReponse.setMaBan(maBan);
            hDCTReponse.setMaHD(maHD);
            hDCTService.createHDCT2(hDCTReponse);
            banService.updateBan(maBan);
        }
        addBan();
    }

    public void addrowHDCT() {
        int row = tbHoaDon.getSelectedRow();
        Integer maHD = (Integer) tbHoaDon.getValueAt(row, 0);
        defaultTableModel = (DefaultTableModel) tbHDCT.getModel();
        defaultTableModel.setRowCount(0);
        List<HoaDonChiTiet> list = hDCTService.getHDCTByMaHD(maHD);

        for (HoaDonChiTiet x : list) {
            double gia = x.getDONGIA().doubleValue();
            double giamGia = x.getSanPham().getKHUYENMAI().getSOTIENKM().doubleValue();
            double gia2 = Math.round(gia * 1000) / 1000;
            BigDecimal b = new BigDecimal(gia2);
            BigDecimal c = new BigDecimal(giamGia);
            defaultTableModel.addRow(new Object[]{
                x.getSanPham().getMASP(), x.getSanPham().getTENSP(), b, c, x.getSOLUONG()
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbHoaDon = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbHDCT = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNgayTao = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtTimSp = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        lblThongTinDn = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        lblChucVu = new javax.swing.JLabel();
        lbl_DongHo = new javax.swing.JLabel();
        lblMaNVLay = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbBan = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        btnClearSP = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("TOCOTOCO");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 90));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setForeground(new java.awt.Color(51, 153, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Desktop\\bts2\\src\\main\\java\\com\\raven\\icon\\1.png")); // NOI18N
        jLabel2.setText("Trang Chủ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 190, 50));

        jPanel5.setBackground(new java.awt.Color(255, 255, 102));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Desktop\\bts2\\src\\main\\java\\com\\raven\\icon\\2.png")); // NOI18N
        jLabel3.setText("Bán Hàng");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 190, 50));

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
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
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 190, 50));

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Desktop\\bts2\\src\\main\\java\\com\\raven\\icon\\6.png")); // NOI18N
        jLabel5.setText("Sản Phẩm");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 190, 50));

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Desktop\\bts2\\src\\main\\java\\com\\raven\\icon\\10.png")); // NOI18N
        jLabel6.setText("Khuyến Mại");

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

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 190, 50));

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Desktop\\bts2\\src\\main\\java\\com\\raven\\icon\\8.png")); // NOI18N
        jLabel7.setText("Thống Kê");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1319, 1319, 1319))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 190, 50));

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
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
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 190, 50));

        jPanel2.setBackground(new java.awt.Color(153, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Hóa Đơn ");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 70, -1));

        tbHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "MA HD", "NHAN VIEN", "KHACH HANG", "NGAY TAO", "BÀN", "TRANGTHAI"
            }
        ));
        tbHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHoaDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbHoaDon);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, 600, 90));

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("SDT:");

        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Họ Tên:");

        jButton1.setText("Tạo Hóa Đơn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Thêm Khách Hàng");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addComponent(txtSDT))
                .addGap(32, 32, 32)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addContainerGap(141, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(16, 16, 16)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, 450, 160));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Gio Hang");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 650, -1, -1));

        tbHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "MASP", "TENSP", "DON GIA", "GIA KM", "SOLUONG"
            }
        ));
        tbHDCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHDCTMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbHDCT);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 680, 600, 90));

        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "MASP", "TENSP", "DONGIA", "GIAMGIA", "HINHANH", "TRANGTHAI"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, javax.swing.ImageIcon.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbSanPham.setRowHeight(140);
        tbSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbSanPham);
        if (tbSanPham.getColumnModel().getColumnCount() > 0) {
            tbSanPham.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 600, 400));

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setText("MA HD");

        jLabel10.setText("NGAYTAO");

        jLabel11.setText("TEN NV");

        jLabel16.setText("Tong Tien");

        jLabel17.setText("Tien Khach Dua");

        txtTienKhachDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachDuaActionPerformed(evt);
            }
        });

        jLabel18.setText("Tien Thua");

        jButton2.setText("Thanh Toan");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setText("Hoa Don");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel10)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaHD)
                            .addComponent(txtNgayTao)
                            .addComponent(txtTenNV)
                            .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienKhachDua)
                            .addComponent(txtTongTien))))
                .addGap(318, 318, 318))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 390, 450, 380));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("Tên Sản Phẩm:");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        txtTimSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimSpActionPerformed(evt);
            }
        });
        jPanel2.add(txtTimSp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 150, -1));

        jButton4.setText("Tìm");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, -1, -1));

        jPanel13.setBackground(new java.awt.Color(0, 255, 204));

        lblThongTinDn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblThongTinDn.setText("TÊN NHÂN VIÊN");

        jButton6.setText("Giao Ca");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        lblChucVu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblChucVu.setText("Chức Vụ");

        lbl_DongHo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_DongHo.setText("ĐỒNG HỒ");

        lblMaNVLay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMaNVLay.setText("Mã NV");

        jButton7.setText("Đăng Xuất");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(18, 18, 18)
                .addComponent(lblMaNVLay, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblThongTinDn, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(lbl_DongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addContainerGap())
            .addComponent(lbl_DongHo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblMaNVLay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblThongTinDn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 50));

        tbBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Bàn", "Tên Bàn", "Số Lượng", "Trạng Thái"
            }
        ));
        jScrollPane4.setViewportView(tbBan);

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(662, 220, 380, 160));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setText("Bàn:");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 190, 50, -1));

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("+");
        jButton5.setBorder(new javax.swing.border.MatteBorder(null));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 220, 40, 40));

        btnClearSP.setText("Clear SP");
        btnClearSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearSPActionPerformed(evt);
            }
        });
        jPanel2.add(btnClearSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 650, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
        int row = tbHoaDon.getSelectedRow();
        Integer maHD = (Integer) tbHoaDon.getValueAt(row, 0);
        String hoTen = khachHangService.getTenKhBySdt(txtSDT.getText());
        txtHoTen.setText(hoTen);
        if (hoTen != null) {
            qLHDService.updateHDBySdt(maHD, txtSDT.getText());
        } else {
            JOptionPane.showMessageDialog(rootPane, "Lỗi");
        }
        addrowHD();
    }//GEN-LAST:event_txtSDTActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        HoaDon hd = new HoaDon();
        hd.setKhachHang(null);
        hd.setNgayTao(date);
        Integer int1 = Integer.valueOf(lblMaNVLay.getText());
        hd.setUsertt(userTTService.getUSerByMaNV(int1));
        hd.setTinhTrang(0);
        qLHDService.createHD(hd);
        addrowHD();
        addrowHDCT();
        addrowHDCT1();
        addrowHD();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMouseClicked
        // TODO add your handling code here:
        int row = tbHoaDon.getSelectedRow();
        Integer maHD = (Integer) tbHoaDon.getValueAt(row, 0);
        List<HoaDonChiTiet> list = hDCTService.getHDCTByMaHD(maHD);
        String soLuong = JOptionPane.showInputDialog(rootPane, "Moi nhap so luong");
        int soLuong2 = Integer.parseInt(soLuong);

        int row2 = tbSanPham.getSelectedRow();
        BigDecimal tienKM = (BigDecimal) tbSanPham.getValueAt(row2, 3);
        BigDecimal donGia = (BigDecimal) tbSanPham.getValueAt(row2, 2);
        Integer maSP = (Integer) tbSanPham.getValueAt(row2, 0);
        HDCTReponse hDCTReponse = new HDCTReponse();
        hDCTReponse.setSoLuong(soLuong2);
        hDCTReponse.setDonGia(donGia);
        hDCTReponse.setMaBan(1);
        hDCTReponse.setMaHD(maHD);
        hDCTReponse.setMaSp(maSP);
        boolean kq = false;
        if (list == null) {
            hDCTService.createHDCT(hDCTReponse);
        } else {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getSanPham().getMASP() == maSP) {
                    int soLuongMoi = list.get(j).getSOLUONG() + soLuong2;
                    hDCTService.updateHDCT(maHD, maSP, soLuongMoi);
                    kq = true;
                }
            }
            if (kq == false) {
                hDCTService.createHDCT(hDCTReponse);
            }
        }
        addrowHDCT();
    }//GEN-LAST:event_tbSanPhamMouseClicked

    private void tbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonMouseClicked
        // TODO add your handling code here:
        int row = tbHoaDon.getSelectedRow();
        Integer maHD = (Integer) tbHoaDon.getValueAt(row, 0);
        String maHD1 = String.valueOf(maHD);
        Date date = (Date) tbHoaDon.getValueAt(row, 3);
        String ngayTaoHD = String.valueOf(date);
        String nv = (String) tbHoaDon.getValueAt(row, 1);
        txtMaHD.setText(maHD1);
        txtNgayTao.setText(ngayTaoHD);
        txtTenNV.setText(nv);
        addrowHDCT();
    }//GEN-LAST:event_tbHoaDonMouseClicked

    private void txtTienKhachDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachDuaActionPerformed
        // TODO add your handling code here:
        double tienKhachDua = Double.parseDouble(txtTienKhachDua.getText());
        double tongTien = Double.parseDouble(txtTongTien.getText());
        double tienThua = Math.ceil((tienKhachDua - tongTien) * 100) / 100;
        String tienThua2 = String.valueOf(tienThua);
        txtTienThua.setText(tienThua2);
    }//GEN-LAST:event_txtTienKhachDuaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int row = tbHoaDon.getSelectedRow();
        Integer maHD = (Integer) tbHoaDon.getValueAt(row, 0);
        qLHDService.updateHD(maHD);
        addrowHD();
        double tongTien = Double.parseDouble(txtTongTien.getText());
        tongTienBanDuoc = tongTienBanDuoc + tongTien;
        System.out.println(tongTienBanDuoc);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tbHDCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHDCTMouseClicked
        // TODO add your handling code here:
        int row = tbHoaDon.getSelectedRow();
        Integer maHD = (Integer) tbHoaDon.getValueAt(row, 0);
        String soLuong = JOptionPane.showInputDialog(rootPane, "Moi nhap so luong");
        int soLuong2 = Integer.parseInt(soLuong);
        int row2 = tbHDCT.getSelectedRow();
        Integer maSP = (Integer) tbHDCT.getValueAt(row2, 0);
        if (soLuong2 == 0) {
            hDCTService.deleteHDBySPVe0(maHD, maSP);
        } else {
            hDCTService.updateHDCT(maHD, maSP, soLuong2);
        }
        addrowHDCT();
    }//GEN-LAST:event_tbHDCTMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        DangKiKhachHang kd = new DangKiKhachHang();
        kd.setLocationRelativeTo(null);
        kd.setVisible(true);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        Integer int1 = Integer.valueOf(lblMaNVLay.getText());
        HoaDonView bh = new HoaDonView(int1);
        bh.show();
        this.dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void btnClearSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearSPActionPerformed
        // TODO add your handling code here:
        int row = tbHoaDon.getSelectedRow();
        Integer maHD = (Integer) tbHoaDon.getValueAt(row, 0);
        hDCTService.deleteAll(maHD);
    }//GEN-LAST:event_btnClearSPActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (txtTimSp.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Mời nhập tên sản phẩm");
        } else {
            List<SanPhamReponse> list = quanLySPService.getSPByTen(txtTimSp.getText());
            defaultTableModel = (DefaultTableModel) tbSanPham.getModel();
            defaultTableModel.setRowCount(0);
            for (SanPhamReponse x : list) {
                ImageIcon icon = new ImageIcon(x.getAnhSP());
                defaultTableModel.addRow(new Object[]{
                    x.getMaSp(), x.getTenSp(), x.getDonGia(), x.getSoTienKM(), icon, x.layTrangThai(x.getTrangThai())
                });
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtTimSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimSpActionPerformed
        // TODO add your handling code here:
        addrow();
    }//GEN-LAST:event_txtTimSpActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        BanView kd = new BanView();
        kd.setLocationRelativeTo(null);
        kd.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        BigDecimal bd=new BigDecimal(tongTienBanDuoc);
        String tongTien=bd.toString();
        Integer tongTien2=Integer.parseInt(tongTien);
        GiaoCaView g = new GiaoCaView(tongTien2, tGBD);
        g.setVisible(true);
        g.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(BanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                int ma = 1;
                new BanHang(ma).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearSP;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
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
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblMaNVLay;
    private javax.swing.JLabel lblThongTinDn;
    private javax.swing.JLabel lbl_DongHo;
    private javax.swing.JTable tbBan;
    private javax.swing.JTable tbHDCT;
    private javax.swing.JTable tbHoaDon;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTimSp;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}