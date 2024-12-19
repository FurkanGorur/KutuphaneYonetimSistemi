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

public class bookLend {

    IbookLendState iBookLendState;

    @FXML
    TextField ogrNo_txt;
    @FXML
    TextField ktpId_txt;
    @FXML
    ListView<String> lst;
    @FXML
    ListView<String> lst1;
    @FXML
    TextField ktpId_txt1;


    @FXML
    protected void geri_gel() throws IOException {
        HelloApplication.changeScene("personPage.fxml","Giriş Sayfası");
    }

    @FXML
    public void initialize() throws SQLException {
        DBCrud dbCrud = new DBCrud();
        ObservableList<String> books_shelf = FXCollections.observableArrayList();

        books_shelf=dbCrud.listBooksShelfPerson();
        lst.setItems(books_shelf);

        ObservableList<String> book_notShelf = FXCollections.observableArrayList();
        book_notShelf=dbCrud.listBooksNotShelfPerson();
       lst1.setItems(book_notShelf);


        lst.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {

                String[] parts = newValue.split("  ");
                ktpId_txt.setText(parts[0]);
                    Integer kitap_id= Integer.parseInt(parts[0]);

                boolean rafta_mi= true;
                try {
                    rafta_mi = dbCrud.rafta_mi(kitap_id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if(rafta_mi==true){
                    iBookLendState=new Lend();
                    System.out.println("lend");
                }else {
                    iBookLendState=new Return();
                }
            }
        });

        lst1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {

                String[] parts = newValue.split("  ");

                ktpId_txt1.setText(parts[0]);

                Integer kitap_id=Integer.parseInt(parts[0]);
                boolean rafta_mi=true;
                try {
                    rafta_mi=dbCrud.rafta_mi(kitap_id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if(rafta_mi){
                    iBookLendState= new Lend();
                }
                else{
                    iBookLendState= new Return();

                }

            }
        });
    }

@FXML
    public void odunc_ver_btn() throws SQLException, IOException {
        DBCrud dbCrud= new DBCrud();

        if(!ogrNo_txt.getText().isEmpty() && !ktpId_txt.getText().isEmpty()){

            int kitap_id_int = Integer.parseInt(ktpId_txt.getText());
           if( dbCrud.isStudent(ogrNo_txt.getText())){
                iBookLendState.odunc_ver(kitap_id_int, ogrNo_txt.getText());
                dbCrud.setDuyuru("Kitap Ödünç Verilmiştir: "+dbCrud.get_kitapAdi(kitap_id_int),ogrNo_txt.getText());
                HelloApplication.changeScene("bookLend.fxml","Kitap Ödünç"); }

           else{
                Alert alert = new Alert(Alert.AlertType.ERROR,"Öğrenci Bulunamadı ",ButtonType.OK);
                alert.show();
            }}
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR,"Öğrenci Bulunamadı ",ButtonType.OK);
            alert.show();
        }



        }


@FXML
    public void iade_al_btn() throws SQLException, IOException {
    DBCrud dbCrud= new DBCrud();
        if(!ktpId_txt1.getText().isEmpty()){

            int kitap_id_int = Integer.parseInt(ktpId_txt1.getText());
            iBookLendState.iade_al(kitap_id_int);
            Announce announce= new Announce();
            announce.setDuyuru("Kitap İade Edilmiştir: "+dbCrud.get_kitapAdi(kitap_id_int),dbCrud.get_ogrenci_no( dbCrud.getOgrenciId_BooksId(kitap_id_int)));
            HelloApplication.changeScene("bookLend.fxml","Kitap Ödünç");

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR,"Lütfen Alanları Boş Bırakmayınız", ButtonType.OK);
            alert.show();
        }

    }

}