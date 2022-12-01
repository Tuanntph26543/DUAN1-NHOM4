/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.nhom4.service;

import java.util.List;
import poly.nhom4.domainmodel.HoaDonChiTiet;
import poly.nhom4.reponse.HDCTReponse;

/**
 *
 * @author ACER
 */
public interface HDCTService {

    boolean createHDCT(HDCTReponse hoaDon);

    List<HoaDonChiTiet> getHDCTByMaHD(int maHD);

    boolean updateHDCT(int maHD, int maSP, int soLuong);
}
