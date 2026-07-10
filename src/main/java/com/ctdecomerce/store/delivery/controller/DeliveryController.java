package com.ctdecomerce.store.delivery.controller;

import com.ctdecomerce.store.delivery.dto.OrderIdReqeust;
import com.ctdecomerce.store.delivery.model.DeliveryModel;
import com.ctdecomerce.store.delivery.service.DeliveryService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("deliveryController")
@RequestMapping("/delivery")
@Setter
@AllArgsConstructor
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping("/order-id")
    public ResponseEntity<DeliveryModel> getDeliveryById(@RequestBody OrderIdReqeust orderIdReqeust) {
        return new ResponseEntity<>(deliveryService.getDeliveryByOrderId(orderIdReqeust), HttpStatus.OK);
    }
}
