package org.example.kutuphanesistemi.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.example.kutuphanesistemi.HelloApplication;
import org.example.kutuphanesistemi.model.DBCrud;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class studentHasBook {


    @FXML
    ListView<String> lst;

@FXML
    Button btn;

    public void initialize() throws SQLException {
        DBCrud dbCrud= new DBCrud();
        ObservableList<String> books= FXCollections.observableArrayList();
        books= dbCrud.listBooksStudent();
        lst.setItems(books);




    }

    @FXML
    public void geri() throws IOException {

        HelloApplication.changeScene("studentPage.fxml","Öğrenci Ekranı");
    }


}