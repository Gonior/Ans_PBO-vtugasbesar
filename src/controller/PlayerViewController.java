/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import koneksi.koneksiClass;

/**
 * FXML Controller class
 *
 * @author Naniya
 */
public class PlayerViewController implements Initializable {

    @FXML
    private WebView webViewPlayer;
    @FXML
    private Label lblJudul;
    private static int idStreaming, idAnime;
    private static String strUrl;
    @FXML
    private Button btnPrev;
    @FXML
    private Button btnNext;
    koneksi.koneksiClass conn = new koneksiClass();

    public String getStrUrl() {
        return strUrl;
    }

    public void setStrUrl(String strUrl) {
        PlayerViewController.strUrl = strUrl;
    }

    @FXML
    private HBox mainPlayer;

    /**
     * Initializes the controller class.
     *
     * @param idStreaming
     * @param idAnime
     */
    public void setPlayer(int idStreaming, int idAnime) {
        PlayerViewController.idStreaming = idStreaming;
        PlayerViewController.idAnime = idAnime;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            conn.cariUrlStreaming(idStreaming);
            conn.cariAnimeId(idAnime, idStreaming);
            validasiEpisode(strUrl);
        } catch (SQLException ex) {
            Logger.getLogger(PlayerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblJudul.setText(conn.getJudulAnime() + " Episode " + conn.getEpisodeStreaming());
        WebEngine engine = webViewPlayer.getEngine();
        engine.reload();
        engine.load(strUrl);
    }

    @FXML
    private void kembaliKeLinkView(ActionEvent event) throws IOException {
        WebEngine engine = webViewPlayer.getEngine();
        engine.load("");
        LinkViewController linkViewController = new LinkViewController();
        linkViewController.setContent(idAnime);
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/linkView.fxml"));
        Stage primaryStage;
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxml);
        primaryStage.setTitle("Ans");
        primaryStage.setScene(scene);
        primaryStage.resizableProperty().setValue(false);
        primaryStage.show();
    }
public void validasiEpisode(String u) throws SQLException {
    if (conn.isNext(u)) {
                btnNext.setDisable(false);
            } else {
                btnNext.setDisable(true);
            }
            if (conn.isPrev(u)) {
                btnPrev.setDisable(false);
            } else {
                btnPrev.setDisable(true);
            }
}
    @FXML
    private void epsPrev(ActionEvent event) throws SQLException {
        setPlayer(conn.prevEpisode(strUrl), idAnime);
        conn.cariUrlStreaming(idStreaming);
        conn.cariAnimeId(idAnime, idStreaming);
        validasiEpisode(strUrl);
        lblJudul.setText(conn.getJudulAnime() + " Episode " + conn.getEpisodeStreaming());
        WebEngine engine = webViewPlayer.getEngine();
        engine.load(strUrl);
    }

    @FXML
    private void epsNext(ActionEvent event) throws SQLException {
        setPlayer(conn.nextEpisode(strUrl), idAnime);
        conn.cariUrlStreaming(idStreaming);
        conn.cariAnimeId(idAnime, idStreaming);
        validasiEpisode(strUrl);        
        lblJudul.setText(conn.getJudulAnime() + " Episode " + conn.getEpisodeStreaming());
        WebEngine engine = webViewPlayer.getEngine();
        engine.reload();
        engine.load(strUrl);
    }

}
