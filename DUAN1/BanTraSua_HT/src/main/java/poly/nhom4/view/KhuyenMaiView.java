/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package poly.nhom4.view;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.sql.Timestamp;
//import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import poly.nhom4.reponse.KhuyenMaiReponse;
import poly.nhom4.reponse.SanPhamReponse;
import poly.nhom4.service.KhuyenMaiService;
import poly.nhom4.service.QuanLySPService;
import poly.nhom4.service.UserTTService;
import poly.nhom4.service.impl.KhuyenMaiServiceImpl;
import poly.nhom4.service.impl.QuanLySPServiceIplm;
import poly.nhom4.service.impl.UserTTServiceIplm;

/**
 *
 * @author Admin
 */
public class KhuyenMaiView extends javax.swing.JFrame {

    /**
     * Creates new form KhuyenMai
     */
    private QuanLySPService spser = new QuanLySPServiceIplm();
    private List<SanPhamReponse> sp2 = spser.getAll2();
    private List<SanPhamReponse> sp3 = spser.getAll3();
    private KhuyenMaiService khuyenMaiService = new KhuyenMaiServiceImpl();
    private List<KhuyenMaiReponse> reponses = khuyenMaiService.getAll();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private List<JCheckBox> listCbx = new ArrayList<>();
    private DefaultTableModel model;
    private final UserTTService userTTService;

    private int row;

