/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.repository;

import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import poly.nhom4.domainmodel.HoaDon;
import poly.nhom4.hibernateconfig.HibernateUtil;

/**
 *
 * @author ACER
 */
public class HoaDonRepository {

    public List<HoaDon> getAll() {
        String fromTable = "FROM HoaDon Where TinhTrang=0 Order By MAHD DESC";
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(fromTable, HoaDon.class);
        List<HoaDon> lists = query.getResultList();
        return lists;
    }
    public List<HoaDon> getAllHDByTinhTrangCXN() {
        String fromTable = "FROM HoaDon Where TinhTrang=1 Order By MAHD DESC";
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(fromTable, HoaDon.class);
        List<HoaDon> lists = query.getResultList();
        return lists;
    }
    public HoaDon getHDByMa(int ma) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery("From HoaDon Where MAHD =: MAHD");// truy vấn trên entity(HQL)
        query.setParameter("MAHD", ma);
        HoaDon hd = (HoaDon) query.getSingleResult();
        return hd;
    }

    public boolean createHD(HoaDon hoaDon) {

        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(hoaDon);
            transaction.commit();
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean updateHD(int maHD) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            org.hibernate.query.Query query = session.createQuery("update HoaDon set TinhTrang = :TinhTrang Where MAHD =:MAHD");
            query.setParameter("TinhTrang", 1);
            query.setParameter("MAHD", maHD);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {

        }
        return false;
    }
 public boolean updateHDThanhCong(int maHD) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            org.hibernate.query.Query query = session.createQuery("update HoaDon set TinhTrang = :TinhTrang Where MAHD =:MAHD");
            query.setParameter("TinhTrang", 2);
            query.setParameter("MAHD", maHD);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {

        }
        return false;
    }
   
}
