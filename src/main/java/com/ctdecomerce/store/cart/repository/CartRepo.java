package com.ctdecomerce.store.cart.repository;

import com.ctdecomerce.store.cart.model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepo extends JpaRepository<CartModel, UUID> {
    CartModel findByUserId(String userId);
}
