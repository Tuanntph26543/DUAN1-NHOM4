/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.nhom4.service;

import java.util.List;
import poly.nhom4.domainmodel.KhachHang;

/**
 *
 * @author ACER
 */
public interface KhachHangService {
    String getTenKhBySdt(String sdt);
    KhachHang getKhBySdt(String sdt);
    boolean createKH(KhachHang kh);
    List<KhachHang> getAll();
}
