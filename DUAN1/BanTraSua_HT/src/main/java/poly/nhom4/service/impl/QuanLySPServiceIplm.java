/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.service.impl;

import java.util.ArrayList;
import java.util.List;
import poly.nhom4.domainmodel.SanPham;
import poly.nhom4.reponse.SanPhamReponse;
import poly.nhom4.repository.SanPhamRepository;
import poly.nhom4.service.QuanLySPService;

/**
 *
 * @author ACER
 */
public class QuanLySPServiceIplm implements QuanLySPService {

    private final SanPhamRepository sanPhamRepository;

    public QuanLySPServiceIplm() {
        sanPhamRepository = new SanPhamRepository();
    }

    @Override
    public List<SanPhamReponse> getAllSanPham() {
        List<SanPham> list = sanPhamRepository.getAll();
        List<SanPhamReponse> list1 = new ArrayList<>();
        for (SanPham sanPham : list) {
            list1.add(new SanPhamReponse(sanPham));
        }
        return list1;
    }

    @Override
    public List<SanPhamReponse> getSPByTen(String tenSP) {
        List<SanPham> list = sanPhamRepository.getSPByTen(tenSP);
        List<SanPhamReponse> list1 = new ArrayList<>();
        for (SanPham sanPham : list) {
            list1.add(new SanPhamReponse(sanPham));
        }
        return list1;
    }

    @Override
    public SanPham getSPByMa(int ma) {
        return sanPhamRepository.getSPByMa(ma);
    }

    @Override
    public String add(SanPham sp) {
        boolean add = sanPhamRepository.add(sp);
        if (add) {
            return "Thanh cong";
        } else {
            return "That bai";
        }
    }

    @Override
    public String update(SanPham sp) {
        boolean update = sanPhamRepository.update(sp);
        if (update) {
            return "Thanh cong";
        } else {
            return "That bai";
        }
    }

    @Override
    public String delete(SanPham sp) {
        boolean delete = sanPhamRepository.delete(sp);
        if (delete) {
            return "Thanh cong";
        } else {
            return "That bai";
        }
    }

    @Override
    public List<SanPhamReponse> getAllByTT() {
        List<SanPham> lists = sanPhamRepository.getAllByTT();
        List<SanPhamReponse> reponses = new ArrayList<>();
        for (SanPham sp : lists) {
            SanPhamReponse reponse = new SanPhamReponse(sp);
            reponses.add(reponse);
        }
        return reponses;
    }

    @Override
    public List<SanPhamReponse> getAllByTT2() {
        List<SanPham> lists = sanPhamRepository.getAllByTT2();
        List<SanPhamReponse> reponses = new ArrayList<>();
        for (SanPham sp : lists) {
            SanPhamReponse reponse = new SanPhamReponse(sp);
            reponses.add(reponse);
        }
        return reponses;
    }

    @Override
    public boolean updateSPKM(int maKM, int maSP) {
        return sanPhamRepository.updateSPKM(maKM, maSP);
    }

}
