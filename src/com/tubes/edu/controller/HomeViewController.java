//
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubes.edu.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.controlsfx.control.textfield.TextFields;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import com.tubes.edu.connection.TubesDB;
import com.tubes.edu.data.TubesSendingData;
import com.tubes.edu.event.AlertHelper;
import com.tubes.edu.event.TubesEvent;
import com.tubes.edu.model.Anime;
import java.util.List;
import javafx.stage.Window;

public class HomeViewController implements Initializable {

    @FXML
    private TextField txtCari;
    @FXML
    private Label lblNamaUser;
    TubesDB conn = new TubesDB();
    TubesSendingData sendData = new TubesSendingData();
    private TubesEvent tubesEvent;
    @FXML
    private ScrollPane homePanel;
    @FXML
    private HBox panelNotFound;
    @FXML
    private Label lblAnimeNotFound;
    @FXML
    private ImageView imageAnimeFound;
    @FXML
    private Label lblAnimeFoundJudul;
    @FXML
    private HBox panelFound;
    @FXML
    private Button btnCari;
    @FXML
    private Button btnKeluar;

    public HomeViewController() {
        try {
            tubesEvent = new TubesEvent(TubesDB.getConnection());
        } catch (SQLException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblNamaUser.setText(TubesSendingData.getUser().getNama());
        try {
            barisDanKolom();
        } catch (SQLException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.tampilAnime();
        } catch (SQLException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<String> dataList = conn.getAnimeData();
        Object[] data = dataList.toArray();
        TextFields.bindAutoCompletion(txtCari, data);
        txtCari.setOnKeyReleased((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    if (conn.cariAnime(HomeViewController.this.txtCari.getText())) {
                        showFoundPanel(true);
                        lblAnimeFoundJudul.setText(conn.getJudulAnime());
                        final Image gambar = new Image(HomeViewController.this.getClass().getResourceAsStream("/com/tubes/edu/asset/tumbnail/" + conn.getGambarAnime()));
                        imageAnimeFound.setImage(gambar);
                    } else {
                        showFoundPanel(false);
                        lblAnimeNotFound.setText(HomeViewController.this.txtCari.getText());
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @FXML
    private void masukKeLinkView2(ActionEvent event) throws IOException {
        nonton(2, event);
    }

    @FXML
    private void masukKeLinkView1(ActionEvent event) throws IOException {
        nonton(1, event);
    }

    @FXML
    private void masukKeLinkView3(ActionEvent event) throws IOException {
        nonton(3, event);
    }

    @FXML
    private void masukKeLinkViewCari(ActionEvent event) throws IOException {
        LinkViewController linkViewController = new LinkViewController();
        linkViewController.setContent(conn.getIdAnime());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        tubesEvent.changeStage(stage, "linkView");
    }

    @FXML
    private void kembaliKeHomePanel(ActionEvent event) {
        showHome();
    }

    @FXML
    private void showTxtCari(ActionEvent event) {
        animasiTxtField();
    }

    @FXML
    private void btnGantiAkun(ActionEvent event) throws IOException {
        Window owner = btnKeluar.getScene().getWindow();
        boolean hasil = AlertHelper.showAndWaitAlert(AlertType.CONFIRMATION, owner, "Konfirmasi", "Tekan Ok untuk ganti akun");
        if (hasil) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            tubesEvent.changeStage(stage, "registerView");
        }

//      
    }

    @FXML
    private void btnKeluar(ActionEvent event) {
        Window owner = btnKeluar.getScene().getWindow();
        boolean hasil = AlertHelper.showAndWaitAlert(AlertType.CONFIRMATION, owner, "Konfirmasi", "Tekan Ok untuk keluar dari aplikasi Ans");
        if (hasil) {
            System.exit(0);
        }
    }
    
    public void showHome() {
        homePanel.setVisible(true);
        panelNotFound.setVisible(false);
        panelFound.setVisible(false);
    }

    public void showFoundPanel(boolean b) {
        if (b) {
            homePanel.setVisible(false);
            panelNotFound.setVisible(false);
            panelFound.setVisible(true);
        } else {
            homePanel.setVisible(false);
            panelNotFound.setVisible(true);
            panelFound.setVisible(false);
        }
    }
    public void animasiTxtField() {
        Timeline timeline = new Timeline();
        KeyValue kv1, kv2;
        KeyFrame kf1, kf2;
        txtCari.translateXProperty().set(266);
        if (txtCari.getPrefWidth() <= 0) {
            txtCari.setStyle("-fx-border-width : 0px 0px 1px 0px;-fx-background-color : transparent;-fx-border-color: #37c5cd;");
            txtCari.setPrefWidth(266);
            txtCari.translateXProperty().set(266);
            btnCari.translateXProperty().set(266);
            kv1 = new KeyValue(txtCari.translateXProperty(), 0, Interpolator.EASE_IN);
            kv2 = new KeyValue(btnCari.translateXProperty(), 0, Interpolator.EASE_IN);
            kf1 = new KeyFrame(Duration.seconds(0.4), kv1);
            kf2 = new KeyFrame(Duration.seconds(0.4), kv2);
            timeline.getKeyFrames().addAll(kf1, kf2);
            timeline.play();
        } else {
            txtCari.setStyle("-fx-border-width : 0px 0px 0px 0px;-fx-background-color : transparent;-fx-border-color: #37c5cd;");
            txtCari.setPrefWidth(0);
            txtCari.translateXProperty().set(266);
            txtCari.translateXProperty().set(266);
            btnCari.translateXProperty().set(-266);
            kv1 = new KeyValue(txtCari.translateXProperty(), 0, Interpolator.EASE_IN);
            kv2 = new KeyValue(btnCari.translateXProperty(), 0, Interpolator.EASE_IN);
            kf1 = new KeyFrame(Duration.seconds(0.2), kv1);
            kf2 = new KeyFrame(Duration.seconds(0.2), kv2);
            timeline.getKeyFrames().addAll(kf1, kf2);
            timeline.play();
        }
    }
    
    public void nonton(int konten, ActionEvent event) throws IOException {
        LinkViewController linkViewController = new LinkViewController();
        linkViewController.setContent(konten);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        tubesEvent.changeStage(stage, "linkView");
    }
    
    public void barisDanKolom() throws SQLException {
        tubesEvent.getAllAnime();
        List<Anime> list = tubesEvent.getAllAnime();
        list.size();
        list.forEach((anime) -> {
            System.out.println(anime.getJudul());
        });
        
    }
}
