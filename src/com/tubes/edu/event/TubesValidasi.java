/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubes.edu.event;

import com.tubes.edu.connection.TubesDB;
import com.tubes.edu.model.Anime;
import com.tubes.edu.model.PesanErrorValidasi;
import com.tubes.edu.model.User;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.util.Arrays;

public class TubesValidasi {

    private static User user;
    private static PesanErrorValidasi pesanError;
    private static TubesEvent tubesEvent;

    public TubesValidasi(User user) throws SQLException {
        TubesValidasi.user = user;
        tubesEvent = new TubesEvent(TubesDB.getConnection());
        pesanError = new PesanErrorValidasi();
        if (validasiNama(user.getNama()) && validasiUsername(user.getUsername()) && validasiPassword(user.getPassword(), user.getRePassword())) {
            pesanError.setHeaderPesan("Pendaftaran berhasil");
            pesanError.setIsiPesan("Tekan OK untuk beralih ke formulir masuk");
            pesanError.setTipePesan(Alert.AlertType.CONFIRMATION);
            pesanError.setValid(true);
        } else {
            pesanError.setValid(false);
        }
    }

    public TubesValidasi() {
    }

    public static PesanErrorValidasi getPesanError() {
        return pesanError;
    }

    private static boolean cekAngka(String s) {
        char[] chars = s.toCharArray();
        boolean cek = false;
        for (char c : chars) {
            if (c >= '0' && c <= '9') {
                cek = true;
            }
        }
        return cek;
    }

