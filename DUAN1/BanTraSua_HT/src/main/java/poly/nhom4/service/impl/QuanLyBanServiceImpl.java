/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.service.impl;

import poly.nhom4.domainmodel.Ban;
import poly.nhom4.reponse.BanReponse;
import poly.nhom4.repository.BanRepository;
import poly.nhom4.service.QuanLyBanService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author My PC
 */
public class QuanLyBanServiceImpl implements QuanLyBanService {

    private BanRepository banRepository = new BanRepository();

    @Override
    public List<BanReponse> getAllBan() {
        List<Ban> lists = banRepository.getAll();
        List<BanReponse> responses = new ArrayList<>();
        for (Ban ban : lists) {
            BanReponse banResponse = new BanReponse(ban);
            responses.add(banResponse);
        }
        return responses;
    }
    public static void main(String[] args) {
        List<BanReponse> lists = new QuanLyBanServiceImpl().getAllBan();
        for (BanReponse x : lists) {
            System.out.println(x.toString());
        }
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
