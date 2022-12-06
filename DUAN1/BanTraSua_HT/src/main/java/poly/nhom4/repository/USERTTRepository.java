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
import poly.nhom4.domainmodel.USERTT;
import poly.nhom4.hibernateconfig.HibernateUtil;
//import poly.nhom4.hibernateconfig.HirbernateUtil;

/**
 *
 * @author ACER
 */
public class USERTTRepository {

    private Session session = HibernateUtil.getFACTORY().openSession();
    private String fromTable = "From USERTT";

    public USERTT getUSerByMaNV(int maNV) {
        Session x = HibernateUtil.getFACTORY().openSession();
        Query query = x.createQuery("From USERTT Where MANV =: MANV");// truy vấn trên entity(HQL)
        query.setParameter("MANV", maNV);
        USERTT nv = (USERTT) query.getSingleResult();
        return nv;
    }

    public List<USERTT> getAll() {
        javax.persistence.Query query = session.createQuery(fromTable, USERTT.class);
        List<USERTT> lst = query.getResultList();
        return lst;
    }

    public USERTT dangNhap(String taikhoan) {
        String sql = fromTable + " where taikhoan =: taikhoan ";
        javax.persistence.Query query = session.createQuery(fromTable, USERTT.class);
        query.setParameter("TaiKhoan", taikhoan);
//        query.setParameter("MATKHAU", matKhauNhap);
        //query.setParameter("1", id);
        USERTT user = (USERTT) query.getSingleResult();
        return user;
    }

    public Boolean add(USERTT userTT) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(userTT);
            transaction.commit();
            return true;
        } catch (Exception e) {
//            e.printStackTrace(System.out);
            return null;
        }
    }

    public Boolean update(USERTT userTT) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.update(userTT);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean delete(USERTT userTT) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(userTT);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
}
