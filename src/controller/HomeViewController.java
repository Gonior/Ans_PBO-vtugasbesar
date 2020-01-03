//
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import koneksi.koneksiClass;
import org.controlsfx.control.textfield.TextFields;

public class HomeViewController implements Initializable {

    @FXML
    private TextField txtCari;
    @FXML
    private Label lblNamaUser;
    koneksiClass conn = new koneksiClass();
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lblNamaUser.setText(conn.getNamaLengkap());
        try {
            conn.tampilAnime();
        } catch (SQLException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList dataList = conn.getAnimeData();
        Object[] data = dataList.toArray();
//        String[] data = {"kimi no na wa", "Chralotte", "kaguya-sama love is war"};
        TextFields.bindAutoCompletion(txtCari, data);
        txtCari.setOnKeyReleased((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    if (conn.cariAnime(HomeViewController.this.txtCari.getText())) {
                        homePanel.setVisible(false);
                        panelNotFound.setVisible(false);
                        panelFound.setVisible(true);
                        lblAnimeFoundJudul.setText(conn.getJudulAnime());
                        final Image gambar = new Image(HomeViewController.this.getClass().getResourceAsStream("/asset/tumbnail/" + conn.getGambarAnime()));
                        imageAnimeFound.setImage(gambar);
                    } else {
                        homePanel.setVisible(false);
                        panelNotFound.setVisible(true);
                        panelFound.setVisible(false);
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

        LinkViewController linkViewController = new LinkViewController();
        linkViewController.setContent(2);
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/linkView.fxml"));
        Stage primaryStage;
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxml);
        primaryStage.setTitle("Ans");
        primaryStage.setScene(scene);
        primaryStage.resizableProperty().setValue(false);
        primaryStage.show();
    }

    @FXML
    private void masukKeLinkView1(ActionEvent event) throws IOException {

        LinkViewController linkViewController = new LinkViewController();
        linkViewController.setContent(1);
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/linkView.fxml"));
        Stage primaryStage;
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxml);
        primaryStage.setTitle("Ans");
        primaryStage.setScene(scene);
        primaryStage.resizableProperty().setValue(false);
        primaryStage.show();
    }

    @FXML
    private void masukKeLinkView3(ActionEvent event) throws IOException {

        LinkViewController linkViewController = new LinkViewController();
        linkViewController.setContent(3);
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/linkView.fxml"));
        Stage primaryStage;
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxml);
        primaryStage.setTitle("Ans");
        primaryStage.setScene(scene);
        primaryStage.resizableProperty().setValue(false);
        primaryStage.show();
    }

    @FXML
    private void masukKeLinkViewCari(ActionEvent event) throws IOException {
        LinkViewController linkViewController = new LinkViewController();
        linkViewController.setContent(conn.getIdAnime());
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/linkView.fxml"));
        Stage primaryStage;
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxml);
        primaryStage.setTitle("Ans");
        primaryStage.setScene(scene);
        primaryStage.resizableProperty().setValue(false);
        primaryStage.show();
    }

    @FXML
    private void kembaliKeHomePanel(ActionEvent event) {
        homePanel.setVisible(true);
        panelNotFound.setVisible(false);
        panelFound.setVisible(false);
    }

    @FXML
    private void showTxtCari(ActionEvent event) {
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

    @FXML
    private void btnGantiAkun(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi");
        alert.setHeaderText(null);
        alert.setContentText("Tekan OK untuk ganti akun");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
//            untuk ganti scene 
            Parent fxml = FXMLLoader.load(getClass().getResource("/view/registerView.fxml"));
            Stage primaryStage;
            primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxml);
            primaryStage.setTitle("Ans");
            primaryStage.setScene(scene);
            primaryStage.resizableProperty().setValue(false);
            primaryStage.show();
        }
//      
    }

    @FXML
    private void btnKeluar(ActionEvent event) {
        Window owner = btnKeluar.getScene().getWindow();

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi");
        alert.setHeaderText("Anda yakin akan keluar?");
        alert.setContentText("Tekan OK untuk keluar");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

}
