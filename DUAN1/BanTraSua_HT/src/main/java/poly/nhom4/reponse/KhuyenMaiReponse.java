/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.reponse;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import poly.nhom4.domainmodel.KhuyenMai;

/**
 *
 * @author Admin
 */
@Getter
@Setter
@ToString
public class KhuyenMaiReponse {

    private Integer maKM;
    private String tenKM;
    private BigDecimal soTienKM;
    private String ngayBD;
    private String ngayKT;
    private Integer trangThai;
    
    public KhuyenMaiReponse() {
    }

    public KhuyenMaiReponse(KhuyenMai km) {
        this.maKM = km.getMAKM();
        this.tenKM = km.getTENKM();
        this.soTienKM = km.getSOTIENKM();
        this.ngayBD = km.getNGAYBD() + "";
        this.ngayKT = km.getNGAYKT() + "";
        this.trangThai = km.getTRANGTHAI();
    }

    public Object[] toDataRow() {
        return new Object[]{maKM, tenKM, soTienKM, ngayBD, ngayKT, trangThai == 0 ? "Ngừng Áp dụng" : "Đang Áp dụng"};
    }
}
