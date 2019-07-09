package com.shoppingcart;

import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShoppingCartTest {
    private ShoppingCart shoppingCart;
    private final Product doveSoap = new Product("Dove", BigDecimal.valueOf(39.99));
    private final Product axeDeo = new Product("Axe Deo", BigDecimal.valueOf(99.99));

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

        assertEquals(5, shoppingCart.getTotalProductSize());
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

        assertEquals(8, shoppingCart.getTotalProductSize());
        assertEquals(BigDecimal.valueOf(319.92), shoppingCart.getTotalPrice());
        assertUnitPrices();
        assertProductNames();
    }

    @Test
    public void shouldCalculateTaxRateOfShoppingCartWithMultipleItems() {
        BigDecimal salesTaxRate = BigDecimal.valueOf(12.5);

        shoppingCart.add(doveSoap);
        shoppingCart.add(doveSoap);
        shoppingCart.add(axeDeo);
        shoppingCart.add(axeDeo);

        List<Product> doveProducts = shoppingCart.filterProducts("Dove", BigDecimal.valueOf(39.99));
        List<Product> axeDeoProducts = shoppingCart.filterProducts("Axe Deo", BigDecimal.valueOf(99.99));

        for(Product doveProduct: doveProducts) {
            assertEquals("Dove", doveProduct.getName());
            assertEquals(BigDecimal.valueOf(39.99), doveProduct.getUnitPrice());
        }

        for(Product axeDeoProduct: axeDeoProducts) {
            assertEquals("Axe Deo", axeDeoProduct.getName());
            assertEquals(BigDecimal.valueOf(99.99), axeDeoProduct.getUnitPrice());
        }

        assertEquals(BigDecimal.valueOf(35), shoppingCart.calculateTotalTax(salesTaxRate));
        assertEquals(BigDecimal.valueOf(314.96), shoppingCart.getPriceWithTaxes(salesTaxRate));
    }

    @Test
    public void shouldBeEmptyIfNoProductsAreAddedToCart() {
        assertEquals(0, shoppingCart.getTotalProductSize());
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