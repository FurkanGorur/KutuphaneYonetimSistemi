package org.example.kutuphanesistemi.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.kutuphanesistemi.HelloApplication;
import org.example.kutuphanesistemi.model.DBCrud;

import java.io.IOException;
import java.sql.SQLException;

public class bookCreate {

    @FXML
    private TextField txtAd;
    @FXML
    private TextField txtYazar;
    @FXML
    private TextField txtYayinevi;

    @FXML
    private TextField dateBasim;

    @FXML
    private ListView<String> lstTur;

    @FXML
    private Button btnEkle;

    @FXML
    private TextField txtTur;

    @FXML
    private Button btnGeri;


    @FXML
    protected void CreateBook() throws IOException {

        if(!txtAd.getText().isEmpty() && !txtYazar.getText().isEmpty() && !txtYayinevi.getText().isEmpty() && !txtTur.getText().isEmpty() && !dateBasim.getText().isEmpty()){

            DBCrud crud = new DBCrud();

            boolean sonuc=crud.createBooks(txtAd.getText(),txtYazar.getText(),dateBasim.getText(),txtYayinevi.getText(),txtTur.getText());
            if (sonuc) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Kitap eklendi", ButtonType.OK);
                alert.show();
            }


        }
        else{
            Alert alert=new Alert(Alert.AlertType.WARNING,"Boş Alan Bırakmayınız",ButtonType.OK);
            alert.show();

        }


    }


    @FXML
    protected void GeriGel() throws IOException {
        HelloApplication.changeScene("personPage.fxml","Personel Sayfası");
    }

    @FXML
    public void initialize() throws SQLException {

        DBCrud dbCrud= new DBCrud();
        ObservableList<String> types= FXCollections.observableArrayList();
        types= dbCrud.listTypeBook();
        lstTur.setItems(types);

    }




}
