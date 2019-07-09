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
        assertUnitPrices();
        assertProductNames();
    }

    @Test
    public void shouldAddAdditionalProductsToTheShoppingCart() {
        shoppingCart.add(doveSoap);
        shoppingCart.add(doveSoap);
        shoppingCart.add(doveSoap);
        shoppingCart.add(doveSoap);
        shoppingCart.add(doveSoap);

        shoppingCart.add(doveSoap);
        shoppingCart.add(doveSoap);
        shoppingCart.add(doveSoap);

        assertEquals(8, shoppingCart.getTotalProduct());
        assertEquals(BigDecimal.valueOf(319.92), shoppingCart.getTotalPrice());
        assertUnitPrices();
        assertProductNames();
    }

    @Test
    public void shouldBeEmptyIfNoProductsAreAddedToCart() {
        assertEquals(0, shoppingCart.getTotalProduct());
    }

    private void assertUnitPrices() {
        for (BigDecimal unitPrice : shoppingCart.getAllUnitPrices()) {
            assertEquals(BigDecimal.valueOf(39.99), unitPrice);
        }
    }

    private void assertProductNames() {
        for (String productName : shoppingCart.getAllProductNames()) {
            assertEquals(productName, "Dove");
        }
    }

}