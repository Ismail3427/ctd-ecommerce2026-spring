package com.ctdecomerce.store.cart.service;

import com.ctdecomerce.store.cart.dto.AddToCart;
import com.ctdecomerce.store.cart.dto.CreateCart;
import com.ctdecomerce.store.cart.model.CartModel;
import com.ctdecomerce.store.cart.repository.CartRepo;
import com.ctdecomerce.store.product.model.ProductModel;
import com.ctdecomerce.store.product.repository.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartService {
    private final CartRepo cartRepo;
    private final ProductRepo productRepo;

    public CartService (CartRepo cartRepo, ProductRepo productRepo) {
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
        System.out.println(addToCart.getProduct());
        ProductModel product = productRepo.findById(UUID.fromString(addToCart.getProduct())).orElse(null);
        try {
            CartModel cart = cartRepo.findById(UUID.fromString(addToCart.getCart())).orElse(null);
            assert cart != null;
            cart.getProducts().add(product);
            return cartRepo.save(cart);
        } catch (NullPointerException e) {
            CartModel newCart = createCart(new CreateCart(addToCart.getUserId()));
            newCart.getProducts().add(product);
            return cartRepo.save(newCart);
        }
    }
}
