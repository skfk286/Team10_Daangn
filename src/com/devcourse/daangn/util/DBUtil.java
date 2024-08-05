package com.devcourse.daangn.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static final String url = "jdbc:mysql://121.88.130.214:3306/daangn";
    public static final String user = "devcourse";
    public static final String password = "1234";

    static {
        try {
            // MySQL JDBC 드라이버를 로드 확인
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC 드라이버를 찾을 수 없습니다.", e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("데이터 베이스 커넥션 생성 오류", e);
        }
    }

    public static void close(AutoCloseable... closeables) {
        for (AutoCloseable closeable : closeables) {
            if (closeable != null) {
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
