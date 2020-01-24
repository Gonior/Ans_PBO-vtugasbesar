/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubes.edu.data;

import com.tubes.edu.model.Anime;
import com.tubes.edu.model.Link;
import com.tubes.edu.model.User;

public class TubesSendingData {
    private static Anime anime;
    private static User user;
    private static Link link;

    public static Anime getAnime() {
        return anime;
    }

    public static void setAnime(Anime anime) {
        TubesSendingData.anime = anime;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        TubesSendingData.user = user;
    }

    public static Link getLink() {
        return link;
    }

    public static void setLink(Link link) {
        TubesSendingData.link = link;
    }
    
}
