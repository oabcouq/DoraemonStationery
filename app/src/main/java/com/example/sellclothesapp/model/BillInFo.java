package com.example.sellclothesapp.model;

public class BillInFo {
    private String id;
    private int idBill;
    private int idUser;
    private int idProduct;
    private int countProduct;

    public BillInFo(String id, int idBill, int idUser, int idProduct, int countProduct) {
        this.id = id;
        this.idBill = idBill;
        this.idUser = idUser;
        this.idProduct = idProduct;
        this.countProduct = countProduct;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
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

    public int getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(int countProduct) {
        this.countProduct = countProduct;
    }
}
