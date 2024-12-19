package org.example.kutuphanesistemi.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.example.kutuphanesistemi.HelloApplication;
import org.example.kutuphanesistemi.model.DBCrud;

import java.io.IOException;

public class personPage {

    @FXML
    Button btnEkle;
    @FXML
    Button btnListele;
    @FXML
    Button btnSil;
    @FXML
    Button btnGuncelle;
    @FXML
    Button btnOdunc;
    @FXML
    Button btnIade;
    @FXML
    Button btnKitapNerede;
    @FXML
    Button btnDuyurular;
    @FXML
    Button btnCikis;
    @FXML
    Button btnTurEkle;

    @FXML
    protected void kitapListele() throws IOException {

        HelloApplication.changeScene("booksList.fxml", "Kitap Listele");

    }

    @FXML
    protected void kitapEkle() throws IOException {
        HelloApplication.changeScene("booksCreate.fxml", "Kitap Ekleme");
    }

    @FXML
    protected void tur_ekle() throws IOException {
        HelloApplication.changeScene("typeAdd.fxml", "Tür Ekleme");
    }

    @FXML
    protected void cikisYap() throws IOException {
        HelloApplication.changeScene("publicLogin.fxml", "Ana Ekran");
    }

    @FXML
    protected void kitapSil() throws IOException {
        HelloApplication.changeScene("booksRemove.fxml", "Kitap Silme");
    }

    @FXML
    protected void kitapGuncelle() throws IOException {
        HelloApplication.changeScene("booksUpdate.fxml", "Kitap Güncelleme");
    }

    @FXML
    protected void kitapOdunc() throws IOException {
        HelloApplication.changeScene("bookLend.fxml", "Kitap Ekleme");
    }

    @FXML
    protected void kitapNerede() throws IOException {
        HelloApplication.changeScene("whereIsBook.fxml", "Kitap Ekleme");
    }


}
