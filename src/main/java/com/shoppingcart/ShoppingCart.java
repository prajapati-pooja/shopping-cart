package com.shoppingcart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ShoppingCart {
    private List<Product> products = new ArrayList<>();

    void add(Product product) {
        products.add(product);
    }

    BigDecimal getTotalPrice() {
        Stream<BigDecimal> allUnitPrices = products.stream().map(Product::getUnitPrice);
        BigDecimal totalPrice = allUnitPrices.reduce(BigDecimal.valueOf(0) , BigDecimal::add);
        return totalPrice.setScale(2, RoundingMode.HALF_UP);
    }

    int getTotalProduct() {
        return products.size();
    }

    List<BigDecimal> getAllUnitPrices() {
        return products.stream().map(Product::getUnitPrice).collect(Collectors.toList());
    }
}
