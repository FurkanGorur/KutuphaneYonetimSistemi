package org.example.kutuphanesistemi.controller;

import org.example.kutuphanesistemi.model.DBCrud;

import java.sql.SQLException;

public class StudentAnnounce implements Observer {


    @Override
    public  void update(String mesaj,String ogrNo) throws SQLException {
        DBCrud dbCrud= new DBCrud();
        dbCrud.setDuyuru(mesaj,ogrNo);

    }
}
