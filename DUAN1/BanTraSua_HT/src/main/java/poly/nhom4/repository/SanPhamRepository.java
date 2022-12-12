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
import poly.nhom4.domainmodel.KhuyenMai;
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

    public List<SanPham> getAllByTT() {
        String sql = "from SanPham WHERE TRANGTHAI=0";
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(sql, SanPham.class);
        List<SanPham> lists = query.getResultList();
        return lists;
    }

    public List<SanPham> getAllByTT2() {
        String sql = "from SanPham WHERE TRANGTHAI=1";
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(sql, SanPham.class);
        List<SanPham> lists = query.getResultList();
        return lists;
    }
    
    public SanPham getSPByMa(int ma) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery("From SanPham Where MASP =: MASP");// truy vấn trên entity(HQL)
        query.setParameter("MASP", ma);
        SanPham sp = (SanPham) query.getSingleResult();
        return sp;
    }

    public List<SanPham> getSPByTen(String tenSP) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery("From SanPham Where TENSP =: TENSP");// truy vấn trên entity(HQL)
        query.setParameter("TENSP", tenSP);
        List<SanPham> list = query.getResultList();
        return list;
    }
    
      public Boolean add(SanPham sp) {
        Transaction transaction = null;
        try {
            
            Session session = HibernateUtil.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.save(sp);
            transaction.commit();
            getAll();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public Boolean update(SanPham sp) {
        Transaction transaction = null;
        try (
            
            Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.update(sp);
            transaction.commit();
            getAll();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public Boolean delete(SanPham sp) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.delete(sp);
            transaction.commit();
            getAll();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public List<Integer> getMaAll() {
        String fromTable = "FROM KhuyenMai ";
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(fromTable, KhuyenMai.class);
        List<Integer> list = new ArrayList<>();
        List<KhuyenMai> lists = query.getResultList();
        for (KhuyenMai x : lists) {
            list.add(x.getMAKM());
        }
        return list;
    }

     public  boolean updateSPKM(int maKM, int maSP){
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            org.hibernate.query.Query query = session.createQuery("update SanPham set KHUYENMAI.MAKM = :MAKM Where MASP = :MASP and TRANGTHAI=0");
            query.setParameter("MAKM", maKM);
            query.setParameter("MASP", maSP);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {

        }
        return false;
    }
     
     public List<SanPham> getAll2() {
        String fromTable = "FROM SanPham where TRANGTHAI=0 and MAKM =10";
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(fromTable, SanPham.class);
        List<SanPham> lists = query.getResultList();
        return lists;
    }
    
    public List<SanPham> getAll3() {
        String fromTable = "FROM SanPham where TRANGTHAI=0 and MAKM !=10";
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(fromTable, SanPham.class);
        List<SanPham> lists = query.getResultList();
        return lists;
    }
 
    public  boolean soOut(int maSP){
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            org.hibernate.query.Query query = session.createQuery("update SanPham set KHUYENMAI.MAKM = 10 Where MASP = :MASP");
            query.setParameter("MASP", maSP);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {

        }
        return false;
    }
    
     public  boolean soOut2(){
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            org.hibernate.query.Query query = session.createQuery("update SanPham set KHUYENMAI.MAKM = 10");
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {

        }
        return false;
    }
}
