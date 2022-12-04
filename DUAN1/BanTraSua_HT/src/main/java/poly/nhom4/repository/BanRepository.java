/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.repository;

import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import poly.nhom4.domainmodel.Ban;
import poly.nhom4.hibernateconfig.HibernateUtil;

/**
 *
 * @author ACER
 */
public class BanRepository {

    public Ban getBanByMa(int ma) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery("From Ban Where MABAN =: MABAN");// truy vấn trên entity(HQL)
        query.setParameter("MABAN", ma);
        Ban hd = (Ban) query.getSingleResult();
        return hd;
    }

    public boolean updateBan(int maBan) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update Ban set TrangThai = 1 WHERE MABAN =:MABAN");
            query.setParameter("MABAN", maBan);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {

        }
        return false;
    }
    public List<Ban> getBanConTrong() {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery("From Ban Where TRANGTHAI =: TRANGTHAI");// truy vấn trên entity(HQL)
        query.setParameter("TRANGTHAI", 0);
        List<Ban> list = query.getResultList();
        return list;
    }
     public List<Ban> getBanAll() {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery("From Ban");// truy vấn trên entity(HQL)
        List<Ban> list = query.getResultList();
        return list;
    }
}
