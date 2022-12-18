package org.example;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
    public final static String DATABASE_URL = "jdbc:mysql://localhost:3306/flower_shop";
    public final static String DATABASE_USER = "root";
    private final static String DATABASE_PASSWORD = "root";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUrl(DATABASE_URL);
            dataSource.setUser(DATABASE_USER);
            dataSource.setPassword(DATABASE_PASSWORD);

            connection = dataSource.getConnection();
            return connection;
        }else{
            return connection;
        }
    }
}
