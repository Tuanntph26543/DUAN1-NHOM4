/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.service.impl;

import poly.nhom4.domainmodel.NhaCungCap;
import poly.nhom4.reponse.NhaCungCapReponse;
import poly.nhom4.repository.NhaCungCapRepository;
import poly.nhom4.service.QuanLyNCCService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author My PC
 */
public class QuanLyNCCServiceImpl implements QuanLyNCCService {

    private NhaCungCapRepository nhaCungCapRepository = new NhaCungCapRepository();

    @Override
    public List<NhaCungCapReponse> getAllNCC() {
        List<NhaCungCap> lists = nhaCungCapRepository.getAll();
        List<NhaCungCapReponse> responses = new ArrayList<>();
        for (NhaCungCap ncc : lists) {
            NhaCungCapReponse nhaCungCapResponse = new NhaCungCapReponse(ncc);
            responses.add(nhaCungCapResponse);
        }
        return responses;
    }

    public static void main(String[] args) {
        List<NhaCungCapReponse> lists = new QuanLyNCCServiceImpl().getAllNCC();
        for (NhaCungCapReponse x : lists) {
            System.out.println(x.toString());
        }
    }

    @Override
    public boolean add(NhaCungCapReponse nhaCungCapReponse) {
        try {
            NhaCungCap ncc = new NhaCungCap();
            ncc.setTenNCC(nhaCungCapReponse.getTenNCC());
            ncc.setDiaChi(nhaCungCapReponse.getDiaChi());
            ncc.setSdt(nhaCungCapReponse.getSdt());
            boolean kq = nhaCungCapRepository.add(ncc);
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
    public boolean update(NhaCungCapReponse nhaCungCapReponse) {
        try {
            NhaCungCap ncc = new NhaCungCap();
            ncc.setMaNCC(nhaCungCapReponse.getMaNCC());
            ncc.setTenNCC(nhaCungCapReponse.getTenNCC());
            ncc.setDiaChi(nhaCungCapReponse.getDiaChi());
            ncc.setSdt(nhaCungCapReponse.getSdt());
            boolean kq = nhaCungCapRepository.update(ncc);
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
    public boolean delete(NhaCungCapReponse nhaCungCapReponse) {
        try {
            NhaCungCap ncc = new NhaCungCap();
            ncc.setMaNCC(nhaCungCapReponse.getMaNCC());
            ncc.setTenNCC(nhaCungCapReponse.getTenNCC());
            ncc.setDiaChi(nhaCungCapReponse.getDiaChi());
            ncc.setSdt(nhaCungCapReponse.getSdt());
            boolean kq = nhaCungCapRepository.delete(ncc);
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
