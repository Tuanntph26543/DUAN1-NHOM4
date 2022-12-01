/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.service.impl;

import java.util.ArrayList;
import java.util.List;
import poly.nhom4.domainmodel.HoaDon;
import poly.nhom4.reponse.HoaDonReponse;
import poly.nhom4.repository.HoaDonRepository;
import poly.nhom4.service.QLHDService;

/**
 *
 * @author ACER
 */
public class QLHDServiceIplm implements QLHDService {

    private final HoaDonRepository hoaDonRepository;

    public QLHDServiceIplm() {
        hoaDonRepository = new HoaDonRepository();
    }

    @Override
    public List<HoaDonReponse> getAll() {
        List<HoaDon> list = hoaDonRepository.getAll();
        List<HoaDonReponse> list1 = new ArrayList<>();
        for (HoaDon hd : list) {
            list1.add(new HoaDonReponse(hd));
        }
        return list1;
    }

    @Override
    public boolean createHD(HoaDon hoaDon) {
        return hoaDonRepository.createHD(hoaDon);
    }

    @Override
    public boolean updateHD(int maHD) {
        return hoaDonRepository.updateHD(maHD);
    }

    @Override
    public List<HoaDonReponse> getAllHDByTinhTrangCXN() {
        List<HoaDon> list = hoaDonRepository.getAllHDByTinhTrangCXN();
        List<HoaDonReponse> list1 = new ArrayList<>();
        for (HoaDon hd : list) {
            list1.add(new HoaDonReponse(hd));
        }
        return list1;
    }

    @Override
    public boolean updateHDThanhCong(int maHD) {
        return hoaDonRepository.updateHDThanhCong(maHD);
    }

}
