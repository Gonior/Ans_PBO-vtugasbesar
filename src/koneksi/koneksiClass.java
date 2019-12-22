package koneksi;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class koneksiClass {
    
    private static final String DATABASE_URL = "jdbc:mysql://localhost/dbTugasBesar?useTimezone=true&serverTimezone=UTC";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";
    //query mysql bro
    private static final String INSERT_QUERY = "INSERT INTO user(id_user, nama_user, username_user,password_user) VALUES (?, ?, ?,?)";
    private static final String CARI_QUERY = "SELECT * FROM user WHERE username_user= ?";
    private static final String LOGIN_QUERY = "SELECT * FROM user WHERE username_user= ? AND password_user = ?";

    public void insertRecord(int id, String nama, String username, String password) throws SQLException {

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, nama);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            
            printSQLException(e);
        }
    }
    public boolean cariRecord(String username) throws SQLException {
        boolean hasil = false;
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(CARI_QUERY)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            hasil = resultSet.next();
        } catch (SQLException e) {
            
            printSQLException(e);
        }
        return hasil;
        
    }
    public boolean login(String username, String password) throws SQLException {
        boolean hasil = false;
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_QUERY)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            hasil = resultSet.next();
        } catch (SQLException e) {
            
            printSQLException(e);
        }
        return hasil;
        
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}