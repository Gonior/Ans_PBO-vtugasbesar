/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubes.edu.interfaceAll;

import com.tubes.edu.model.Anime;
import com.tubes.edu.model.Link;
import com.tubes.edu.model.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.stage.Stage;

public interface TubesInterface {
    public void changeStage(Stage primaryStage, String source) throws IOException;
    public void insertUSer(User user) throws SQLException;
    public boolean cariUser(String username) throws SQLException;
    public boolean login(User user) throws SQLException;  
    public boolean cariAnime(String judulAnime) throws SQLException;
    public boolean cariAnime(int idAnime) throws SQLException;
    public boolean cariLink(Anime anime, int episode) throws SQLException;
    public List<Link> getAllLink(Anime anime) throws SQLException;
    public List<Anime> getAllAnime() throws SQLException;
    public User getUSer();
    public Anime getAnime();
}
