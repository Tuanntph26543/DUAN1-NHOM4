/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.repository;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import poly.nhom4.domainmodel.LoaiSP;
import poly.nhom4.hibernateconfig.HibernateUtil;

/**
 *
 * @author ACER
 */
public class LoaiSPRepository {

    public List<String> getLoaiSp() {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery("From LoaiSP");// truy vấn trên entity(HQL)
        List<LoaiSP> list = query.getResultList();
        List<String> list1 = new ArrayList<>();
        for (LoaiSP loaiSP : list) {
            list1.add(loaiSP.getTENLSP());
        }
        return list1;
    }
    
}
