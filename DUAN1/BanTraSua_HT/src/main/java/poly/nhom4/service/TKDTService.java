/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.nhom4.service;

import java.math.BigDecimal;
import java.util.List;


/**
 *
 * @author Admin
 */
public interface TKDTService {

    List<Object[]> getAll();

    List<Long> getHoaDon();

    List<BigDecimal> getAllNow();

    List<BigDecimal> getYear();

    List< Object[]> getSP();

    List<String> getSeller();
}
