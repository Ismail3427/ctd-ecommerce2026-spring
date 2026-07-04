package com.ctdecomerce.store.cart.service;

import com.ctdecomerce.store.cart.dto.AddToCart;
import com.ctdecomerce.store.cart.dto.CreateCart;
import com.ctdecomerce.store.cart.model.CartModel;
import com.ctdecomerce.store.cart.repository.CartRepo;
import com.ctdecomerce.store.product.model.ProductModel;
import com.ctdecomerce.store.product.repository.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.lang.IllegalArgumentException;

import java.util.UUID;

@Service
public class CartService {
    private final CartRepo cartRepo;
    private final ProductRepo productRepo;

    public CartService(CartRepo cartRepo, ProductRepo productRepo) {
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
    }

    @Transactional
    public CartModel createCart(CreateCart createCart) {
        CartModel cartModel = new CartModel();
        cartModel.setUserId(createCart.getUserId());
        return cartRepo.save(cartModel);
    }

    @Transactional
    public CartModel addToCart(AddToCart addToCart) {
        ProductModel product = null;
        if (addToCart.getProduct() != null && !addToCart.getProduct().isBlank()) {
            try {
                product = productRepo.findById(UUID.fromString(addToCart.getProduct())).orElse(null);
            } catch (IllegalArgumentException ignored) {
                product = null;
            }
        }

        if (addToCart.getCart() != null && !addToCart.getCart().isBlank()) {
            CartModel cartById = null;
            try {
                cartById = cartRepo.findById(UUID.fromString(addToCart.getCart())).orElse(null);
            } catch (IllegalArgumentException ignored) {
                cartById = null;
            }

            if (cartById != null) {
                if (cartById.getProducts() == null) {
                    cartById.setProducts(new java.util.ArrayList<>());
                }
                if (product != null) {
                    cartById.getProducts().add(product);
                }
                return cartRepo.save(cartById);
            }
        }

        CartModel userCart = null;
        if (addToCart.getUserId() != null && !addToCart.getUserId().isBlank()) {
            userCart = cartRepo.findByUserId(addToCart.getUserId());
        }

        if (userCart != null) {
            if (userCart.getProducts() == null) {
                userCart.setProducts(new java.util.ArrayList<>());
            }
            if (product != null) {
                userCart.getProducts().add(product);
            }
            return cartRepo.save(userCart);
        }

        CartModel newCart = createCart(new CreateCart(addToCart.getUserId()));
        if (newCart.getProducts() == null) {
            newCart.setProducts(new java.util.ArrayList<>());
        }
        if (product != null) {
            newCart.getProducts().add(product);
        }
        return cartRepo.save(newCart);
    }
}
