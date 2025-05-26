package com.pluralsight.datamodels;

public class Meat extends PremiumTopping{

    // constructor
    public Meat(String topping, boolean isExtra, int numOfExtraToppings) {
        super(topping, isExtra, numOfExtraToppings);
    }

    // override method
    @Override
    public double getPrice(int breadSize) {

        // gets price based on the size of bread
        return switch (breadSize) {
            // if 4 inches then base price is $1 and if extra, multiples extra number of toppings by extra $0.50 price and adds it to base price
            case 4 -> this.isExtra() ? (this.getNumOfExtraToppings() * 0.50) + 1.00 : 1.00;
            // if 8 inches then base price is $2 and if extra, multiples extra number of toppings by extra $1.00 price and adds it to base price
            case 8 -> this.isExtra() ? (this.getNumOfExtraToppings() * 1.00) + 2.00 : 2.00;
            // if 12 inches then base price is $3 and if extra, multiples extra number of toppings by extra $1.50 price and adds it to base price
            case 12 -> this.isExtra() ? (this.getNumOfExtraToppings() * 1.50) + 3.00 : 3.00;
            // if no meat toppings then its $0
            default -> 0.0;
        };
    }
}
