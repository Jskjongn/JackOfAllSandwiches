package com.pluralsight.datamodels;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeatTest {

    @Test
    public void premiumTopping_should_getPremiumTopping() {
        // arrange
        PremiumTopping premiumTopping = new Meat("Steak", false, 0);

        //act
        String topping = premiumTopping.getTopping();

        //assert
        assertEquals("Steak", topping);
    }

    @Test
    public void premiumTopping_should_setPremiumTopping() {
        // arrange
        PremiumTopping premiumTopping = new Meat("", false, 0);

        //act
        premiumTopping.setTopping("Bacon");

        //assert
        assertEquals("Bacon", premiumTopping.getTopping());
    }

    @Test
    public void premiumTopping_should_returnFalseIfNotExtra() {
        // arrange
        PremiumTopping premiumTopping = new Meat("Steak", false, 0);

        //act
        boolean extra = premiumTopping.isExtra();

        //assert
        assertFalse(extra);
    }

    @Test
    public void premiumTopping_should_returnTrueIfExtra() {
        // arrange
        PremiumTopping premiumTopping = new Meat("Steak", true, 0);

        //act
        boolean extra = premiumTopping.isExtra();

        //assert
        assertTrue(extra);
    }

    @Test
    public void premiumTopping_should_returnBasePriceIfNotExtra_fourInchSize() {
        // arrange
        PremiumTopping premiumTopping = new Meat("Steak", false, 0);

        // act
        double price = premiumTopping.getPrice(4);

        //assert
        assertEquals(1.00, price);
    }

    @Test
    public void premiumTopping_should_returnBasePriceIfNotExtra_eightInchSize() {
        // arrange
        PremiumTopping premiumTopping = new Meat("Steak", false, 0);

        // act
        double price = premiumTopping.getPrice(8);

        //assert
        assertEquals(2.00, price);
    }

    @Test
    public void premiumTopping_should_returnBasePriceIfNotExtra_twelveInchSize() {
        // arrange
        PremiumTopping premiumTopping = new Meat("Steak", false, 0);

        // act
        double price = premiumTopping.getPrice(12);

        //assert
        assertEquals(3.00, price);
    }

    @Test
    public void premiumTopping_should_returnExtraPriceIfExtra_fourInchSize() {
        // arrange
        PremiumTopping premiumTopping = new Meat("Steak", true, 1);

        // act
        double price = premiumTopping.getPrice(4);

        //assert
        assertEquals(1.50, price);
    }

    @Test
    public void premiumTopping_should_returnExtraPriceIfExtra_eightInchSize() {
        // arrange
        PremiumTopping premiumTopping = new Meat("Steak", true, 1);

        // act
        double price = premiumTopping.getPrice(8);

        //assert
        assertEquals(3.00, price);
    }

    @Test
    public void premiumTopping_should_returnExtraPriceIfExtra_twelveInchSize() {
        // arrange
        PremiumTopping premiumTopping = new Meat("Steak", true, 1);

        // act
        double price = premiumTopping.getPrice(12);

        //assert
        assertEquals(4.50, price);
    }

    @Test
    public void premiumTopping_should_returnNoPriceIfNoSize() {
        // arrange
        PremiumTopping premiumTopping = new Meat("Steak", true, 1);

        // act
        double price = premiumTopping.getPrice(0);

        //assert
        assertEquals(0.0, price);
    }

    @Test
    public void premiumTopping_should_returnExtraPriceIfMoreExtraToppings_fourInchSize() {
        // arrange
        PremiumTopping premiumTopping = new Meat("Steak", true, 3);

        // act
        double price = premiumTopping.getPrice(4);

        //assert
        assertEquals(2.50, price);
    }

}