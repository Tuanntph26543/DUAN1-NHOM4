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
    private Integer soNguoiMax;
    private int trangThai;

    public BanReponse() {
    }

    public BanReponse(Ban ban) {
        this.maBan = ban.getMaBan();
        this.tenBan = ban.getTenBan();
        this.soNguoiMax = ban.getSoNguoiNgoiMax();
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

    public Integer getSoNguoiMax() {
        return soNguoiMax;
    }

    public void setSoNguoiMax(Integer soNguoiMax) {
        this.soNguoiMax = soNguoiMax;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String trangThai() {
        if (trangThai == 1) {
            return "Có người";
        }
        return "Còn trống";
    }

}
