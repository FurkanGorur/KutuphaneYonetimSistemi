
package org.example.kutuphanesistemi.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.kutuphanesistemi.HelloApplication;
import org.example.kutuphanesistemi.model.DBCrud;

import java.io.IOException;
import java.sql.SQLException;

public class personLogin {


    @FXML
    private TextField eposta;
    @FXML
    private TextField sifre;
    @FXML
    private Button btn;

    @FXML
    private Button geri;


    @FXML
    protected void loginPersonel() throws IOException, SQLException {

        Login login= new loginPerson();

        if(login.login(eposta.getText(),sifre.getText())) {

            HelloApplication.changeScene("personPage.fxml","Personel Sayfası");
        }else{
            Alert alert= new Alert(Alert.AlertType.ERROR,"Personel Bulunamadı", ButtonType.OK);
            alert.show();
        }


    }
    @FXML
    protected void gerigel() throws IOException {
        HelloApplication.changeScene("publicLogin.fxml","Giriş Sayfası");
    }



}