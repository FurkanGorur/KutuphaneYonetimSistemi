package org.example.kutuphanesistemi.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.kutuphanesistemi.HelloApplication;
import org.example.kutuphanesistemi.model.DBCrud;


import java.io.IOException;
import java.sql.SQLException;

public class studentBookList {


    @FXML
    ListView book_list;
    @FXML
    TextField book_search;



    @FXML
    protected void geri_gel() throws IOException {
        HelloApplication.changeScene("studentPage.fxml","Öğrenci Sayfası");
    }


    @FXML
    public void initialize() throws SQLException {
        DBCrud dbCrud = new DBCrud();

        ObservableList<String> books = FXCollections.observableArrayList();

        books=dbCrud.kitap_list2();
        book_list.setItems(books);

    }


    @FXML
    protected void book_search() {

        DBCrud dbCrud = new DBCrud();
        ObservableList<String> books = FXCollections.observableArrayList();

        try {
            books = dbCrud.listBooksStudent(book_search.getText().toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        book_list.setItems(books);
    }





}
