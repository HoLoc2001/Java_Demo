/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HoTanLoc_1900442_TH4;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class NhanVien {
    private String maNV;
    private String hoTen;
    private float luong;
    private String maPB;
    Scanner scan = new Scanner(System.in);

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public float getLuong() {
        return luong;
    }

    public void setLuong(float luong) {
        this.luong = luong;
    }

    public String getMaPB() {
        return maPB;
    }

    public void setMaPB(String maPB) {
        this.maPB = maPB;
    }
    
    public void nhapThongTin(String manv, String tennv, float luong, String mapb) {
        try {
            PreparedStatement pre = MysqlConn.CRUD("insert into nhanvien(manv, tennv, luong, mapb) values(?, ?, ?, ?)", manv, tennv, luong, mapb);
            int roweff = pre.executeUpdate();
            if(roweff >= 1)
                System.out.println("Thêm nhân viên thành công!");
            else
                System.out.println("Thêm nhân viên thất bại!");
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void xuatThongTin() {
        try {
            ResultSet rs = MysqlConn.select("select * from nhanvien");
            System.out.println("manv  |    tennv     | luong  | phong ban");
            while(rs.next()){  
                System.out.println(rs.getString(1)+" | "+rs.getString(2)+" | "+rs.getFloat(3) + " | "+ rs.getString(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void xuatNhanvienMaxLuong() {
        try {
            ResultSet rs = MysqlConn.select("select tennv from nhanvien where luong = (select max(luong) from nhanvien)");
            System.out.println("tennv co luong cao nhat");
            while(rs.next()){  
                System.out.println(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
