package org.example.kutuphanesistemi.controller;

import org.example.kutuphanesistemi.model.ItypeAdd;
import org.example.kutuphanesistemi.model.typeAddModel;

public class typeAddFactory implements ItypeAddFactory{
    @Override
    public ItypeAdd type_add() {
        return new typeAddModel();
    }
}
