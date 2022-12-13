/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.nhom4.service;

import java.util.List;
import poly.nhom4.domainmodel.GiaoCa;
import poly.nhom4.repository.GiaoCaRepository;

/**
 *
 * @author ACER
 */
public interface GiaoCaService {

    List<GiaoCa> getGiaoCaMoiNhat();

    boolean createGC(GiaoCa gc);

    boolean updateGC(GiaoCa gc);

    boolean updateGC1(GiaoCa gc);
    boolean updateGC3(GiaoCa gc);

}
