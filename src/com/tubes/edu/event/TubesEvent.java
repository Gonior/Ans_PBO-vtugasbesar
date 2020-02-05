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

public class TubesEvent implements TubesInterface {

    private Connection conn;
    private Anime anime = new Anime();
    private Link link = new Link();
    private User user = new User();

    public TubesEvent(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void changeStage(Stage primaryStage, String source) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/com/tubes/edu/view/" + source + ".fxml"));
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
        final String query = "SELECT * FROM user WHERE username_user= ?";
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
        final String query = "SELECT * FROM user WHERE username_user= ? AND password_user = ?";
        PreparedStatement st;
        try {
            st = conn.prepareStatement(query);
            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
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
                anim.setDurasi(rs.getInt("durasi_anime"));
                anim.setStatus(rs.getString("status"));
                anim.setGenre(rs.getString("genre_anime"));
                list.add(anim);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            if (st != null) {
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

    @Override
    public Anime getAnime() {
        return this.anime;
    }

    @Override
    public boolean cariAnime(String judulAnime) throws SQLException {
        boolean hasil = false;
        final String query = "SELECT * FROM anime WHERE judul_anime= ?";
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(query);
            st.setString(1, judulAnime);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                this.anime.setId(rs.getInt("id_anime"));
                this.anime.setJudul(rs.getString("judul_anime"));
                this.anime.setJumlahEpisode(rs.getInt("jumlah_episode"));
                this.anime.setRating(rs.getDouble("rating_anime"));
                this.anime.setGambar(rs.getString("gambar_anime"));
                this.anime.setSinopsis(rs.getString("sinopsis_anime"));
                this.anime.setDurasi(rs.getInt("durasi_anime"));
                this.anime.setStatus(rs.getString("status"));
                this.anime.setGenre(rs.getString("genre_anime"));
                hasil = true;
            }
        } catch (SQLException e) {
        }

        return hasil;
    }

    @Override
    public boolean cariAnime(int idAnime) throws SQLException {
        boolean hasil = false;
        final String query = "SELECT * FROM anime WHERE id_anime= ?";
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(query);
            st.setInt(1, idAnime);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                this.anime.setId(rs.getInt("id_anime"));
                this.anime.setJudul(rs.getString("judul_anime"));
                this.anime.setJumlahEpisode(rs.getInt("jumlah_episode"));
                this.anime.setRating(rs.getDouble("rating_anime"));
                this.anime.setGambar(rs.getString("gambar_anime"));
                this.anime.setSinopsis(rs.getString("sinopsis_anime"));
                this.anime.setDurasi(rs.getInt("durasi_anime"));
                this.anime.setStatus(rs.getString("status"));
                this.anime.setGenre(rs.getString("genre_anime"));
                hasil = true;
            }
        } catch (SQLException e) {
        }

