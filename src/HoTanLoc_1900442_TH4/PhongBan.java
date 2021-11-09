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
public class PhongBan {
    private String maPhong;
    private String tenPhong;
    Scanner scan = new Scanner(System.in);
    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }
    public void nhapThongTin(String mP, String tP){
        try {
            setMaPhong(mP);
            setTenPhong(tP);
            PreparedStatement pre = MysqlConn.CRUD("insert into phongban(mapb, tenpb) values (?, ?)");
            pre.setString(1, getMaPhong());
            pre.setString(2, getTenPhong());
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
            ResultSet rs = MysqlConn.select("select * from phongban");
            System.out.println("mpb | ten pb");
            while(rs.next()){  
                System.out.println(rs.getString(1)+" | "+rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhongBan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void xuatNhanvien() {
        try {
            ResultSet rs = MysqlConn.select("select pb.tenpb, nv.tennv from phongban pb left join nhanvien nv on pb.mapb = nv.mapb");
            System.out.println("tenpb | ten nv");
            while(rs.next()){  
                System.out.println(rs.getString(1)+" | "+rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhongBan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void xuatNhanvienCoLuongNhat() {
        try {
            ResultSet rs = MysqlConn.select("select pb.tenpb, max(nv.luong) from phongban pb left join nhanvien nv on pb.mapb = nv.mapb group by pb.mapb");
            System.out.println("Nhan vien cao nhat trong tung phong ban");
            while(rs.next()){  
                System.out.println(rs.getString(1)+" | "+rs.getFloat(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhongBan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
