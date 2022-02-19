package br.com.fabricio.ecommerce;

import java.math.BigDecimal;

public class Order {
    private final String orderID;
    private final BigDecimal amount;
    private final String email;

    public Order(String orderID, BigDecimal value, String email) {
        this.orderID = orderID;
        this.amount = value;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}