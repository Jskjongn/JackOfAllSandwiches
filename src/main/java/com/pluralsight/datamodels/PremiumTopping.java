package com.pluralsight.datamodels;

public abstract class PremiumTopping extends Topping{

    public PremiumTopping(String topping, boolean isExtra) {
        super(topping, isExtra);
    }

    // abstract method
    public abstract double getPrice();
}
