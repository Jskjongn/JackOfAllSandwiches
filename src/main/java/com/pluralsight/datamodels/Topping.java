package com.pluralsight.datamodels;

// abstract class that implements an Interface
public abstract class Topping implements Priceable {

    // properties
    private String topping;
    private boolean isExtra;

    // constructor
    public Topping(String topping, boolean isExtra) {
        this.topping = topping;
        this.isExtra = isExtra;
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

    // abstract method
    public abstract double getPrice();
}
