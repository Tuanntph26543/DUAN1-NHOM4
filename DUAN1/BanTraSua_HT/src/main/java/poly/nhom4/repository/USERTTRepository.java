/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import poly.nhom4.domainmodel.KhachHang;
import poly.nhom4.domainmodel.USERTT;
import poly.nhom4.hibernateconfig.HibernateUtil;

/**
 *
 * @author ACER
 */
public class USERTTRepository {
     public USERTT getUSerByMaNV(int maNV) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery("From USERTT Where MANV =: MANV");// truy vấn trên entity(HQL)
        query.setParameter("MANV", maNV);
        USERTT nv = (USERTT) query.getSingleResult();
        return nv;
    }
}
