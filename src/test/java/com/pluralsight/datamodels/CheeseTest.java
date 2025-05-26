package com.pluralsight.datamodels;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheeseTest {

    @Test
    public void premiumTopping_should_getPremiumTopping() {
        // arrange
        PremiumTopping premiumTopping = new Cheese("American", false, 0);

        //act
        String topping = premiumTopping.getTopping();

        //assert
        assertEquals("American", topping);
    }

    @Test
    public void premiumTopping_should_setPremiumTopping() {
        // arrange
        PremiumTopping premiumTopping = new Cheese("", false, 0);

        //act
        premiumTopping.setTopping("Cheddar");

        //assert
        assertEquals("Cheddar", premiumTopping.getTopping());
    }

    @Test
    public void premiumTopping_should_returnFalseIfNotExtra() {
        // arrange
        PremiumTopping premiumTopping = new Cheese("American", false, 0);

        //act
        boolean extra = premiumTopping.isExtra();

        //assert
        assertFalse(extra);
    }

    @Test
    public void premiumTopping_should_returnTrueIfExtra() {
        // arrange
        PremiumTopping premiumTopping = new Cheese("American", true, 0);

        //act
        boolean extra = premiumTopping.isExtra();

        //assert
        assertTrue(extra);
    }

    @Test
    public void premiumTopping_should_returnBasePriceIfNotExtra_fourInchSize() {
        // arrange
        PremiumTopping premiumTopping = new Cheese("American", false, 0);

        // act
        double price = premiumTopping.getPrice(4);

        //assert
        assertEquals(0.75, price);
    }

    @Test
    public void premiumTopping_should_returnBasePriceIfNotExtra_eightInchSize() {
        // arrange
        PremiumTopping premiumTopping = new Cheese("American", false, 0);

        // act
        double price = premiumTopping.getPrice(8);

        //assert
        assertEquals(1.50, price);
    }

    @Test
    public void premiumTopping_should_returnBasePriceIfNotExtra_twelveInchSize() {
        // arrange
        PremiumTopping premiumTopping = new Cheese("American", false, 0);

        // act
        double price = premiumTopping.getPrice(12);

        //assert
        assertEquals(2.25, price);
    }

    @Test
    public void premiumTopping_should_returnExtraPriceIfExtra_fourInchSize() {
        // arrange
        PremiumTopping premiumTopping = new Cheese("American", true, 1);

        // act
        double price = premiumTopping.getPrice(4);

        //assert
        assertEquals(1.05, price);
    }

    @Test
    public void premiumTopping_should_returnExtraPriceIfExtra_eightInchSize() {
        // arrange
        PremiumTopping premiumTopping = new Cheese("American", true, 1);

        // act
        double price = premiumTopping.getPrice(8);

        //assert
        assertEquals(2.10, price);
    }

    @Test
    public void premiumTopping_should_returnExtraPriceIfExtra_twelveInchSize() {
        // arrange
        PremiumTopping premiumTopping = new Cheese("American", true, 1);

        // act
        double price = premiumTopping.getPrice(12);

        //assert
        assertEquals(3.15, price);
    }

    @Test
    public void premiumTopping_should_returnNoPriceIfNoSize() {
        // arrange
        PremiumTopping premiumTopping = new Cheese("American", true, 1);

        // act
        double price = premiumTopping.getPrice(0);

        //assert
        assertEquals(0.0, price);
    }

    @Test
    public void premiumTopping_should_returnExtraPriceIfMoreExtraToppings_fourInchSize() {
        // arrange
        PremiumTopping premiumTopping = new Cheese("American", true, 3);

        // act
        double price = premiumTopping.getPrice(4);

        //assert
        assertEquals(1.65, price);
    }

}