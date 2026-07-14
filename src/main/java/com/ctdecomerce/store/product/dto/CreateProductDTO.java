package com.ctdecomerce.store.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateProductDTO {
    private String name;
    private String description;
    private int priceInCents;
    private int stock;
    private Boolean isAvailable;
    private Boolean isShowing;
    private String ownerId;


}
