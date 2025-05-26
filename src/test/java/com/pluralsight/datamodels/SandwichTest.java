package com.pluralsight.datamodels;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SandwichTest {

    @Test
    public void sandwich_should_getSandwichBreadDetails_breadSize() {
        // arrange
        Sandwich sandwich = new Sandwich(4, "White", false);

        // act
        int breadSize = sandwich.getSize();

        // assert
        assertEquals(4, breadSize);
    }

    @Test
    public void sandwich_should_getSandwichBreadDetails_breadType() {
        // arrange
        Sandwich sandwich = new Sandwich(4, "White", false);

        // act
        String breadType = sandwich.getBreadType();

        // assert
        assertEquals("White", breadType);
    }

    @Test
    public void sandwich_should_getSandwichBreadDetails_returnFalseIfNotToasted() {
        // arrange
        Sandwich sandwich = new Sandwich(4, "White", false);

        // act
        boolean toasted = sandwich.isToasted();

        // assert
        assertFalse(toasted);
    }

    @Test
    public void sandwich_should_getSandwichBreadDetails_returnTrueIfToasted() {
        // arrange
        Sandwich sandwich = new Sandwich(4, "White", true);

        // act
        boolean toasted = sandwich.isToasted();

        // assert
        assertTrue(toasted);
    }

    @Test
    public void sandwich_should_getNumberOfSandwichToppings() {
        // arrange
        Sandwich sandwich = new Sandwich(4, "White", false);

        // act
        sandwich.addTopping(new Meat("Chicken", false, 0));
        sandwich.addTopping(new Cheese("Swiss", false, 0));
        sandwich.addTopping(new RegularTopping("Lettuce", false, 0));

        // assert
        assertEquals(3, sandwich.getToppings().size());
    }

    @Test
    public void sandwich_should_getSandwichToppingsPrice() {
        // arrange
        Sandwich sandwich = new Sandwich(4, "White", false);

        // act
        sandwich.addTopping(new Meat("Chicken", false, 0));
        sandwich.addTopping(new Cheese("Swiss", false, 0));
        sandwich.addTopping(new RegularTopping("Lettuce", false, 0));

        // $5.50 for 4 inch bread, $1.00 for chicken, $0.75 for swiss
        double sandwichPrice = 5.50 + 1.00 + 0.75;

        // assert
        assertEquals(sandwichPrice, sandwich.getPrice());
    }

    @Test
    public void sandwich_should_getSandwichExtraToppingsPrice() {
        // arrange
        Sandwich sandwich = new Sandwich(4, "White", false);

        // act
        sandwich.addTopping(new Meat("Chicken", true, 1));
        sandwich.addTopping(new Cheese("Swiss", true, 1));
        sandwich.addTopping(new RegularTopping("Lettuce", false, 0));

        // $5.50 for 4 inch bread, $1.50 for extra chicken, $1.05 for extra swiss
        double sandwichPrice = 5.50 + 1.50 + 1.05;

        // assert
        assertEquals(sandwichPrice, sandwich.getPrice());
    }

}