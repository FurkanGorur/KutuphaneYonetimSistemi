package org.example.kutuphanesistemi.controller;

import java.sql.SQLException;

public abstract class Login {


    public  abstract boolean login(String email,String password) throws SQLException;


}
