/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.repository;

import poly.nhom4.domainmodel.NhaCungCap;
import poly.nhom4.hibernateconfig.HibernateUtil;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author My PC
 */
public class NhaCungCapRepository {

    public List<NhaCungCap> getAll() {
        Session session = HibernateUtil.getFACTORY().openSession();
        String fromTable = "FROM NhaCungCap"; 
        Query query = session.createQuery(fromTable, NhaCungCap.class);
        List<NhaCungCap> lists = query.getResultList();
        return lists;
    }

    public Boolean add(NhaCungCap nhaCungCap) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(nhaCungCap);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean update(NhaCungCap nhaCungCap) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.update(nhaCungCap);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean delete(NhaCungCap nhaCungCap) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(nhaCungCap);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static void main(String[] args) {
        List<NhaCungCap> lists = new NhaCungCapRepository().getAll();
        for (NhaCungCap x : lists) {
            System.out.println(x.toString());
        }
    }
}
