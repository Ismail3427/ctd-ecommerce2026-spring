package com.ctdecomerce.store.retailers.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductDto {
    private String name;
    private String description;
    private int price_in_cents;
    private UUID owner_id;
}
