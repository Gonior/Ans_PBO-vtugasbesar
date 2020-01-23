/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubes.edu.event;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;

public class AlertHelper {

    private static Alert alert;

    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        
        AlertHelper.alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();

    }

    public static boolean showAndWaitAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        boolean hasil;
        AlertHelper.alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        Optional<ButtonType> result = alert.showAndWait();
        hasil = result.get() == ButtonType.OK;
        return hasil;
    }


}
