package com.ctdecomerce.store.retailers.repository;

import com.ctdecomerce.store.retailers.model.RetailersModel;
import com.ctdecomerce.store.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RetailersRepo extends JpaRepository<RetailersModel, UUID> {
    RetailersModel findRetailerByUser(UserModel user);
}
