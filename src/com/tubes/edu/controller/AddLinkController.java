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
import com.tubes.edu.event.TubesValidasi;
import com.tubes.edu.model.Anime;
import com.tubes.edu.model.Link;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Firman Alfinas
 */
public class AddLinkController implements Initializable {

    @FXML
    protected VBox container;
    protected VBox copyThis;
    protected HBox row1;
    protected Label indicatorEpisodeLbl;
    protected Label infoLbl;
    protected HBox row2;
    protected TextField urlTxt;
    protected FontAwesomeIcon fontAwesomeIcon0;
    @FXML
    protected Label judulLbl;
    protected Label label0;
    @FXML
    protected Label jumlahEpLbl;
    @FXML
    private ScrollPane addThis;

    private TubesValidasi validasi;
    private Anime anime;
    private Link link;
    private TubesEvent tube;
    private TextField[] texts;
    private Label[] lbls;
    private FontAwesomeIcon[] ics;
    private List<Link> animeLinks;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        validasi = new TubesValidasi();
        anime = new Anime();

        try {
            loadAnime();
        } catch (SQLException ex) {
            Logger.getLogger(AddLinkController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void loadAnime() throws SQLException {
        tube = new TubesEvent(TubesDB.getConnection());
        this.anime = TubesSendingData.getAnime();
        animeLinks = new ArrayList<>();
        texts = new TextField[anime.getJumlahEpisode()];
        lbls = new Label[anime.getJumlahEpisode()];
        ics = new FontAwesomeIcon[anime.getJumlahEpisode()];
        jumlahEpLbl.setText(": " + anime.getJumlahEpisode() + " Episode");
        makeVBox(anime.getJumlahEpisode());
        judulLbl.setText(": " + anime.getJudul());

    }

    public void makeVBox(int i) {
        TextField[] textFields = new TextField[i];
        Label[] labels = new Label[i];
        FontAwesomeIcon[] icons = new FontAwesomeIcon[i];
        for (int j = 1; j <= i; j++) {
            copyThis = new VBox();
            row1 = new HBox();
            label0 = new Label();
            indicatorEpisodeLbl = new Label();
            infoLbl = new Label();
            row2 = new HBox();
            urlTxt = new TextField();
            fontAwesomeIcon0 = new FontAwesomeIcon();
            fontAwesomeIcon0.setVisible(false);
            fontAwesomeIcon0.setGlyphName("CHECK");
            fontAwesomeIcon0.setFill(javafx.scene.paint.Color.GREEN);

            copyThis.setPrefHeight(9.0);
            copyThis.setPrefWidth(754.0);

            row1.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
            row1.setPrefHeight(32.0);
            row1.setPrefWidth(786.0);
            row1.setSpacing(10.0);
            row1.setPadding(new Insets(10.0));

            indicatorEpisodeLbl.setText("Episode " + j);
            indicatorEpisodeLbl.setFont(new Font("Segoe UI Bold", 12.0));

            infoLbl.setText("");
            infoLbl.setTextFill(javafx.scene.paint.Color.RED);
            infoLbl.setFont(new Font("Segoe UI", 12.0));

            row2.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
            row2.setPrefHeight(32.0);
            row2.setPrefWidth(786.0);
            row2.setSpacing(10.0);

            label0.setText("Url");
            label0.setFont(new Font("Segoe UI Bold", 12.0));

            urlTxt.setId("url" + j);
            int index = j - 1;
            String idTxt = urlTxt.getId();
            urlTxt.setPrefHeight(25.0);
            urlTxt.setPrefWidth(663.0);
            urlTxt.setOnKeyReleased((KeyEvent event) -> {
                validasi.validasiLink(textFields[index], labels[index], icons[index]);
            });

            row2.setPadding(new Insets(10.0));
            container.setPadding(new Insets(20.0));
            row1.getChildren().add(indicatorEpisodeLbl);
            row1.getChildren().add(infoLbl);
            copyThis.getChildren().add(row1);
            row2.getChildren().add(label0);
            row2.getChildren().add(urlTxt);
            row2.getChildren().add(fontAwesomeIcon0);
            copyThis.getChildren().add(row2);
            container.getChildren().add(copyThis);
            textFields[j - 1] = urlTxt;
            texts[j - 1] = textFields[j - 1];
            labels[j - 1] = infoLbl;
            lbls[j - 1] = labels[j - 1];
            icons[j - 1] = fontAwesomeIcon0;
            ics[j - 1] = icons[j - 1];

        }

    }

    @FXML
    private void simpan(ActionEvent event) throws SQLException, IOException {
        for (int index = 0; index < anime.getJumlahEpisode(); index++) {
            validasi.validasiLink(texts[index], lbls[index], ics[index]);
        }
        if (validasi.validasiUrl(anime, texts)) {
            tube.insertAnime(anime);
            System.out.println("Data berhasil disimpan");
            Anime newAnime = new Anime();
            if (tube.cariAnime(anime.getJudul())) {
                newAnime = tube.getAnime();
            }
            for (int i = 0; i < anime.getJumlahEpisode(); i++) {
                link = new Link();
                link.setEpisode(i + 1);
                link.setIdAnime(newAnime.getId());
                link.setUrlStreaming(texts[i].getText().trim());
                animeLinks.add(link);
            }
            for (Link l : animeLinks) {
                tube.insertLink(l);
            }
            AlertHelper.showAlert(Alert.AlertType.INFORMATION, null, "SUCCESS", "Data berhasil disimpan!");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            tube.changeStage(stage, "AdminView");
        } else {
            AlertHelper.showAlert(Alert.AlertType.ERROR, null, "Form Error!", "Opps.. Silahkan periksa kembali form url");
        }

    }

    @FXML
    private void kembaliKeTahap1(ActionEvent event) throws IOException {
        TubesSendingData.setAnime(anime);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        tube.changeStage(stage, "CreateView");

    }

}
