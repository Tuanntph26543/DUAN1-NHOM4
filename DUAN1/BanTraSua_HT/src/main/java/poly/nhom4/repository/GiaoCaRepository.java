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
import poly.nhom4.domainmodel.GiaoCa;
import poly.nhom4.domainmodel.HoaDon;
import poly.nhom4.hibernateconfig.HibernateUtil;

/**
 *
 * @author ACER
 */
public class GiaoCaRepository {
    
    public List<GiaoCa> getGiaoCaMoiNhat() {
        String fromTable = "FROM GiaoCa order by THOIGIANNHANCA DESC";
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(fromTable, GiaoCa.class);
        List<GiaoCa> list = query.getResultList();
        return list;
    }
    
    public boolean createGC(GiaoCa gc) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(gc);
            transaction.commit();
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    
    public boolean updateGC(GiaoCa gc) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            org.hibernate.query.Query query = session.createQuery("update GiaoCa"
                    + " set THOIGIANGIAOCA = :THOIGIANGIAOCA,TONGTIENTRONGCA=:TONGTIENTRONGCA"
                    + " where MACA = :MACA");
            query.setParameter("THOIGIANGIAOCA", gc.getTHOIGIANGIAOCA());
            query.setParameter("TONGTIENTRONGCA", gc.getTONGTIENTRONGCA());
            query.setParameter("MACA", gc.getMACA());
            query.executeUpdate();
            transaction.commit();
            return true;
        }
    }
    
    public boolean updateGC1(GiaoCa gc) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            org.hibernate.query.Query query = session.createQuery("update GiaoCa"
                    + " set MANHANVIENNHANCA = :MANHANVIENNHANCA"
                    + " where MACA = :MACA");
            query.setParameter("MANHANVIENNHANCA", gc.getUsertt1().getMANV());
            query.setParameter("MACA", gc.getMACA());
            query.executeUpdate();
            transaction.commit();
            return true;
        }
    }
    
    public boolean updateGC2(GiaoCa gc) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            org.hibernate.query.Query query = session.createQuery("update GiaoCa"
                    + " set MANHANVIENTRONGCA = :MANHANVIENTRONGCA"
                    + " where MACA = :MACA");
            query.setParameter("MANHANVIENTRONGCA", gc.getUsertt().getMANV());
            query.setParameter("MACA", gc.getMACA());
            query.executeUpdate();
            transaction.commit();
            return true;
        }
    }
    
    public boolean updateGC3(GiaoCa gc) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            org.hibernate.query.Query query = session.createQuery("update GiaoCa"
                    + " set THOIGIANRESET = :THOIGIANRESET,MAQUANLIRESET=2,TONGTIENRUT=:TONGTIENRUT,TONGTIENTRONGCA=:TONGTIENTRONGCA"
                    + " where MACA = :MACA");
            query.setParameter("THOIGIANRESET", gc.getTHOIGIANRESET());
            query.setParameter("TONGTIENRUT", gc.getTONGTIENRUT());
            query.setParameter("TONGTIENTRONGCA", new BigDecimal(1000000));
            query.setParameter("MACA", gc.getMACA());
            query.executeUpdate();
            transaction.commit();
            return true;
        }
    }
    
    public static void main(String[] args) {
        List<GiaoCa> list = new GiaoCaRepository().getGiaoCaMoiNhat();
        for (GiaoCa x : list) {
            System.out.println(x);
        }
    }
}
