package com.example.sellclothesapp.model;

public class Size {
    private int id;
    private int name;

    public Size(int id, int name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getName() {
        return name;
    }
}
