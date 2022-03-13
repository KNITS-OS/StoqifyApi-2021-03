package com.knits.product.entity;

import javax.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "delivery_dock_link")
public class DeliveryDockLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "delivery_delivery_id")
    private Long deliveryId;

    @Column(name = "delivery_dock_id")
    private Long deliveryDockId;
}
