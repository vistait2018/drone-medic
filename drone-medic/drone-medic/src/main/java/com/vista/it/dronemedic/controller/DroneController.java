package com.vista.it.dronemedic.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vista.it.dronemedic.constants.Model;
import com.vista.it.dronemedic.dao.DroneRepository;
import com.vista.it.dronemedic.model.Drone;
import com.vista.it.dronemedic.model.DroneHistory;
import com.vista.it.dronemedic.model.Medication;
import com.vista.it.dronemedic.service.DroneHistoryService;
import com.vista.it.dronemedic.service.DroneService;
import com.vista.it.dronemedic.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/")
public class DroneController {

    @Autowired
    private DroneService droneService;

    @Autowired
    private DroneHistoryService droneHistoryService;



    @GetMapping("drones")
    public ResponseEntity<List<Drone>> getDrone() {
        try {
            List<Drone> drones = droneService.allDrones();
            if (drones.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(drones, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println("Error"+ ex.fillInStackTrace());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("drones/{droneId}")
    public ResponseEntity<Drone> getDrone(@PathVariable Long droneId) {
        try {
            Drone foundDrone = droneService.findDrone(droneId).orElse(null);
            if (foundDrone == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(foundDrone, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/add-drone")
    public ResponseEntity<String> registerDrone(@Valid @RequestBody Drone drone) {

        try {
            Drone redisteredDrone = droneService.registerDrone(drone);

            return new ResponseEntity<>("Drone registered Successfully", HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Error: Suggestions: Drone Serial no must be Unique and Weight be less than 500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("drone/{droneId}/check-medication")
    public ResponseEntity<List<Medication>> getLoadedMedication(@PathVariable Long droneId) {
        try {
            List<Medication> medications = droneService.checkMedicationInADrone(droneId);
            if (medications.isEmpty() || medications == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(medications, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


      @PostMapping("drone/{droneId}/add-medications/{medicationId}")
         public ResponseEntity<String> loadMedications(
            @PathVariable
             final  Long droneId, @PathVariable final Long medicationId) {
        try {

            Optional<Drone> droneToLoad = droneService.findDrone(droneId);
            if (droneToLoad.isPresent()) {
                String droneWeight = droneToLoad.get().getModel();
                if (droneService.checkWeight(droneWeight) == 0) {
                    return new ResponseEntity<>("Drone's weight cannot be zero", HttpStatus.NOT_IMPLEMENTED);
                }
                if (droneToLoad.get().getWeight() == droneService.checkWeight(droneWeight)) {
                    droneService.saveMedication(droneId, medicationId);
                }

            }
           return new ResponseEntity<>("Medication saved", HttpStatus.CREATED);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("drone-to-land")
    public ResponseEntity<List<Drone>> landingDrones() {
        try {
            List<Drone> dronesForLanding = droneService.checkDronesForLanding();
            return new ResponseEntity<>(dronesForLanding, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("drone-to-load")
    public ResponseEntity<List<Drone>> loadingDrones() {
        try {
            List<Drone> dronesForLoading = droneService.checkAvailableDroneForLoading();
            return new ResponseEntity<>(dronesForLoading, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/medical/upload-photo")
    public ResponseEntity<Object> uploadMedicalPhoto(@RequestParam("File") MultipartFile file,Long medicalId) {
        try{
                   droneService.savePhoto(file,medicalId);

            return new ResponseEntity<>("Medical Image Uploaded", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("drone-histories")
    public ResponseEntity<List<DroneHistory>> getAllDroneHistories(){
        try{
           List<DroneHistory> droneHistories = droneHistoryService.droneHistories();

            return new ResponseEntity<List<DroneHistory>>(droneHistories, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<List<DroneHistory>>((List<DroneHistory>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("drone-history/{droneId}")
    public ResponseEntity<List<DroneHistory>> getDroneHistoriesById(@PathVariable Long droneId){
        try{
            List<DroneHistory> droneHistories = droneHistoryService.droneHistoryForAParticularDrone(droneId);


            return  new ResponseEntity<List<DroneHistory>>(droneHistories,HttpStatus.OK);
        }catch(Exception ex){
            return  new ResponseEntity< List<DroneHistory>>((List<DroneHistory>) null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

@GetMapping("drone/{droneId}/battery-level")
    public ResponseEntity<Integer> getDroneBatteryLevel(@PathVariable Long droneId){
        try{

            //// here
            int batteryLevel =0;
            Optional<Drone> drone =  droneService.findDrone(droneId);
            System.out.println("drone battery"+drone.get().getBatteryCapacity());
            if(drone.isPresent()) {
                batteryLevel =  drone.get().getBatteryCapacity();
            }

            return new ResponseEntity<Integer>(batteryLevel, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Integer>((Integer) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
