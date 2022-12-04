/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.service.impl;

import java.util.List;
import poly.nhom4.repository.LoaiSPRepository;
import poly.nhom4.service.LoaiSPService;

/**
 *
 * @author ACER
 */
public class LoaiSPServiceIplm implements LoaiSPService{
  private final LoaiSPRepository loaiSPRepository;
    public LoaiSPServiceIplm() {
        loaiSPRepository=new LoaiSPRepository();
    }

    
    @Override
    public List<String> getLoaiSp() {
        return loaiSPRepository.getLoaiSp();
    }
    
}
