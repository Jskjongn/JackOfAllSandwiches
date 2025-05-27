package com.pluralsight.datamodels;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    public void order_should_addSandwichToOrder() {
        // arrange
        Order order = new Order("Jack", true);
        Sandwich sandwich = new Sandwich(4, "White", false);

        // act
        order.addSandwich(sandwich);

        // assert
        assertTrue(order.getOrder().contains("4 inch White"));
    }

    @Test
    public void order_should_addSideToOrder() {
        // arrange
        Order order = new Order("Jack", true);
        Drink drink = new Drink("Coke", "Large");

        // act
        order.addSide(drink);

        // assert
        assertTrue(order.getOrder().contains("Large Coke"));
    }

    @Test
    public void order_should_getOrderPrice() {
        // arrange
        Order order = new Order("Mekayla", true);
        Sandwich sandwich = new Sandwich(4, "White", false);

        // act
        sandwich.addTopping(new Meat("Chicken", false, 0));
        sandwich.addTopping(new Cheese("Swiss", false, 0));
        sandwich.addTopping(new RegularTopping("Lettuce", false, 0));
        order.addSandwich(sandwich);
        order.addSide(new Chips("Lay's BBQ"));

        // $5.50 bread + $1.00 meat + $0.75 cheese + $0.0 lettuce + $1.50 chips)
        double price = order.getTotal();

        // assert
        assertEquals(8.75, price);
    }

    @Test
    public void order_should_haveCustomerName() {
        // arrange
        Order order = new Order("Jack", false);
        Sandwich sandwich = new Sandwich(4, "White", false);


        // act
        order.addSandwich(sandwich);
        String orderName = order.getOrder();

        // assert
        assertTrue(orderName.contains("Order for Jack"));
    }

    @Test
    public void order_should_returnZeroWhenNoOrders() {
        // arrange
        Order order = new Order("Test", true);

        // act
        double price = order.getTotal();

        // assert
        assertEquals(0.0, price);
    }
}