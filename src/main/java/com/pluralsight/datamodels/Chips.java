package com.pluralsight.datamodels;

public class Chips extends Sides{

    // constructor
    public Chips(String name) {
        super(name);
    }

    // override method
    @Override
    public double getPrice() {
        // returns constant chip price of $1.50
        return 1.50;
    }
}
