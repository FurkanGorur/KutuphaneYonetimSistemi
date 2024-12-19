package org.example.kutuphanesistemi.controller;

import org.example.kutuphanesistemi.model.DBCrud;

import java.sql.SQLException;

public class loginPerson extends Login{

    @Override
    public boolean login(String email, String password)  {
        DBCrud dbCrud= new DBCrud();

      return dbCrud.loginPerson(email,password);
        }
}
