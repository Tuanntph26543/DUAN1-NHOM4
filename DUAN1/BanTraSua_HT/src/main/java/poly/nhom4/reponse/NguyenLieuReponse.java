/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.reponse;

import poly.nhom4.domainmodel.NguyenLieu;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author My PC
 */
@Getter
@Setter
@ToString
public class NguyenLieuReponse {
    private Integer maNL;
    private String tenNL;
    private String moTa;

    public NguyenLieuReponse() {
    }

    public NguyenLieuReponse(NguyenLieu nguyenLieu) {
        this.maNL = nguyenLieu.getMaNL();
        this.tenNL = nguyenLieu.getTenNL();
        this.moTa = nguyenLieu.getMoTa();
    }

    
}
