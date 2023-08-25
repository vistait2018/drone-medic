package com.vista.it.dronemedic.service;

import com.vista.it.dronemedic.droneTask.DroneTask;
import com.vista.it.dronemedic.model.Drone;
import com.vista.it.dronemedic.model.DroneHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class LogServiceDb  {
    private static final Logger log = LoggerFactory.getLogger(DroneTask.class);
    private static final SimpleDateFormat dateFormat  = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    private   DroneHistoryService droneHistoryService;
    @Autowired
    private DroneService droneService;


    public void writeLog(){
        List<Drone> drones = droneService.allDrones();


        DroneHistory droneHistory = new DroneHistory();
        log.info("====================================================");
        log.info("Writing Drone histories  at in Database {}", dateFormat.format(new Date()));
        for(Drone d : drones){

            droneHistory.setDroneId(d.getId());
            droneHistory.setBatteryState(d.getBatteryCapacity());

            droneHistory.setTime(LocalDateTime.now());
            droneHistory.setDroneState(d.getDroneState() );
            droneHistory.setModel(d.getModel() );
            droneHistory.setWeight(d.getWeight() );
            droneHistory.setSerialNo(d.getSerialNo());
            //droneHistories.add(droneHistory);
            droneHistoryService.saveDroneHistory(droneHistory);
        }



        log.info("Drone history ends at {}", dateFormat.format(new Date()));
        log.info("====================================================");
    }
}
