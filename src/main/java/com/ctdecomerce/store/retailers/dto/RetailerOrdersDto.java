package com.ctdecomerce.store.retailers.dto;


import com.ctdecomerce.store.orders.model.OrdersModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RetailerOrdersDto {
    private UUID orderId;
    private String userName;
    private String userEmail;
    private Boolean completed;
    private List<OrderItemDto> items;

}
