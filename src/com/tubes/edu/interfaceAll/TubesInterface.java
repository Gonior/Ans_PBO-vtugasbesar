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
    public void cariAnime(String judulAnime) throws SQLException;
    public void cariAnime(int idAnime) throws SQLException;
    public void cariLink(Anime anime) throws SQLException;
    public List<Link> getAllLink() throws SQLException;
    public List<Anime> getAllAnime() throws SQLException;
    public User getUSer();
    
}
