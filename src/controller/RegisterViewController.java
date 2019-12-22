/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import anspbotugasbesar.AlertHelper;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import javafx.scene.control.Alert;
import javafx.scene.layout.*;
import koneksi.koneksiClass;

public class RegisterViewController implements Initializable {

    public static boolean cekAngka(String name) {
        char[] chars = name.toCharArray();
        boolean cek = false;
        for (char c : chars) {
            if (c >= '0' && c <= '9') {
                cek= true;
            }
        }
        return cek;
    }
    @FXML
    private Button btnMasuk;
    @FXML
    private Button btnDaftar;
    @FXML
    private VBox boxMasuk;
    @FXML
    private VBox boxDaftar;
    @FXML
    private TextField namaFieldReg;
    @FXML
    private TextField usernameFieldReg;
    @FXML
    private PasswordField ps1FieldReg;
    @FXML
    private PasswordField ps2FieldReg;
    @FXML
    private Button btnDaftarReg;
    @FXML
    private TextField loginUsername;
    @FXML
    private PasswordField loginPassword;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void daftar(ActionEvent event) {
        boxMasuk.setVisible(false);
        boxDaftar.setVisible(true);
        btnDaftar.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-border-width: 0px 0px 2px 0px; -fx-border-color:white;");
        btnMasuk.setStyle("-fx-background-color: transparent; -fx-text-fill: #aeaeae; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #aeaeae;");
        namaFieldReg.setText("");
        usernameFieldReg.setText("");
        ps1FieldReg.setText("");
        ps2FieldReg.setText("");
    }

    @FXML
    private void masuk(ActionEvent event) {
        boxMasuk.setVisible(true);
        boxDaftar.setVisible(false);
        btnMasuk.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: white;");
        btnDaftar.setStyle("-fx-background-color: transparent; -fx-text-fill: #aeaeae; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #aeaeae;");
        loginPassword.setText("");
        loginUsername.setText("");
    }

    @FXML
    private void userMasuk(ActionEvent event) throws SQLException {
        koneksiClass conn = new koneksiClass();
        Window owner = btnDaftarReg.getScene().getWindow();
        String user, pass;
        user = loginUsername.getText();
        pass = loginPassword.getText();
        
        if(conn.login(user,pass)) {
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner,
                "Anda berhasil login!", 
                "Tekan OK untuk melanjutkan");
            loginPassword.setText("");
            loginUsername.setText("");
            
        } else  {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Nama pengguna atau kata sandi anda salah!");
            return;
        }
    }

    @FXML
    private void userDaftar(ActionEvent event) throws SQLException {
        koneksiClass conn = new koneksiClass();
        Window owner = btnDaftarReg.getScene().getWindow();
        if (namaFieldReg.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Masukan nama anda!");
            return;
        } 
        
        if (cekAngka(namaFieldReg.getText())){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Nama anda tidak sesuai, silakan masukan huruf saja!");
            return;
        }
        

        if (usernameFieldReg.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Masukan nama pengguna anda!");
            return;
        } else {
            int flag = 0;
            for (int i = 0; i < usernameFieldReg.getText().length(); i++) {
                if (usernameFieldReg.getText().charAt(i) == ' ') {
                    flag++;
                }
            }

            if (flag != 0) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Nama pengguna tidak sesuai, masukan tanpa spasi!");
                return;
            }
            if (conn.cariRecord(usernameFieldReg.getText())) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Nama pengguna sudah digunakan!");
                return;
            }
            
        }

        if (ps1FieldReg.getText()
                .isEmpty()) {

            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Masukan kata sandi anda!");
            return;
        }

        if (!ps1FieldReg.getText()
                .equals(ps2FieldReg.getText())) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Kata sandi tidak cocok!");
            return;
        }

        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner,
                "Registration Successful!",
                "Silahkan Lakukan Login");
        
        int id = 0;
        String nama = namaFieldReg.getText();
        String uName = usernameFieldReg.getText();
        String pass = ps1FieldReg.getText();
        
        
        conn.insertRecord(id,nama, uName, pass);
        namaFieldReg.setText("");
        usernameFieldReg.setText("");
        ps1FieldReg.setText("");
        ps2FieldReg.setText("");
    }
}
