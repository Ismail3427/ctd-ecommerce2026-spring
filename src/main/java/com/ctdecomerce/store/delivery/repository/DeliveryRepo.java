package com.ctdecomerce.store.delivery.repository;

import com.ctdecomerce.store.delivery.model.DeliveryModel;
import com.ctdecomerce.store.orders.model.OrdersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DeliveryRepo extends JpaRepository<DeliveryModel, UUID> {
    DeliveryModel getDeliveryModelByOrder(OrdersModel order);

    List<DeliveryModel> findByRetailerId(UUID retailer_id);
}
