/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.nhom4.service;

import java.util.List;
import poly.nhom4.reponse.BanReponse;

/**
 *
 * @author ACER
 */
public interface QuanLyBanService {

    List<BanReponse> getBanConTrong();

    List<BanReponse> getBanAll();

    boolean updateBan(int maBan);

    boolean add(BanReponse banReponse);

    boolean update(BanReponse banReponse);

    boolean delete(BanReponse banReponse);
}
