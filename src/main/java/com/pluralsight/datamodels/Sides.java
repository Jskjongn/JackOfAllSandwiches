package com.pluralsight.datamodels;

// abstract class that implements an interface
public abstract class Sides implements Priceable{

    // property of a side
    private String name;

    // constructor
    public Sides(String name) {
        this.name = name;
    }

    // abstract method
    public abstract double getPrice();

    // getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
