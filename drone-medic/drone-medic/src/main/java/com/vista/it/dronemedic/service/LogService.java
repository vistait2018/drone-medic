package com.vista.it.dronemedic.service;

import com.vista.it.dronemedic.droneTask.DroneTask;
import com.vista.it.dronemedic.model.Drone;
import com.vista.it.dronemedic.model.DroneHistory;
import com.vista.it.dronemedic.model.Medication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class LogService {
    private static final Logger log = LoggerFactory.getLogger(DroneTask.class);
    private static final SimpleDateFormat dateFormat  = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    private   DroneHistoryService droneHistoryService;
    @Autowired
    private DroneService droneService;
    public void writelog(){
        List<Drone> drones = droneService.allDrones();
        log.info("====================================================");
        log.info("Writing Drone histories  at {}", dateFormat.format(new Date()));
        for(Drone d :drones){
            log.info("Drone Model:  {} State: {} Battery-Capacity:{} Weight: {}g" ,d.getModel()
                    ,d.getDroneState(),d.getBatteryCapacity(),d.getWeight());
          }
        log.info("Drone history ends at {}", dateFormat.format(new Date()));
        log.info("====================================================");
    }
}
