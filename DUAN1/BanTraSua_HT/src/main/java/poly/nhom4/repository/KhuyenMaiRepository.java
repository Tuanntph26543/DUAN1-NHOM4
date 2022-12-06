/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.repository;

import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import poly.nhom4.domainmodel.KhuyenMai;
import poly.nhom4.hibernateconfig.HibernateUtil;

/**
 *
 * @author Admin
 */
public class KhuyenMaiRepository {

    Session session;
    private String fromTable = "from KhuyenMai";

    public List<KhuyenMai> getAll() {
        try {
            session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery(fromTable, KhuyenMai.class);
            List<KhuyenMai> list = query.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public KhuyenMai getOne(String tenKM) {
        String sql = fromTable + "WHERE TENKM = :TENKM";
        Query query = session.createQuery(sql, KhuyenMai.class);
        query.setParameter("TENKM", tenKM);
        KhuyenMai khuyenMai = (KhuyenMai) query.getSingleResult();
        return khuyenMai;

    }

    public List<KhuyenMai> getAllByTT() {
        String sql = "from KhuyenMai WHERE TRANGTHAI=1";
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(sql, KhuyenMai.class);
        List<KhuyenMai> lists = query.getResultList();
        return lists;
    }
    
    public List<KhuyenMai> getAllByTT2() {
        String sql = "from KhuyenMai WHERE TRANGTHAI=0";
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(sql, KhuyenMai.class);
        List<KhuyenMai> lists = query.getResultList();
        return lists;
    }

    public boolean updateKM(int maKM, int trangThai) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            org.hibernate.query.Query query = session.createQuery("update KhuyenMai set TRANGTHAI = 0"
                    + " where MAKM = :MAKM");
            query.setParameter("MAKM", maKM);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {

        }
        return false;
    }
    public Boolean add(KhuyenMai km) {
        Transaction transaction = null;
        try {
            session = HibernateUtil.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.save(km);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean update(KhuyenMai km) {
        try {
            session = HibernateUtil.getFACTORY().openSession();
            session.getTransaction().begin();
            session.saveOrUpdate(km);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public Boolean delete(KhuyenMai km) {
        Transaction transaction = null;
        try {
            session = HibernateUtil.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.delete(km);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static void main(String[] args) {
    }
}
