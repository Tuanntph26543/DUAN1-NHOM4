/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.service.impl;

import poly.nhom4.domainmodel.NguyenLieu;
import poly.nhom4.reponse.NguyenLieuReponse;
import poly.nhom4.repository.NguyenLieuRepository;
import poly.nhom4.service.QuanLyNguyenLieuService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author My PC
 */
public class QuanLyNguyenLieuServiceImpl implements QuanLyNguyenLieuService {

    private NguyenLieuRepository nguyenLieuRepository = new NguyenLieuRepository();

    @Override
    public List<NguyenLieuReponse> getAllNguyenLieu() {
        List<NguyenLieu> lists = nguyenLieuRepository.getAll();
        List<NguyenLieuReponse> responses = new ArrayList<>();
        for (NguyenLieu x : lists) {
            NguyenLieuReponse nguyenLieuResponse = new NguyenLieuReponse(x);
            responses.add(nguyenLieuResponse);
        }
        return responses;
    }

    public static void main(String[] args) {
        List<NguyenLieuReponse> lists = new QuanLyNguyenLieuServiceImpl().getAllNguyenLieu();
        for (NguyenLieuReponse x : lists) {
            System.out.println(x.toString());
        }
    }

    @Override
    public boolean add(NguyenLieuReponse nguyenLieuReponse) {
        try {
            NguyenLieu nguyenLieu = new NguyenLieu();
            nguyenLieu.setTenNL(nguyenLieuReponse.getTenNL());
            nguyenLieu.setMoTa(nguyenLieuReponse.getMoTa());
            boolean kq = nguyenLieuRepository.add(nguyenLieu);
            if (kq == true) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(NguyenLieuReponse nguyenLieuReponse) {
        try {
            NguyenLieu nguyenLieu = new NguyenLieu();
            nguyenLieu.setMaNL(nguyenLieuReponse.getMaNL());
            nguyenLieu.setTenNL(nguyenLieuReponse.getTenNL());
            nguyenLieu.setMoTa(nguyenLieuReponse.getMoTa());
            boolean kq = nguyenLieuRepository.update(nguyenLieu);
            if (kq == true) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(NguyenLieuReponse nguyenLieuReponse) {
        try {
            NguyenLieu nguyenLieu = new NguyenLieu();
            nguyenLieu.setMaNL(nguyenLieuReponse.getMaNL());
            nguyenLieu.setTenNL(nguyenLieuReponse.getTenNL());
            nguyenLieu.setMoTa(nguyenLieuReponse.getMoTa());
            boolean kq = nguyenLieuRepository.delete(nguyenLieu);
            if (kq == true) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
