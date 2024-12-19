package org.example.kutuphanesistemi.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.kutuphanesistemi.HelloApplication;
import org.example.kutuphanesistemi.model.DBCrud;

import java.io.IOException;
import java.sql.SQLException;

public class studentLogin {


    @FXML
    private TextField eposta;
    @FXML
    private TextField sifre;

    @FXML
    private Label lbl;

    @FXML
    private Button btn;

@FXML
private Button geri;

    @FXML
    protected void loginStudent() throws IOException, SQLException {

        Login login= new loginstudent();


        if(login.login(eposta.getText(),sifre.getText())) {
            HelloApplication.changeScene("studentPage.fxml","Ogrenci Sayfası");
        }else{
            Alert alert= new Alert(Alert.AlertType.INFORMATION,"Geçersiz Kullanıcı", ButtonType.OK);
            alert.show();
        }


    }
@FXML
protected void gerigel() throws IOException {
        HelloApplication.changeScene("personLogin.fxml","Giriş Sayfası");
}

    @FXML
    protected void signUpPage() throws IOException {
        HelloApplication.changeScene("studentSignUp.fxml","Öğrenci Kayıt Ekranı");
    }

}
