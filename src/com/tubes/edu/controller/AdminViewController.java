/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubes.edu.controller;

import com.tubes.edu.connection.TubesDB;
import com.tubes.edu.data.TubesSendingData;
import com.tubes.edu.event.AlertHelper;
import com.tubes.edu.event.TubesEvent;
import com.tubes.edu.model.Anime;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Deek
 */
public class AdminViewController implements Initializable {

    @FXML
    private Button btn_Add;
    @FXML
    private VBox container;
    private HBox copyThis;
    private Pane layer;
    private Label judulLbl;
    private Label statusLbl;
    private Button updateUrlBtn;
    private Button updateInfoBtn;
    private Button deleteBtn;
    private TubesEvent tubesEvent;
    private Anime anime;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            tubesEvent = new TubesEvent(TubesDB.getConnection());
        } catch (SQLException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(AdminViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadData() throws SQLException {
        container.getChildren().clear();
        List<Anime> list = tubesEvent.getAllAnime();
        list.forEach((anime) -> {
            makeHBox(anime);
        });

    }

    private void makeHBox(Anime anime) {
        copyThis = new HBox();
        layer = new Pane();
        judulLbl = new Label();
        statusLbl = new Label();
        updateUrlBtn = new Button();
        updateInfoBtn = new Button();
        deleteBtn = new Button();
        copyThis.setPrefHeight(81.0);
        copyThis.setPrefWidth(536.0);

        layer.setPrefHeight(77.0);
        layer.setPrefWidth(536.0);
        layer.setStyle("-fx-background-color: #ecf0f1;");

        judulLbl.setLayoutX(14.0);
        judulLbl.setLayoutY(14.0);
        judulLbl.setText(anime.getJudul());
        judulLbl.setTextFill(javafx.scene.paint.Color.valueOf("#d06262"));
        judulLbl.setFont(new Font("Calibri Bold", 18.0));

        statusLbl.setLayoutX(14.0);
        statusLbl.setLayoutY(36.0);
        statusLbl.setText(anime.getStatus());

        updateUrlBtn.setLayoutX(268.0);
        updateUrlBtn.setLayoutY(32.0);
        updateUrlBtn.setMnemonicParsing(false);
        updateUrlBtn.setStyle("-fx-background-color: #3498db;");
        updateUrlBtn.setText("Update URL");
        updateUrlBtn.setTextFill(javafx.scene.paint.Color.valueOf("#eeeeee"));
        updateUrlBtn.setOnAction((ActionEvent event) -> {
            try {
                updateUrl(anime.getId());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                tubesEvent.changeStage(stage, "UpdateLink");
            } catch (SQLException | IOException ex) {
                Logger.getLogger(AdminViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        updateInfoBtn.setLayoutX(369.0);
        updateInfoBtn.setLayoutY(32.0);
        updateInfoBtn.setMnemonicParsing(false);
        updateInfoBtn.setStyle("-fx-background-color: #3498db;");
        updateInfoBtn.setText("Update Info");
        updateInfoBtn.setTextFill(javafx.scene.paint.Color.WHITE);
        updateInfoBtn.setOnAction((ActionEvent event) -> {
            try {
                updateInfo(anime.getId());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                tubesEvent.changeStage(stage, "UpdateAnime");
            } catch (SQLException | IOException ex) {
                Logger.getLogger(AdminViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        deleteBtn.setLayoutX(466.0);
        deleteBtn.setLayoutY(32.0);
        deleteBtn.setMnemonicParsing(false);
        deleteBtn.setStyle("-fx-background-color: #ab0101;");
        deleteBtn.setText("Delete");
        deleteBtn.setTextFill(javafx.scene.paint.Color.valueOf("#dac3c3"));
        deleteBtn.setOnAction((event) -> {
            Window owner = deleteBtn.getScene().getWindow();
            boolean hasil = AlertHelper.showAndWaitAlert(Alert.AlertType.CONFIRMATION, owner, "Konfirmasi", "Tekan Ok untuk menghapus anime");
            if (hasil) {
                try {
                    deleteAnime(anime.getId());
                    loadData();
                } catch (SQLException ex) {
                    Logger.getLogger(AdminViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });

        layer.getChildren().add(judulLbl);
        layer.getChildren().add(statusLbl);
        layer.getChildren().add(updateUrlBtn);
        layer.getChildren().add(updateInfoBtn);
        layer.getChildren().add(deleteBtn);
        copyThis.getChildren().add(layer);
        container.getChildren().add(copyThis);

    }

    @FXML
    private void kembaliKehome(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        tubesEvent.changeStage(stage, "homeView");
    }

    private void updateInfo(int id) throws SQLException {
        if (tubesEvent.cariAnime(id)) {
            this.anime = tubesEvent.getAnime();
            TubesSendingData.setAnime(anime);
        }
    }

    private void updateUrl(int id) throws SQLException {
        if (tubesEvent.cariAnime(id)) {
            this.anime = tubesEvent.getAnime();
            TubesSendingData.setAnime(anime);
        }
    }

    private void deleteAnime(int id) throws SQLException {
        if (tubesEvent.cariAnime(id)) {
            this.anime = tubesEvent.getAnime();
            tubesEvent.deleteAnime(anime);
        }
    }

    @FXML
    private void keCreatView(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        tubesEvent.changeStage(stage, "CreateView");
        
    }
}