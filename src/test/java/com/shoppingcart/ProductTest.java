package com.shoppingcart;

import org.junit.Test;
import java.math.BigDecimal;
import static org.junit.Assert.*;

public class ProductTest {
    @Test
    public void shouldGetUnitPriceOfProduct() {
        Product product = new Product("Dove", BigDecimal.valueOf(15.65));

        assertEquals(product.getUnitPrice(), BigDecimal.valueOf(15.65));
    }
}