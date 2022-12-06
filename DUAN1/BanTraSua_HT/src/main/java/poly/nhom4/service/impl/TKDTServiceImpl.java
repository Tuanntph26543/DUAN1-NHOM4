/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.service.impl;

import java.math.BigDecimal;
import java.util.List;
import poly.nhom4.repository.TKDTRepository;
import poly.nhom4.service.TKDTService;

/**
 *
 * @author Admin
 */
public class TKDTServiceImpl implements TKDTService {

    private final TKDTRepository repo;

    public TKDTServiceImpl() {
        repo = new TKDTRepository();
    }

    @Override
    public List<Object[]> getAll() {
        return repo.getAllDT();
    }

    @Override
    public List<Long> getHoaDon() {
        return repo.getHoaDon();
    }

    @Override
    public List<BigDecimal> getAllNow() {
        return repo.getAllNow();
    }

    @Override
    public List<BigDecimal> getYear() {
        return repo.getYear();
    }

    @Override
    public List<Object[]> getSP() {
        return repo.getAllSP();
    }

    @Override
    public List<String> getSeller() {
        return repo.getSeller();
    }

}
