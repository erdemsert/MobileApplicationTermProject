package com.erdem.sert.hw2.Food;

import java.util.Arrays;

public class Food {
    private int id;
    private String name;
    private byte [] image;
    private int type;
    private double price;
    private double calorie;

    public Food(int id, String name, byte[] image, int type, double price,double calorie) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.type = type;
        this.price = price;
        this.calorie=calorie;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCalorie() {
        return calorie;
    }

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image=" + Arrays.toString(image) +
                ", type=" + type +
                ", price=" + price +
                ", calorie=" + calorie +
                '}';
    }
}
