package com.shoppingcart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ShoppingCart {
    private List<Product> products = new ArrayList<>();

    void add(Product product) {
        products.add(product);
    }

    BigDecimal getTotalPrice() {
        Stream<BigDecimal> allUnitPrices = getProductsUnitPriceStream();
        BigDecimal totalPrice = allUnitPrices.reduce(BigDecimal.valueOf(0) , BigDecimal::add);
        return totalPrice.setScale(2, RoundingMode.HALF_UP);
    }

    int getTotalProductSize() {
        return products.size();
    }

    List<BigDecimal> getAllUnitPrices() {
        return getProductsUnitPriceStream().collect(Collectors.toList());
    }

    List<String> getAllProductNames() {
        return getProductsNameStream().collect(Collectors.toList());
    }

    BigDecimal calculateTotalTax(BigDecimal salesTaxRate) {
        BigDecimal priceMultipliedByTaxRate = getTotalPrice().multiply(salesTaxRate);
        return priceMultipliedByTaxRate.divide(BigDecimal.valueOf(100), 0, RoundingMode.HALF_UP);
    }

    BigDecimal getPriceWithTaxes(BigDecimal salesTaxRate) {
        return calculateTotalTax(salesTaxRate).add(getTotalPrice());
    }

    List<Product> filterProducts(String name, BigDecimal price) {
        Predicate<Product> criteria = p -> p.getName().equals(name) && p.getUnitPrice().equals(price);
        return products.stream().filter(criteria).collect(Collectors.toList());
    }

    private Stream<BigDecimal> getProductsUnitPriceStream() {
        return products.stream().map(Product::getUnitPrice);
    }

    private Stream<String> getProductsNameStream() {
        return products.stream().map(Product::getName);
    }
}
