/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anspbotugasbesar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class AnsPBOTugasBesar extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/playerView.fxml"));
        
        Scene scene = new Scene(fxml);
        primaryStage.setTitle("Ans");
        primaryStage.setScene(scene);
        primaryStage.resizableProperty().setValue(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
