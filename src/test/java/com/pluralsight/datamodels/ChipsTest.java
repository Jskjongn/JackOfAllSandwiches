package com.pluralsight.datamodels;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChipsTest {

    @Test
    public void chips_should_getChipsName() {
        // arrange
        Chips chips = new Chips("Lay's BBQ");

        // act
        String name = chips.getName();

        // arrange
        assertEquals("Lay's BBQ", name);

    }

    @Test
    public void chips_should_getChipsPrice() {
        // arrange
        Chips chips = new Chips("Lay's BBQ");

        // act
        double price = chips.getPrice();

        // arrange
        assertEquals(1.50, price);
    }
}