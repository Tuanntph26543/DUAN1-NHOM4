/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.hibernateconfig;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import poly.nhom4.domainmodel.Ban;
import poly.nhom4.domainmodel.ChucVu;
import poly.nhom4.domainmodel.HoaDon;
import poly.nhom4.domainmodel.HoaDonChiTiet;
import poly.nhom4.domainmodel.KhachHang;
import poly.nhom4.domainmodel.KhuyenMai;
import poly.nhom4.domainmodel.SanPham;
import poly.nhom4.domainmodel.USERTT;

/**
 *
 * @author hangnt
 */
public class HibernateUtil {

    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=QLBANTS");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "123456");
        properties.put(Environment.SHOW_SQL, "true");

        conf.setProperties(properties);
        conf.addAnnotatedClass(SanPham.class);
        conf.addAnnotatedClass(KhuyenMai.class);
        conf.addAnnotatedClass(HoaDon.class);
        conf.addAnnotatedClass(Ban.class);
        conf.addAnnotatedClass(HoaDonChiTiet.class);

        conf.addAnnotatedClass(USERTT.class);
        conf.addAnnotatedClass(KhachHang.class);
        conf.addAnnotatedClass(ChucVu.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void main(String[] args) {
        getFACTORY();
    }
}
