package com.vista.it.dronemedic.droneTask;

import com.vista.it.dronemedic.constants.Charger;
import com.vista.it.dronemedic.dao.DroneHistoryRepository;
import com.vista.it.dronemedic.dao.DroneRepository;
import com.vista.it.dronemedic.model.Drone;
import com.vista.it.dronemedic.model.DroneHistory;
import com.vista.it.dronemedic.service.DroneHistoryService;
import com.vista.it.dronemedic.service.DroneService;
import com.vista.it.dronemedic.service.LogService;
import com.vista.it.dronemedic.service.LogServiceDb;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;

import org.slf4j.Logger;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
//import org.slf4j.LoggerFactory;

@Component
public class DroneTask {

    @Autowired
    LogService logService;




    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
       logService.writelog();


    }



}