    private static boolean cekSpasi(String s) {
        int flag = 0;
        boolean hasil = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                flag++;
            }
        }
        if (flag != 0) {
            hasil = true;
        }
        return hasil;
    }

    private static boolean validasiNama(String s) {
        boolean hasil = false;
        pesanError = new PesanErrorValidasi();
        if (s.isEmpty()) {
            pesanError.setHeaderPesan("Form error");
            pesanError.setIsiPesan("Masukan nama anda");
            pesanError.setTipePesan(Alert.AlertType.ERROR);
        } else if (cekAngka(s)) {

            pesanError.setHeaderPesan("Form error");
            pesanError.setIsiPesan("Nama Tidak valid");
            pesanError.setTipePesan(Alert.AlertType.ERROR);
        } else {
            hasil = true;
        }
        return hasil;
    }

    private String kapitalMaker(String s) {
        String temp = s.substring(0, 1).toUpperCase() + s.substring(1);
        return temp;
    }

    private static boolean validasiUsername(String s) throws SQLException {
        boolean hasil = false;
        pesanError = new PesanErrorValidasi();
        if (s.isEmpty()) {

            pesanError.setHeaderPesan("Form error");
            pesanError.setIsiPesan("Masukan Username anda");
            pesanError.setTipePesan(Alert.AlertType.ERROR);
        } else if (cekSpasi(s)) {
            pesanError.setHeaderPesan("Form error");
            pesanError.setIsiPesan("Username Tidak valid");
            pesanError.setTipePesan(Alert.AlertType.ERROR);
        } else if (tubesEvent.cariUser(s)) {
            pesanError.setHeaderPesan("Form error");
            pesanError.setIsiPesan("Username sudah digunakan");
            pesanError.setTipePesan(Alert.AlertType.ERROR);
        } else {
            hasil = true;
        }
        return hasil;
    }

    private static boolean validasiPassword(String s1, String s2) {
        boolean hasil = false;
        pesanError = new PesanErrorValidasi();
        if (s1.isEmpty()) {
            pesanError.setHeaderPesan("Form error");
            pesanError.setIsiPesan("Masukan Password anda");
            pesanError.setTipePesan(Alert.AlertType.ERROR);
        } else if (s1.length() < 6) {
            pesanError.setHeaderPesan("Form error");
            pesanError.setIsiPesan("Password harus lebih dari 6 karakter");
            pesanError.setTipePesan(Alert.AlertType.ERROR);

        } else if (s2.isEmpty()) {
            pesanError.setHeaderPesan("Form error");
            pesanError.setIsiPesan("Ketik ulang password anda");
            pesanError.setTipePesan(Alert.AlertType.ERROR);
        } else if (!s1.equals(s2)) {
            pesanError.setHeaderPesan("Form error");
            pesanError.setIsiPesan("Password tidak cocok");
            pesanError.setTipePesan(Alert.AlertType.ERROR);
        } else {
            hasil = true;
        }
        return hasil;
    }

    private String durasiMaker(String durasi) {
        int angka = Integer.parseInt(durasi);
        String str = "";
        int jam = (int) angka / 60;
        int menit = angka % 60;
        if (menit <= 0) {
            str = jam + " Jam ";
        } else if (jam <= 0) {
            str = menit + " Menit";
        } else {
            str = jam + " Jam " + menit + " Menit";
        }
        return str;
    }

    private String genreMaker(String str) {
        String temp = "";
        int panjangStr = str.length() - 2;
        for (int i = 0; i < panjangStr; i++) {
            temp += str.charAt(i);
        }
        return temp;
    }

    public void validasiJudulAnime(Anime anime, TextField txt, Label captionLabel, FontAwesomeIcon ok) {
        if (txt.getText().trim().equals("")) {
            captionLabel.setText("Judul harus diisi");
            anime.setJudul("");
            ok.setVisible(false);
        } else {
            captionLabel.setText("");
            ok.setVisible(true);
            String judul = kapitalMaker(txt.getText().trim());
            anime.setJudul(judul);
        }
    }

    public void validasiJumlahEpisode(Anime anime, TextField txt, Label captionLabel, FontAwesomeIcon ok) {

        if (!txt.getText().trim().equals("")) {
            try {
                int jumlahEpisode = Integer.parseInt(txt.getText());
                captionLabel.setText("");
                anime.setJumlahEpisode(jumlahEpisode);
                ok.setVisible(true);
            } catch (NumberFormatException e) {
                captionLabel.setText("Masukan tipe data integer");
                anime.setJumlahEpisode(0);
                ok.setVisible(false);
            }
        } else {
            captionLabel.setText("Masukan Jumlah episode");
            anime.setJumlahEpisode(0);
            ok.setVisible(false);
        }
    }

    public void validasiRatingAnime(Anime anime, TextField txt, Label captionLabel, FontAwesomeIcon ok) {
        if (!txt.getText().trim().equals("")) {

            try {
                double rating = Double.parseDouble(txt.getText());
                try {
                    if (rating > 10) {
                        captionLabel.setText("Skala 1 - 10");
                        anime.setRating(0.0);
                        ok.setVisible(false);
                    } else {
                        captionLabel.setText("");
                        anime.setRating(rating);
                        ok.setVisible(true);
                    }
                } catch (NumberFormatException e) {
                    captionLabel.setText("Skala 1 - 10");
                    anime.setRating(0.0);
                    ok.setVisible(false);
                }

            } catch (NumberFormatException e) {
                captionLabel.setText("Masukan tipe data double");
                anime.setRating(0.0);
                ok.setVisible(false);
            }
        } else {
            captionLabel.setText("Masukan Rating anime");
            anime.setRating(0.0);
            ok.setVisible(false);
        }
    }

    public void validasiDurasiAnime(Anime anime, TextField txt, Label captionLabel, FontAwesomeIcon ok) {
        if (!txt.getText().trim().equals("")) {
            try {
                int a = Integer.parseInt(txt.getText());
                String durasi = durasiMaker(txt.getText().trim());
                anime.setDurasi(durasi);
                captionLabel.setText("");
                ok.setVisible(true);
            } catch (NumberFormatException e) {
                captionLabel.setText("Masukan tipe data integer");
                anime.setDurasi("");
                ok.setVisible(false);
            }
        } else {
            captionLabel.setText("Masukan durasi anime");
            anime.setDurasi("");
            ok.setVisible(false);
        }

    }

    public void validasiSinopsis(Anime anime, TextArea txt, Label captionLabel, FontAwesomeIcon ok) {
        if (!txt.getText().trim().equals("")) {
            if (txt.getText().length() >= 255) {
                captionLabel.setText("Tidak lebih dari 255 karakter");
                anime.setSinopsis("");
                ok.setVisible(false);
            } else {
                captionLabel.setText("");
                anime.setSinopsis(txt.getText().trim());
                ok.setVisible(true);
            }
        } else {
            captionLabel.setText("Masukan sinopsis");
            anime.setSinopsis("");
            ok.setVisible(false);
        }
    }

    public void validasiGenre(Anime anime, String genre, Label captionLabel, FontAwesomeIcon ok) {
        if (genre.equals("")) {
            captionLabel.setText("Setidaknya centang salah satu genre");
            anime.setGenre("");
            ok.setVisible(false);
        } else {
            captionLabel.setText("");
            String gnr = genreMaker(genre);
            anime.setGenre(gnr);
            ok.setVisible(true);
        }
    }

    public void validasiLink(TextField txt, Label info, FontAwesomeIcon ok) {
        if (txt.getText().trim().equals("")) {
            info.setText("Masukan URL");
            ok.setVisible(false);
        } else {
            info.setText("");
            ok.setVisible(true);
        }
    }

    public void validasiStatus(Anime anime, String status, Label captionLabel, FontAwesomeIcon ok) {
        if (status.trim().equals("")) {
            captionLabel.setText("Setidaknya pilih salah satu status");
            anime.setStatus("");
            ok.setVisible(false);
        } else {
            captionLabel.setText("");
            anime.setStatus(status);
            ok.setVisible(true);

        }
    }

    public boolean validasiUrl(Anime anime, TextField[] tx) {
        boolean hasil = false;
        int flag = 0;
        boolean[] hasilarr = new boolean[anime.getJumlahEpisode()];
        int boolean_to_int[] = new int[anime.getJumlahEpisode()];
        for (int i = 0; i < anime.getJumlahEpisode(); i++) {
            hasilarr[i] = !tx[i].getText().trim().equals("");
        }
        for (int i = 0; i < anime.getJumlahEpisode(); i++) {
            if (hasilarr[i]) {
                boolean_to_int[i] = 1;
            } else {
                boolean_to_int[i] = 0;
            }
        }
        for (int i = 0; i < anime.getJumlahEpisode(); i++) {
            flag += boolean_to_int[i];
        }
        hasil = flag == anime.getJumlahEpisode();
        
        return hasil;
    }

    public boolean validasiAnime(Anime anime) {
        boolean hasil = false;
        hasil = !anime.getJudul().equals("") && !anime.getDurasi().equals("") && !anime.getGenre().equals("") && anime.getJumlahEpisode() != 0 && anime.getRating() != 0.0 && anime.getRating() <= 10 && !anime.getSinopsis().equals("") && anime.getSinopsis().length() <= 255 && !anime.getStatus().equals("");
        return hasil;
    }

}
