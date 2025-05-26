package com.pluralsight.datamodels;

public abstract class PremiumTopping extends Topping{

    // constructor
    public PremiumTopping(String topping, boolean isExtra, int numOfExtraToppings) {
        super(topping, isExtra, numOfExtraToppings);
    }

    // abstract method
    public abstract double getPrice(int breadSize);
}
