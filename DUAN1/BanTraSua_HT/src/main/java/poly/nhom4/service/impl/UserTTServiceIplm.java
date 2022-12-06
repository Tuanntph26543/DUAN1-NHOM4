/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.service.impl;

import java.util.ArrayList;
import java.util.List;
import poly.nhom4.domainmodel.USERTT;
import poly.nhom4.reponse.UserTTReponse;
import poly.nhom4.repository.KhachHangRepository;
import poly.nhom4.repository.USERTTRepository;
import poly.nhom4.service.UserTTService;

/**
 *
 * @author ACER
 */
public class UserTTServiceIplm implements UserTTService {

    private final USERTTRepository userTTRepository = new USERTTRepository();

    ;


    @Override
    public USERTT getUSerByMaNV(int maNV) {
        return userTTRepository.getUSerByMaNV(maNV);
    }

    @Override
    public List<UserTTReponse> getAllUserTTs() {
        // B1: lấy list từ repository
        List<USERTT> lists = userTTRepository.getAll();
        // B2: convert list từ reposotory sang list response
        List<UserTTReponse> lst = new ArrayList<>();
//        return lists.stream().map(UserTTReponse::new).collect(Collectors.toList());
        for (USERTT userDomain : lists) {
            UserTTReponse y = new UserTTReponse(userDomain);
            lst.add(y);
        }
        return lst;
    }

    @Override
    public UserTTReponse dangNhap(String taiKhoanNhap, String matKhauNhap) {
        List<USERTT> lists = userTTRepository.getAll();
        // B2: convert list từ reposotory sang list response
//        List<UserTTReponse> lst = new ArrayList<>();
//        return lists.stream().map(UserTTReponse::new).collect(Collectors.toList());
        for (USERTT userDomain : lists) {
            if (userDomain.getTAIKHOAN().equals(taiKhoanNhap) && userDomain.getMATKHAU().equalsIgnoreCase(matKhauNhap)) {
                UserTTReponse y = new UserTTReponse(userDomain);
                return y;
            } else {
                return null;
            }

        }
        return null;

    }

    @Override
    public boolean updateUser(UserTTReponse userReponse) {
        try {
            USERTT user = new USERTT();
            user.setMANV(userReponse.getMaNV());
            user.setCCCD(userReponse.getCccd());
            user.setCHUCVU(userReponse.getChucVu());
            user.setDIACHI(userReponse.getDiaChi());
            user.setGIOITINH(userReponse.getGioiTinh());
            user.setHOTEN(userReponse.getHoTen());
            user.setMATKHAU(userReponse.getMatKhau());
            user.setNAMSINH(userReponse.getNamSinh());
            user.setSDT(userReponse.getSdt());
            user.setTAIKHOAN(userReponse.getTaiKhoan());
            user.setTRANGTHAI(1);
            boolean kq = userTTRepository.update(user);
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
    public boolean insertUser(UserTTReponse userReponse) {
        try {
            USERTT user = new USERTT();
            user.setMANV(userReponse.getMaNV());
            user.setCCCD(userReponse.getCccd());
            user.setCHUCVU(userReponse.getChucVu());
            user.setDIACHI(userReponse.getDiaChi());
            user.setGIOITINH(userReponse.getGioiTinh());
            user.setHOTEN(userReponse.getHoTen());
            user.setMATKHAU(userReponse.getMatKhau());
            user.setNAMSINH(userReponse.getNamSinh());
            user.setSDT(userReponse.getSdt());
            user.setTAIKHOAN(userReponse.getTaiKhoan());
            user.setTRANGTHAI(1);
            boolean kq = userTTRepository.add(user);
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
