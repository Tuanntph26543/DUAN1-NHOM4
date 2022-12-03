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
 * @author nguye
 */
@Entity
@Table(name = "ncc")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NhaCungCap {

    @Id  //khoachinh
    @Column(name = "MANCC")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maNCC;

    @Column(name = "tenNCC")
    private String tenNCC;

    @Column(name = "diaChi")
    private String diaChi;

    @Column(name = "SDT")
    private String sdt;
}
