package com.tubes.edu.connection;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TubesDB {

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            MysqlDataSource datasource = new MysqlDataSource();
            datasource.setURL("jdbc:mysql://localhost/dbtugasbesar?useTimezone=true&serverTimezone=UTC");
            datasource.setUser("root");
            datasource.setPassword("");
            connection = datasource.getConnection();
        }
        return connection;
    }

}
