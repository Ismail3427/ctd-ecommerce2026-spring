package com.ctdecomerce.store.product.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditAvailableReqDto {
    private UUID product_id;
    private boolean isAvailable;
}
