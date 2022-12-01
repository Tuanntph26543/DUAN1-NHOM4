/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import poly.nhom4.domainmodel.HoaDon;
import poly.nhom4.domainmodel.SanPham;
import poly.nhom4.hibernateconfig.HibernateUtil;

/**
 *
 * @author ACER
 */
public class SanPhamRepository {

    public List<SanPham> getAll() {
        String fromTable = "FROM SanPham";
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(fromTable, SanPham.class);
        List<SanPham> lists = query.getResultList();
        return lists;
    }

     public boolean createSP(SanPham sanPham) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(sanPham);
            transaction.commit();
            return true;
        } catch (Exception e) {
        }
        return false;
    }
  public SanPham getSPByMa(int ma) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery("From SanPham Where MASP =: MASP");// truy vấn trên entity(HQL)
        query.setParameter("MASP", ma);
        SanPham sp = (SanPham) query.getSingleResult();
        return sp;
    }

}
