package com.ctdecomerce.store.delivery.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderIdReqeust {
    private String orderId;
}
