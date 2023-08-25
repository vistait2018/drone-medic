package com.vista.it.dronemedic.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Data
@Entity
public class DroneHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private Long droneId;
    private int batteryState;
    private LocalDateTime time;
    private LocalDate date;
    private int weight;
    private String model;
    private String droneState;
    private String serialNo;


}
