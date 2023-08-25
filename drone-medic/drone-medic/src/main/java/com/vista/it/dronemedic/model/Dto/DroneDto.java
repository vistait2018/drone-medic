package com.vista.it.dronemedic.model.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vista.it.dronemedic.model.Drone;
import com.vista.it.dronemedic.model.Medication;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DroneDto {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "id",unique= true, nullable = false)
    private Long Id;
    private int batteryCapacity;
    private String droneState;
    private String model;
    private String batteryState;
    private String serialNo;
    private int weight;


    private List<MedicationDto> medicationsDto = new ArrayList<>() ;
    public static DroneDto from(Drone drone){
        DroneDto droneDto = new DroneDto();
        droneDto.setId(drone.getId());
        droneDto.setBatteryCapacity(drone.getBatteryCapacity());
        droneDto.setBatteryState(drone.getBatteryState());
        droneDto.setModel(drone.getModel());
        droneDto.setWeight(drone.getWeight());
        droneDto.setSerialNo(drone.getSerialNo());

        droneDto.setMedicationsDto(drone.getMedications().stream().map(MedicationDto::from).collect(Collectors.toList()));
        return droneDto;
    }
}
