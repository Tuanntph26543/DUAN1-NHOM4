/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.nhom4.service;

import java.util.List;
import poly.nhom4.domainmodel.SanPham;
import poly.nhom4.reponse.SanPhamReponse;

/**
 *
 * @author ACER
 */
public interface QuanLySPService {

    List<SanPhamReponse> getAllSanPham();

    List<SanPhamReponse> getAllByTT();

    List<SanPhamReponse> getAllByTT2();

    SanPham getSPByMa(int ma);

    String add(SanPham sp);

    String update(SanPham maSP);

    String delete(SanPham sp);

    List<SanPhamReponse> getSPByTen(String tenSP);

    boolean updateSPKM(int maKM, int maSP);
}
