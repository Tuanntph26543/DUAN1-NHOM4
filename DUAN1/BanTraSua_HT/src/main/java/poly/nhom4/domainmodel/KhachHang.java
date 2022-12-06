/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.domainmodel;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ACER
 */
@Table
@Entity
public class KhachHang {
     
    @Id
    @Column(name = "MAKH")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MAKH;

    @Column(name = "TENKH")
    private String TENKH;

    @Column(name = "DIACHI")
    private String DIACHI;

     @Column(name = "SDT")
    private String SDT;

    public KhachHang() {
    }

    public KhachHang(int MAKH, String TENKH, String DIACHI, String SDT) {
        this.MAKH = MAKH;
        this.TENKH = TENKH;
        this.DIACHI = DIACHI;
        this.SDT = SDT;
    }

    public int getMAKH() {
        return MAKH;
    }

    public void setMAKH(int MAKH) {
        this.MAKH = MAKH;
    }

    public String getTENKH() {
        return TENKH;
    }

    public void setTENKH(String TENKH) {
        this.TENKH = TENKH;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
     
    
     
}
