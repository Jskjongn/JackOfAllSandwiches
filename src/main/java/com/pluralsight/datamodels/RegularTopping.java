package com.pluralsight.datamodels;

public class RegularTopping extends Topping{

    // regular topping constructor
    public RegularTopping(String topping, boolean isExtra) {
        super(topping, isExtra);
    }

    // override Priceable Interface
    @Override
    public double getPrice() {
        // returns 0 since regular toppings included
        return 0.0;
    }

}
