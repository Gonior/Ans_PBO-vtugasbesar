package koneksi;

import controller.LinkViewController;
import controller.PlayerViewController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class koneksiClass {

    private static final String DATABASE_URL = "jdbc:mysql://localhost/dbTugasBesar?useTimezone=true&serverTimezone=UTC";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";
    private ArrayList<String> animeData = new ArrayList<>(10);

    public ArrayList<String> getAnimeData() {
        return animeData;
    }
    
    //query mysql bro
    private static final String INSERT_QUERY = "INSERT INTO user(id_user, nama_user, username_user,password_user) VALUES (?, ?, ?,?)";
    private static final String CARI_QUERY = "SELECT * FROM user WHERE username_user= ?";
    private static final String LOGIN_QUERY = "SELECT * FROM user WHERE username_user= ? AND password_user = ?";
    private static final String CARI_ANIME_QUERY = "SELECT * FROM anime WHERE id_anime= ?";
    private static final String CARI_ANIMEALL_QUERY = "SELECT * FROM anime WHERE judul_anime= ?";
    private static final String CARI_ANIMELINK_QUERY = "SELECT * FROM streaming WHERE id_anime= ? AND episode_streaming= ?";
    private static final String CARI_URL_QUERY = "SELECT * FROM streaming WHERE id_streaming = ?";

    public void insertRecord(int id, String nama, String username, String password) throws SQLException {

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
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
    public void tampilAnime() throws SQLException {
        String query = "SELECT * FROM anime";
        
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()) {
            String a = rs.getString("judul_anime");
            animeData.add(a);
        }
    }
    private static String judulAnime = null;
    private static String gambarAnime = null;
    private static int episodeStreaming = 0;
    private static int idAnime = 0;

    public String getJudulAnime() {
        return judulAnime;
    }
    
    public String getGambarAnime() {
        return gambarAnime;
    }

    public int getIdAnime() {
        return idAnime;
    }
    public int getEpisodeStreaming() {
        return episodeStreaming;
    }
    
    public boolean cariAnime (String judul) throws SQLException {
        boolean hasil = false;
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(CARI_ANIMEALL_QUERY)) {
            preparedStatement.setString(1, judul);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                judulAnime = resultSet.getString("judul_anime");
                gambarAnime = resultSet.getString("gambar_anime");
                idAnime = resultSet.getInt("id_anime");
                hasil = true;
            }
        }
        return hasil;
    }
    public void cariAnimeId(int id1, int id2) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM anime, streaming WHERE anime.id_anime = ? AND streaming.id_streaming = ?")) {
            preparedStatement.setInt(1, id1);
            preparedStatement.setInt(2, id2);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                judulAnime = resultSet.getString("judul_anime");
                episodeStreaming = resultSet.getInt("episode_streaming");
                
            }
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
    
    public void cariUrlStreaming(int id) throws SQLException {
        String url = null;
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(CARI_URL_QUERY);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            url = rs.getString("url_streaming");
        }
        PlayerViewController p = new PlayerViewController();
        p.setStrUrl(url);
    }
    //masukan ke dalam player view
    public boolean isNext(String url) throws SQLException {
        int episode_streaming = 0, id_anime= 0,nextEpisode = 0;
        boolean hasil = false;
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM streaming WHERE url_streaming = ?");
        preparedStatement.setString(1, url);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            episode_streaming = rs.getInt("episode_streaming");            
            id_anime = rs.getInt("id_anime");
        }
        nextEpisode = episode_streaming + 1; 
        PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT * FROM streaming WHERE id_anime = ? AND episode_streaming=?");
        preparedStatement2.setInt(1, id_anime);
        preparedStatement2.setInt(2,nextEpisode);
        ResultSet rs2 = preparedStatement2.executeQuery();
        while(rs2.next()) {
            hasil = true;
        }
        return hasil;
    }
    
    public int nextEpisode(String url) throws SQLException {
        int episode_streaming = 0, id_anime= 0,nextEpisode = 0, ids_next_episode =0;
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM streaming WHERE url_streaming = ?");
        preparedStatement.setString(1, url);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            episode_streaming = rs.getInt("episode_streaming");            
            id_anime = rs.getInt("id_anime");
        }
        nextEpisode = episode_streaming + 1; 
        PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT * FROM streaming WHERE id_anime = ? AND episode_streaming=?");
        preparedStatement2.setInt(1, id_anime);
        preparedStatement2.setInt(2,nextEpisode);
        ResultSet rs2 = preparedStatement2.executeQuery();
        while(rs2.next()) {
            ids_next_episode = rs2.getInt("id_streaming");
        }
        return ids_next_episode;
    }
    public int prevEpisode(String url) throws SQLException {
        int episode_streaming = 0, id_anime= 0, prevEpisode = 0, ids_next_episode =0;
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM streaming WHERE url_streaming = ?");
        preparedStatement.setString(1, url);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            episode_streaming = rs.getInt("episode_streaming");            
            id_anime = rs.getInt("id_anime");
        }
        prevEpisode = episode_streaming - 1; 
        PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT * FROM streaming WHERE id_anime = ? AND episode_streaming=?");
        preparedStatement2.setInt(1, id_anime);
        preparedStatement2.setInt(2, prevEpisode);
        ResultSet rs2 = preparedStatement2.executeQuery();
        while(rs2.next()) {
            ids_next_episode = rs2.getInt("id_streaming");
        }
        return ids_next_episode;
    }
    
    
    public boolean isPrev(String url) throws SQLException {
        int episode_streaming = 0, id_anime= 0,prevEpisode = 0;
        boolean hasil = false;
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM streaming WHERE url_streaming = ?");
        preparedStatement.setString(1, url);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            episode_streaming = rs.getInt("episode_streaming");            
            id_anime = rs.getInt("id_anime");
        }
        
        prevEpisode = episode_streaming - 1; 
        PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT * FROM streaming WHERE id_anime = ? AND episode_streaming=?");
        preparedStatement2.setInt(1, id_anime);
        preparedStatement2.setInt(2,prevEpisode);
        ResultSet rs2 = preparedStatement2.executeQuery();
        while(rs2.next()) {
            hasil = true;
        }
        return hasil;
    }

    public void cariAnime(int id) {
        String judul = null, gambar = null, sinopsis = null, durasi = null, status = null, genre = null;
        int jumlah = 0, id_anime_tampung = 0;
        double rating = 0;
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(CARI_ANIME_QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                judul = resultSet.getString("judul_anime");
                gambar = resultSet.getString("gambar_anime");
                sinopsis = resultSet.getString("sinopsis_anime");
                durasi = resultSet.getString("durasi_anime");
                status = resultSet.getString("status");
                genre = resultSet.getString("genre_anime");
                id_anime_tampung = resultSet.getInt("id_anime");
                jumlah = resultSet.getInt("jumlah_episode");
                rating = resultSet.getDouble("rating_anime");
            }

        } catch (SQLException e) {

            printSQLException(e);
        }
        LinkViewController l = new LinkViewController();
        l.masukanData(judul, gambar, sinopsis, durasi, status, genre, jumlah, id_anime_tampung, rating);

    }

    public void cariLinkAnime(int id, int eps) {
        int linkId = 0;
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(CARI_ANIMELINK_QUERY)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, eps);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                linkId = resultSet.getInt("id_streaming");
            }

        } catch (SQLException e) {

            printSQLException(e);
        }
        LinkViewController l = new LinkViewController();
        l.masukanDataLink(linkId);

    }
    private static String namaLengkap = null;
    private static String namaPengguna = null;
    private static String passPengguna = null;

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public String getPassPengguna() {
        return passPengguna;
    }
    
    
    public boolean login(String username, String password) throws SQLException {
        boolean hasil = false;
        
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_QUERY)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                koneksiClass.namaLengkap = resultSet.getString("nama_user");
                koneksiClass.namaPengguna = resultSet.getString("username_user");
                koneksiClass.passPengguna = resultSet.getString("password_user");
                hasil = true;
            }
            
        } catch (SQLException e) {

            printSQLException(e);
        }
        return hasil;

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
