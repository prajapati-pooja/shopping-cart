package com.shoppingcart;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ShoppingCartTest {
    private ShoppingCart shoppingCart;
    private final Product doveSoap = new Product("Dove", BigDecimal.valueOf(39.99));

    @Before
    public void setUp() {
        shoppingCart = new ShoppingCart();
    }

    @Test
    public void shouldAddProductsToTheShoppingCart() {
        shoppingCart.add(doveSoap);
        shoppingCart.add(doveSoap);
        shoppingCart.add(doveSoap);
        shoppingCart.add(doveSoap);
        shoppingCart.add(doveSoap);

        assertEquals(5, shoppingCart.getTotalProduct());
        assertEquals(BigDecimal.valueOf(199.95), shoppingCart.getTotalPrice());

        for (BigDecimal unitPrice : shoppingCart.getAllUnitPrices()) {
            assertEquals(BigDecimal.valueOf(39.99), unitPrice);
        }
    }

    @Test
    public void shouldBeEmptyIfNoProductsAreAddedToCart() {
        assertEquals(0, shoppingCart.getTotalProduct());
    }

}