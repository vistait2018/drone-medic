package com.vista.it.dronemedic.service;

import com.vista.it.dronemedic.dao.MedicationRepository;
import com.vista.it.dronemedic.model.Drone;
import com.vista.it.dronemedic.model.Medication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationService {
    @Autowired
    private MedicationRepository medicationRepository;

    public List<Medication> medications(){
        return (List<Medication>) medicationRepository.findAll();
    }

    public Medication saveMedication(Medication medication){

        return medicationRepository.save(medication);
    }


    public Medication getMedication(Long id) {
       return  medicationRepository.findById(id)
               .orElse(null);

    }

    public void  saveAll(List<Medication> medications){
        medicationRepository.saveAll(medications);
    }

    public void updateMedication(Medication medication,Long id){
        Optional<Medication> medicationToUpdate = medicationRepository.findById(id);
        //Optional<LoadState> loadState = loadStateRepository.findById(id);
        if (medicationToUpdate.isPresent()) {
            medicationToUpdate.get().setWeight(medication.getWeight());
            medicationToUpdate.get().setImagePath(medication.getImagePath());
            medicationToUpdate.get().setCode(medication.getCode());
            medicationToUpdate.get().setName(medication.getName());
        }

        medicationRepository.save(medicationToUpdate.get());
    }

    public void deleteMedication(Long id){
        Optional<Medication> medicationToDelete = medicationRepository.findById(id);
        if(!medicationToDelete.isPresent()){
            throw new IllegalStateException("Medication with given Id not found");
        }
        medicationRepository.delete(medicationToDelete.get());

    }
}
