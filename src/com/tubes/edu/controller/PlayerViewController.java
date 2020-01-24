/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubes.edu.controller;

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
import com.tubes.edu.connection.TubesDB;
import com.tubes.edu.data.TubesSendingData;
import com.tubes.edu.event.TubesEvent;
import com.tubes.edu.model.Anime;
import com.tubes.edu.model.Link;

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
    @FXML
    private Button btnPrev;
    @FXML
    private Button btnNext;
    @FXML
    private HBox mainPlayer;
    private TubesEvent tubesEvent;
    private Anime anime;
    private Link link;

    public void loadData() {
        this.anime = TubesSendingData.getAnime();
        this.link = TubesSendingData.getLink();
    }

    public PlayerViewController() {
        try {
            tubesEvent = new TubesEvent(TubesDB.getConnection());
        } catch (SQLException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        try {
            playAnime(anime, link);
        } catch (SQLException ex) {
            Logger.getLogger(PlayerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void kembaliKeLinkView(ActionEvent event) throws IOException {
        WebEngine engine = webViewPlayer.getEngine();
        engine.load("");
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        tubesEvent.changeStage(primaryStage, "linkView");
        TubesSendingData.setAnime(anime);
    }

    public void validasiEpisode(Link link) throws SQLException {
        if (link.isNext()) {
            btnNext.setDisable(false);
        } else {
            btnNext.setDisable(true);
        }
        if (link.isPrev()) {
            btnPrev.setDisable(false);
        } else {
            btnPrev.setDisable(true);
        }
    }

    @FXML
    private void epsPrev(ActionEvent event) throws SQLException {
        if(tubesEvent.cariLink(anime, link.getEpisode()-1)) {
            TubesSendingData.setLink(tubesEvent.getLink());
        }
        loadData();
        playAnime(anime, link);
    }

    @FXML
    private void epsNext(ActionEvent event) throws SQLException {
        if(tubesEvent.cariLink(anime, link.getEpisode()+1)) {
            TubesSendingData.setLink(tubesEvent.getLink());
        }
        loadData();
        playAnime(anime, link);
    }

    public void playAnime(Anime anime, Link link) throws SQLException {
        validasiEpisode(link);
        lblJudul.setText(anime.getJudul() + " Episode " + link.getEpisode());
        WebEngine engine = webViewPlayer.getEngine();
        engine.reload();
        engine.load(link.getUrlStreaming());
    }

}
