package org.example.kutuphanesistemi.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.example.kutuphanesistemi.HelloApplication;

import java.io.IOException;

public class publicLogin {

@FXML
    Button btnperson;

@FXML
    Button btnstudent;

     @FXML
    protected void personLogin() throws IOException{

    HelloApplication.changeScene("personLogin.fxml","Personel Giris");

    }

    @FXML
    protected void studentLogin() throws IOException{

    HelloApplication.changeScene("studentLogin.fxml","Ogrenci Giris");
    }



}
