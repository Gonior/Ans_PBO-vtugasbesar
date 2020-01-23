/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubes.edu.event;

import com.tubes.edu.interfaceAll.TubesInterface;
import com.tubes.edu.model.Anime;
import com.tubes.edu.model.Link;
import com.tubes.edu.model.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TubesEvent implements TubesInterface{
    
    private Connection conn;
    private Anime anime = new Anime();
    private Link link = new Link();
    private User user = new User();   
    private int jumlahAnime = 0;
    
    public TubesEvent(Connection conn) {
        this.conn = conn;
    }

    public int getJumlahAnime() {
        return jumlahAnime;
    }
    
    
    
    @Override
    public void changeStage(Stage primaryStage, String source) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/com/tubes/edu/view/"+source+".fxml"));
        Scene scene = new Scene(fxml);
        primaryStage.setTitle("Ans");
        primaryStage.setScene(scene);
        primaryStage.resizableProperty().setValue(false);
        primaryStage.show();
    }

    @Override
    public void insertUSer(User user) throws SQLException {
        final String query = "INSERT INTO user(id_user, nama_user, username_user,password_user) VALUES (?, ?, ?,?)";
        PreparedStatement st;
        try {
            st = conn.prepareStatement(query);
            st.setInt(1, user.getId());
            st.setString(2, user.getNama());
            st.setString(3, user.getUsername());
            st.setString(4, user.getPassword());
            st.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public boolean cariUser(String username) throws SQLException {
        boolean hasil = false;
        final String query ="SELECT * FROM user WHERE username_user= ?";
        PreparedStatement st;
        try {
            st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet resultSet = st.executeQuery();
            hasil = resultSet.next();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return hasil;
    }

    @Override
    public boolean login(User user) throws SQLException {
        boolean hasil = false;
        final String query ="SELECT * FROM user WHERE username_user= ? AND password_user = ?";
        PreparedStatement st;
        try {
            st = conn.prepareStatement(query);
            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            ResultSet resultSet = st.executeQuery();
            while(resultSet.next())  {
                this.user.setNama(resultSet.getString("nama_user"));
                this.user.setUsername(resultSet.getString("username_user"));
                this.user.setPassword(resultSet.getString("password_user"));
                this.user.setId(resultSet.getInt("id_user"));
                hasil = true;
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return hasil;
        
    }

    @Override
    public void cariAnime(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cariAnime(String judulAnime) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Link> getAllLink() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cariLink(Anime anime) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Anime> getAllAnime() throws SQLException {
        Statement st = null;
        List<Anime> list = new ArrayList<>();
        final String query = "SELECT * FROM anime";
        
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Anime anim = null;
            while (rs.next()) {
                anim = new Anime();
                anim.setId(rs.getInt("id_anime"));
                anim.setJudul(rs.getString("judul_anime"));
                anim.setJumlahEpisode(rs.getInt("jumlah_episode"));                
                anim.setRating(rs.getDouble("rating_anime"));
                anim.setGambar(rs.getString("gambar_anime"));
                anim.setSinopsis(rs.getString("sinopsis_anime"));
                anim.setDurasi(rs.getString("durasi_anime"));
                anim.setStatus(rs.getString("status"));
                anim.setGenre(rs.getString("genre_anime"));
                list.add(anim);
                jumlahAnime++;
            }
        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            if(st != null) {
                try {
                    st.close();
                } catch (SQLException exception) {
                }
            }
        }
        return list;
    }

    @Override
    public User getUSer() {
        return this.user;
    }
        
    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
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
