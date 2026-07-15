package com.ctdecomerce.store.product.dto;

import lombok.*;

import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private UUID id;
    private String name;
    private OwnerDTO owner;
    private double priceInCents;
    private boolean discounted;
    private double ogPriceInCents;
<<<<<<< HEAD
    private boolean isShowing;
    private boolean isAvailable;
=======
    private int stock;
>>>>>>> e857963de0bce7de6f17dac3f95fd955ab9f7a24
}
