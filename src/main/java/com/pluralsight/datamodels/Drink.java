package com.pluralsight.datamodels;

public class Drink extends Sides {

    private String size;

    public Drink(String name, String size) {
        super(name);
        // capitalizes the first letter of the size
        this.size = size.substring(0, 1).toUpperCase() + size.substring(1).toLowerCase().trim();
    }

    // override method
    @Override
    public double getPrice() {

        // returns base price of drink size
        return switch (this.size) {
            // small is $2.00
            case "Small" -> 2.00;
            // medium is $2.50
            case "Medium" -> 2.50;
            // large is $3.00
            case "Large" -> 3.00;
            // if no drink size then zero
            default -> 0.0;
        };
    }

    //getter and setter
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return this.size + " " + this.getName();
    }
}
