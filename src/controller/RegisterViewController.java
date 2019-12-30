/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import anspbotugasbesar.AlertHelper;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import javafx.scene.control.Alert;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import koneksi.koneksiClass;

public class RegisterViewController implements Initializable {

    public static boolean cekAngka(String name) {
        char[] chars = name.toCharArray();
        boolean cek = false;
        for (char c : chars) {
            if (c >= '0' && c <= '9') {
                cek = true;
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
        ps2FieldReg.setOnKeyReleased((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                koneksiClass conn = new koneksiClass();
                Window owner = btnDaftarReg.getScene().getWindow();
                if (namaFieldReg.getText().isEmpty()) {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                            "Masukan nama anda!");
                    return;
                }
                
                if (cekAngka(namaFieldReg.getText())) {
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
                    try {
                        if (conn.cariRecord(usernameFieldReg.getText())) {
                            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                                    "Nama pengguna sudah digunakan!");
                            return;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(RegisterViewController.class.getName()).log(Level.SEVERE, null, ex);
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
                
                try {
                    conn.insertRecord(id, nama, uName, pass);
                } catch (SQLException ex) {
                    Logger.getLogger(RegisterViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                namaFieldReg.setText("");
                usernameFieldReg.setText("");
                ps1FieldReg.setText("");
                ps2FieldReg.setText("");
            }
        });
        loginPassword.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                koneksiClass conn = new koneksiClass();
                Window owner = btnDaftarReg.getScene().getWindow();
                String user, pass;
                user = loginUsername.getText();
                pass = loginPassword.getText();

                try {
                    if (conn.login(user, pass)) {
                        loginPassword.setText("");
                        loginUsername.setText("");
                        Parent fxml = FXMLLoader.load(getClass().getResource("/view/homeView.fxml"));
                        Stage primaryStage;
                        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(fxml);
                        primaryStage.setTitle("Ans");
                        primaryStage.setScene(scene);
                        primaryStage.resizableProperty().setValue(false);
                        primaryStage.show();
                    } else {
                        AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                                "Nama pengguna atau kata sandi anda salah!");

                    }
                } catch (SQLException | IOException ex) {
                    Logger.getLogger(RegisterViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        loginUsername.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                koneksiClass conn = new koneksiClass();
                Window owner = btnDaftarReg.getScene().getWindow();
                String user, pass;
                user = loginUsername.getText();
                pass = loginPassword.getText();

                try {
                    if (conn.login(user, pass)) {
                        loginPassword.setText("");
                        loginUsername.setText("");
                        Parent fxml = FXMLLoader.load(getClass().getResource("/view/homeView.fxml"));
                        Stage primaryStage;
                        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(fxml);
                        primaryStage.setTitle("Ans");
                        primaryStage.setScene(scene);
                        primaryStage.resizableProperty().setValue(false);
                        primaryStage.show();
                    } else {
                        AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                                "Nama pengguna atau kata sandi anda salah!");

                    }
                } catch (SQLException | IOException ex) {
                    Logger.getLogger(RegisterViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    }

    @FXML
    private void daftar(ActionEvent event) {
        boxMasuk.setVisible(false);
        boxDaftar.setVisible(true);
        btnDaftar.setStyle("-fx-background-color: transparent; -fx-text-fill:  #37c5cd; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #37c5cd;");
        btnMasuk.setStyle("-fx-background-color: transparent; -fx-text-fill:  #37c5cda2; -fx-border-width: 0px 0px 2px 0px; -fx-border-color:  #37c5cda2;");
        namaFieldReg.setText("");
        usernameFieldReg.setText("");
        ps1FieldReg.setText("");
        ps2FieldReg.setText("");
    }

    @FXML
    private void masuk(ActionEvent event) {
        boxMasuk.setVisible(true);
        boxDaftar.setVisible(false);
        btnMasuk.setStyle("-fx-background-color: transparent; -fx-text-fill:  #37c5cd; -fx-border-width: 0px 0px 2px 0px; -fx-border-color:  #37c5cd;");
        btnDaftar.setStyle("-fx-background-color: transparent; -fx-text-fill:  #37c5cda2; -fx-border-width: 0px 0px 2px 0px; -fx-border-color:  #37c5cda2;");
        loginPassword.setText("");
        loginUsername.setText("");
    }

    @FXML
    private void userMasuk(ActionEvent event) throws SQLException, IOException {
        koneksiClass conn = new koneksiClass();
        Window owner = btnDaftarReg.getScene().getWindow();
        String user, pass;
        user = loginUsername.getText();
        pass = loginPassword.getText();

        if (conn.login(user, pass)) {
            loginPassword.setText("");
            loginUsername.setText("");
            Parent fxml = FXMLLoader.load(getClass().getResource("/view/homeView.fxml"));
            Stage primaryStage;
            primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxml);
            primaryStage.setTitle("Ans");
            primaryStage.setScene(scene);
            primaryStage.resizableProperty().setValue(false);
            primaryStage.show();
        } else {
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

        if (cekAngka(namaFieldReg.getText())) {
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

        conn.insertRecord(id, nama, uName, pass);
        namaFieldReg.setText("");
        usernameFieldReg.setText("");
        ps1FieldReg.setText("");
        ps2FieldReg.setText("");
    }

}
