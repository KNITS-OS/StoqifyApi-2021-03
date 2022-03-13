package com.knits.product.entity;

import lombok.Data;
import java.util.Date;
import javax.persistence.*;
import lombok.NoArgsConstructor;

/**
 * This is a entity which will handle delivery data
 * @author Soumen Banerjee
 */
@Data
@Entity
@Table(name = "delivery")
@NoArgsConstructor
public class DeliveryProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;

    @OneToOne
    @JoinTable(name = "asn_link_delivery", joinColumns = @JoinColumn(name = "delivery_delivery_id"),
            inverseJoinColumns = @JoinColumn(name = "advanced_shipping_notice_id"))
    private AdvancedShippingNotice asn;

    @OneToOne
    @JoinTable(name = "supervisor_link_delivery", joinColumns = @JoinColumn(name = "delivery_delivery_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;

    @Column(name = "arrival")
    private Date arrivalTime;

    @Column(name = "start")
    private Date startReceving;

    @Column(name = "end_receving")
    private Date endReceving;

    @Column(name = "time_taken")
    private String totalTimeTaken;

    @Column(name = "comment")
    private String comment;

    @OneToOne
    @JoinTable(name = "delivery_dock_link", joinColumns = @JoinColumn(name = "delivery_delivery_id"),
            inverseJoinColumns = @JoinColumn(name = "delivery_dock_id"))
    private Dock dock;

    @Column(name = "status")
    private String status;

    public DeliveryProgress(Date arrivalTime, Date startReceving, Date endReceving, String totalTimeTaken,
                            String comment, String status) {
        this.arrivalTime = arrivalTime;
        this.startReceving = startReceving;
        this.endReceving = endReceving;
        this.totalTimeTaken = totalTimeTaken;
        this.comment = comment;
        this.status = status;
    }
}
