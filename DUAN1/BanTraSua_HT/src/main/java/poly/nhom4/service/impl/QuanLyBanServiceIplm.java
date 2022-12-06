/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.service.impl;

import java.util.ArrayList;
import java.util.List;
import poly.nhom4.domainmodel.Ban;
import poly.nhom4.reponse.BanReponse;
import poly.nhom4.repository.BanRepository;
import poly.nhom4.service.QuanLyBanService;

/**
 *
 * @author ACER
 */
public class QuanLyBanServiceIplm implements QuanLyBanService {

    private final BanRepository banRepository;

    public QuanLyBanServiceIplm() {
        banRepository = new BanRepository();
    }

    @Override
    public List<BanReponse> getBanConTrong() {
        List<Ban> list = banRepository.getBanConTrong();
        List<BanReponse> list1 = new ArrayList<>();
        for (Ban ban : list) {
            list1.add(new BanReponse(ban));
        }
        return list1;
    }

    @Override
    public boolean updateBan(int maBan) {
        return banRepository.updateBan(maBan);
    }

    @Override
    public List<BanReponse> getBanAll() {
        List<Ban> list = banRepository.getBanAll();
        List<BanReponse> list1 = new ArrayList<>();
        for (Ban ban : list) {
            list1.add(new BanReponse(ban));
        }
        return list1;
    }
     @Override
    public boolean add(BanReponse banReponse) {
        try {
            Ban ban = new Ban();
            ban.setTenBan(banReponse.getTenBan());
            ban.setSoNguoiNgoiMax(banReponse.getSoNguoiMax());
            ban.setTrangThai(banReponse.getTrangThai());
            boolean kq = banRepository.add(ban);
            if(kq == true){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(BanReponse banReponse) {
        try {
            Ban ban = new Ban();
            ban.setMaBan(banReponse.getMaBan());
            ban.setTenBan(banReponse.getTenBan());
            ban.setSoNguoiNgoiMax(banReponse.getSoNguoiMax());
            ban.setTrangThai(banReponse.getTrangThai());
            boolean kq = banRepository.update(ban);
            if(kq == true){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(BanReponse banReponse) {
        try {
            Ban ban = new Ban();
            ban.setMaBan(banReponse.getMaBan());
            ban.setTenBan(banReponse.getTenBan());
            ban.setSoNguoiNgoiMax(banReponse.getSoNguoiMax());
            ban.setTrangThai(banReponse.getTrangThai());
            boolean kq = banRepository.delete(ban);
            if(kq == true){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

}
