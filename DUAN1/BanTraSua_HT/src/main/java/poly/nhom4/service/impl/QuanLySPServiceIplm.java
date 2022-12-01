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
    public List<SanPhamReponse> getAll() {
        List<SanPham> list = sanPhamRepository.getAll();
        List<SanPhamReponse> list1 = new ArrayList<>();
        for (SanPham sanPham : list) {
            list1.add(new SanPhamReponse(sanPham));
        }
        return list1;
    }

    
}
