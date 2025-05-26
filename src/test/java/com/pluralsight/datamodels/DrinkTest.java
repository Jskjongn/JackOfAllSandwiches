package com.pluralsight.datamodels;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrinkTest {

    @Test
    public void drink_should_getDrinkName() {
        // arrange
        Drink drink = new Drink("Dr Pepper", "large");

        // act
        String name = drink.getName();

        // assert
        assertEquals("Dr Pepper", name);
    }

    @Test
    public void drink_should_getDrinkSize() {
        // arrange
        Drink drink = new Drink("Dr Pepper", "large");

        // act
        String size = drink.getSize();

        // assert
        assertEquals("Large", size);
    }

    @Test
    public void drink_should_getDrinkSizePrice_small() {
        // arrange
        Drink drink = new Drink("Dr Pepper", "small");

        // act
        double price = drink.getPrice();

        // assert
        assertEquals(2.00, price);
    }

    @Test
    public void drink_should_getDrinkSizePrice_medium() {
        // arrange
        Drink drink = new Drink("Dr Pepper", "medium");

        // act
        double price = drink.getPrice();

        // assert
        assertEquals(2.50, price);
    }

    @Test
    public void drink_should_getDrinkSizePrice_large() {
        // arrange
        Drink drink = new Drink("Dr Pepper", "large");

        // act
        double price = drink.getPrice();

        // assert
        assertEquals(3.00, price);
    }

    @Test
    public void drink_should_getNoDrinkSizePrice_empty() {
        // arrange
        Drink drink = new Drink("Dr Pepper", " ");

        // act
        double price = drink.getPrice();

        // assert
        assertEquals(0.0, price);
    }


}