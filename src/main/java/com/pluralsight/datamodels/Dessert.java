package com.pluralsight.datamodels;

public class Dessert extends Sides{

    // constructor
    public Dessert(String name) {
        super(name);
    }

    // override method
    @Override
    public double getPrice() {
        // returns constant dessert price of $1.50
        return 1.50;
    }
}
