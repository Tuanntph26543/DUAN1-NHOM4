/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.repository;

import poly.nhom4.domainmodel.NguyenLieu;
import poly.nhom4.hibernateconfig.HibernateUtil;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author My PC
 */
public class NguyenLieuRepository {

    public List<NguyenLieu> getAll() {

        Session session = HibernateUtil.getFACTORY().openSession();

        String fromTable = "FROM NguyenLieu"; // HQL
        Query query = session.createQuery(fromTable, NguyenLieu.class);
        List<NguyenLieu> lists = query.getResultList();
        return lists;
    }

    public Boolean add(NguyenLieu nguyenLieu) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(nguyenLieu);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean update(NguyenLieu nguyenLieu) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.update(nguyenLieu);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean delete(NguyenLieu nguyenLieu) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(nguyenLieu);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static void main(String[] args) {
        List<NguyenLieu> lists = new NguyenLieuRepository().getAll();
        for (NguyenLieu x : lists) {
            System.out.println(x.toString());
        }
    }

}
