package com.knits.product.mapper;

import com.knits.product.dto.DeliveryAssignDto;
import com.knits.product.dto.DeliveryProgressDto;
import com.knits.product.entity.DeliveryAssign;
import com.knits.product.entity.DeliveryProgress;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * This is a delivery data mapper
 * @author Soumen Banerjee
 */
@Mapper(componentModel = "spring")
public interface DeliveryMapper {

    DeliveryProgressDto toDeliveryProgressDto(DeliveryProgress deliveryProgress);
    DeliveryProgress toDeliveryProgressEntity(DeliveryProgressDto deliveryProgressDto);

    DeliveryAssignDto toDeliveryAssignDto(DeliveryAssign deliveryAssign);
    DeliveryAssign toDeliveryAssignEntity(DeliveryAssignDto deliveryAssignDto);

}
