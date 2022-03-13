package com.knits.product.dto;

import lombok.Data;
import java.util.Date;
import com.knits.product.entity.Dock;
import com.knits.product.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.knits.product.entity.AdvancedShippingNotice;

@Data
public class DeliveryAssignDto {

    private Long id;
    private String status;
    private User user;
    private Dock dock;

    @JsonFormat(pattern = "dd.mm.YYYY hh:mm")
    private Date arrivalTime;
    
    private AdvancedShippingNotice asn;
}
