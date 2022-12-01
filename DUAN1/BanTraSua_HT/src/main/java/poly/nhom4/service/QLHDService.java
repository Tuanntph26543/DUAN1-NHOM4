/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.nhom4.service;

import java.util.List;
import poly.nhom4.domainmodel.HoaDon;
import poly.nhom4.reponse.HoaDonReponse;

/**
 *
 * @author ACER
 */
public interface QLHDService {
    List<HoaDonReponse> getAll();
    boolean createHD(HoaDon hoaDon);
    boolean updateHD(int maHD) ;
    List<HoaDonReponse> getAllHDByTinhTrangCXN();
    boolean updateHDThanhCong(int maHD);
}
