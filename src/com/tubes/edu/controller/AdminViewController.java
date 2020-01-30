/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubes.edu.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import com.tubes.edu.event.TubesValidasi;
import com.tubes.edu.model.Anime;
import javafx.scene.input.InputMethodEvent;

/**
 * FXML Controller class
 *
 * @author naniya
 */
public class AdminViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private ImageView lihatGambar;
    @FXML
    private Button pilihGambar;
    @FXML
    private TextField judulTxt;
    @FXML
    private Label judulLbl;
    @FXML
    private TextField duraiTxt;
    @FXML
    private Label durasiLbl;
    @FXML
    private TextField jumlahEpTxt;
    @FXML
    private Label jumlahEpLbl;
    @FXML
    private TextField ratingTxt;
    @FXML
    private Label ratingLbl;
    @FXML
    private RadioButton completed;
    @FXML
    private ToggleGroup Status;
    @FXML
    private RadioButton onGoing;
    @FXML
    private Label statusLbl;
    @FXML
    private Label genreLbl;
    @FXML
    private TextArea sinopsisTxt;
    @FXML
    private Label sinopsisLbl;
    @FXML
    private CheckBox genre1cb;
    @FXML
    private CheckBox genre2cb;
    @FXML
    private CheckBox genre8cb;
    @FXML
    private CheckBox genre5cb;
    @FXML
    private CheckBox genre9cb;
    @FXML
    private CheckBox genre6cb;
    @FXML
    private CheckBox genre7cb;
    @FXML
    private CheckBox genre10cb;
    @FXML
    private CheckBox genre11cb;
    @FXML
    private CheckBox genre4cb;
    @FXML
    private CheckBox genre3cb;
    @FXML
    private CheckBox genre12cb;
    @FXML
    private CheckBox genre13cb;
    @FXML
    private CheckBox genre14cb;
    @FXML
    private CheckBox genre15cb;
    @FXML
    private CheckBox genre16cb;
    
    private FileChooser fileChooser;
    private File file;
    private String gambar;
    private Path copy, files;
    private TubesValidasi valid = new TubesValidasi();
    private Anime anime;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anime = new Anime();
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*jpeg")
        );
        final Image tumbnail = new Image(getClass().getResourceAsStream("/com/tubes/edu/asset/tumbnail/no-image-available.jpg"));
            lihatGambar.setImage(tumbnail);
    }

    @FXML
    private void pilihGambarClicked(ActionEvent event) {        
        file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                lihatGambar.setFitWidth(128);
                lihatGambar.setFitHeight(187);
                lihatGambar.setPreserveRatio(true);
                lihatGambar.setImage(image);
                gambar = file.getName();
                files = Paths.get(file.toURI());
            } catch (IOException ex) {
                Logger.getLogger(AdminViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (gambar != null) {
            try {
                File dir = new File(System.getProperty("user.dir"));
                copy = (Path) Paths.get(dir + "/src/com/tubes/edu/asset/tumbnail/" + gambar);
                CopyOption[] options = new CopyOption[]{
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.COPY_ATTRIBUTES
                };
                Files.copy(files, copy, options);
            } catch (IOException ex) {
                Logger.getLogger(AdminViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else gambar = "no-image-available.jpg";
    }

    @FXML
    private void judulTyped(KeyEvent event) {
        valid.validasiJudulAnime(anime, judulTxt, judulLbl);
    }

    @FXML
    private void durasiTyped(KeyEvent event) {
        valid.validasiDurasiAnime(anime, duraiTxt, durasiLbl);
    }

    @FXML
    private void jumlahEpTyped(KeyEvent event) {
        valid.validasiJumlahEpisode(anime, jumlahEpTxt, jumlahEpLbl);
    }

    @FXML
    private void ratingTyped(KeyEvent event) {
        valid.validasiRatingAnime(anime, ratingTxt, ratingLbl);
    }

    @FXML
    private void sinopsisTyped(KeyEvent event) {
        valid.validasiSinopsis(anime, sinopsisTxt, sinopsisLbl);
    }

    @FXML
    private void simpan(ActionEvent event) {
        if (valid.validasiAnime(anime)) {
            System.out.println("Mantul ya");
        } else {
            System.out.println("Ada yang salah");
        }
    }

    @FXML
    private void resetBtn(ActionEvent event) {
    }
    
    @FXML
    public void statusPilih(ActionEvent event) {
        String statusStr = "";
        if(completed.isSelected()) {
            statusStr = completed.getText();
        }
        if(onGoing.isSelected()) {
            statusStr = onGoing.getText();
        }
        valid.validasiStatus(anime, statusStr, statusLbl);
    }
                
    @FXML
    private void genrePilih(ActionEvent event) {
        String genreStr = "";
        if(genre1cb.isSelected()) {
            genreStr += genre1cb.getText() +", ";
        }
        if(genre2cb.isSelected()) {
            genreStr += genre2cb.getText() +", ";
        }
        if(genre3cb.isSelected()) {
            genreStr += genre3cb.getText() +", ";
        }
        if(genre4cb.isSelected()) {
            genreStr += genre4cb.getText() +", ";
        }
        if(genre5cb.isSelected()) {
            genreStr += genre5cb.getText() +", ";
        }
        if(genre6cb.isSelected()) {
            genreStr += genre6cb.getText() +", ";
        }
        if(genre7cb.isSelected()) {
            genreStr += genre7cb.getText() +", ";
        }
        if(genre8cb.isSelected()) {
            genreStr += genre8cb.getText() +", ";
        }
        if(genre9cb.isSelected()) {
            genreStr += genre9cb.getText() +", ";
        }
        if(genre10cb.isSelected()) {
            genreStr += genre10cb.getText() +", ";
        }
        if(genre11cb.isSelected()) {
            genreStr += genre11cb.getText() +", ";
        }
        if(genre12cb.isSelected()) {
            genreStr += genre12cb.getText() +", ";
        }
        if(genre13cb.isSelected()) {
            genreStr += genre13cb.getText() +", ";
        }
        if(genre14cb.isSelected()) {
            genreStr += genre14cb.getText() +", ";
        }
        if(genre15cb.isSelected()) {
            genreStr += genre15cb.getText() +", ";
        }
        if(genre16cb.isSelected()) {
            genreStr += genre16cb.getText() +", ";
        }
        if(genre1cb.isSelected()) {
            genreStr += genre1cb.getText() +", ";
        }
        
        valid.validasiGenre(anime, genreStr, genreLbl);
        System.out.println(genreStr);
        
    }

    private void ratingTyped(InputMethodEvent event) {
        valid.validasiRatingAnime(anime, ratingTxt, ratingLbl);
    }


}
