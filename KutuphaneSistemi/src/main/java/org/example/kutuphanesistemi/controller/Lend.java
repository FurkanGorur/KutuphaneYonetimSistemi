package org.example.kutuphanesistemi.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.example.kutuphanesistemi.model.DBCrud;

import java.sql.SQLException;

public class Lend implements IbookLendState{


    @Override
    public void odunc_ver(int id,String ogr_no) throws SQLException {

        DBCrud crud = new DBCrud();

        int idOgrenci= crud.get_ogrenci_id(ogr_no);

            if (crud.odunc_ver(id, idOgrenci)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Ödünç verme başarılı");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Ödünç verme başarısız");
                alert.show();
            }
        }




    @Override
    public void iade_al(int id) {
        Alert alert = new Alert(Alert.AlertType.ERROR,"Kitap zaten rafta", ButtonType.OK);
        alert.show();
    }



}