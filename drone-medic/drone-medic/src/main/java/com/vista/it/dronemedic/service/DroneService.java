package com.vista.it.dronemedic.service;
import com.vista.it.dronemedic.Exception.DroneHasAlreadyLoadedMicalException;
import com.vista.it.dronemedic.Exception.MedicationNotFound;
import com.vista.it.dronemedic.constants.Charger;
import com.vista.it.dronemedic.constants.Model;
import com.vista.it.dronemedic.constants.State;
import com.vista.it.dronemedic.dao.DroneRepository;
import com.vista.it.dronemedic.model.Drone;
import com.vista.it.dronemedic.model.Medication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DroneService {
    private final DroneRepository droneRepository;
    private final MedicationService medicationService ;

    @Autowired
    public DroneService(DroneRepository droneRepository, MedicationService medicationService) {
        this.droneRepository = droneRepository;
        this.medicationService = medicationService;
    }


    public List<Drone> allDrones() {
        List<Drone> drones = (List<Drone>) droneRepository.findAll();
      return drones;
    }

    public Optional<Drone> findDrone(Long id) {
        return droneRepository.findById(id);
    }

    public Drone registerDrone(Drone drone) {
          if(analyseEnum(drone.getModel(),drone.getDroneState())){
              Drone d = droneRepository.save(drone);
              return d;
          }
       return null;

    }

    public void updateStateAndBatteryInDrone(Long droneId,int batteryCapacity,String state){
         droneRepository.updateDrone(batteryCapacity,state,droneId);
    }
    @Transactional
    public Drone updateDrone(Drone drone, Long id) {
        Optional<Drone> droneToUpdate = droneRepository.findById(id);

        if (droneToUpdate.isPresent() &&
                analyseEnum(drone.getModel(),drone.getDroneState())) {

            droneToUpdate.get().setModel(drone.getModel());
            droneToUpdate.get().setBatteryCapacity(drone.getBatteryCapacity());
            droneToUpdate.get().setSerialNo(drone.getSerialNo());
            droneToUpdate.get().setDroneState(drone.getDroneState());
            droneToUpdate.get().setBatteryCapacity(drone.getBatteryCapacity());
            droneToUpdate.get().setWeight(drone.getWeight());
            return droneRepository.save(droneToUpdate.get());
        }
        return null;


    }
    public Drone saveDrone(Drone drone){
        if(analyseEnum(drone.getModel(),drone.getDroneState())){
            droneRepository.save(drone);
            return drone;
        }
       return null;
    }

    public void destroyDrone(Long id) {
        Optional<Drone> droneToDelete = droneRepository.findById(id);
        droneRepository.delete(droneToDelete.get());
    }

   public List<Drone> saveDrones (List<Drone> drones){
        return (List<Drone>) droneRepository.saveAll(drones);
   }


    public   List<Medication>  checkMedicationInADrone(Long droneId){
        Optional<Drone> checkedDrone = droneRepository.findById(droneId);

        if(checkedDrone.isPresent()) {
            List<Medication> medications = checkedDrone.get().getMedications();
            return medications;
        }
        return null;
    }
   public List<Drone> checkDronesForLanding(){
        List<Drone> returningDrone = this.allDrones().stream()
                .filter(e->e.getDroneState().equals(State.RETURNING.name()) )
                .collect(Collectors.toList());
                if(returningDrone.isEmpty() ){
                    return null;
                }
                return returningDrone;

   }
   @Transactional
   public Drone saveMedication(Long droneId, Long medicationId){

           Drone drone = findDrone(droneId).orElseThrow(()->new RuntimeException());
           Medication medication = medicationService.getMedication(medicationId);

           if(Objects.nonNull(drone.getMedications())){
               throw new DroneHasAlreadyLoadedMicalException(droneId,
                       medicationId);
           }
           drone.addMedication(medication);
           medication.setDrone(drone);
           return drone;

    }
    @Transactional
    public Drone removeMedication(Long droneId, Long medicationId){

        Drone drone = findDrone(droneId).orElseThrow(()->new RuntimeException());;
        Medication medication = medicationService.getMedication(medicationId);
        drone.removeMedication(medication);
        return drone;

    }
   public List<Drone> checkAvailableDroneForLoading(){
        List<Drone> d = (List<Drone>) droneRepository.findAll();
      return  d.stream()
              .filter(e->e.getDroneState().equals(State.IDLE.name()) && e.getBatteryCapacity()>25)
                .collect(Collectors.toList());
   }


    private boolean checkState ( String str) {
        for (State s : State.values()) {
            if (s.name().equalsIgnoreCase(str))
                return true;
        }
        return false;
    }

    private boolean checkModel ( String str) {
        for (Model m : Model.values()) {
            if (m.name().equalsIgnoreCase(str))
                return true;
        }
        return false;
    }
    public int checkWeight ( String model) {
        boolean modelExist =checkModel ( model);
        int value = 0;
        if(modelExist){
            for (Model m : Model.values()) {
                if (m.name().equalsIgnoreCase(model))
                    value = m.getValue();
            }
        }

        return value;
    }

    private boolean analyseEnum(String model,String state){
        if(checkState ( state) && checkModel(model) == true){
            return true;
        }
        return false;
    }
    public String fectchEnumName(int modelNo) {
        String model = "";
        switch (modelNo) {
            case 150:
                model = "LIGHTWEIGHT";
                break;
            case 300:
                model = "MIDDLEWEIGHT";
                break;
            case 350:
                model = "CRUISERWEIGHT";
                break;
            case 450:
                model = "HEAVYWEIGHT";
                break;
            default:
                model = "Invalid daytype";
        }

        return model;

    }


    public String fectchStateName(int modelNo) {
        String model = "";
        switch (modelNo) {
            case 1:
                model = "IDLE";
                break;
            case 2:
                model = "LOADED";
                break;
            case 3:
                model = "DELIVERING";
                break;
            case 4:
                model = "DELIVERED";
                break;
            case 5:
                model = "RETURNING";
                break;
            case 6:
                model = "LOADING";
                break;
            default:
                model = "LOADING";
        }

        return model;

    }


    public int fectchStateNo(String state) {
        int model = 0;
        switch (state) {
            case "IDLE":
                model = 1;
                break;
            case "LOADED":
                model = 2;
                break;
            case "DELIVERING":
                model = 3;
                break;
            case "DELIVERED":
                model = 4;
                break;
            case "RETURNING":
                model = 5;
                break;
            case "LOADING":
                model = 6;
                break;
            default:
                model = 6;
        }

        return model;

    }


    public int randonmCharger(int droneCharge, int droneState){
        System.out.println("===================");
        System.out.println(droneCharge);
       int value = 10;

        //System.out.println(10);
        if (droneCharge <= 0){
            return  value = 25;
        }
        if(droneCharge > 100){
            return  value = 100;
        }
        if(droneState ==State.DELIVERING.getValue()){

           return value = droneCharge--;
        }
        if( droneState  == State.DELIVERED.getValue() )
        {
            return value = droneCharge--;
        }
        if( droneState ==State.DELIVERING.getValue() ){
            return value = droneCharge--;
        }
        if(droneState == State.RETURNING.getValue()){
            return value = droneCharge--;
        }
        if( droneState ==State.IDLE.getValue()){
            return value = droneCharge ++;
        }
        if( droneState ==State.LOADING.getValue()){
             return value = droneCharge + 15;
        }
        if( droneState ==State.LOADED.getValue()){
            return value = droneCharge +20;
        }


        System.out.println(value);
        return value;

    }



    public int randonmStateChange(int State){
        int value = 0;
        if(State == 1){
             value= 6;
        }
        if(State == 6){
            value= 2;
        }
        if(State == 2){
            value= 3;
        }
        if(State == 3){
            value= 4;
        }
        if(State == 5){
            value= 1;
        }
        if(State ==4){
            value= 5;
        }
        if(State == 1){
            value= 6;
        }
        return value;
    }

    public void savePhoto (MultipartFile file, Long medicalId) throws IOException {
           Medication medication= medicationService.getMedication(medicalId);

        String folder = "/medicphoto/";
        String cwd = Path.of("").toAbsolutePath().toString();
       // System.out.println(cwd);
        byte[] imageByte = file.getBytes();
        Path path = Paths.get(folder +"myfile"+medication.getId()+medication.getCode() +file.getOriginalFilename());

        Files.write(path, imageByte);
        medication.setImagePath(path.toString());
        medication.setImagePath(path.toString());
        Medication m= medicationService.saveMedication(medication);
       // System.out.println(m);
    }

    public void loadDroneWithMedication(Long droneId,Long[] medicationIds){
       Optional<Drone> drone = droneRepository.findById(droneId);
       if(!drone.isPresent()){throw new IllegalStateException("Drone with id "+droneId+" not found");}
         for(int i =0 ; i<medicationIds.length ;i++){
             Medication m = medicationService.getMedication(medicationIds[i]);
             m.setDrone(drone.get());
         }

    }

    public String getBatteryState( String droneState,int batteryLevel){
        String chargerName = Charger.CHARGING.name();
        System.out.println("intial Drone State " + droneState);
        System.out.println("initial batterylevel " + batteryLevel);

        if(droneState.equals(State.IDLE)
                || droneState.equals(State.LOADING)
                || droneState.equals(State.LOADED)
                &&batteryLevel <= 0 ){
            return Charger.CHARGING.name();
        }
        if(droneState.equals(State.DELIVERING)
                || droneState.equals(State.DELIVERED)
                || droneState.equals(State.RETURNING)
                &&batteryLevel <= 0 ){
            return Charger.DISCHARGING.name();
        }

        if (droneState.equals(State.IDLE)
                || droneState.equals(State.LOADING)
                || droneState.equals(State.LOADED)
                && batteryLevel >= 100) {

            chargerName = Charger.FULL.name();


        } else if ((droneState.equals(State.RETURNING)
                || droneState.equals(State.DELIVERING)
                || droneState.equals(State.DELIVERED))
                && batteryLevel >= 100) {
            chargerName = Charger.DISCHARGING.name();

        }


            switch (droneState) {
                case "IDLE":
                    chargerName = Charger.CHARGING.name();
                    break;
                case "LOADING":
                    chargerName = Charger.CHARGING.name();
                    break;
                case "LOADED":
                    chargerName = Charger.DISCHARGING.name();
                    break;
                case "DELIVERING":
                    chargerName = Charger.DISCHARGING.name();
                    break;
                case "DELIVERED":
                    chargerName = Charger.DISCHARGING.name();
                    break;
                case "RETURNING":
                    chargerName = Charger.DISCHARGING.name();
                    break;
                default:
                    chargerName = Charger.DISCHARGING.name();
            }

            System.out.println("Final Charget " + chargerName);



        return chargerName;
    }

}
