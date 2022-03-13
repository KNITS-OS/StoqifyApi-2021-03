package com.knits.product.entity;

import javax.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "supervisor_link_delivery")
public class UserLinkDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "delivery_delivery_id")
    private Long deliveryId;

    @Column(name = "user_id")
    private Long userId;
}
