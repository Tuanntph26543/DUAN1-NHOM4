/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.nhom4.service;

import poly.nhom4.reponse.NguyenLieuReponse;
import java.util.List;

/**
 *
 * @author My PC
 */
public interface QuanLyNguyenLieuService {

    List<NguyenLieuReponse> getAllNguyenLieu();

    boolean add(NguyenLieuReponse nguyenLieuReponse);

    boolean update(NguyenLieuReponse nguyenLieuReponse);

    boolean delete(NguyenLieuReponse nguyenLieuReponse);
}
