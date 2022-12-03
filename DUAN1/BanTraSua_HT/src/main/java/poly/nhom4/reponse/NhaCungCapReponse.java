/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.reponse;

import poly.nhom4.domainmodel.NhaCungCap;
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
public class NhaCungCapReponse {
    private Integer maNCC;
    private String tenNCC;
    private String diaChi;
    private String sdt;

    public NhaCungCapReponse() {
    }

    public NhaCungCapReponse(NhaCungCap nhaCungCap) {
        this.maNCC = nhaCungCap.getMaNCC();
        this.tenNCC = nhaCungCap.getTenNCC();
        this.diaChi = nhaCungCap.getDiaChi();
        this.sdt = nhaCungCap.getSdt();
    }
    
    
}
