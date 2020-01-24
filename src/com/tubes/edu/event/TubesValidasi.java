/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tubes.edu.event;

import com.tubes.edu.connection.TubesDB;
import com.tubes.edu.model.PesanErrorValidasi;
import com.tubes.edu.model.User;
import java.sql.SQLException;
import javafx.scene.control.Alert;

public class TubesValidasi {
        
    private static User user;
    private static PesanErrorValidasi pesanError;
    private static TubesEvent tubesEvent;
    
    
    public TubesValidasi(User user) throws SQLException {
        TubesValidasi.user = user;
        tubesEvent = new TubesEvent(TubesDB.getConnection());
        pesanError = new PesanErrorValidasi();
        if (validasiNama(user.getNama()) && validasiUsername(user.getUsername()) && validasiPassword(user.getPassword(), user.getRePassword())) {
            pesanError.setHeaderPesan("Pendaftaran berhasil");
            pesanError.setIsiPesan("Tekan OK untuk beralih ke formulir masuk");
            pesanError.setTipePesan(Alert.AlertType.CONFIRMATION);
            pesanError.setValid(true);
        } else {
            pesanError.setValid(false);
        }
    }

    
    public static PesanErrorValidasi getPesanError() {
        return pesanError;
    }
    
    
    public static boolean cekAngka(String s) {
        char[] chars = s.toCharArray();
        boolean cek = false;
        for (char c : chars) {
            if (c >= '0' && c <= '9') {
                cek = true;
            }
        }
        return cek;
    }

    public static boolean cekSpasi(String s) {
        int flag = 0;
        boolean hasil = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                flag++;
            }
        }
        if (flag != 0) {
            hasil = true;
        }
        return hasil;
    }
    
    protected static boolean validasiNama(String s) {
        boolean hasil =false;
        pesanError = new PesanErrorValidasi();
        if (s.isEmpty()) {
            pesanError.setHeaderPesan("Form error");
            pesanError.setIsiPesan("Masukan nama anda");
            pesanError.setTipePesan(Alert.AlertType.ERROR);
        } else if (cekAngka(s)) {
            
            pesanError.setHeaderPesan("Form error");
            pesanError.setIsiPesan("Nama Tidak valid");
            pesanError.setTipePesan(Alert.AlertType.ERROR);
        } else {
            hasil = true;
        }
        return hasil;
    }
    protected static boolean validasiUsername(String s) throws SQLException {
        boolean hasil =false;
        pesanError = new PesanErrorValidasi();
        if (s.isEmpty()) {
            
            pesanError.setHeaderPesan("Form error");
            pesanError.setIsiPesan("Masukan Username anda");
            pesanError.setTipePesan(Alert.AlertType.ERROR);
        } else if (cekSpasi(s)) {
            pesanError.setHeaderPesan("Form error");
            pesanError.setIsiPesan("Username Tidak valid");
            pesanError.setTipePesan(Alert.AlertType.ERROR);
        }else if(tubesEvent.cariUser(s)) {
            pesanError.setHeaderPesan("Form error");
            pesanError.setIsiPesan("Username sudah digunakan");
            pesanError.setTipePesan(Alert.AlertType.ERROR);
        } else {
            hasil = true;
        }
        return hasil;
    }
    
    protected static boolean validasiPassword(String s1, String s2) {
        boolean hasil = false ;
        pesanError = new PesanErrorValidasi();
        if (s1.isEmpty()) {
            pesanError.setHeaderPesan("Form error");
            pesanError.setIsiPesan("Masukan Password anda");
            pesanError.setTipePesan(Alert.AlertType.ERROR);  
        }else if(s1.length()<6) {
            pesanError.setHeaderPesan("Form error");
            pesanError.setIsiPesan("Password harus lebih dari 6 karakter");
            pesanError.setTipePesan(Alert.AlertType.ERROR);  
            
        }
        else if (s2.isEmpty()) {
            pesanError.setHeaderPesan("Form error");
            pesanError.setIsiPesan("Ketik ulang password anda");
            pesanError.setTipePesan(Alert.AlertType.ERROR);
        } else if (!s1.equals(s2)) {
            pesanError.setHeaderPesan("Form error");
            pesanError.setIsiPesan("Password tidak cocok");
            pesanError.setTipePesan(Alert.AlertType.ERROR);
        } else {
            hasil = true;
        }
        return hasil;
    }
    
}
