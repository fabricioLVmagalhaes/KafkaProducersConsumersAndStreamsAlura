package br.com.fabricio.ecommerce;

import java.math.BigDecimal;

public class Order {
    private final String userId, orderID;
    private final BigDecimal amount;
    private final String email;

    public Order(String userId, String orderID, BigDecimal value, String email) {
        this.userId = userId;
        this.orderID = orderID;
        this.amount = value;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }
}