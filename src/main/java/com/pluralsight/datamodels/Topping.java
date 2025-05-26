package com.pluralsight.datamodels;

// abstract class
public abstract class Topping {

    // properties
    private String topping;
    private boolean isExtra;
    private int numOfExtraToppings;

    // constructor
    public Topping(String topping, boolean isExtra, int numOfExtraToppings) {
        this.topping = topping;
        this.isExtra = isExtra;
        this.numOfExtraToppings = numOfExtraToppings;
    }

    // getter and setters
    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public boolean isExtra() {
        return isExtra;
    }

    public void setExtra(boolean extra) {
        isExtra = extra;
    }

    public int getNumOfExtraToppings() {
        return numOfExtraToppings;
    }

    public void setNumOfExtraToppings(int numOfExtraToppings) {
        this.numOfExtraToppings = numOfExtraToppings;
    }

    // abstract method
    public abstract double getPrice(int breadSize);
}