    public KhuyenMaiView(int ma) {
        initComponents();
        userTTService = new UserTTServiceIplm();
        String ma1 = String.valueOf(ma);
        lblMaNVLay.setText(ma1);
        lblThongTinDn.setText(userTTService.getUSerByMaNV(ma).getHOTEN());
        lblChucVu.setText(userTTService.getUSerByMaNV(ma).getCHUCVU().getTENCV());
        showData(reponses);
        showSP(sp2);
        showSPKM(sp3);
        txtMa.disable();
        ComBoBox();
        dongHo();
        Auto();
        tbSP.getColumn("Chọn").setCellEditor(new RadioButtonEditor(new JCheckBox()));
        tbSP.getColumn("Chọn").setCellRenderer(new RadioButtonRenderer());
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

    private void showData(List<KhuyenMaiReponse> list) {
        model = (DefaultTableModel) tbKM.getModel();
        model.setRowCount(0);
        for (KhuyenMaiReponse km : list) {
            model.addRow(new Object[]{
                km.getMaKM(), km.getTenKM(), km.getSoTienKM(), km.getNgayBD(),
                km.getNgayKT(), km.getTrangThai() == 0 ? "Ngừng áp dụng" : "Đang áp dụng"
            });
        }
    }

    private void showSPKM(List<SanPhamReponse> list) {
        model = (DefaultTableModel) tbSPKM.getModel();
        model.setRowCount(0);
        for (SanPhamReponse s : list) {
            model.addRow(new Object[]{
                s.getMaSp(), s.getMaKM(), s.getTenSp()
            });
        }
    }

    private void showSP(List<SanPhamReponse> list) {
        model = (DefaultTableModel) tbSP.getModel();
        model.setRowCount(0);
        int stt = 1;
        for (SanPhamReponse s : list) {
            JCheckBox cbx = stt <= listCbx.size() ? listCbx.get(stt - 1) : new JCheckBox();
            if (stt > listCbx.size()) {
                listCbx.add(cbx);
            }
            model.addRow(new Object[]{
                stt++, s.getMaSp(), s.getTenSp(), cbx
            });
        }
    }

    private void ComBoBox() {
        DefaultComboBoxModel<String> combo = new DefaultComboBoxModel<>();
        combo.addElement("Tất cả");
        combo.addElement("Đang áp dụng");
        combo.addElement("Ngừng áp dụng");
        cbLoc.setModel(combo);
    }

    private void fillForm() {
        try {
            KhuyenMaiReponse km = new KhuyenMaiServiceImpl().getAll().get(row);
            txtMa.setText(km.getMaKM().toString());
            txtTen.setText(km.getTenKM());
            txtGiaTri.setText(km.getSoTienKM().toString());
            txtDate1.setDate(sdf.parse(km.getNgayBD()));
            txtDate2.setDate(sdf.parse(km.getNgayKT()));
            int row1 = tbKM.getSelectedRow();
            String ngay = (String) tbKM.getValueAt(row1, 4);
            String gio = ngay.substring(11, 13);
            String p = ngay.substring(14, 16);
            String s = ngay.substring(17, 19);
            System.out.println(gio + p + s);
            txtGio2.setText(gio);
            txtPhut2.setText(p);
            txtGiay2.setText(s);
            chkTrangThai.setSelected(km.getTrangThai().equals(1));
        } catch (ParseException ex) {
            Logger.getLogger(KhuyenMaiView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void clickKM() {
        Integer km = (Integer) tbKM.getValueAt(row, 0);
        List<SanPhamReponse> list = spser.getAll3();
        model = (DefaultTableModel) tbSPKM.getModel();
        model.setRowCount(0);
        for (SanPhamReponse s : list) {
            if (km == s.getMaKM()) {
                model.addRow(new Object[]{
                    s.getMaSp(), s.getMaKM(), s.getTenSp()
                });
            }
        }
    }

    public void Auto() {
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        List<KhuyenMaiReponse> auto = khuyenMaiService.getAll();
                        auto.stream().forEach(km -> {
                            Timestamp date = new Timestamp(new Date().getTime());
                            if (date.compareTo(Timestamp.valueOf(km.getNgayKT())) > 0) {
                                km.setTrangThai(0);
                                khuyenMaiService.update(km);
                            }
                        });
                        showData(new KhuyenMaiServiceImpl().getAll());
                        Thread.sleep(60000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(KhuyenMaiView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();
    }

    private void clear() {
        txtMa.setText("");
        txtTen.setText("");
        txtDate1.setDate(null);
        txtDate2.setDate(null);
        txtGiaTri.setText("");
        txtGio2.setText("00");
        txtPhut2.setText("00");
        txtGiay2.setText("00");
        cbxAll.setSelected(false);
        for (JCheckBox rd : listCbx) {
            rd.setSelected(false);
        }

    }

    private KhuyenMaiReponse getKM() {
        KhuyenMaiReponse km = new KhuyenMaiReponse();
        km.setTenKM(txtTen.getText());
        String tien = txtGiaTri.getText();
        BigDecimal bg = new BigDecimal(tien);
        km.setSoTienKM(bg);
        km.setNgayBD(sdf.format(txtDate1.getDate()));
        String ngayKT = sdf.format(txtDate2.getDate());
        ngayKT += " " + txtGio2.getText() + ":" + txtPhut2.getText() + ":" + txtGiay2.getText();
        km.setNgayKT(ngayKT);
        km.setTrangThai(chkTrangThai.isSelected() ? 1 : 0);
        return km;
    }

    private Boolean check() {
        if (txtTen.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Không để trống Tên Voucher", "ERROR", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtGiaTri.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Không để trống Giá trị Voucher", "ERROR", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtDate1.getDate() == null || txtDate2.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Không để trống ngày", "ERROR", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (txtDate1.getDate().after(txtDate2.getDate())) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải <= ngày kết thúc", "ERROR", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
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
        jPanel2 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        lblThongTinDn = new javax.swing.JLabel();
        lblChucVu = new javax.swing.JLabel();
        lbl_DongHo = new javax.swing.JLabel();
        lblMaNVLay = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        txtGiaTri = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtDate1 = new com.toedter.calendar.JDateChooser();
        txtDate2 = new com.toedter.calendar.JDateChooser();
        jPanel15 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        chkTrangThai = new javax.swing.JCheckBox();
        txtGio2 = new javax.swing.JTextField();
        txtPhut2 = new javax.swing.JTextField();
        txtGiay2 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbSP = new javax.swing.JTable();
        cbxAll = new javax.swing.JCheckBox();
        btnAll = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbKM = new javax.swing.JTable();
        btnLoc = new javax.swing.JButton();
        cbLoc = new javax.swing.JComboBox<>();
        txtTim = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbSPKM = new javax.swing.JTable();
        btnLoad = new javax.swing.JButton();
        chkReset = new javax.swing.JCheckBox();
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

        jPanel2.setBackground(new java.awt.Color(153, 255, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setBackground(new java.awt.Color(153, 255, 204));
        jPanel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

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

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 521, Short.MAX_VALUE)
                .addComponent(lbl_DongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMaNVLay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addContainerGap())
            .addComponent(lblThongTinDn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_DongHo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, -1));

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập voucher", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Source Sans Pro SemiBold", 0, 15), new java.awt.Color(255, 102, 51))); // NOI18N

        txtGiaTri.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtTen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Source Sans Pro SemiBold", 0, 14)); // NOI18N
        jLabel9.setText("Mã khuyến mại");

        jLabel10.setFont(new java.awt.Font("Source Sans Pro SemiBold", 0, 14)); // NOI18N
        jLabel10.setText("Tên khuyến mại");

        jLabel11.setFont(new java.awt.Font("Source Sans Pro SemiBold", 0, 14)); // NOI18N
        jLabel11.setText("Giá trị");

        jLabel12.setFont(new java.awt.Font("Source Sans Pro SemiBold", 0, 14)); // NOI18N
        jLabel12.setText("Ngày bắt đầu");

        jLabel13.setFont(new java.awt.Font("Source Sans Pro SemiBold", 0, 14)); // NOI18N
        jLabel13.setText("Ngày kết thúc");

        jLabel14.setFont(new java.awt.Font("Source Sans Pro SemiBold", 0, 14)); // NOI18N
        jLabel14.setText("Trạng thái");

        txtMa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtMa.setMinimumSize(new java.awt.Dimension(7, 23));
        txtMa.setPreferredSize(new java.awt.Dimension(7, 23));

        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThem.setBackground(new java.awt.Color(255, 102, 51));
        btnThem.setFont(new java.awt.Font("Source Sans Pro SemiBold", 0, 15)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(255, 102, 51));
        btnSua.setFont(new java.awt.Font("Source Sans Pro SemiBold", 0, 15)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Cập nhật");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 102, 51));
        btnXoa.setFont(new java.awt.Font("Source Sans Pro SemiBold", 0, 15)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnNew.setBackground(new java.awt.Color(255, 102, 51));
        btnNew.setFont(new java.awt.Font("Source Sans Pro SemiBold", 0, 14)); // NOI18N
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setText("Làm mới");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem)
                .addGap(18, 18, 18)
                .addComponent(btnSua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(btnNew)
                .addContainerGap())
        );

        chkTrangThai.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        chkTrangThai.setText("Kích hoạt");

        txtGio2.setText("00");

        txtPhut2.setText("00");

        txtGiay2.setText("00");

        jLabel15.setText("Giờ:");

        jLabel16.setText("Phút:");

        jLabel17.setText("Giây:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel11)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10)))
                        .addGap(168, 168, 168))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtGiaTri, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                                .addComponent(txtTen)
                                .addComponent(txtMa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(chkTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGio2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPhut2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGiay2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtGiaTri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jLabel13))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtGio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtPhut2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtGiay2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel15)
                                        .addComponent(jLabel16)
                                        .addComponent(jLabel17)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(chkTrangThai))))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 710, 330));

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chọn sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Source Sans Pro SemiBold", 1, 15), new java.awt.Color(255, 102, 0))); // NOI18N

        tbSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Chọn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbSP);

        cbxAll.setText("Select All");
        cbxAll.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbxAllStateChanged(evt);
            }
        });

        btnAll.setText("Thêm");
        btnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(cbxAll, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAll)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxAll)
                    .addComponent(btnAll))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 90, 410, 330));

        jTabbedPane1.setFont(new java.awt.Font("Source Sans Pro SemiBold", 0, 12)); // NOI18N

        jPanel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbKM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbKM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã KM", "Tên KM", "Giá trị", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"
            }
        ));
        tbKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKMMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbKM);

        btnLoc.setBackground(new java.awt.Color(255, 102, 51));
        btnLoc.setFont(new java.awt.Font("Source Sans Pro SemiBold", 0, 15)); // NOI18N
        btnLoc.setForeground(new java.awt.Color(255, 255, 255));
        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        cbLoc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbLoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtTim.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btnTim.setBackground(new java.awt.Color(255, 102, 51));
        btnTim.setFont(new java.awt.Font("Source Sans Pro SemiBold", 0, 15)); // NOI18N
        btnTim.setForeground(new java.awt.Color(255, 255, 255));
        btnTim.setText("Tìm khuyến mại");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(btnTim)
                        .addGap(18, 18, 18)
                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(cbLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1091, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLoc)
                    .addComponent(cbLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );

        jTabbedPane1.addTab("Danh sách voucher", jPanel20);

        tbSPKM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Mã KM", "Tên SP"
            }
        ));
        jScrollPane3.setViewportView(tbSPKM);

        btnLoad.setText("Load");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        chkReset.setText("Reset");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkReset, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 843, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(btnLoad)))
                .addGap(134, 134, 134))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(btnLoad)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(chkReset)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sản phẩm KM", jPanel21);

        jPanel2.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 1120, 310));

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
        jLabel8.setText("Nhân Viên");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1123, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        DoiMatKhauForm dmk = new DoiMatKhauForm();
        dmk.setVisible(true);
        dmk.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton8ActionPerformed

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

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        Integer int1 = Integer.valueOf(lblMaNVLay.getText());

        NhanVienView1 bh = new NhanVienView1(int1);
        bh.show();
        bh.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        //        // TODO add your handling code here:
        if (check()) {
            String them = khuyenMaiService.add(getKM());
            JOptionPane.showMessageDialog(this, them);
            showData(new KhuyenMaiServiceImpl().getAll());
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        row = tbKM.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn bản ghi");
        } else {
            int choice = JOptionPane.showConfirmDialog(this, "Chắc chắn cập nhật?", "UPDATE", 0);
            if (choice == JOptionPane.YES_OPTION) {
                if (check()) {
                    KhuyenMaiReponse km = getKM();
                    km.setMaKM((khuyenMaiService.getAll().get(row).getMaKM()));
                    JOptionPane.showMessageDialog(this, khuyenMaiService.update(km));
                    showData(new KhuyenMaiServiceImpl().getAll());
                    clear();
                }

            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        //        // TODO add your handling code here:
        row = tbKM.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn bản ghi");
        } else {
            int choice = JOptionPane.showConfirmDialog(this, "Xóa không?", "Delete", 0);
            if (choice == JOptionPane.YES_OPTION) {
                KhuyenMaiReponse km = getKM();
                km.setMaKM(khuyenMaiService.getAll().get(row).getMaKM());
                JOptionPane.showMessageDialog(this, khuyenMaiService.delete(km));
                showData(new KhuyenMaiServiceImpl().getAll());
                clear();
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnNewActionPerformed

    private void cbxAllStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cbxAllStateChanged
        // TODO add your handling code here:
        if (cbxAll.isSelected()) {
            for (JCheckBox rd : listCbx) {
                rd.setSelected(true);
            }
        } else {
            for (JCheckBox rd : listCbx) {
                rd.setSelected(false);
            }
        }
        showSP(new QuanLySPServiceIplm().getAll2());
    }//GEN-LAST:event_cbxAllStateChanged

    private void btnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllActionPerformed
        // TODO add your handling code here:
        row = tbKM.getSelectedRow();
        String tt = (String) tbKM.getValueAt(row, 5);
        List<KhuyenMaiReponse> list1 = khuyenMaiService.getAll();
        List<SanPhamReponse> list2 = spser.getAll2();
        if (row == -1 || tt.equals("Ngừng áp dụng")) {
            JOptionPane.showMessageDialog(this, "Chọn Khuyến mại có hiệu lực");
        } else {
            for (int i = 0; i < list2.size(); i++) {
                if (listCbx.get(i).isSelected()) {
                    SanPhamReponse sp = list2.get(i);
                    if (!sp.getMaKM().equals(list1.get(row).getMaKM())) {
                        spser.updateSPKM(list1.get(row).getMaKM(), sp.getMaSp());
                    }
                }
            }
        }
        clear();
        showSP(new QuanLySPServiceIplm().getAll2());
        showSPKM(new QuanLySPServiceIplm().getAll3());
    }//GEN-LAST:event_btnAllActionPerformed

    private void tbKMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKMMouseClicked
        // TODO add your handling code here:
        row = tbKM.getSelectedRow();
        fillForm();
        clickKM();
    }//GEN-LAST:event_tbKMMouseClicked

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        if (cbLoc.getSelectedItem().equals("Ngừng áp dụng")) {
            showData(new KhuyenMaiServiceImpl().getAllByTT2());
        } else if (cbLoc.getSelectedItem().equals("Đang áp dụng")) {
            showData(new KhuyenMaiServiceImpl().getAllByTT());
        } else {
            showData(new KhuyenMaiServiceImpl().getAll());
        }
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        String input = txtTim.getText();
        int count = 0;
        if (input != null) {
            for (int i = 0; i < reponses.size(); i++) {
                if (input.equalsIgnoreCase(reponses.get(i).getTenKM().toString())) {
                    tbKM.setRowSelectionInterval(i, i);
                    count++;
                }
            }
        }
        if (count == 0) {
            showData(new KhuyenMaiServiceImpl().getAll());
            showSPKM(new QuanLySPServiceIplm().getAll3());
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        // TODO add your handling code here:
        row = tbSPKM.getSelectedRow();
        if (chkReset.isSelected()) {
            spser.soOut2();
            chkReset.setSelected(false);
            showSPKM(new QuanLySPServiceIplm().getAll3());
            showSP(new QuanLySPServiceIplm().getAll2());
        } else {
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Chọn bản ghi");
            } else {
                Integer tt = (Integer) tbSPKM.getValueAt(row, 0);
                spser.soOut(tt);
                showSPKM(new QuanLySPServiceIplm().getAll3());
                showSP(new QuanLySPServiceIplm().getAll2());

            }
        }
    }//GEN-LAST:event_btnLoadActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KhuyenMaiView(1).setVisible(true);
            }
        });
    }
    /**
     * @param args the command line arguments //
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(KhuyenMaiView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(KhuyenMaiView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(KhuyenMaiView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(KhuyenMaiView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new KhuyenMaiView().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAll;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbLoc;
    private javax.swing.JCheckBox cbxAll;
    private javax.swing.JCheckBox chkReset;
    private javax.swing.JCheckBox chkTrangThai;
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
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblMaNVLay;
    private javax.swing.JLabel lblThongTinDn;
    private javax.swing.JLabel lbl_DongHo;
    private javax.swing.JTable tbKM;
    private javax.swing.JTable tbSP;
    private javax.swing.JTable tbSPKM;
    private com.toedter.calendar.JDateChooser txtDate1;
    private com.toedter.calendar.JDateChooser txtDate2;
    private javax.swing.JTextField txtGiaTri;
    private javax.swing.JTextField txtGiay2;
    private javax.swing.JTextField txtGio2;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtPhut2;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
class RadioButtonRenderer implements TableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            if (value == null) {
                return null;
            }
            return (Component) value;
        }
    }

    class RadioButtonEditor extends DefaultCellEditor implements ItemListener {

        private JCheckBox button;

        public RadioButtonEditor(JCheckBox checkBox) {
            super(checkBox);
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            if (value == null) {
                return null;
            }
            button = (JCheckBox) value;
            button.addItemListener(this);
            return (Component) value;
        }

        public Object getCellEditorValue() {
            button.removeItemListener(this);
            return button;
        }

        public void itemStateChanged(ItemEvent e) {
            super.fireEditingStopped();
        }

    }
}
