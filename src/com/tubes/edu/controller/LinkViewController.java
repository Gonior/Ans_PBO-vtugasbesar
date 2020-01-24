/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubes.edu.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import com.tubes.edu.connection.TubesDB;
import com.tubes.edu.data.TubesSendingData;
import com.tubes.edu.event.TubesEvent;
import com.tubes.edu.model.Anime;
import com.tubes.edu.model.Link;
import java.sql.SQLException;
import java.util.List;

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
    private TubesEvent tubesEvent;
    private Anime anime;

    public void loadAnime() {
        this.anime = TubesSendingData.getAnime();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAnime();
        try {
            ambilDataLink();
        } catch (SQLException ex) {
            Logger.getLogger(LinkViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public LinkViewController() {
        try {
            tubesEvent = new TubesEvent(TubesDB.getConnection());
        } catch (SQLException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addBox(Link link) {
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
        lblEpisode.setText(Integer.toString(link.getEpisode()));
        lblEpisode.setFont(new Font("Segoe UI", 18.0));
        lblEpisode.setPadding(new Insets(10.0));
        HBox.setMargin(lblEpisode, new Insets(0.0, 2.0, 0.0, 2.0));

        hBox0.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        hBox0.setPrefHeight(43.0);
        hBox0.setPrefWidth(677.0);

        button.setOnAction((ActionEvent event) -> {
            try {
                nonton(anime, link, event);
            } catch (IOException ex) {
                Logger.getLogger(LinkViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    
    public void ambilDataLink() throws SQLException {
        List<Link> list = tubesEvent.getAllLink(anime);
        final Image tumbnail = new Image(getClass().getResourceAsStream("/com/tubes/edu/asset/tumbnail/" + anime.getGambar()));
        lblJudul.setText(anime.getJudul());
        lblSinopsis.setText(anime.getSinopsis());
        lblStatus.setText(anime.getStatus());
        lblGenre.setText(anime.getGenre());
        lblImageView.setImage(tumbnail);
        lblDurasi.setText(anime.getDurasi());
        lblJumlahEpisode.setText(Integer.toString(anime.getJumlahEpisode()));
        lblRating.setText(Double.toString(anime.getRating()));
        list.forEach((link) -> {
            addBox(link);
        });
    }
    public void nonton(Anime anime, Link link, ActionEvent event) throws IOException {
        TubesSendingData.setLink(link);
        TubesSendingData.setAnime(anime);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        tubesEvent.changeStage(stage, "playerView");
    }

    @FXML
    private void kembaliKeHomeView(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        tubesEvent.changeStage(stage, "homeView");
    }

}
