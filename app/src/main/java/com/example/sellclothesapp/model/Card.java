package com.example.sellclothesapp.model;

public class Card {
    private int id;
    private int idUser;
    private int idProduct;
    private int size;
    private int quality;

    public Card() {
    }

    public Card(int id, int idUser, int idProduct, int size, int quality) {
        this.id = id;
        this.idUser = idUser;
        this.idProduct = idProduct;
        this.size = size;
        this.quality = quality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }
}
