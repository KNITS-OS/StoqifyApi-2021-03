package com.knits.product.entity;

import javax.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "asn_link_delivery")
public class ASNLinkDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "delivery_delivery_id")
    private Long deliveryId;

    @Column(name = "advanced_shipping_notice_id")
    private Long asnId;

}
