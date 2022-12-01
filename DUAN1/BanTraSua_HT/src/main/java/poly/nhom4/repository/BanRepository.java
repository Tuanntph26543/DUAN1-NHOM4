/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.repository;

import javax.persistence.Query;
import org.hibernate.Session;
import poly.nhom4.domainmodel.Ban;
import poly.nhom4.domainmodel.HoaDon;
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
}
