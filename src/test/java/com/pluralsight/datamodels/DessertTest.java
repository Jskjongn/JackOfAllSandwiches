package com.pluralsight.datamodels;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DessertTest {

    @Test
    public void dessert_should_getDessertName() {
        // arrange
        Dessert dessert = new Dessert("Vanilla Ice Cream Sandwich");

        // act
        String name = dessert.getName();

        // arrange
        assertEquals("Vanilla Ice Cream Sandwich", name);

    }

    @Test
    public void dessert_should_getDessertPrice() {
        // arrange
        Dessert dessert = new Dessert("Vanilla Ice Cream Sandwich");

        // act
        double price = dessert.getPrice();

        // arrange
        assertEquals(1.50, price);
    }

}