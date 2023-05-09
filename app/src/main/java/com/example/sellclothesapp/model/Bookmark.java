package com.example.sellclothesapp.model;

public class Bookmark {
    private int id;
    private int idProduct;
    private int idUser;

    public Bookmark(int id, int idProduct, int idUser) {
        this.id = id;
        this.idProduct = idProduct;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public int getIdUser() {
        return idUser;
    }
}
