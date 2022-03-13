package com.knits.product.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import com.knits.product.dto.DeliveryAssignDto;
import org.springframework.http.ResponseEntity;
import com.knits.product.entity.DeliveryAssign;
import com.knits.product.dto.DeliveryProgressDto;
import com.knits.product.service.DeliveryService;
import org.springframework.web.bind.annotation.*;

/**
 * This is REST API controller to handle delivery
 * @author Soumen Banerjee
 */
@RestController
@AllArgsConstructor
@RequestMapping("api/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping("assigns")
    public ResponseEntity<List<DeliveryAssignDto>> getAllDeliveryAssigns() {
        return ResponseEntity.ok().body(deliveryService.getAllAssignedDeliveries());
    }

    @PostMapping
    public ResponseEntity<List<DeliveryAssignDto>> assignDelivery(@RequestBody DeliveryAssignDto deliveryAssign) {
        return ResponseEntity.ok().body(deliveryService.assignNewDelivery(deliveryAssign));
    }

    @PostMapping("progress")
    public ResponseEntity<List<DeliveryProgressDto>> getProgress(@Valid @RequestBody DeliveryProgressDto deliveryProgressDto) {
        return ResponseEntity.ok().body(deliveryService.getDeliveryProgress(deliveryProgressDto));
    }

    @GetMapping("report")
    public ResponseEntity<List<DeliveryProgressDto>> getAllProgresReport() {
        return ResponseEntity.ok().body(deliveryService.getAllProgressReport());
    }

}
