package org.example.kutuphanesistemi.controller;

import javafx.css.Stylesheet;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.example.kutuphanesistemi.HelloApplication;
import org.example.kutuphanesistemi.model.DBCrud;

import java.io.IOException;
import java.sql.SQLException;
import java.text.MessageFormat;

public class studentSignUp {

    @FXML
    private TextField ogrNo;
    @FXML
    private TextField ogrAd;
    @FXML
    private TextField ogrSoyad;
    @FXML
    private TextField ogrEposta;
    @FXML
    private TextField ogrSifre;
    @FXML
    private Button btnKayit;



    @FXML
    protected void SignUp() throws IOException  {


        if(!ogrAd.getText().isEmpty() && !ogrSoyad.getText().isEmpty() && !ogrEposta.getText().isEmpty() && !ogrSifre.getText().isEmpty() && !ogrNo.getText().isEmpty()){

            DBCrud crud = new DBCrud();

            boolean sonuc=crud.signUpStudent(ogrAd.getText(),ogrSoyad.getText(),ogrEposta.getText(),ogrSifre.getText(),ogrNo.getText());
            if (sonuc) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Kullanıcı eklendi", ButtonType.OK);
                alert.show();
                HelloApplication.changeScene("publicLogin.fxml","Giriş sayfası");
            }


        }
        else{
            Alert alert=new Alert(Alert.AlertType.WARNING,"Boş Alan Bırakmayınız",ButtonType.OK);
            alert.show();

        }

    }

    @FXML
    protected void gerigel() throws IOException {
        HelloApplication.changeScene("studentLogin.fxml","Öğrenci Giriş Ekranı");
    }


}
