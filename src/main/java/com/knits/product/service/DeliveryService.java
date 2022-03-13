package com.knits.product.service;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import com.knits.product.entity.*;
import java.util.stream.Collectors;
import java.util.concurrent.TimeUnit;
import com.knits.product.repository.*;
import org.springframework.stereotype.Service;
import com.knits.product.mapper.DeliveryMapper;
import com.knits.product.dto.DeliveryAssignDto;
import com.knits.product.dto.DeliveryProgressDto;
import com.knits.product.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This is a Delivery service class
 * @author Soumen Banerjee
 */
@Service("delivery")
public class DeliveryService {

    private Date start;
    private List<DeliveryProgressDto> deliveryProgressList = new ArrayList<>();

    @Autowired
    private DeliveryMapper deliveryMapper;

    @Autowired
    private DeliveryAssignRepository deliveryAssignRepository;

    @Autowired
    private DeliveryProgressRepository deliveryProgressRepository;

    /**
     *
     * @return list of all assigned deliveries
     */
    public List<DeliveryAssignDto> getAllAssignedDeliveries() {

        return deliveryAssignRepository.findAll().stream().map(deliveryMapper::toDeliveryAssignDto).collect(Collectors.toList());
    }

    /**
     *
     * @param deliveryAssign requested delivery assigning data
     * @return full list of assigned deliveries data
     */
    public List<DeliveryAssignDto> assignNewDelivery(DeliveryAssignDto deliveryAssign) {

        deliveryAssign.setStatus("C");
        deliveryAssign.setArrivalTime(new Date());

        deliveryAssignRepository.save(deliveryMapper.toDeliveryAssignEntity(deliveryAssign));

        return getAllAssignedDeliveries();
    }

    /**
     *
     * @param deliveryProgressDto requested to fetch current progress of the delivery
     * @return list of all delivery progress
     */
    public List<DeliveryProgressDto> getDeliveryProgress(DeliveryProgressDto deliveryProgressDto) {

        DeliveryAssign getDeliveryAssignData = deliveryAssignRepository
                .findById(deliveryProgressDto.getDeliveryAssignId())
                .orElseThrow(() -> new UserException("Delivery Assign #" + deliveryProgressDto.getDeliveryAssignId() + " not found"));

        if(deliveryProgressDto.getStatus().equals("E")) {
            Date end = new Date();

            long diffInMillies = Math.abs(end.getTime() - start.getTime());
            long min = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
            long hours = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            long seconds = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);


            DeliveryProgressDto fetchSingleDeliveryReport =
                    this.deliveryProgressList
                    .stream()
                    .filter(getDeliveryReport -> getDeliveryReport.getDeliveryAssignId().equals(getDeliveryAssignData.getId()))
                    .findAny().orElseThrow(() -> new UserException("Delivery Report #" + getDeliveryAssignData.getId() +
                                    " does not exists"));

            int index = this.deliveryProgressList.indexOf(fetchSingleDeliveryReport);

            this.deliveryProgressList.remove(index);

            fetchSingleDeliveryReport.setStatus("E");
            fetchSingleDeliveryReport.setEndReceving(new Date());
            fetchSingleDeliveryReport.setComment("Ended Receving Process");
            fetchSingleDeliveryReport.setTotalTimeTaken(hours + "h: " + min + "m: " + seconds + "s");


            DeliveryProgress updatedDeliveryProgress = deliveryProgressRepository.save(new DeliveryProgress(getDeliveryAssignData.getArrivalTime(),
                    fetchSingleDeliveryReport.getStartReceving(),
                    fetchSingleDeliveryReport.getEndReceving(), fetchSingleDeliveryReport.getTotalTimeTaken(),
                    fetchSingleDeliveryReport.getComment(), fetchSingleDeliveryReport.getStatus()));

            fetchSingleDeliveryReport.setDeliveryId(updatedDeliveryProgress.getDeliveryId());
            this.deliveryProgressList.add(fetchSingleDeliveryReport);

            return this.deliveryProgressList;

        } else if(deliveryProgressDto.getStatus().equals("S")) {
            this.start = new Date();
            this.deliveryProgressList.add(new DeliveryProgressDto(0L, getDeliveryAssignData.getId(), this.start,
                    null, null, "Started", "S"));
        }

        return this.deliveryProgressList;
    }

    /**
     *
     * @return full list of stored delivery progress
     */
    public List<DeliveryProgressDto> getAllProgressReport() {
        return this.deliveryProgressRepository.findAll()
                .stream()
                .map(deliveryMapper::toDeliveryProgressDto)
                .distinct()
                .collect(Collectors.toList());
    }
}
