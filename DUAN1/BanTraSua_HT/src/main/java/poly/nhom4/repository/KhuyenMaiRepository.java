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
       String sql = "from KhuyenMai WHERE MAKM !=11";
        session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(sql, KhuyenMai.class);
        List<KhuyenMai> list = query.getResultList();
        return list;
    }
    
    public KhuyenMai getOne(String tenKM) {
        String sql = fromTable + "WHERE TENKM = :TENKM";
        Query query = session.createQuery(sql, KhuyenMai.class);
        query.setParameter("TENKM", tenKM);
        KhuyenMai khuyenMai = (KhuyenMai) query.getSingleResult();
        return khuyenMai;

    }

    public KhuyenMai getKMByMa(Integer maKM) {
        String sql = "FROM KhuyenMai WHERE MAKM = :MAKM";
        Query query = session.createQuery(sql, KhuyenMai.class);
        query.setParameter("MAKM", maKM);
        KhuyenMai khuyenMai = (KhuyenMai) query.getSingleResult();
        return khuyenMai;

    }

    public int getMa(BigDecimal soTienKM) {
        String sql = "FROM KhuyenMai WHERE SOTIENKM = :SOTIENKM";
        Query query = session.createQuery(sql, KhuyenMai.class);
        query.setParameter("SOTIENKM", soTienKM);
        KhuyenMai khuyenMai = (KhuyenMai) query.getSingleResult();
        return khuyenMai.getMAKM();
    }

    public List<KhuyenMai> getAllByTT() {
        String sql = "from KhuyenMai WHERE TRANGTHAI=1 and MAKM !=11";
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

    public List<Integer> getMaAll() {
        String fromTable1 = "FROM KhuyenMai ";
        Session session1 = HibernateUtil.getFACTORY().openSession();
        Query query = session1.createQuery(fromTable1, KhuyenMai.class);
        List<Integer> list = new ArrayList<>();
        List<KhuyenMai> lists = query.getResultList();
        for (KhuyenMai x : lists) {
            list.add(x.getMAKM());
        }
        return list;
    }

    public BigDecimal getsoTienKM(int maKM) {
        String fromTable2 = "FROM KhuyenMai Where MAKM =:MAKM";
        Session session2 = HibernateUtil.getFACTORY().openSession();
        Query query = session2.createQuery(fromTable2, KhuyenMai.class);
        query.setParameter("MAKM", maKM);
        KhuyenMai lists = (KhuyenMai) query.getSingleResult();
        return lists.getSOTIENKM();
    }
public String getTenKM(int maKM) {
        String fromTable2 = "FROM KhuyenMai Where MAKM =:MAKM";
        Session session2 = HibernateUtil.getFACTORY().openSession();
        Query query = session2.createQuery(fromTable2, KhuyenMai.class);
        query.setParameter("MAKM", maKM);
        KhuyenMai lists = (KhuyenMai) query.getSingleResult();
        return lists.getTENKM();
    }

    public static void main(String[] args) {
        System.out.println(new KhuyenMaiRepository().getsoTienKM(1));
    }

 
}
