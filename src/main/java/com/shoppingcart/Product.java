package com.shoppingcart;

import java.math.BigDecimal;

class Product {
    private String name;
    private BigDecimal unitPrice;

    Product(String name, BigDecimal unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
    }

    BigDecimal getUnitPrice() {
        return unitPrice;
    }
}
