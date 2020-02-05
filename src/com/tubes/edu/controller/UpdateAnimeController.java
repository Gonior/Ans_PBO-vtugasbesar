package com.tubes.edu.controller;

import com.tubes.edu.connection.TubesDB;
import com.tubes.edu.data.TubesSendingData;
import com.tubes.edu.event.TubesEvent;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.scene.control.MenuItem;
import java.io.File;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import com.tubes.edu.event.TubesValidasi;
import com.tubes.edu.model.Anime;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class UpdateAnimeController implements Initializable {

    @FXML
    private TextField judulTxt;
    @FXML
    private MenuItem completedMenuItem;
    @FXML
    private MenuItem OnGoingMenuItem;
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
    @FXML
    private Button pilihGambar;
    @FXML
    private TextField ratingTxt;
    @FXML
    private Label infoJudulLbl;
    @FXML
    private Label infoSinopsisLbl;
    @FXML
    private Label infoRatingLbl;
    @FXML
    private Label infoJumlahEpLbl;
    @FXML
    private Label infoStatusLbl;
    @FXML
    private Label infoGenreLbl;
    @FXML
    private FontAwesomeIcon okJudul;
    @FXML
    private FontAwesomeIcon okRating;
    @FXML
    private FontAwesomeIcon okJumlahEp;
    @FXML
    private FontAwesomeIcon okGenre;
    @FXML
    private FontAwesomeIcon okStatus;
    @FXML
    private Label infoDurasiLbl;
    @FXML
    private FontAwesomeIcon okDurasi;
    @FXML
    private TextField jumlahEpTxt;
    @FXML
    private TextField durasiTxt;
    @FXML
    private FontAwesomeIcon okSinopsis;
    @FXML
    private Label hasilMenuItem;

    private FileChooser fileChooser;
    private TubesEvent tube;
    private File file;
    private String gambar;
    private Path copy, files;
    private TubesValidasi valid = new TubesValidasi();
    private static boolean isUpdate;
    private Anime anime;
    private CheckBox[] cb = new CheckBox[20];
    private String genreAnime = "";
    @FXML
    private TextArea sinopsisTxt;
    @FXML
    private ImageView lihatGambar;
    @FXML
    private Label okehLbl;
    @FXML
    private CheckBox genre17cb;
    @FXML
    private CheckBox genre18cb;
    @FXML
    private CheckBox genre19cb;
    @FXML
    private CheckBox genre20cb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*jpeg")
        );
        try {
            tube = new TubesEvent(TubesDB.getConnection());
        } catch (SQLException ex) {
            Logger.getLogger(UpdateAnimeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        anime = TubesSendingData.getAnime();
        loadAnime();

    }

    private void loadAnime() {
        final Image tumbnail = new Image(getClass().getResourceAsStream("/com/tubes/edu/asset/tumbnail/" + anime.getGambar()));
        final String[] genre = anime.getGenre().split(",");

        judulTxt.setText(anime.getJudul());
        sinopsisTxt.setText(anime.getSinopsis());
        gambar = anime.getGambar();
        lihatGambar.setImage(tumbnail);
        ratingTxt.setText(Double.toString(anime.getRating()));
        jumlahEpTxt.setText(Integer.toString(anime.getJumlahEpisode()));
        hasilMenuItem.setText(anime.getStatus());
        durasiTxt.setText(Integer.toString(anime.getDurasi()));
        cb[0] = genre1cb;
        cb[1] = genre2cb;
        cb[2] = genre3cb;
        cb[3] = genre4cb;
        cb[4] = genre5cb;
        cb[5] = genre6cb;
        cb[6] = genre7cb;
        cb[7] = genre8cb;
        cb[8] = genre9cb;
        cb[9] = genre10cb;
        cb[10] = genre11cb;
        cb[11] = genre12cb;
        cb[12] = genre13cb;
        cb[13] = genre14cb;
        cb[14] = genre15cb;
        cb[15] = genre16cb;
        cb[16] = genre17cb;
        cb[17] = genre18cb;
        cb[18] = genre19cb;
        cb[19] = genre20cb;

        for (String genre1 : genre) {
            for (CheckBox cb1 : cb) {
                if (genre1.toUpperCase().trim().equals(cb1.getText().toUpperCase())) {
                    cb1.setSelected(true);
                }
            }
        }

    }

    @FXML
    private void genrePilih(ActionEvent event) {
        String genreStr = "";
        if (genre1cb.isSelected()) {
            genreStr += genre1cb.getText() + ", ";
        }
        if (genre2cb.isSelected()) {
            genreStr += genre2cb.getText() + ", ";
        }
        if (genre3cb.isSelected()) {
            genreStr += genre3cb.getText() + ", ";
        }
        if (genre4cb.isSelected()) {
            genreStr += genre4cb.getText() + ", ";
        }
        if (genre5cb.isSelected()) {
            genreStr += genre5cb.getText() + ", ";
        }
        if (genre6cb.isSelected()) {
            genreStr += genre6cb.getText() + ", ";
        }
        if (genre7cb.isSelected()) {
            genreStr += genre7cb.getText() + ", ";
        }
        if (genre8cb.isSelected()) {
            genreStr += genre8cb.getText() + ", ";
        }
        if (genre9cb.isSelected()) {
            genreStr += genre9cb.getText() + ", ";
        }
        if (genre10cb.isSelected()) {
            genreStr += genre10cb.getText() + ", ";
        }
        if (genre11cb.isSelected()) {
            genreStr += genre11cb.getText() + ", ";
        }
        if (genre12cb.isSelected()) {
            genreStr += genre12cb.getText() + ", ";
        }
        if (genre13cb.isSelected()) {
            genreStr += genre13cb.getText() + ", ";
        }
        if (genre14cb.isSelected()) {
            genreStr += genre14cb.getText() + ", ";
        }
        if (genre15cb.isSelected()) {
            genreStr += genre15cb.getText() + ", ";
        }
        if (genre16cb.isSelected()) {
            genreStr += genre16cb.getText() + ", ";
        }
        if (genre17cb.isSelected()) {
            genreStr += genre17cb.getText() + ", ";
        }
        if (genre18cb.isSelected()) {
            genreStr += genre18cb.getText() + ", ";
        }
        if (genre19cb.isSelected()) {
            genreStr += genre19cb.getText() + ", ";
        }
        if (genre20cb.isSelected()) {
            genreStr += genre20cb.getText() + ", ";
        }
        genreAnime = genreStr;
        valid.validasiGenre(anime, genreStr, infoGenreLbl, okGenre);
    }

    @FXML
    private void pilihGambarClicked(ActionEvent event) {
        file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                lihatGambar.setImage(image);
                gambar = file.getName();
                files = Paths.get(file.toURI());
            } catch (IOException ex) {
                Logger.getLogger(UpdateAnimeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void judulTyped(KeyEvent event) {
        valid.validasiJudulAnime(anime, judulTxt, infoJudulLbl, okJudul);
    }

    @FXML
    private void sinopsisTyped(KeyEvent event) {
        valid.validasiSinopsis(anime, sinopsisTxt, infoSinopsisLbl, okSinopsis);
    }

    @FXML
    private void ratingTyped(KeyEvent event) {
        valid.validasiRatingAnime(anime, ratingTxt, infoRatingLbl, okRating);
    }

    @FXML
    private void jumlahEpTyped(KeyEvent event) {
        valid.validasiJumlahEpisode(anime, jumlahEpTxt, infoJumlahEpLbl, okJumlahEp);
    }

    EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            hasilMenuItem.setText(((MenuItem) event.getSource()).getText());
            valid.validasiStatus(anime, hasilMenuItem.getText(), infoStatusLbl, okStatus);

        }
    };

    @FXML
    private void pilihStatus(ActionEvent event) {
        valid.validasiStatus(anime, hasilMenuItem.getText(), infoStatusLbl, okStatus);
    }

    @FXML
    private void durasiTyped(KeyEvent event) {
        valid.validasiDurasiAnime(anime, durasiTxt, infoDurasiLbl, okDurasi);
    }

    @FXML
    private void pilihStatus(MouseEvent event) {
        valid.validasiStatus(anime, hasilMenuItem.getText(), infoStatusLbl, okStatus);

    }

    @FXML
    private void kembaliKehome(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        tube.changeStage(stage, "AdminView");

    }

    @FXML
    private void updateAnime(ActionEvent event) throws SQLException {
        valid.validasiStatus(anime, hasilMenuItem.getText(), infoStatusLbl, okStatus);
        valid.validasiGenre(anime, genreAnime, infoGenreLbl, okGenre);
        valid.validasiJudulAnime(anime, judulTxt, infoJudulLbl, okJudul);
        valid.validasiSinopsis(anime, sinopsisTxt, infoSinopsisLbl, okSinopsis);
        valid.validasiRatingAnime(anime, ratingTxt, infoRatingLbl, okRating);
        valid.validasiJumlahEpisode(anime, jumlahEpTxt, infoJumlahEpLbl, okJumlahEp);
        valid.validasiDurasiAnime(anime, durasiTxt, infoDurasiLbl, okDurasi);
        if (!gambar.equals(anime.getGambar())) {
            try {
                File dir = new File(System.getProperty("user.dir"));
                copy = (Path) Paths.get(dir + "/src/com/tubes/edu/asset/tumbnail/" + gambar);
                CopyOption[] options = new CopyOption[]{
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.COPY_ATTRIBUTES
                };
                Files.copy(files, copy, options);
            } catch (IOException ex) {
                Logger.getLogger(UpdateAnimeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        anime.setGambar(gambar);
        if (valid.validasiAnime(anime)) {
            tube.updateAnime(anime);
            okehLbl.setVisible(false);
        } else {
            okehLbl.setVisible(true);
        }
    }
}
