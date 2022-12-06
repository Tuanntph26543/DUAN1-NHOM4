/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.service.impl;

import java.math.BigDecimal;
import java.util.List;
import poly.nhom4.domainmodel.HoaDonChiTiet;
import poly.nhom4.reponse.HDCTReponse;
import poly.nhom4.reponse.HoaDonReponse;
import poly.nhom4.repository.BanRepository;
import poly.nhom4.repository.HDCTRepository;
import poly.nhom4.repository.HoaDonRepository;
import poly.nhom4.repository.SanPhamRepository;
import poly.nhom4.service.HDCTService;

/**
 *
 * @author ACER
 */
public class HDCTIplm implements HDCTService {

    private final HDCTRepository hDCTRepository;
    private final HoaDonRepository hoaDonRepository;
    private final SanPhamRepository sanPhamRepository;
    private final BanRepository banRepository;

    public HDCTIplm() {
        hDCTRepository = new HDCTRepository();
        hoaDonRepository = new HoaDonRepository();
        sanPhamRepository = new SanPhamRepository();
        banRepository = new BanRepository();
    }

    @Override
    public boolean createHDCT(HDCTReponse hoaDon) {
        return hDCTRepository.createHDCT(new HoaDonChiTiet(hoaDon.getSoLuong(),
                hoaDon.getDonGia(), hoaDonRepository.getHDByMa(hoaDon.getMaHD()),
                sanPhamRepository.getSPByMa(hoaDon.getMaSp())));
    }

    @Override
    public List<HoaDonChiTiet> getHDCTByMaHD(int maHD) {
        return hDCTRepository.getHDCTByMaHD(maHD);
    }

    @Override
    public boolean updateHDCT(int maHD, int maSP, int soLuong) {
        return hDCTRepository.updateHDCT(maHD, maSP, soLuong);
    }

    @Override
    public boolean deleteHDBySPVe0(int maHD, int maSP) {
        return hDCTRepository.deleteHDBySPVe0(maHD, maSP);
    }

    @Override
    public boolean deleteAll(int maHD) {
        return hDCTRepository.deleteAll(maHD);
    }

    @Override
    public boolean createHDCT2(HDCTReponse hoaDon) {
        return hDCTRepository.createHDCT(new HoaDonChiTiet(
                hoaDonRepository.getHDByMa(hoaDon.getMaHD()),
                banRepository.getBanByMa(hoaDon.getMaBan())));
    }

}
