/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.domainmodel;

import java.math.BigDecimal;
import java.sql.Date;
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
public class SanPham {

    @Id
    @Column(name = "MASP")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MASP;

    @Column(name = "GIABAN")
    private BigDecimal GIABAN;

    @Column(name = "TRANGTHAI")
    private int TRANGTHAI;
    
    
    @Column(name = "TENSP")
    private String TENSP;
    
    @Column(name = "ANHSP")
    private String ANHSP;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAKM", referencedColumnName = "MAKM")
    private KhuyenMai KHUYENMAI;

    public int getMASP() {
        return MASP;
    }

    public void setMASP(int MASP) {
        this.MASP = MASP;
    }

    public BigDecimal getGIABAN() {
        return GIABAN;
    }

    public void setGIABAN(BigDecimal GIABAN) {
        this.GIABAN = GIABAN;
    }

    public int getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setTRANGTHAI(int TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }

    public String getTENSP() {
        return TENSP;
    }

    public void setTENSP(String TENSP) {
        this.TENSP = TENSP;
    }

    public String getANHSP() {
        return ANHSP;
    }

    public void setANHSP(String ANHSP) {
        this.ANHSP = ANHSP;
    }

    public KhuyenMai getKHUYENMAI() {
        return KHUYENMAI;
    }

    public void setKHUYENMAI(KhuyenMai KHUYENMAI) {
        this.KHUYENMAI = KHUYENMAI;
    }

    @Override
    public String toString() {
        return "SanPham{" + "MASP=" + MASP + ", GIABAN=" + GIABAN + ", TRANGTHAI=" + TRANGTHAI + ", TENSP=" + TENSP + ", ANHSP=" + ANHSP + ", KHUYENMAI=" + KHUYENMAI + '}';
    }

  
}
