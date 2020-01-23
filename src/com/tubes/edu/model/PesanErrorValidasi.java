/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubes.edu.model;

import javafx.scene.control.Alert;

public class PesanErrorValidasi {
    private String headerPesan;
    private String isiPesan;
    private Alert.AlertType tipePesan;
    private boolean valid;

    public PesanErrorValidasi() {
        this.valid = false;
    }
    
    public String getHeaderPesan() {
        return headerPesan;
    }

    public void setHeaderPesan(String headerPesan) {
        this.headerPesan = headerPesan;
    }

    public String getIsiPesan() {
        return isiPesan;
    }

    public void setIsiPesan(String isiPesan) {
        this.isiPesan = isiPesan;
    }

    public Alert.AlertType getTipePesan() {
        return tipePesan;
    }

    public void setTipePesan(Alert.AlertType tipePesan) {
        this.tipePesan = tipePesan;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
    
    
}
