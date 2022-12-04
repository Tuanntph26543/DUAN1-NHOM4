/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.reponse;

import poly.nhom4.domainmodel.Ban;

/**
 *
 * @author ACER
 */
public class BanReponse {

    private int maBan;
    private String tenBan;
    private int soLuong;
    private int trangThai;

    public BanReponse() {
    }

    
    public BanReponse(Ban ban) {
        this.maBan = ban.getMaBan();
        this.tenBan = ban.getTenBan();
        this.soLuong = ban.getSoNguoiNgoiMax();
        this.trangThai = ban.getTrangThai();
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
public String layTrangThai(int trangThai){
    if (trangThai==0) {
        return "Còn Trống";
    } else {
        return "Có Người";
    }
}
    
}
