/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Naniya
 */
public class PlayerViewController implements Initializable {

    @FXML
    private WebView webViewPlayer;
    @FXML
    private Label lblJudul;
    private static int idStreaming, idAnime;
    private static String strUrl;
    
    @FXML
    private HBox mainPlayer;

    /**
     * Initializes the controller class.
     * @param idStreaming
     * @param idAnime
     */
    
    public void setPlayer(int idStreaming, int idAnime) {
        PlayerViewController.idStreaming = idStreaming;
        PlayerViewController.idAnime = idAnime;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        strUrl = "http://localhost/player/player.php?id="+Integer.toString(idStreaming);
        WebEngine engine = webViewPlayer.getEngine();
        engine.load(strUrl);
    }

    @FXML
    private void kembaliKeLinkView(ActionEvent event) throws IOException {
        LinkViewController linkViewController = new LinkViewController();
        linkViewController.setContent(idAnime);
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/linkView.fxml"));
        Stage primaryStage;
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxml);
        primaryStage.setTitle("Ans");
        primaryStage.setScene(scene);
        primaryStage.resizableProperty().setValue(false);
        primaryStage.show();
    }

    @FXML
    private void epsPrev(ActionEvent event) {
    }

    @FXML
    private void epsNext(ActionEvent event) {
    }
    
}
