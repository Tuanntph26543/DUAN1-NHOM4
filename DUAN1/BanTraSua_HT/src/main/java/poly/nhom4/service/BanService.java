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
public interface BanService {
    List<BanReponse> getBanConTrong();
    List<BanReponse> getBanAll();

     boolean updateBan(int maBan);
}
