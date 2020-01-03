/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anspbotugasbesar;
import javafx.scene.control.Alert;
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
     public Alert getAlertName() {
         return alert;
     }
}