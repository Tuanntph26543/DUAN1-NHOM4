/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.domainmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ACER
 */
@Table
@Entity
public class ChucVu {
    
    @Id
    @Column(name = "MACV")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MACV;

    @Column(name = "TECV")
    private String TENCV;

    public ChucVu() {
    }

    public ChucVu(int MACV, String TENCV) {
        this.MACV = MACV;
        this.TENCV = TENCV;
    }
    
    

    public int getMACV() {
        return MACV;
    }

    public void setMACV(int MACV) {
        this.MACV = MACV;
    }

    public String getTENCV() {
        return TENCV;
    }

    public void setTENCV(String TENCV) {
        this.TENCV = TENCV;
    }

    
}
