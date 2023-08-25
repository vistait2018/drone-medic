package com.vista.it.dronemedic.service;

import com.vista.it.dronemedic.constants.Charger;
import com.vista.it.dronemedic.constants.State;
import com.vista.it.dronemedic.constants.StateWithoutReturning;
import com.vista.it.dronemedic.dao.DroneRepository;
import com.vista.it.dronemedic.droneTask.DroneAutomationTask;
import com.vista.it.dronemedic.droneTask.DroneTask;
import com.vista.it.dronemedic.model.Drone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DroneAutomationService {
    @Autowired
    private  DroneService droneService;

    private static final Logger log = LoggerFactory.getLogger(DroneAutomationTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    @Transactional
    public String chargeDroneChangeState() {
        String value = "";
       // try {
            log.info("====================================================");
            log.info("Starting Drone automation at {}", dateFormat.format(new Date()));
            log.info("It Changes State of a drone if the drone state is not returning");
            log.info("It also charge the battery with a random charge between 1 and 100");
            log.info("Condition for charging is that the addition\n" +
                    " of the charge volume and the present charge must be less than 100");
            Optional<Drone> droneToCharge = null;
            List<Drone> chargedDronesToSave = new ArrayList<>();
            List<Drone>  drones =droneService.allDrones();
            for (Drone d : drones) {
                droneToCharge = droneService.findDrone(d.getId());
                int  presentState = droneService.fectchStateNo(d.getDroneState());
                int chargeVolume = droneService.randonmCharger(d.getBatteryCapacity(), presentState);

                if (chargeVolume <= 100) {
                  droneToCharge.get().setBatteryCapacity(chargeVolume);
                }

                int stateno = droneService.fectchStateNo(droneToCharge.get().getDroneState());
                     int randomStateno = droneService.randonmStateChange(stateno);
                String state = droneService.fectchStateName(randomStateno);

                droneToCharge.get().setDroneState(state);
                chargedDronesToSave.add(droneToCharge.get());
               String batteryState = droneService.getBatteryState(droneToCharge.get().getDroneState()
                        ,chargeVolume);
                droneToCharge.get().setBatteryState(batteryState);
            }

          for(Drone changedDrone: chargedDronesToSave){
                  droneService.updateStateAndBatteryInDrone(changedDrone.getId(),
                      changedDrone.getBatteryCapacity(),changedDrone.getDroneState());

              //System.out.println("state to save "+ changedDrone.getId()+"  "+changedDrone.getdroneState());
          }

            log.info("Drone Automated Charging and Changing of state ends at {}", dateFormat.format(new Date()));
            log.info("====================================================");
            return value;
//
//        } catch (Exception ex) {
//          return value;
//        }

    }





}
