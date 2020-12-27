package com.erdem.sert.hw2.Activities;

public class mealJson {
    private String name;
    private String calori;

    public mealJson(String name, String calori) {
        this.name = name;
        this.calori = calori;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCalori() {
        return calori;
    }

    public void setCalori(String calori) {
        this.calori = calori;
    }
}

