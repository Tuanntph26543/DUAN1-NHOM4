/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.reponse;

import poly.nhom4.domainmodel.Ban;
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
public class BanReponse { // response truy vấn trực tiếp trên entity
    private Integer maBan;
    private String tenBan;
    private Integer soNguoiMax;
    private Integer trangThai;


    public BanReponse() {
    }

    public BanReponse(Ban ban) {
        this.maBan = ban.getMaBan();
        this.tenBan = ban.getTenBan();
        this.soNguoiMax = ban.getSoNguoiNgoiMax();
        this.trangThai = ban.getTrangThai();
    }

    public String trangThai(){
        if(trangThai == 1){
            return "Có người";
        }
        return "Còn trống";
    }


    
    
}
