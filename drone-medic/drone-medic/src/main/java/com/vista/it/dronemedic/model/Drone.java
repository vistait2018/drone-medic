package com.vista.it.dronemedic.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vista.it.dronemedic.model.Dto.DroneDto;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@Table(name="drone")
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "id",unique= true, nullable = false)
    private Long Id;
    private int batteryCapacity;
    private String droneState;
    private String model;
    private String batteryState;



    @Size( max = 100,message = "Serial No must be between 3 and 100")
    @Column(unique = true)
    private String serialNo;

    @Max(value=500, message="Maximum number for weight is 500gr")
     private int weight;



    @JsonIgnoreProperties(value = {"drone", "hibernateLazyInitializer"})
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "drone_id")
    private List<Medication> medications = new ArrayList<>() ;



    public void addMedication(Medication medication){
       // medication.setId(this.getId());
        medications.add(medication);
    }

    public void removeMedication(Medication medication){
        medications.remove(medication);
    }

    public static Drone from(DroneDto droneDto){
        Drone drone = new Drone();
        drone.setDroneState(droneDto.getDroneState());
        drone.setModel(droneDto.getModel());
        drone.setWeight(droneDto.getWeight());
        drone.setSerialNo(droneDto.getSerialNo());
          drone.setBatteryCapacity(droneDto.getBatteryCapacity());
              drone.setBatteryState(droneDto.getBatteryState());

        return drone;
    }
}
