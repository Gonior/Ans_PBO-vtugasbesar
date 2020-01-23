/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubes.edu.controller;

import com.tubes.edu.event.AlertHelper;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import com.tubes.edu.connection.TubesDB;
import com.tubes.edu.data.TubesSendingData;
import com.tubes.edu.event.TubesEvent;
import com.tubes.edu.event.TubesValidasi;
import com.tubes.edu.model.PesanErrorValidasi;
import com.tubes.edu.model.User;


public class RegisterViewController implements Initializable {
    
    public void resetField() {
        loginPassword.setText("");
        loginUsername.setText(""); 
        namaFieldReg.setText("");
        usernameFieldReg.setText("");
        ps1FieldReg.setText("");
        ps2FieldReg.setText("");
    }
    public void tabMasukShow() {
        boxMasuk.setVisible(true);
        boxDaftar.setVisible(false);
        btnMasuk.setStyle(AKTIV);
        btnDaftar.setStyle(DEAKTIV);
        resetField();
        
    }
    
    public void tabDaftarShow() {
        boxMasuk.setVisible(false);
        boxDaftar.setVisible(true);
        btnMasuk.setStyle(DEAKTIV);
        btnDaftar.setStyle(AKTIV);
        resetField();
        
    }

    private final String AKTIV = "-fx-background-color: transparent; -fx-text-fill:  #37c5cd; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #37c5cd;";
    private final String DEAKTIV = "-fx-background-color: transparent; -fx-text-fill:  #37c5cda2; -fx-border-width: 0px 0px 2px 0px; -fx-border-color:  #37c5cda2;";
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
    private User user;
    private TubesEvent tubesEvent;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ps2FieldReg.setOnKeyReleased((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    Window owner = btnDaftarReg.getScene().getWindow();
                    user = new User();
                    user.setId(0);
                    user.setNama(namaFieldReg.getText());
                    user.setUsername(usernameFieldReg.getText());
                    user.setPassword(ps1FieldReg.getText());
                    user.setRePassword(ps2FieldReg.getText());
                    TubesValidasi valid = new TubesValidasi(user);
                    PesanErrorValidasi pesan = TubesValidasi.getPesanError();
                    if (pesan.isValid()) {
                        tubesEvent = new TubesEvent(TubesDB.getConnection());
                        tubesEvent.insertUSer(user);
                        boolean hasil = AlertHelper.showAndWaitAlert(pesan.getTipePesan(), owner, pesan.getHeaderPesan(), pesan.getIsiPesan());
                        if (hasil) {
                            tabMasukShow();
                        } else {
                            tabDaftarShow();
                        }
                    } else {
                        AlertHelper.showAlert(pesan.getTipePesan(),owner, pesan.getHeaderPesan(), pesan.getIsiPesan());       
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(RegisterViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        loginPassword.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Window owner = btnMasuk.getScene().getWindow();
                user = new User();
                user.setUsername(loginUsername.getText());
                user.setPassword(loginPassword.getText());        
                try {
                    tubesEvent = new TubesEvent(TubesDB.getConnection());
                } catch (SQLException ex) {
                    Logger.getLogger(RegisterViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    if(tubesEvent.login(user)) {
                        TubesSendingData.setUser(tubesEvent.getUSer());
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        tubesEvent.changeStage(stage, "homeView");
                    } else {
                        
                        AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Nama pengguna atau kata sandi anda salah!");
                    }       
                } catch (SQLException | IOException ex) {
                    Logger.getLogger(RegisterViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        loginUsername.setOnKeyReleased((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                Window owner = btnMasuk.getScene().getWindow();
                user = new User();
                user.setUsername(loginUsername.getText());
                user.setPassword(loginPassword.getText());        
                try {
                    tubesEvent = new TubesEvent(TubesDB.getConnection());
                } catch (SQLException ex) {
                    Logger.getLogger(RegisterViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    if(tubesEvent.login(user)) {
                        TubesSendingData.setUser(tubesEvent.getUSer());
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        tubesEvent.changeStage(stage, "homeView");
                    } else {
                        
                        AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Nama pengguna atau kata sandi anda salah!");
                    }       
                } catch (SQLException | IOException ex) {
                    Logger.getLogger(RegisterViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

   

    @FXML
    private void daftar(ActionEvent event) {
        tabDaftarShow();
    }

    @FXML
    private void masuk(ActionEvent event) {
        tabMasukShow();
    }

    @FXML
    private void userMasuk(ActionEvent event) throws SQLException, IOException {
        Window owner = btnMasuk.getScene().getWindow();
        user = new User();
        user.setUsername(loginUsername.getText());
        user.setPassword(loginPassword.getText());        
        tubesEvent = new TubesEvent(TubesDB.getConnection());
        if(tubesEvent.login(user)) {
            TubesSendingData.setUser(tubesEvent.getUSer());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            tubesEvent.changeStage(stage, "homeView");
        } else {
            
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Nama pengguna atau kata sandi anda salah!");
        }

    }   

    @FXML
    private void userDaftar(ActionEvent event) throws SQLException {
        Window owner = btnDaftarReg.getScene().getWindow();
        user = new User();
        user.setId(0);
        user.setNama(namaFieldReg.getText());
        user.setUsername(usernameFieldReg.getText());
        user.setPassword(ps1FieldReg.getText());
        user.setRePassword(ps2FieldReg.getText());
        TubesValidasi valid = new TubesValidasi(user);
        PesanErrorValidasi pesan = TubesValidasi.getPesanError();
        if (pesan.isValid()) {
            tubesEvent = new TubesEvent(TubesDB.getConnection());
            tubesEvent.insertUSer(user);
            boolean hasil = AlertHelper.showAndWaitAlert(pesan.getTipePesan(), owner, pesan.getHeaderPesan(), pesan.getIsiPesan());
            if (hasil) {
                tabMasukShow();
            } else {
                tabDaftarShow();       
            }
        } else {
            AlertHelper.showAlert(pesan.getTipePesan(),owner, pesan.getHeaderPesan(), pesan.getIsiPesan());
        }
        
    }
    
      
}
