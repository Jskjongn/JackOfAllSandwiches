package com.pluralsight.datamodels;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Order {

    // properties of an order
    private String orderName;
    private boolean isTakeOut;
    private ArrayList<Sandwich> sandwiches;
    private ArrayList<Priceable> sides;

    // constructor
    public Order(String orderName, boolean isTakeOut) {
        this.orderName = orderName;
        this.isTakeOut = isTakeOut;
        this.sandwiches = new ArrayList<>();
        this.sides = new ArrayList<>();
    }

    // -------------------------------------------------------------------------------------------------------

    // derived method
    // adds a sandwich to the order
    public void addSandwich(Sandwich sandwich) {
        this.sandwiches.add(sandwich);
    }

    // adds a side to the order
    public void addSide(Priceable side) {
        this.sides.add(side);
    }

    // -------------------------------------------------------------------------------------------------------

    // derived getters
    public double getTotal() {

        double sandwichTotal = 0;
        // for each sandwich it gets the price and adds it to the total
        for (Sandwich sandwich : sandwiches) {
            sandwichTotal += sandwich.getPrice();
        }

        double sideTotal = 0;
        // for each side it gets the price and adds it to the total
        for (Priceable side : sides) {
            sideTotal += side.getPrice();
        }
        // adds sandwich and side total to get total price
        return sandwichTotal + sideTotal;
    }

    public String getOrder() {

        // creates string builder
        StringBuilder receiptBuilder = new StringBuilder();

        // receipt header with customer name and displays either eat in or take out
        receiptBuilder.append("Order for ").append(this.orderName).append("\n");
        receiptBuilder.append("--------------------------------------\n");
        String eatInOrTakeOut = isTakeOut ? "Take-Out" : "Eat-In";
        receiptBuilder.append("              ").append(eatInOrTakeOut).append("                \n\n");

        // displays each sandwich's details and its toppings
        for (Sandwich sandwich : sandwiches) {
            receiptBuilder.append("1 ").append(sandwich.getSandwich())
                    .append(" - $").append(String.format("%.2f", sandwich.getPrice())).append("\n");

            for (Topping topping : sandwich.getToppings()) {
                receiptBuilder.append("   ").append(topping).append("\n");
            }
        }
        // displays each side
        for (Priceable side : sides) {
            receiptBuilder.append("1 ").append(side.toString())
                    .append(" - $").append(String.format("%.2f", side.getPrice())).append("\n");
        }

        receiptBuilder.append("--------------------------------------\n");
        receiptBuilder.append("Total: $").append(String.format("%.2f", getTotal())).append("\n");

        // gets the current time and date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss a");
        receiptBuilder.append("            ").append(LocalDateTime.now().format(formatter)).append("              \n");
        receiptBuilder.append("   Thank you, and please come again!   ");

        // puts receipt together into a string
        return receiptBuilder.toString();
    }

    // -------------------------------------------------------------------------------------------------------

    // getter and setter
    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public boolean isTakeOut() {
        return isTakeOut;
    }

    public void setTakeOut(boolean takeOut) {
        isTakeOut = takeOut;
    }
}
