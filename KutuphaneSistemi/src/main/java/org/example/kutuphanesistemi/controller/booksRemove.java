package org.example.kutuphanesistemi.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.example.kutuphanesistemi.HelloApplication;
import org.example.kutuphanesistemi.model.DBCrud;

import java.io.IOException;
import java.sql.SQLException;

public class booksRemove {

    @FXML
    TextField txtAd;

    @FXML
    TextField txtId;

    @FXML
    ListView<String> lst;

    @FXML
    public void initialize() throws SQLException {

        DBCrud dbCrud = new DBCrud();
        ObservableList<String> books = FXCollections.observableArrayList();
        books = dbCrud.listBooksPerson();
        lst.setItems(books);

    }

    @FXML
    protected void kitapAra() throws SQLException {

        DBCrud dbCrud = new DBCrud();
        ObservableList<String> books = FXCollections.observableArrayList();
        books = dbCrud.removeBookListPerson(txtAd.getText());
        lst.setItems(books);
    }

    @FXML
    protected void kitapSil() throws SQLException, IOException {
        DBCrud dbCrud = new DBCrud();
       if( dbCrud.deleteBooks(txtId.getText())){
           Alert alert= new Alert(Alert.AlertType.CONFIRMATION,"Kitap Başarıyla Silindi", ButtonType.OK);
           alert.show();
           HelloApplication.changeScene("booksRemove.fxml","Kitap Silme Ekranı");
       }
       else{
           Alert alert= new Alert(Alert.AlertType.WARNING,"Kitap Silinemedi", ButtonType.OK);
           alert.show();
       }


    }

    @FXML
    protected void geriGel() throws IOException {
        HelloApplication.changeScene("personPage.fxml","Personel Ekranı");
    }

}
