package com.vista.it.dronemedic.controller;

import com.vista.it.dronemedic.model.Medication;
import com.vista.it.dronemedic.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/medications")
public class MedicationController {
    @Autowired
    private MedicationService medicationService;

    @GetMapping("all-medications")
    public ResponseEntity<List<Medication>> getAllMedication(){
        try{
            List<Medication>  medications =medicationService.medications();
            return new  ResponseEntity<List<Medication>>(medications, HttpStatus.OK);

        }catch(Exception ex){
            return new ResponseEntity<List<Medication>>((List<Medication>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("add-medication")
    public  ResponseEntity<String> addMedical(@Valid Medication medication){
        try{

            medicationService.saveMedication(medication);
            return new ResponseEntity<String>("Medical Added Successfully",HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<String>((String) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("medication/{id}")
    public  ResponseEntity<Medication> getMedical(@PathVariable Long id){
        try{

            Medication m = medicationService.getMedication(id);
            return new ResponseEntity<Medication>(m,HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<Medication>((Medication) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
