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

        // sandwich shop name and address
        receiptBuilder.append("     Jack of All Sandwiches\n");
        receiptBuilder.append("             \uD83C\uDCDC\uD83C\uDCDA\uD83C\uDCD6\uD83C\uDCC1\uD83C\uDCAD\uD83C\uDCBA\n");
        receiptBuilder.append("      123 Plae Grownd Stret\n");
        receiptBuilder.append("      Fort Worth, TX, 76006\n");

        // receipt header with customer name and displays either eat in or take out
        receiptBuilder.append("\nOrder for: ").append(this.orderName).append("\n");
        receiptBuilder.append("--------------------------------\n");
        String eatInOrTakeOut = isTakeOut ? "Take-Out" : "Eat-In";
        receiptBuilder.append("             ").append(eatInOrTakeOut).append("\n\n");

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
        // displays subtotal, tax, and total
        double tax = getTotal() * 0.10;
        double total = getTotal() + tax;
        receiptBuilder.append("--------------------------------\n");
        receiptBuilder.append("SUBTOTAL:                 $").append(String.format("%.2f", getTotal())).append("\n");
        receiptBuilder.append("TAX:                      $").append(String.format("%.2f", tax)).append("\n");
        receiptBuilder.append("TOTAL:                    $").append(String.format("%.2f", total)).append("\n");;
        // displays tip and total
        receiptBuilder.append("\nTip:   _________________________\n");
        receiptBuilder.append("\nTOTAL: _________________________\n");

        // gets the current time and date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a MM/dd/yy");
        receiptBuilder.append("\n        ").append(LocalDateTime.now().format(formatter)).append("              \n\n");
        receiptBuilder.append("     ║▌║█║▌│║▌║▌█ ▌│█║▌║▌║\n");
        receiptBuilder.append("     ║▌║█║▌│║▌║▌█ ▌│█║▌║▌║\n");
        receiptBuilder.append("\n         Customer Copy\n");
        receiptBuilder.append("\nThank you, and please come again!");

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
