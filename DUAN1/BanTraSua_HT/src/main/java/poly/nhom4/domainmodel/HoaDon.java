/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.domainmodel;

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
public class HoaDon {

    @Id
    @Column(name = "MAHD")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MAHD;

    @Column(name = "NGAYTAO")
    private Date ngayTao;

    @Column(name = "TINHTRANG")
    private int tinhTrang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MANV", referencedColumnName = "MANV")
    private USERTT usertt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAKH", referencedColumnName = "MAKH")
    private KhachHang khachHang;

    public HoaDon() {
    }

    public HoaDon(int MAHD, Date ngayTao, int tinhTrang, USERTT usertt, KhachHang khachHang) {
        this.MAHD = MAHD;
        this.ngayTao = ngayTao;
        this.tinhTrang = tinhTrang;
        this.usertt = usertt;
        this.khachHang = khachHang;
    }

    public int getMAHD() {
        return MAHD;
    }

    public void setMAHD(int MAHD) {
        this.MAHD = MAHD;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public USERTT getUsertt() {
        return usertt;
    }

    public void setUsertt(USERTT usertt) {
        this.usertt = usertt;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "MAHD=" + MAHD + ", ngayTao=" + ngayTao + ", tinhTrang=" + tinhTrang + ", usertt=" + usertt + ", khachHang=" + khachHang + '}';
    }
    
    
}
