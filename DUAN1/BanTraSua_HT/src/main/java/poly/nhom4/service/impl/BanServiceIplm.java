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
import poly.nhom4.service.BanService;

/**
 *
 * @author ACER
 */
public class BanServiceIplm implements BanService {

    private final BanRepository banRepository;

    public BanServiceIplm() {
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
    

}
