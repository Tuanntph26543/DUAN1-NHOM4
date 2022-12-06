/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.reponse;

import java.math.BigDecimal;
import poly.nhom4.domainmodel.HoaDonChiTiet;

/**
 *
 * @author ACER
 */
public class HDCTReponse {

    private int maSp;
    private String tenSp;
    private BigDecimal donGia;
    private BigDecimal soTienKM;
    private int soLuong;
    private BigDecimal thanhTien;
    private int maHD;
    private int maBan;

    public HDCTReponse() {
    }

    public HDCTReponse(HoaDonChiTiet hdct) {
        this.maSp = hdct.getSanPham().getMASP();
        this.tenSp = hdct.getSanPham().getTENSP();
        this.donGia = hdct.getDONGIA();
        this.soTienKM = hdct.getSanPham().getKHUYENMAI().getSOTIENKM();
        this.soLuong = hdct.getSOLUONG();
        this.maHD=hdct.getHoaDon().getMAHD();
    }

    public int getMaSp() {
        return maSp;
    }

    public void setMaSp(int maSp) {
        this.maSp = maSp;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public BigDecimal getSoTienKM() {
        return soTienKM;
    }

    public void setSoTienKM(BigDecimal soTienKM) {
        this.soTienKM = soTienKM;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }
    
}
