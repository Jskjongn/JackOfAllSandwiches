package com.pluralsight.datamodels;

public class RegularTopping extends Topping{

    // regular topping constructor
    public RegularTopping(String topping, boolean isExtra, int numOfExtraToppings) {
        super(topping, isExtra, numOfExtraToppings);
    }

    // override Priceable Interface
    @Override
    public double getPrice(int breadSize) {
        // returns 0 since regular toppings included
        return 0.0;
    }

}
