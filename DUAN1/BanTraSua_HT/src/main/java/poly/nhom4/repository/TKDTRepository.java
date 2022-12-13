/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import poly.nhom4.domainmodel.HoaDon;
import poly.nhom4.hibernateconfig.HibernateUtil;

/**
 *
 * @author Admin
 */
public class TKDTRepository {

    Session session;

    public List<Object[]> getAllDT() {
        session = HibernateUtil.getFACTORY().openSession();
        String sql = "select p.hoaDon.ngayTao, sum((p.DONGIA-p.sanPham.KHUYENMAI.SOTIENKM)*p.SOLUONG) as DOANHTHU\n"
                + "from HoaDonChiTiet p where p.hoaDon.tinhTrang != 1 and p.hoaDon.tinhTrang != 0 group by p.hoaDon.ngayTao Order by p.hoaDon.ngayTao desc";
        Query query = session.createQuery(sql);
        List<Object[]> list1 = query.list();
        return list1;
    }

    public List<BigDecimal> getAllNow() {
        session = HibernateUtil.getFACTORY().openSession();
        String sql = "select sum(p.DONGIA-p.sanPham.KHUYENMAI.SOTIENKM*p.SOLUONG) as DOANHTHU\n"
                + "from HoaDonChiTiet p where p.hoaDon.ngayTao = convert(date, GETDATE())"
                + " and p.hoaDon.tinhTrang != 1 and p.hoaDon.tinhTrang != 0 group by p.hoaDon.ngayTao";
        Query query = session.createQuery(sql);
        List<BigDecimal> list1 = query.list();
        return list1;
    }

    public List<Object[]> getAllDT2(Date ngayDau, Date ngayCuoi) {
        session = HibernateUtil.getFACTORY().openSession();
        String sql = "select p.hoaDon.ngayTao, sum((p.DONGIA-p.sanPham.KHUYENMAI.SOTIENKM)*p.SOLUONG) as DOANHTHU\n"
                + "from HoaDonChiTiet p where p.hoaDon.tinhTrang != 1 and p.hoaDon.tinhTrang != 0 and p.hoaDon.ngayTao>=:NGAYDAU and p.hoaDon.ngayTao<=:NGAYCUOI  group by p.hoaDon.ngayTao Order by p.hoaDon.ngayTao desc";
        Query query = session.createQuery(sql);
        query.setParameter("NGAYDAU", ngayDau);
        query.setParameter("NGAYCUOI", ngayCuoi);
        List<Object[]> list1 = query.list();
        return list1;
    }

    public List<BigDecimal> getYear() {
        session = HibernateUtil.getFACTORY().openSession();
        String sql = "select sum(p.DONGIA-p.sanPham.KHUYENMAI.SOTIENKM*p.SOLUONG) as DOANHTHU\n"
                + "from HoaDonChiTiet p where p.hoaDon.tinhTrang != 1 and p.hoaDon.tinhTrang != 0 group by YEAR(p.hoaDon.ngayTao)";
        Query query = session.createQuery(sql);
        List<BigDecimal> list1 = query.list();
        return list1;
    }

    public List<Long> getHoaDon() {
        session = HibernateUtil.getFACTORY().openSession();
        String sql = "select count(MAHD) as soLuong from HoaDon where ngayTao = convert(date, GETDATE())"
                + " and tinhTrang != 1 and tinhTrang != 0";
        Query query = session.createQuery(sql);
        List<Long> list1 = query.list();
        return list1;
    }

    public List<Object[]> getAllSP() {
        String fromTable = "SELECT p.sanPham.TENSP,Sum(p.SOLUONG),p.hoaDon.ngayTao, "
                + "Sum(p.SOLUONG*(p.sanPham.GIABAN-p.sanPham.KHUYENMAI.SOTIENKM))"
                + " From HoaDonChiTiet p where p.hoaDon.tinhTrang != 1 and p.hoaDon.tinhTrang != 0 Group by p.sanPham.TENSP,p.hoaDon.ngayTao, "
                + "p.sanPham.GIABAN, p.sanPham.KHUYENMAI.SOTIENKM Order by p.hoaDon.ngayTao desc";
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(fromTable);
        List<Object[]> lists = query.list();
        return lists;
    }

    public List<String> getSeller() {
        String fromTable = "SELECT p.sanPham.TENSP From HoaDonChiTiet p Group by p.sanPham.TENSP Order by sum(p.SOLUONG) desc";
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(fromTable);
        List<String> lists = query.list();
        return lists;
    }

    public static void main(String[] args) {
        List<Object[]> list = new TKDTRepository().getAllDT();
        for (Object[] x : list) {
            System.out.println(x[0] + "" + x[1]);
        }
        List<BigDecimal> list2 = new TKDTRepository().getAllNow();
        for (BigDecimal x : list2) {
            System.out.println(x);
        }
        List<BigDecimal> list3 = new TKDTRepository().getYear();
        for (BigDecimal x : list3) {
            System.out.println(x);
        }

        List<Long> list1 = new TKDTRepository().getHoaDon();
        for (Long l : list1) {
            System.out.println(l);
        }
    }

}
