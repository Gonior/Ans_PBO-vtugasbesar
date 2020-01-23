/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubes.edu.model;


public class Link {
    private int idAnime, idStreaming,episode;
    private String urlStreaming;
    private boolean next;

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }
    
    

    public int getIdAnime() {
        return idAnime;
    }

    public void setIdAnime(int idAnime) {
        this.idAnime = idAnime;
    }

    public int getIdStreaming() {
        return idStreaming;
    }

    public void setIdStreaming(int idStreaming) {
        this.idStreaming = idStreaming;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public String getUrlStreaming() {
        return urlStreaming;
    }

    public void setUrlStreaming(String urlStreaming) {
        this.urlStreaming = urlStreaming;
    }
    
    
}
