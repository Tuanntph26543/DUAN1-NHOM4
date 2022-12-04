/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.domainmodel;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ACER
 */
@Table
@Entity
public class USERTT {

    @Id
    @Column(name = "MANV")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MANV;

    @Column(name = "HOTEN")
    private String HOTEN;

    @Column(name = "GIOITINH")
    private String GIOITINH;

    @Column(name = "NAMSINH")
    private String NAMSINH;

    @Column(name = "DIACHI")
    private String DIACHI;

    @Column(name = "CCCD")
    private String CCCD;

    @Column(name = "SDT")
    private String SDT;

    @Column(name = "TAIKHOAN")
    private String TAIKHOAN;

    @Column(name = "MATKHAU")
    private String MATKHAU;

    @Column(name = "TRANGTHAI")
    private int TRANGTHAI;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MACV", referencedColumnName = "MACV")
    private ChucVu CHUCVU;

    public USERTT() {
    }

    public USERTT(int MANV, String HOTEN, String GIOITINH, String NAMSINH, String DIACHI, String CCCD, String SDT, String TAIKHOAN, String MATKHAU, int TRANGTHAI, ChucVu CHUCVU) {
        this.MANV = MANV;
        this.HOTEN = HOTEN;
        this.GIOITINH = GIOITINH;
        this.NAMSINH = NAMSINH;
        this.DIACHI = DIACHI;
        this.CCCD = CCCD;
        this.SDT = SDT;
        this.TAIKHOAN = TAIKHOAN;
        this.MATKHAU = MATKHAU;
        this.TRANGTHAI = TRANGTHAI;
        this.CHUCVU = CHUCVU;
    }

    public int getMANV() {
        return MANV;
    }

    public void setMANV(int MANV) {
        this.MANV = MANV;
    }

    public String getHOTEN() {
        return HOTEN;
    }

    public void setHOTEN(String HOTEN) {
        this.HOTEN = HOTEN;
    }

    public String getGIOITINH() {
        return GIOITINH;
    }

    public void setGIOITINH(String GIOITINH) {
        this.GIOITINH = GIOITINH;
    }

    public String getNAMSINH() {
        return NAMSINH;
    }

    public void setNAMSINH(String NAMSINH) {
        this.NAMSINH = NAMSINH;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getTAIKHOAN() {
        return TAIKHOAN;
    }

    public void setTAIKHOAN(String TAIKHOAN) {
        this.TAIKHOAN = TAIKHOAN;
    }

    public String getMATKHAU() {
        return MATKHAU;
    }

    public void setMATKHAU(String MATKHAU) {
        this.MATKHAU = MATKHAU;
    }

    public int getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setTRANGTHAI(int TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }

    public ChucVu getCHUCVU() {
        return CHUCVU;
    }

    public void setCHUCVU(ChucVu CHUCVU) {
        this.CHUCVU = CHUCVU;
    }

}
