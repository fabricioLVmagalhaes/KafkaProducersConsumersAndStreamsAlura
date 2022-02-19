package br.fabricio.ecommerce;

import java.math.BigDecimal;

public class Order {
    private final String orderID;
    private final BigDecimal amount;
    private final String email;

    public Order(String orderID, BigDecimal value, String email) {
        this.email = email;
        this.orderID = orderID;
        this.amount = value;
    }


}