/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.service.impl;

import java.util.List;
import poly.nhom4.domainmodel.GiaoCa;
import poly.nhom4.repository.GiaoCaRepository;
import poly.nhom4.service.GiaoCaService;

/**
 *
 * @author ACER
 */
public class GiaoCaIplm implements GiaoCaService {

    private final GiaoCaRepository giaoCaRepository;

    public GiaoCaIplm() {
        giaoCaRepository = new GiaoCaRepository();
    }

    @Override
    public List<GiaoCa> getGiaoCaMoiNhat() {
        return giaoCaRepository.getGiaoCaMoiNhat();
    }

    @Override
    public boolean createGC(GiaoCa gc) {
        return giaoCaRepository.createGC(gc);
    }

    @Override
    public boolean updateGC(GiaoCa gc) {
        return giaoCaRepository.updateGC(gc);

    }

    @Override
    public boolean updateGC1(GiaoCa gc) {
        return giaoCaRepository.updateGC1(gc);
    }

}
