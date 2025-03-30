package ru.supcm.foodish.order;

import java.util.List;

public record AddOrderRequest(String clientPhone, List<String> dishes, String commentary, Order.PaymentType payment)  {}
