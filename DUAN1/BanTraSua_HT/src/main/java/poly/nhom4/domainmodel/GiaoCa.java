/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.domainmodel;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ACER
 */
@Table
@Entity
public class GiaoCa {

    @Id
    @Column(name = "MACA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MACA;

    @Column(name = "THOIGIANNHANCA")
    private LocalDateTime THOIGIANNHANCA;

    @Column(name = "THOIGIANGIAOCA")
    private LocalDateTime THOIGIANGIAOCA;

    @Column(name = "TIENBANDAU")
    private BigDecimal TIENBANDAU;

    @Column(name = "TONGTIENTRONGCA")
    private BigDecimal TONGTIENTRONGCA;

    @Column(name = "THOIGIANRESET")
    private LocalDateTime THOIGIANRESET;

    @Column(name = "TONGTIENRUT")
    private BigDecimal TONGTIENRUT;

    @Column(name = "TRANGTHAI")
    private int TRANGTHAI;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MANHANVIENTRONGCA", referencedColumnName = "MANV")
    private USERTT usertt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MANHANVIENNHANCA", referencedColumnName = "MANV")
    private USERTT usertt1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAQUANLIRESET", referencedColumnName = "MANV")
    private USERTT usertt2;

    public GiaoCa() {
    }

    public GiaoCa(int MACA, LocalDateTime THOIGIANNHANCA, LocalDateTime THOIGIANGIAOCA, BigDecimal TIENBANDAU, BigDecimal TONGTIENTRONGCA, LocalDateTime THOIGIANRESET, BigDecimal TONGTIENRUT, int TRANGTHAI, USERTT usertt, USERTT usertt1, USERTT usertt2) {
        this.MACA = MACA;
        this.THOIGIANNHANCA = THOIGIANNHANCA;
        this.THOIGIANGIAOCA = THOIGIANGIAOCA;
        this.TIENBANDAU = TIENBANDAU;
        this.TONGTIENTRONGCA = TONGTIENTRONGCA;
        this.THOIGIANRESET = THOIGIANRESET;
        this.TONGTIENRUT = TONGTIENRUT;
        this.TRANGTHAI = TRANGTHAI;
        this.usertt = usertt;
        this.usertt1 = usertt1;
        this.usertt2 = usertt2;
    }

    public int getMACA() {
        return MACA;
    }

    public void setMACA(int MACA) {
        this.MACA = MACA;
    }

    public LocalDateTime getTHOIGIANNHANCA() {
        return THOIGIANNHANCA;
    }

    public void setTHOIGIANNHANCA(LocalDateTime THOIGIANNHANCA) {
        this.THOIGIANNHANCA = THOIGIANNHANCA;
    }

    public LocalDateTime getTHOIGIANGIAOCA() {
        return THOIGIANGIAOCA;
    }

    public void setTHOIGIANGIAOCA(LocalDateTime THOIGIANGIAOCA) {
        this.THOIGIANGIAOCA = THOIGIANGIAOCA;
    }

    public BigDecimal getTIENBANDAU() {
        return TIENBANDAU;
    }

    public void setTIENBANDAU(BigDecimal TIENBANDAU) {
        this.TIENBANDAU = TIENBANDAU;
    }

    public BigDecimal getTONGTIENTRONGCA() {
        return TONGTIENTRONGCA;
    }

    public void setTONGTIENTRONGCA(BigDecimal TONGTIENTRONGCA) {
        this.TONGTIENTRONGCA = TONGTIENTRONGCA;
    }

    public LocalDateTime getTHOIGIANRESET() {
        return THOIGIANRESET;
    }

    public void setTHOIGIANRESET(LocalDateTime THOIGIANRESET) {
        this.THOIGIANRESET = THOIGIANRESET;
    }

    public BigDecimal getTONGTIENRUT() {
        return TONGTIENRUT;
    }

    public void setTONGTIENRUT(BigDecimal TONGTIENRUT) {
        this.TONGTIENRUT = TONGTIENRUT;
    }

    public int getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setTRANGTHAI(int TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }

    public USERTT getUsertt() {
        return usertt;
    }

    public void setUsertt(USERTT usertt) {
        this.usertt = usertt;
    }

    public USERTT getUsertt1() {
        return usertt1;
    }

    public void setUsertt1(USERTT usertt1) {
        this.usertt1 = usertt1;
    }

    public USERTT getUsertt2() {
        return usertt2;
    }

    public void setUsertt2(USERTT usertt2) {
        this.usertt2 = usertt2;
    }

    @Override
    public String toString() {
        return "GiaoCa{" + "MACA=" + MACA + ", THOIGIANNHANCA=" + THOIGIANNHANCA + ", THOIGIANGIAOCA=" + THOIGIANGIAOCA + ", TIENBANDAU=" + TIENBANDAU + ", TONGTIENTRONGCA=" + TONGTIENTRONGCA + ", THOIGIANRESET=" + THOIGIANRESET + ", TONGTIENRUT=" + TONGTIENRUT + ", TRANGTHAI=" + TRANGTHAI + ", usertt=" + usertt + ", usertt1=" + usertt1 + ", usertt2=" + usertt2 + '}';
    }

   
}
