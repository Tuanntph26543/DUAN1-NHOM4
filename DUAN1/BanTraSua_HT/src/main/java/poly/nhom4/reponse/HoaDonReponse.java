/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.reponse;

import java.sql.Date;
import poly.nhom4.domainmodel.HoaDon;

/**
 *
 * @author ACER
 */
public class HoaDonReponse {
    private int MAHD;
    private String NHANVIEN;
    private String KHACHHANG;
    private Date ngayTao;
    private int trangThai;

    public HoaDonReponse(HoaDon hoaDon) {
        this.MAHD = hoaDon.getMAHD();
        this.NHANVIEN = hoaDon.getUsertt().getHOTEN();
        this.KHACHHANG = hoaDon.getKhachHang().getTENKH();
        this.ngayTao = hoaDon.getNgayTao();
        this.trangThai = hoaDon.getTinhTrang();
    }

    
    
    public int getMAHD() {
        return MAHD;
    }

    public void setMAHD(int MAHD) {
        this.MAHD = MAHD;
    }

    public String getNHANVIEN() {
        return NHANVIEN;
    }

    public void setNHANVIEN(String NHANVIEN) {
        this.NHANVIEN = NHANVIEN;
    }

    public String getKHACHHANG() {
        return KHACHHANG;
    }

    public void setKHACHHANG(String KHACHHANG) {
        this.KHACHHANG = KHACHHANG;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    public String layTrangThai(int trangThai1){
        if (trangThai1==0) {
            return "Chờ Order";
        } else if(trangThai1==1) {
            return "Chờ Xác Nhận";
        } else{
            return "Hoàn Thành";
        }
    }
}
