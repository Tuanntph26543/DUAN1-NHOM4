/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.domainmodel;

import java.math.BigDecimal;
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
public class KhuyenMai {
    
    
    @Id
    @Column(name = "MAKM")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MAKM;

     @Column(name = "TENKM")
    private String TENKM;
    
    @Column(name = "SOTIENKM")
    private BigDecimal SOTIENKM;

    @Column(name = "NGAYBD")
    private Date NGAYBD;

     @Column(name = "NGAYKT")
    private Date NGAYKT;
     
      @Column(name = "TRANGTHAI")
    private int TRANGTHAI;

    public int getMAKM() {
        return MAKM;
    }

    public void setMAKM(int MAKM) {
        this.MAKM = MAKM;
    }

    public String getTENKM() {
        return TENKM;
    }

    public void setTENKM(String TENKM) {
        this.TENKM = TENKM;
    }

    public BigDecimal getSOTIENKM() {
        return SOTIENKM;
    }

    public void setSOTIENKM(BigDecimal SOTIENKM) {
        this.SOTIENKM = SOTIENKM;
    }

    public Date getNGAYBD() {
        return NGAYBD;
    }

    public void setNGAYBD(Date NGAYBD) {
        this.NGAYBD = NGAYBD;
    }

    public Date getNGAYKT() {
        return NGAYKT;
    }

    public void setNGAYKT(Date NGAYKT) {
        this.NGAYKT = NGAYKT;
    }

    public int getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setTRANGTHAI(int TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }

   
      
}
