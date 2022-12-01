/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.repository;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import poly.nhom4.domainmodel.KhachHang;
import poly.nhom4.hibernateconfig.HibernateUtil;

/**
 *
 * @author ACER
 */
public class KhachHangRepository {
    public String getTenKhBySdt(String sdt) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery("From KhachHang Where SDT =: SDT");// truy vấn trên entity(HQL)
        query.setParameter("SDT", sdt);
        KhachHang kh = (KhachHang) query.getSingleResult();
        String list1=kh.getTENKH();
        return list1;
    }
    
     public KhachHang getKhBySdt(String sdt) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery("From KhachHang Where SDT =: SDT");// truy vấn trên entity(HQL)
        query.setParameter("SDT", sdt);
        KhachHang kh = (KhachHang) query.getSingleResult();
        return kh;
    }
     public boolean createKH(KhachHang kh){
         Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(kh);
            transaction.commit();
            return true;
        } catch (Exception e) {
        }
        return false;
     }
     
      public List<KhachHang> getAll() {
        String fromTable = "FROM KhachHang";
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(fromTable, KhachHang.class);
        List<KhachHang> lists = query.getResultList();
        return lists;
    }

}
