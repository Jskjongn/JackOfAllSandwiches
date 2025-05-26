package com.pluralsight.datamodels;

public class Cheese extends PremiumTopping{

    // constructor
    public Cheese(String topping, boolean isExtra, int numOfExtraToppings) {
        super(topping, isExtra, numOfExtraToppings);
    }

    // override method
    @Override
    public double getPrice(int breadSize) {

        // gets price based on the size of bread
        return switch (breadSize) {
            // if 4 inches then base price is $0.75 and if extra, multiplies extra number of toppings by extra $0.30 price and adds it to base price
            case 4 -> this.isExtra() ? (this.getNumOfExtraToppings() * 0.30) + 0.75 : 0.75;
            // if 8 inches then base price is $1.50 and if extra, multiplies extra number of toppings by extra $0.60 price and adds it to base price
            case 8 -> this.isExtra() ? (this.getNumOfExtraToppings() * 0.60) + 1.50 : 1.50;
            // if 12 inches then base price is $2.25 and if extra, multiplies extra number of toppings by extra $0.90 price and adds it to base price
            case 12 -> this.isExtra() ? (this.getNumOfExtraToppings() * 0.90) + 2.25 : 2.25;
            // if no cheese toppings then its $0
            default -> 0.0;
        };
    }
}
