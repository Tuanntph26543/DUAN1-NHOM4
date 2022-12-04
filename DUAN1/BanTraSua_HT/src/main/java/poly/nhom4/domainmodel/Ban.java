/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.domainmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "BAN")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Ban {

    @Id  //khoachinh
    @Column(name = "MABAN")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maBan;

    @Column(name = "TENBAN")
    private String tenBan;
    
    @Column(name = "SONGUOINGOIMAX")
    private Integer soNguoiNgoiMax;
    
    @Column(name = "TRANGTHAI")
    private Integer trangThai;
}
