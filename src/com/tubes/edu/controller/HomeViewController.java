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
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.util.List;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Window;

public class HomeViewController implements Initializable {

    @FXML
    private TextField txtCari;
    @FXML
    private Label lblNamaUser;
    @FXML
    private ScrollPane homePanel;
    @FXML
    private HBox panelNotFound;
    @FXML
    private Label lblAnimeNotFound;
    @FXML
    private Button btnCari;
    @FXML
    private Button btnKeluar;
    @FXML
    private VBox barisKontainer;
    @FXML
    private HBox homePanelBox;
    @FXML
    private FontAwesomeIcon home;
    @FXML
    private HBox aboutPanelBox;
    
    
    protected VBox cotainer;
    protected Pane parentPane;
    protected ImageView imgGambar;
    protected Button button;
    protected Label lblJudul;
    protected Label lblGenre;
    protected Label label;
    protected VBox vBox;
    protected Label lblSinopsi;
    private static String[] judulList;
    TubesSendingData sendData = new TubesSendingData();
    private TubesEvent tubesEvent;
    
    
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
            ambilDataAnime();
        } catch (SQLException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TextFields.bindAutoCompletion(txtCari, judulList);
        txtCari.setOnKeyReleased((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    if (tubesEvent.cariAnime(txtCari.getText())) {
                        Anime anime = tubesEvent.getAnime();
                        barisKontainer.getChildren().clear();
                        addContainer(anime);
                        panelNotFound.setVisible(false);
                        homePanelBox.setStyle("-fx-background-color :  #37c5cd;");
                    } else {
                        homePanelBox.setStyle("-fx-background-color :  #37c5cd;");
                        showNotFoundPanel();
                        lblAnimeNotFound.setText(HomeViewController.this.txtCari.getText());
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @FXML
    private void kembaliKeHomePanel(ActionEvent event) throws SQLException {
        
        barisKontainer.getChildren().clear();
        ambilDataAnime();
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
        homePanelBox.setStyle("-fx-background-color : #3ba1b8;");
        aboutPanelBox.setStyle("-fx-background-color : #37c5cd;");
        homePanel.setVisible(true);
        panelNotFound.setVisible(false);
        //tambahin aboutAspanel set false
    }
    public void showAboutUS() {
        homePanelBox.setStyle("-fx-background-color : #37c5cd;");
        aboutPanelBox.setStyle("-fx-background-color : #3ba1b8;");
        homePanel.setVisible(false);
        panelNotFound.setVisible(false);
        //tambahin aboutAspanel set true
    }
    public void showNotFoundPanel() {
        homePanel.setVisible(false);
        panelNotFound.setVisible(true);
        //tambahin aboutAspanel
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
    
    public void nonton(Anime anime, ActionEvent event) throws IOException {
        TubesSendingData.setAnime(anime);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        tubesEvent.changeStage(stage, "linkView");
    }
    public void addContainer(Anime anime) {
        cotainer = new VBox();
        parentPane = new Pane();
        imgGambar = new ImageView();
        button = new Button();
        lblJudul = new Label();
        lblGenre = new Label();
        label = new Label();
        vBox = new VBox();
        lblSinopsi = new Label();

        cotainer.setAlignment(javafx.geometry.Pos.CENTER);
        cotainer.setPrefHeight(340.0);
        cotainer.setPrefWidth(451.0);

        parentPane.setPrefHeight(340.0);
        parentPane.setPrefWidth(0.0);
        final Image tumbnail = new Image(getClass().getResourceAsStream("/com/tubes/edu/asset/tumbnail/" + anime.getGambar()));
        imgGambar.setFitHeight(258.0);
        imgGambar.setFitWidth(164.0);
        imgGambar.setLayoutX(37.0);
        imgGambar.setLayoutY(52.0);
        imgGambar.setPickOnBounds(true);
        imgGambar.setPreserveRatio(true);
        imgGambar.setImage(tumbnail);

        button.setLayoutX(235.0);
        button.setLayoutY(246.0);
        button.setMnemonicParsing(false);
        button.setOnAction((ActionEvent event)->{
            try {
                nonton(anime, event);
            } catch (IOException ex) {
                Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        button.setPrefHeight(38.0);
        button.setPrefWidth(188.0);
        button.setStyle("-fx-text-fill: #000000; -fx-text-fill: white; -fx-background-color: #37c5cd;");
        button.setText("Tonton");
        button.setFont(new Font("Segoe UI", 20.0));

        lblJudul.setLayoutX(232.0);
        lblJudul.setLayoutY(52.0);
        lblJudul.setPrefHeight(29.0);
        lblJudul.setPrefWidth(660.0);
        lblJudul.setText(anime.getJudul());
        lblJudul.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        lblJudul.setWrapText(true);
        lblJudul.setFont(new Font("Segoe UI Bold", 24.0));

        lblGenre.setLayoutX(234.0);
        lblGenre.setLayoutY(82.0);
        lblGenre.setPrefHeight(29.0);
        lblGenre.setPrefWidth(660.0);
        lblGenre.setText(anime.getGenre());
        lblGenre.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        lblGenre.setTextFill(javafx.scene.paint.Color.valueOf("#797878"));
        lblGenre.setWrapText(true);
        lblGenre.setFont(new Font("Segoe UI", 16.0));

        label.setLayoutX(236.0);
        label.setLayoutY(125.0);
        label.setPrefHeight(29.0);
        label.setPrefWidth(660.0);
        label.setText("sinopsis :");
        label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        label.setTextFill(javafx.scene.paint.Color.valueOf("#797878"));
        label.setWrapText(true);
        label.setFont(new Font("Segoe UI", 16.0));

        vBox.setLayoutX(236.0);
        vBox.setLayoutY(154.0);
        vBox.setPrefHeight(70.0);
        vBox.setPrefWidth(666.0);

        lblSinopsi.setPrefHeight(66.0);
        lblSinopsi.setPrefWidth(669.0);
        lblSinopsi.setText(anime.getSinopsis());
        lblSinopsi.setTextFill(javafx.scene.paint.Color.valueOf("#797878"));
        lblSinopsi.setWrapText(true);
        lblSinopsi.setFont(new Font("Segoe UI", 16.0));
        parentPane.getChildren().add(imgGambar);
        parentPane.getChildren().add(button);
        parentPane.getChildren().add(lblJudul);
        parentPane.getChildren().add(lblGenre);
        parentPane.getChildren().add(label);
        vBox.getChildren().add(lblSinopsi);
        parentPane.getChildren().add(vBox);
        cotainer.getChildren().add(parentPane);
        barisKontainer.getChildren().add(cotainer);
        
    }
    
    public void ambilDataAnime() throws SQLException {
        List<Anime> list = tubesEvent.getAllAnime();
        HomeViewController.judulList = new String[list.size()];
        list.forEach((anime) -> {
            addContainer(anime);
        });
        for (int i = 0; i < list.size(); i++) {
            judulList[i] = list.get(i).getJudul();
        }
    }

    @FXML
    private void kembaliKeAboutUs(ActionEvent event) {
        showAboutUS();
    }
}
