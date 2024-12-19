package org.example.kutuphanesistemi.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import org.example.kutuphanesistemi.HelloApplication;
import org.example.kutuphanesistemi.model.DBCrud;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class booksList {

    @FXML
  private ListView<String> lste;





    public void initialize() throws SQLException {
        DBCrud dbCrud= new DBCrud();
        ObservableList<String> books= FXCollections.observableArrayList();
    books= dbCrud.listBooksPerson();
lste.setItems(books);


}

    @FXML
    protected void geriGel() throws IOException {

        HelloApplication.changeScene("personPage.fxml","Personel Sayfası");
    }



}
