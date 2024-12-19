package org.example.kutuphanesistemi.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.kutuphanesistemi.HelloApplication;
import org.example.kutuphanesistemi.model.DBCrud;

import java.io.IOException;
import java.sql.SQLException;

public class studentPage {

    @FXML
   private Label lblWelcome;

    @FXML
    private Button btnBook;
    @FXML
    private Button btnAnnounce;

    @FXML
    private Button btnSuggestion;

    @FXML
    private Button btnHasBook;

    @FXML
    private Button btnReadBook;

    @FXML
    private Button btnExit;

@FXML
    public void exit() throws IOException {

        HelloApplication.changeScene("publicLogin.fxml","Ana Sayfa");
    }

    @FXML
    public void hasBook() throws IOException {
  HelloApplication.changeScene("studentHasBook.fxml","Ana Sayfa");
 }

@FXML
    public void wasBook() throws IOException {
        HelloApplication.changeScene("studentReadWasBook.fxml","Ana Sayfa");
    }

@FXML
    public void books() throws IOException {
    HelloApplication.changeScene("studentBookList.fxml","Ana Sayfa");

}

@FXML
    public void duyuru() throws IOException {
    HelloApplication.changeScene("studentAnnounce.fxml","Duyuru Sayfası");
}
public void initialize() throws SQLException {
    DBCrud dbCrud= new DBCrud();
    lblWelcome.setText(dbCrud.get_ogrenci_name());
}


}
