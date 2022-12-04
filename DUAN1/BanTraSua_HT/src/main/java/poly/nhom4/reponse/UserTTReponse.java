/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.reponse;

import lombok.AllArgsConstructor;
import poly.nhom4.domainmodel.ChucVu;
import poly.nhom4.domainmodel.USERTT;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author FPT Shop
 */
@Getter
@Setter
@ToString
public class UserTTReponse {

    private Integer maNV;
    private ChucVu chucVu;
    private String tenCV;
    private String hoTen;
    private String gioiTinh;
    private Integer namSinh;
    private String diaChi;
    private String cccd;
    private String sdt;
    private String taiKhoan;
    private String matKhau;
    private Integer trangThai;

    public UserTTReponse() {
    }

    public UserTTReponse(USERTT user) {
        this.maNV = user.getMANV();
        this.chucVu = user.getCHUCVU();
        this.tenCV = user.getCHUCVU() == null ? null : user.getCHUCVU().getTENCV();
        this.hoTen = user.getHOTEN();
        this.gioiTinh = user.getGIOITINH();
        this.namSinh = user.getNAMSINH();
        this.diaChi = user.getDIACHI();
        this.cccd = user.getCCCD();
        this.sdt = user.getSDT();
        this.taiKhoan = user.getTAIKHOAN();
        this.matKhau = user.getMATKHAU();
        this.trangThai = user.getTRANGTHAI();
    }

}
