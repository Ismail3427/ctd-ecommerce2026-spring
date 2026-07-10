package com.ctdecomerce.store.delivery.dto;

import lombok.*;

import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateDeliveryDTO {
    private UUID orderId;
    private UUID retailerId;
}
