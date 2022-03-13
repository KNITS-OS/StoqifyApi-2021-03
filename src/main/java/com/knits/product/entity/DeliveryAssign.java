package com.knits.product.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "delivery_assign")
public class DeliveryAssign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private String status;

    @Column(name = "arrival_time")
    private Date arrivalTime;

    @OneToOne
    @JoinTable(name = "asn_link_delivery", joinColumns = @JoinColumn(name = "delivery_delivery_id"),
            inverseJoinColumns = @JoinColumn(name = "advanced_shipping_notice_id"))
    private AdvancedShippingNotice asn;

    @OneToOne
    @JoinTable(name = "supervisor_link_delivery", joinColumns = @JoinColumn(name = "delivery_delivery_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;

    @OneToOne
    @JoinTable(name = "delivery_dock_link", joinColumns = @JoinColumn(name = "delivery_delivery_id"),
            inverseJoinColumns = @JoinColumn(name = "delivery_dock_id"))
    private Dock dock;

}
