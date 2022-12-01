/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.reponse;

import java.math.BigDecimal;
import poly.nhom4.domainmodel.SanPham;

/**
 *
 * @author ACER
 */
public class SanPhamReponse {

    private int maSp;
    private String tenSp;
    private BigDecimal donGia;
    private BigDecimal soTienKM;
    private String anhSP;
    private int trangThai;

    public SanPhamReponse(SanPham sanPham) {
        this.maSp = sanPham.getMASP();
        this.tenSp = sanPham.getTENSP();
        this.donGia = sanPham.getGIABAN();
        this.soTienKM = sanPham.getKHUYENMAI().getSOTIENKM();
        this.anhSP = sanPham.getANHSP();
        this.trangThai = sanPham.getTRANGTHAI();
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

    public String getAnhSP() {
        return anhSP;
    }

    public void setAnhSP(String anhSP) {
        this.anhSP = anhSP;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String layTrangThai(int trangThai1) {
        if (trangThai1 == 0) {
            return "Dang Ban";
        } else {
            return "Ngung Ban";
        }

    }

}
