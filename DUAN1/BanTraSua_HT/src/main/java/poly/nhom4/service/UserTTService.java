/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.nhom4.service;

import java.util.List;
import poly.nhom4.domainmodel.USERTT;
import poly.nhom4.reponse.UserTTReponse;

/**
 *
 * @author ACER
 */
public interface UserTTService {

    USERTT getUSerByMaNV(int maNV);

    List<UserTTReponse> getAllUserTTs();

    UserTTReponse dangNhap(String taiKhoanNhap, String matKhauNhap);

    boolean insertUser(UserTTReponse userReponse);

    boolean updateUser(UserTTReponse userReponse);

    boolean delete(UserTTReponse userReponse);

}