        return hasil;
    }

    @Override
    public boolean cariLink(Anime anime, int episode) throws SQLException {
        boolean hasil = false;
        PreparedStatement st = null;
        final String query = "SELECT * FROM streaming WHERE id_anime= ? AND episode_streaming = ?";
        try {
            st = conn.prepareStatement(query);
            st.setInt(1, anime.getId());
            st.setInt(2, episode);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                this.link.setEpisode(rs.getInt("episode_streaming"));
                this.link.setIdAnime(rs.getInt("id_anime"));
                this.link.setIdStreaming(rs.getInt("id_streaming"));
                this.link.setUrlStreaming(rs.getString("url_streaming"));
                this.link.setNext(cekNext(this.link));
                this.link.setPrev(cekPrev(this.link));
                hasil = true;
            }
        } catch (SQLException e) {
        }
        return hasil;
    }

    @Override
    public List<Link> getAllLink(Anime anime) throws SQLException {
        PreparedStatement st = null;
        List<Link> list = new ArrayList<>();
        final String query = "SELECT * FROM streaming WHERE id_anime = ? ORDER BY episode_streaming";
        try {
            st = conn.prepareStatement(query);
            st.setInt(1, anime.getId());
            ResultSet rs = st.executeQuery();
            Link link = null;
            while (rs.next()) {
                link = new Link();
                link.setEpisode(rs.getInt("episode_streaming"));
                link.setIdAnime(rs.getInt("id_anime"));
                link.setIdStreaming(rs.getInt("id_streaming"));
                link.setUrlStreaming(rs.getString("url_streaming"));
                link.setNext(cekNext(link));
                link.setPrev(cekPrev(link));
                list.add(link);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException exception) {
                }
            }
        }
        return list;
    }

    protected boolean cekNext(Link link) throws SQLException {
        boolean hasil = false;
        PreparedStatement st = null;
        final String query = "SELECT * FROM streaming WHERE id_anime= ? AND episode_streaming = ?";
        int episode = link.getEpisode() + 1;
        try {
            st = conn.prepareStatement(query);
            st.setInt(1, link.getIdAnime());
            st.setInt(2, episode);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                hasil = true;
            }
        } catch (SQLException e) {
        }
        return hasil;
    }

    protected boolean cekPrev(Link link) throws SQLException {
        boolean hasil = false;
        PreparedStatement st = null;
        final String query = "SELECT * FROM streaming WHERE id_anime= ? AND episode_streaming = ?";
        int episode = link.getEpisode() - 1;
        try {
            st = conn.prepareStatement(query);
            st.setInt(1, link.getIdAnime());
            st.setInt(2, episode);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                hasil = true;
            }
        } catch (SQLException e) {
        }
        return hasil;
    }

    @Override
    public Link getLink() {
        return link;
    }

    @Override
    public void insertAnime(Anime anime) throws SQLException {
        final String query = "INSERT INTO anime(id_anime, judul_anime, jumlah_episode, rating_anime, gambar_anime, sinopsis_anime, durasi_anime, status, genre_anime) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(query);
            st.setInt(1, anime.getId());
            st.setString(2, anime.getJudul());
            st.setInt(3, anime.getJumlahEpisode());
            st.setDouble(4, anime.getRating());
            st.setString(5, anime.getGambar());
            st.setString(6, anime.getSinopsis());
            st.setInt(7, anime.getDurasi());
            st.setString(8, anime.getStatus());
            st.setString(9, anime.getGenre());
            st.executeUpdate();
        } catch (SQLException e) {
        }

    }

    @Override
    public void insertLink(Link link) throws SQLException {
        final String query = "INSERT INTO streaming(id_streaming, id_anime, episode_streaming, url_streaming) VALUES (?,?,?,?)";
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(query);
            st.setInt(1, link.getIdStreaming());
            st.setInt(2, link.getIdAnime());
            st.setInt(3, link.getEpisode());
            st.setString(4, link.getUrlStreaming());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public void updateAnime(Anime anime) throws SQLException {
        final String query = "UPDATE anime SET judul_anime = ?, jumlah_episode = ?, rating_anime = ?, gambar_anime = ?, sinopsis_anime = ?, durasi_anime = ?, status = ?, genre_anime = ? WHERE id_anime=?";
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(query);
            st.setString(1, anime.getJudul());
            st.setInt(2, anime.getJumlahEpisode());
            st.setDouble(3, anime.getRating());
            st.setString(4, anime.getGambar());
            st.setString(5, anime.getSinopsis());
            st.setInt(6, anime.getDurasi());
            st.setString(7, anime.getStatus());
            st.setString(8, anime.getGenre());
            st.setInt(9, anime.getId());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public void updateLink(Link link) throws SQLException {
        final String query = "UPDATE anime SET url_streaming WHERE id_streaming = ?";
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(query);
            st.setString(1, link.getUrlStreaming());
            st.setInt(2, link.getIdStreaming());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public void deleteAnime(Anime anime) throws SQLException {
        final String query = "DELETE FROM streaming WHERE id_anime = ?";
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(query);
            st.setInt(1, anime.getId());
            st.executeUpdate();
        } catch (SQLException e) {
        }
        final String query2 = "DELETE FROM anime WHERE id_anime = ?";
        PreparedStatement st2 = null;
        try {
            st2 = conn.prepareStatement(query2);
            st2.setInt(1, anime.getId());
            st2.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
