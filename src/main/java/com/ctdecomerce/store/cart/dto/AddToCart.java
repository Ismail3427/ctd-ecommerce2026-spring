package com.ctdecomerce.store.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddToCart {
    private String product;
    private String cart;
    private String userId;
}
