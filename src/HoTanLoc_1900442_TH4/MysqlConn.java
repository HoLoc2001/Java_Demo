package HoTanLoc_1900442_TH4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asus
 */
public class MysqlConn {
    public static Connection conn(){
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlnv","root","hotanloc");  
            return  conn;
        }
        catch (ClassNotFoundException | SQLException ex) { System.out.println(ex);}  
        return null;
    }
    public static ResultSet select(Object... params){  
        try{  
            Connection conn = MysqlConn.conn();
            PreparedStatement pre = conn.prepareStatement(params[0].toString());
            for (int i = 1; i < params.length; i++) {
                pre.setObject(i, params[i]);
            }
            ResultSet rs = pre.executeQuery();
            return  rs;
        }
        catch (SQLException ex) { System.out.println(ex);}  
        return null;
    }
    public static PreparedStatement CRUD(Object... params){  
        try{  
            Connection conn = MysqlConn.conn();
            PreparedStatement pre = conn.prepareStatement(params[0].toString());
            for (int i = 1; i < params.length; i++) {
                pre.setObject(i, params[i]);
            }
            return  pre;
        }
        catch ( SQLException ex) { System.out.println(ex);}  
        return null;
    }  
}

