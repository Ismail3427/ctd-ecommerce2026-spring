package com.ctdecomerce.store.cart.controller;

import com.ctdecomerce.store.cart.dto.AddToCart;
import com.ctdecomerce.store.cart.dto.CreateCart;
import com.ctdecomerce.store.cart.model.CartModel;
import com.ctdecomerce.store.cart.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("CartController")
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/create")
    public ResponseEntity<CartModel> createNewCart(CreateCart createCart) {
        return new ResponseEntity<>(cartService.createCart(createCart), HttpStatus.CREATED);
    }

    @PostMapping("/add")
    public ResponseEntity<CartModel> addToCart(@RequestBody  AddToCart addToCart) {
        return new ResponseEntity<>(cartService.addToCart(addToCart), HttpStatus.OK);
    }
}
