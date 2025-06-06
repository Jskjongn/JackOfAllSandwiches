package com.pluralsight.datamodels;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Sandwich implements Priceable{

    // properties of a sandwich
    private int size;
    private String breadType;
    private boolean isToasted;
    private ArrayList<Topping> toppings;

    // constructor
    public Sandwich(int size, String breadType, boolean isToasted) {
        this.size = size;
        this.breadType = breadType;
        this.isToasted = isToasted;
        this.toppings = new ArrayList<>();
    }

    // -------------------------------------------------------------------------------------------------------

    // derived methods
    public void addTopping(Topping topping) {
        // adds topping to toppings list
        this.toppings.add(topping);
    }

    public void removeTopping(Topping topping) {
        // removes topping from toppings list
        this.toppings.remove(topping);
    }

    public ArrayList<Topping> getToppings() {
        // returns list of toppings
        return this.toppings;
    }

    public String getFormattedToppings() {
        // returns list of toppings to display
        return toppings.stream()
                .map(Topping::toString)
                .collect(Collectors.joining(", "));
    }

    @Override
    public double getPrice() {

        double toppingPrice = 0.0;
        // for each topping in toppings it gets the price based on size and adds to the topping price
        for (Topping topping : toppings) {
            toppingPrice += topping.getPrice(size);
        }

        // returns base price of bread size plus topping price, if no toppings adds zero
        return switch (this.size) {
            // 4 inch is $5.50
            case 4 -> 5.50 + toppingPrice;
            // 8 inch is $7.00
            case 8 -> 7.00 + toppingPrice;
            // 12 inch is $8.50
            case 12 -> 8.50 + toppingPrice;
            // if no bread size then zero
            default -> 0.0;
        };
    }

    // displays user's completed sandwich
    public String getSandwich() {

        // creates string builder
        StringBuilder sandwichBuilder = new StringBuilder();
        // appends bread size, inch, and bread type (4 inch wheat)
        sandwichBuilder.append(size).append(" inch ").append(breadType);
        // if the sandwich is toasted then appends toasted, if not toasted doesn't append it
        if (isToasted) sandwichBuilder.append(" Toasted");
        // returns the full string to display
        return sandwichBuilder.toString();
    }

    // -------------------------------------------------------------------------------------------------------

    // getters and setters
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getBreadType() {
        return breadType;
    }

    public void setBreadType(String breadType) {
        this.breadType = breadType;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public void setToasted(boolean toasted) {
        isToasted = toasted;
    }
}
