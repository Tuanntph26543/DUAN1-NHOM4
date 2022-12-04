/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.service.impl;

import poly.nhom4.domainmodel.USERTT;
import poly.nhom4.repository.KhachHangRepository;
import poly.nhom4.repository.USERTTRepository;
import poly.nhom4.service.UserTTService;

/**
 *
 * @author ACER
 */
public class UserTTServiceIplm implements UserTTService{
private final USERTTRepository uSERTTRepository;

    public UserTTServiceIplm() {
      uSERTTRepository=new USERTTRepository();
    }
    @Override
    public USERTT getUSerByMaNV(int maNV) {
    return uSERTTRepository.getUSerByMaNV(maNV);
    }
    
}
