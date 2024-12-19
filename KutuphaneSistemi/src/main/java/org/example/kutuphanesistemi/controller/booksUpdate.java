package org.example.kutuphanesistemi.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.kutuphanesistemi.HelloApplication;
import org.example.kutuphanesistemi.model.DBCrud;

import java.io.IOException;
import java.sql.SQLException;

public class booksUpdate {


    @FXML
    private TextField txtAd;

    @FXML
    private TextField txtAra;

    @FXML
    private TextField txtYazar;
    @FXML
    private TextField txtYayinevi;

    @FXML
    private TextField dateBasim;

    @FXML
    private ListView<String> lstTur;
    @FXML
    private ListView<String> lstKitap;

    @FXML
    private Button btnEkle;

    @FXML
    private TextField txtTur;

    @FXML
    private Button btnGeri;

    private static int kitap_id;

    @FXML
    public void initialize() throws SQLException {
        DBCrud dbCrud = new DBCrud();
        ObservableList<String> books = FXCollections.observableArrayList();
        ObservableList<String> type = FXCollections.observableArrayList();


        books=dbCrud.listBooksPersonNotInnerJoin();
        lstKitap.setItems(books);

        type= dbCrud.listTypeBook();
        lstTur.setItems(type);

        lstKitap.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Satırdaki değerleri ayır ve TextField'lara aktar
                String[] parts = newValue.split("  ");
                kitap_id=Integer.valueOf(parts[0]);
                txtAd.setText(parts[1]); // İlk değeri doldur
                txtYazar.setText(parts[2]);
                txtYayinevi.setText(parts[3]);
                dateBasim.setText(parts[4]);
                txtTur.setText(parts[5]);

            }
        });
    }

    @FXML
    protected void lstKitap() throws SQLException {

        DBCrud dbCrud = new DBCrud();
        ObservableList<String> books = FXCollections.observableArrayList();
        books = dbCrud.updateBookListPerson(txtAra.getText());
        lstKitap.setItems(books);

    }

    @FXML
    protected void kitapGuncelle() throws SQLException, IOException {
int turId= Integer.parseInt(txtTur.getText());
DBCrud dbCrud= new DBCrud();
if( dbCrud.updateBookPerson(txtAd.getText(),txtYazar.getText(),dateBasim.getText(),txtYayinevi.getText(),turId,kitap_id)){
    Alert alert= new Alert(Alert.AlertType.CONFIRMATION,"Kitap Güncellendi", ButtonType.OK);
    alert.show();
    HelloApplication.changeScene("booksUpdate.fxml","Personel Sayfası");

}
else{
    Alert alert= new Alert(Alert.AlertType.WARNING,"Kitap güncellenmedi lütfen değerleri giriniz", ButtonType.OK);
    alert.show();
}



    }

    @FXML
    protected void btnGeri() throws IOException {
        HelloApplication.changeScene("personPage.fxml","Personel Sayfası");
    }

}
