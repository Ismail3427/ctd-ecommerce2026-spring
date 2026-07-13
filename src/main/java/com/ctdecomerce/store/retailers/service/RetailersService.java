package com.ctdecomerce.store.retailers.service;

import com.ctdecomerce.store.delivery.model.DeliveryModel;
import com.ctdecomerce.store.delivery.repository.DeliveryRepo;
import com.ctdecomerce.store.orders.model.OrdersModel;
import com.ctdecomerce.store.orders.repository.OrdersRepo;
import com.ctdecomerce.store.product.dto.EditNameReqDto;
import com.ctdecomerce.store.product.model.ProductModel;
import com.ctdecomerce.store.product.repository.ProductRepo;
import com.ctdecomerce.store.retailers.dto.*;
import com.ctdecomerce.store.retailers.mappers.OrderMapper;
import com.ctdecomerce.store.retailers.model.RetailersModel;
import com.ctdecomerce.store.retailers.repository.RetailersRepo;
import com.ctdecomerce.store.user.model.UserModel;
import com.ctdecomerce.store.user.repository.UserRepo;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.AccountLink;
import com.stripe.model.Product;
import com.stripe.param.AccountCreateParams;
import com.stripe.param.AccountLinkCreateParams;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@Setter
@Getter
@ToString
public class RetailersService {
    private final RetailersRepo retailersRepo;
    private final UserRepo userRepo;
    private final OrdersRepo ordersRepo;
    private final DeliveryRepo deliveryRepo;
    private final OrderMapper orderMapper;
    private final ProductRepo productRepo;

    public RetailersService(RetailersRepo retailersRepo, UserRepo userRepo, OrdersRepo ordersRepo, DeliveryRepo deliveryRepo, OrderMapper orderMapper, ProductRepo productRepo) {
        this.retailersRepo = retailersRepo;
        this.userRepo = userRepo;
        this.ordersRepo = ordersRepo;
        this.deliveryRepo = deliveryRepo;
        this.orderMapper = orderMapper;
        this.productRepo = productRepo;
    }

    @Transactional
    public ConnectedAccountDTO createNewRetailer(ConnectedAccountRequest connectedAccountRequest) throws StripeException {
        AccountCreateParams accountCreateParams = AccountCreateParams
                .builder()
                .putMetadata("name", connectedAccountRequest.getName())
                .putMetadata("userId", connectedAccountRequest.getUserId())
                .setType(AccountCreateParams.Type.EXPRESS)
                .build();
        Account account = Account.create(accountCreateParams);

        AccountLinkCreateParams accountLinkCreateParams = AccountLinkCreateParams.builder()
                .setAccount(account.getId())
                .setRefreshUrl("http://localhost:3000")
                .setReturnUrl("http://localhost:3000")
                .setType(AccountLinkCreateParams.Type.ACCOUNT_ONBOARDING)
                .build();

        AccountLink accountLink = AccountLink.create(accountLinkCreateParams);
        return new ConnectedAccountDTO(accountLink.getUrl());
    }

    @Transactional
    public void createAccountToDB(String name, String accountId, String userId) {
        RetailersModel retailersModel = new RetailersModel();
        retailersModel.setName(name);
        retailersModel.setAccountId(accountId);
        UserModel user = userRepo.findUserModelByUserId(userId);
        retailersModel.setUser(user);
        retailersRepo.save(retailersModel);
    }

    @Transactional
    public IsRetailer checkIfRetailer(UserIdRequest userIdRequest) {
        System.out.println(userIdRequest);
        try {
            var user = userRepo.findUserModelByUserId(userIdRequest.getUserId());
            System.out.println(user);
            var retailer = retailersRepo.findRetailerByUser(user);
            System.out.println(retailer);
            System.out.println(user.toString() + ": " + retailer.getUser().toString());
            if (retailer.getUser() == user) {
                return new IsRetailer(true);
            }
            return new IsRetailer(false);
        } catch (NoSuchElementException | NullPointerException e) {
            System.out.println("passed and except");
            return new IsRetailer(false);
        }
    }



    @Transactional
    public RetailersModel findRetailerFromUser(UserIdRequest userIdRequest) {
        try {
            var user = userRepo.findUserModelByUserId(userIdRequest.getUserId());
            var retailer = retailersRepo.findRetailerByUser(user);
            if (retailer.getUser() == user ) {
                return retailer;
            }
            return null;
        } catch(NoSuchElementException | NullPointerException e) {
            return null;
        }
    }

    @Transactional
    public List<OrderItemDto> findRetailerOrders(RetailerIdRequest retailerIdRequest) {
        var retailer = retailersRepo.findById(retailerIdRequest.getRetailer_id()).orElseThrow(() -> new RuntimeException("Retailer not Found"));
        List<DeliveryModel> deliveryModel = deliveryRepo.findByRetailerId(retailer.getId());
        return deliveryModel.
                stream()
                .map(DeliveryModel::getOrder)
                .map(orderMapper::toDto)
                .toList();
    }

    @Transactional
    public ProductModel changeProductName(EditNameReqDto editNameReqDto) {
        var product = productRepo.findById(editNameReqDto.getProduct_id()).orElseThrow();
        product.setName(editNameReqDto.getName());
        return productRepo.save(product);
    }
}