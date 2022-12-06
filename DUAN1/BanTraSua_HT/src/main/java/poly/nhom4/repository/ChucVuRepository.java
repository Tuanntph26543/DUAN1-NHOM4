/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.repository;

import poly.nhom4.domainmodel.ChucVu;
import poly.nhom4.hibernateconfig.HibernateUtil;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author FPT Shop
 */
public class ChucVuRepository {

    private Session session = HibernateUtil.getFACTORY().openSession();
    private String fromTable = "From ChucVu";

    public List<ChucVu> getAll() {
        Query query = session.createQuery(fromTable, ChucVu.class);
        List<ChucVu> lst = query.getResultList();
        return lst;
    }
}
