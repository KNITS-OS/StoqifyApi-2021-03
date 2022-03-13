package com.knits.product.dto;

import lombok.Data;
import java.util.Date;
import lombok.AllArgsConstructor;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * This is a Data Transfer Object class for delivery data
 * @author Soumen Banerjee
 */
@Data
@AllArgsConstructor
public class DeliveryProgressDto {

    private Long deliveryId;

    private Long deliveryAssignId;

    @JsonFormat(pattern = "dd.mm.YYYY hh:mm")
    private Date startReceving;

    @JsonFormat(pattern = "dd.mm.YYYY hh:mm")
    private Date endReceving;

    private String totalTimeTaken;
    private String comment;

    @Pattern(regexp = "^[S|E|F]$", message = "Invalid Status")
    private String status;
}
