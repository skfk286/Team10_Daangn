package com.devcourse.daangn.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static final String url = "jdbc:mysql://121.88.130.214:3306/daangn";
    public static final String user = "devcourse";
    public static final String password = "1234";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("커넥션 생성 오류");
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static void close(AutoCloseable... closeables) {
        for(AutoCloseable closeable : closeables) {
            if(closeable != null) {
                try {
                    closeable.close();
                } catch (Exception e) {
                    System.out.println("close Error");
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
