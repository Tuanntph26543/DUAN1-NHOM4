/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.domainmodel;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table
@Entity
@Setter
@Getter
@ToString

public class HoaDonChiTiet {

    @Id
    @Column(name = "MAHOADONCT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MAHOADONCT;

    @Column(name = "SOLUONG")
    private int SOLUONG;

    @Column(name = "DONGIA")
    private BigDecimal DONGIA;

    @Column(name = "MOTA")
    private String MOTA;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAHD", referencedColumnName = "MAHD")
    private HoaDon hoaDon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MASP", referencedColumnName = "MASP")
    private SanPham sanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MABAN", referencedColumnName = "MABAN")
    private Ban ban;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet( int SOLUONG, BigDecimal DONGIA, HoaDon hoaDon, SanPham sanPham, Ban ban) {
        this.SOLUONG = SOLUONG;
        this.DONGIA = DONGIA;
        this.hoaDon = hoaDon;
        this.sanPham = sanPham;
        this.ban = ban;
    }
    
    
}
