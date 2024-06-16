package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Database instance;
    private Connection connection;

    public Database() {
        final String DATABASE_URL = "jdbc:postgresql://localhost:5432/kaanbooking";
        final String DATABASE_NAME = "postgres";
        final String DATABASE_PW = "database";
        try {
            this.connection = DriverManager.getConnection(DATABASE_URL,DATABASE_NAME, DATABASE_PW);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public static Connection getInstance() {
        try {
            if (instance==null || instance.getConnection().isClosed()) {
                instance = new Database();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return instance.getConnection();
    }

}
