package com.pluralsight.datamodels;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegularToppingTest {

    @Test
    public void regularTopping_should_getTopping() {
        // arrange
        RegularTopping regularTopping = new RegularTopping("Lettuce", false, 0);

        // act
        String topping = regularTopping.getTopping();

        // assert
        assertEquals("Lettuce", topping);
    }

    @Test
    public void regularTopping_should_setTopping() {
        // arrange
        RegularTopping regularTopping = new RegularTopping("", false, 0);

        // act
        regularTopping.setTopping("Onions");

        // assert
        assertEquals("Onions", regularTopping.getTopping());
    }

    @Test
    public void regularTopping_should_returnFalseIfNotExtra() {
        // arrange
        RegularTopping regularTopping = new RegularTopping("Lettuce", false, 0);

        // act
        boolean extra = regularTopping.isExtra();

        // assert
        assertFalse(extra);
    }

    @Test
    public void regularTopping_should_returnTrueIfExtra() {
        // arrange
        RegularTopping regularTopping = new RegularTopping("Lettuce", true, 1);

        // act
        boolean extra = regularTopping.isExtra();

        // assert
        assertTrue(extra);
    }

    @Test
    public void regularToppingPrice_should_beZero() {
        // arrange
        RegularTopping regularTopping = new RegularTopping("Lettuce", true, 1);

        // act
        double price = regularTopping.getPrice(4);

        // assert
        assertEquals(0.0, price);
    }

}