package com.ctdecomerce.store.delivery.repository;

import com.ctdecomerce.store.delivery.model.DeliveryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryRepo extends JpaRepository<DeliveryModel, UUID> {
}
