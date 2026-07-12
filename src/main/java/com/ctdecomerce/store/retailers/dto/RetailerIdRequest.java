package com.ctdecomerce.store.retailers.dto;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RetailerIdRequest {
    private UUID retailer_id;
}
