package com.edu.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
