package com.ctdecomerce.store.cart.model;

import com.ctdecomerce.store.product.model.ProductModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "carts")
public class CartModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String userId;

    @OneToMany()
    @Column()
    private List<ProductModel> products;
}
