/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import koneksi.ParsingDataContent;
import koneksi.koneksiClass;

/**
 * FXML Controller class
 *
 * @author Naniya
 */
public class LinkViewController implements Initializable {

    @FXML
    private VBox addToThis;
    @FXML
    private Label lblJudul;

    @FXML
    private Label lblRating;
    @FXML
    private Label lblStatus;
    @FXML
    private Label lblGenre;
    @FXML
    private Label lblDurasi;
    @FXML
    private Label lblJumlahEpisode;
    @FXML
    private Label lblSinopsis;
    @FXML
    private ImageView lblImageView;
    koneksiClass conn = new koneksiClass();
    private static String judul, gambar, genre, sinopsis, durasi, status;
    private static int id, jumlah, konten, urlId;
    private static double rating;

    public void masukanData(String judul, String gambar, String sinopsis, String durasi, String status, String genre_anime, int jumlah, int id_anime_tampung, double rating) {
        LinkViewController.sinopsis = sinopsis;
        LinkViewController.judul = judul;
        LinkViewController.gambar = gambar;
        LinkViewController.durasi = durasi;
        LinkViewController.status = status;
        LinkViewController.genre = genre_anime;
        LinkViewController.jumlah = jumlah;
        LinkViewController.id = id_anime_tampung;
        LinkViewController.rating = rating;
    }

    public void masukanDataLink(int urlId) {
        LinkViewController.urlId = urlId;
    }

    public void addBox(int a) {
        HBox hBox, addFontToThis, hBox0, container;
        Button button;

        Label label, lblEpisode;
        hBox = new HBox();
        label = new Label();
        addFontToThis = new HBox();
        lblEpisode = new Label();
        hBox0 = new HBox();
        container = new HBox();
        button = new Button("Play");
        container.setPrefHeight(69.0);
        container.setPrefWidth(900.0);
        hBox.setPrefHeight(43.0);
        hBox.setPrefWidth(116.0);

        label.setPrefHeight(43.0);
        label.setPrefWidth(90.0);
        label.setText("Episode");
        label.setFont(new Font("Segoe UI", 18.0));
        label.setPadding(new Insets(10.0));
        HBox.setMargin(label, new Insets(0.0, 10.0, 0.0, 0.0));

        addFontToThis.setPrefHeight(100.0);
        addFontToThis.setPrefWidth(100.0);

        lblEpisode.setPrefHeight(0.0);
        lblEpisode.setText(Integer.toString(a));
        lblEpisode.setFont(new Font("Segoe UI", 18.0));
        lblEpisode.setPadding(new Insets(10.0));
        HBox.setMargin(lblEpisode, new Insets(0.0, 2.0, 0.0, 2.0));

        hBox0.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        hBox0.setPrefHeight(43.0);
        hBox0.setPrefWidth(677.0);

        int episodeSelected = a;
        button.setOnAction((ActionEvent event) -> {
            
            conn.cariLinkAnime(id, episodeSelected);
            PlayerViewController player = new PlayerViewController();
            player.setPlayer(urlId, id);
            Parent fxml = null;
            try {
                fxml = FXMLLoader.load(getClass().getResource("/view/playerView.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(LinkViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Stage primaryStage;
            primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxml);
            primaryStage.setTitle("Ans");
            primaryStage.setScene(scene);
            primaryStage.resizableProperty().setValue(false);
            primaryStage.show();
        });
        button.setStyle("-fx-background-color: transparent;");
        button.setTextFill(javafx.scene.paint.Color.valueOf("#2600ff"));
        button.setFont(new Font("Segoe UI", 18.0));
        button.setPadding(new Insets(10.0));
        HBox.setMargin(hBox0, new Insets(0.0, 0.0, 0.0, 20.0));
        container.setPadding(new Insets(20.0, 20.0, 20.0, 50.0));

        hBox.getChildren().add(label);
        container.getChildren().add(hBox);
        addFontToThis.getChildren().add(lblEpisode);
        container.getChildren().add(addFontToThis);
        hBox0.getChildren().add(button);
        container.getChildren().add(hBox0);
        addToThis.getChildren().add(container);
    }

    public void TampilkanKonten(int i) {
        conn.cariAnime(i);
    }

    public void setContent(int i) {
        LinkViewController.konten = i;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TampilkanKonten(konten);
        final Image tumbnail = new Image(getClass().getResourceAsStream("/asset/tumbnail/" + gambar));
        lblJudul.setText(judul);
        lblSinopsis.setText(sinopsis);
        lblStatus.setText(status);
        lblGenre.setText(genre);
        lblImageView.setImage(tumbnail);
        lblDurasi.setText(durasi);
        lblJumlahEpisode.setText(Integer.toString(jumlah));
        lblRating.setText(Double.toString(rating));
        for (int i = 1; i <= jumlah; i++) {
            addBox(i);
        }

    }

    @FXML
    private void kembaliKeHomeView(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/homeView.fxml"));
        Stage primaryStage;
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxml);
        primaryStage.setTitle("Ans");
        primaryStage.setScene(scene);
        primaryStage.resizableProperty().setValue(false);
        primaryStage.show();
    }

}
