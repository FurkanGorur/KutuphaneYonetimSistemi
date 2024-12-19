package org.example.kutuphanesistemi.controller;

import java.sql.SQLException;

public interface Observer {


    void update(String mesaj,String ogrNo) throws SQLException;
}
