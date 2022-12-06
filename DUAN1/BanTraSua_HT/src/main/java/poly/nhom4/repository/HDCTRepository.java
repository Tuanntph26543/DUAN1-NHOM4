/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.repository;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import poly.nhom4.domainmodel.HoaDonChiTiet;
import poly.nhom4.hibernateconfig.HibernateUtil;

/**
 *
 * @author ACER
 */
public class HDCTRepository {

    public boolean createHDCT(HoaDonChiTiet hdct) {

        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(hdct);
            transaction.commit();
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public List<HoaDonChiTiet> getHDCTByMaHD(int maHD) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery("From HoaDonChiTiet Where MAHD =: MAHD and masp >0");// truy vấn trên entity(HQL)
        query.setParameter("MAHD", maHD);
        List<HoaDonChiTiet> list = query.getResultList();
        return list;
    }

    public List<Integer> getBanByMaHD(int maHD) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(" From HoaDonChiTiet Where MAHD =: MAHD AND MABAN IS NOT NULL ");// truy vấn trên entity(HQL)
        query.setParameter("MAHD", maHD);
        List<Integer> list1 = new ArrayList<>();
        List<HoaDonChiTiet> list = query.getResultList();
        for (HoaDonChiTiet x : list) {
            int ban = x.getBan().getMaBan();
            list1.add(ban);
        }
        return list1;
    }

    public boolean updateHDCT(int maHD, int maSP, int soLuong) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update HoaDonChiTiet set SoLuong = :SoLuong"
                    + " where MAHD = :MAHD AND MASP = :MASP");
            query.setParameter("SoLuong", soLuong);
            query.setParameter("MAHD", maHD);
            query.setParameter("MASP", maSP);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

     public boolean updateHDCT1(int maHD, int maKH) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update HoaDonChiTiet set MaKH = :MaKH"
                    + " where MAHD = :MAHD ");
            query.setParameter("MAHD", maHD);
            query.setParameter("MaKH", maKH);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {

        }
        return false;
    }
    public boolean deleteAll(int maHD) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE HoaDonChiTiet Where MAHD=:MAHD");
            query.setParameter("MAHD", maHD);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public boolean deleteHDBySPVe0(int maHD, int maSP) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("Delete HoaDonChiTiet where MAHD = :MAHD AND MASP = :MASP");
            query.setParameter("MAHD", maHD);
            query.setParameter("MASP", maSP);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {

        }
        return false;
    }
}
