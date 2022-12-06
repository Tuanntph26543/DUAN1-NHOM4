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
@Table(name = "NGUYENLIEU")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NguyenLieu {

    @Id  //khoachinh
    @Column(name = "MANL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maNL;

    @Column(name = "TENNL")
    private String tenNL;

    @Column(name = "MOTA")
    private String moTa;

}
