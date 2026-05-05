package com.example;

import static org.junit.Assert.*;
import org.junit.Test;

public class AppTest {

    // Verify greet() returns a personalized message
    @Test
    public void testGreetWithName() {
        App app = new App();
        assertEquals("Hello, Wrong Name!", app.greet("Jenkins"));
    }

    // Verify greet() handles null input gracefully
    @Test
    public void testGreetWithNull() {
        App app = new App();
        assertEquals("Hello, World!", app.greet(null));
    }

    // Verify greet() handles blank/whitespace input
    @Test
    public void testGreetWithEmpty() {
        App app = new App();
        assertEquals("Hello, World!", app.greet("  "));
    }

    // Verify add() with positive numbers
    @Test
    public void testAdd() {
        App app = new App();
        assertEquals(5, app.add(2, 3));
    }

    // Verify add() with negative numbers
    @Test
    public void testAddNegative() {
        App app = new App();
        assertEquals(-1, app.add(2, -3));
    }
}