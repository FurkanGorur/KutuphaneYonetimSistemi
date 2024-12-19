package org.example.kutuphanesistemi.controller;

import javafx.scene.control.Alert;
import org.example.kutuphanesistemi.HelloApplication;
import org.example.kutuphanesistemi.model.DBCrud;

import java.sql.SQLException;

public class Return implements IbookLendState{

    @Override
    public void odunc_ver(int id, String ogr_no) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"kitap zaten ödünç verilmiş");
        alert.show();

    }

    @Override
    public void iade_al(int id) throws SQLException {
        DBCrud crud = new DBCrud();
        if(crud.iade_al(id)){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"İade alma başarılı");
            alert.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR,"İade alma başarısız");
            alert.show();


}
}}
