package com.vista.it.dronemedic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name="medication")
public class Medication {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    //@Column(unique = true)
    private Long Id;

    @Pattern(regexp="^[A-Za-z0-9-_]+$",message = "allowed only letters, numbers, ‘-‘, ‘_’")
    private String Name;

    @Range(min = 0,message = "Please add positive numbers Only")
    private Integer weight;

    @Pattern(regexp="^[A-Z]+_[0-9]+$",message = "allowed only upper case letters, underscore and numbers")
    private String code;

    private String imagePath;

     @ManyToOne
     private Drone drone;


}
