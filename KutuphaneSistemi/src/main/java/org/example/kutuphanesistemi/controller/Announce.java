package org.example.kutuphanesistemi.controller;

import org.example.kutuphanesistemi.model.DBCrud;

import java.sql.SQLException;

public class Announce implements Subject{
   private String duyuru;
private String No;



    @Override
    public void notifyObservers() throws SQLException {
        StudentAnnounce studentAnnounce= new StudentAnnounce();
        studentAnnounce.update(duyuru,No);
    }


    public void setDuyuru(String duyuru,String ogrNo) throws SQLException {
        this.duyuru = duyuru;
        this.No=ogrNo;
        notifyObservers();
    }
}

