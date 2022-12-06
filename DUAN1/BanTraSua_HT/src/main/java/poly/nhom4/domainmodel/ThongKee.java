/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.domainmodel;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class ThongKee {
    private Date ngayTao;
    private BigDecimal doanhThu;

    public ThongKee() {
    }

    public ThongKee(Date ngayTao, BigDecimal doanhThu) {
        this.ngayTao = ngayTao;
        this.doanhThu = doanhThu;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public BigDecimal getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(BigDecimal doanhThu) {
        this.doanhThu = doanhThu;
    }
    
}