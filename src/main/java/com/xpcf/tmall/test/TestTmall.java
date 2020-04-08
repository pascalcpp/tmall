package com.xpcf.tmall.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-03-26 11:29
 **/
public class TestTmall {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try (Connection c = DriverManager
                .getConnection("jdbc:mysql://127.0.0.1:3306/tmall_springboot?Unicode=true&characterEncoding=utf8"
                        ,"root","123456");
             Statement s =  c.createStatement();
        ){
            for(int i=1;i<=100;++i){
                String sqlFormat = "insert into category values (null,'测试分类%d')";
                String sql = String.format(sqlFormat,i);
                s.execute(sql);
            }
            System.out.println("success");
        }
        catch (SQLException e){
                e.printStackTrace();
        }
    }
}
