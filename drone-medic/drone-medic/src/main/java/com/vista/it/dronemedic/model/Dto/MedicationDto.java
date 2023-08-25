package com.vista.it.dronemedic.model.Dto;
import com.vista.it.dronemedic.model.Medication;
import lombok.Data;
import java.util.Objects;

@Data
public class MedicationDto {

    private Long Id;
    private String Name;
    private Integer weight;
    private String code;
    private String imagePath;
    private PlainDroneDto plainDroneDto;

    public static MedicationDto from(Medication medication){
        MedicationDto medicationDto = new MedicationDto();
        medicationDto.setId(medication.getId());
        medicationDto.setImagePath(medication.getImagePath());
        medicationDto.setWeight(medication.getWeight());
        medicationDto.setCode(medication.getCode());
        medicationDto.setName(medication.getName());
        if(Objects.nonNull(medication.getDrone()))
            medicationDto.setPlainDroneDto(PlainDroneDto.from(medication.getDrone()));
        return medicationDto;
    }
}
