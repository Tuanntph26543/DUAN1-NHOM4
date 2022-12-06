/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.nhom4.service;

import poly.nhom4.reponse.NhaCungCapReponse;
import java.util.List;

/**
 *
 * @author My PC
 */
public interface QuanLyNCCService {

    List<NhaCungCapReponse> getAllNCC();

    boolean add(NhaCungCapReponse nhaCungCapReponse);

    boolean update(NhaCungCapReponse nhaCungCapReponse);

    boolean delete(NhaCungCapReponse nhaCungCapReponse);
}
