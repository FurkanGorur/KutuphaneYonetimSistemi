package org.example.kutuphanesistemi.controller;

import org.example.kutuphanesistemi.model.DBCrud;

import java.sql.SQLException;

public class loginstudent extends Login{

   public static int ogr_id;
    @Override
    public boolean login(String email, String password) throws SQLException {
        DBCrud dbCrud= new DBCrud();
       ogr_id= dbCrud.get_ogr_id(email,password);
        return dbCrud.loginStudent(email,password);
    }
}
