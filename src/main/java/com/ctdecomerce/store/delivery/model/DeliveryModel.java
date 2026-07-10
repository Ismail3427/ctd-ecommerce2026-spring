package com.ctdecomerce.store.delivery.model;

import com.ctdecomerce.store.orders.model.OrdersModel;
import com.ctdecomerce.store.retailers.model.RetailersModel;
import jakarta.persistence.*;
import lombok.*;

import javax.xml.stream.Location;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "deliveries")
public class DeliveryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column()
    private boolean completed = false;

    @OneToOne()
    @JoinColumn()
    private OrdersModel order;

    @ManyToOne()
    @JoinColumn()
    private RetailersModel retailer;

    @Column()
    private String shippingAddress;

    @ManyToOne()
    @JoinColumn()
    private LocationModel productStartAddress;
}
