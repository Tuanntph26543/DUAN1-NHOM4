/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import poly.nhom4.domainmodel.KhuyenMai;
import poly.nhom4.reponse.KhuyenMaiReponse;
import poly.nhom4.repository.KhuyenMaiRepository;
import poly.nhom4.service.KhuyenMaiService;

/**
 *
 * @author Admin
 */
public class KhuyenMaiServiceImpl implements KhuyenMaiService {

    private KhuyenMaiRepository repo = new KhuyenMaiRepository();

    @Override
    public List<KhuyenMaiReponse> getAll() {
        List<KhuyenMai> lists = repo.getAll();
        List<KhuyenMaiReponse> reponses = new ArrayList<>();
        for (KhuyenMai km : lists) {
            KhuyenMaiReponse reponse = new KhuyenMaiReponse(km);
            reponses.add(reponse);
        }
        return reponses;
    }

    @Override
    public KhuyenMaiReponse KMGetOne(String tenKM) {
        KhuyenMai k = new KhuyenMai();
        k.setTENKM(tenKM);
        repo.getOne(tenKM);
        return null;

    }

    @Override
    public String add(KhuyenMaiReponse km) {
        try {
            Date ngayBD = Date.valueOf(km.getNgayBD());
//        Date ngayKT = Date.valueOf(km.getNgayKT());
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            repo.add(new KhuyenMai(km.getTenKM(), km.getSoTienKM(), ngayBD, new Timestamp(sdf2.parse(km.getNgayKT()).getTime()), km.getTrangThai()));

            if (km != null) {
                return "Thành công";
            } else {
                return "Thất bại";
            }
        } catch (ParseException ex) {
            Logger.getLogger(KhuyenMaiServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String update(KhuyenMaiReponse km) {
        try {
            Date ngayBD = Date.valueOf(km.getNgayBD());
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            repo.update(new KhuyenMai(km.getMaKM(), km.getTenKM(), km.getSoTienKM(), ngayBD, new Timestamp(sdf2.parse(km.getNgayKT()).getTime()), km.getTrangThai()));
            if (km != null) {
                return "Thành công";
            } else {
                return "Thất bại";
            }
        } catch (ParseException ex) {
            Logger.getLogger(KhuyenMaiServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String delete(KhuyenMaiReponse km) {
        KhuyenMai k = new KhuyenMai();
        k.setMAKM(km.getMaKM());
        if (repo.delete(k)) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

    @Override
    public List<KhuyenMaiReponse> getAllByTT() {
        List<KhuyenMai> lists = repo.getAllByTT();
        List<KhuyenMaiReponse> reponses = new ArrayList<>();
        for (KhuyenMai km : lists) {
            KhuyenMaiReponse reponse = new KhuyenMaiReponse(km);
            reponses.add(reponse);
        }
        return reponses;
    }

    @Override
    public List<KhuyenMaiReponse> getAllByTT2() {
        List<KhuyenMai> lists = repo.getAllByTT2();
        List<KhuyenMaiReponse> reponses = new ArrayList<>();
        for (KhuyenMai km : lists) {
            KhuyenMaiReponse reponse = new KhuyenMaiReponse(km);
            reponses.add(reponse);
        }
        return reponses;
    }

    @Override
    public boolean NgungAll(int maKM, int trangThai) {
        return repo.updateKM(maKM, trangThai);
    }

    @Override
    public List<Integer> getMaAll() {
        return repo.getMaAll();
    }

    @Override
    public BigDecimal getsoTienKM(int maKM) {
        return repo.getsoTienKM(maKM);
    }

    @Override
    public KhuyenMai getKMByMa(Integer maKM) {
        return repo.getKMByMa(maKM);
    }

    @Override
    public int getMa(BigDecimal soTienKM) {
        return repo.getMa(soTienKM);
    }

    @Override
    public String getTenKM(int maKM) {
        return repo.getTenKM(maKM) ;
    }

}
