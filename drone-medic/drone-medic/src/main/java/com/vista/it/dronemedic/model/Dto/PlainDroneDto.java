package com.vista.it.dronemedic.model.Dto;

import com.vista.it.dronemedic.model.Drone;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlainDroneDto {
    private Long Id;
    private int batteryCapacity;
    private String droneState;
    private String model;
    private String batteryState;
    private String serialNo;
    private int weight;


    public static PlainDroneDto from(Drone drone){
        PlainDroneDto plainDroneDto = new PlainDroneDto();
        plainDroneDto.setId(drone.getId());
        plainDroneDto.setBatteryCapacity(drone.getBatteryCapacity());
        plainDroneDto.setBatteryState(drone.getBatteryState());
        plainDroneDto.setModel(drone.getModel());
        plainDroneDto.setWeight(drone.getWeight());
        plainDroneDto.setSerialNo(drone.getSerialNo());

        return plainDroneDto;
    }
}
